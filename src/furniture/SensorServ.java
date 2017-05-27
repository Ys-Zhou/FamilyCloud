package furniture;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ���¼Ҿ���Ϣ��������
 * 
 * @author Yukikari Samuya
 * 
 */
public class SensorServ extends ServerletMain {
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
		Sensor sensor = (Sensor) furnitureSet.getFurniture(familyName, floor,
				room, type);

		// ɾ���ɶ���
		furnitureSet.removeFurniture(sensor);

		// ��������ʱ��
		sensor.setLastTime();

		// ��ȡ�豸��ǰ״̬
		String s_time = (String) session.getAttribute("time");
		String s_switch = (String) session.getAttribute("switch");
		String s_alarm = (String) session.getAttribute("alarm");
		String s_set_threshold = (String) session.getAttribute("set_threshold");

		// ��ȡ��������ǰ״̬
		String a_time = sensor.getTime();
		String a_switch = sensor.getSwitch();
		String a_alarm = sensor.getAlarm();
		String a_set_threshold = sensor.getSet_threshold();

		// ͳһΪ����״̬
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