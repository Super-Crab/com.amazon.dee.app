package com.amazon.alexa.alertsca;

import androidx.annotation.NonNull;
import com.amazon.alexa.alertsca.utils.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AssetIdentifier implements StronglyTypedString {
    public static AssetIdentifier create(String str) {
        return new AutoValue_AssetIdentifier(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<AssetIdentifier> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<AssetIdentifier>() { // from class: com.amazon.alexa.alertsca.AssetIdentifier.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.alertsca.utils.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public AssetIdentifier mo718instantiate(@NonNull String str) {
                return AssetIdentifier.create(str);
            }
        };
    }

    public static TypeAdapter<AssetIdentifier> typeAdapter(Gson gson) {
        return new StronglyTypedString.StronglyTypedStringAdapter<AssetIdentifier>() { // from class: com.amazon.alexa.alertsca.AssetIdentifier.2
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.alertsca.utils.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public AssetIdentifier mo718instantiate(@NonNull String str) {
                return AssetIdentifier.create(str);
            }
        };
    }
}
