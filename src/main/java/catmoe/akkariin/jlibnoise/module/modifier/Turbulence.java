package catmoe.akkariin.jlibnoise.module.modifier;

import catmoe.akkariin.jlibnoise.module.source.Perlin;
import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.exception.NoModuleException;

public class Turbulence
extends Module {
    public static final double DEFAULT_TURBULENCE_FREQUENCY = 1.0;
    public static final double DEFAULT_TURBULENCE_POWER = 1.0;
    public static final int DEFAULT_TURBULENCE_ROUGHNESS = 3;
    public static final int DEFAULT_TURBULENCE_SEED = 0;
    double power = 1.0;
    final Perlin xDistortModule = new Perlin();
    final Perlin yDistortModule = new Perlin();
    final Perlin zDistortModule = new Perlin();

    public Turbulence() {
        super(1);
    }

    public double getPower() {
        return this.power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public int getRoughnessCount() {
        return this.xDistortModule.getOctaveCount();
    }

    public double getFrequency() {
        return this.xDistortModule.getFrequency();
    }

    public int getSeed() {
        return this.xDistortModule.getSeed();
    }

    public void setSeed(int seed) {
        this.xDistortModule.setSeed(seed);
        this.yDistortModule.setSeed(seed + 1);
        this.zDistortModule.setSeed(seed + 2);
    }

    public void setFrequency(double frequency) {
        this.xDistortModule.setFrequency(frequency);
        this.yDistortModule.setFrequency(frequency);
        this.zDistortModule.setFrequency(frequency);
    }

    public void setRoughness(int roughness) {
        this.xDistortModule.setOctaveCount(roughness);
        this.yDistortModule.setOctaveCount(roughness);
        this.zDistortModule.setOctaveCount(roughness);
    }

    public int GetSourceModuleCount() {
        return 1;
    }

    public double GetValue(double x, double y, double z) {
        if (this.SourceModule[0] == null) {
            throw new NoModuleException();
        }
        double x0 = x + 0.189422607421875;
        double y0 = y + 0.99371337890625;
        double z0 = z + 0.4781646728515625;
        double x1 = x + 0.4046478271484375;
        double y1 = y + 0.276611328125;
        double z1 = z + 0.9230499267578125;
        double x2 = x + 0.82122802734375;
        double y2 = y + 0.1710968017578125;
        double z2 = z + 0.6842803955078125;
        double xDistort = x + this.xDistortModule.GetValue(x0, y0, z0) * this.power;
        double yDistort = y + this.yDistortModule.GetValue(x1, y1, z1) * this.power;
        double zDistort = z + this.zDistortModule.GetValue(x2, y2, z2) * this.power;
        return this.SourceModule[0].GetValue(xDistort, yDistort, zDistort);
    }
}