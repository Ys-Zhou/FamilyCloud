import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import familyCloud.AddLog;
import familyCloud.DoFile;
import familyCloud.DoMember;
import familyCloud.Member;
import familyCloud.UserFile;

/**
 * 普通用户注册
 * 
 * @author Yukikari Samuya
 * 
 */
public class MemberSin extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		// 创建一个javabean实例
		Member member = new Member();

		member.setEmail(jsonIn.getString("email"));
		member.setPassword(jsonIn.getString("password"));
		member.setName(jsonIn.getString("name"));
		member.setFamilyName(jsonIn.getString("familyName"));
		member.setPermission("normal");

		// 获取session并设置属性
		HttpSession session = req.getSession();

		session.setAttribute("ipAddress", req.getRemoteAddr());
		session.setAttribute("email", jsonIn.getString("email"));

		// 通过javabean创建一个DoMember实例，并用JSON输出
		JSONObject jsonOut = new JSONObject();

		try {
			DoMember doMember = new DoMember(member);

			if (doMember.ifPass(jsonIn.getString("familyKey"))) {

				doMember.memberSin();

				jsonOut.put("MemberSinStat", 1);

				UserFile userFile = new UserFile();

				userFile.setFamilyName(member.getFamilyName());
				userFile.setEmail(member.getEmail());

				DoFile doFile = new DoFile(userFile);

				doFile.createFold();

				AddLog.addLog(session, "用户注册", "注册成功");
			} else {

				jsonOut.put("MemberSinStat", -1);

				AddLog.addLog(session, "用户注册", "注册失败");
			}
		} catch (Exception e) {

			jsonOut.put("MemberSinStat", 0);

			try {
				AddLog.addLog(session, "用户注册", "注册失败");
			} catch (Exception e1) {

			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}
