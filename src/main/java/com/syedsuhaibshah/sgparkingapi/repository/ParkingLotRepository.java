package com.syedsuhaibshah.sgparkingapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.syedsuhaibshah.sgparkingapi.models.ParkingLot;

import org.locationtech.jts.geom.Point;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {

    // Its simpler to do a native query for these kinds of db native geometry
    // oerations.
    @Query(value = "SELECT * FROM parking_lots pl where pl.available_lots > 0 ORDER BY pl.geom <-> :queryPoint limit :queryLimit offset :queryOffset ;", nativeQuery = true)
    List<ParkingLot> getNearbyParkingLots(Point queryPoint, int queryLimit, int queryOffset);
}
