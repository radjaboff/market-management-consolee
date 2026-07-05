package market_management.repository;

import market_management.entity.User;
import market_management.enums.UserRole;

import java.io.*;
import java.util.*;

public class UserRepository {

    public static UserRepository userRepository;
    private  UserRepository(){}
    public static UserRepository getInstance(){
        if (userRepository==null){
            userRepository=new UserRepository();
        }
        return userRepository;
    }


    public void saveUser(User user) {
        List<User> list = getList();
        list.add(user);
        saveList(list);

    }

    public  Optional<User> getUserById(String id){
        List<User> list = getList();
        for (User user : list) {
            if (user.getId().equals(id)){
                return Optional.of(user);
            }
        }
       return Optional.empty();
    }


    public Optional<User> getUserByEmail(String email){
        List<User> list = getList();
        for (User user : list) {
            if ( user.getEmail().equals(email)){
                return  Optional.of(user);
            }
        }
        return  Optional.empty();
    }



    private void saveList(List<User> list) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/market_management/repository/user.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public List<User> getList() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/market_management/repository/user.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<User> list = (List<User>) objectInputStream.readObject();
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
        List<User> list1 = getList();
        if (list1.isEmpty()) {
            List<User> list = new ArrayList<>();
            list.add(new User(UUID.randomUUID().toString(),"Akmal Rajabov","akmalrajabov017@gmail.com","Akmal017", UserRole.ADMIN));
            list.add(new User(UUID.randomUUID().toString(),"Yunus Zoirov","yunuszoirov@gmail.com","yunus2937", UserRole.CUSTOMER));
            saveList(list);
        }
    }





}
