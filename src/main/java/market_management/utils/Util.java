package market_management.utils;

import java.util.Scanner;

public class Util {
    public  static Scanner scannerStr=new Scanner(System.in);
    public  static Scanner scannerNum =new Scanner(System.in);

    public  static  String  currentUserId;

    public  static  String  getStr(String  text){
        System.out.print(text+": ");
        return  scannerStr.nextLine();
    }
    public  static  int  getNum(String  text){
        System.out.print(text+": ");
        return  scannerNum.nextInt();
    }
    public  static  Double  getDouble(String  text){
        System.out.print(text+": ");
        return  scannerNum.nextDouble();
    }

}
