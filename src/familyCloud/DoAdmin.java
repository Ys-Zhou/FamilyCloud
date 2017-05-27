package familyCloud;

import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * AdminMember²Ù×÷
 * 
 * @author Yukikari Samuya
 * 
 */
public class DoAdmin {

	private AdminMember adminMember;
	private DBConnector dBConnector;

	public DoAdmin(AdminMember iAdminMember) throws Exception {

		adminMember = iAdminMember;
		dBConnector = DBConnector.Instance();
	}

	public void adminSin() throws Exception {

		String email = adminMember.getEmail();
		String password = adminMember.getPassword();
		String name = adminMember.getName();
		String familyName = adminMember.getFamilyName();
		String permission = adminMember.getPermission();
		String familyKey = adminMember.getFamilyKey();
		String ipAddress = adminMember.getIpAddress();

		String[] sql = new String[3];

		sql[0] = "INSERT INTO member VALUES('" + email + "','" + password
				+ "','" + name + "','" + permission + "')";

		sql[1] = "INSERT INTO family VALUES('" + familyName + "','" + familyKey
				+ "','" + ipAddress + "','" + email + "')";

		sql[2] = "INSERT INTO familymember VALUES('" + email + "','"
				+ familyName + "')";

		dBConnector.runChainedUpdate(sql);
	}

	public JSONObject getMembers() throws Exception {

		JSONObject json = new JSONObject();

		String familyName = adminMember.getFamilyName();

		String sql = "SELECT adminEmail FROM family WHERE familyName='"
				+ familyName + "'";

		ResultSet r = dBConnector.runQuery(sql);

		r.next();

		String adminEmail = r.getString("adminEmail");

		sql = "SELECT email, name, permission FROM member WHERE email IN "
				+ "(SELECT email FROM familymember WHERE familyName='"
				+ familyName + "')";

		r = dBConnector.runQuery(sql);

		JSONArray jsonArray = new JSONArray();

		while (r.next()) {

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("email", r.getString("email"));
			jsonObject.put("name", r.getString("name"));
			if (r.getString("permission").equals("admin"))
				jsonObject.put("permission", true);
			else
				jsonObject.put("permission", false);

			jsonArray.add(jsonObject);
		}

		json.put("adminEmail", adminEmail);
		json.put("familyList", jsonArray);

		return json;
	}

	public JSONArray getLog() throws Exception {

		JSONArray jsonArray = new JSONArray();

		String familyName = adminMember.getFamilyName();

		String sql = "SELECT * FROM log WHERE email IN "
				+ "(SELECT email FROM familymember WHERE familyName='"
				+ familyName + "') ORDER BY date DESC";

		ResultSet r = dBConnector.runQuery(sql);

		int i = 0;

		while (r.next() && i <= 50) {

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("date", r.getString("date"));
			jsonObject.put("ipAddress", r.getString("ipAddress"));
			jsonObject.put("email", r.getString("email"));
			jsonObject.put("operation", r.getString("operation"));
			jsonObject.put("result", r.getString("result"));

			jsonArray.add(jsonObject);
			i++;
		}

		return jsonArray;
	}

	public JSONArray getSinFur() throws Exception {

		JSONArray jsonArray = new JSONArray();

		String familyName = adminMember.getFamilyName();

		String sql = "SELECT * FROM furniture WHERE familyName='" + familyName
				+ "'";

		ResultSet r = dBConnector.runQuery(sql);

		while (r.next()) {

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("floor", r.getString("floor"));
			jsonObject.put("room", r.getString("room"));
			jsonObject.put("type", r.getString("type"));

			jsonArray.add(jsonObject);
		}

		return jsonArray;
	}
}