package catmoe.akkariin.jlibnoise.module.combiner;

import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.module.Module;

public class Add
extends Module {
    public Add() {
        super(2);
    }

    public int GetSourceModuleCount() {
        return 2;
    }

    public double GetValue(double x, double y, double z) {
        if (this.SourceModule[0] == null) {
            throw new NoModuleException();
        }
        if (this.SourceModule[1] == null) {
            throw new NoModuleException();
        }
        return this.SourceModule[0].GetValue(x, y, z) + this.SourceModule[1].GetValue(x, y, z);
    }
}
 