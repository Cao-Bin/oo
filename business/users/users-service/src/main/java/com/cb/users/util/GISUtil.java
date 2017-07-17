package com.cb.users.util;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

/**
 * Created by oo on 17-7-15.
 */
public class GISUtil {
    public static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

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

    /**
     * create a Circle  创建一个圆，圆心(x,y) 半径RADIUS
     *
     * @param x
     * @param y
     * @param RADIUS
     * @return
     */
    public static Polygon createCircle(double x, double y, final double RADIUS) {

        final int SIDES = 32;//圆上面的点个数
        Coordinate coords[] = new Coordinate[SIDES + 1];
        for (int i = 0; i < SIDES; i++) {
            double angle = ((double) i / (double) SIDES) * Math.PI * 2.0;
            double dx = Math.cos(angle) * RADIUS;
            double dy = Math.sin(angle) * RADIUS;
            coords[i] = new Coordinate((double) x + dx, (double) y + dy);
        }
        coords[SIDES] = coords[0];
        LinearRing ring = GISUtil.geometryFactory.createLinearRing(coords);
        Polygon polygon = GISUtil.geometryFactory.createPolygon(ring, null);
        return polygon;
    }

    public static void main(String[] args) {
        Point point = GISUtil.lonLat2Mercator(new Point(116.403322, 39.920255));
        System.out.println(point);

        Coordinate coordinate = new Coordinate(116.403322, 39.920255);
        Coordinate[] coordinates = {coordinate};
        CoordinateArraySequence coordinateArraySequence = new CoordinateArraySequence(coordinates, 2);

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

        com.vividsolutions.jts.geom.Point point1 = new com.vividsolutions.jts.geom.Point(coordinateArraySequence, geometryFactory);

        double meter = 0.05 * (Math.PI / 180) * 6378137;
        double degree100 = 1000 / ((Math.PI / 180) * 6378137);

        System.out.println("[" + String.format("%.6f", point1.getX() - degree100) + "," + String.format("%.6f", point1.getY() + degree100) + "],");
        System.out.println("[" + String.format("%.6f", point1.getX() + degree100) + "," + String.format("%.6f", point1.getY() + degree100) + "],");
        System.out.println("[" + String.format("%.6f", point1.getX() + degree100) + "," + String.format("%.6f", point1.getY() - degree100) + "],");
        System.out.println("[" + String.format("%.6f", point1.getX() - degree100) + "," + String.format("%.6f", point1.getY() - degree100) + "],");
        System.out.println("[" + String.format("%.6f", point1.getX() - degree100) + "," + String.format("%.6f", point1.getY() + degree100) + "],");

        Geometry buffer = point1.buffer(degree100);




    }


}
