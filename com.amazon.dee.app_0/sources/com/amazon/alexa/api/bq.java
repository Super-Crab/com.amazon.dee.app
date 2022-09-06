package com.amazon.alexa.api;

import android.os.RemoteException;
import com.amazon.alexa.api.AlexaStateListenerProxy;
import com.amazon.alexa.utils.ApiThreadHelper;
/* loaded from: classes6.dex */
class bq extends AlexaStateListenerProxy.Stub {
    private final AlexaStateListener a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bq(AlexaStateListener alexaStateListener) {
        this.a = alexaStateListener;
    }

    @Override // com.amazon.alexa.api.AlexaStateListenerProxy
    public void onAlexaStateChanged(final AlexaState alexaState) throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.bq.1
            @Override // java.lang.Runnable
            public void run() {
                bq.this.a.onAlexaStateChanged(alexaState);
            }
        });
    }
}
