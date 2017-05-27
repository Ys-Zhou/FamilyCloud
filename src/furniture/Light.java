package furniture;

/**
 * JavaBean:Light
 * 
 * @author Yukikari Samuya
 * 
 */
public class Light extends Furniture {

	private String brightness;

	public Light() {

		super();

		brightness = null;
	}

	public String getBrightness() {

		return brightness;
	}

	public void setBrightness(String iBrightness) {

		brightness = iBrightness;
	}
}
