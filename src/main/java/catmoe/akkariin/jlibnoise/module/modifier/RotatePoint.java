package catmoe.akkariin.jlibnoise.module.modifier;

import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.MathHelper;

public class RotatePoint
extends Module {
    public static final double DEFAULT_ROTATE_X = 0.0;
    public static final double DEFAULT_ROTATE_Y = 0.0;
    public static final double DEFAULT_ROTATE_Z = 0.0;
    double xAngle = 0.0;
    double yAngle = 0.0;
    double zAngle = 0.0;
    double x1Matrix;
    double x2Matrix;
    double x3Matrix;
    double y1Matrix;
    double y2Matrix;
    double y3Matrix;
    double z1Matrix;
    double z2Matrix;
    double z3Matrix;

    public RotatePoint() {
        super(1);
        this.setAngles(0.0, 0.0, 0.0);
    }

    public void setAngles(double x, double y, double z) {
        double xCos = MathHelper.cos(x * (Math.PI / 180));
        double yCos = MathHelper.cos(y * (Math.PI / 180));
        double zCos = MathHelper.cos(z * (Math.PI / 180));
        double xSin = MathHelper.sin(x * (Math.PI / 180));
        double ySin = MathHelper.sin(y * (Math.PI / 180));
        double zSin = MathHelper.sin(z * (Math.PI / 180));
        this.x1Matrix = ySin * xSin * zSin + yCos * zCos;
        this.y1Matrix = xCos * zSin;
        this.z1Matrix = ySin * zCos - yCos * xSin * zSin;
        this.x2Matrix = ySin * xSin * zCos - yCos * zSin;
        this.y2Matrix = xCos * zCos;
        this.z2Matrix = -yCos * xSin * zCos - ySin * zSin;
        this.x3Matrix = -ySin * xCos;
        this.y3Matrix = xSin;
        this.z3Matrix = yCos * xCos;
        this.xAngle = x;
        this.yAngle = y;
        this.zAngle = z;
    }

    public double getxAngle() {
        return this.xAngle;
    }

    public void setxAngle(double xAngle) {
        this.setAngles(xAngle, this.yAngle, this.zAngle);
    }

    public double getyAngle() {
        return this.yAngle;
    }

    public void setyAngle(double yAngle) {
        this.setAngles(this.xAngle, yAngle, this.zAngle);
    }

    public double getzAngle() {
        return this.zAngle;
    }

    public void setzAngle(double zAngle) {
        this.setAngles(this.xAngle, this.yAngle, zAngle);
    }

    public int GetSourceModuleCount() {
        return 1;
    }

    public double GetValue(double x, double y, double z) {
        if (this.SourceModule[0] == null) {
            throw new NoModuleException();
        }
        double nx = this.x1Matrix * x + this.y1Matrix * y + this.z1Matrix * z;
        double ny = this.x2Matrix * x + this.y2Matrix * y + this.z2Matrix * z;
        double nz = this.x3Matrix * x + this.y3Matrix * y + this.z3Matrix * z;
        return this.SourceModule[0].GetValue(nx, ny, nz);
    }
}