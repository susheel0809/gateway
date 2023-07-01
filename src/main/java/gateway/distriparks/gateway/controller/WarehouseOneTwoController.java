package gateway.distriparks.gateway.controller;


import gateway.distriparks.gateway.repository.WarehouseOneTwoRepository;
import gateway.distriparks.gateway.constants.EnumFields;
import gateway.distriparks.gateway.model.WarehouseOneTwo;
import gateway.distriparks.gateway.util.CallingExternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin("*")
public class WarehouseOneTwoController {

    @Autowired
    private WarehouseOneTwoRepository warehouseOneTwoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

//    @GetMapping("/stuffed_containers")
//    private List<Map<String, Object>> getStuffedContainers() throws Exception {
//        //removing all data for live data operation
//        warehouseOneTwoRepository.deleteAll();
//
//        //inserting fresh data
//        CallingExternalApi.warehouseOneTwo();
//
//        List<WarehouseOneTwo> all = warehouseOneTwoRepository.findAll();
//        Set<String> unique_consolidator = new HashSet<>();
//        if (!all.isEmpty())
//            for (WarehouseOneTwo warehouseOneTwo : all) {
//                unique_consolidator.add(warehouseOneTwo.getConsolidator() != null ? warehouseOneTwo.getConsolidator() : "");
//            }
//
//        LinkedHashMap<String, Object> stringObjectHashMap = new LinkedHashMap<>();
//        int total = 0;
//        for (String company : unique_consolidator) {
//            List<WarehouseOneTwo> running = warehouseOneTwoRepository.findUniqueConsolidatorForStatus(company, EnumFields.Status.STUFFED.name());
//            stringObjectHashMap.put(company, running.size());
//            total = total + running.size();
//        }
//        stringObjectHashMap.put("total", total);
////        stringObjectHashMap.remove(EnumFields.Status.OTHERS.name());
////        stringObjectHashMap.remove("");
//
//        List<Map<String, Object>> objects = new ArrayList<>();
//        objects.add(stringObjectHashMap);
//
//        return objects;
//    }

    @GetMapping("/warehouse/one/two/dataset")
    private List<Map<Object, Object>> getRunningContainers() throws Exception {

//        //removing all data for live data operation
        warehouseOneTwoRepository.deleteAll();
//
//        //inserting fresh data
        mongoTemplate.insertAll(CallingExternalApi.warehouseOneTwo());


        List<WarehouseOneTwo> all = warehouseOneTwoRepository.findAll();
        Set<String> unique_consolidator = new HashSet<>();
        if (!all.isEmpty())
            for (WarehouseOneTwo warehouseOneTwo : all) {
                unique_consolidator.add(warehouseOneTwo.getConsolidator() != null ? warehouseOneTwo.getConsolidator() : "");
            }

        List<Map<Object, Object>> objects = new ArrayList<>();
        for (String company : unique_consolidator) {
            int total = 0;
            LinkedHashMap<Object, Object> stringObjectHashMap = new LinkedHashMap<>();
            if (!company.equals("")) {
                stringObjectHashMap.put("consolidator", company);
                for (EnumFields.Status value : EnumFields.Status.values()) {
                    List<WarehouseOneTwo> status = warehouseOneTwoRepository.findUniqueConsolidatorForStatus(company, value.name());
                    stringObjectHashMap.put(value.name().toLowerCase(), status.size() != 0 ? status.size() : 0);
                    total = total + status.size();
                }


                int size = warehouseOneTwoRepository.findUniqueConsolidator(company).size();
                stringObjectHashMap.put("cbm", size);
                stringObjectHashMap.put("tues", size);
                stringObjectHashMap.put("total", total + size + size);
                objects.add(stringObjectHashMap);
            }
        }


        return objects;
    }

//    @GetMapping("/warehouse/onetwo/consolidator")
//    private List<Map<Object, Object>> getConsolidatorStatus() throws Exception {
////        removing all data for live data operation
//        warehouseOneTwoRepository.deleteAll();
//
//        //inserting fresh data
////        List<WarehouseOneTwo> warehouseOneTwos = CallingExternalApi.warehouseOneTwo();
//
//        mongoTemplate.insertAll(CallingExternalApi.warehouseOneTwo());
//
//        List<WarehouseOneTwo> all = warehouseOneTwoRepository.findAll();
//
//        LinkedHashMap<Object, Object> stringObjectHashMap;
//        List<Map<Object, Object>> objects = new ArrayList<>();
//        Map<Object, List<Map<Object, Object>>> finalObject = new LinkedHashMap<>();
//
//        List<Map<Object, Object>> columnForRows = new ArrayList<>();
//        Map<Object, Object> forColumn = new LinkedHashMap<>();
//        forColumn.put("label", "Consolidator");
//        forColumn.put("field", "consolidator");
//        forColumn.put("sort", "asc");
//        forColumn.put("width", 150);
//        columnForRows.add(forColumn);
//
//        forColumn = new LinkedHashMap<>();
//        forColumn.put("label", "Plan Received");
//        forColumn.put("field", "plan_received_time");
//        forColumn.put("sort", "asc");
//        forColumn.put("width", 150);
//        columnForRows.add(forColumn);
//
//        forColumn = new LinkedHashMap<>();
//        forColumn.put("label", "Status");
//        forColumn.put("field", "Status");
//        forColumn.put("sort", "asc");
//        forColumn.put("width", 150);
//        columnForRows.add(forColumn);
//
//        forColumn = new LinkedHashMap<>();
//        forColumn.put("label", "CBM");
//        forColumn.put("field", "cbm");
//        forColumn.put("sort", "asc");
//        forColumn.put("width", 150);
//        columnForRows.add(forColumn);
//
//
//        forColumn = new LinkedHashMap<>();
//        forColumn.put("label", "Tue's");
//        forColumn.put("field", "tues");
//        forColumn.put("sort", "asc");
//        forColumn.put("width", 150);
//        columnForRows.add(forColumn);
//
//        finalObject.put("columns", columnForRows);
//
//
//        for (WarehouseOneTwo warehouseOneTwo : all) {
//            if (!warehouseOneTwo.getConsolidator().equals("")) {
//                stringObjectHashMap = new LinkedHashMap<>();
//                stringObjectHashMap.put("consolidator", warehouseOneTwo.getConsolidator());
//                stringObjectHashMap.put("plan_received_time", warehouseOneTwo.getPlan_received_time() != null ? warehouseOneTwo.getPlan_received_time() : "");
//                stringObjectHashMap.put("status", warehouseOneTwo.getStatus());
//                stringObjectHashMap.put("cbm", warehouseOneTwo.getCbm());
//                stringObjectHashMap.put("tues", warehouseOneTwo.getTues());
//                objects.add(stringObjectHashMap);
//            }
//        }
//
//        finalObject.put("rows", objects);
//
//        return objects;
//    }


    @GetMapping("/warehouse/one/two/running_consolidator")
    public List<List> dashboard() throws Exception {


        Set<String> unique_consolidator = new HashSet<>();
        Thread.sleep(2000);
        List<WarehouseOneTwo> all = warehouseOneTwoRepository.findAll();
        if (!all.isEmpty())
            for (WarehouseOneTwo warehouseOneTwo : all) {
                unique_consolidator.add(warehouseOneTwo.getConsolidator() != null ? warehouseOneTwo.getConsolidator() : "");
            }


        List<List> lists = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        list.add("title");
        list.add("dashboard");
        lists.add(list);
        for (String company : unique_consolidator) {
//            if (!company.equals("")) {
            List<WarehouseOneTwo> running = warehouseOneTwoRepository.findUniqueConsolidatorForStatus(company, EnumFields.Status.RUNNING.name());
            list = new ArrayList<>();
            list.add(company);
            list.add(running.size());
            lists.add(list);
//            }

        }

        return lists;
    }

}
