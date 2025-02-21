package datos.lab2.frame;

import java.util.stream.DoubleStream;

/**
 *
 * @author Sean
 * Just a useful data structure for X,Y,Z triplets.

 */
public class Point3D {

    public float x = 0;
    public float y = 0;
    public float z = 0;

    public float f = 0; // for function evaluation
    /*
     * @param X,Y,Z are all floats to align with TriangleMesh needs
     */
    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(double x, double y, double z) {
        this((float) x, (float) y, (float) z);
    }

    public Point3D(float x, float y, float z, float f) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.f = f;
    }

    public DoubleStream getCoordinates() { return DoubleStream.of(x,y,z); }
    public DoubleStream getCoordinates(float factor) { return DoubleStream.of(factor*x,factor*y,factor*z); }

    public Point3D add(Point3D point) {
        return add(point.x, point.y, point.z);
    }

    public Point3D add(float x, float y, float z) {
        return new Point3D(this.x + x, this.y + y, this.z + z);
    }

    public Point3D substract(Point3D point) {
        return substract(point.x, point.y, point.z);
    }

    public Point3D substract(float x, float y, float z) {
        return new Point3D(this.x - x, this.y - y, this.z - z);
    }

    public Point3D multiply(float factor) {
        return new Point3D(this.x * factor, this.y * factor, this.z * factor);
    }

    public Point3D normalize() {
        final float mag = magnitude();

        if (mag == 0.0) {
            return new Point3D(0f, 0f, 0f);
        }

        return new Point3D(x / mag, y / mag, z / mag);
    }

    public float magnitude() {
        return (float)Math.sqrt(x * x + y * y + z * z);
    }

    public float dotProduct(Point3D point) {
        return dotProduct(point.x, point.y, point.z);
    }

    public float dotProduct(float x, float y, float z) {
        return this.x * x + this.y * y + this.z * z;
    }

    public Point3D crossProduct(Point3D point) {
        return crossProduct(point.x, point.y, point.z);
    }

    public Point3D crossProduct(float x, float y, float z) {
        return new Point3D(-this.z * y + this.y * z,
                this.z * x - this.x * z,
                -this.y * x + this.x * y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public static Point3D convertFromJavaFXPoint3D(javafx.geometry.Point3D point3D) {
        return new Point3D(point3D.getX(), point3D.getY(), point3D.getZ());
    }

    public static javafx.geometry.Point3D convertToJavaFXPoint3D(Point3D point3D) {
        return new javafx.geometry.Point3D(point3D.getX(), point3D.getY(), point3D.getZ());
    }

    @Override
    public String toString() {
        return "Point3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Float.floatToIntBits(this.x);
        hash = 29 * hash + Float.floatToIntBits(this.y);
        hash = 29 * hash + Float.floatToIntBits(this.z);
        hash = 29 * hash + Float.floatToIntBits(this.f);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point3D other = (Point3D) obj;
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) {
            return false;
        }
        if (Float.floatToIntBits(this.z) != Float.floatToIntBits(other.z)) {
            return false;
        }
        if (Float.floatToIntBits(this.f) != Float.floatToIntBits(other.f)) {
            return false;
        }
        return true;
    }

    public String toCSV() {
        return "" + x + ";" + y + ";" + z + ";"+f;
    }

}
