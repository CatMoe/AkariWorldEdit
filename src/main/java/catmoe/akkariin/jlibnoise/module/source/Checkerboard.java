package catmoe.akkariin.jlibnoise.module.source;

import catmoe.akkariin.jlibnoise.MathHelper;
import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.Utils;

public class Checkerboard
extends Module {
    public Checkerboard() {
        super(0);
    }

    public int GetSourceModuleCount() {
        return 0;
    }

    public double GetValue(double x, double y, double z) {
        @SuppressWarnings("unused")
        int iz;
        @SuppressWarnings("unused")
        int iy;
        int ix = MathHelper.floor(Utils.MakeInt32Range(x));
        return (ix & 1 ^ (iy = MathHelper.floor(Utils.MakeInt32Range(y))) & 1 ^ (iz = MathHelper.floor(Utils.MakeInt32Range(z))) & 1) != 0 ? -1.0 : 1.0;
    }
}
 