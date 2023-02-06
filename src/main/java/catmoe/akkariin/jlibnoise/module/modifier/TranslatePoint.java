package catmoe.akkariin.jlibnoise.module.modifier;

import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.module.Module;

public class TranslatePoint
extends Module {
    public static final double DEFAULT_TRANSLATE_POINT_X = 0.0;
    public static final double DEFAULT_TRANSLATE_POINT_Y = 0.0;
    public static final double DEFAULT_TRANSLATE_POINT_Z = 0.0;
    double xTranslation = 0.0;
    double yTranslation = 0.0;
    double zTranslation = 0.0;

    public TranslatePoint() {
        super(1);
    }

    public double getXTranslation() {
        return this.xTranslation;
    }

    public void setXTranslation(double xTranslation) {
        this.xTranslation = xTranslation;
    }

    public double getYTranslation() {
        return this.yTranslation;
    }

    public void setYTranslation(double yTranslation) {
        this.yTranslation = yTranslation;
    }

    public double getZTranslation() {
        return this.zTranslation;
    }

    public void setZTranslation(double zTranslation) {
        this.zTranslation = zTranslation;
    }

    public void setTranslations(double x, double y, double z) {
        this.setXTranslation(x);
        this.setYTranslation(y);
        this.setZTranslation(z);
    }

    public int GetSourceModuleCount() {
        return 1;
    }

    public double GetValue(double x, double y, double z) {
        if (this.SourceModule[0] == null) {
            throw new NoModuleException();
        }
        return this.SourceModule[0].GetValue(x + this.xTranslation, y + this.yTranslation, z + this.zTranslation);
    }
}
 