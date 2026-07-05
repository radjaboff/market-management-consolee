package market_management.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order implements Serializable {
    private  String  id;
    private  String consumerId;
    private  String  productId;
    private  int count;
    private LocalDateTime localDateTime;
}
