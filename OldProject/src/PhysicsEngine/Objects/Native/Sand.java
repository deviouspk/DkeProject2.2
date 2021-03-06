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
public class Sand extends WorldObject {
    public Sand(Point3D offset, Matrix r, double x, double y, WorldData w) {
        super(w);
        pointsOriginal = new ArrayList<>();
        colors = new ArrayList<>();
        sides = new ArrayList<>();
        edges = new ArrayList<>();
        waters = new ArrayList<>();

        colors.add(new Color3f(1.0f, 0.5f, 0.0f));
        Point3D p1 = new Point3D(0, 0, 0);
        Point3D p2 = new Point3D(0 + x, 0, 0);
        Point3D p3 = new Point3D(0 + x, 0 + y, 0);
        Point3D p4 = new Point3D(0, 0 + y, 0);
        addSquare(p1, p2, p3, p4, 0, 0.5);
        center = offset;
        rotation = r;
    }
}
