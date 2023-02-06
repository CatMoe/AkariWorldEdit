package catmoe.akkariin.jlibnoise.module.modifier;

import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.module.Module;

public class Abs
extends Module {
    public Abs() {
        super(1);
    }

    public int GetSourceModuleCount() {
        return 1;
    }

    public double GetValue(double x, double y, double z) {
        if (this.SourceModule == null) {
            throw new NoModuleException();
        }
        return Math.abs(this.SourceModule[0].GetValue(x, y, z));
    }
}
 