package gateway.distriparks.gateway.repository;

import gateway.distriparks.gateway.model.WarehouseOneTwo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface WarehouseOneTwoRepository extends MongoRepository<WarehouseOneTwo, String> {

    @Query(value = "{'consolidator' : ?0, 'status' : ?1}", fields = "{'_id' : 0,'consolidator' :0,'status' :0}")
    List<WarehouseOneTwo> findUniqueConsolidatorForStatus(String consolidator, String status);

    @Query(value = "{'consolidator' : ?0}", fields = "{'_id' : 0,'consolidator' :0}")
    List<WarehouseOneTwo> findUniqueConsolidator(String consolidator);

//    @Query(value = "{'date' : ?0}")
//    List<WarehouseOneTwo> findByDate(String date);


}
