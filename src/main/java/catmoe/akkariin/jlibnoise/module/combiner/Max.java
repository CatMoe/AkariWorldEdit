package catmoe.akkariin.jlibnoise.module.combiner;

import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.Utils;

public class Max
extends Module {
    public Max() {
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
        double v0 = this.SourceModule[0].GetValue(x, y, z);
        double v1 = this.SourceModule[1].GetValue(x, y, z);
        return Utils.GetMax(v0, v1);
    }
}
 