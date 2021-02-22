package com.cg.converter;

import com.cg.model.LABColor;
import com.cg.model.RGBColor;
import com.cg.model.XYZColor;

public class XYZConverter {

    public static XYZColor fromRGB(RGBColor rgbColor) {
        double rn = rgbToXyzFunc(rgbColor.getR() / 255) * 100;
        double gn = rgbToXyzFunc(rgbColor.getG() / 255) * 100;
        double bn = rgbToXyzFunc(rgbColor.getB() / 255) * 100;
        double xParam = 0.412453 * rn + 0.357580 * gn + 0.180423 * bn;
        double yParam = 0.212671 * rn + 0.715160 * gn + 0.072169 * bn;
        double zParam = 0.019334 * rn + 0.119193 * gn + 0.950227 * bn;
        return new XYZColor(xParam, yParam, zParam);
    }

    public static XYZColor fromLab(LABColor labColor) {
        double tmp = (labColor.getL() + 16) / 116;
        double yParam = labToXyzFunc(tmp) * 95.047;
        double xParam = labToXyzFunc(labColor.getA() / 500 + tmp) * 100;
        double zParam = labToXyzFunc(tmp - labColor.getB() / 200) * 108.883;
        return new XYZColor(xParam, yParam, zParam);
    }

    private static double rgbToXyzFunc(double x) {
        double f;
        if (x >= 0.04045) {
            f = Math.pow((x + 0.055) / 1.055, 2.4);
        } else {
            f = x / 12.92;
        }
        return f;
    }

    private static double labToXyzFunc(double x) {
        double f;
        double tmp = Math.pow(x, 3);
        if (tmp >= 0.008856) {
            f = tmp;
        } else {
            f = (x - (double) 16 / 116) / 7.787;
        }
        return f;
    }
}
