package firmwarecomparingtool;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.*;


public class Database {

	private String url; //"jdbc:mysql://localhost/bdd_test"
	private String login; //"root"
	private String passwd; //"mysql"
	
	public Database(String u, String l, String p){
		super();
		this.url = u;
		this.login = l;
		this.passwd = p;
	}
	
	public void write(String vendor, String model, String md5, String sha1, String path, String version){
		//access informations to the database

		Connection cn = null;
		Statement st = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(this.url, this.login, this.passwd);
			st = cn.createStatement();
			String sql = "insert into firmware (vendor,model,md5hash,sha1hash,path,version) values ('"+ vendor + "','"+ model +"','"+ md5 +"','"+ sha1 +"','"+ path +"','"+ version +"')";
			
			st.executeUpdate(sql);
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void read(){
		
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(this.url, this.login, this.passwd);
			st = cn.createStatement();
			String sql = "SELECT * FROM firmware where version = '20.19'";
			rs = st.executeQuery(sql);
			
			while (rs.next()){
				System.out.println(rs.getString("md5hash"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
public List<Firmware> createBaseline(){
		
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Firmware> lf = new ArrayList<Firmware>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(this.url, this.login, this.passwd);
			st = cn.createStatement();
			String sql = "SELECT * FROM firmware";
			rs = st.executeQuery(sql);
			
			while (rs.next()){
				lf.add(new Firmware(rs.getString("vendor"),rs.getString("model"),rs.getString("md5hash"),rs.getString("sha1hash"),"Firmwares/"+rs.getString("path"),rs.getString("version"),rs.getInt("length")));
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return lf;
	}

public List<Firmware> createBaseline(String vendor, String model, String version) throws SQLException{
	
	Connection cn = null;
	Statement st = null;
	ResultSet rs = null;
	List<Firmware> lf = new ArrayList<Firmware>();
	Boolean first;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		cn = DriverManager.getConnection(this.url, this.login, this.passwd);
		st = cn.createStatement();
		String sql = "SELECT * FROM firmware";
		
		if (vendor.equals(model) && model.equals(version) && version.equals("")){
			first = false;
		} else {
			first = true;
			
		}
		
		if (!vendor.equals("")){
			sql += " where vendor ='" + vendor +"'";
			first = false;
			System.out.println(sql);
		}
		
		
		if (!model.equals("")){
			if (first){
				sql += " where model ='" + model +"'";
				first = false;
				System.out.println(sql);
			} else {
				sql += " and model ='" + model +"'";
				System.out.println(sql);
			}
		}
		
		if (!version.equals("")){
			if (first){
				sql += " where version ='" + version +"'";
				first = false;
				System.out.println(sql);
			} else {
				sql += " and version ='" + version +"'";
				System.out.println(sql);
			}
		}

		rs = st.executeQuery(sql);
		
		while (rs.next()){
			lf.add(new Firmware(rs.getString("vendor"),rs.getString("model"),rs.getString("md5hash"),rs.getString("sha1hash"),"Firmwares/"+rs.getString("path"),rs.getString("version"),rs.getInt("length")));
		}
	} catch (SQLException e){
		e.printStackTrace();
	} catch (ClassNotFoundException e){
		e.printStackTrace();
	} finally {
		try {
			cn.close();
			st.close();
		} catch (SQLException e){
			e.printStackTrace();
			throw new SQLException();
		}
	}
	return lf;
}
}
