import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import familyCloud.AdminMember;
import familyCloud.DoAdmin;

/**
 * ��ȡ�Ѿ�ע������ܼҾ�
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetSinFur extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));
		String familyName = jsonIn.getString("familyName");

		// ����adminMember
		AdminMember adminMember = new AdminMember();

		adminMember.setFamilyName(familyName);

		// �½����
		JSONArray jsonArray = new JSONArray();

		// ����Doadminʵ��
		try {

			DoAdmin doAdmin = new DoAdmin(adminMember);

			jsonArray = doAdmin.getSinFur();

		} catch (Exception e) {

		}

		PrintWriter out = res.getWriter();

		out.print(jsonArray.toString());

		out.flush();

		out.close();
	}
}
