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

    // TODO Tracking number for now is not necessary thing.
    @Column(name = "track_number")
    private String trackNumber;

    @Column(name = "is_job_done")
    private Boolean jobDone = false;

    @Column(name = "is_job_totally_cancelled")
    private Boolean jobTotallyCancelled = false;

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

    // Cancelled tracking
    @Nullable
    @Column(name = "cancel_reason", columnDefinition = "TEXT")
    private String cancelReason;

    @Nullable
    @Column(name = "date_cancelled")
    private LocalDateTime dateCancelled;
    //

    // Evidence of done job
    @Nullable
    @Column(name = "invoice_image")
    private String invoiceImage;

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
