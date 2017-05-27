import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import familyCloud.AdminMember;
import familyCloud.DoAdmin;

/**
 * 获取家庭用户列表
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetFamilyMembers extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String familyName = jsonIn.getString("familyName");

		// 创建javabean实例
		AdminMember adminMember = new AdminMember();

		adminMember.setFamilyName(familyName);

		JSONObject jsonOut = new JSONObject();

		// 创建DoAdmin实例
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
