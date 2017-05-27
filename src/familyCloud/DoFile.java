package familyCloud;

import java.io.File;
import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * File²Ù×÷
 * 
 * @author Yukikari Samuya
 * 
 */
public class DoFile {

	private UserFile userFile;
	private String root = "C:/GAME/MyEclipse Workspace/.metadata/.me_tcat7/webapps/FamilyCloud/root";
	private String httpRoot = "http://192.168.200.199:8080/FamilyCloud/root";
	private DBConnector dBConnector;

	public DoFile(UserFile iUserFile) throws Exception {

		userFile = iUserFile;
		dBConnector = DBConnector.Instance();
	}

	public JSONArray getFileInfo() throws Exception {

		String familyName = userFile.getFamilyName();
		String type = userFile.getType();

		String path = root + "/" + familyName + "/" + type;

		JSONArray jsonArray = new JSONArray();

		File file = new File(path);

		File[] emailFold = file.listFiles();

		if (emailFold != null)

			for (File firstChild : emailFold) {

				File[] fileNameFold = firstChild.listFiles();

				if (fileNameFold != null) {

					String email = firstChild.getName();

					String sql = "SELECT name FROM member WHERE email='"
							+ email + "'";

					ResultSet r = dBConnector.runQuery(sql);

					r.next();

					String name = r.getString("name");

					for (File secondChile : fileNameFold) {

						JSONObject jsonObject = new JSONObject();

						jsonObject.put("email", email);
						jsonObject.put("fileName", secondChile.getName());
						jsonObject.put("size", secondChile.length());
						jsonObject.put("name", name);

						jsonArray.add(jsonObject);
					}
				}
			}

		return jsonArray;
	}

	public boolean deleteFile() {

		String email = userFile.getEmail();
		String type = userFile.getType();
		String fileName = userFile.getFileName();
		String familyName = userFile.getFamilyName();

		String path = root + "/" + familyName + "/" + type + "/" + email + "/"
				+ fileName;

		File file = new File(path);

		if (file.exists()) {

			file.delete();
			return true;
		} else
			return false;
	}

	private long getTotalSizeOfFilesInDir(File file) {

		if (file.isFile())
			return file.length();
		final File[] children = file.listFiles();
		long total = 0;
		if (children != null)
			for (final File child : children)
				total += getTotalSizeOfFilesInDir(child);
		return total;
	}

	public long getSize() {

		String familyName = userFile.getFamilyName();
		String type = userFile.getType();

		String path = root + "/" + familyName + "/" + type;

		File file = new File(path);

		return getTotalSizeOfFilesInDir(file);
	}

	public String getHttp() {

		String familyName = userFile.getFamilyName();
		String type = userFile.getType();
		String email = userFile.getEmail();
		String fileName = userFile.getFileName();

		String url = httpRoot + "/" + familyName + "/" + type + "/" + email
				+ "/" + fileName;

		return url;
	}

	public void createFold() {

		String familyName = userFile.getFamilyName();
		String email = userFile.getEmail();

		File file = new File(root + "/" + familyName + "/file/" + email);
		file.mkdirs();

		file = new File(root + "/" + familyName + "/photo/" + email);
		file.mkdirs();

		file = new File(root + "/" + familyName + "/video/" + email);
		file.mkdirs();

		file = new File(root + "/" + familyName + "/music/" + email);
		file.mkdirs();
	}

	public void deletedFolds() throws Exception {

		String email = userFile.getEmail();

		String sql = "SELECT familyName FROM familymember WHERE email='"
				+ email + "'";

		ResultSet r = dBConnector.runQuery(sql);

		r.next();

		String familyName = r.getString("familyName");

		File file = new File(root + "/" + familyName + "/file/" + email);
		deleteFile(file);

		file = new File(root + "/" + familyName + "/photo/" + email);
		deleteFile(file);

		file = new File(root + "/" + familyName + "/video/" + email);
		deleteFile(file);

		file = new File(root + "/" + familyName + "/music/" + email);
		deleteFile(file);
	}

	private void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					this.deleteFile(files[i]);
				}
			}
			file.delete();
		}
	}
}
