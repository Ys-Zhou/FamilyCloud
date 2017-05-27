package furniture;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 更新家具信息：冰箱
 * 
 * @author Yukikari Samuya
 * 
 */
public class IceboxServ extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取session
		HttpSession session = req.getSession();

		// 获取application
		ServletContext application = getServletContext();

		// 获取家具集
		FurnitureSet furnitureSet = (FurnitureSet) application
				.getAttribute("furnitureSet");

		// 获取设备属性
		String familyName = (String) session.getAttribute("familyName");
		String floor = (String) session.getAttribute("floor");
		String room = (String) session.getAttribute("room");
		String type = (String) session.getAttribute("type");

		// 获得指定的对象
		Icebox icebox = (Icebox) furnitureSet.getFurniture(familyName, floor,
				room, type);

		// 删除旧对象
		furnitureSet.removeFurniture(icebox);

		// 更新连接时间
		icebox.setLastTime();

		// 获取设备当前状态
		String s_time = (String) session.getAttribute("time");
		String s_temperature = (String) session.getAttribute("temperature");
		String s_switch = (String) session.getAttribute("switch");
		String s_model = (String) session.getAttribute("model");
		String s_set_time = (String) session.getAttribute("set_time");

		// 获取服务器当前状态
		String a_time = icebox.getTime();
		String a_temperature = icebox.getTemperature();
		String a_switch = icebox.getSwitch();
		String a_model = icebox.getModel();
		String a_set_time = icebox.getSet_time();

		// 统一为最新状态
		if (a_time == null || a_time.compareTo(s_time) < 0) {

			icebox.setTime(s_time);
			icebox.setTemperature(s_temperature);
			icebox.setSwitch(s_switch);
			icebox.setModel(s_model);
			icebox.setSet_time(s_set_time);

		} else {

			session.setAttribute("time", a_time);
			session.setAttribute("temperature", a_temperature);
			session.setAttribute("switch", a_switch);
			session.setAttribute("model", a_model);
			session.setAttribute("set_time", a_set_time);
		}

		furnitureSet.addFurniture(icebox);
		application.setAttribute("furnitureSet", furnitureSet);
		res.sendRedirect(req.getContextPath() + "/Icebox.jsp");
	}
}