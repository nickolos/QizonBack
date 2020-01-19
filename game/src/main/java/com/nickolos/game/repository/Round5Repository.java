package com.nickolos.game.repository;


import com.nickolos.game.entities.Round5;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@ConditionalOnProperty("game.micro.service")
public interface Round5Repository extends JpaRepository<Round5, Integer> {

    @Query(
            value = "SELECT min(id) FROM round5.round5;",
            nativeQuery = true)
    int getFirstById();

}
