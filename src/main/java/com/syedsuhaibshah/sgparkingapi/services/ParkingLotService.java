package com.syedsuhaibshah.sgparkingapi.services;

import com.syedsuhaibshah.sgparkingapi.models.ParkingLot;
import com.syedsuhaibshah.sgparkingapi.repository.ParkingLotRepository;

import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.Point;

import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository repository;

    private GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 3414);

    public boolean updateLotInfo() {

        Table parkingLotAvailabilityTable;

        try {
            parkingLotAvailabilityTable = Table.read().csv("src/parkingData.csv");
            System.out.println(parkingLotAvailabilityTable.structure()); // Debugging: For the purpose of schema
                                                                         // checking.
            parkingLotAvailabilityTable.where(parkingLotAvailabilityTable.column("carpark_number").isNotMissing());
        } catch (Exception e) {
            System.out.println("Failed to read the Parking Lot csv file. " + e);
            return false;
        }

        try {
            for (Row row : parkingLotAvailabilityTable) {

                Optional<ParkingLot> parkingLotData = repository.findById(row.getString("carpark_number"));

                if (parkingLotData.isPresent()) {
                    ParkingLot _parkingLot = parkingLotData.get();
                    _parkingLot.setAvailableLots(row.getInt("lots_available"));
                    _parkingLot.setTotalLots(row.getInt("total_lots"));
                    repository.save(_parkingLot);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to insert Parking Lot data into DB. " + e);
            return false;
        }

        return true;

    }

    public boolean addParkingLot() {
        Table parkingLotTable;

        try {
            parkingLotTable = Table.read().csv("src/data.csv");
            System.out.println(parkingLotTable.structure()); // For the purpose of schema checking.
        } catch (Exception e) {
            System.out.println("Failed to read the Parking Lot csv file. " + e);
            return false;
        }

        try {
            for (Row row : parkingLotTable) {
                Point p = factory.createPoint(new Coordinate(row.getDouble("x_coord"), row.getDouble("y_coord")));
                ParkingLot newLot = new ParkingLot(row.getString("car_park_no"), row.getString("address"), p, 0, 0);
                repository.save(newLot);
            }
        } catch (Exception e) {
            System.out.println("Failed to insert Parking Lot data into DB. " + e);
            return false;
        }

        return true;
    }
}
