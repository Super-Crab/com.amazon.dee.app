package com.drew.metadata.photoshop;
/* loaded from: classes2.dex */
public class Knot {
    private final double[] _points = new double[6];
    private final String _type;

    public Knot(String str) {
        this._type = str;
    }

    public double getPoint(int i) {
        return this._points[i];
    }

    public String getType() {
        return this._type;
    }

    public void setPoint(int i, double d) {
        this._points[i] = d;
    }
}
