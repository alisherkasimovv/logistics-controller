package uz.lc.db.entities.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.lc.db.entities.base.UpdateBaseEntity;
import uz.lc.db.enums.DriverStatus;

import javax.persistence.*;

@Entity
@Table(name = "document_driver_statuses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverWorkload extends UpdateBaseEntity {

    @Column(name = "driver_id")
    private Integer driverId;

    @Column(name = "driver_status")
    @Enumerated(value = EnumType.STRING)
    private DriverStatus driverStatus = DriverStatus.FREE;

    @Nullable
    @Column(name = "current_tracking_id")
    private Integer currentTrackingId;

    @Nullable
    @Column(name = "track_number")
    private String trackNumber;

}
