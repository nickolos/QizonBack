package com.nickolos.result.repository;

import com.nickolos.result.entities.FullResult;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty("result.micro.service")
public interface FullResultRepo extends JpaRepository<FullResult, Long> {

    @Query(
            value = "SELECT * FROM result.fullresult WHERE id_game = ? And uid =?;",
            nativeQuery = true)
    FullResult findById_gameAndUid(Long id_game, String uid);

    @Query(
            value = "SELECT * FROM result.fullresult  WHERE id_game = ?  order by result.fullresult.score desc;",
            nativeQuery = true)
    List<FullResult> fullRes(Long id_game);
}
