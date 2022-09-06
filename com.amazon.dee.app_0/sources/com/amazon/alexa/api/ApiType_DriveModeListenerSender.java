package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.ApiType_DriveModeListenerArgumentType;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
class ApiType_DriveModeListenerSender extends AlexaMessageSender<ApiType_DriveModeListenerMessageType> implements AlexaDriveModeListener {
    private static final String TAG = "ApiType_DriveModeListenerSender";
    private final ExtendedClient client;
    private final MessageReceiversManager messageReceiversManager;

    /* loaded from: classes6.dex */
    private static class OnDriveModeEnabled_booleanPayload extends BaseMessagePayload {
        OnDriveModeEnabled_booleanPayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(ApiType_DriveModeListenerArgumentType.OnDriveModeEnabled_booleanArgumentType.ENABLED, z);
        }
    }

    /* loaded from: classes6.dex */
    private static class OnDriveModeState_com_amazon_alexa_api_DriveModeStatePayload extends BaseMessagePayload {
        OnDriveModeState_com_amazon_alexa_api_DriveModeStatePayload(ExtendedClient extendedClient, DriveModeState driveModeState) {
            super(extendedClient);
            add((Bundles.Key) ApiType_DriveModeListenerArgumentType.OnDriveModeState_com_amazon_alexa_api_DriveModeStateArgumentType.STATE, BundleTransformer.getDefaultInstance().toBundle(driveModeState));
        }
    }

    /* loaded from: classes6.dex */
    private static class OnDriveModeThemeChanged_booleanPayload extends BaseMessagePayload {
        OnDriveModeThemeChanged_booleanPayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(ApiType_DriveModeListenerArgumentType.OnDriveModeThemeChanged_booleanArgumentType.IS_DARK_THEME, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_DriveModeListenerSender(ExtendedClient extendedClient, IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder);
        this.client = extendedClient;
        this.messageReceiversManager = messageReceiversManager;
    }

    @Override // com.amazon.alexa.api.AlexaDriveModeListener
    public void onDriveModeEnabled(boolean z) {
        try {
            sendMessage(ApiType_DriveModeListenerMessageType.ON_DRIVE_MODE_ENABLED_BOOLEAN, new OnDriveModeEnabled_booleanPayload(this.client, z).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onDriveModeEnabled_boolean", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaDriveModeListener
    public void onDriveModeState(DriveModeState driveModeState) {
        try {
            sendMessage(ApiType_DriveModeListenerMessageType.ON_DRIVE_MODE_STATE_COM_AMAZON_ALEXA_API_DRIVE_MODE_STATE, new OnDriveModeState_com_amazon_alexa_api_DriveModeStatePayload(this.client, driveModeState).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onDriveModeState_com_amazon_alexa_api_DriveModeState", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaDriveModeListener
    public void onDriveModeThemeChanged(boolean z) {
        try {
            sendMessage(ApiType_DriveModeListenerMessageType.ON_DRIVE_MODE_THEME_CHANGED_BOOLEAN, new OnDriveModeThemeChanged_booleanPayload(this.client, z).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onDriveModeThemeChanged_boolean", e);
        }
    }
}
