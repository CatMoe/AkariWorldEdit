package catmoe.akkariin.jlibnoise.model;

import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.Utils;

public class Sphere {
    Module module;

    public Sphere(Module module) {
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

    public double getValue(double lat, double log) {
        if (this.module == null) {
            throw new NoModuleException();
        }
        double[] vec = Utils.LatLonToXYZ(lat, log);
        return this.module.GetValue(vec[0], vec[1], vec[2]);
    }
}
 