package jjto.com.workportal.mvp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by jhon on 21/02/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Users implements Serializable{
    private String activity;
    private String activityId;
    private String approved;
    private String beginDate;
    private String employee;
    private String endDate;
    private String lastVacationOn;
    private String process;
    private String processId;
    private String requestDate;

    public Users(){}

    public Users(String activity, String activityId, String approved, String beginDate, String employee, String endDate,
                 String lastVacationOn, String process, String processId, String requestDate) {
        this.activity = activity;
        this.activityId = activityId;
        this.approved = approved;
        this.beginDate = beginDate;
        this.employee = employee;
        this.endDate = endDate;
        this.lastVacationOn = lastVacationOn;
        this.process = process;
        this.processId = processId;
        this.requestDate = requestDate;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLastVacationOn() {
        return lastVacationOn;
    }

    public void setLastVacationOn(String lastVacationOn) {
        this.lastVacationOn = lastVacationOn;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
}
