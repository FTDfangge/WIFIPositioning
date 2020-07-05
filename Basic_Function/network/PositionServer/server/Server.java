package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Server {
	private static Server ps = new Server();
	public Object obj = new Object();
	private Connection c;
	private Statement stmt;
	
	private Server() {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("×¢²áÇý¶¯£¡");
			String url = "jdbc:sqlite:data/InfoDB.db";
			c = DriverManager.getConnection(url);
			System.out.println("½¨Á¢Á¬½Ó£¡");
			stmt = c.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("×¢²áÇý¶¯Ê§°Ü!!");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			System.out.println("SQL´íÎó!!");
			e.printStackTrace();
		}
	}
	
	public void closeDataBase() {
		try {
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String searchCustomer(int id, String password) {
		try {
			String sql = "select * from UserInfo;";
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			String p = "";
			while (rs.next()) {
				i = rs.getInt("id");
				p = rs.getString("password");
				if(id == i && password.equals(p)) {
					String name = rs.getString("name");
					String info = i + "#" + p + "#" + name;
					rs.close();
					return info;
				} else if (id == i && !password.equals(p)) {
					rs.close();
					return null;
				}
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQL´íÎó!!");
		}
		return null;
	}
	
	public void modifyCustomer(int id, char type, String newOne) {
		String sql = null;
		if (type == 'P') {
			sql = "update UserInfo set password = '" + newOne + "' where id = " + id + ";";
		} else {
			sql = "update UserInfo set name = '" + newOne + "' where id = " + id + ";";
		}
		
		synchronized(obj) {
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println("SQL´íÎó!!");
			}
		}
		
	}
	
	public String insertCustomer(String name, String password) {
		try {
			String sql = "select * from UserInfo order by id desc limit 0,1";
			ResultSet rs = stmt.executeQuery(sql);
			int i = rs.getInt("id") + 1;
			rs.close();
			sql = "insert into UserInfo values(" + i + ", '" + password + "', '"
					+ name + "');";
			synchronized(obj) {
				stmt.executeUpdate(sql);
			}
			return i + "";
		} catch (SQLException e) {
			System.out.println("SQL´íÎó!!");
		}
		return null;
	}
	
	public static Server getServer() {
		return ps;
	}

	public static void main(String[] args) {
		Server ps = Server.getServer();
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(2000);
			while (true) {
				Socket client = server.accept();
				System.out.println("Client " + client.getRemoteSocketAddress() + " is already connected!");
				new Thread(new ServerThread(client, ps)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
