import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import familyCloud.AdminMember;
import familyCloud.DoAdmin;

/**
 * ��ȡ��ͥ�û��б�
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetFamilyMembers extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String familyName = jsonIn.getString("familyName");

		// ����javabeanʵ��
		AdminMember adminMember = new AdminMember();

		adminMember.setFamilyName(familyName);

		JSONObject jsonOut = new JSONObject();

		// ����DoAdminʵ��
		try {

			DoAdmin doAdmin = new DoAdmin(adminMember);

			jsonOut = doAdmin.getMembers();

		} catch (Exception e) {

		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}
