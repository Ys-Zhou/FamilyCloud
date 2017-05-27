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
 * ɾ���û�
 * 
 * @author Yukikari Samuya
 * 
 */
public class DeleteMember extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String email = jsonIn.getString("email");

		// ����javabeanʵ��
		Member member = new Member();

		member.setEmail(email);

		// ��ȡsession����������
		HttpSession session = req.getSession();

		// ����DoMemberʵ��
		JSONObject jsonOut = new JSONObject();

		try {

			DoMember doMember = new DoMember(member);

			UserFile userFile = new UserFile();

			userFile.setEmail(email);

			DoFile doFile = new DoFile(userFile);

			doFile.deletedFolds();

			doMember.deleteMember();

			jsonOut.put("DeleteMemberStat", 1);

			AddLog.addLog(session, "ɾ���û�", "�ɹ�");

		} catch (Exception e) {

			jsonOut.put("DeleteMemberStat", 0);

			e.printStackTrace();

			try {
				AddLog.addLog(session, "ɾ���û�", "ʧ��");
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