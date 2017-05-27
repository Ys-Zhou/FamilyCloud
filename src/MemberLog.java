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
 * �û���¼
 * 
 * @author Yukikari Samuya
 * 
 */
public class MemberLog extends ServerletMain {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		// ����һ��javabeanʵ��
		Member member = new Member();

		member.setEmail(jsonIn.getString("email"));
		member.setPassword(jsonIn.getString("password"));

		// ��ȡsession����������
		HttpSession session = req.getSession();

		session.setAttribute("ipAddress", req.getRemoteAddr());
		session.setAttribute("email", jsonIn.getString("email"));

		// ͨ��javabean����һ��DoMemberʵ��������JSON���
		JSONObject jsonOut = new JSONObject();

		try {
			DoMember doMember = new DoMember(member);

			if (doMember.memberLog()) {

				if (doMember.checkType()) {

					jsonOut.put("logStat", 1);// ����ԱȨ��
				} else {

					jsonOut.put("logStat", 0);// ��ͨȨ��
				}

				jsonOut.put("familyName", doMember.getFamily());

				jsonOut.put("name", doMember.getName());

				AddLog.addLog(session, "�û���¼", "��¼�ɹ�");
			} else {

				jsonOut.put("logStat", -1);

				AddLog.addLog(session, "�û���¼", "��¼ʧ��");
			}

		} catch (Exception e) {

			jsonOut.put("logStat", -1);

			try {

				AddLog.addLog(session, "�û���¼", "��¼ʧ��");

			} catch (Exception e1) {

			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}