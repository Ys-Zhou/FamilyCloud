import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * 服务转发
 * 
 * @author Yukikari Samuya
 * 
 */
public class Serverlet extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 设置编码
		req.setCharacterEncoding("GBK");
		res.setCharacterEncoding("GBK");

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String service = jsonIn.getString("service");

		switch (service) {

		case "MemberLog":
			req.getRequestDispatcher("/servlet/MemberLog").forward(req, res);
			break;
		case "AdminSin":
			req.getRequestDispatcher("/servlet/AdminSin").forward(req, res);
			break;
		case "MemberSin":
			req.getRequestDispatcher("/servlet/MemberSin").forward(req, res);
			break;
		case "GetFtp":
			req.getRequestDispatcher("/servlet/GetFtp").forward(req, res);
			break;
		case "GetHttp":
			req.getRequestDispatcher("/servlet/GetHttp").forward(req, res);
			break;
		case "UploadFile":
			req.getRequestDispatcher("/servlet/UploadFile").forward(req, res);
			break;
		case "GetFileInfo":
			req.getRequestDispatcher("/servlet/GetFileInfo").forward(req, res);
			break;
		case "DeleteFile":
			req.getRequestDispatcher("/servlet/DeleteFile").forward(req, res);
			break;
		case "GetServerInfo":
			req.getRequestDispatcher("/servlet/GetServerInfo")
					.forward(req, res);
			break;
		case "ChangePermission":
			req.getRequestDispatcher("/servlet/ChangePermission").forward(req,
					res);
			break;
		case "GetFamilyMembers":
			req.getRequestDispatcher("/servlet/GetFamilyMembers").forward(req,
					res);
			break;
		case "DeleteMember":
			req.getRequestDispatcher("/servlet/DeleteMember").forward(req, res);
			break;
		case "Log":
			req.getRequestDispatcher("/servlet/Log").forward(req, res);
			break;
		case "GetRunFur":
			req.getRequestDispatcher("/servlet/GetRunFur").forward(req, res);
			break;
		case "GetSinFur":
			req.getRequestDispatcher("/servlet/GetSinFur").forward(req, res);
			break;
		case "AddFurniture":
			req.getRequestDispatcher("/servlet/AddFurniture").forward(req, res);
			break;
		case "DeleteFurniture":
			req.getRequestDispatcher("/servlet/DeleteFurniture").forward(req,
					res);
			break;
		case "GetFurInfo":
			req.getRequestDispatcher("/servlet/GetFurInfo").forward(req, res);
			break;
		case "SetFurInfo":
			req.getRequestDispatcher("/servlet/SetFurInfo").forward(req, res);
			break;
		}
	}
}