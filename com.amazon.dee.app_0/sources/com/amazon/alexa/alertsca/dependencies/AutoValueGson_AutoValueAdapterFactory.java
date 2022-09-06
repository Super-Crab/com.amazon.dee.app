package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.alertsca.AlertLabel;
import com.amazon.alexa.alertsca.Asset;
import com.amazon.alexa.alertsca.AssetIdentifier;
import com.amazon.alexa.alertsca.payload.AlertsPayload;
import com.amazon.alexa.alertsca.payload.AlertsStatePayload;
import com.amazon.alexa.alertsca.payload.DeleteAlertsPayload;
import com.amazon.alexa.alertsca.payload.SetAlertsPayload;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
/* loaded from: classes6.dex */
public final class AutoValueGson_AutoValueAdapterFactory extends AutoValueAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (AlertLabel.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) AlertLabel.typeAdapter(gson);
        }
        if (AlertsPayload.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) AlertsPayload.typeAdapter(gson);
        }
        if (AlertsStatePayload.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) AlertsStatePayload.typeAdapter(gson);
        }
        if (Asset.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Asset.typeAdapter(gson);
        }
        if (AssetIdentifier.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) AssetIdentifier.typeAdapter(gson);
        }
        if (DeleteAlertsPayload.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) DeleteAlertsPayload.typeAdapter(gson);
        }
        if (!SetAlertsPayload.class.isAssignableFrom(rawType)) {
            return null;
        }
        return (TypeAdapter<T>) SetAlertsPayload.typeAdapter(gson);
    }
}
