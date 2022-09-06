package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_MediaStructure;
import com.amazon.alexa.mRm;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: MediaStructure.java */
@AutoValue
/* renamed from: com.amazon.alexa.ddD  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0197ddD {
    public static final AbstractC0197ddD zZm = zZm().zZm();

    /* compiled from: MediaStructure.java */
    @AutoValue.Builder
    /* renamed from: com.amazon.alexa.ddD$zZm */
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public abstract AbstractC0197ddD zZm();
    }

    public static zZm zZm() {
        mRm.zZm zzm = new mRm.zZm();
        zzm.zZm = "ExternalMediaPlayerMusicItem";
        WlR wlR = WlR.zZm;
        if (wlR != null) {
            zzm.BIo = wlR;
            return zzm;
        }
        throw new NullPointerException("Null value");
    }

    public static TypeAdapter<AbstractC0197ddD> zZm(Gson gson) {
        return new AutoValue_MediaStructure.GsonTypeAdapter(gson);
    }
}
