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
 * 用户登录
 * 
 * @author Yukikari Samuya
 * 
 */
public class MemberLog extends ServerletMain {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		// 创建一个javabean实例
		Member member = new Member();

		member.setEmail(jsonIn.getString("email"));
		member.setPassword(jsonIn.getString("password"));

		// 获取session并设置属性
		HttpSession session = req.getSession();

		session.setAttribute("ipAddress", req.getRemoteAddr());
		session.setAttribute("email", jsonIn.getString("email"));

		// 通过javabean创建一个DoMember实例，并用JSON输出
		JSONObject jsonOut = new JSONObject();

		try {
			DoMember doMember = new DoMember(member);

			if (doMember.memberLog()) {

				if (doMember.checkType()) {

					jsonOut.put("logStat", 1);// 管理员权限
				} else {

					jsonOut.put("logStat", 0);// 普通权限
				}

				jsonOut.put("familyName", doMember.getFamily());

				jsonOut.put("name", doMember.getName());

				AddLog.addLog(session, "用户登录", "登录成功");
			} else {

				jsonOut.put("logStat", -1);

				AddLog.addLog(session, "用户登录", "登录失败");
			}

		} catch (Exception e) {

			jsonOut.put("logStat", -1);

			try {

				AddLog.addLog(session, "用户登录", "登录失败");

			} catch (Exception e1) {

			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}