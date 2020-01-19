package com.nickolos.game.repository;


import com.nickolos.game.entities.Game;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@ConditionalOnProperty("game.micro.service")
public interface GameRepository  extends JpaRepository<Game, Long> {

   @Query(
           value = "SELECT id FROM game.game Where game_status=true;",
           nativeQuery = true)
    List<Long> findAllByGame_statusIsTrue();

}
