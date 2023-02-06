package catmoe.akkariin.jlibnoise.model;

import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.exception.NoModuleException;

public class Plane {
    Module module;

    public Plane(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("module cannot be null");
        }
        this.module = module;
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

    double getValue(double x, double z) {
        if (this.module == null) {
            throw new NoModuleException();
        }
        return this.module.GetValue(x, 0.0, z);
    }
}
 