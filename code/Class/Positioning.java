import java.util.Iterator;
import java.util.List;


public class Positioning {

    private static final double MAX_DISTANCE = Double.MAX_VALUE;
    private Point currentPositionPoint;
    private Map currentMap ;

    public Positioning()
    {

    }

    public void setCurrentPositionPoint(Point p)
    {
        currentPositionPoint = p;
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

    private Point findTheNearestPointByDis()
    {
        Point nearestPoint = new Point();
        double minDis = MAX_DISTANCE;
        Iterator<Point> itr = currentMap.getPointList().iterator();
        while(itr.hasNext())
        {
            Point cP = itr.next();
            double dis = calculateDis(cP,currentPositionPoint);
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

    private Point findTheNearestPointByCos()
    {
        Point nearestPoint = new Point();
        double maxCos = 1;
        Iterator<Point> itr = currentMap.getPointList().iterator();
        while(itr.hasNext())
        {
            Point cP = itr.next();
            double dis = calculateCos(cP,currentPositionPoint);
            if (dis >= maxCos)
            {
                maxCos = dis;
                nearestPoint = cP;
            }
        }

        return nearestPoint;
    }


    //major positioning code
    public int getMyPositionByDis()
    {
        return findTheNearestPointByDis().getId();
    }

    public int getMyPositionByCos()
    {
        return findTheNearestPointByCos().getId();
    }
}
