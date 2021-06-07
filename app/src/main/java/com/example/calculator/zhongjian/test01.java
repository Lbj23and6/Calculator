package PTA;

/*import java.lang.reflect.*;

class Student{
    private String name;
    private int age;
    public String toString(){
        return  "name is" + name +",age is" + "age";
    }
}

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Student p = new Student();
        Class cla = p.getClass();
        Field nameField = cla.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(p，"Jack");
        //设置对象的姓名属性为Jack
    }
}*/
/*import java.util.*;
public class Main{
    public static void main(String[] args) {
        List<String> al=new ArrayList<String>();
        al.add("red");
        al.add("yellow");
        al.add("blue");

        ListIterator< String > listIter=al.listIterator();
        while(listIter.hasNext())
            System.out.print(listIter.next()+"  ");
    }
}*/
/*
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new PrintTask());
        t1.start();
        t1.setDaemon(true);



        System.out.println(Thread.currentThread().getName()+" end");
    }
}*/
/*在这里给出函数被调用进行测试的例子。例如：*//*

import java.util.*;
class SharedBike {
    private int bid;//单车编号
    private String bname;//单车名称-->ofo 哈罗 摩拜
    private int status;//单车状态--> 0 已借出 1 可借状态
    private int usetime;//使用时间
    public int getBid() {
        return bid;
    }
    public String getBname() {
        return bname;
    }
    public int getStatus() {
        return status;
    }
    public int getUsetime() {
        return usetime;
    }
    public SharedBike(int bid, String bname, int status, int usetime)
    {
        this.bid=bid;
        this.bname=bname;
        this.status=status;
        this.usetime=usetime;
    }
    public float rent(){ //计算租金
        return 0.0f;
    }
    //重写toString()方法，输出单车元素不是地址
    public String toString(){
        return bid+" "+bname+" "+status+" "+usetime+" "+String.format("%.2f", rent());
    }
}
public class Main {
    public static void main(String[] args) {
        int bid;
        String bname;
        int status;//单车状态--> 0 已借出 1 可借状态
        int usetime;//使用时间
        String password;//密码
        int weight;

        Scanner sc=new Scanner(System.in);
        bid=sc.nextInt();
        bname=sc.next();
        status=sc.nextInt();
        usetime=sc.nextInt();
        password=sc.next();
        OfoBike obike=new OfoBike(bid,bname,status,usetime,password);
        bid=sc.nextInt();
        bname=sc.next();
        status=sc.nextInt();
        usetime=sc.nextInt();
        weight=sc.nextInt();
        HaloBike hbike=new HaloBike(bid,bname,status,usetime,weight);
        System.out.println(obike.toString());
        System.out.print(hbike.toString());
    }
}

*/
/* 请在这里填写答案 编写OfoBike类和HaloBike类*//*

class OfoBike extends SharedBike{
    private String password;

    @Override
    public float rent() {
        return (float) this.getUsetime();
    }

    @Override
    public String toString() {
        return this.getBid()+" "+this.getBname()+" "+this.getStatus()+" "+this.getUsetime()+" "+password+" "+String.format("%.2f", rent());
    }

    public OfoBike(int bid, String bname, int status, int usetime, String password) {
        super(bid, bname, status, usetime);
        this.password = password;
    }

    public OfoBike(int bid, String bname, int status, int usetime) {
        super(bid, bname, status, usetime);
    }
}
class HaloBike extends SharedBike{
private int weight;

    @Override
    public float rent() {
        return (float)this.getUsetime()*0.5f;
    }

    @Override
    public String toString() {
        return this.getBid()+" "+this.getBname()+" "+this.getStatus()+" "+this.getUsetime()+" "+weight+" "+String.format("%.2f", rent());
    }

    public HaloBike(int bid, String bname, int status, int usetime, int weight) {
        super(bid, bname, status, usetime);
        this.weight = weight;
    }

    public HaloBike(int bid, String bname, int status, int usetime) {
        super(bid, bname, status, usetime);
    }
}
*/
/*
public class Main {

    public static void main(String[] args) {
        IGeometry [] tuxing=new IGeometry[29]; //有29个Geometry对象
        for(int i=0;i<tuxing.length;i++) {   //29个Geometry对象分成两类
            if(i%2==0)
                tuxing[i]=new Rect(16+i,68);
            else if(i%2==1)
                tuxing[i]=new Circle(10+i);
        }
        TotalArea computer=new TotalArea();
        computer.setTuxing(tuxing);
        System.out.printf("各种图形的面积之和:\n%.2f",computer.computerTotalArea());

    }
}

 */
/*请在这里填写答案  请结合主类代码，在代码框完成IGeometry接口类，Rect类，Circle类和TotalArea类*//*


interface IGeometry{
    double getArea();
    final double PI = 3.14;
}
class Rect implements IGeometry{
    double a,b;

    @Override
    public double getArea() {
        return a*b*0.5;
    }

    public Rect(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Rect() {
    }
}
class Circle implements IGeometry{
    double r;

    @Override
    public double getArea() {
        return PI*r*r;
    }

    public Circle(double r) {
        this.r = r;
    }
}
class TotalArea{
    IGeometry[] tuxing;
    void setTuxing(IGeometry[] t){
            tuxing=t;
    }
    double computerTotalArea(){
        double a=0;
        for (int i = 0; i < tuxing.length; i++) {
            a += tuxing[i].getArea();
        }
        return a;
    }
}

*/

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        double s=0;
        Scanner sc=new Scanner(System.in);
        double r1,r2;
        r1=sc.nextDouble();
        r2=sc.nextDouble();
        Circle c1=new Circle(r1);
        Circle c2=new Circle(r2);
        try{
            s = c1.area();
            System.out.println(s);
            s = c2.area();
            System.out.println(s);
        }
        catch (CircleException e){
            e.print();
        }
    }
}


/* 请在这里填写答案 编写Circle 和CircleException类*/

class Circle{
    double r;
    final double PI = 3.14;
    public Circle(double r) {
        this.r = r;
    }
    double area() throws CircleException {
        if(r<0){
            /*通过异常类的构造函数将异常值传入异常类进行处理*/
            throw new CircleException(r);
        }
        return PI*r*r;
    }
}

class CircleException extends Exception{
    double r;
/*第一次做的时候就一直没有给异常类定义一个r，导致在输出异常时不能动态输出产生异常的具体的值*/
    public CircleException(double r) {
        this.r = r;
    }
    void print(){
        System.out.println("圆半径为"+r+"不合理");
    }
}
