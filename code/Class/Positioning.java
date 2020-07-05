import java.util.Iterator;
import java.util.List;


public class Positioning {

    private static final double MAX_DISTANCE = Double.MAX_VALUE;
    private Map currentMap ;
    private static final Positioning positionTool = new Positioning();

    public Positioning()
    {

    }
    
    public static Positioning positionTool()
    {
        return positionTool;
    }

    public Point correctCoordinate(Point pInMap, Point currentPosition)
    {
        Point newVirtualPoint = new Point();
        String BSSID1 = pInMap.getAp1().getBSSID();
        String BSSID2 = pInMap.getAp2().getBSSID();
        String BSSID3 = pInMap.getAp3().getBSSID();
        String BSSID4 = pInMap.getAp4().getBSSID();

        Iterator <AP> itr = currentPosition.getAPList().iterator();
        while(itr.hasNext())
        {
            AP ap = itr.next();
            if (ap.getBSSID() == BSSID1)
            {
                newVirtualPoint.setAp1(ap);
            }
            if (ap.getBSSID() == BSSID2)
            {
                newVirtualPoint.setAp2(ap);
            }
            if (ap.getBSSID() == BSSID3)
            {
                newVirtualPoint.setAp3(ap);
            }
            if (ap.getBSSID() == BSSID4)
            {
                newVirtualPoint.setAp4(ap);
            }
        }
        return newVirtualPoint;
    }

    public void initCurrentMap(List<Point> pList , String id) {
        this.currentMap.setPoints(pList);
        this.currentMap.setID(id);
    }

    // calculate the Euclidean Distance
    private double calculateDis(Point p1, Point p2)
    {
        double distance = -1;


        distance = Math.sqrt((p1.getAp1().getLevel()-p2.getAp1().getLevel())*(p1.getAp1().getLevel()-p2.getAp1().getLevel())
                            +(p1.getAp2().getLevel()-p2.getAp2().getLevel())*(p1.getAp2().getLevel()-p2.getAp2().getLevel())
                            +(p1.getAp3().getLevel()-p2.getAp3().getLevel())*(p1.getAp3().getLevel()-p2.getAp3().getLevel())
                            +(p1.getAp4().getLevel()-p2.getAp4().getLevel())*(p1.getAp4().getLevel()-p2.getAp4().getLevel()));

        return distance;
    }

    private Point findTheNearestPointByDis(Point currentPositionPoint)
    {
        Point nearestPoint = new Point();
        double minDis = MAX_DISTANCE;
        Iterator<Point> itr = currentMap.getPointList().iterator();
        while(itr.hasNext())
        {
            Point cP = itr.next();
            double dis = calculateDis(cP,correctCoordinate(cP,currentPositionPoint));
            if (dis <= minDis)
            {
                minDis = dis;
                nearestPoint = cP;
            }
        }

        return nearestPoint;
    }

    // calculate the cosine in 4-dimensional map
    private double calculateCos(Point p1, Point p2)
    {
        double cosine = -1; // 180° is impossible

        Point Opoint = new Point();
        cosine = calculateInnerProduct(p1,p2) / (calculateDis(p1,Opoint)*calculateDis(p2,Opoint));

        return cosine;
    }

    // calculate the inner product
    private double calculateInnerProduct(Point p1, Point p2) {
        double InnerProduct = -2;

        InnerProduct = (p1.getAp1().getLevel()*p2.getAp1().getLevel()
                        +p1.getAp2().getLevel()*p2.getAp2().getLevel()
                        +p1.getAp3().getLevel()*p2.getAp3().getLevel()
                        +p1.getAp4().getLevel()*p2.getAp4().getLevel());

        return InnerProduct;
    }

    private Point findTheNearestPointByCos(Point currentPositionPoint)
    {
        Point nearestPoint = new Point();
        double maxCos = 1;
        Iterator<Point> itr = currentMap.getPointList().iterator();
        while(itr.hasNext())
        {
            Point cP = itr.next();
            double dis = calculateCos(cP,correctCoordinate(cP,currentPositionPoint));
            if (dis >= maxCos)
            {
                maxCos = dis;
                nearestPoint = cP;
            }
        }

        return nearestPoint;
    }


    //major positioning code
    public int getMyPositionByDis(Point cPosition)
    {
        return findTheNearestPointByDis(cPosition).getId();
    }

    public int getMyPositionByCos(Point cPosition)
    {
        return findTheNearestPointByCos(cPosition).getId();
    }
}
