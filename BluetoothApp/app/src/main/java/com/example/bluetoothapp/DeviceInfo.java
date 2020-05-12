package com.example.bluetoothapp;

public class DeviceInfo {

    private String name;
    private String macAddress;

    public DeviceInfo(String name, String macAddress){
        this.macAddress = macAddress;
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getName() {
        return name;
    }
}
