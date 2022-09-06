package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models;

import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes.dex */
public abstract class ArtifactIdentifier implements StronglyTypedString {
    public static ArtifactIdentifier create(String str) {
        return new AutoValue_ArtifactIdentifier(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<ArtifactIdentifier> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<ArtifactIdentifier>() { // from class: com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactIdentifier.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public ArtifactIdentifier mo328instantiate(String str) {
                return ArtifactIdentifier.create(str);
            }
        };
    }
}
