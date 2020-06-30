/**
 * 
 */
package customerList;

import java.net.Socket;

/**
 * 用户类
 * @author 章富 2019年11月20日
 */
public class Customer {
	private Socket mySocket; // 用户对应的socket对象
	private String name; // 昵称
	private int id; // id号
	private String password; // 密码

	/**
	 * @param id       id号
	 * @param password 密码
	 * @param nickname 昵称
	 */
	public Customer(int id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
		mySocket = null;
	}

	/**
	 * 设置密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置昵称
	 * @param nickname 昵称
	 */
	public void setName(String nickname) {
		this.name = nickname;
	}

	/**
	 * 设置socket
	 * @param s socket对象
	 */
	public void setSocket(Socket s) {
		mySocket = s;
	}
	
	/**
	 * 获取ID
	 * @return 用户ID
	 */
	public int getID() {
		return id;
	}

	/**
	 * 获取密码
	 * @return 用户密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 获取昵称
	 * @return 用户昵称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取socket
	 * @return 用户对应的socket
	 */
	public Socket getSocket() {
		return mySocket;
	}

	/**
	 * 重写campareTo方法
	 * @param c 需要比较的对象
	 * @return 1，0，-1分别表示大，相等，小
	 */
	public int compareTo(Customer c) {
		if (c.getID() < id) {
			return -1;
		} else if (c.getID() > id) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 重写toString方法
	 */
	public String toString() {
		return id + " " + password + " " + name + " ";
	}
}
