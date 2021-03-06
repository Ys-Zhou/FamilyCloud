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
 * 删除用户
 * 
 * @author Yukikari Samuya
 * 
 */
public class DeleteMember extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String email = jsonIn.getString("email");

		// 创建javabean实例
		Member member = new Member();

		member.setEmail(email);

		// 获取session并设置属性
		HttpSession session = req.getSession();

		// 创建DoMember实例
		JSONObject jsonOut = new JSONObject();

		try {

			DoMember doMember = new DoMember(member);

			UserFile userFile = new UserFile();

			userFile.setEmail(email);

			DoFile doFile = new DoFile(userFile);

			doFile.deletedFolds();

			doMember.deleteMember();

			jsonOut.put("DeleteMemberStat", 1);

			AddLog.addLog(session, "删除用户", "成功");

		} catch (Exception e) {

			jsonOut.put("DeleteMemberStat", 0);

			e.printStackTrace();

			try {
				AddLog.addLog(session, "删除用户", "失败");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}