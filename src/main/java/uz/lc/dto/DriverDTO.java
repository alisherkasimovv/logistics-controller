package uz.lc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lc.db.enums.DriverStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private DriverStatus currentStatus;
    private Integer currentTrackingId;

}
