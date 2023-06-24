package gateway.distriparks.gateway;

import gateway.distriparks.gateway.model.WarehouseOneTwo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Component
public interface WarehouseOneTwoRepository extends MongoRepository<WarehouseOneTwo, String> {

    @Query(value = "{'consolidator' : ?0, 'status' : ?1}", fields = "{'_id' : 0,'consolidator' :0,'status' :0}")
    List<WarehouseOneTwo> findUniqueConsolidator(String consolidator, String status);


}
