package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import customerList.Customer;
import customerList.CustomerList;
import positionList.Position;
import positionList.PositionList;

public class PositionServer {
	/**
	 * 从数据库中获取用户的基本信息
	 * @param customers 用户链表
	 */
	public static void getCustomerList(ArrayList<Customer> customers) {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("注册驱动！");
			String url = "jdbc:sqlite:data/InfoDB.db";
			Connection c = DriverManager.getConnection(url);
			System.out.println("建立连接！");
			Statement stmt = c.createStatement();
			String sql = "select * from UserInfo;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String password = rs.getString("password");
				String nickname = rs.getString("name");
				customers.add(new Customer(id, password, nickname));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (ClassNotFoundException e) {
			System.out.println("注册驱动失败!!");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			System.out.println("SQL语句错误!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改其中一个用户的信息
	 * @param type 修改项，'P'代表password，'N'代表nickname
	 * @param newOne 新的值
	 * @param cus 需要被修改的用户
	 */
	public static void modifyCustomer(char type, String newOne, Customer cus) {
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:data/InfoDB.db";
			Connection c = DriverManager.getConnection(url);
			Statement stmt = c.createStatement();
			String sql = null;
			if (type == 'P') {
				sql = "update UserInfo set password = '" + newOne + "' where id = " + cus.getID() + ";";
			} else {
				sql = "update UserInfo set name = '" + newOne + "' where id = " + cus.getID() + ";";
			}
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (ClassNotFoundException e) {
			System.out.println("注册驱动失败!!");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			System.out.println("SQL语句错误!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加一个新的用户
	 * @param customer 新用户
	 */
	public static void insertCustomer(Customer customer) {
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:data/InfoDB.db";
			Connection c = DriverManager.getConnection(url);
			Statement stmt = c.createStatement();
			String sql = "insert into UserInfo values(" + customer.getID() + ", '" + customer.getPassword() + "', '"
					+ customer.getName() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (ClassNotFoundException e) {
			System.out.println("注册驱动失败!!");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			System.out.println("SQL语句错误!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 从数据库中获取位置的基本信息
	 * @param positions 地点链表
	 */
	public static void getPositionList(ArrayList<Position> positions) {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("注册驱动！");
			String url = "jdbc:sqlite:data/InfoDB.db";
			Connection c = DriverManager.getConnection(url);
			System.out.println("建立连接！");
			Statement stmt = c.createStatement();
			String sql = "select * from PositionInfo;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				int[] pos = new int[3];
				pos[0] = rs.getInt("posX");
				pos[1] = rs.getInt("posY");
				pos[2] = rs.getInt("posZ");
				int feature = rs.getInt("feature");
				String description = rs.getString("description");
				int times = rs.getInt("times");
				double feedback = rs.getDouble("feedback");
				positions.add(new Position(name, pos, feature, description, times, feedback));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (ClassNotFoundException e) {
			System.out.println("注册驱动失败!!");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			System.out.println("SQL语句错误!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新对地点的评价
	 * @param p 需要更新的地点
	 */
	public static void modifyFeedback(Position p) {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("注册驱动！");
			String url = "jdbc:sqlite:data/InfoDB.db";
			Connection c = DriverManager.getConnection(url);
			System.out.println("建立连接！");
			Statement stmt = c.createStatement();
			String sql = null;
			sql = "update UserInfo set times = " + p.getTimesComment() + ", feedback = "+ p.getFeedback() + " where name = '" + p.getName() + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (ClassNotFoundException e) {
			System.out.println("注册驱动失败!!");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			System.out.println("SQL语句错误!!!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CustomerList listC = CustomerList.getList();
		PositionList listP = PositionList.getList();
		PositionServer.getCustomerList(listC.getCustomerList());
		PositionServer.getPositionList(listP.getPositionList());
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(2000);
			while (true) {
				Socket client = server.accept();
				System.out.println("Client " + client.getRemoteSocketAddress() + " is already connected!");
				new Thread(new ServerThread(client, listC, listP)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
