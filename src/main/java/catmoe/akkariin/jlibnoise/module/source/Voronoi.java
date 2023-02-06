package catmoe.akkariin.jlibnoise.module.source;

import catmoe.akkariin.jlibnoise.MathHelper;
import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.Noise;

public class Voronoi
extends Module {
    public static final double DEFAULT_VORONOI_DISPLACEMENT = 1.0;
    public static final double DEFAULT_VORONOI_FREQUENCY = 1.0;
    public static final int DEFAULT_VORONOI_SEED = 0;
    double displacement = 1.0;
    boolean enableDistance = false;
    double frequency = 1.0;
    int seed = 0;

    public Voronoi() {
        super(0);
    }

    public double getDisplacement() {
        return this.displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public boolean isEnableDistance() {
        return this.enableDistance;
    }

    public void setEnableDistance(boolean enableDistance) {
        this.enableDistance = enableDistance;
    }

    public double getFrequency() {
        return this.frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
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
        double value;
        double x1 = x;
        double y1 = y;
        double z1 = z;
        int xInt = x1 > 0.0 ? (int)x1 : (int)(x1 *= this.frequency) - 1;
        int yInt = y1 > 0.0 ? (int)y1 : (int)(y1 *= this.frequency) - 1;
        int zInt = z1 > 0.0 ? (int)z1 : (int)(z1 *= this.frequency) - 1;
        double minDist = 2.147483647E9;
        double xCandidate = 0.0;
        double yCandidate = 0.0;
        double zCandidate = 0.0;
        for (int zCur = zInt - 2; zCur <= zInt + 2; ++zCur) {
            for (int yCur = yInt - 2; yCur <= yInt + 2; ++yCur) {
                for (int xCur = xInt - 2; xCur <= xInt + 2; ++xCur) {
                    double zPos;
                    double zDist;
                    double yPos;
                    double yDist;
                    double xPos = (double)xCur + Noise.ValueNoise3D(xCur, yCur, zCur, this.seed);
                    double xDist = xPos - x1;
                    double dist = xDist * xDist + (yDist = (yPos = (double)yCur + Noise.ValueNoise3D(xCur, yCur, zCur, this.seed + 1)) - y1) * yDist + (zDist = (zPos = (double)zCur + Noise.ValueNoise3D(xCur, yCur, zCur, this.seed + 2)) - z1) * zDist;
                    if (!(dist < minDist)) continue;
                    minDist = dist;
                    xCandidate = xPos;
                    yCandidate = yPos;
                    zCandidate = zPos;
                }
            }
        }
        if (this.enableDistance) {
            double xDist = xCandidate - x1;
            double yDist = yCandidate - y1;
            double zDist = zCandidate - z1;
            value = MathHelper.sqrt(xDist * xDist + yDist * yDist + zDist * zDist) * 1.7320508075688772 - 1.0;
        } else {
            value = 0.0;
        }
        return value + this.displacement * Noise.ValueNoise3D(MathHelper.floor(xCandidate), MathHelper.floor(yCandidate), MathHelper.floor(zCandidate), this.seed);
    }
}