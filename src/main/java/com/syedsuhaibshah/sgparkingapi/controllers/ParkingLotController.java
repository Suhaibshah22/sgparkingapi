package com.syedsuhaibshah.sgparkingapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syedsuhaibshah.sgparkingapi.models.ParkingLot;
import com.syedsuhaibshah.sgparkingapi.services.ParkingLotService;

@RestController
@RequestMapping("carparks")
public class ParkingLotController {

    @Autowired
    private ParkingLotService service;

    @GetMapping("/nearest")
    public ResponseEntity<List<ParkingLot>> getNearbyParkingLots(
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "longitude") double longitude,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "pageSize") int pageSize) {
        try {
            List<ParkingLot> parkingLotsNearby = new ArrayList<ParkingLot>();
            parkingLotsNearby = service.getnearbyParkingLots(longitude, latitude, page, pageSize);
            return new ResponseEntity<>(parkingLotsNearby, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/load-parking-lots")
    public boolean loadData() {
        return service.addParkingLot();
    }

    @GetMapping("/update-lot-availabilty")
    public boolean updateLotInfo() {
        return service.updateLotInfo();
    }
}
