package furniture;

import java.util.Date;
import java.util.Vector;

/**
 * JavaBeanSet:FurnitureSet
 * 
 * @author Yukikari Samuya
 * 
 */
public class FurnitureSet {

	public Vector<Furniture> set;

	public FurnitureSet() {

		set = new Vector<Furniture>();
	}

	public void addFurniture(Furniture iFurniture) {

		set.addElement(iFurniture);
	}

	public void removeFurniture(Furniture iFurniture) {

		set.removeElement(iFurniture);
	}

	public Furniture getFurniture(String familyName, String floor, String room,
			String type) {

		clearOld();

		for (Furniture furniture : set) {

			if (furniture.getFamilyName().equals(familyName)
					&& furniture.getFloor().equals(floor)
					&& furniture.getRoom().equals(room)
					&& furniture.getType().equals(type))
				return furniture;
		}
		return null;
	}

	public Vector<Furniture> getFurnitureSet(String familyName) {

		clearOld();

		Vector<Furniture> furnitureSet = new Vector<Furniture>();

		for (Furniture furniture : set) {

			if (furniture.getFamilyName().equals(familyName))

				furnitureSet.addElement(furniture);
		}

		return furnitureSet;
	}

	private void clearOld() {

		Vector<Furniture> deleteSet = new Vector<Furniture>();

		for (Furniture furniture : set) {

			Date date = new Date();
			long past = date.getTime() - furniture.getLastTime().getTime();

			if (past > 7000) {
				System.out.println("³¬Ê±É¾³ý " + past);
				deleteSet.addElement(furniture);
			}
		}

		if (!deleteSet.isEmpty())
			set.removeAll(deleteSet);
	}
}