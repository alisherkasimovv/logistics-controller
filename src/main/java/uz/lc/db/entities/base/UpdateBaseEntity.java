package uz.lc.db.entities.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class UpdateBaseEntity extends BaseEntity {

    @Column(name = "is_deleted")
    private Boolean deleted = false;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Objects.nonNull(this.createdAt) ? this.createdAt : new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Objects.nonNull(this.updatedAt) ? this.updatedAt : new Date();
    }

}
