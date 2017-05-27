package furniture;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 更新家具信息：电灯
 * 
 * @author Yukikari Samuya
 * 
 */
public class LightServ extends ServerletMain {
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
		Light light = (Light) furnitureSet.getFurniture(familyName, floor,
				room, type);

		// 删除旧对象
		furnitureSet.removeFurniture(light);

		// 更新连接时间
		light.setLastTime();

		// 获取设备当前状态
		String s_time = (String) session.getAttribute("time");
		String s_brightness = (String) session.getAttribute("brightness");
		String s_switch = (String) session.getAttribute("switch");

		// 获取服务器当前状态
		String a_time = light.getTime();
		String a_brightness = light.getBrightness();
		String a_switch = light.getSwitch();

		// 统一为最新状态
		if (a_time == null || a_time.compareTo(s_time) < 0) {

			light.setTime(s_time);
			light.setBrightness(s_brightness);
			light.setSwitch(s_switch);

		} else {

			session.setAttribute("time", a_time);
			session.setAttribute("brightness", a_brightness);
			session.setAttribute("switch", a_switch);
		}

		furnitureSet.addFurniture(light);
		application.setAttribute("furnitureSet", furnitureSet);
		res.sendRedirect(req.getContextPath() + "/Light.jsp");
	}
}