package market_management.service;

public class UserService {
    public static UserService userService;
    private  UserService(){}
    public static UserService getInstance(){
        if (userService==null){
            userService=new UserService();
        }
        return userService;
    }
}
