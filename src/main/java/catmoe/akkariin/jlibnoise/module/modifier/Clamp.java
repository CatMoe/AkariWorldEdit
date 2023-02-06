package catmoe.akkariin.jlibnoise.module.modifier;

import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.module.Module;

public class Clamp
extends Module {
    double lowerBound = 0.0;
    double upperBound = 1.0;

    public Clamp() {
        super(1);
    }

    public double getLowerBound() {
        return this.lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public double getUpperBound() {
        return this.upperBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public int GetSourceModuleCount() {
        return 1;
    }

    public double GetValue(double x, double y, double z) {
        if (this.SourceModule[0] == null) {
            throw new NoModuleException();
        }
        double value = this.SourceModule[0].GetValue(x, y, z);
        if (value < this.lowerBound) {
            return this.lowerBound;
        }
        if (value > this.upperBound) {
            return this.upperBound;
        }
        return value;
    }
}
 