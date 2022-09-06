package com.amazon.alexa.accessory.speechapi.csm;

import amazon.speech.simclient.settings.Settings;
import amazon.speech.simclient.settings.SettingsData;
import amazon.speech.simclient.settings.SettingsStatusCallback;
import android.os.Handler;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.listeners.StateListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmAlexaConnection.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/amazon/alexa/accessory/speechapi/csm/CsmAlexaConnection$registerStateListener$callback$1", "Lamazon/speech/simclient/settings/SettingsStatusCallback;", "onResult", "", "data", "Lamazon/speech/simclient/settings/SettingsData;", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmAlexaConnection$registerStateListener$callback$1 implements SettingsStatusCallback {
    final /* synthetic */ StateListener $listener;
    final /* synthetic */ CsmAlexaConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CsmAlexaConnection$registerStateListener$callback$1(CsmAlexaConnection csmAlexaConnection, StateListener stateListener) {
        this.this$0 = csmAlexaConnection;
        this.$listener = stateListener;
    }

    @Override // amazon.speech.simclient.settings.SettingsStatusCallback
    public void onResult(@NotNull final SettingsData data) {
        Handler handler;
        Intrinsics.checkParameterIsNotNull(data, "data");
        handler = this.this$0.handler;
        handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speechapi.csm.CsmAlexaConnection$registerStateListener$callback$1$onResult$1
            @Override // java.lang.Runnable
            public final void run() {
                String name = data.getName();
                if (name != null) {
                    switch (name.hashCode()) {
                        case -2134659376:
                            if (name.equals(Settings.SPEAKING)) {
                                if (!data.asBoolean()) {
                                    return;
                                }
                                CsmAlexaConnection$registerStateListener$callback$1.this.$listener.onAlexaStateChanged(StateListener.AlexaState.SPEAKING);
                                return;
                            }
                            break;
                        case -1218715461:
                            if (name.equals(Settings.LISTENING)) {
                                if (!data.asBoolean()) {
                                    return;
                                }
                                CsmAlexaConnection$registerStateListener$callback$1.this.$listener.onAlexaStateChanged(StateListener.AlexaState.LISTENING);
                                return;
                            }
                            break;
                        case 1224578480:
                            if (name.equals(Settings.THINKING)) {
                                if (!data.asBoolean()) {
                                    return;
                                }
                                CsmAlexaConnection$registerStateListener$callback$1.this.$listener.onAlexaStateChanged(StateListener.AlexaState.THINKING);
                                return;
                            }
                            break;
                        case 1844104684:
                            if (name.equals(Settings.INTERACTING)) {
                                if (data.asBoolean()) {
                                    return;
                                }
                                CsmAlexaConnection$registerStateListener$callback$1.this.$listener.onAlexaStateChanged(StateListener.AlexaState.IDLE);
                                return;
                            }
                            break;
                    }
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmAlexaConnection: Unhandled callback for state ");
                outline107.append(data.getName());
                Logger.d(outline107.toString());
            }
        });
    }
}
