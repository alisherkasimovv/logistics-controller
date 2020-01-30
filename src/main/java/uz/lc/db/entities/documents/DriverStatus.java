package uz.lc.db.entities.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.lc.db.entities.base.UpdateBaseEntity;
import uz.lc.db.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "document_driver_statuses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverStatus extends UpdateBaseEntity {

    @Column(name = "driver_id")
    private Integer driverId;

    @Column(name = "driver_status")
    private Status driverStatus = Status.FREE;

    @Nullable
    @Column(name = "track_number")
    private String trackNumber;

}
