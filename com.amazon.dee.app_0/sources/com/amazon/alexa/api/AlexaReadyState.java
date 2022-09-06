package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
/* loaded from: classes6.dex */
public class AlexaReadyState {
    public static final AlexaReadyState DEFAULT_STATE = create(false, false);
    private final boolean isConnected;
    private final boolean isReady;

    /* loaded from: classes6.dex */
    static class AlexaReadyStateAdapter implements u<AlexaReadyState> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public enum AlexaReadyStateKey implements Bundles.Key {
            IS_CONNECTED,
            IS_READY
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public AlexaReadyState mo844createFromBundle(Bundle bundle) {
            return new AlexaReadyState(bundle.getBoolean(AlexaReadyStateKey.IS_CONNECTED.name(), false), bundle.getBoolean(AlexaReadyStateKey.IS_READY.name(), false));
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(AlexaReadyState alexaReadyState) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(AlexaReadyStateKey.IS_CONNECTED.name(), alexaReadyState.isConnected());
            bundle.putBoolean(AlexaReadyStateKey.IS_READY.name(), alexaReadyState.isReady());
            return bundle;
        }
    }

    private AlexaReadyState(boolean z, boolean z2) {
        this.isConnected = z;
        this.isReady = z2;
    }

    public static AlexaReadyState create(boolean z, boolean z2) {
        return new AlexaReadyState(z, z2);
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public boolean isReady() {
        return this.isReady;
    }
}
