package com.example.coronago;

public class StateItem {

    private String name;

    private String total;

    private String recoverd;

    private String death;

    public StateItem(String name, String total, String recoverd, String death) {
        this.name = name;
        this.total = total;
        this.recoverd = recoverd;
        this.death = death;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRecoverd() {
        return recoverd;
    }

    public void setRecoverd(String recoverd) {
        this.recoverd = recoverd;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }
}
