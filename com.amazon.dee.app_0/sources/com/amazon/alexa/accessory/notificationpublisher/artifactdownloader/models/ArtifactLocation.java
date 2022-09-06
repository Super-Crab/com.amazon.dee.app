package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models;

import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes.dex */
public abstract class ArtifactLocation implements StronglyTypedString {
    public static ArtifactLocation create(String str) {
        return new AutoValue_ArtifactLocation(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<ArtifactLocation> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<ArtifactLocation>() { // from class: com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactLocation.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public ArtifactLocation mo328instantiate(String str) {
                return ArtifactLocation.create(str);
            }
        };
    }
}
