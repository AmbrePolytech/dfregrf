package firmwarecomparingtool;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.*;
/**
 * The Database is a wrapper to use easily the MySQL tool in Java through JDBC 
 * @author Ambre Person
 *
 */

public class Database {
	/**
	 * Field description :
	 * url : The Database URL
	 * login : The username used on the Database
	 * passwd : The user's password
	 */
	private String url; 
	private String login; 
	private String passwd; 
	/**
	 * The constructor creates a new Database object using :
	 * @param u : the URL of the Database
	 * @param l : the username of the user
	 * @param p : the password of the user
	 */
	public Database(String u, String l, String p){
		super();
		this.url = u;
		this.login = l;
		this.passwd = p;
	}
	/**
	 * The write method allow the user to insert elements into the table referencing all the firmware images
	 * @param vendor : vendor of the firmware
	 * @param model : model of the PLC
	 * @param md5 : md5 hash code of the firmware image
	 * @param sha1 : md5 hash code of the firmware image
	 * @param path : path of the file
	 * @param version : version of the firmware image
	 */
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
	
	/**
	 * The read method gives all the versions available in the Database. This method is not used, it is just an example of use.
	 */
	public void read(){
		
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(this.url, this.login, this.passwd);
			st = cn.createStatement();
			String sql = "SELECT * FROM firmware";
			rs = st.executeQuery(sql);
			
			while (rs.next()){
				System.out.println(rs.getString("version"));
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
	/**
	 * The createBaseline method creates a list of Firmware using the Database. This list is intended to be used to initialize the baseline in the Model of the application.
	 * @return : returns a List<Firmware> composed of the whole Database
	 */
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
	/**
	 * The createBaseline method creates a list of Firmware using the Database. This list is intended to be used to initialize the baseline in the Model of the application. This version gives to the user some filters to limit the baseline.
	 * @param vendor : filter on the vendor name
	 * @param model : filter on the model name
	 * @param version : filter on the version name
	 * @return : returns a List<Firmware> composed of the filtered Database. If the filters are all empty, return the same thing as the createBaseline() call.
	 * @throws SQLException : throws SQLException if an SQL Error appends during the filtering process.
	 */
	public List<Firmware> createBaseline(String vendor, String model, String version) throws SQLException {
	
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
