import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��дHttpServlet
 * 
 * @author Yukikari Samuya
 * 
 */
public class ServerletMain extends HttpServlet {

	/**
	 * ��д��ʼ������
	 */
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}

	/**
	 * ��дdoGet
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
}
