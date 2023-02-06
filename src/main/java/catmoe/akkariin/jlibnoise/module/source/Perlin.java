package catmoe.akkariin.jlibnoise.module.source;

import catmoe.akkariin.jlibnoise.Noise;
import catmoe.akkariin.jlibnoise.NoiseQuality;
import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.Utils;

public class Perlin
extends Module {
    public static final double DEFAULT_PERLIN_FREQUENCY = 1.0;
    public static final double DEFAULT_PERLIN_LACUNARITY = 2.0;
    public static final int DEFAULT_PERLIN_OCTAVE_COUNT = 6;
    public static final double DEFAULT_PERLIN_PERSISTENCE = 0.5;
    public static final NoiseQuality DEFAULT_PERLIN_QUALITY = NoiseQuality.STANDARD;
    public static final int DEFAULT_PERLIN_SEED = 0;
    public static final int PERLIN_MAX_OCTAVE = 30;
    double frequency = 1.0;
    double lacunarity = 2.0;
    NoiseQuality noiseQuality = DEFAULT_PERLIN_QUALITY;
    int octaveCount = 6;
    double persistence = 0.5;
    int seed = 0;

    public Perlin() {
        super(0);
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

    public NoiseQuality getNoiseQuality() {
        return this.noiseQuality;
    }

    public void setNoiseQuality(NoiseQuality noiseQuality) {
        this.noiseQuality = noiseQuality;
    }

    public int getOctaveCount() {
        return this.octaveCount;
    }

    public void setOctaveCount(int octaveCount) {
        if (octaveCount < 1 || octaveCount > 30) {
            throw new IllegalArgumentException("octaveCount must be between 1 and MAX OCTAVE: 30");
        }
        this.octaveCount = octaveCount;
    }

    public double getPersistence() {
        return this.persistence;
    }

    public void setPersistence(double persistence) {
        this.persistence = persistence;
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
        double x1 = x;
        double y1 = y;
        double z1 = z;
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
            double signal = Noise.GradientCoherentNoise3D(nx, ny, nz, seed, this.noiseQuality);
            value += signal * curPersistence;
            x1 *= this.lacunarity;
            y1 *= this.lacunarity;
            z1 *= this.lacunarity;
            curPersistence *= this.persistence;
        }
        return value;
    }
}