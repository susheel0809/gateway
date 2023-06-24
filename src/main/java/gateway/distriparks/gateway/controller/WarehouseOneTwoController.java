package gateway.distriparks.gateway.controller;


import gateway.distriparks.gateway.WarehouseOneTwoRepository;
import gateway.distriparks.gateway.constants.EnumFields;
import gateway.distriparks.gateway.model.WarehouseOneTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin("*")
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

    @GetMapping("/consolidator")
    private List<Map<Object, Object>> getConsolidatorStatus() {

        List<WarehouseOneTwo> all = warehouseOneTwoRepository.findAll();

        LinkedHashMap<Object, Object> stringObjectHashMap;
        List<Map<Object, Object>> objects = new ArrayList<>();
        Map<Object, List<Map<Object, Object>>> finalObject = new LinkedHashMap<>();

        List<Map<Object, Object>> columnForRows = new ArrayList<>();
        Map<Object, Object> forColumn = new LinkedHashMap<>();
        forColumn.put("label", "Consolidator");
        forColumn.put("field", "consolidator");
        forColumn.put("sort", "asc");
        forColumn.put("width", 150);
        columnForRows.add(forColumn);

        forColumn = new LinkedHashMap<>();
        forColumn.put("label", "Plan Received");
        forColumn.put("field", "plan_received_time");
        forColumn.put("sort", "asc");
        forColumn.put("width", 150);
        columnForRows.add(forColumn);

        forColumn = new LinkedHashMap<>();
        forColumn.put("label", "Status");
        forColumn.put("field", "Status");
        forColumn.put("sort", "asc");
        forColumn.put("width", 150);
        columnForRows.add(forColumn);

        forColumn = new LinkedHashMap<>();
        forColumn.put("label", "CBM");
        forColumn.put("field", "cbm");
        forColumn.put("sort", "asc");
        forColumn.put("width", 150);
        columnForRows.add(forColumn);


        forColumn = new LinkedHashMap<>();
        forColumn.put("label", "Tue's");
        forColumn.put("field", "tues");
        forColumn.put("sort", "asc");
        forColumn.put("width", 150);
        columnForRows.add(forColumn);

        finalObject.put("columns",columnForRows);


        for (WarehouseOneTwo warehouseOneTwo : all) {
            if (!warehouseOneTwo.getConsolidator().equals("")) {
                stringObjectHashMap = new LinkedHashMap<>();
                stringObjectHashMap.put("consolidator", warehouseOneTwo.getConsolidator());
                stringObjectHashMap.put("plan_received_time", warehouseOneTwo.getPlan_received_time() != null ? warehouseOneTwo.getPlan_received_time() : "");
                stringObjectHashMap.put("status", warehouseOneTwo.getStatus());
                stringObjectHashMap.put("cbm", warehouseOneTwo.getCbm());
                stringObjectHashMap.put("tues", warehouseOneTwo.getTues());
                objects.add(stringObjectHashMap);
            }
        }

        finalObject.put("rows",objects);

        return objects;
    }

}
