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
 * 设置智能家具状态
 * 
 * @author Yukikari Samuya
 * 
 */
public class SetFurInfo extends ServerletMain {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 获取表单值
		JSONObject jsonIn = JSONObject.fromObject(req.getParameter("jsonIn"));

		String familyName = jsonIn.getString("familyName");
		String floor = jsonIn.getString("floor");
		String room = jsonIn.getString("room");
		String type = jsonIn.getString("type");

		// 建立输出
		JSONObject jsonObject = new JSONObject();

		// 获取application
		ServletContext application = getServletContext();

		if (application.getAttribute("furnitureSet") != null) {

			// 获取家具集
			FurnitureSet furnitureSet = (FurnitureSet) application
					.getAttribute("furnitureSet");

			switch (type) {

			case "Aircondition":
				// 获得指定的对象
				Aircondition aircondition = (Aircondition) furnitureSet
						.getFurniture(familyName, floor, room, type);
				if (aircondition != null) {

					furnitureSet.removeFurniture(aircondition);

					furnitureSet.addFurniture(setAirconditionInfo(jsonIn,
							aircondition));

					jsonObject.put("SetFurInfoStat", 1);
				} else {

					jsonObject.put("SetFurInfoStat", 0);
				}
				break;

			case "Curtain":
				Curtain curtain = (Curtain) furnitureSet.getFurniture(
						familyName, floor, room, type);
				if (curtain != null) {

					furnitureSet.removeFurniture(curtain);

					furnitureSet.addFurniture(setCurtainInfo(jsonIn, curtain));

					jsonObject.put("SetFurInfoStat", 1);
				} else {

					jsonObject.put("SetFurInfoStat", 0);
				}
				break;

			case "Icebox":
				Icebox icebox = (Icebox) furnitureSet.getFurniture(familyName,
						floor, room, type);
				if (icebox != null) {

					furnitureSet.removeFurniture(icebox);

					furnitureSet.addFurniture(setIceboxInfo(jsonIn, icebox));

					jsonObject.put("SetFurInfoStat", 1);
				} else {

					jsonObject.put("SetFurInfoStat", 0);
				}
				break;

			case "Light":
				Light light = (Light) furnitureSet.getFurniture(familyName,
						floor, room, type);
				if (light != null) {

					furnitureSet.removeFurniture(light);

					furnitureSet.addFurniture(setLightInfo(jsonIn, light));

					jsonObject.put("SetFurInfoStat", 1);
				} else {

					jsonObject.put("SetFurInfoStat", 0);
				}
				break;

			case "Sensor":
				Sensor sensor = (Sensor) furnitureSet.getFurniture(familyName,
						floor, room, type);
				if (sensor != null) {

					furnitureSet.removeFurniture(sensor);

					furnitureSet.addFurniture(setSensorInfo(jsonIn, sensor));

					jsonObject.put("SetFurInfoStat", 1);
				} else {

					jsonObject.put("SetFurInfoStat", 0);
				}
				break;

			case "Television":
				Television television = (Television) furnitureSet.getFurniture(
						familyName, floor, room, type);
				if (television != null) {

					furnitureSet.removeFurniture(television);

					furnitureSet.addFurniture(setTelevisionInfo(jsonIn,
							television));

					jsonObject.put("SetFurInfoStat", 1);
				} else {

					jsonObject.put("SetFurInfoStat", 0);
				}
				break;
			}

			application.setAttribute("furnitureSet", furnitureSet);

		} else {
			jsonObject.put("SetFurInfoStat", 0);
		}

		PrintWriter out = res.getWriter();

		out.print(jsonObject.toString());

		out.flush();

		out.close();
	}

	private Aircondition setAirconditionInfo(JSONObject jsonIn,
			Aircondition aircondition) {

		String temperature = jsonIn.getString("temperature");
		String state = jsonIn.getString("switch");
		String wind = jsonIn.getString("wind");
		String windrate = jsonIn.getString("windrate");
		String model = jsonIn.getString("model");
		String set_time = jsonIn.getString("set_time");

		aircondition.setTemperature(temperature);
		aircondition.setSwitch(state);
		aircondition.setWind(wind);
		aircondition.setWindrate(windrate);
		aircondition.setModel(model);
		aircondition.setSet_time(set_time);

		return aircondition;
	}

	private Curtain setCurtainInfo(JSONObject jsonIn, Curtain curtain) {

		String state = jsonIn.getString("switch");

		curtain.setSwitch(state);

		return curtain;
	}

	private Icebox setIceboxInfo(JSONObject jsonIn, Icebox icebox) {

		String temperature = jsonIn.getString("temperature");
		String state = jsonIn.getString("switch");
		String model = jsonIn.getString("model");
		String set_time = jsonIn.getString("set_time");

		icebox.setTemperature(temperature);
		icebox.setSwitch(state);
		icebox.setModel(model);
		icebox.setSet_time(set_time);

		return icebox;
	}

	private Light setLightInfo(JSONObject jsonIn, Light light) {

		String brightness = jsonIn.getString("brightness");
		String state = jsonIn.getString("switch");

		light.setBrightness(brightness);
		light.setSwitch(state);

		return light;
	}

	private Sensor setSensorInfo(JSONObject jsonIn, Sensor sensor) {

		String alarm = jsonIn.getString("alarm");
		String set_threshold = jsonIn.getString("set_threshold");
		String state = jsonIn.getString("switch");

		sensor.setAlarm(alarm);
		sensor.setSet_threshold(set_threshold);
		sensor.setSwitch(state);

		return sensor;
	}

	private Television setTelevisionInfo(JSONObject jsonIn,
			Television television) {

		String channel = jsonIn.getString("channel");
		String state = jsonIn.getString("switch");
		String model = jsonIn.getString("model");
		String voice = jsonIn.getString("voice");

		television.setChannel(channel);
		television.setSwitch(state);
		television.setModel(model);
		television.setVoice(voice);

		return television;
	}
}