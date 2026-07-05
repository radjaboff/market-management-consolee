package market_management.repository;

import market_management.entity.Order;
import market_management.entity.Product;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class OrderRepository {



    public static OrderRepository oderRepository;
    private  OrderRepository(){}
    public static OrderRepository getInstance(){
        if (oderRepository==null){
            oderRepository=new OrderRepository();
        }
        return oderRepository;
    }


    public void saveOrder(Order order) {
        List<Order> list = getList();
        list.add(order);
        saveList(list);

    }

    public List<Order> findByCustomerId(String currentId){
        List<Order> list = getList();
        List<Order> newList=new ArrayList<>();
        for (Order order : list) {
            if (order.getConsumerId().equals(currentId)){
                newList.add(order);
            }
        }
        return newList;
    }



    public void saveList(List<Order> list) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/market_management/repository/order.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public List<Order> getList() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/market_management/repository/order.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Order> list = (List<Order>) objectInputStream.readObject();
            objectInputStream.close();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }




    {
        List<Order> list1 = getList();
        if (list1.isEmpty()) {
            List<Order> list = new ArrayList<>();
            saveList(list);
        }
    }




}
