package furniture;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ���¼Ҿ���Ϣ���յ�
 * 
 * @author Yukikari Samuya
 * 
 */
public class AirconditionServ extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡsession
		HttpSession session = req.getSession();

		// ��ȡapplication
		ServletContext application = getServletContext();

		// ��ȡ�Ҿ߼�
		FurnitureSet furnitureSet = (FurnitureSet) application
				.getAttribute("furnitureSet");

		// ��ȡ�豸����
		String familyName = (String) session.getAttribute("familyName");
		String floor = (String) session.getAttribute("floor");
		String room = (String) session.getAttribute("room");
		String type = (String) session.getAttribute("type");

		// ���ָ���Ķ���
		Aircondition aircondition = (Aircondition) furnitureSet.getFurniture(
				familyName, floor, room, type);

		// ɾ���ɶ���
		furnitureSet.removeFurniture(aircondition);

		// ��������ʱ��
		aircondition.setLastTime();

		// ��ȡ�豸��ǰ״̬
		String s_time = (String) session.getAttribute("time");
		String s_temperature = (String) session.getAttribute("temperature");
		String s_switch = (String) session.getAttribute("switch");
		String s_wind = (String) session.getAttribute("wind");
		String s_windrate = (String) session.getAttribute("windrate");
		String s_model = (String) session.getAttribute("model");
		String s_set_time = (String) session.getAttribute("set_time");

		// ��ȡ��������ǰ״̬
		String a_time = aircondition.getTime();
		String a_temperature = aircondition.getTemperature();
		String a_switch = aircondition.getSwitch();
		String a_wind = aircondition.getWind();
		String a_windrate = aircondition.getWindrate();
		String a_model = aircondition.getModel();
		String a_set_time = aircondition.getSet_time();

		// ͳһΪ����״̬
		if (a_time == null || a_time.compareTo(s_time) < 0) {

			aircondition.setTime(s_time);
			aircondition.setTemperature(s_temperature);
			aircondition.setSwitch(s_switch);
			aircondition.setWind(s_wind);
			aircondition.setWindrate(s_windrate);
			aircondition.setModel(s_model);
			aircondition.setSet_time(s_set_time);

		} else {

			session.setAttribute("time", a_time);
			session.setAttribute("temperature", a_temperature);
			session.setAttribute("switch", a_switch);
			session.setAttribute("wind", a_wind);
			session.setAttribute("windrate", a_windrate);
			session.setAttribute("model", a_model);
			session.setAttribute("set_time", a_set_time);
		}

		furnitureSet.addFurniture(aircondition);
		application.setAttribute("furnitureSet", furnitureSet);
		res.sendRedirect(req.getContextPath() + "/Aircondition.jsp");
	}
}