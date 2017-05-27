package familyCloud;

/**
 * JavaBean:Member
 * 
 * @author Yukikari Samuya
 * 
 */
public class Furniture {

	private String familyName;

	private String floor;

	private String room;

	private String type;

	public Furniture() {

		familyName = null;
		floor = null;
		room = null;
		type = null;
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

}
