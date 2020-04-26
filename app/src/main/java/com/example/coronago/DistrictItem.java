package com.example.coronago;

public class DistrictItem {

    private String name;

    private String total;

    private String recoverd;

    private String death;

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setRecoverd(String recoverd) {
        this.recoverd = recoverd;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public DistrictItem(String name, String total, String recoverd, String death) {
        this.name = name;
        this.total = total;
        this.recoverd = recoverd;
        this.death = death;
    }

    public String getName() {
        return name;
    }

    public String getTotal() {
        return total;
    }

    public String getRecoverd() {
        return recoverd;
    }

    public String getDeath() {
        return death;
    }
}
