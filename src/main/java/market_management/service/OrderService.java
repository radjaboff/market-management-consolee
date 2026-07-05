package market_management.service;

import market_management.controller.UserController;
import market_management.dto.OrderDTO;
import market_management.entity.Order;
import market_management.entity.Product;
import market_management.entity.User;
import market_management.repository.OrderRepository;
import market_management.repository.ProductRepository;
import market_management.repository.UserRepository;
import market_management.utils.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderService {
    private OrderRepository orderRepository=OrderRepository.getInstance();
    private final ProductRepository productRepository=ProductRepository.getInstance();
    private  final UserRepository userRepository=UserRepository.getInstance();

    public static OrderService orderService;
    private  OrderService(){}
    public static OrderService getInstance(){
        if (orderService==null){
            orderService=new OrderService();
        }
        return orderService;
    }


    public boolean buyProduct(int chooseProduct, int sellQuantity) {
        List<Product> list = productRepository.getList();
        if (chooseProduct < 0 || chooseProduct >= list.size()) return  false;
        if (sellQuantity <= 0) {
            return false;
        }
        Product product = list.get(chooseProduct);
        if (product.getQuantity()<sellQuantity) return  false;
        product.setQuantity(product.getQuantity()-sellQuantity);
        productRepository.saveList(list);



        Order order=new Order(UUID.randomUUID().toString(),Util.currentUserId, product.getId(), sellQuantity,LocalDateTime.now());
        orderRepository.saveOrder(order);

        return  true;
    }

    public List<OrderDTO> historyProducts() {
        List<Order> list = orderRepository.findByCustomerId(Util.currentUserId);
        List<OrderDTO> orderDTOS=new ArrayList<>();


        for (Order order : list) {
            Product product = productRepository.getProductById(order.getProductId()).get();
            User user = userRepository.getUserById(order.getConsumerId()).get();
            OrderDTO dto = new OrderDTO(
                    user.getFullName(),
                    product.getName(),
                    product.getPrice(),
                    order.getCount(),
                    product.getPrice() * order.getCount(),
                    order.getLocalDateTime()
            );
            orderDTOS.add(dto);
        }
        return orderDTOS;
    }

    public List<OrderDTO> showAllHistory() {
        List<Order> list = orderRepository.getList();
        List<OrderDTO> dtoList=new ArrayList<>();
        for (Order order : list) {
            User user = userRepository.getUserById(order.getConsumerId()).get();
            Product product = productRepository.getProductById(order.getProductId()).get();
            dtoList.add(new OrderDTO(user.getFullName(),product.getName(),product.getPrice(),order.getCount(),product.getPrice()*order.getCount(),order.getLocalDateTime()));
        }
        return  dtoList;
    }
}
