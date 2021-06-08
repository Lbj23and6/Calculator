package Menu;

import Car.*;
import DB.DAO.CarDao;
import DB.DAO.Impl.BusDaoImpl;
import DB.DAO.Impl.CarDaoImpl;
import DB.DAO.Impl.TrunkDaoImpl;
import DB.pojo.Bus;
import DB.pojo.Car;
import DB.pojo.Trunk;

import java.util.List;
import java.util.Scanner;
public class Cmenu {
    public static void menu(){
        Scanner sc = new Scanner(System.in);
        CarDao carDao = new CarDaoImpl();//多态
        BusDaoImpl busDao = new BusDaoImpl();
        TrunkDaoImpl trunkDao = new TrunkDaoImpl();
        while(true){
            int choice,a;
            String id;
            System.out.println("1:查看所有车辆信息     2:按车牌号查询车辆      3:租车      4:还车");
            System.out.println("请选择:");
            choice=sc.nextInt();
            switch (choice){
                case 1:
                    List<Car> cars = carDao.showAllCars();
                    List<Bus> buses = busDao.showAllBuss();
                    List<Trunk> trunks = trunkDao.showAllTrunks();
                    System.out.println("汽车信息如下：");
                    for (Car car : cars) {
                        System.out.println(car);
                    }
                    System.out.println("公交车信息如下：");
                    for (Bus bus : buses) {
                        System.out.println(bus);
                    }
                    System.out.println("货车信息如下：");
                    for (Trunk trunk : trunks) {
                        System.out.println(trunk);
                    }
                    break;
                case 2:
                    System.out.println("1:汽车        2:公交车        3:货车");
                    System.out.println("请选择:");
                    a=sc.nextInt();
                    switch (a){
                        case 1:
                            System.out.println("请输入所需查看的车辆的车牌号：");
                            id = sc.next();
                            System.out.println(carDao.showCar(id));
                            break;
                        case 2:
                            System.out.println("请输入所需查看的车辆的车牌号：");
                            id = sc.next();
                            System.out.println(busDao.showBus(id));
                            break;
                        case 3:
                            System.out.println("请输入所需查看的车辆的车牌号：");
                            id = sc.next();
                            System.out.println(trunkDao.showTrunk(id));
                            break;
                        default:
                            System.out.println("输入错误！");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("1:汽车        2:公交车        3:货车");
                    System.out.println("请选择:");
                    a=sc.nextInt();
                    switch (a){
                        case 1:
                            System.out.println("请输入您要租车的车牌号：");
                            id=sc.next();
                            Car car = carDao.showCar(id);
                            if(car.getState()==0){
                                System.out.println("给您分配的车辆信息为："+car);
                                System.out.println("租车费用为:"+new CAR().calRent(car.getPerRent()));
                                carDao.changeState(id,1);
                            }else {
                                System.out.println("该车已被出租，请选择其他车辆！");
                                continue;
                            }
                            break;
                        case 2:
                            System.out.println("请输入您要租车的车牌号：");
                            id=sc.next();
                            Bus bus = busDao.showBus(id);
                            if(bus.getState()==0){
                                System.out.println("给您分配的车辆信息为："+bus);
                                System.out.println("租车费用为:"+new BUS().calRent(bus.getPerRent()));
                                carDao.changeState(id,1);
                            }else {
                                System.out.println("该车已被出租，请选择其他车辆！");
                                continue;
                            }
                            break;
                        case 3:
                            System.out.println("请输入您要租车的车牌号：");
                            id=sc.next();
                            Trunk trunk = trunkDao.showTrunk(id);
                            if(trunk.getState()==0){
                                System.out.println("给您分配的车辆信息为："+trunk);
                                System.out.println("租车费用为:"+new TRUNK().calRent(trunk.getPerRent()));
                                carDao.changeState(id,1);
                            }else {
                                System.out.println("该车已被出租，请选择其他车辆！");
                                continue;
                            }
                            break;
                        default:
                            System.out.println("输入错误！");
                            break;
                    }
                    break;

                case 4:
                    System.out.println("1:汽车        2:公交车        3:货车");
                    System.out.println("请选择还车的类型:");
                    a=sc.nextInt();
                    switch (a){
                        case 1:
                            System.out.println("请输入您要还车的车牌号：");
                            id=sc.next();
                            Car car = carDao.showCar(id);
                            if(car.getState()==1){
                                System.out.println("车辆归还成功！欢迎下次使用！");
                                carDao.changeState(id,0);
                            }else {
                                System.out.println("该车未被出租，请检查你输入的车辆信息！");
                                continue;
                            }
                            break;
                        case 2:
                            System.out.println("请输入您要还车的车牌号：");
                            id=sc.next();
                            Bus bus = busDao.showBus(id);
                            if(bus.getState()==1){
                                System.out.println("车辆归还成功！欢迎下次使用！");
                                busDao.changeState(id,0);
                            }else {
                                System.out.println("该车未被出租，请检查你输入的车辆信息！");
                                continue;
                            }
                            break;
                        case 3:
                            System.out.println("请输入您要还车的车牌号：");
                            id=sc.next();
                            Trunk trunk = trunkDao.showTrunk(id);
                            if(trunk.getState()==1){
                                System.out.println("车辆归还成功！欢迎下次使用！");
                                trunkDao.changeState(id,0);
                            }else {
                                System.out.println("该车未被出租，请检查你输入的车辆信息！");
                                continue;
                            }
                            break;
                    }
                    break;
                default:
            }
            System.out.println("输入1返回菜单");
            a=sc.nextInt();
            if(a==1){
                continue;
            }
            break;

        }
    }
}