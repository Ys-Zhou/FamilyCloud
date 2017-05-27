package furniture;

/**
 * JavaBean:Aircondition
 * 
 * @author Yukikari Samuya
 * 
 */
public class Aircondition extends Furniture {

	private String temperature;
	private String wind;
	private String windrate;
	private String model;
	private String set_time;

	public Aircondition() {

		super();
		temperature = null;
		wind = null;
		model = null;
		set_time = null;
	}

	public String getTemperature() {

		return temperature;
	}

	public void setTemperature(String iTemperature) {

		temperature = iTemperature;
	}

	public String getWind() {

		return wind;
	}

	public void setWind(String iWind) {

		wind = iWind;
	}

	public String getWindrate() {

		return windrate;
	}

	public void setWindrate(String iWindrate) {

		windrate = iWindrate;
	}

	public String getModel() {

		return model;
	}

	public void setModel(String iModel) {

		model = iModel;
	}

	public String getSet_time() {

		return set_time;
	}

	public void setSet_time(String iSet_time) {

		set_time = iSet_time;
	}
}
