import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import familyCloud.AddLog;
import familyCloud.DoFile;
import familyCloud.UserFile;

/**
 * ��ȡƽ̨״̬��Ϣ
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetServerInfo extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));
		String familyName = jsonIn.getString("familyName");

		// ����C#���
		ActiveXComponent dotnetCom = new ActiveXComponent("TestCom.Encrypt");

		// ����C#�������ϵͳ��Ϣ
		String CPU = Dispatch.call(dotnetCom, "GetEncrypt").toString();

		String availableMemory = Dispatch.call(dotnetCom, "GetAMemory")
				.toString();

		String committedMemory = Dispatch.call(dotnetCom, "GetCMemory")
				.toString();

		// ��ȡsession����������
		HttpSession session = req.getSession();

		// ���
		JSONObject jsonOut = new JSONObject();

		jsonOut.put("CPU", CPU);
		jsonOut.put("availableMemory", availableMemory);
		jsonOut.put("committedMemory", committedMemory);

		long space = 1073741824;
		jsonOut.put("space", space);

		// ��ȡ�ļ���Ϣ
		UserFile userFile = new UserFile();

		userFile.setFamilyName(familyName);

		try {
			userFile.setType("music");
			DoFile doFile = new DoFile(userFile);
			long musicSize = doFile.getSize();
			jsonOut.put("musicSize", musicSize);

			userFile.setType("video");
			doFile = new DoFile(userFile);
			long videoSize = doFile.getSize();
			jsonOut.put("videoSize", videoSize);

			userFile.setType("photo");
			doFile = new DoFile(userFile);
			long photoSize = doFile.getSize();
			jsonOut.put("photoSize", photoSize);

			userFile.setType("file");
			doFile = new DoFile(userFile);
			long fileSize = doFile.getSize();
			jsonOut.put("fileSize", fileSize);

			AddLog.addLog(session, "��ȡ��ƽ̨��Ϣ", "�ɹ�");

		} catch (Exception e) {

		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}