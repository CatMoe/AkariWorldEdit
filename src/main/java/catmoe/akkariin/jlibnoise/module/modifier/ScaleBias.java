package catmoe.akkariin.jlibnoise.module.modifier;

import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.module.Module;

public class ScaleBias
extends Module {
    public static final double DEFAULT_BIAS = 0.0;
    public static final double DEFAULT_SCALE = 1.0;
    double bias = 0.0;
    double scale = 1.0;

    public ScaleBias() {
        super(1);
    }

    public double getBias() {
        return this.bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getScale() {
        return this.scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public int GetSourceModuleCount() {
        return 1;
    }

    public double GetValue(double x, double y, double z) {
        if (this.SourceModule[0] == null) {
            throw new NoModuleException();
        }
        return this.SourceModule[0].GetValue(x, y, z) * this.scale + this.bias;
    }
}
 