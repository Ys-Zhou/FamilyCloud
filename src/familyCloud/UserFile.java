package familyCloud;

/**
 * JavaBean:UserFile
 * 
 * @author Yukikari Samuya
 * 
 */
public class UserFile {

	private String familyName;//
	private String type;//
	private String email;
	private String fileName;

	public UserFile() {

		familyName = null;
		type = null;
		email = null;
		fileName = null;
	}

	public void setFamilyName(String iFamilyName) {

		familyName = iFamilyName;
	}

	public String getFamilyName() {

		return familyName;
	}

	public void setType(String iType) {

		type = iType;
	}

	public String getType() {

		return type;
	}

	public void setEmail(String iEmail) {

		email = iEmail;
	}

	public String getEmail() {

		return email;
	}

	public void setFileName(String iFileName) {

		fileName = iFileName;
	}

	public String getFileName() {

		return fileName;
	}
}