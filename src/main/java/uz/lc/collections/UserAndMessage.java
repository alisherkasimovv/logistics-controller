package uz.lc.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.lc.db.entities.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAndMessage {

    private User user;
    private String message;

}
