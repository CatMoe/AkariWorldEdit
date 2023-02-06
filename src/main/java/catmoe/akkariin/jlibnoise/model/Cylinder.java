package catmoe.akkariin.jlibnoise.model;

import catmoe.akkariin.jlibnoise.MathHelper;
import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.module.Module;

public class Cylinder {
    Module module;

    public Cylinder(Module mod) {
        this.module = mod;
    }

    public Module getModule() {
        return this.module;
    }

    public void setModule(Module mod) {
        if (mod == null) {
            throw new IllegalArgumentException("Mod cannot be null");
        }
        this.module = mod;
    }

    double getValue(double angle, double height) {
        if (this.module == null) {
            throw new NoModuleException();
        }
        double x = MathHelper.cos(angle * (Math.PI / 180));
        double y = height;
        double z = MathHelper.sin(angle * (Math.PI / 180));
        return this.module.GetValue(x, y, z);
    }
}
 