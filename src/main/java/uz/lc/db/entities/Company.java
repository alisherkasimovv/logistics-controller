package uz.lc.db.entities;

import lombok.*;
import org.springframework.lang.Nullable;
import uz.lc.db.entities.base.UpdateBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "db_companies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company extends UpdateBaseEntity {

    @Column(name = "name")
    private String name;

    @Nullable
    @Column(name = "address")
    private String address;

    // MFO
    @Nullable
    @Column(name = "interbranch_turnover")
    private String interbranchTurnover;

    // INN
    @Nullable
    @Column(name = "individual_tax_number")
    private String individualTaxNumber;

    @Nullable
    @Column(name = "contract_no")
    private String contractNo;

    @Nullable
    @Column(name = "rate_per_trip")
    private Double rate;

    @Nullable
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

}
