package com.nickolos.game.repository;


import com.nickolos.game.entities.Player;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty("game.micro.service")
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByUid(String uid);

}
