package familyCloud;

/**
 * JavaBean:AdminMember
 * 
 * @author Yukikari Samuya
 * 
 */
public class AdminMember extends Member {

	private String familyKey;
	private String ipAddress;

	public AdminMember() {

		super();
		familyKey = null;
		ipAddress = null;
	}

	public String getFamilyKey() {

		return familyKey;
	}

	public void setFamilyKey(String iFamilyKey) {

		familyKey = iFamilyKey;
	}

	public String getIpAddress() {

		return ipAddress;
	}

	public void setIpAddress(String iIpAddress) {

		ipAddress = iIpAddress;
	}
}
