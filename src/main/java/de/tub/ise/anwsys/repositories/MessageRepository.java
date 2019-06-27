package de.tub.ise.anwsys.repositories;

import de.tub.ise.anwsys.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    @Query("SELECT m from Message AS m WHERE channelId = :channelId")
    List<Message> findMessagesByChannelId(@Param("channelId") long channelId);

    @Query("SELECT DISTINCT m.creator from Message AS m WHERE channelId = :channelId")
    List<String> findUniqueCreatorsByChannelId(@Param("channelId") long channelId);

}
