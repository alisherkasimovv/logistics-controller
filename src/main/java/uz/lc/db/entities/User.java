package uz.lc.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.lc.db.entities.base.UpdateBaseEntity;
import uz.lc.db.enums.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "db_users")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends UpdateBaseEntity {
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    private Type type;


}
