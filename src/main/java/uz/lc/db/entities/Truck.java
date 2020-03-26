package uz.lc.db.entities;

import lombok.*;
import org.springframework.lang.Nullable;
import uz.lc.db.entities.base.UpdateBaseEntity;

import javax.persistence.*;

@Entity
@Table(name="db_trucks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Truck extends UpdateBaseEntity {

    @Nullable
    @Column(name = "model")
    private String model;

    @Nullable
    @Column(name = "plate_number")
    private String plateNumber;

    @Nullable
    @Column(name = "technical_certificate")
    private String technicalCertificate;

}
