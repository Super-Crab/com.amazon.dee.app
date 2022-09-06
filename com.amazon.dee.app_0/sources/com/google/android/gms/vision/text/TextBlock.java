package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.gms.internal.vision.zzr;
import com.google.android.gms.internal.vision.zzx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzx[] zzdb;
    private List<Line> zzdc;
    private String zzdd;
    private Rect zzde;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextBlock(SparseArray<zzx> sparseArray) {
        this.zzdb = new zzx[sparseArray.size()];
        int i = 0;
        while (true) {
            zzx[] zzxVarArr = this.zzdb;
            if (i < zzxVarArr.length) {
                zzxVarArr[i] = sparseArray.valueAt(i);
                i++;
            } else {
                return;
            }
        }
    }

    @Override // com.google.android.gms.vision.text.Text
    public Rect getBoundingBox() {
        if (this.zzde == null) {
            this.zzde = zzc.zza(this);
        }
        return this.zzde;
    }

    @Override // com.google.android.gms.vision.text.Text
    public List<? extends Text> getComponents() {
        zzx[] zzxVarArr = this.zzdb;
        if (zzxVarArr.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzdc == null) {
            this.zzdc = new ArrayList(zzxVarArr.length);
            for (zzx zzxVar : this.zzdb) {
                this.zzdc.add(new Line(zzxVar));
            }
        }
        return this.zzdc;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Point[] getCornerPoints() {
        TextBlock textBlock;
        zzx[] zzxVarArr;
        TextBlock textBlock2 = this;
        if (textBlock2.cornerPoints == null) {
            char c = 0;
            if (textBlock2.zzdb.length != 0) {
                int i = Integer.MAX_VALUE;
                int i2 = Integer.MIN_VALUE;
                int i3 = Integer.MIN_VALUE;
                int i4 = 0;
                int i5 = Integer.MAX_VALUE;
                while (true) {
                    zzxVarArr = textBlock2.zzdb;
                    if (i4 >= zzxVarArr.length) {
                        break;
                    }
                    zzr zzrVar = zzxVarArr[i4].zzdj;
                    zzr zzrVar2 = zzxVarArr[c].zzdj;
                    double sin = Math.sin(Math.toRadians(zzrVar2.zzdh));
                    double cos = Math.cos(Math.toRadians(zzrVar2.zzdh));
                    Point[] pointArr = new Point[4];
                    pointArr[c] = new Point(zzrVar.left, zzrVar.top);
                    pointArr[c].offset(-zzrVar2.left, -zzrVar2.top);
                    int i6 = i;
                    int i7 = (int) ((pointArr[c].y * sin) + (pointArr[c].x * cos));
                    int i8 = (int) ((pointArr[0].y * cos) + ((-pointArr[0].x) * sin));
                    pointArr[0].x = i7;
                    pointArr[0].y = i8;
                    pointArr[1] = new Point(zzrVar.width + i7, i8);
                    pointArr[2] = new Point(zzrVar.width + i7, zzrVar.height + i8);
                    pointArr[3] = new Point(i7, i8 + zzrVar.height);
                    i = i6;
                    for (int i9 = 0; i9 < 4; i9++) {
                        Point point = pointArr[i9];
                        i = Math.min(i, point.x);
                        i2 = Math.max(i2, point.x);
                        i5 = Math.min(i5, point.y);
                        i3 = Math.max(i3, point.y);
                    }
                    i4++;
                    c = 0;
                    textBlock2 = this;
                }
                int i10 = i;
                zzr zzrVar3 = zzxVarArr[c].zzdj;
                int i11 = zzrVar3.left;
                int i12 = zzrVar3.top;
                double sin2 = Math.sin(Math.toRadians(zzrVar3.zzdh));
                double cos2 = Math.cos(Math.toRadians(zzrVar3.zzdh));
                Point[] pointArr2 = {new Point(i10, i5), new Point(i2, i5), new Point(i2, i3), new Point(i10, i3)};
                for (int i13 = 0; i13 < 4; i13++) {
                    pointArr2[i13].x = (int) ((pointArr2[i13].x * cos2) - (pointArr2[i13].y * sin2));
                    pointArr2[i13].y = (int) ((pointArr2[i13].y * cos2) + (pointArr2[i13].x * sin2));
                    pointArr2[i13].offset(i11, i12);
                }
                textBlock = this;
                textBlock.cornerPoints = pointArr2;
                return textBlock.cornerPoints;
            }
            textBlock2.cornerPoints = new Point[0];
        }
        textBlock = textBlock2;
        return textBlock.cornerPoints;
    }

    public String getLanguage() {
        zzx[] zzxVarArr;
        String str = this.zzdd;
        if (str != null) {
            return str;
        }
        HashMap hashMap = new HashMap();
        for (zzx zzxVar : this.zzdb) {
            hashMap.put(zzxVar.zzdd, Integer.valueOf((hashMap.containsKey(zzxVar.zzdd) ? ((Integer) hashMap.get(zzxVar.zzdd)).intValue() : 0) + 1));
        }
        this.zzdd = (String) ((Map.Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        String str2 = this.zzdd;
        if (str2 == null || str2.isEmpty()) {
            this.zzdd = C.LANGUAGE_UNDETERMINED;
        }
        return this.zzdd;
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getValue() {
        zzx[] zzxVarArr = this.zzdb;
        if (zzxVarArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zzxVarArr[0].zzdm);
        for (int i = 1; i < this.zzdb.length; i++) {
            sb.append("\n");
            sb.append(this.zzdb[i].zzdm);
        }
        return sb.toString();
    }
}
