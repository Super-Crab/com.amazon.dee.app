package com.amazon.alexa.voice.handsfree.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.decider.HandsFreeSetupDecider;
import com.amazon.alexa.voice.handsfree.decider.HandsFreeSetupDeciderProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes11.dex */
public class WakeWordStateValidator {
    private static final String TAG = "WakeWordStateValidator";
    private final HandsFreeSetupDecider mHandsFreeSetupDecider;
    private final UVRConnector mUVRConnector;
    private final WakeWordSettingsManager mWakeWordSettingsManager;

    @Inject
    public WakeWordStateValidator(@NonNull Context context) {
        this(HandsFreeSetupDeciderProvider.getInstance().getHandsFreeSetupDecider(context), WakeWordSettingsManagerProvider.getInstance().get(), UVRModule.INSTANCE.isUVRAvailable() ? UVRModule.INSTANCE.getUVRContract().getUVRConnector() : null);
    }

    public void validateWakeWordState(@NonNull Context context) {
        Log.d(TAG, "Validating wake word state");
        UVRConnector uVRConnector = this.mUVRConnector;
        if (uVRConnector != null) {
            uVRConnector.startConnection(context, false);
        }
        if (!this.mHandsFreeSetupDecider.canEnableWakeWord()) {
            Log.d(TAG, "Cannot enable wake word! Disabling.");
            this.mWakeWordSettingsManager.setHandsFreeState(false, new ResponseCallback() { // from class: com.amazon.alexa.voice.handsfree.utils.WakeWordStateValidator.1
                @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                    String str = WakeWordStateValidator.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setHandsFreeState onError: ");
                    outline107.append(callbackErrorMetadata.getErrorMessage());
                    Log.e(str, outline107.toString());
                }

                @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                public void onSuccess() {
                    Log.d(WakeWordStateValidator.TAG, "Wake word detection successfully disabled.");
                }
            }, TAG);
        }
        UVRConnector uVRConnector2 = this.mUVRConnector;
        if (uVRConnector2 != null) {
            uVRConnector2.endConnection(context);
        }
    }

    @VisibleForTesting
    WakeWordStateValidator(@NonNull HandsFreeSetupDecider handsFreeSetupDecider, @NonNull WakeWordSettingsManager wakeWordSettingsManager, @Nullable UVRConnector uVRConnector) {
        this.mHandsFreeSetupDecider = handsFreeSetupDecider;
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
        this.mUVRConnector = uVRConnector;
    }
}
