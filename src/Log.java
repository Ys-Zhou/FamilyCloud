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
 * ��ȡ��־
 * 
 * @author Yukikari Samuya
 * 
 */
public class Log extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));
		String familyName = jsonIn.getString("familyName");

		// ����javabeanʵ��
		AdminMember adminMember = new AdminMember();

		adminMember.setFamilyName(familyName);

		JSONArray jsonArray = new JSONArray();

		// ����DoAdminʵ��
		try {

			DoAdmin doAdmin = new DoAdmin(adminMember);

			jsonArray = doAdmin.getLog();

		} catch (Exception e) {

		}

		//res.setCharacterEncoding("gbk");

		PrintWriter out = res.getWriter();

		out.print(jsonArray.toString());

		out.flush();

		out.close();
	}

}
