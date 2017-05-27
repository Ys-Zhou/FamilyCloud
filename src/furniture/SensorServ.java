package furniture;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 更新家具信息：传感器
 * 
 * @author Yukikari Samuya
 * 
 */
public class SensorServ extends ServerletMain {
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
		Sensor sensor = (Sensor) furnitureSet.getFurniture(familyName, floor,
				room, type);

		// 删除旧对象
		furnitureSet.removeFurniture(sensor);

		// 更新连接时间
		sensor.setLastTime();

		// 获取设备当前状态
		String s_time = (String) session.getAttribute("time");
		String s_switch = (String) session.getAttribute("switch");
		String s_alarm = (String) session.getAttribute("alarm");
		String s_set_threshold = (String) session.getAttribute("set_threshold");

		// 获取服务器当前状态
		String a_time = sensor.getTime();
		String a_switch = sensor.getSwitch();
		String a_alarm = sensor.getAlarm();
		String a_set_threshold = sensor.getSet_threshold();

		// 统一为最新状态
		if (a_time == null || a_time.compareTo(s_time) < 0) {

			sensor.setTime(s_time);
			sensor.setSwitch(s_switch);
			sensor.setAlarm(s_alarm);
			sensor.setSet_threshold(s_set_threshold);

		} else {

			session.setAttribute("time", a_time);
			session.setAttribute("switch", a_switch);
			session.setAttribute("alarm", a_alarm);
			session.setAttribute("set_threshold", a_set_threshold);
		}

		furnitureSet.addFurniture(sensor);
		application.setAttribute("furnitureSet", furnitureSet);
		res.sendRedirect(req.getContextPath() + "/Sensor.jsp");
	}
}