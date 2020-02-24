package pow;
/*点的类*/
public class Point {
	public double x;
	public double y;
	public Point(double x,double y){
		this.x = x;
		this.y = y;
	}
	public Point(){
		this.x = 0;
		this.y = 0;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
}
