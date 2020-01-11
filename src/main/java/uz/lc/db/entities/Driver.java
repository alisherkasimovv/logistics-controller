package uz.lc.db.entities;

import lombok.*;
import org.springframework.lang.Nullable;
import uz.lc.db.entities.base.UpdateBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "db_drivers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver extends UpdateBaseEntity {

    @Column(name = "name")
    private String name;

    @Nullable
    @Column(name = "first_name")
    private String firstName;

    @Nullable
    @Column(name = "last_name")
    private String lastName;

    @Nullable
    @Column(name = "truck_id")
    private Integer truckId;


}
