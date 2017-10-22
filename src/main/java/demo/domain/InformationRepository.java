package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


//  repository is for CRUD (Create Read Update Delete) 增删改查
public interface InformationRepository extends JpaRepository<InfoAnalysis, Long>{
    //   Long is the the type of @Id (i.e. userId)
    //   JpaRepository includes some basic methods so no need to write them here, otherwise methods need to be written
    //   spring data implements methods (according to method names) and generate query, no need to implment by yourself and methods name must follow its pattern
    Page<InfoAnalysis> findByUserId(@Param("userId") Long userId, Pageable pageable);

    Page<InfoAnalysis> findByRunningId(@Param("runningId") String runningId, Pageable pageable);

    Page<InfoAnalysis> findAllByOrderByHeartRateDesc(Pageable pageable);
//    Page<InfoAnalysis> findAllByOrderByHeartRateDesc(int heartRate, Pageable pageable);

    void deleteByRunningId(@Param("runningId") String runningId);
}