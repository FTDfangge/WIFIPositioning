/**
 * 
 */
package customerList;

import java.util.Comparator;

/**
 * 用户比较器类
 * @author 章富 2019年11月21日
 */
public class CustomerComparator implements Comparator<Customer> {

	@Override
	public int compare(Customer c1, Customer c2) {
		return c1.compareTo(c2);
	}
}
