package Menu;

import DB.DAO.CarDao;
import DB.DAO.Impl.*;
import DB.pojo.*;

import java.util.List;
import java.util.Scanner;

public class Mmenu {
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        CarDao carDao = new CarDaoImpl();//多态，下转型
        BusDaoImpl busDao = new BusDaoImpl();
        TrunkDaoImpl trunkDao = new TrunkDaoImpl();
        while (true) {
            int choice;
            System.out.println("1:查看所有车辆信息      2:管理车辆        3:管理人员");
            System.out.println("请选择:");
            choice = sc.nextInt();
            if (choice == 1) {
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
                int a = 0;
                System.out.println("输入1返回菜单");
                a = sc.nextInt();
                if (a == 1) {
                    continue;
                }
                break;
            } else if (choice == 2) {
                System.out.println("1:修改车辆信息        2:新增车辆        3:删除车辆");
                System.out.println("请选择:");
                choice = sc.nextInt();
                int a;
                String id;
                switch (choice) {
                    case 1:
                        System.out.println("1:汽车        2:公交车        3:货车");
                        System.out.println("请选择:");
                        a = sc.nextInt();
                        switch (a) {
                            case 1:
                                System.out.println("请输入所需修改的车牌号：");
                                id = sc.next();
                                Car car = new Car();
                                System.out.println("请输入新的车牌号:");
                                car.setVehicleId(sc.next());
                                System.out.println("请输入新的品牌：");
                                car.setBrand(sc.next());
                                System.out.println("请输入新的租金：");
                                car.setPerRent(sc.nextInt());
                                System.out.println("请输入新的型号：");
                                car.setType(sc.next());
                                if (carDao.modifyCar(id, car)) {
                                    System.out.println("修改成功！");
                                    break;
                                }
                                System.out.println("修改失败！请稍后再试！");
                                break;
                            case 2:
                                System.out.println("请输入所需修改的车牌号：");
                                id = sc.next();
                                Bus bus = new Bus();
                                System.out.println("请输入新的车牌号:");
                                bus.setVehicleId(sc.next());
                                System.out.println("请输入新的品牌：");
                                bus.setBrand(sc.next());
                                System.out.println("请输入新的租金：");
                                bus.setPerRent(sc.nextInt());
                                System.out.println("请输入新的型号：");
                                bus.setSeat(sc.nextInt());
                                if (busDao.modifyBus(id, bus)) {
                                    System.out.println("修改成功！");
                                    break;
                                }
                                System.out.println("修改失败！请稍后再试！");
                                break;
                            case 3:
                                System.out.println("请输入所需修改的车牌号：");
                                id = sc.next();
                                Trunk trunk = new Trunk();
                                System.out.println("请输入新的车牌号:");
                                trunk.setVehicleId(sc.next());
                                System.out.println("请输入新的品牌：");
                                trunk.setBrand(sc.next());
                                System.out.println("请输入新的租金：");
                                trunk.setPerRent(sc.nextInt());
                                System.out.println("请输入新的型号：");
                                trunk.setWeight(sc.nextInt());
                                if (trunkDao.modifyTrunk(id, trunk)) {
                                    System.out.println("修改成功！");
                                    break;
                                }
                                System.out.println("修改失败！请稍后再试！");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("1:汽车        2:公交车        3:货车");
                        System.out.println("请选择:");
                        a = sc.nextInt();
                        switch (a) {
                            case 1:
                                Car car = new Car();
                                System.out.println("请输入新的车牌号:");
                                car.setVehicleId(sc.next());
                                System.out.println("请输入新的品牌：");
                                car.setBrand(sc.next());
                                System.out.println("请输入新的租金：");
                                car.setPerRent(sc.nextInt());
                                System.out.println("请输入新的型号：");
                                car.setType(sc.next());
                                if (carDao.addCar(car)) {
                                    System.out.println("修改成功！");
                                    break;
                                }
                                System.out.println("修改失败！请稍后再试！");
                                break;
                            case 2:
                                Bus bus = new Bus();
                                System.out.println("请输入新的车牌号:");
                                bus.setVehicleId(sc.next());
                                System.out.println("请输入新的品牌：");
                                bus.setBrand(sc.next());
                                System.out.println("请输入新的租金：");
                                bus.setPerRent(sc.nextInt());
                                System.out.println("请输入新的型号：");
                                bus.setSeat(sc.nextInt());
                                if (busDao.addBus(bus)) {
                                    System.out.println("修改成功！");
                                    break;
                                }
                                System.out.println("修改失败！请稍后再试！");
                                break;
                            case 3:
                                Trunk trunk = new Trunk();
                                System.out.println("请输入新的车牌号:");
                                trunk.setVehicleId(sc.next());
                                System.out.println("请输入新的品牌：");
                                trunk.setBrand(sc.next());
                                System.out.println("请输入新的租金：");
                                trunk.setPerRent(sc.nextInt());
                                System.out.println("请输入新的型号：");
                                trunk.setWeight(sc.nextInt());
                                if (trunkDao.addTrunk(trunk)) {
                                    System.out.println("修改成功！");
                                    break;
                                }
                                System.out.println("修改失败！请稍后再试！");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("1:汽车        2:公交车        3:货车");
                        System.out.println("请选择:");
                        a = sc.nextInt();
                        switch (a) {
                            case 1:
                                System.out.println("请输入所需删除的车辆的车牌号：");
                                id = sc.next();
                                if (carDao.deleteCar(id)) {
                                    System.out.println("删除成功！");
                                    break;
                                }
                                System.out.println("删除失败！请稍后再试！");
                                break;
                            case 2:
                                System.out.println("请输入所需删除的车辆的车牌号：");
                                id = sc.next();
                                if (busDao.deleteBus(id)) {
                                    System.out.println("修改成功！");
                                    break;
                                }
                                System.out.println("修改失败！请稍后再试！");
                                break;
                            case 3:
                                System.out.println("请输入所需删除的车辆的车牌号：");
                                id = sc.next();
                                if (trunkDao.deleteTrunk(id)) {
                                    System.out.println("删除成功！");
                                    break;
                                }
                                System.out.println("删除失败！请稍后再试！");
                                break;
                        }
                        break;
                    default:
                        System.out.println("输入错误！");
                        break;
                }
                break;
            } else if (choice == 3) {
                CustmerDaoImpl custmerDao = new CustmerDaoImpl();
                ManagerDaoImpl managerDao = new ManagerDaoImpl();
                System.out.println("1:管理用户信息        2:管理管理员信息");
                System.out.println("请选择:");
                choice = sc.nextInt();
                int a;
                String id;
                switch (choice) {
                    case 1:
                        System.out.println("1:查看用户信息        2:修改用户信息        3:删除用户信息");
                        a = sc.nextInt();
                        switch (a) {
                            case 1:
                                List<Customer> list = custmerDao.showPeoples();
                                System.out.println("用户信息如下：");
                                for (Customer customer : list) {
                                    System.out.println(customer);
                                }
                                System.out.println("输入1返回菜单");
                                a = sc.nextInt();
                                if (a == 1) {
                                    continue;
                                }
                                break;
                            case 2:
                                System.out.println("请输入所需用户名：");
                                id = sc.next();
                                Customer customer = new Customer();
                                System.out.println("请输入新的用户名:");
                                customer.setUsername(sc.next());
                                System.out.println("请输入新的密码：");
                                customer.setPwd(sc.next());

                                if (custmerDao.modifyPeople(id, customer)) {
                                    System.out.println("修改成功！");
                                    break;
                                }
                                System.out.println("修改失败！请稍后再试！");
                                break;
                            case 3:
                                System.out.println("请输入所需删除的用户名：");
                                id = sc.next();
                                if (custmerDao.deletePeople(id)) {
                                    System.out.println("删除成功！");
                                    break;
                                }
                                System.out.println("修改失败！请稍后再试！");
                                break;
                        }
                    case 2:
                        System.out.println("1:查看管理员信息        2:修改管理员信息        3:删除管理员信息");
                        a = sc.nextInt();
                        switch (a) {
                            case 1:
                                List<Manager> list = managerDao.showPeoples();
                                System.out.println("用户信息如下：");
                                for (Manager manager : list) {
                                    System.out.println(manager);
                                }
                                System.out.println("输入1返回菜单");
                                a = sc.nextInt();
                                if (a == 1) {
                                    continue;
                                }
                                break;
                            case 2:
                                System.out.println("请输入所需用户名：");
                                id = sc.next();
                                Manager manager = new Manager();
                                System.out.println("请输入新的用户名:");
                                manager.setUsername(sc.next());
                                System.out.println("请输入新的密码：");
                                manager.setPwd(sc.next());

                                if (managerDao.modifyPeople(id, manager)) {
                                    System.out.println("修改成功！");
                                    System.out.println("输入1返回菜单");
                                    a = sc.nextInt();
                                    if (a == 1) {
                                        continue;
                                    }
                                    break;
                                }
                                System.out.println("修改失败！请稍后再试！");
                                break;
                            case 3:
                                System.out.println("请输入所需删除的用户名：");
                                id = sc.next();
                                if (custmerDao.deletePeople(id)) {
                                    System.out.println("删除成功！");
                                    System.out.println("输入1返回菜单");
                                    a = sc.nextInt();
                                    if (a == 1) {
                                        continue;
                                    }
                                    break;
                                }
                                System.out.println("删除失败！请稍后再试！");
                                break;
                            default: System.out.println("没有这个选项，请重新选择");
                        }
                }
            }else{
                System.exit(1);
            }
        }
    }
}