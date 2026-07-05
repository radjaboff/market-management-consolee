package market_management.controller;
import market_management.dto.OrderDTO;
import market_management.dto.ProductDTO;
import market_management.entity.Product;
import market_management.service.OrderService;
import market_management.service.ProductService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import  static market_management.utils.Util.*;
public class UserController {

    ProductService productService=ProductService.getInstance();
    OrderService orderService=OrderService.getInstance();

    public static UserController userController;
    private  UserController(){}
    public static UserController getInstance(){
        if (userController==null){
            userController=new UserController();
        }
        return userController;
    }


    public  void userMenu(){
        while (true){
            System.out.print("""
                    1. Barcha mahsulotlarni ko‘rish
                    2. Mahsulot qidirish
                    3. Mahsulot sotib olish
                    4. Xaridlar tarixini ko‘rish
                    5. Filter
                    6. Chiqish
                    """);
            int choose = getNum("Tanlang");
            switch (choose){
                case 1->showProduct();
                case 2->searchProduct();
                case 3->sellProduct();
                case 4->historyProducts();
                case 5->productFilter();
                case 6->{return;}
            }

        }
    }

    private void productFilter() {
        Double minPrice = getDouble("Min summa kiriting");
        Double maxPrice = getDouble("Min summa kiriting");
        List<ProductDTO> list = productService.filterProduct(minPrice, maxPrice);
        if ( list.isEmpty()){
            System.out.println("Sz kiritgan summalar buyicha mahsulot topilmadi");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+") "+"Mahsulot Nomi: " +list.get(i).name()+" | Mahsulot narxi: "+list.get(i).price()+" som "+" | Mahsulot miqdori: "+list.get(i).quantity());
        }

    }

    private void historyProducts() {
        List<OrderDTO> orderDTOS = orderService.historyProducts();
        if (orderDTOS.isEmpty()) {
            System.out.println("Siz hali hech qanday mahsulot sotib olmagansiz.");
            return;
        }
        System.out.println("===========Xaridlar tarixi============");
        for (OrderDTO orderDTO : orderDTOS) {
            System.out.println(" Nomi: "+orderDTO.productName()+" | "+" Miqdori: "+orderDTO.quantity()+" | " +" Narxi: "+orderDTO.price()+" som "+" | "+" Jami: "+orderDTO.totalPrice()+" | "+" Vaqti: "+orderDTO.orderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }

    private void showProduct() {
        List<ProductDTO> list = productService.showProducts();
        if (list.isEmpty()) {
            System.out.println("Hozircha mahsulotlar mavjud emas.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+") "+"Mahsulot Nomi: " +list.get(i).name()+" | Mahsulot narxi: "+list.get(i).price()+" som "+" | Mahsulot miqdori: "+list.get(i).quantity());
        }
    }

    private void sellProduct() {
        List<ProductDTO> list = productService.showProducts();
        if (list.isEmpty()) {
            System.out.println("Hozircha mahsulotlar mavjud emas.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+") "+"Mahsulot Nomi: " +list.get(i).name()+" | Mahsulot narxi: "+list.get(i).price()+" som "+" | Mahsulot miqdori: "+list.get(i).quantity());
        }
        int chooseProduct = getNum("Sotib olmoqchi bolgan praduct tanlang");
        int sellQuantity = getNum("Miqdorini kiriting");
        boolean success = orderService.buyProduct(chooseProduct, sellQuantity);
        if (success) {
            System.out.println("Mahsulot muvaffaqiyatli sotib olindi.");
        } else {
            System.out.println("Xaridni amalga oshirib bo'lmadi.");
        }
    }

    private void searchProduct() {
        String searchName = getStr("Qidirmoqchi bolgan Praduct nomi");
        Optional<Product> optional = productService.searchProduct(searchName);
        optional.ifPresentOrElse(System.out::println,()-> System.out.println("Sz izlagan product yoq"));
    }

}
