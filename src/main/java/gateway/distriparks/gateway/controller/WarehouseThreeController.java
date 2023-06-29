package gateway.distriparks.gateway.controller;

import gateway.distriparks.gateway.model.WarehouseThree;
import gateway.distriparks.gateway.repository.WarehouseThreeRepository;
import gateway.distriparks.gateway.util.CallingExternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class WarehouseThreeController {

    @Autowired
    private WarehouseThreeRepository warehouseThreeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/warehouse/three/consolidator")
    private List<Map<Object, Object>> getConsolidatorStatus() throws Exception{
        //removing all data for live data operation
        warehouseThreeRepository.deleteAll();

        //inserting fresh data
        mongoTemplate.insertAll(CallingExternalApi.warehouseThree());

        List<WarehouseThree> all = warehouseThreeRepository.findAll();

        LinkedHashMap<Object, Object> stringObjectHashMap;
        List<Map<Object, Object>> objects = new ArrayList<>();

        for (WarehouseThree warehouseThree : all) {
            if (!warehouseThree.getConsolidator().equals("")) {
                stringObjectHashMap = new LinkedHashMap<>();
                stringObjectHashMap.put("consolidator", warehouseThree.getConsolidator());
                stringObjectHashMap.put("plan_received_time", warehouseThree.getPlan_received_time() != null ? warehouseThree.getPlan_received_time() : "");
                stringObjectHashMap.put("status", warehouseThree.getStatus());
                stringObjectHashMap.put("cbm", warehouseThree.getCbm());
                stringObjectHashMap.put("tues", warehouseThree.getTues());
                objects.add(stringObjectHashMap);
            }
        }

        return objects;
    }
}
