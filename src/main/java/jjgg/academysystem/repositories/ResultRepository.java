package jjgg.academysystem.repositories;

import jjgg.academysystem.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResultRepository extends JpaRepository<Result, Long> {
    @Query("SELECT COUNT(r) FROM Result r WHERE r.user.document = :userDocument AND r.test.idTest = :testId")
    Long countByUserAndTest(@Param("userDocument") Long userDocument, @Param("testId") Long testId);
}
