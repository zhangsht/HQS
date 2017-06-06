package com.study.android.zhangsht.hqs.model;

/**
 * Created by zhangsht on 2017/5/25.
 */

public class ClinicItem {
    private String clinicName;
    private String doctorName;
    private String inTreatName;
    private String waitTreatNames;
    private String afterTreatNames;

    public ClinicItem(String clinicName, String doctorName, String inTreatName, String waitTreatName, String afterTreatNames) {
        this.clinicName = clinicName;
        this.doctorName = doctorName;
        this.inTreatName = inTreatName;
        this.waitTreatNames = waitTreatName;
        this.afterTreatNames = afterTreatNames;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getInTreatName() {
        return inTreatName;
    }

    public void setInTreatName(String inTreatName) {
        this.inTreatName = inTreatName;
    }

    public String getWaitTreatNames() {
        return waitTreatNames;
    }

    public void setWaitTreatNames(String waitTreatName) {
        this.waitTreatNames = waitTreatName;
    }

    public String getAfterTreatNames() {
        return afterTreatNames;
    }

    public void setAfterTreatNames(String afterTreatNames) {
        this.afterTreatNames = afterTreatNames;
    }
}
