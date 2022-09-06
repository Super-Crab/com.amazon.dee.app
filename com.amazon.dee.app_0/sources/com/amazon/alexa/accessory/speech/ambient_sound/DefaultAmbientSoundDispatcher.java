package com.amazon.alexa.accessory.speech.ambient_sound;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.avsclient.ambient_sound.LastUtteranceSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessory.speechapi.AmbientSoundDispatcher;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public final class DefaultAmbientSoundDispatcher implements AmbientSoundDispatcher {
    private static final String TAG = "speech.DefaultAmbientSoundDispatcher:";
    private final LastUtteranceSupplier lastUtteranceSupplier;
    private final SessionSupplier sessionSupplier;

    /* renamed from: com.amazon.alexa.accessory.speech.ambient_sound.DefaultAmbientSoundDispatcher$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$speechapi$AmbientSoundDispatcher$Algorithm = new int[AmbientSoundDispatcher.Algorithm.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$speechapi$AmbientSoundDispatcher$Algorithm[AmbientSoundDispatcher.Algorithm.PASSTHROUGH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$speechapi$AmbientSoundDispatcher$Algorithm[AmbientSoundDispatcher.Algorithm.ANC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$speechapi$AmbientSoundDispatcher$Algorithm[AmbientSoundDispatcher.Algorithm.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public DefaultAmbientSoundDispatcher(SessionSupplier sessionSupplier, LastUtteranceSupplier lastUtteranceSupplier) {
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(lastUtteranceSupplier, "lastUtteranceSupplier");
        this.sessionSupplier = sessionSupplier;
        this.lastUtteranceSupplier = lastUtteranceSupplier;
    }

    @SuppressLint({"CheckResult"})
    private void dispatchToSession(final AmbientSoundDispatcher.Algorithm algorithm, final AccessorySession accessorySession) {
        Observable.fromIterable(getStatesForAlgorithm(algorithm)).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.speech.ambient_sound.-$$Lambda$DefaultAmbientSoundDispatcher$AN-o8rrpaJAg0aFB4w-lRQ1DP_k
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                CompletableSource onErrorComplete;
                onErrorComplete = AccessorySession.this.getStateRepository().set((StateOuterClass.State) obj).onErrorComplete();
                return onErrorComplete;
            }
        }).subscribe(new Action() { // from class: com.amazon.alexa.accessory.speech.ambient_sound.-$$Lambda$DefaultAmbientSoundDispatcher$uiiti7mSu8isgAILOQHRFbsaagw
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Logger.d("%s Dispatched algorithm change %s to session %s", DefaultAmbientSoundDispatcher.TAG, AmbientSoundDispatcher.Algorithm.this.name(), accessorySession.getAccessory());
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.speech.ambient_sound.-$$Lambda$DefaultAmbientSoundDispatcher$u3ZBkSZRpHIJwbmhgABS2VkSrU8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Logger.e("%s Failed to dispatch algorithm %s to session %s", (Throwable) obj, DefaultAmbientSoundDispatcher.TAG, AmbientSoundDispatcher.Algorithm.this.name(), accessorySession.getAccessory());
            }
        });
    }

    @Nullable
    private AccessorySession getSessionForUuid(String str) {
        for (AccessorySession accessorySession : this.sessionSupplier.getActiveSessions()) {
            if (accessorySession.getUuid().equals(str)) {
                return accessorySession;
            }
        }
        return null;
    }

    private List<StateOuterClass.State> getStatesForAlgorithm(AmbientSoundDispatcher.Algorithm algorithm) {
        int ordinal = algorithm.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return Collections.singletonList(StateOuterClass.State.newBuilder().setFeature(StateFeature.ACTIVE_NOISE_CANCELLATION_ENABLED.toInteger()).setBoolean(true).mo10084build());
            }
            if (ordinal == 2) {
                return Arrays.asList(StateOuterClass.State.newBuilder().setFeature(StateFeature.FEEDBACK_ACTIVE_NOISE_CANCELLATION_LEVEL.toInteger()).setInteger(0).mo10084build(), StateOuterClass.State.newBuilder().setFeature(StateFeature.ACTIVE_NOISE_CANCELLATION_ENABLED.toInteger()).setBoolean(false).mo10084build(), StateOuterClass.State.newBuilder().setFeature(StateFeature.PASSTHROUGH_ENABLED.toInteger()).setBoolean(false).mo10084build());
            }
            throw new IllegalStateException("Unexpected algorithm: " + algorithm);
        }
        return Collections.singletonList(StateOuterClass.State.newBuilder().setFeature(StateFeature.PASSTHROUGH_ENABLED.toInteger()).setBoolean(true).mo10084build());
    }

    @Override // com.amazon.alexa.accessory.speechapi.AmbientSoundDispatcher
    public boolean dispatch(@NonNull AmbientSoundDispatcher.Algorithm algorithm) {
        Preconditions.notNull(algorithm, "algorithm");
        Preconditions.mainThread();
        String uuidForLastUtterance = this.lastUtteranceSupplier.getUuidForLastUtterance();
        if (uuidForLastUtterance == null) {
            Logger.e("%s No known last accessory. Cannot dispatch algorithm", TAG);
            return false;
        }
        AccessorySession sessionForUuid = getSessionForUuid(uuidForLastUtterance);
        if (sessionForUuid == null) {
            Logger.e("%s session %s no longer connected for dispatching algorithm change", TAG, uuidForLastUtterance);
            return false;
        }
        dispatchToSession(algorithm, sessionForUuid);
        return true;
    }
}
