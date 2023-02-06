package catmoe.akkariin.jlibnoise.module.combiner;

import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.Utils;
import catmoe.akkariin.jlibnoise.module.Module;

public class Blend
extends Module {
    public Blend() {
        super(3);
    }

    public Module getControlModule() {
        if (this.SourceModule[2] == null) {
            throw new NoModuleException();
        }
        return this.SourceModule[2];
    }

    public void setControlModule(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("Control Module cannot be null");
        }
        this.SourceModule[2] = module;
    }

    public int GetSourceModuleCount() {
        return 3;
    }

    public double GetValue(double x, double y, double z) {
        if (this.SourceModule[0] == null) {
            throw new NoModuleException();
        }
        if (this.SourceModule[1] == null) {
            throw new NoModuleException();
        }
        if (this.SourceModule[2] == null) {
            throw new NoModuleException();
        }
        double v0 = this.SourceModule[0].GetValue(x, y, z);
        double v1 = this.SourceModule[1].GetValue(x, y, z);
        double alpha = (this.SourceModule[2].GetValue(x, y, z) + 1.0) / 2.0;
        return Utils.LinearInterp(v0, v1, alpha);
    }
}