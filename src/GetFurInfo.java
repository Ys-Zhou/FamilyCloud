import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import furniture.Aircondition;
import furniture.Curtain;
import furniture.FurnitureSet;
import furniture.Icebox;
import furniture.Light;
import furniture.Sensor;
import furniture.Television;

/**
 * 获取智能家具状态
 * 
 * @author Yukikari Samuya
 * 
 */
public class GetFurInfo extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String familyName = jsonIn.getString("familyName");
		String floor = jsonIn.getString("floor");
		String room = jsonIn.getString("room");
		String type = jsonIn.getString("type");

		// 建立输出JSONObject
		JSONObject jsonObject = new JSONObject();

		// 获取application
		ServletContext application = getServletContext();

		if (application.getAttribute("furnitureSet") != null) {

			// 获取家具集
			FurnitureSet furnitureSet = (FurnitureSet) application
					.getAttribute("furnitureSet");

			// 根据类型选择输出格式
			switch (type) {

			case "Aircondition":
				// 获得指定的对象
				Aircondition aircondition = (Aircondition) furnitureSet
						.getFurniture(familyName, floor, room, type);
				// 调用函数获得输出JSONObject
				if (aircondition != null) {
					jsonObject = getAirconditionInfo(aircondition);
				}
				break;

			case "Curtain":
				Curtain curtain = (Curtain) furnitureSet.getFurniture(
						familyName, floor, room, type);
				if (curtain != null) {
					jsonObject = getCurtainInfo(curtain);
				}
				break;

			case "Icebox":
				Icebox icebox = (Icebox) furnitureSet.getFurniture(familyName,
						floor, room, type);
				if (icebox != null) {
					jsonObject = getIceboxInfo(icebox);
				}
				break;

			case "Light":
				Light light = (Light) furnitureSet.getFurniture(familyName,
						floor, room, type);
				if (light != null) {
					jsonObject = getLightInfo(light);
				}
				break;

			case "Sensor":
				Sensor sensor = (Sensor) furnitureSet.getFurniture(familyName,
						floor, room, type);
				if (sensor != null) {
					jsonObject = getSensorInfo(sensor);
				}
				break;

			case "Television":
				Television television = (Television) furnitureSet.getFurniture(
						familyName, floor, room, type);
				if (television != null) {
					jsonObject = getTelevisionInfo(television);
				}
				break;
			}
		}

		PrintWriter out = res.getWriter();

		out.print(jsonObject.toString());

		out.flush();

		out.close();
	}

	private JSONObject getAirconditionInfo(Aircondition aircondition) {

		JSONObject jsonObject = new JSONObject();

		String temperature = filter(aircondition.getTemperature());
		String state = filter(aircondition.getSwitch());
		String wind = filter(aircondition.getWind());
		String windrate = filter(aircondition.getWindrate());
		String model = filter(aircondition.getModel());
		String set_time = filter(aircondition.getSet_time());

		jsonObject.put("temperature", temperature);
		jsonObject.put("switch", state);
		jsonObject.put("wind", wind);
		jsonObject.put("windrate", windrate);
		jsonObject.put("model", model);
		jsonObject.put("set_time", set_time);

		return jsonObject;
	}

	private JSONObject getCurtainInfo(Curtain curtain) {

		JSONObject jsonObject = new JSONObject();

		String state = filter(curtain.getSwitch());

		jsonObject.put("switch", state);

		return jsonObject;
	}

	private JSONObject getLightInfo(Light light) {

		JSONObject jsonObject = new JSONObject();

		String brightness = filter(light.getBrightness());
		String state = filter(light.getSwitch());

		jsonObject.put("brightness", brightness);
		jsonObject.put("switch", state);

		return jsonObject;
	}

	private JSONObject getIceboxInfo(Icebox icebox) {

		JSONObject jsonObject = new JSONObject();

		String temperature = filter(icebox.getTemperature());
		String state = filter(icebox.getSwitch());
		String model = filter(icebox.getModel());
		String set_time = filter(icebox.getSet_time());

		jsonObject.put("temperature", temperature);
		jsonObject.put("switch", state);
		jsonObject.put("model", model);
		jsonObject.put("set_time", set_time);

		return jsonObject;
	}

	private JSONObject getSensorInfo(Sensor sensor) {

		JSONObject jsonObject = new JSONObject();

		String state = filter(sensor.getSwitch());
		String alarm = filter(sensor.getAlarm());
		String set_threshold = filter(sensor.getSet_threshold());

		jsonObject.put("switch", state);
		jsonObject.put("alarm", alarm);
		jsonObject.put("set_threshold", set_threshold);

		return jsonObject;
	}

	private JSONObject getTelevisionInfo(Television television) {

		JSONObject jsonObject = new JSONObject();

		String channel = filter(television.getChannel());
		String state = filter(television.getSwitch());
		String model = filter(television.getModel());
		String voice = filter(television.getVoice());

		jsonObject.put("channel", channel);
		jsonObject.put("switch", state);
		jsonObject.put("model", model);
		jsonObject.put("voice", voice);

		return jsonObject;
	}

	/**
	 * 防止给JSON赋值null出错
	 */
	private String filter(String hasNull) {

		if (hasNull == null)
			return "null";
		return hasNull;
	}
}