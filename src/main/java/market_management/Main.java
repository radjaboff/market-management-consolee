package market_management;

import market_management.controller.AuthController;

public class Main {
    public static void main(String[] args) {

        AuthController controller=new AuthController();
        controller.start();


    }
}