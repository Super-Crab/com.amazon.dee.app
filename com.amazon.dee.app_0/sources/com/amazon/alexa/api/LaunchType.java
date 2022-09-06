package com.amazon.alexa.api;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public enum LaunchType {
    WAKE_WORD,
    TAP_TO_TALK,
    TEXT,
    UNKNOWN;

    /* loaded from: classes6.dex */
    static class a implements u<LaunchType> {
        private static final String a = "a";

        @Override // com.amazon.alexa.api.u
        /* renamed from: a */
        public Bundle toBundle(LaunchType launchType) {
            Preconditions.notNull(launchType, "launchType can't be null");
            Bundle bundle = new Bundle();
            bundle.putString("launchType", launchType.name());
            return bundle;
        }

        @Override // com.amazon.alexa.api.u
        @Nullable
        /* renamed from: a */
        public LaunchType mo844createFromBundle(Bundle bundle) {
            Preconditions.notNull(bundle, "Bundle can't be null");
            String string = bundle.getString("launchType");
            if (!TextUtils.isEmpty(string)) {
                return LaunchType.valueOf(string);
            }
            String str = a;
            Log.w(str, "Unable to create launchType from bundle: " + bundle);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LaunchType fromString(String str) {
        LaunchType[] values;
        for (LaunchType launchType : values()) {
            if (launchType.name().equals(str)) {
                return launchType;
            }
        }
        return UNKNOWN;
    }
}
