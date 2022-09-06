package com.amazon.alexa.api;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public enum ArtifactDownloadListenerFailure {
    INTERRUPTION,
    DOWNLOAD;

    /* loaded from: classes6.dex */
    static class a implements u<ArtifactDownloadListenerFailure> {
        private static final String a = "a";

        @Override // com.amazon.alexa.api.u
        /* renamed from: a */
        public Bundle toBundle(@NonNull ArtifactDownloadListenerFailure artifactDownloadListenerFailure) {
            Preconditions.notNull(artifactDownloadListenerFailure, "ArtifactDownloadListenerFailure can't be null");
            Bundle bundle = new Bundle();
            bundle.putString("artifactDownloadFailureAdapter", artifactDownloadListenerFailure.name());
            return bundle;
        }

        @Override // com.amazon.alexa.api.u
        /* renamed from: a */
        public ArtifactDownloadListenerFailure mo844createFromBundle(@NonNull Bundle bundle) {
            Preconditions.notNull(bundle, "Bundle can't be null");
            String string = bundle.getString("artifactDownloadFailureAdapter");
            if (!TextUtils.isEmpty(string)) {
                return ArtifactDownloadListenerFailure.valueOf(string);
            }
            String str = a;
            Log.w(str, "Unable to create ArtifactDownloadListenerFailure from bundle: " + bundle);
            return null;
        }
    }
}
