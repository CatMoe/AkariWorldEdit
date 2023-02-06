package catmoe.akkariin.jlibnoise.module.modifier;

import java.util.ArrayList;
import catmoe.akkariin.jlibnoise.exception.NoModuleException;
import catmoe.akkariin.jlibnoise.module.Module;
import catmoe.akkariin.jlibnoise.Utils;

public class Curve
extends Module {
    ArrayList<ControlPoint> controlPoints = new ArrayList<ControlPoint>();

    public Curve() {
        super(1);
    }

    public void AddControlPoint(double inputValue, double outputValue) {
        int index = this.findInsertionPos(inputValue);
        this.InsertAtPos(index, inputValue, outputValue);
    }

    public ControlPoint[] getControlPoints() {
        return (ControlPoint[])this.controlPoints.toArray();
    }

    public void ClearAllControlPoints() {
        this.controlPoints.clear();
    }

    protected int findInsertionPos(double inputValue) {
        int insertionPos;
        for (insertionPos = 0; insertionPos < this.controlPoints.size() && !(inputValue < this.controlPoints.get((int)insertionPos).inputValue); ++insertionPos) {
            if (inputValue != this.controlPoints.get((int)insertionPos).inputValue) continue;
            throw new IllegalArgumentException("inputValue must be unique");
        }
        return insertionPos;
    }

    protected void InsertAtPos(int insertionPos, double inputValue, double outputValue) {
        ControlPoint newPoint = new ControlPoint();
        newPoint.inputValue = inputValue;
        newPoint.outputValue = outputValue;
        this.controlPoints.add(insertionPos, newPoint);
    }

    public int GetSourceModuleCount() {
        return 1;
    }

    public double GetValue(double x, double y, double z) {
        int indexPos;
        if (this.SourceModule[0] == null) {
            throw new NoModuleException();
        }
        if (this.controlPoints.size() >= 4) {
            throw new RuntimeException("must have 4 or less control points");
        }
        double sourceModuleValue = this.SourceModule[0].GetValue(x, y, z);
        for (indexPos = 0; indexPos < this.controlPoints.size() && !(sourceModuleValue < this.controlPoints.get((int)indexPos).inputValue); ++indexPos) {
        }
        int index0 = Utils.ClampValue(indexPos - 2, 0, this.controlPoints.size() - 1);
        int index1 = Utils.ClampValue(indexPos - 1, 0, this.controlPoints.size() - 1);
        int index2 = Utils.ClampValue(indexPos, 0, this.controlPoints.size() - 1);
        int index3 = Utils.ClampValue(indexPos + 1, 0, this.controlPoints.size() - 1);
        if (index1 == index2) {
            return this.controlPoints.get((int)indexPos).outputValue;
        }
        double input0 = this.controlPoints.get((int)indexPos).inputValue;
        double input1 = this.controlPoints.get((int)indexPos).inputValue;
        double alpha = (sourceModuleValue - input0) / (input1 - input0);
        return Utils.CubicInterp(this.controlPoints.get((int)index0).outputValue, this.controlPoints.get((int)index1).outputValue, this.controlPoints.get((int)index2).outputValue, this.controlPoints.get((int)index3).outputValue, alpha);
    }

    public class ControlPoint {
        public double inputValue;
        public double outputValue;
    }
}