package com.amazon.alexa.devices.notifier.internal;

import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.AndroidAlexa;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.notifier.AlexaNotification;
import com.amazon.alexa.devices.notifier.INotifierComponent;
import com.amazon.alexa.devices.notifier.PublishResult;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
import com.amazon.alexa.devices.sdk.INotifierCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public class NotifierComponent extends AndroidAlexa.BaseProxyClient implements INotifierComponent {
    private static final String TAG = "NotifierComponent";
    private com.amazon.alexa.devices.sdk.INotifierComponent mNotifierComponent;

    public NotifierComponent() throws AlexaException {
        this(true);
    }

    private INotifierCallback convertCallbackToAidl(final com.amazon.alexa.devices.notifier.INotifierCallback iNotifierCallback) {
        return new INotifierCallback.Stub() { // from class: com.amazon.alexa.devices.notifier.internal.NotifierComponent.2
            @Override // com.amazon.alexa.devices.sdk.INotifierCallback
            public void onResult(String str) {
                try {
                    iNotifierCallback.onResult(PublishResult.valueOf(str));
                } catch (IllegalArgumentException | NullPointerException e) {
                    String str2 = NotifierComponent.TAG;
                    Log.e(str2, "Unable to parse publish result: " + str, e);
                }
            }
        };
    }

    private <N> String serialize(AlexaNotification<N> alexaNotification) {
        return new Gson().toJson(alexaNotification, new TypeToken<AlexaNotification<N>>() { // from class: com.amazon.alexa.devices.notifier.internal.NotifierComponent.1
        }.getType());
    }

    @Override // com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        this.mNotifierComponent = (com.amazon.alexa.devices.sdk.INotifierComponent) getRemoteComponent(iAlexaApiGateway, com.amazon.alexa.devices.sdk.INotifierComponent.class, version);
        if (this.mNotifierComponent == null) {
            Log.w(TAG, "Device either does not support or app is not allowed to Publish Notifications");
        }
    }

    @Override // com.amazon.alexa.devices.notifier.INotifierComponent
    public <N> void publish(AlexaNotification<N> alexaNotification) throws AlexaException {
        if (this.mNotifierComponent == null) {
            Log.w(TAG, "Device either does not support or app is not allowed to Publish Notifications");
            return;
        }
        try {
            this.mNotifierComponent.publish(serialize(alexaNotification), convertCallbackToAidl(alexaNotification.getCallback()));
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @VisibleForTesting
    public NotifierComponent(Boolean bool) throws AlexaException {
        super(bool);
    }
}
