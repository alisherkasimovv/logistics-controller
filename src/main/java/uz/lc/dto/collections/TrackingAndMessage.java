package uz.lc.dto.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lc.db.entities.documents.Tracking;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TrackingAndMessage {

    private String message;
    private Tracking tracking;

}
