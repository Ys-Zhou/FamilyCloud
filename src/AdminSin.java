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
 * ����Աע��
 * 
 * @author Yukikari Samuya
 * 
 */
public class AdminSin extends ServerletMain {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		// ����һ��javabeanʵ��
		AdminMember adminMember = new AdminMember();

		adminMember.setEmail(jsonIn.getString("email"));
		adminMember.setPassword(jsonIn.getString("password"));
		adminMember.setName(jsonIn.getString("name"));
		adminMember.setFamilyName(jsonIn.getString("familyName"));
		adminMember.setPermission("admin");
		adminMember.setFamilyKey(jsonIn.getString("familyKey"));
		adminMember.setIpAddress(jsonIn.getString("ipAddress"));

		// ��ȡsession����������
		HttpSession session = req.getSession();

		session.setAttribute("ipAddress", req.getRemoteAddr());
		session.setAttribute("email", jsonIn.getString("email"));

		// ͨ��javabean����һ��DoAdminʵ��������JSON���
		JSONObject jsonOut = new JSONObject();

		try {
			DoAdmin doAdmin = new DoAdmin(adminMember);
			doAdmin.adminSin();

			jsonOut.put("AdminSinStat", 1);// �ɹ�

			UserFile userFile = new UserFile();

			userFile.setFamilyName(adminMember.getFamilyName());
			userFile.setEmail(adminMember.getEmail());

			DoFile doFile = new DoFile(userFile);

			doFile.createFold();

			AddLog.addLog(session, "����ע��", "ע��ɹ�");

		} catch (Exception e) {

			jsonOut.put("AdminSinStat", 0);// ʧ��

			try {
				AddLog.addLog(session, "����ע��", "ע��ʧ��");

			} catch (Exception e1) {

			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}
