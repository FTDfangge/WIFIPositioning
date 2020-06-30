/**
 * 
 */
package customerList;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 用户链表类，单实例
 * @author 章富 2019年11月21日
 */
public class CustomerList {
	private static CustomerList list = new CustomerList(); //单实例对象
	private ArrayList<Customer> cList; //用户链表
	
	/**
	 * 构造器
	 */
	private CustomerList() {
		cList = new ArrayList<Customer>();
	}
	
	/**
	 * 获取用户链表
	 * @return
	 */
	public ArrayList<Customer> getCustomerList(){
		return cList;
	}
	
	/**
	 * 添加新用户，ID自动分配
	 * @param password 密码
	 * @param nickname 昵称
	 * @return 返回该用户
	 */
	public Customer addNewCustomer(String password, String nickname){
		Customer c = new Customer(cList.get(cList.size() - 1).getID() + 1, password, nickname);
		cList.add(c);
		return c;
	}
	
	/**
	 * 用户排序
	 */
	public void sortList() {
		cList.sort(new CustomerComparator());
	}
	
	/**
	 * 在链表中搜索一个用户
	 * @param id 用户的ID
	 * @param password 用户的密码
	 * @return 找到则返回用户，未找到则返回null
	 */
	public Customer searchCustomer(int id, String password) {
		Iterator<Customer> it = cList.iterator();
		while (it.hasNext()) {
			Customer c = it .next();
			if (c.getID() == id && c.getPassword().equals(password)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * 获取该类的单实例对象
	 * @return 单实例对象
	 */
	public static CustomerList getList() {
		return list;
	}
	
	/**
	 * 重写toString方法
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < cList.size(); i++) {
			str += cList.get(i).toString() + "\n";
		}
		return str;
	}
}
