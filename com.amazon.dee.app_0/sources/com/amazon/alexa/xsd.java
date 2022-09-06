package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.settings.payload.AutoValue_SettingsPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
/* compiled from: SettingsPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class xsd implements Payload {
    public static xsd zZm(qWv... qwvArr) {
        return zZm(Arrays.asList(qwvArr));
    }

    public static xsd zZm(Set<qWv> set) {
        return zZm(new ArrayList(set));
    }

    public static xsd zZm(List<qWv> list) {
        return new AutoValue_SettingsPayload(list);
    }

    public static TypeAdapter<xsd> zZm(Gson gson) {
        return new AutoValue_SettingsPayload.GsonTypeAdapter(gson);
    }
}
