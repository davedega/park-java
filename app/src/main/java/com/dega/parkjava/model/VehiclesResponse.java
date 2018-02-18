package com.dega.parkjava.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by davedega on 17/02/18.
 */

public class VehiclesResponse {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("vehicles")
    @Expose
    private List<Vehicle> vehicles = null;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("nextPage")
    @Expose
    private Integer nextPage;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
