package PhysicsEngine.Objects.Native;

import PhysicsEngine.Matrix;
import PhysicsEngine.Physics3.WorldData;
import PhysicsEngine.Physics3.WorldObject;
import javafx.geometry.Point3D;

import javax.vecmath.Color3f;
import java.util.ArrayList;

/**
 * Created by pmmde on 6/13/2016.
 */
public class Hole extends WorldObject {

    public Hole(WorldData w, Point3D offset, Matrix r, double radius, double depth, int parts) {
        super(w);
        pointsOriginal = new ArrayList<>();
        colors = new ArrayList<>();
        sides = new ArrayList<>();
        edges = new ArrayList<>();
        waters = new ArrayList<>();

        colors.add(new Color3f(0.8f, 0.8f, 0.8f));//0
        colors.add(new Color3f(0.5f, 0.5f, 0.5f));//1
        colors.add(new Color3f(0.0f, 1.0f, 0.0f));//2
        Point3D mid = new Point3D(0, 0, -depth);
        Point3D cornerPoints[] = new Point3D[4];
        cornerPoints[0] = new Point3D(radius, radius, 0);
        cornerPoints[1] = new Point3D(-radius, radius, 0);
        cornerPoints[2] = new Point3D(-radius, -radius, 0);
        cornerPoints[3] = new Point3D(radius, -radius, 0);
        double angleGrowSize = Math.PI / (parts / 2);
        for (int i = 0; i < 4; i++) {
            for (double angle = i * Math.PI / 2; angle < (i + 1) * Math.PI * 1.99 / 4; angle += angleGrowSize) {
                Point3D p1 = new Point3D(Math.cos(angle) * radius, Math.sin(angle) * radius, 0);
                Point3D p2 = new Point3D(Math.cos(angle + angleGrowSize) * radius, Math.sin(angle + angleGrowSize) * radius, 0);
                Point3D p3 = new Point3D(Math.cos(angle) * radius, Math.sin(angle) * radius, -depth);
                Point3D p4 = new Point3D(Math.cos(angle + angleGrowSize) * radius, Math.sin(angle + angleGrowSize) * radius, -depth);
                addSquare(p1, p2, p4, p3, 0, 0.1);
                addTriangle(p3, p4, mid, 1, 0.1);
                addTriangle(p1, p2, cornerPoints[i], 2, 0.1);
            }
        }
        center = offset;
        rotation = r;
    }
}
