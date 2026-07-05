package market_management.controller;

import market_management.dto.OrderDTO;
import market_management.dto.ProductDTO;
import market_management.repository.UserRepository;
import market_management.service.OrderService;
import market_management.service.ProductService;

import java.time.format.DateTimeFormatter;
import java.util.List;

import  static  market_management.utils.Util.*;

public class AdminController {
    private  final ProductService productService=ProductService.getInstance();
    private  final OrderService orderService=OrderService.getInstance();

    public static AdminController adminController;
    private  AdminController(){}
    public static AdminController getInstance(){
        if (adminController==null){
            adminController=new AdminController();
        }
        return adminController;
    }

    public  void  adminMenu(){
        while (true){
            System.out.print("""
                    1. Mahsulot qo‘shish
                    2. Mahsulotni o‘chirish
                    3. Mahsulotni yangilash
                    4. Mahsulotlar ro‘yxatini ko‘rish
                    5. Savdo tarixini ko‘rish
                    6. Chiqish
                    """);
            int choose = getNum("Tanlang");
            switch (choose){
                case 1->addProduct();
                case 2->deleteProduct();
                case 3->updateProduct();
                case 4->showProduct();
                case 5->historySell();
                case 6->{return;}
            }

        }
    }

    private void historySell() {
        List<OrderDTO> orderDTOS = orderService.showAllHistory();
        if (orderDTOS.isEmpty()){
            System.out.println("Hali heech qanday mahsulot sotilmagan!!!");
            return;
        }
        for (OrderDTO orderDTO : orderDTOS) {
            System.out.println(orderDTO.customerName()+" | "+" Nomi: "+orderDTO.productName()+" | "+" Miqdori: "+orderDTO.quantity()+" | " +" Narxi: "+orderDTO.price()+" som "+" | "+" Jami: "+orderDTO.totalPrice()+" | "+" Vaqti: "+orderDTO.orderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }

    private void updateProduct() {
        List<ProductDTO> list = productService.showProducts();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+") "+"Mahsulot Nomi: " +list.get(i).name()+" | Mahsulot narxi: "+list.get(i).price()+" som "+" | Mahsulot miqdori: "+list.get(i).quantity());
        }
        int index = getNum("Qaysi Mahsulotni uzgartirmoqchisz tanlang");
        System.out.print("""
                1.Narxi buyicha
                2.Miqdori buyicha
                """);
        int choose = getNum("Qaysi buyicha uzgartirmoqchisz");
        switch (choose){
            case 1->{
                Double newPrice = getDouble("Yangi narx kiriting");
                boolean b = productService.updatePrice(index, newPrice);
                if (b){
                    System.out.println("Narx uzgardi");
                }else {
                    System.out.println("Tugri narx kiriiting");
                }
            }
            case 2->{
                int newQuantity=getNum("Yangi miqdor kiriting");
                boolean b = productService.updateQuantity(index,newQuantity);
                if (b){
                    System.out.println("Midroq uzgardi");
                }else {
                    System.out.println("Tugri miqdor kiriiting");
                }
            }
        }


    }

    public void showProduct() {
        List<ProductDTO> list = productService.showProducts();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+") "+"Mahsulot Nomi: " +list.get(i).name()+" | Mahsulot narxi: "+list.get(i).price()+" som "+" | Mahsulot miqdori: "+list.get(i).quantity());
        }
    }

    private void deleteProduct() {
        List<ProductDTO> list = productService.showProducts();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+") "+"Mahsulot Nomi: " +list.get(i).name()+" | Mahsulot narxi: "+list.get(i).price()+" som "+" | Mahsulot miqdori: "+list.get(i).quantity());
        }
        int choose = getNum("Uchirmoqchi bo'lgan mahsulotni tanlang");
        boolean b = productService.deleteProduct(choose);
        if (b){
            System.out.println("Mahsulot uchirildi!!!");
        }else {
            System.out.println("Mahsulot uchirilmadi malumot tog'ri kiriting!!!");
        }
    }

    private void addProduct() {
        String name = getStr("Nomini kiriting");
        Double price= getDouble("Narxini kiriting");
        int quantity = getNum("Miqdorini kiriting");
        ProductDTO dto=new ProductDTO(name,price,quantity);
        boolean b = productService.addProduct(dto);
        if (b){
            System.out.println("Mahsulot qushildi");
        }else {
            System.out.println("Xatolik yuz berdi Qayta urinib koring");
        }
    }

}
