

abstract class Shape
{
	String shapeName;
	protected double area;
	static int count=0;
	public Shape(String name)
	{
		shapeName=name;
		count++;
	}
	public String getShapeName()
	{
		return shapeName;
	}
	public abstract double area();
	public abstract double area(double i);
}

class Rectangle extends Shape
{
	protected double length,width;
	public Rectangle(String name,double length,double width)
	{
		super(name);
		this.length=length;
		this.width=width;
	}
	public double area() 
	{
		area=(double)length*width;
		return area;
	}
	public double area(double diagonal)
	{
		double d= (diagonal*diagonal) - (length*length);
		return area = length*((double)Math.sqrt(d));
	}
	
}
class Circle extends Shape
{
	public final double PI=3.14;
	protected double radius;
	public Circle(String name,double radius)
	{
		super(name);
		this.radius=radius;
	}
	public double area()
	{
		area=(double)PI*radius*radius;
		return area;
	}
	public double area(double diameter)
	{
		radius=(double)diameter/2;
		return area=(double)PI*radius*radius;
	}
}
class SemiCircle extends Circle
{
	public SemiCircle(String name,double radius)
	{
		super(name,radius);                                     
	}
	public double area()
	{
		return area = ((double)1/2)*(PI*radius*radius);
	}
	public double area(double diameter)
	{
		radius=(double)diameter/2; 
		return area = ((double)1/2)*(PI*radius*radius);
	}
}

public class MainProject2
{
	public static void main(String args[])
	{
		Shape ref;
		ref=new Rectangle("Rectangle",10.0,8.0);
		System.out.println(Shape.count+". The shape is: "+ref.getShapeName()+" and its area is: "+ref.area(Math.sqrt(164)));
		ref=new SemiCircle("SemiCircle",10.0);
		System.out.println(Shape.count+". The shape is: "+ref.getShapeName()+" and its area is: "+ref.area());
		ref=new Circle("Circle",10.0);
		System.out.println(Shape.count+". The shape is: "+ref.getShapeName()+" and its area is: "+ref.area());
		
	}
}
