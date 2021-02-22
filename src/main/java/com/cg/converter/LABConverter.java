package com.cg.converter;

import com.cg.model.LABColor;
import com.cg.model.RGBColor;
import com.cg.model.XYZColor;

public class LABConverter {

    public static LABColor fromRGB(RGBColor rgbColor){
        XYZColor xyzColor = XYZConverter.fromRGB(rgbColor);
        return fromXYZ(xyzColor);
    }

    public static LABColor fromXYZ(XYZColor xyzColor){
        double yResTemp = xyzToLabFunc(xyzColor.getY() / 100);
        double lParam = 116 * yResTemp - 16;
        double aParam = 500 * (xyzToLabFunc(xyzColor.getX() / 95.047) - yResTemp);
        double bParam = 200 * (yResTemp - xyzToLabFunc(xyzColor.getZ() / 108.883));
        return new LABColor(lParam,aParam,bParam);
    }

    private static double xyzToLabFunc(double x){
        double f;
        double third = 0.3333333333333333;
        if(x >= 0.008856){
            f = Math.pow(x, third);
        }
        else{
            f = 7.787 * x + (double)16/116;
        }
        return f;
    }
}
