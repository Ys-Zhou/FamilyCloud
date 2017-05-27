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
public class TelevisionServ extends ServerletMain {
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
		Television television = (Television) furnitureSet.getFurniture(
				familyName, floor, room, type);

		// ɾ���ɶ���
		furnitureSet.removeFurniture(television);

		// ��������ʱ��
		television.setLastTime();

		// ��ȡ�豸��ǰ״̬
		String s_time = (String) session.getAttribute("time");
		String s_channel = (String) session.getAttribute("channel");
		String s_switch = (String) session.getAttribute("switch");
		String s_model = (String) session.getAttribute("model");
		String s_voice = (String) session.getAttribute("voice");

		// ��ȡ��������ǰ״̬
		String a_time = television.getTime();
		String a_channel = television.getChannel();
		String a_switch = television.getSwitch();
		String a_model = television.getModel();
		String a_voice = television.getVoice();

		// ͳһΪ����״̬
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

		// �����豸�б�
		furnitureSet.addFurniture(television);
		application.setAttribute("furnitureSet", furnitureSet);
		res.sendRedirect(req.getContextPath() + "/Television.jsp");
	}
}