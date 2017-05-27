import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import familyCloud.AddLog;
import familyCloud.AdminMember;
import familyCloud.DoAdmin;
import familyCloud.DoFile;
import familyCloud.UserFile;

/**
 * 管理员注册
 * 
 * @author Yukikari Samuya
 * 
 */
public class AdminSin extends ServerletMain {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		// 创建一个javabean实例
		AdminMember adminMember = new AdminMember();

		adminMember.setEmail(jsonIn.getString("email"));
		adminMember.setPassword(jsonIn.getString("password"));
		adminMember.setName(jsonIn.getString("name"));
		adminMember.setFamilyName(jsonIn.getString("familyName"));
		adminMember.setPermission("admin");
		adminMember.setFamilyKey(jsonIn.getString("familyKey"));
		adminMember.setIpAddress(jsonIn.getString("ipAddress"));

		// 获取session并设置属性
		HttpSession session = req.getSession();

		session.setAttribute("ipAddress", req.getRemoteAddr());
		session.setAttribute("email", jsonIn.getString("email"));

		// 通过javabean创建一个DoAdmin实例，并用JSON输出
		JSONObject jsonOut = new JSONObject();

		try {
			DoAdmin doAdmin = new DoAdmin(adminMember);
			doAdmin.adminSin();

			jsonOut.put("AdminSinStat", 1);// 成功

			UserFile userFile = new UserFile();

			userFile.setFamilyName(adminMember.getFamilyName());
			userFile.setEmail(adminMember.getEmail());

			DoFile doFile = new DoFile(userFile);

			doFile.createFold();

			AddLog.addLog(session, "户主注册", "注册成功");

		} catch (Exception e) {

			jsonOut.put("AdminSinStat", 0);// 失败

			try {
				AddLog.addLog(session, "户主注册", "注册失败");

			} catch (Exception e1) {

			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}
