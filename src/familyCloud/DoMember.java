package familyCloud;

import java.io.File;
import java.sql.ResultSet;

/**
 * Member²Ù×÷
 * 
 * @author Yukikari Samuya
 * 
 */
public class DoMember {

	private Member member;
	private DBConnector dBConnector;
	private String root = "C:/GAME/MyEclipse Workspace/.metadata/.me_tcat7/webapps/FamilyCloud/root";

	public DoMember(Member iMember) throws Exception {

		member = iMember;
		dBConnector = DBConnector.Instance();
	}

	public boolean ifPass(String familyKey) throws Exception {

		String familyName = member.getFamilyName();

		String sql = "SELECT * FROM family WHERE familyName='" + familyName
				+ "' AND familyKey='" + familyKey + "'";

		ResultSet r = dBConnector.runQuery(sql);

		return r.next();
	}

	public void memberSin() throws Exception {

		String email = member.getEmail();
		String password = member.getPassword();
		String name = member.getName();
		String familyName = member.getFamilyName();
		String permission = member.getPermission();

		String[] sql = new String[2];

		sql[0] = "INSERT INTO member VALUES ('" + email + "','" + password
				+ "','" + name + "','" + permission + "')";

		sql[1] = "INSERT INTO familymember VALUES ('" + email + "','"
				+ familyName + "')";

		dBConnector.runChainedUpdate(sql);
	}

	public boolean memberLog() throws Exception {

		String email = member.getEmail();
		String password = member.getPassword();

		String sql = "SELECT * FROM member WHERE email='" + email
				+ "' AND password='" + password + "'";

		ResultSet r = dBConnector.runQuery(sql);

		return r.next();
	}

	public boolean checkType() throws Exception {

		String email = member.getEmail();

		String sql = "SELECT * FROM member WHERE email='" + email
				+ "' AND permission='admin'";

		ResultSet r = dBConnector.runQuery(sql);

		return r.next();
	}

	public String getFamily() throws Exception {

		String email = member.getEmail();

		String sql = "SELECT familyName FROM familymember WHERE email='"
				+ email + "'";

		ResultSet r = dBConnector.runQuery(sql);

		r.next();

		return r.getString("familyName");
	}

	public String getName() throws Exception {

		String email = member.getEmail();

		String sql = "SELECT name FROM member WHERE email='" + email + "'";

		ResultSet r = dBConnector.runQuery(sql);

		r.next();

		return r.getString("name");
	}

	public boolean ifExsit() throws Exception {

		String email = member.getEmail();

		String familyName = member.getFamilyName();

		String sql = "SELECT * FROM familymember WHERE email='" + email
				+ "' AND familyName='" + familyName + "'";

		ResultSet r = dBConnector.runQuery(sql);

		return r.next();
	}

	public boolean setPermission() throws Exception {

		String email = member.getEmail();
		String permission = member.getPermission();

		String sql = "UPDATE member SET permission='" + permission
				+ "' WHERE email='" + email + "'";

		if (dBConnector.runUpdate(sql) > 0)
			return true;
		return false;
	}

	public void deleteMember() throws Exception {

		String email = member.getEmail();

		String[] sql = new String[2];

		sql[0] = "DELETE FROM familymember WHERE email ='" + email + "'";

		sql[1] = "DELETE FROM member WHERE email ='" + email + "'";

		dBConnector.runChainedUpdate(sql);
	}
}