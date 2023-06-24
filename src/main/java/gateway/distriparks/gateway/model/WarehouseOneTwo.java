package gateway.distriparks.gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@JsonIgnoreProperties
public class WarehouseOneTwo {

    @Id
    private String id;
    private String sr_no;
    private String date;
    private String container_no;
    private String size;
    private String tues;
    private String shipping_line;
    private String consolidator;
    private String consignee;
    private String cbm;
    private String no_of_pkg;
    private String gate_no;
    private String labour;
    private String plan_received_time;
    private String start_date_time;
    private String hold_date_time;
    private String restart_date_time;
    private String end_time;
    private String tat;
    private String status;
    private String remarks;

    public String getSr_no() {
        return sr_no;
    }

    public void setSr_no(String sr_no) {
        this.sr_no = sr_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContainer_no() {
        return container_no;
    }

    public void setContainer_no(String container_no) {
        this.container_no = container_no;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTues() {
        return tues;
    }

    public void setTues(String tues) {
        this.tues = tues;
    }

    public String getShipping_line() {
        return shipping_line;
    }

    public void setShipping_line(String shipping_line) {
        this.shipping_line = shipping_line;
    }

    public String getConsolidator() {
        return consolidator;
    }

    public void setConsolidator(String consolidator) {
        this.consolidator = consolidator;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getCbm() {
        return cbm;
    }

    public void setCbm(String cbm) {
        this.cbm = cbm;
    }

    public String getNo_of_pkg() {
        return no_of_pkg;
    }

    public void setNo_of_pkg(String no_of_pkg) {
        this.no_of_pkg = no_of_pkg;
    }

    public String getGate_no() {
        return gate_no;
    }

    public void setGate_no(String gate_no) {
        this.gate_no = gate_no;
    }

    public String getLabour() {
        return labour;
    }

    public void setLabour(String labour) {
        this.labour = labour;
    }

    public String getPlan_received_time() {
        return plan_received_time;
    }

    public void setPlan_received_time(String plan_received_time) {
        this.plan_received_time = plan_received_time;
    }

    public String getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(String start_date_time) {
        this.start_date_time = start_date_time;
    }

    public String getHold_date_time() {
        return hold_date_time;
    }

    public void setHold_date_time(String hold_date_time) {
        this.hold_date_time = hold_date_time;
    }

    public String getRestart_date_time() {
        return restart_date_time;
    }

    public void setRestart_date_time(String restart_date_time) {
        this.restart_date_time = restart_date_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTat() {
        return tat;
    }

    public void setTat(String tat) {
        this.tat = tat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}


