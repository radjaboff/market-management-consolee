package market_management.controller;
import market_management.dto.LoginDTO;
import market_management.dto.UserDTO;
import market_management.enums.UserRole;
import market_management.service.AuthService;

import java.util.Optional;

import  static  market_management.utils.Util.*;
public class AuthController {

    private  final AuthService authService=AuthService.getInstance();
    private  final AdminController adminController=AdminController.getInstance();
    private  final  UserController userController=UserController.getInstance();

    public  void  start(){


      while (true){
          System.out.print("""
                1. Kirish
                2. Ro'yxatdan o'tish
                3. Chiqish
                """);
          int menuChoose = getNum("Tanlang");
          switch (menuChoose){
              case 1->loogin();
              case 2->registration();
              case 3->{return;}
          }


      }

    }

    private void loogin() {
        String email = getStr("Emailigizni  kiriting");
        String password = getStr("Password kiritng");
        LoginDTO dto=new LoginDTO(email,password);
        Optional<UserRole> res = authService.loogin(dto);
        if (res.isEmpty()){
            System.out.println("Email yoki password xato!!!");
        }else {
            UserRole userRole = res.get();
            switch (userRole){
                case ADMIN -> adminController.adminMenu();
                case CUSTOMER -> userController.userMenu();
            }
        }


    }

    private void registration() {
        String fullName = getStr("Ism Familiya kiriting");
        String email = getStr("Emailigizni  kiriting");
        String password = getStr("Password yarating");
        UserDTO dto=new UserDTO(fullName,email,password);
        boolean registration = authService.registration(dto);
        if ( registration){
            System.out.println("Sz rruyxatdan mufaqiyatli utdingiz!!!");
        }else {
            System.out.println("Bu email oldin ruyxatdan utgan.Qayta urinib koring!!!");
        }

    }

}
