package com.dega.parkjava.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by davedega on 17/02/18.
 */
public class Vehicle {

    @SerializedName("vehicleId")
    @Expose
    private Integer vehicleId;
    @SerializedName("vrn")
    @Expose
    private String vrn;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("default")
    @Expose
    private Boolean _default;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVrn() {
        return vrn;
    }

    public void setVrn(String vrn) {
        this.vrn = vrn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDefault() {
        return _default;
    }

    public void setDefault(Boolean _default) {
        this._default = _default;
    }
}
