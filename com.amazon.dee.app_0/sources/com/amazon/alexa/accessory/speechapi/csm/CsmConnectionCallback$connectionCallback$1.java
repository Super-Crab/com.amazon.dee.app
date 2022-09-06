package com.amazon.alexa.accessory.speechapi.csm;

import amazon.speech.simclient.settings.SettingsData;
import amazon.speech.simclient.settings.SettingsStatusCallback;
import android.os.Handler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmConnectionCallback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/amazon/alexa/accessory/speechapi/csm/CsmConnectionCallback$connectionCallback$1", "Lamazon/speech/simclient/settings/SettingsStatusCallback;", "onResult", "", "data", "Lamazon/speech/simclient/settings/SettingsData;", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmConnectionCallback$connectionCallback$1 implements SettingsStatusCallback {
    final /* synthetic */ CsmConnectionCallback this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CsmConnectionCallback$connectionCallback$1(CsmConnectionCallback csmConnectionCallback) {
        this.this$0 = csmConnectionCallback;
    }

    @Override // amazon.speech.simclient.settings.SettingsStatusCallback
    public void onResult(@NotNull final SettingsData data) {
        Handler handler;
        Intrinsics.checkParameterIsNotNull(data, "data");
        String name = data.getName();
        if (name != null && name.hashCode() == -579210487 && name.equals("connected")) {
            handler = this.this$0.mainHandler;
            handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speechapi.csm.CsmConnectionCallback$connectionCallback$1$onResult$1
                @Override // java.lang.Runnable
                public final void run() {
                    CsmConnectionCallback$connectionCallback$1.this.this$0.alexaConnectedStatus = data.asBoolean();
                    if (data.asBoolean()) {
                        CsmConnectionCallback$connectionCallback$1.this.this$0.notifyConnectionListeners(true);
                    }
                }
            });
        }
    }
}
