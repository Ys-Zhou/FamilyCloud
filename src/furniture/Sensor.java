package furniture;

/**
 * JavaBean:Sensor
 * 
 * @author Yukikari Samuya
 * 
 */
public class Sensor extends Furniture {

	private String alarm;
	private String set_threshold;

	public Sensor() {

		super();
		alarm = null;
		set_threshold = null;
	}

	public String getAlarm() {

		return alarm;
	}

	public void setAlarm(String iAlarm) {

		alarm = iAlarm;
	}

	public String getSet_threshold() {

		return set_threshold;
	}

	public void setSet_threshold(String iSet_threshold) {

		set_threshold = iSet_threshold;
	}
}
