import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import familyCloud.AddLog;
import familyCloud.DoFile;
import familyCloud.UserFile;

/**
 * 获取在线播放url
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetHttp extends ServerletMain {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String familyName = jsonIn.getString("familyName");
		String type = jsonIn.getString("type");
		String email = jsonIn.getString("email");
		String fileName = jsonIn.getString("fileName");

		UserFile userFile = new UserFile();

		userFile.setFamilyName(familyName);
		userFile.setType(type);
		userFile.setEmail(email);
		userFile.setFileName(fileName);

		JSONObject jsonOut = new JSONObject();

		// 获取session并设置属性
		HttpSession session = req.getSession();

		try {

			DoFile doFile = new DoFile(userFile);

			String url = doFile.getHttp();

			jsonOut.put("url", url);

			AddLog.addLog(session, "在线播放", "成功");

		} catch (Exception e) {

		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}
