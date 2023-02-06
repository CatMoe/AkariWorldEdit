package catmoe.akkariin.jlibnoise.module.combiner;

import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.exception.NoModuleException;

public class Displace
extends Module {
    public Displace() {
        super(4);
    }

    public int GetSourceModuleCount() {
        return 4;
    }

    public Module GetXDisplaceModule() {
        if (this.SourceModule == null || this.SourceModule[1] == null) {
            throw new NoModuleException();
        }
        return this.SourceModule[1];
    }

    public Module GetYDisplaceModule() {
        if (this.SourceModule == null || this.SourceModule[2] == null) {
            throw new NoModuleException();
        }
        return this.SourceModule[2];
    }

    public Module GetZDisplaceModule() {
        if (this.SourceModule == null || this.SourceModule[3] == null) {
            throw new NoModuleException();
        }
        return this.SourceModule[3];
    }

    public void SetXDisplaceModule(Module x) {
        if (x == null) {
            throw new IllegalArgumentException("x cannot be null");
        }
        this.SourceModule[1] = x;
    }

    public void SetYDisplaceModule(Module y) {
        if (y == null) {
            throw new IllegalArgumentException("y cannot be null");
        }
        this.SourceModule[2] = y;
    }

    public void SetZDisplaceModule(Module z) {
        if (z == null) {
            throw new IllegalArgumentException("z cannot be null");
        }
        this.SourceModule[3] = z;
    }

    public void SetDisplaceModules(Module x, Module y, Module z) {
        this.SetXDisplaceModule(x);
        this.SetYDisplaceModule(y);
        this.SetZDisplaceModule(z);
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
        if (this.SourceModule[3] == null) {
            throw new NoModuleException();
        }
        double xDisplace = x + this.SourceModule[1].GetValue(x, y, z);
        double yDisplace = y + this.SourceModule[2].GetValue(x, y, z);
        double zDisplace = z + this.SourceModule[3].GetValue(x, y, z);
        return this.SourceModule[0].GetValue(xDisplace, yDisplace, zDisplace);
    }
}