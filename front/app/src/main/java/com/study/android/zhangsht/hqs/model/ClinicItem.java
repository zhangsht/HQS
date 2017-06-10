package com.study.android.zhangsht.hqs.model;

/**
 * Created by zhangsht on 2017/5/25.
 */

public class ClinicItem {
    private String clinicName;
    private String doctorName;

    public ClinicItem(String clinicName, String doctorName) {
        this.clinicName = clinicName;
        this.doctorName = doctorName;
    }

    public String display() {
        return this.clinicName + this.doctorName;
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
}
