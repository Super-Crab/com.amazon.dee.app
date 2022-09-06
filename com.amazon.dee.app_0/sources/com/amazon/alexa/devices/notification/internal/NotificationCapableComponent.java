package com.amazon.alexa.devices.notification.internal;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.AndroidAlexa;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.notification.INotificationComponent;
import com.amazon.alexa.devices.notification.NotificationSubscription;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
import com.amazon.alexa.devices.sdk.INotificationCallback;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public abstract class NotificationCapableComponent extends AndroidAlexa.BaseProxyClient implements INotificationComponent {
    private static final String TAG = "NotificationCapableComponent";
    private final Map<NotificationSubscription, INotificationCallback> mAidlCallbackRegistry = new HashMap();
    private com.amazon.alexa.devices.sdk.INotificationComponent mNotificationComponent;

    private <N> INotificationCallback convertCallbackToAidl(final Class<N> cls, final com.amazon.alexa.devices.notification.INotificationCallback<N> iNotificationCallback) {
        return new INotificationCallback.Stub() { // from class: com.amazon.alexa.devices.notification.internal.NotificationCapableComponent.2
            @Override // com.amazon.alexa.devices.sdk.INotificationCallback
            public void onHandleNotification(String str) {
                iNotificationCallback.onHandleNotification(new Gson().fromJson(str, (Class<Object>) cls));
            }
        };
    }

    private <N> void doSubscribe(NotificationSubscription<N> notificationSubscription, INotificationCallback iNotificationCallback) throws RemoteException {
        this.mNotificationComponent.subscribe(serialize(notificationSubscription), iNotificationCallback);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Subscribed to: ");
        outline107.append(notificationSubscription.getClassName());
        Log.i(str, outline107.toString());
    }

    private <N> String serialize(NotificationSubscription<N> notificationSubscription) {
        return new Gson().toJson(notificationSubscription, new TypeToken<NotificationSubscription<N>>() { // from class: com.amazon.alexa.devices.notification.internal.NotificationCapableComponent.1
        }.getType());
    }

    @Override // com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        this.mNotificationComponent = (com.amazon.alexa.devices.sdk.INotificationComponent) getRemoteComponent(iAlexaApiGateway, com.amazon.alexa.devices.sdk.INotificationComponent.class, version);
        if (this.mNotificationComponent == null) {
            Log.w(TAG, "Device does not support Notification Subscription");
            return;
        }
        Map<NotificationSubscription, INotificationCallback> map = this.mAidlCallbackRegistry;
        if (map == null) {
            return;
        }
        for (Map.Entry<NotificationSubscription, INotificationCallback> entry : map.entrySet()) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Resubscribing Notification callback ");
            outline107.append(entry.getKey().getClassName());
            Log.i(str, outline107.toString());
            NotificationSubscription key = entry.getKey();
            INotificationCallback convertCallbackToAidl = convertCallbackToAidl(key.getNotificationClass(), key.getCallback());
            entry.setValue(convertCallbackToAidl);
            doSubscribe(entry.getKey(), convertCallbackToAidl);
        }
    }

    @Override // com.amazon.alexa.devices.notification.INotificationComponent
    public <N> void subscribe(NotificationSubscription<N> notificationSubscription) throws AlexaException {
        if (this.mNotificationComponent == null) {
            Log.w(TAG, "Cannot subscribe - Device does not support Notification Subscription");
            return;
        }
        try {
            INotificationCallback convertCallbackToAidl = convertCallbackToAidl(notificationSubscription.getNotificationClass(), notificationSubscription.getCallback());
            this.mAidlCallbackRegistry.put(notificationSubscription, convertCallbackToAidl);
            doSubscribe(notificationSubscription, convertCallbackToAidl);
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.notification.INotificationComponent
    public <N> void unsubscribe(NotificationSubscription<N> notificationSubscription) throws AlexaException {
        if (this.mNotificationComponent == null) {
            Log.w(TAG, "Cannot unsubscribe - Device does not support Notification Subscription");
            return;
        }
        try {
            com.amazon.alexa.devices.notification.INotificationCallback<N> callback = notificationSubscription.getCallback();
            INotificationCallback iNotificationCallback = null;
            if (callback != null) {
                iNotificationCallback = this.mAidlCallbackRegistry.get(notificationSubscription);
            }
            this.mNotificationComponent.unsubscribe(serialize(notificationSubscription), iNotificationCallback);
            if (callback != null && iNotificationCallback != null) {
                this.mAidlCallbackRegistry.remove(callback);
            }
            String str = TAG;
            Log.i(str, "Unsubscribed from: " + notificationSubscription.getClassName());
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }
}
