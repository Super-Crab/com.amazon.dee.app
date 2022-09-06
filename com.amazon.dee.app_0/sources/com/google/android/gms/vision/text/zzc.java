package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzr;
/* loaded from: classes2.dex */
final class zzc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Rect zza(Text text) {
        Point[] cornerPoints;
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (Point point : text.getCornerPoints()) {
            i = Math.min(i, point.x);
            i2 = Math.max(i2, point.x);
            i4 = Math.min(i4, point.y);
            i3 = Math.max(i3, point.y);
        }
        return new Rect(i, i4, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Point[] zza(zzr zzrVar) {
        double sin = Math.sin(Math.toRadians(zzrVar.zzdh));
        double cos = Math.cos(Math.toRadians(zzrVar.zzdh));
        int i = zzrVar.width;
        int i2 = (int) ((i * cos) + zzrVar.left);
        double d = zzrVar.top;
        int i3 = zzrVar.height;
        Point[] pointArr = {new Point(zzrVar.left, zzrVar.top), new Point(i2, (int) ((i * sin) + d)), new Point((int) (pointArr[1].x - (i3 * sin)), (int) ((i3 * cos) + pointArr[1].y)), new Point((pointArr[2].x - pointArr[1].x) + pointArr[0].x, (pointArr[2].y - pointArr[1].y) + pointArr[0].y)};
        return pointArr;
    }
}
