package uz.lc.configs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lc.db.enums.Region;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TrackNumberCreator {

    // FH20200101T1R0001TAS
    private String driver;
    private int truckId;
    private LocalDateTime dateTime;
    private Region region;
    private int reference;

    /**
     * Returns generated track number which consist of:
     *
     * AA   - driver's initials
     * 2020 - year
     * 01   - month
     * 01   - day
     * T1   - truck ID
     * R0000- reference number, generated automatically
     * TAS  - destination region (if not set, will be used TAS (Tashkent))
     *
     * @return AA20200101T1R0000TAS
     */
    public String generateTrackNumber() {
        return driver
                + dateTime.getYear()
                + dateTime.getMonth()
                + dateTime.getDayOfMonth()
                + "R"
                + referenceGenerator()
                + "T"
                + truckId
                + region;
    }

    private String referenceGenerator() {
        String ref = "";
        if (reference < 10) {
            ref =  "000" + reference;
        } else if (reference < 100) {
            ref = "00" + reference;
        } else if (reference < 1000) {
            ref = "0" + reference;
        } else if (reference == 1000) {
            ref = "" + 1000;
            reference = 0;
        }

        return ref;
    }

}
