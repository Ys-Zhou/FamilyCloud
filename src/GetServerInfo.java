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
 * 获取平台状态信息
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetServerInfo extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));
		String familyName = jsonIn.getString("familyName");

		// 调用C#类库
		ActiveXComponent dotnetCom = new ActiveXComponent("TestCom.Encrypt");

		// 调用C#函数获得系统信息
		String CPU = Dispatch.call(dotnetCom, "GetEncrypt").toString();

		String availableMemory = Dispatch.call(dotnetCom, "GetAMemory")
				.toString();

		String committedMemory = Dispatch.call(dotnetCom, "GetCMemory")
				.toString();

		// 获取session并设置属性
		HttpSession session = req.getSession();

		// 输出
		JSONObject jsonOut = new JSONObject();

		jsonOut.put("CPU", CPU);
		jsonOut.put("availableMemory", availableMemory);
		jsonOut.put("committedMemory", committedMemory);

		long space = 1073741824;
		jsonOut.put("space", space);

		// 获取文件信息
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

			AddLog.addLog(session, "获取云平台信息", "成功");

		} catch (Exception e) {

		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}