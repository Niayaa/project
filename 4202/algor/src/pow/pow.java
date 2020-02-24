package pow;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
public class pow{
    public static void main(String[] arg){
        //初始化赋值过程
        List<Point> points = new ArrayList<Point>();
        Point point = new Point(0,0);
        points.add(point);
        point = new Point(7,2);
        points.add(point);
        point = new Point(5,9);
        points.add(point);
        point = new Point(3,8);
        points.add(point);
        point = new Point(0,1);
        points.add(point);
        List<Vector> vectors = new ArrayList<Vector>();
        vectors.add(new Vector(new Point(8,0), new Point(8,6)));
        vectors.add(new Vector(new Point(8,6), new Point(1,6)));
        vectors.add(new Vector(new Point(1,6), new Point(1,0)));
        vectors.add(new Vector(new Point(1,0), new Point(8,0)));
        userWindow ui = new userWindow();
        //利用算法求出切割后的顶点集合，代表新的多边形
        List<Point> result = Sutherland_Hodgeman(points,vectors);
        //将所有的节点打印出来
        for(int k=0;k<result.size();k++){
            System.out.println(result.get(k).getX()+"|"+result.get(k).getY());
        }
    }
    //GUI
    //裁剪算法
    public static List<Point> Sutherland_Hodgeman(List<Point> points,List<Vector> vectors){
        List<Point> result = new ArrayList<Point>();
        List<Point> cur = new ArrayList<Point>();

        int vectorsSize = vectors.size();
        int pointSize = points.size();

        Point S = points.get(pointSize-1);
        //初始化操作的集合
        for(int i=0;i<pointSize;i++){
            result.add(points.get(i));
        }

        boolean flag;
        for(int j=0;j<vectorsSize;j++){
            //flag = false表示在内侧，flag = true表示在外侧
            if(isInside(S,vectors.get(j)))
                flag = false;
            else
                flag = true;
            int resultSize = result.size();
            for(int i=0;i<resultSize;i++){
                //证明其在当前vector的内
                if(isInside(result.get(i),vectors.get(j))){
                    //如果前一个点在vector的外侧，那么将他们的交点加入到结果集中
                    if(flag){
                        flag = false;
                        cur.add(Intersection(S, result.get(i), vectors.get(j).start, vectors.get(j).end));
                    }
                    //并将当前节点加入到结果集中
                    cur.add(result.get(i));
                }
                else{
                    //前一个点在外侧吗？
                    if(!flag){
                        flag = true;
                        //如果前一个点在vector的内侧，那么将他们的交点加入到结果集中
                        cur.add(Intersection(S, result.get(i), vectors.get(j).start, vectors.get(j).end));
                    }
                }
                //更新首次比较的节点
                S = result.get(i);
            }
            //将本次结果拷贝出来，作为下次对比的样本，并将本次结果进行清空
            int resultLen = result.size();
            result.clear();
            for(int i=0;i<resultLen;i++){
                result.add(cur.get(i));
            }
            cur.clear();
        }
        return result;
    }

    //求一个点是否在一条边的内侧，在点序为逆时针的时候（如果点在线上，也算在内侧）
    public static boolean isInside(Point p,Vector v){
        return Multi(p,v.start,v.end)>=0?true:false;
    }


    //求叉积p0->p1与p0->p2的叉积
    public static double Multi(Point p0,Point p1,Point p2){
        return (p1.x-p0.x)*(p2.y-p0.y)-(p2.x-p0.x)*(p1.y-p0.y);
    }

    public static Point Intersection(Point start0,Point end0,Point start1,Point end1){
        //由正弦定理推出
        double pX = (Multi(start0, end1, start1)*end0.x - Multi(end0, end1, start1)*start0.x)/
                (Multi(start0, end1, start1) - Multi(end0, end1, start1));
        double pY = (Multi(start0, end1, start1)*end0.y - Multi(end0, end1, start1)*start0.y)/
                (Multi(start0, end1, start1) - Multi(end0, end1, start1));
        return new Point(pX,pY);
    }
}
