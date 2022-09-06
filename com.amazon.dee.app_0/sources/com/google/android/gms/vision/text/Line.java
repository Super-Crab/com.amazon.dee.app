package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzag;
import com.google.android.gms.internal.vision.zzx;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class Line implements Text {
    private zzx zzcz;
    private List<Element> zzda;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Line(zzx zzxVar) {
        this.zzcz = zzxVar;
    }

    public float getAngle() {
        return this.zzcz.zzdj.zzdh;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Rect getBoundingBox() {
        return zzc.zza(this);
    }

    @Override // com.google.android.gms.vision.text.Text
    public List<? extends Text> getComponents() {
        zzag[] zzagVarArr = this.zzcz.zzdi;
        if (zzagVarArr.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzda == null) {
            this.zzda = new ArrayList(zzagVarArr.length);
            for (zzag zzagVar : this.zzcz.zzdi) {
                this.zzda.add(new Element(zzagVar));
            }
        }
        return this.zzda;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Point[] getCornerPoints() {
        return zzc.zza(this.zzcz.zzdj);
    }

    public String getLanguage() {
        return this.zzcz.zzdd;
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getValue() {
        return this.zzcz.zzdm;
    }

    public boolean isVertical() {
        return this.zzcz.zzdo;
    }
}
