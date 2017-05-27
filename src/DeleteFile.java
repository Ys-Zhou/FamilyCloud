import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import familyCloud.AddLog;
import familyCloud.DoFile;
import familyCloud.UserFile;

/**
 * ɾ���ļ�
 * 
 * @author Yukikari Samuya
 * 
 */
public class DeleteFile extends ServerletMain {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String familyName = jsonIn.getString("familyName");
		String email = jsonIn.getString("email");
		String type = jsonIn.getString("type");
		String fileNameSet = jsonIn.getString("fileName");

		JSONArray jsonArray = JSONArray.fromObject(fileNameSet);

		// ��ȡsession����������
		HttpSession session = req.getSession();

		// ����һ��DoFileʵ����ִ�в���
		JSONObject jsonOut = new JSONObject();

		for (int i = 0; i < jsonArray.size(); i++) {

			String fileName = jsonArray.getString(i);

			// ����һ��javabeanʵ��
			UserFile userFile = new UserFile();

			userFile.setEmail(email);
			userFile.setType(type);
			userFile.setFileName(fileName);
			userFile.setFamilyName(familyName);

			DoFile doFile;
			try {

				doFile = new DoFile(userFile);

				if (doFile.deleteFile()) {

				} else {

					jsonOut.put("DeleteFileStat", 0);

					AddLog.addLog(session, "ɾ���ļ�", "ʧ��");

					break;
				}
			} catch (Exception e) {

				jsonOut.put("DeleteFileStat", 0);

				try {

					AddLog.addLog(session, "ɾ���ļ�", "ʧ��");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			}
		}

		jsonOut.put("DeleteFileStat", 1);

		try {
			AddLog.addLog(session, "ɾ���ļ�", "�ɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}