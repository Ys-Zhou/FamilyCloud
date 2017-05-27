package familyCloud;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

/**
 * 静态方法：添加日志
 * 
 * @author Yukikari Samuya
 * 
 */
public class AddLog {

	public static void addLog(HttpSession session, String operation,
			String result) throws Exception {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String date = df.format(new Date());

		String ipAddress = (String) session.getAttribute("ipAddress");
		String email = (String) session.getAttribute("email");

		DBConnector dBConnector = DBConnector.Instance();

		String sql = "INSERT INTO log VALUES('" + date + "','" + ipAddress
				+ "','" + email + "','" + operation + "','" + result + "')";

		dBConnector.runUpdate(sql);
	}
}
