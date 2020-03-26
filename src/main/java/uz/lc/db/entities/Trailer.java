package uz.lc.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.lc.db.entities.base.UpdateBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "db_trailers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trailer extends UpdateBaseEntity {

    @Nullable
    @Column(name = "truck_id")
    private Integer truckId;

    @Nullable
    @Column(name = "model")
    private String model;

    @Nullable
    @Column(name = "plate_number")
    private String plateNumber;

    @Nullable
    @Column(name = "technical_certificate")
    private String technicalCertificate;

    @Nullable
    @Column(name = "capacity")
    private Double capacity;

}
