package com.amazon.alexa.api;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public enum AlexaSupportedInitiationType {
    WAKE_WORD,
    TAP_TO_TALK,
    SERVER;

    /* loaded from: classes6.dex */
    static class SupportedInitiationTypeAdapter implements u<AlexaSupportedInitiationType> {
        static final String KEY_SUPPORTED_INITIATION_TYPE = "suppressedInitiationType";
        private static final String TAG = "SupportedInitiationTypeAdapter";

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        @Nullable
        /* renamed from: createFromBundle */
        public AlexaSupportedInitiationType mo844createFromBundle(Bundle bundle) {
            Preconditions.notNull(bundle, "Bundle can't be null");
            String string = bundle.getString(KEY_SUPPORTED_INITIATION_TYPE);
            if (!TextUtils.isEmpty(string)) {
                return AlexaSupportedInitiationType.valueOf(string);
            }
            Log.w(TAG, "Unable to create AlexaSupportedInitiationType from bundle");
            return null;
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(AlexaSupportedInitiationType alexaSupportedInitiationType) {
            Preconditions.notNull(alexaSupportedInitiationType, "supportedInitiationType can't be null");
            Bundle bundle = new Bundle();
            bundle.putString(KEY_SUPPORTED_INITIATION_TYPE, alexaSupportedInitiationType.name());
            return bundle;
        }
    }
}
