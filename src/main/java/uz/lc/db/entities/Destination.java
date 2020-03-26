package uz.lc.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.lc.db.entities.base.UpdateBaseEntity;
import uz.lc.db.enums.Region;

import javax.persistence.*;

@Entity
@Table(name = "db_company_destinations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Destination extends UpdateBaseEntity {

    @Nullable
    @Column(name = "company_id")
    private Integer companyId;

    @Nullable
    @Enumerated(value = EnumType.STRING)
    private Region region = Region.TAS;

    @Nullable
    @Column(name = "address")
    private String address;

    @Nullable
    @Column(name = "address_longitude")
    private String longitude;

    @Nullable
    @Column(name = "address_latitude")
    private String latitude;

}
