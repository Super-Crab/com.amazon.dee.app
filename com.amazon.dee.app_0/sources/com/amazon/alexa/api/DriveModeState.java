package com.amazon.alexa.api;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public enum DriveModeState {
    ACCESSORY,
    AUTOBLUETOOTH,
    MANUAL,
    NONE;

    /* loaded from: classes6.dex */
    static class a implements u<DriveModeState> {
        private static final String a = "a";

        @Override // com.amazon.alexa.api.u
        /* renamed from: a */
        public Bundle toBundle(DriveModeState driveModeState) {
            Preconditions.notNull(driveModeState, "driveModeState can't be null");
            Bundle bundle = new Bundle();
            bundle.putString("driveModeState", driveModeState.name());
            return bundle;
        }

        @Override // com.amazon.alexa.api.u
        @Nullable
        /* renamed from: a */
        public DriveModeState mo844createFromBundle(Bundle bundle) {
            Preconditions.notNull(bundle, "Bundle can't be null");
            String string = bundle.getString("driveModeState");
            if (!TextUtils.isEmpty(string)) {
                return DriveModeState.valueOf(string);
            }
            String str = a;
            Log.w(str, "Unable to create driveModeState from bundle: " + bundle);
            return null;
        }
    }
}
