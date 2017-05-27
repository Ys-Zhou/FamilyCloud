package furniture;

import java.util.Date;

/**
 * JavaBean:Furniture
 * 
 * @author Yukikari Samuya
 * 
 */
public class Furniture {

	protected Date lastTime;

	protected String familyName;

	protected String floor;

	protected String room;

	protected String type;

	protected String time;

	protected String state;// switch

	public Furniture() {

		lastTime = null;
		familyName = null;
		floor = null;
		room = null;
		time = null;
		state = null;
	}

	public Date getLastTime() {

		return lastTime;
	}

	public void setLastTime() {

		lastTime = new Date();
	}

	public String getFamilyName() {

		return familyName;
	}

	public void setFamilyName(String iFamilyName) {

		familyName = iFamilyName;
	}

	public String getFloor() {

		return floor;
	}

	public void setFloor(String iFloor) {

		floor = iFloor;
	}

	public String getType() {

		return type;
	}

	public void setType(String iType) {

		type = iType;
	}

	public String getRoom() {

		return room;
	}

	public void setRoom(String iRoom) {

		room = iRoom;
	}

	public String getTime() {

		return time;
	}

	public void setTime(String iTime) {

		time = iTime;
	}

	public String getSwitch() {

		return state;
	}

	public void setSwitch(String iState) {

		state = iState;
	}
}