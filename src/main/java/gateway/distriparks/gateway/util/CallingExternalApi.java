package gateway.distriparks.gateway.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gateway.distriparks.gateway.model.WarehouseOneTwo;
import gateway.distriparks.gateway.model.WarehouseThree;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONArray;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class CallingExternalApi {

    public static List<WarehouseOneTwo> warehouseOneTwo() throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("https://script.googleusercontent.com/macros/echo?user_content_key=Z7slB14zmdnRo6j9qOfbmFEjjIT15-xIojBjCdTsIC9yFmhM5FJ1YrUWO2xwvi_sNf5jppxTV4GVJRLsGJONxfyMeS4Yqudmm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnDboLRU3SS3zOwoWCaXWoQlwLNNdjDkN-JRiz3ubd6uV4UufUAR28WV1R0pD9KVkGstfw1UsmuOsmjhN17J3YwnkRwI5SNjLhw&lib=MW8rkVqNzYSQZl_gZuXusDUqO0XQ_ydfb");

        HttpResponse httpresponse = httpclient.execute(httpget);
        final InputStream content = httpresponse.getEntity().getContent();


        JSONObject jsonObject = new JSONObject(IOUtils.toString(content, StandardCharsets.UTF_8));

        JSONArray data = jsonObject.getJSONArray("data");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonArray = objectMapper.readTree(data.toString());

        List<WarehouseOneTwo> warehouseOneTwos = new ArrayList<>();

        for (JsonNode element : jsonArray) {
            WarehouseOneTwo obj = objectMapper.treeToValue(element, WarehouseOneTwo.class);
            warehouseOneTwos.add(obj);
        }

        return warehouseOneTwos;

    }

    public static List<WarehouseThree> warehouseThree() throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("https://script.googleusercontent.com/macros/echo?user_content_key=nA3icT_K2AohazlebAEMoWPuRw-ZHpf9mcegw5cSIoS6PR9UZsvFcuyMGT8DxXtWl9yJrtEBg5XxmfTy5ig3UNAOS67vT8SYm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnNwGh7h27yqwh9FJVKHgj1T32u4j5AC1GlNk0vKTZ-LyowDHzp7WHOjNQblCCmt5TMmHtQOFXea0bTg0U_vaoJ9MOO8LsVGurtz9Jw9Md8uu&lib=MzFsmOfzUWvArIz4A7cM7CTWkEKQozq2-");

        HttpResponse httpresponse = httpclient.execute(httpget);
        final InputStream content = httpresponse.getEntity().getContent();


        JSONObject jsonObject = new JSONObject(IOUtils.toString(content, StandardCharsets.UTF_8));

        JSONArray data = jsonObject.getJSONArray("data");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonArray = objectMapper.readTree(data.toString());

        List<WarehouseThree> warehouseThrees = new ArrayList<>();

        for (JsonNode element : jsonArray) {
            WarehouseThree obj = objectMapper.treeToValue(element, WarehouseThree.class);
            warehouseThrees.add(obj);
        }

        return warehouseThrees;

    }

}
