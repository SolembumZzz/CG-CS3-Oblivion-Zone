package com.cg.model;

import java.math.BigDecimal;

public class Description {
    private int id;
    private String brand;
    private String mainboard;
    private String CPU;
    private String RAM;
    private String VGA;
    private String harddrive;
    private BigDecimal insurance;

    public Description() {}

    public Description(int id, String brand, String mainboard, String CPU, String RAM, String VGA, String harddrive, BigDecimal insurance) {
        this.id = id;
        this.brand = brand;
        this.mainboard = mainboard;
        this.CPU = CPU;
        this.RAM = RAM;
        this.VGA = VGA;
        this.harddrive = harddrive;
        this.insurance = insurance;
    }

    public Description(String brand, String mainboard, String CPU, String RAM, String VGA, String harddrive, BigDecimal insurance) {
        this.brand = brand;
        this.mainboard = mainboard;
        this.CPU = CPU;
        this.RAM = RAM;
        this.VGA = VGA;
        this.harddrive = harddrive;
        this.insurance = insurance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMainboard() {
        return mainboard;
    }

    public void setMainboard(String mainboard) {
        this.mainboard = mainboard;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getVGA() {
        return VGA;
    }

    public void setVGA(String VGA) {
        this.VGA = VGA;
    }

    public String getHarddrive() {
        return harddrive;
    }

    public void setHarddrive(String harddrive) {
        this.harddrive = harddrive;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }
}
