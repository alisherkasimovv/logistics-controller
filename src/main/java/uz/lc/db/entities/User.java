package uz.lc.db.entities;

import com.sun.istack.internal.Nullable;
import lombok.*;
import uz.lc.db.entities.base.UpdateBaseEntity;
import uz.lc.db.enums.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "db_users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends UpdateBaseEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Nullable
    @Column(name = "user_type")
    private Type type;

}
