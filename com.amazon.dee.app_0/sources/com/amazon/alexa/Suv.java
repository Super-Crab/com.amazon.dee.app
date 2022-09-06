package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.MQV;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_GeolocationStatePayload;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Date;
/* compiled from: GeolocationStatePayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Suv implements ComponentStatePayload {

    /* compiled from: GeolocationStatePayload.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public abstract zZm zZm(Xdr xdr);

        public abstract zZm zZm(@Nullable Date date);
    }

    public static zZm zZm() {
        return new MQV.zZm();
    }

    public static TypeAdapter<Suv> zZm(Gson gson) {
        return new AutoValue_GeolocationStatePayload.GsonTypeAdapter(gson);
    }
}
