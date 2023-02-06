package catmoe.akkariin.jlibnoise.module.source;

import catmoe.akkariin.jlibnoise.Noise;
import catmoe.akkariin.jlibnoise.NoiseQuality;
import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.Utils;

public class RidgedMulti
extends Module {
    public static final double DEFAULT_RIDGED_FREQUENCY = 1.0;
    public static final double DEFAULT_RIDGED_LACUNARITY = 2.0;
    public static final int DEFAULT_RIDGED_OCTAVE_COUNT = 6;
    public static final NoiseQuality DEFAULT_RIDGED_QUALITY = NoiseQuality.STANDARD;
    public static final int DEFAULT_RIDGED_SEED = 0;
    public static final int RIDGED_MAX_OCTAVE = 30;
    double frequency = 1.0;
    double lacunarity = 2.0;
    NoiseQuality noiseQuality = DEFAULT_RIDGED_QUALITY;
    int octaveCount = 6;
    double[] SpectralWeights;
    int seed = 0;

    public RidgedMulti() {
        super(0);
        this.CalcSpectralWeights();
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
        this.octaveCount = Utils.GetMin(octaveCount, 30);
    }

    public int getSeed() {
        return this.seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    protected void CalcSpectralWeights() {
        double h = 1.0;
        double frequency = 1.0;
        this.SpectralWeights = new double[30];
        for (int i = 0; i < 30; ++i) {
            this.SpectralWeights[i] = Math.pow(frequency, -h);
            frequency *= this.lacunarity;
        }
    }

    public int GetSourceModuleCount() {
        return 0;
    }

    public double GetValue(double x, double y, double z) {
        double x1 = x;
        double y1 = y;
        double z1 = z;
        x1 *= this.frequency;
        y1 *= this.frequency;
        z1 *= this.frequency;
        double value = 0.0;
        double weight = 1.0;
        double offset = 1.0;
        double gain = 2.0;
        for (int curOctave = 0; curOctave < this.octaveCount; ++curOctave) {
            double nx = Utils.MakeInt32Range(x1);
            double ny = Utils.MakeInt32Range(y1);
            double nz = Utils.MakeInt32Range(z1);
            int seed = this.seed + curOctave & Integer.MAX_VALUE;
            double signal = Noise.GradientCoherentNoise3D(nx, ny, nz, seed, this.noiseQuality);
            signal = Math.abs(signal);
            signal = offset - signal;
            signal *= signal;
            if ((weight = (signal *= weight) * gain) > 1.0) {
                weight = 1.0;
            }
            if (weight < 0.0) {
                weight = 0.0;
            }
            value += signal * this.SpectralWeights[curOctave];
            x1 *= this.lacunarity;
            y1 *= this.lacunarity;
            z1 *= this.lacunarity;
        }
        return value * 1.25 - 1.0;
    }
}