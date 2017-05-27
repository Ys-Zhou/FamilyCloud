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
 * 获取FTP服务器连接信息
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetFtp extends ServerletMain {

	private String ip = "192.168.200.199";
	private String user = "anonymous";
	private String password = "";
	private String fold = null;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));
		String email = jsonIn.getString("email");
		String type = jsonIn.getString("type");
		String familyName = jsonIn.getString("familyName");

		fold = "/" + familyName + "/" + type + "/" + email;

		// 获取session并设置属性
		HttpSession session = req.getSession();

		// 创建JSON对象并赋值
		JSONObject jsonOut = new JSONObject();

		try {

			Member member = new Member();

			member.setEmail(email);
			member.setFamilyName(familyName);

			DoMember doMember = new DoMember(member);

			if (doMember.ifExsit()) {

				jsonOut.put("stat", 1);
				jsonOut.put("ip", ip);
				jsonOut.put("user", user);
				jsonOut.put("password", password);
				jsonOut.put("fold", fold);

				AddLog.addLog(session, "上传/下载文件", "成功");

			} else {

				jsonOut.put("stat", 0);// 用户不存在

				AddLog.addLog(session, "上传/下载文件", "失败");
			}
		} catch (Exception e) {
			// This error dosen't need to be handled
			e.printStackTrace();
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}