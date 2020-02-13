package uz.lc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lc.db.entities.Company;
import uz.lc.db.entities.Destination;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyWithDestinations {

    private Company company;
    private List<Destination> destinations;

}
