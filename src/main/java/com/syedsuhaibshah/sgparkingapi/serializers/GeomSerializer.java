package com.syedsuhaibshah.sgparkingapi.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Point;

import net.qxcg.svy21.*;

import java.io.IOException;

public class GeomSerializer extends JsonSerializer<Point> {

    @Override
    public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (point == null) {
            jsonGenerator.writeNull();
        } else {

            SVY21Coordinate svy21Coordinate = new SVY21Coordinate(point.getY(), point.getX());

            // Convert SVY21 coordinates to latitude and longitude (WGS84)
            LatLonCoordinate convertedCoordinate = svy21Coordinate.asLatLon();

            double latitude = convertedCoordinate.getLatitude();
            double longitude = convertedCoordinate.getLongitude();

            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("latitude");
            jsonGenerator.writeNumber(latitude);
            jsonGenerator.writeFieldName("longitude");
            jsonGenerator.writeNumber(longitude);
            jsonGenerator.writeEndObject();
        }
    }
}