package com.syedsuhaibshah.sgparkingapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.syedsuhaibshah.sgparkingapi.services.ParkingLotService;

public class ParkingLotController {

    @Autowired
    private ParkingLotService service;

    @GetMapping("/load-parking-lots")
    public boolean loadData() {
        return service.addParkingLot();
    }

    @GetMapping("/update-lot-availabilty")
    public boolean updateLotInfo() {
        return service.updateLotInfo();
    }
}
