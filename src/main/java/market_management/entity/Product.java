package market_management.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"id"})
public class Product  implements Serializable {
    private  String id;
    private  String name;
    private  double price;
    private  int quantity;

}
