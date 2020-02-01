package uz.lc.db.entities.documents;

import lombok.*;
import org.springframework.lang.Nullable;
import uz.lc.db.entities.base.UpdateBaseEntity;
import uz.lc.db.enums.Region;
import uz.lc.db.enums.TrackStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_trackings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tracking extends UpdateBaseEntity {

    @Column(name = "track_number")
    private String trackNumber;

    @Column(name = "is_job_done")
    private Boolean jobDone = false;

    @Nullable
    @Column(name = "driver_id")
    private Integer driverId;

    @Nullable
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "tracking_status")
    @Enumerated(value = EnumType.STRING)
    private TrackStatus status = TrackStatus.PENDING;

    @Column(name = "agent_id")
    private Integer agentId;

    // FROM
    @Nullable
    @Column(name = "latitude_from")
    private String latitudeFrom;

    @Nullable
    @Column(name = "longitude_from")
    private String longitudeFrom;

    @Nullable
    @Column(name = "address_from")
    private String addressFrom;

    // TO
    @Nullable
    @Column(name = "latitude_to")
    private String latitudeTo;

    @Nullable
    @Column(name = "longitude_to")
    private String longitudeTo;

    @Column(name = "address_to")
    private String addressTo;

    @Column(name = "region")
    private Region region = Region.TAS;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Delayed tracking fields
    @Nullable
    @Column(name = "tracking_status_before_delay")
    private TrackStatus statusBeforeDelay;

    @Nullable
    @Column(name = "delay_reason", columnDefinition = "TEXT")
    private String delayReason;

    @Nullable
    @Column(name = "date_delayed")
    private LocalDateTime dateDelayed;

    @Nullable
    @Column(name = "date_continued")
    private LocalDateTime dateContinued;
    //

    @Nullable
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Nullable
    @Column(name = "date_accepted")
    private LocalDateTime dateAccepted;

    @Nullable
    @Column(name = "date_loaded")
    private LocalDateTime dateLoaded;

    @Nullable
    @Column(name = "date_going")
    private LocalDateTime dateGoing;

    @Nullable
    @Column(name = "date_delivered")
    private LocalDateTime dateDelivered;

    @Nullable
    @Column(name = "date_shipped")
    private LocalDateTime dateShipped;

    @Nullable
    @Column(name = "date_done")
    private LocalDateTime dateDone;

}
