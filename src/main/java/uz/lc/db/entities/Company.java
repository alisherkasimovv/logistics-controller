package uz.lc.db.entities;

import lombok.*;
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

    @Column(name = "address")
    private String address;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;
}
