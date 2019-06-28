package de.tub.ise.anwsys.repositories;

import de.tub.ise.anwsys.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    @Query("SELECT DISTINCT m.creator from Message AS m WHERE channelId = :channelId")
    List<String> findUniqueCreatorsByChannelId(@Param("channelId") long channelId);

    @Query("SELECT m from Message AS m WHERE channelId = :channelId ORDER BY m.timestamp DESC")
    Page<Message> findLast10Messages(@Param("channelId") long channelId, Pageable pageable);

    @Query("SELECT m from Message AS m WHERE channelId = :channelId AND timestamp >= :timestamp")
    Page<Message> findMessagesNewerThanTimestamp(@Param("timestamp") LocalDateTime timestamp, @Param("channelId") long channelId, Pageable pageable);

}
