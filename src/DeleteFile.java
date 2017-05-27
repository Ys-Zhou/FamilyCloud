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
 * 删除文件
 * 
 * @author Yukikari Samuya
 * 
 */
public class DeleteFile extends ServerletMain {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String familyName = jsonIn.getString("familyName");
		String email = jsonIn.getString("email");
		String type = jsonIn.getString("type");
		String fileNameSet = jsonIn.getString("fileName");

		JSONArray jsonArray = JSONArray.fromObject(fileNameSet);

		// 获取session并设置属性
		HttpSession session = req.getSession();

		// 创建一个DoFile实例，执行操作
		JSONObject jsonOut = new JSONObject();

		for (int i = 0; i < jsonArray.size(); i++) {

			String fileName = jsonArray.getString(i);

			// 创建一个javabean实例
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

					AddLog.addLog(session, "删除文件", "失败");

					break;
				}
			} catch (Exception e) {

				jsonOut.put("DeleteFileStat", 0);

				try {

					AddLog.addLog(session, "删除文件", "失败");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			}
		}

		jsonOut.put("DeleteFileStat", 1);

		try {
			AddLog.addLog(session, "删除文件", "成功");
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