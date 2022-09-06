package com.amazon.deecomms.calling.model;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class Boundary {
    private List<Point> points = new ArrayList();

    /* loaded from: classes12.dex */
    public static class Point {
        public final double x;
        public final double y;

        public Point(double d, double d2) {
            this.x = d;
            this.y = d2;
        }
    }

    public void clear() {
        this.points.clear();
    }

    public boolean contains(double d, double d2) {
        int size = this.points.size() - 1;
        boolean z = false;
        for (int i = 0; i < this.points.size(); i++) {
            if ((this.points.get(i).y > d2) != (this.points.get(size).y > d2)) {
                if (d < (((d2 - this.points.get(i).y) * (this.points.get(size).x - this.points.get(i).x)) / (this.points.get(size).y - this.points.get(i).y)) + this.points.get(i).x) {
                    z = !z;
                }
            }
            size = i;
        }
        return z;
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public void setPoints(List<Point> list) {
        this.points = list;
    }
}
