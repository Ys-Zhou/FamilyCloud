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
 * �޸��û�Ȩ��
 * 
 * @author Yukikari Samuya
 * 
 */
public class ChangePermission extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String email = jsonIn.getString("email");
		String permission = null;
		if (jsonIn.getBoolean("permission"))
			permission = "admin";
		else
			permission = "normal";

		// ����javabeanʵ��
		Member member = new Member();

		member.setEmail(email);
		member.setPermission(permission);

		JSONObject jsonOut = new JSONObject();

		// ��ȡsession����������
		HttpSession session = req.getSession();

		// ����DoMemberʵ��
		try {

			DoMember doMember = new DoMember(member);

			if (doMember.setPermission()) {

				jsonOut.put("ChangePermissionStat", 1);

				AddLog.addLog(session, "�޸�Ȩ��", "�ɹ�");

			} else {

				jsonOut.put("ChangePermissionStat", 0);

				AddLog.addLog(session, "�޸�Ȩ��", "ʧ��");
			}
		} catch (Exception e) {

			jsonOut.put("ChangePermissionStat", 0);

			try {

				AddLog.addLog(session, "�޸�Ȩ��", "ʧ��");

			} catch (Exception e1) {

			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}
