package positionList;

import java.util.ArrayList;
import java.util.Iterator;

public class PositionList {
	private static PositionList list = new PositionList();
	private ArrayList<Position> pList;
	
	private PositionList() {
		pList = new ArrayList<Position>();
	}
	
	public static PositionList getList() {
		return list;
	}

	public Position searchPosition(String name) {
		Iterator<Position> it = pList.iterator();
		while(it.hasNext()) {
			Position p = it.next();
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Position> getPositionList(){
		return pList;
	}
}
