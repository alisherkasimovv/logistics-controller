package uz.lc.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.lc.db.entities.Company;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyAndMessage {

    private Company company;
    private String message;

}
