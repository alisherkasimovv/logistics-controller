package uz.lc.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.lc.db.entities.base.UpdateBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "db_drivers")
@Data
public class Driver extends UpdateBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name="Name")
    String name;


    @Column(name = "Trunck")
    Integer truck;


}
