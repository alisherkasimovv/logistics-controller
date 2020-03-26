package uz.lc.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lc.db.entities.documents.Tracking;

import java.util.List;

public interface TrackingRepository extends JpaRepository<Tracking, Integer> {

    List<Tracking> findAllByDeletedFalseOrderByDateCreatedAsc();
    List<Tracking> findAllByJobDoneFalseAndDeletedFalseOrderByDateCreatedAsc();
    List<Tracking> findAllByJobDoneTrueAndDeletedFalseOrderByDateCreatedAsc();
    List<Tracking> findAllByCompanyIdAndJobDoneFalseAndDeletedFalseOrderByDateCreatedAsc(int id);
    Tracking findByTrackNumber(String trackNumber);
    Tracking findById(int id);

}
