package com.cb.users.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import com.vividsolutions.jts.io.WKTWriter;

/**
 * Created by oo on 17-7-15.
 */
public class GISUtil {
    /**
     * 经纬度转墨卡托
     */
    public static final Point lonLat2Mercator(Point lonLat) {
        double x = lonLat.getX() * 20037508.34 / 180;
        double y = Math.log(Math.tan((90 + lonLat.getY()) * Math.PI / 360)) / (Math.PI / 180);
        y = y * 20037508.34 / 180;
        Point mercator = new Point(x, y);
        return mercator;
    }

    /**
     * 墨卡托转经纬度
     *
     * @param mercator
     * @return
     */
    public static final Point Mercator2lonLat(Point mercator) {
        double x = mercator.getX() / 20037508.34 * 180;
        double y = mercator.getY() / 20037508.34 * 180;
        y = 180 / Math.PI * (2 * Math.atan(Math.exp(y * Math.PI / 180)) - Math.PI / 2);
        Point lonLat = new Point(x, y);
        return lonLat;
    }

    public static void main(String[] args) {
        Point point = GISUtil.lonLat2Mercator(new Point(116.397428, 39.90923));
        System.out.println(point);


        Coordinate coordinate = new Coordinate(116.397428, 39.90923);
        Coordinate[] coordinates = {coordinate};
        CoordinateArraySequence coordinateArraySequence = new CoordinateArraySequence(coordinates, 2);


        Coordinate coordinate2 = new Coordinate(116.397428, 39.90953);
        Coordinate[] coordinates2 = {coordinate2};
        CoordinateArraySequence coordinateArraySequence2 = new CoordinateArraySequence(coordinates2, 2);


        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

        com.vividsolutions.jts.geom.Point point1 = new com.vividsolutions.jts.geom.Point(coordinateArraySequence, geometryFactory);
        com.vividsolutions.jts.geom.Point point2 = new com.vividsolutions.jts.geom.Point(coordinateArraySequence2, geometryFactory);

        double degree = point1.distance(point2);
        System.out.println(degree);


        double meter = degree * (Math.PI / 180) * 6378137;
        System.out.println(meter);

        double degree2 = meter / ((Math.PI / 180) * 6378137);

        System.out.println(degree2);


        double degree100 = 100 / ((Math.PI / 180) * 6378137);

        Geometry buffer = point1.buffer(degree100);
        Coordinate[] coordinates1 = buffer.getCoordinates();
        for (int i = 0; i < coordinates1.length; i++) {
            Coordinate coord = coordinates1[i];
            System.out.println("[" + String.format("%.6f", coord.x) + "," + String.format("%.6f", coord.y) + "],");
        }
        String s = WKTWriter.toLineString(coordinates1);
        System.out.println(s);


//        double dis=0.09999999999999432;
//        double degree = 2*Math.PI*Math.cos(2*Math.PI/360*dis)*6378140/360;
//        double degree2 = (1/degree)*100;
//        System.out.println(degree2);


    }


}
