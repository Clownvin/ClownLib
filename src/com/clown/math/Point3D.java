package com.clown.math;

/**
 * 
 * @author Calvin Gene Hall
 *
 *         <code>Point3D</code> objects are immutable. The main reason behind
 *         this was to help deter creating accidental thread bugs, since this
 *         class was first created for my 3D Rendering engine.
 */
public class Point3D {
	private static final Point3D POINT3D_BUILDER = new Point3D();

	/**
	 * Static method for getting the basic builder object.
	 * 
	 * @return the builder object
	 */
	public static Point3D getBuilder() {
		return POINT3D_BUILDER;
	}

	public final float x, y, z;

	/**
	 * Private constructor to prevent instantiation except by builder.
	 */
	private Point3D() {
		// For builder only
		x = 0;
		y = 0;
		z = 0;
	}

	/**
	 * General constructor for a new <code>Point3D</code>
	 * 
	 * @param x
	 *            the x coordinate of the new 3D point
	 * @param y
	 *            the y coordinate of the new 3D point
	 * @param z
	 *            the z coordinate of the new 3D point
	 */
	public Point3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Created a new object with the original values incremented by the offset
	 * point values.
	 * 
	 * @param offsetPoint
	 *            the offset point from which to increment the original values
	 *            of this object
	 * @return a new point3D object with the values stored into it.
	 */
	public Point3D applyOffest(Point3D offsetPoint) {
		return new Point3D(x + offsetPoint.getX(), y + offsetPoint.getY(), z + offsetPoint.getZ());
	}

	/**
	 * A simple getter method for the "x" value
	 * 
	 * @return the x value of this object
	 */
	public float getX() {
		return x;
	}

	/**
	 * A simple getter method for the "y" value
	 * 
	 * @return the y value of this object
	 */
	public float getY() {
		return y;
	}

	/**
	 * A simple getter method for the "z" value
	 * 
	 * @return the z value of this object
	 */
	public float getZ() {
		return z;
	}

	/**
	 * Setter method for the x value. Deprecated because, since this is a
	 * mutable object, a "setter" method cannot modify the values of the object
	 * object, so therefore they must return new objects only, which means they
	 * aren't setter method in the proper sense.
	 * 
	 * @param newX
	 *            new x value for this object
	 * @return a new <code>Point3D</code> object with the x value replaced with
	 *         the new x value
	 */
	@Deprecated
	public Point3D setX(float newX) {
		return new Point3D(newX, y, z);
	}

	/**
	 * Setter method for the y value. Deprecated because, since this is a
	 * mutable object, a "setter" method cannot modify the values of the object
	 * object, so therefore they must return new objects only, which means they
	 * aren't setter method in the proper sense.
	 * 
	 * @param newY
	 *            new y value for this object
	 * @return a new <code>Point3D</code> object with the y value replaced with
	 *         the new y value
	 */
	@Deprecated
	public Point3D setY(float newY) {
		return new Point3D(x, newY, z);
	}

	/**
	 * Setter method for the z value. Deprecated because, since this is a
	 * mutable object, a "setter" method cannot modify the values of the object
	 * object, so therefore they must return new objects only, which means they
	 * aren't setter method in the proper sense.
	 * 
	 * @param newZ
	 *            new z value for this object
	 * @return a new <code>Point3D</code> object with the z value replaced with
	 *         the new z value
	 */
	@Deprecated
	public Point3D setZ(float newZ) {
		return new Point3D(x, y, newZ);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}
}
