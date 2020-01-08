package uz.lc.db.entities;

import lombok.Data;
import uz.lc.db.entities.base.UpdateBaseEntity;

import javax.persistence.*;

@Entity
@Table(name="db_trucks")
@Data
public class Truck extends UpdateBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "Name")
    String name;

    @Column(name = "Capacity")
    String capacity;

}
