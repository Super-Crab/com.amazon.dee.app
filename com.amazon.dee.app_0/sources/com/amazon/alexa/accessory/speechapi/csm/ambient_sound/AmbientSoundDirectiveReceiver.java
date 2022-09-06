package com.amazon.alexa.accessory.speechapi.csm.ambient_sound;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* compiled from: AmbientSoundDirectiveReceiver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/ambient_sound/AmbientSoundDirectiveReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AmbientSoundDirectiveReceiver extends BroadcastReceiver {
    private static final String AMBIENT_SOUND_PROCESSING_MODE_INTENT_ACTION = "com.amazon.speech.Alexa.AudioSignal.ActiveNoiseControl_SetAmbientSoundProcessingMode";
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AmbientSoundDirectiveReceiver:";

    /* compiled from: AmbientSoundDirectiveReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/ambient_sound/AmbientSoundDirectiveReceiver$Companion;", "", "()V", "AMBIENT_SOUND_PROCESSING_MODE_INTENT_ACTION", "", "TAG", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        Preconditions.mainThread();
        Logger.d("AmbientSoundDirectiveReceiver: onReceive for intent: " + intent);
        if (!Intrinsics.areEqual(AMBIENT_SOUND_PROCESSING_MODE_INTENT_ACTION, intent != null ? intent.getAction() : null)) {
            Logger.e("AmbientSoundDirectiveReceiver: Unexpected intent received " + intent);
            return;
        }
        AmbientSoundDirectiveProcessor.Companion.handleIntent(intent);
    }
}
