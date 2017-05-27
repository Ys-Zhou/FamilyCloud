package furniture;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ���¼Ҿ���Ϣ�����
 * 
 * @author Yukikari Samuya
 * 
 */
public class LightServ extends ServerletMain {
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
		Light light = (Light) furnitureSet.getFurniture(familyName, floor,
				room, type);

		// ɾ���ɶ���
		furnitureSet.removeFurniture(light);

		// ��������ʱ��
		light.setLastTime();

		// ��ȡ�豸��ǰ״̬
		String s_time = (String) session.getAttribute("time");
		String s_brightness = (String) session.getAttribute("brightness");
		String s_switch = (String) session.getAttribute("switch");

		// ��ȡ��������ǰ״̬
		String a_time = light.getTime();
		String a_brightness = light.getBrightness();
		String a_switch = light.getSwitch();

		// ͳһΪ����״̬
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