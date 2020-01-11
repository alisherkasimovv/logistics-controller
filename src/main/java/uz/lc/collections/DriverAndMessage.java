package uz.lc.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lc.db.entities.Driver;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverAndMessage {

    private Driver driver;
    private String message;

}
