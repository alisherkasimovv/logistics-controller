package uz.lc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TruckDTO {

    private Integer truckId;
    private String truckModel;
    private String truckPlateNumber;

    private Integer trailerId;
    private String trailerModel;
    private String trailerPlateNumber;
    private String trailerCapacity;

}
