import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import familyCloud.DoFile;
import familyCloud.UserFile;

/**
 * ��ȡ�ļ���Ϣ�б�
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetFileInfo extends ServerletMain {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String familyName = jsonIn.getString("familyName");

		// ����һ��javabeanʵ��
		UserFile userFile = new UserFile();

		userFile.setFamilyName(familyName);

		// ͨ��javabean����һ��DoFileʵ��
		JSONObject jsonOut = new JSONObject();

		try {
			userFile.setType("music");
			DoFile doFile = new DoFile(userFile);
			jsonOut.put("music", doFile.getFileInfo());

			userFile.setType("video");
			doFile = new DoFile(userFile);
			jsonOut.put("video", doFile.getFileInfo());

			userFile.setType("photo");
			doFile = new DoFile(userFile);
			jsonOut.put("photo", doFile.getFileInfo());

			userFile.setType("file");
			doFile = new DoFile(userFile);
			jsonOut.put("file", doFile.getFileInfo());

		} catch (Exception e) {

			e.printStackTrace();
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}