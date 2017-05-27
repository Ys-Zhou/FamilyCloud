package furniture;

/**
 * JavaBean:Icebox
 * 
 * @author Yukikari Samuya
 * 
 */
public class Icebox extends Furniture {

	private String temperature;
	private String model;
	private String set_time;

	public Icebox() {

		super();
		temperature = null;
		model = null;
		set_time = null;
	}

	public String getTemperature() {

		return temperature;
	}

	public void setTemperature(String iTemperature) {

		temperature = iTemperature;
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
