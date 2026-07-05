package market_management.repository;

import market_management.entity.Product;
import market_management.entity.User;
import market_management.enums.UserRole;

import java.io.*;
import java.util.*;

public class ProductRepository {
    public static ProductRepository productRepository;
    private  ProductRepository(){}
    public static ProductRepository getInstance(){
        if (productRepository==null){
            productRepository=new ProductRepository();
        }
        return productRepository;
    }

    public void saveProduct(Product product) {
        List<Product> list = getList();
        list.add(product);
        saveList(list);

    }

    public  Optional<Product> getProductById(String id){
        List<Product> list = getList();
        for (Product product : list) {
            if (product.getId().equals(id)){
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }


    public  Optional<Product> getProductByName(String  name){
        List<Product> list = getList();
        for (Product product : list) {
            if (product.getName().equals(name)){
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public boolean delete(int index) {
        List<Product> list = getList();

        if (index < 0 || index >= list.size()) {
            return false;
        }
        list.remove(index);
        saveList(list);
        return true;
    }



    public boolean update(int index,double newPrice) {
        List<Product> list = getList();

        if (index < 0 || index >= list.size()) {
            return false;
        }
        list.get(index).setPrice(newPrice);
        saveList(list);
        return true;
    }

    public boolean updateQuantity(int index, int newQuantity) {
        List<Product> list = getList();

        if (index < 0 || index >= list.size()) {
            return false;
        }
        list.get(index).setQuantity(newQuantity);
        saveList(list);
        return true;
    }





    public void saveList(List<Product> list) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/market_management/repository/product.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public List<Product> getList() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/market_management/repository/product.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Product> list = (List<Product>) objectInputStream.readObject();
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
        List<Product> list1 = getList();
        if (list1.isEmpty()) {
            List<Product> list = new ArrayList<>();
            list.add(new Product(UUID.randomUUID().toString(),"Non",5000,20));
            list.add(new Product(UUID.randomUUID().toString(),"Daftar",3000,30));
            list.add(new Product(UUID.randomUUID().toString(),"Kartoshka",6000,20));
            list.add(new Product(UUID.randomUUID().toString(),"Piyoz",2000,10));
            saveList(list);
        }
    }



}
