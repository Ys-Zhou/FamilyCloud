package familyCloud;

/**
 * Furniture²Ù×÷
 * 
 * @author Yukikari Samuya
 * 
 */
public class DoFurniture {

	private Furniture furniture;
	private DBConnector dBConnector;

	public DoFurniture(Furniture iFurniture) throws Exception {

		furniture = iFurniture;
		dBConnector = DBConnector.Instance();
	}

	public void addFurniture() throws Exception {

		String floor = furniture.getFloor();
		String room = furniture.getRoom();
		String familyName = furniture.getFamilyName();
		String type = furniture.getType();

		String sql = "INSERT INTO furniture VALUES('" + familyName + "','"
				+ floor + "','" + room + "','" + type + "')";

		dBConnector.runUpdate(sql);
	}

	public void deleteFurniture() throws Exception {

		String floor = furniture.getFloor();
		String room = furniture.getRoom();
		String familyName = furniture.getFamilyName();
		String type = furniture.getType();

		String sql = "DELETE FROM furniture WHERE familyName='" + familyName
				+ "' AND floor='" + floor + "' AND room='" + room
				+ "' AND type='" + type + "'";

		dBConnector.runUpdate(sql);
	}
}
