package catmoe.akkariin.jlibnoise.model;

import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.exception.NoModuleException;

public class Line {
    boolean attenuate = false;
    Module module;
    double x0 = 0.0;
    double x1 = 1.0;
    double y0 = 0.0;
    double y1 = 1.0;
    double z0 = 0.0;
    double z1 = 1.0;

    public Line(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("module cannot be null");
        }
        this.module = module;
    }

    public boolean attenuate() {
        return this.attenuate;
    }

    public void setAttenuate(boolean att) {
        this.attenuate = att;
    }

    public void setStartPoint(double x, double y, double z) {
        this.x0 = x;
        this.y0 = y;
        this.z0 = z;
    }

    public void setEndPoint(double x, double y, double z) {
        this.x1 = x;
        this.y1 = y;
        this.z1 = z;
    }

    public Module getModule() {
        return this.module;
    }

    public void setModule(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("module cannot be null");
        }
        this.module = module;
    }

    public double getValue(double p) {
        if (this.module == null) {
            throw new NoModuleException();
        }
        double x = (this.x1 - this.x0) * p + this.x0;
        double y = (this.y1 - this.y0) * p + this.y0;
        double z = (this.z1 - this.z0) * p + this.z0;
        double value = this.module.GetValue(x, y, z);
        if (this.attenuate) {
            return p * (1.0 - p) * 4.0 * value;
        }
        return value;
    }
}