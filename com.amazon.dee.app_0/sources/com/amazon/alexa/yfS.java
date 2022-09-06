package com.amazon.alexa;

import android.content.ComponentName;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_ExternalMediaPlayerRegistration;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
/* compiled from: ExternalMediaPlayerRegistration.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class yfS {
    public static final yfS zZm = zZm(vQe.zZm, "", "", AbstractC0188bKf.zZm, zYH.zZm, ZYY.zZm, zZm.UNAUTHORIZED, Collections.emptySet());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ExternalMediaPlayerRegistration.java */
    /* loaded from: classes.dex */
    public enum zZm {
        UNKNOWN,
        AUTHORIZED,
        UNAUTHORIZED
    }

    public static yfS zZm(vQe vqe, yfS yfs) {
        bve bveVar = (bve) yfs;
        return zZm(vqe, bveVar.zyO.getPackageName(), bveVar.zyO.getClassName(), bveVar.jiA, bveVar.Qle, bveVar.JTe, zZm.AUTHORIZED, bveVar.yPL);
    }

    public String toString() {
        bve bveVar = (bve) this;
        return String.format(Locale.US, "{%s: %s, %s: %s, %s: %s, %s: %s, %s: %s, %s: %s, %s: %s}", "Registration", bveVar.zQM, "Version", bveVar.jiA.getValue(), "Component", bveVar.zyO, "Cookie", bveVar.Qle, "PlayerVersion", bveVar.JTe, "ExternalPlayerID", bveVar.BIo, "State", bveVar.LPk.name());
    }

    public static yfS zZm(yfS yfs) {
        bve bveVar = (bve) yfs;
        return zZm(vQe.zZm, bveVar.zyO.getPackageName(), bveVar.zyO.getClassName(), bveVar.jiA, bveVar.Qle, bveVar.JTe, zZm.UNAUTHORIZED, bveVar.yPL);
    }

    public static yfS zZm(String str, String str2, AbstractC0188bKf abstractC0188bKf, zYH zyh, ZYY zyy, Set<String> set) {
        return zZm(vQe.zZm, str, str2, abstractC0188bKf, zyh, zyy, zZm.UNKNOWN, set);
    }

    public static yfS zZm(vQe vqe, String str, String str2, AbstractC0188bKf abstractC0188bKf, zYH zyh, ZYY zyy, zZm zzm, Set<String> set) {
        return new AutoValue_ExternalMediaPlayerRegistration(vqe, FHI.zZm(str), new ComponentName(str, str2), abstractC0188bKf, zyh, zyy, zzm, set);
    }

    public static TypeAdapter<yfS> zZm(Gson gson) {
        return new AutoValue_ExternalMediaPlayerRegistration.GsonTypeAdapter(gson);
    }
}
