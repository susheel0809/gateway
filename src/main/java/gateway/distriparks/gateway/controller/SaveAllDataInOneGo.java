package gateway.distriparks.gateway.controller;

import gateway.distriparks.gateway.WarehouseOneTwoRepository;
import gateway.distriparks.gateway.model.WarehouseOneTwo;
import gateway.distriparks.gateway.util.CallingExternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaveAllDataInOneGo {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private WarehouseOneTwoRepository warehouseOneTwoRepository;

    @GetMapping("/test")
    private List<WarehouseOneTwo> getData() throws Exception {

        return warehouseOneTwoRepository.saveAll(CallingExternalApi.dataFromGoogleSheet());
    }

//    @GetMapping("")
}
