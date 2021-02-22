package com.cg.converter;

import com.cg.model.LABColor;
import com.cg.model.RGBColor;
import com.cg.model.XYZColor;

public class RGBConverter {

    public static RGBColor fromXYZ(XYZColor xyzColor) {
        double xTemp = xyzColor.getX() / 100;
        double yTemp = xyzColor.getY() / 100;
        double zTemp = xyzColor.getZ() / 100;
        double rn = 3.2406 * xTemp - 1.5372 * yTemp - 0.4986 * zTemp;
        double gn = 1.8758 * yTemp + 0.0415 * zTemp - 0.9689 * xTemp;
        double bn = 0.0557 * xTemp - 0.2040 * yTemp + 1.0570 * zTemp;
        double rParam = xyzToRgbFunc(rn) * 255;
        double gParam = xyzToRgbFunc(gn) * 255;
        double bParam = xyzToRgbFunc(bn) * 255;
        return new RGBColor(rParam, gParam, bParam);
    }

    public static RGBColor fromLAB(LABColor labColor){
        XYZColor xyz = XYZConverter.fromLab(labColor);
        return fromXYZ(xyz);
    }

    private static double xyzToRgbFunc(double x) {
        double f;
        if (x >= 0.0031308) {
            f = 1.055 * Math.pow(x, 1 / 2.4) - 0.055;
        } else {
            f = x * 12.92;
        }
        return f;
    }
}
