package furniture;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 选择转发
 * 
 * @author Yukikari Samuya
 * 
 */
public class Choice extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String type = req.getParameter("type");
		String familyName = req.getParameter("familyName");
		String floor = req.getParameter("floor");
		String room = req.getParameter("room");

		HttpSession session = req.getSession();

		// 设置为连接上
		session.setAttribute("login", "connect");

		session.setAttribute("familyName", familyName);
		session.setAttribute("floor", floor);
		session.setAttribute("room", room);
		session.setAttribute("type", type);

		switch (type) {

		case "Aircondition":
			Aircondition aircondition = new Aircondition();
			ini(aircondition, familyName, floor, room, type);
			res.sendRedirect(req.getContextPath() + "/Aircondition.jsp");
			break;

		case "Curtain":
			Curtain curtain = new Curtain();
			ini(curtain, familyName, floor, room, type);
			res.sendRedirect(req.getContextPath() + "/Curtain.jsp");
			break;

		case "Icebox":
			Icebox icebox = new Icebox();
			ini(icebox, familyName, floor, room, type);
			res.sendRedirect(req.getContextPath() + "/Icebox.jsp");
			break;

		case "Light":
			Light light = new Light();
			ini(light, familyName, floor, room, type);
			res.sendRedirect(req.getContextPath() + "/Light.jsp");
			break;

		case "Sensor":
			Sensor sensor = new Sensor();
			ini(sensor, familyName, floor, room, type);
			res.sendRedirect(req.getContextPath() + "/Sensor.jsp");
			break;

		case "Television":
			Television television = new Television();
			ini(television, familyName, floor, room, type);
			res.sendRedirect(req.getContextPath() + "/Television.jsp");
			break;
		}
	}

	private void ini(Furniture furniture, String familyName, String floor,
			String room, String type) {

		ServletContext application = getServletContext();

		if (application.getAttribute("furnitureSet") == null)
			application.setAttribute("furnitureSet", new FurnitureSet());
		FurnitureSet furnitureSet = (FurnitureSet) application
				.getAttribute("furnitureSet");

		furniture.setFamilyName(familyName);
		furniture.setFloor(floor);
		furniture.setRoom(room);
		furniture.setType(type);
		furniture.setLastTime();

		furnitureSet.addFurniture(furniture);

		application.setAttribute("furnitureSet", furnitureSet);
	}
}