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
 * ��ͨ�û�ע��
 * 
 * @author Yukikari Samuya
 * 
 */
public class MemberSin extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		// ����һ��javabeanʵ��
		Member member = new Member();

		member.setEmail(jsonIn.getString("email"));
		member.setPassword(jsonIn.getString("password"));
		member.setName(jsonIn.getString("name"));
		member.setFamilyName(jsonIn.getString("familyName"));
		member.setPermission("normal");

		// ��ȡsession����������
		HttpSession session = req.getSession();

		session.setAttribute("ipAddress", req.getRemoteAddr());
		session.setAttribute("email", jsonIn.getString("email"));

		// ͨ��javabean����һ��DoMemberʵ��������JSON���
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

				AddLog.addLog(session, "�û�ע��", "ע��ɹ�");
			} else {

				jsonOut.put("MemberSinStat", -1);

				AddLog.addLog(session, "�û�ע��", "ע��ʧ��");
			}
		} catch (Exception e) {

			jsonOut.put("MemberSinStat", 0);

			try {
				AddLog.addLog(session, "�û�ע��", "ע��ʧ��");
			} catch (Exception e1) {

			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}
