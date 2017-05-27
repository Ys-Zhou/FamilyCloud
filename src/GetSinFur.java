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
 * 获取已经注册的智能家具
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetSinFur extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));
		String familyName = jsonIn.getString("familyName");

		// 创建adminMember
		AdminMember adminMember = new AdminMember();

		adminMember.setFamilyName(familyName);

		// 新建输出
		JSONArray jsonArray = new JSONArray();

		// 创建Doadmin实例
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
