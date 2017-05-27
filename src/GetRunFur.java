import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import furniture.Furniture;
import furniture.FurnitureSet;

/**
 * ��ȡ�������е����ܼҾ�
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetRunFur extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// ��ȡ��ֵ
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));
		String familyName = jsonIn.getString("familyName");

		// ��ȡapplication
		ServletContext application = getServletContext();

		// ���
		JSONArray jsonArray = new JSONArray();

		if (application.getAttribute("furnitureSet") != null) {

			// ��ȡ�Ҿ߼�
			FurnitureSet furnitureSet = (FurnitureSet) application
					.getAttribute("furnitureSet");

			Vector<Furniture> runFurSet = furnitureSet
					.getFurnitureSet(familyName);

			if (!runFurSet.isEmpty()) {

				for (Furniture furniture : runFurSet) {

					JSONObject jsonObject = new JSONObject();

					jsonObject.put("floor", furniture.getFloor());
					jsonObject.put("room", furniture.getRoom());
					jsonObject.put("type", furniture.getType());

					jsonArray.add(jsonObject);
				}
			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonArray.toString());

		out.flush();

		out.close();
	}
}