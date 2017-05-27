import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 重写HttpServlet
 * 
 * @author Yukikari Samuya
 * 
 */
public class ServerletMain extends HttpServlet {

	/**
	 * 重写初始化函数
	 */
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}

	/**
	 * 重写doGet
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
}
