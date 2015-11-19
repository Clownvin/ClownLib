package com.clown.math;

import java.awt.Dimension;
import java.util.Arrays;

public final class MathUtils {

	public static Point2D calculateAveragePoint(final Point2D[] points) {
		int x = 0, y = 0;
		for (int i = 0; i < points.length; i++) {
			x += points[i].getX();
			y += points[i].getY();
		}
		return new Point2D(x / points.length, y / points.length);
	}

	public static Point3D calculateAveragePoint(final Point3D[] points) {
		float x = 0, y = 0, z = 0;
		for (int i = 0; i < points.length; i++) {
			x += points[i].getX();
			y += points[i].getY();
			z += points[i].getZ();
		}
		return new Point3D(x / points.length, y / points.length, z / points.length);
	}

	public static Polygon convexHull(Point2D[] points) {
		if (points.length > 1) {
			int n = points.length, k = 0;
			Point2D[] h = new Point2D[2 * n];
			Arrays.sort(points);
			for (int i = 0; i < n; ++i) {
				while (k >= 2 && cross(h[k - 2], h[k - 1], points[i]) <= 0)
					k--;
				h[k++] = points[i];
			}

			for (int i = n - 2, t = k + 1; i >= 0; i--) {
				while (k >= t && cross(h[k - 2], h[k - 1], points[i]) <= 0)
					k--;
				h[k++] = points[i];
			}
			if (k > 1) {
				h = Arrays.copyOfRange(h, 0, k - 1);
			}
			return new Polygon(h);
		} else if (points.length <= 1) {
			return new Polygon(points);
		} else {
			return null;
		}
	}

	public static boolean coordinateInArea(final int x, final int y, final Dimension area) {
		return (x >= 0 && y >= 0) && (x <= area.getWidth() && y <= area.getHeight());
	}

	public static double cross(Point2D p1, Point2D p2, Point2D p3) {
		return (double) (((p2.getX() - p1.getX()) * (p3.getY() - p1.getY()))
				- ((p2.getY() - p1.getY()) * (p3.getX() - p1.getX())));
	}

	public static int distance(Point3D p1, Point3D p2) {
		return (int) Math.sqrt((Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2) + Math.pow(p2.z - p1.z, 2)));
	}

	public static float dotProduct(Point3D p1, Point3D p2) {
		return (p1.getX() * p2.getX()) + (p1.getY() * p2.getY()) + (p1.getZ() * p2.getZ());
	}

	public static Point3D normalizeVector(Point3D vector) {
		float magnitude = (float) Math
				.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) + Math.pow(vector.getZ(), 2));
		return new Point3D(vector.getX() / magnitude, vector.getY() / magnitude, vector.getZ() / magnitude);
	}

	private MathUtils() {
		// To prevent instantiation.
	}
}
