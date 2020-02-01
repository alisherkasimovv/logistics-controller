package uz.lc.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lc.db.entities.Truck;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TruckAndMessage {

    private Truck truck;
    private String message;

}
