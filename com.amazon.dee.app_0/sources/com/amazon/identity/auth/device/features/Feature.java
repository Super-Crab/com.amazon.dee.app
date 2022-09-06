package com.amazon.identity.auth.device.features;

import android.content.Context;
import com.amazon.identity.auth.device.eg;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.hx;
import com.amazon.identity.auth.device.mz;
import com.amazon.identity.platform.weblab.Weblab;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public enum Feature {
    OverrideDeviceAttributes { // from class: com.amazon.identity.auth.device.features.Feature.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return !mz.aY(context);
        }
    },
    DSNWhenNotRegistered { // from class: com.amazon.identity.auth.device.features.Feature.2
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return !hx.ak(context) || mz.aY(context);
        }
    },
    PandaRegistration { // from class: com.amazon.identity.auth.device.features.Feature.3
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return "panda".equals(new eg().get("com.amazon.map.registration")) || !hx.ak(context);
        }
    },
    SplitRegistration { // from class: com.amazon.identity.auth.device.features.Feature.4
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return "split".equals(new eg().get("com.amazon.map.registration")) || !mz.bb(context);
        }
    },
    DirectedIdSupported { // from class: com.amazon.identity.auth.device.features.Feature.5
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return mz.aW(context);
        }
    },
    SplitRegistrationWithDirectedId { // from class: com.amazon.identity.auth.device.features.Feature.6
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return !mz.bb(context);
        }
    },
    RegistrationViaAuthToken { // from class: com.amazon.identity.auth.device.features.Feature.7
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return mz.bb(context);
        }
    },
    UseDeviceLocaleAsLanguagePreference { // from class: com.amazon.identity.auth.device.features.Feature.8
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return mz.aY(context);
        }
    },
    IsolateApplication { // from class: com.amazon.identity.auth.device.features.Feature.9
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return IsolatedModeSwitcher.isAppInIsolatedMode(context);
        }
    },
    SecondaryRegistrationUsingPanda { // from class: com.amazon.identity.auth.device.features.Feature.10
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return Weblab.Treatment.T1.equals(Weblab.MAP_ANDROID_SECONDARY_PANDA_93840.bu(context));
        }
    };

    public abstract boolean fetchValue(Context context);

    public boolean fetchValueNoCache(Context context) {
        return fetchValue(context);
    }
}
