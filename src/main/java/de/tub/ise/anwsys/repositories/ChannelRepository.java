package de.tub.ise.anwsys.repositories;

import de.tub.ise.anwsys.model.Channel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ChannelRepository extends PagingAndSortingRepository<Channel, Long> {

    Channel findByName(@Param("name") String name);

}
