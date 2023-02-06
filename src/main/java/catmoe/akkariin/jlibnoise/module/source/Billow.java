package catmoe.akkariin.jlibnoise.module.source;

import catmoe.akkariin.jlibnoise.Noise;
import catmoe.akkariin.jlibnoise.NoiseQuality;
import catmoe.akkariin.jlibnoise.Utils;
import catmoe.akkariin.jlibnoise.module.Module;

public class Billow
extends Module {
    public static final double DEFAULT_BILLOW_FREQUENCY = 1.0;
    public static final double DEFAULT_BILLOW_LACUNARITY = 2.0;
    public static final int DEFAULT_BILLOW_OCTAVE_COUNT = 6;
    public static final double DEFAULT_BILLOW_PERSISTENCE = 0.5;
    public static final NoiseQuality DEFAULT_BILLOW_QUALITY = NoiseQuality.STANDARD;
    public static final int DEFAULT_BILLOW_SEED = 0;
    public static final int BILLOW_MAX_OCTAVE = 30;
    protected double frequency = 1.0;
    protected double lacunarity = 2.0;
    protected NoiseQuality quality = DEFAULT_BILLOW_QUALITY;
    protected double persistence = 0.5;
    protected int seed = 0;
    protected int octaveCount = 6;

    public Billow() {
        super(0);
    }

    public int getOctaveCount() {
        return this.octaveCount;
    }

    public void setOctaveCount(int octaveCount) {
        if (octaveCount < 1 || octaveCount > 30) {
            throw new IllegalArgumentException("octaveCount must be between 1 and BILLOW_MAX_OCTAVE: 30");
        }
        this.octaveCount = octaveCount;
    }

    public double getFrequency() {
        return this.frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getLacunarity() {
        return this.lacunarity;
    }

    public void setLacunarity(double lacunarity) {
        this.lacunarity = lacunarity;
    }

    public NoiseQuality getQuality() {
        return this.quality;
    }

    public void setQuality(NoiseQuality quality) {
        this.quality = quality;
    }

    public double getPersistence() {
        return this.persistence;
    }

    public void setPersistence(double persistance) {
        this.persistence = persistance;
    }

    public int getSeed() {
        return this.seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int GetSourceModuleCount() {
        return 0;
    }

    public double GetValue(double x, double y, double z) {
        double z1 = z;
        double y1 = y;
        double x1 = x;
        double value = 0.0;
        double curPersistence = 1.0;
        x1 *= this.frequency;
        y1 *= this.frequency;
        z1 *= this.frequency;
        for (int curOctave = 0; curOctave < this.octaveCount; ++curOctave) {
            double nx = Utils.MakeInt32Range(x1);
            double ny = Utils.MakeInt32Range(y1);
            double nz = Utils.MakeInt32Range(z1);
            int seed = this.seed + curOctave;
            double signal = Noise.GradientCoherentNoise3D(nx, ny, nz, seed, this.quality);
            signal = 2.0 * Math.abs(signal) - 1.0;
            value += signal * curPersistence;
            x1 *= this.lacunarity;
            y1 *= this.lacunarity;
            z1 *= this.lacunarity;
            curPersistence *= this.persistence;
        }
        return value += 0.5;
    }
}