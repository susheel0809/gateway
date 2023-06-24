package gateway.distriparks.gateway.controller;


import gateway.distriparks.gateway.WarehouseOneTwoRepository;
import gateway.distriparks.gateway.constants.EnumFields;
import gateway.distriparks.gateway.model.WarehouseOneTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class WarehouseOneTwoController {

    @Autowired
    private WarehouseOneTwoRepository warehouseOneTwoRepository;

    private MongoTemplate mongoTemplate;

    @GetMapping("/stuffed_containers")
    private List<Map<String, Object>> getStuffedContainers() {

        List<WarehouseOneTwo> all = warehouseOneTwoRepository.findAll();
        Set<String> unique_consolidator = new HashSet<>();
        if (!all.isEmpty())
            for (WarehouseOneTwo warehouseOneTwo : all) {
                unique_consolidator.add(warehouseOneTwo.getConsolidator() != null ? warehouseOneTwo.getConsolidator() : EnumFields.Status.OTHERS.name());
            }

        LinkedHashMap<String, Object> stringObjectHashMap = new LinkedHashMap<>();
        int total = 0;
        for (String company : unique_consolidator) {
            List<WarehouseOneTwo> running = warehouseOneTwoRepository.findUniqueConsolidator(company, EnumFields.Status.STUFFED.name());
            stringObjectHashMap.put(company, running.size());
            total = total + running.size();
        }
        stringObjectHashMap.put("total", total);
        stringObjectHashMap.remove(EnumFields.Status.OTHERS.name());
        stringObjectHashMap.remove("");

        List<Map<String, Object>> objects = new ArrayList<>();
        objects.add(stringObjectHashMap);

        return objects;
    }

    @GetMapping("/running_containers")
    private List<Map<String, Object>> getRunningContainers() {

        List<WarehouseOneTwo> all = warehouseOneTwoRepository.findAll();
        Set<String> unique_consolidator = new HashSet<>();
        if (!all.isEmpty())
            for (WarehouseOneTwo warehouseOneTwo : all) {
                unique_consolidator.add(warehouseOneTwo.getConsolidator() != null ? warehouseOneTwo.getConsolidator() : EnumFields.Status.OTHERS.name());
            }

        LinkedHashMap<String, Object> stringObjectHashMap = new LinkedHashMap<>();
        int total = 0;
        for (String company : unique_consolidator) {
            List<WarehouseOneTwo> running = warehouseOneTwoRepository.findUniqueConsolidator(company, EnumFields.Status.RUNNING.name());
            stringObjectHashMap.put(company, running.size());
            total = total + running.size();
        }
        stringObjectHashMap.put("TOTAL", total);
        stringObjectHashMap.remove(EnumFields.Status.OTHERS.name());
        stringObjectHashMap.remove("");
        List<Map<String, Object>> objects = new ArrayList<>();
        objects.add(stringObjectHashMap);

        return objects;
    }


}
