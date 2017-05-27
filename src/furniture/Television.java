package furniture;

/**
 * JavaBean:Television
 * 
 * @author Yukikari Samuya
 * 
 */
public class Television extends Furniture {

	private String channel;
	private String voice;
	private String model;

	public Television() {

		super();
		channel = null;
		voice = null;
		model = null;
	}

	public String getChannel() {

		return channel;
	}

	public void setChannel(String iChannel) {

		channel = iChannel;
	}

	public String getVoice() {

		return voice;
	}

	public void setVoice(String iVoice) {

		voice = iVoice;
	}

	public String getModel() {

		return model;
	}

	public void setModel(String iModel) {

		model = iModel;
	}
}