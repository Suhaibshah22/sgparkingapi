package com.syedsuhaibshah.sgparkingapi.models;

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.syedsuhaibshah.sgparkingapi.serializers.GeomSerializer;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "parking_lots")
public class ParkingLot {

    @Id
    @Column(name = "id")
    private String id; // same as Car Park No. in sample dataset

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @Column(name = "geom", columnDefinition = "geometry(point, 3414)")
    private Point geom;

    @JsonProperty("total_lots")
    @Column(name = "total_lots")
    private int totalLots;

    @JsonProperty("available_lots")
    @Column(name = "available_lots")
    private int availableLots;

    public ParkingLot() {

    }

    public ParkingLot(String id, String address, Point geom, int totalLots, int availableLots) {
        this.id = id;
        this.address = address;
        this.geom = geom;
        this.totalLots = totalLots;
        this.availableLots = availableLots;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Point getGeom() {
        return geom;
    }

    public void setGeom(Point geom) {
        this.geom = geom;
    }

    public int getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    public int getAvailableLots() {
        return availableLots;
    }

    public void setAvailableLots(int availableLots) {
        this.availableLots = availableLots;
    }

    @JsonSerialize(using = GeomSerializer.class)
    public Point getLocation() {
        return geom;
    }

    @Override
    public String toString() {
        return "ParkingLot [id=" + id + ", address=" + address + ", geom=" + geom + ", totalLots=" + totalLots
                + ", availableLots=" + availableLots + "]";
    }
}
