package familyCloud;

/**
 * JavaBean:Member
 * 
 * @author Yukikari Samuya
 * 
 */
public class Member {

	protected String email;
	protected String password;
	protected String name;
	protected String familyName;
	protected String permission;

	public Member() {

		email = null;
		password = null;
		name = null;
		familyName = null;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String iEmail) {

		email = iEmail;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String iPassword) {

		password = iPassword;
	}

	public String getName() {

		return name;
	}

	public void setName(String iName) {

		name = iName;
	}

	public String getFamilyName() {

		return familyName;
	}

	public void setFamilyName(String iFamilyName) {

		familyName = iFamilyName;
	}

	public String getPermission() {

		return permission;
	}

	public void setPermission(String iPermission) {

		permission = iPermission;
	}
}
