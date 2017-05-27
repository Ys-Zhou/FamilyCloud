import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import familyCloud.AddLog;
import familyCloud.DoMember;
import familyCloud.Member;

/**
 * 修改用户权限
 * 
 * @author Yukikari Samuya
 * 
 */
public class ChangePermission extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String email = jsonIn.getString("email");
		String permission = null;
		if (jsonIn.getBoolean("permission"))
			permission = "admin";
		else
			permission = "normal";

		// 创建javabean实例
		Member member = new Member();

		member.setEmail(email);
		member.setPermission(permission);

		JSONObject jsonOut = new JSONObject();

		// 获取session并设置属性
		HttpSession session = req.getSession();

		// 创建DoMember实例
		try {

			DoMember doMember = new DoMember(member);

			if (doMember.setPermission()) {

				jsonOut.put("ChangePermissionStat", 1);

				AddLog.addLog(session, "修改权限", "成功");

			} else {

				jsonOut.put("ChangePermissionStat", 0);

				AddLog.addLog(session, "修改权限", "失败");
			}
		} catch (Exception e) {

			jsonOut.put("ChangePermissionStat", 0);

			try {

				AddLog.addLog(session, "修改权限", "失败");

			} catch (Exception e1) {

			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}
