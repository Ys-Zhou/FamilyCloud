package furniture;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ���¼Ҿ���Ϣ������
 * 
 * @author Yukikari Samuya
 * 
 */
public class IceboxServ extends ServerletMain {
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
		Icebox icebox = (Icebox) furnitureSet.getFurniture(familyName, floor,
				room, type);

		// ɾ���ɶ���
		furnitureSet.removeFurniture(icebox);

		// ��������ʱ��
		icebox.setLastTime();

		// ��ȡ�豸��ǰ״̬
		String s_time = (String) session.getAttribute("time");
		String s_temperature = (String) session.getAttribute("temperature");
		String s_switch = (String) session.getAttribute("switch");
		String s_model = (String) session.getAttribute("model");
		String s_set_time = (String) session.getAttribute("set_time");

		// ��ȡ��������ǰ״̬
		String a_time = icebox.getTime();
		String a_temperature = icebox.getTemperature();
		String a_switch = icebox.getSwitch();
		String a_model = icebox.getModel();
		String a_set_time = icebox.getSet_time();

		// ͳһΪ����״̬
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