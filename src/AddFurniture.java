import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import familyCloud.DoFurniture;
import familyCloud.Furniture;

/**
 * 添加家具
 * 
 * @author Yukikari Samuya
 * 
 */
public class AddFurniture extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String familyName = jsonIn.getString("familyName");
		String floor = jsonIn.getString("floor");
		String room = jsonIn.getString("room");
		String type = jsonIn.getString("type");

		// 获取json并创建javabean
		Furniture furniture = new Furniture();

		furniture.setFamilyName(familyName);
		furniture.setFloor(floor);
		furniture.setRoom(room);
		furniture.setType(type);

		JSONObject jsonOut = new JSONObject();

		try {

			DoFurniture doFurniture = new DoFurniture(furniture);

			doFurniture.addFurniture();

			jsonOut.put("AddFurnitureStat", 1);

		} catch (Exception e) {

			jsonOut.put("AddFurnitureStat", 0);
		}

		PrintWriter out = res.getWriter();

		out.print(jsonOut.toString());

		out.flush();

		out.close();
	}
}