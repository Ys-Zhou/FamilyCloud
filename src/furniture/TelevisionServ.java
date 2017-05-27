package furniture;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 更新家具信息：电视
 * 
 * @author Yukikari Samuya
 * 
 */
public class TelevisionServ extends ServerletMain {
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
		Television television = (Television) furnitureSet.getFurniture(
				familyName, floor, room, type);

		// 删除旧对象
		furnitureSet.removeFurniture(television);

		// 更新连接时间
		television.setLastTime();

		// 获取设备当前状态
		String s_time = (String) session.getAttribute("time");
		String s_channel = (String) session.getAttribute("channel");
		String s_switch = (String) session.getAttribute("switch");
		String s_model = (String) session.getAttribute("model");
		String s_voice = (String) session.getAttribute("voice");

		// 获取服务器当前状态
		String a_time = television.getTime();
		String a_channel = television.getChannel();
		String a_switch = television.getSwitch();
		String a_model = television.getModel();
		String a_voice = television.getVoice();

		// 统一为最新状态
		if (a_time == null || a_time.compareTo(s_time) < 0) {

			television.setTime(s_time);
			television.setChannel(s_channel);
			television.setSwitch(s_switch);
			television.setModel(s_model);
			television.setVoice(s_voice);

		} else {

			session.setAttribute("time", a_time);
			session.setAttribute("channel", a_channel);
			session.setAttribute("switch", a_switch);
			session.setAttribute("model", a_model);
			session.setAttribute("voice", a_voice);
		}

		// 更新设备列表
		furnitureSet.addFurniture(television);
		application.setAttribute("furnitureSet", furnitureSet);
		res.sendRedirect(req.getContextPath() + "/Television.jsp");
	}
}