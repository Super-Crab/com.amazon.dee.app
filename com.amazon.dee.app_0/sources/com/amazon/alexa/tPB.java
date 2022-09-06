package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.settings.payload.AutoValue_SupportsMobileDownchannelSettingsPayload;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.ArrayList;
/* compiled from: SupportsMobileDownchannelSettingsPayload.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class tPB implements Payload {
    public static tPB zZm(yqu yquVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(yquVar);
        return new AutoValue_SupportsMobileDownchannelSettingsPayload(arrayList);
    }

    public static TypeAdapter<tPB> zZm(Gson gson) {
        return new AutoValue_SupportsMobileDownchannelSettingsPayload.GsonTypeAdapter(gson);
    }
}
