package com.drew.metadata.photoshop;

import java.util.ArrayList;
/* loaded from: classes2.dex */
public class Subpath {
    private final ArrayList<Knot> _knots;
    private final String _type;

    public Subpath() {
        this("");
    }

    public Subpath(String str) {
        this._knots = new ArrayList<>();
        this._type = str;
    }

    public void add(Knot knot) {
        this._knots.add(knot);
    }

    public Iterable<Knot> getKnots() {
        return this._knots;
    }

    public String getType() {
        return this._type;
    }

    public int size() {
        return this._knots.size();
    }
}
