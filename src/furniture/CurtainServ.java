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
public class CurtainServ extends ServerletMain {
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
		Curtain curtain = (Curtain) furnitureSet.getFurniture(familyName,
				floor, room, type);

		// ɾ���ɶ���
		furnitureSet.removeFurniture(curtain);

		// ��������ʱ��
		curtain.setLastTime();

		// ��ȡ�豸��ǰ״̬
		String s_time = (String) session.getAttribute("time");
		String s_switch = (String) session.getAttribute("switch");

		// ��ȡ��������ǰ״̬
		String a_time = curtain.getTime();
		String a_switch = curtain.getSwitch();

		// ͳһΪ����״̬
		if (a_time == null || a_time.compareTo(s_time) < 0) {

			curtain.setTime(s_time);
			curtain.setSwitch(s_switch);

		} else {

			session.setAttribute("time", a_time);
			session.setAttribute("switch", a_switch);

		}

		furnitureSet.addFurniture(curtain);
		application.setAttribute("furnitureSet", furnitureSet);
		res.sendRedirect(req.getContextPath() + "/Curtain.jsp");
	}
}