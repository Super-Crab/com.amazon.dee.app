package com.amazon.alexa.alertsca;

import com.amazon.alexa.alertsca.AutoValue_Asset;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class Asset {
    public static Asset create(AssetIdentifier assetIdentifier, String str) {
        return new AutoValue_Asset(assetIdentifier, str);
    }

    public static TypeAdapter<Asset> typeAdapter(Gson gson) {
        return new AutoValue_Asset.GsonTypeAdapter(gson);
    }

    public abstract AssetIdentifier getAssetId();

    public abstract String getUrl();
}
