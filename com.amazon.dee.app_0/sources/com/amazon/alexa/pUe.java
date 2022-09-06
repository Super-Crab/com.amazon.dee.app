package com.amazon.alexa;

import com.amazon.alexa.VIE;
import com.amazon.alexa.client.alexaservice.iocomponent.payload.AutoValue_TrustedStatesPayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
/* compiled from: TrustedStatesPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class pUe implements ComponentStatePayload {
    public static pUe zZm(VIE.BIo bIo, String str, VIE.zZm zzm, List<PcE> list) {
        return new AutoValue_TrustedStatesPayload(bIo, str, zzm, list);
    }

    public static TypeAdapter<pUe> zZm(Gson gson) {
        return new AutoValue_TrustedStatesPayload.GsonTypeAdapter(gson);
    }
}
