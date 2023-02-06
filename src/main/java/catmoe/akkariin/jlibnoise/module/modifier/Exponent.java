package catmoe.akkariin.jlibnoise.module.modifier;

import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.module.Module;

public class Exponent
extends Module {
    public static final double DEFAULT_EXPONENT = 1.0;
    protected double exponent = 1.0;

    public Exponent() {
        super(1);
    }

    public double getExponent() {
        return this.exponent;
    }

    public void setExponent(double exponent) {
        this.exponent = exponent;
    }

    public int GetSourceModuleCount() {
        return 1;
    }

    public double GetValue(double x, double y, double z) {
        if (this.SourceModule[0] == null) {
            throw new NoModuleException();
        }
        double value = this.SourceModule[0].GetValue(x, y, z);
        return Math.pow(Math.abs((value + 1.0) / 2.0), this.exponent) * 2.0 - 1.0;
    }
}
 