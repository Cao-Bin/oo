package com.cb.users.util;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import com.vividsolutions.jts.io.WKTWriter;
import org.osgeo.proj4j.*;

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

        System.out.println("~~~~~~~~~~投影转换测试~~~~~~~~~~~~~~~~~~~~");

        CoordinateTransformTester tester = new CoordinateTransformTester(true);
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CRSFactory crsFactory = new CRSFactory();

        String WGS84_PARAM = "+title=long/lat:WGS84 +proj=longlat +datum=WGS84 +units=degrees";
        CoordinateReferenceSystem WGS84 = crsFactory.createFromParameters("WGS84", WGS84_PARAM);
        CoordinateReferenceSystem srcCRS = WGS84;
        CoordinateReferenceSystem tgtCRS = crsFactory.createFromName("EPSG:3857");


        CoordinateTransform trans = ctFactory.createTransform(srcCRS, tgtCRS);
        ProjCoordinate p = new ProjCoordinate();
        p.x = 116.403322;
        p.y = 39.920255;
        ProjCoordinate pout = new ProjCoordinate();
        trans.transform(p, pout);
        System.out.println(pout);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        Coordinate coordinate2 = new Coordinate(116.403322, 39.920255);
        Coordinate[] coordinates2 = {coordinate2};
        CoordinateArraySequence coordinateArraySequence2 = new CoordinateArraySequence(coordinates2, 2);


        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
//        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 3785);

        com.vividsolutions.jts.geom.Point point1 = new com.vividsolutions.jts.geom.Point(coordinateArraySequence, geometryFactory);
        com.vividsolutions.jts.geom.Point point2 = new com.vividsolutions.jts.geom.Point(coordinateArraySequence2, geometryFactory);

        double degree = point1.distance(point2);
        System.out.println(degree);


        //“距离”转换位米
        double meter = degree * (Math.PI / 180) * 6378137;
        System.out.println(meter);

        double degree2 = meter / ((Math.PI / 180) * 6378137);

        System.out.println(degree2);


        //米转换位“距离”
        double degree100 = 1000 / ((Math.PI / 180) * 6378137);
        System.out.println("~~~~~~~~~~~~~~矩形测试~~~~~~~~~~~~~~~~");

        System.out.println("[" + String.format("%.6f", point1.getX() - degree100) + "," + String.format("%.6f", point1.getY() + degree100) + "],");
        System.out.println("[" + String.format("%.6f", point1.getX() + degree100) + "," + String.format("%.6f", point1.getY() + degree100) + "],");
        System.out.println("[" + String.format("%.6f", point1.getX() + degree100) + "," + String.format("%.6f", point1.getY() - degree100) + "],");
        System.out.println("[" + String.format("%.6f", point1.getX() - degree100) + "," + String.format("%.6f", point1.getY() - degree100) + "],");
        System.out.println("[" + String.format("%.6f", point1.getX() - degree100) + "," + String.format("%.6f", point1.getY() + degree100) + "],");

        System.out.println("~~~~~~~~~~~~~~矩形测试结束~~~~~~~~~~~~~~~~");

        Geometry buffer = point1.buffer(degree100).getEnvelope();
        Coordinate[] coordinates1 = buffer.getCoordinates();
        for (int i = 0; i < coordinates1.length; i++) {
            Coordinate coord = coordinates1[i];
            System.out.println("[" + String.format("%.6f", coord.x) + "," + String.format("%.6f", coord.y) + "],");
        }
        String s = WKTWriter.toLineString(coordinates1);
        System.out.println(s);

        System.out.println("~~~~~~~~~~~~~~矩形测试结束~~~~~~~~~~~~~~~~");

        Polygon circle = GISUtil.createCircle(116.403322, 39.920255, degree100);
        Coordinate[] coordinates3 = circle.getCoordinates();
        for (int i = 0; i < coordinates3.length; i++) {
            Coordinate coord = coordinates3[i];
            System.out.println("[" + String.format("%.6f", coord.x) + "," + String.format("%.6f", coord.y) + "],");
        }

//        double dis=0.09999999999999432;
//        double degree = 2*Math.PI*Math.cos(2*Math.PI/360*dis)*6378140/360;
//        double degree2 = (1/degree)*100;
//        System.out.println(degree2);


    }


}
