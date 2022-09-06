package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.ApiType_DriveModeListenerArgumentType;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
final class ApiType_DriveModeListenerProcessor extends MessageProcessor<ApiType_DriveModeListenerMessageType> {
    private final ApiType_DriveModeListenerWrapper apiType_DriveModeListenerWrapper;

    /* renamed from: com.amazon.alexa.api.ApiType_DriveModeListenerProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$ApiType_DriveModeListenerMessageType = new int[ApiType_DriveModeListenerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_DriveModeListenerMessageType[ApiType_DriveModeListenerMessageType.ON_DRIVE_MODE_ENABLED_BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_DriveModeListenerMessageType[ApiType_DriveModeListenerMessageType.ON_DRIVE_MODE_STATE_COM_AMAZON_ALEXA_API_DRIVE_MODE_STATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiType_DriveModeListenerMessageType[ApiType_DriveModeListenerMessageType.ON_DRIVE_MODE_THEME_CHANGED_BOOLEAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_DriveModeListenerProcessor(AlexaDriveModeListener alexaDriveModeListener) {
        this.apiType_DriveModeListenerWrapper = new ApiType_DriveModeListenerWrapper(alexaDriveModeListener);
    }

    private void processOnDriveModeEnabled_boolean(Bundle bundle) {
        this.apiType_DriveModeListenerWrapper.onDriveModeEnabled(Bundles.getBoolean(bundle, ApiType_DriveModeListenerArgumentType.OnDriveModeEnabled_booleanArgumentType.ENABLED));
    }

    private void processOnDriveModeState_com_amazon_alexa_api_DriveModeState(Bundle bundle) {
        DriveModeState driveModeState = (DriveModeState) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, ApiType_DriveModeListenerArgumentType.OnDriveModeState_com_amazon_alexa_api_DriveModeStateArgumentType.STATE), DriveModeState.class);
        Preconditions.notNull(driveModeState, "state cannot be null");
        this.apiType_DriveModeListenerWrapper.onDriveModeState(driveModeState);
    }

    private void processOnDriveModeThemeChanged_boolean(Bundle bundle) {
        this.apiType_DriveModeListenerWrapper.onDriveModeThemeChanged(Bundles.getBoolean(bundle, ApiType_DriveModeListenerArgumentType.OnDriveModeThemeChanged_booleanArgumentType.IS_DARK_THEME));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public ApiType_DriveModeListenerMessageType mo845getMessageType(Message message) {
        try {
            return ApiType_DriveModeListenerMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException unused) {
            return ApiType_DriveModeListenerMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(ApiType_DriveModeListenerMessageType apiType_DriveModeListenerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        Bundles.getClient(bundle);
        int ordinal = apiType_DriveModeListenerMessageType.ordinal();
        if (ordinal == 1) {
            processOnDriveModeEnabled_boolean(bundle);
        } else if (ordinal == 2) {
            processOnDriveModeState_com_amazon_alexa_api_DriveModeState(bundle);
        } else if (ordinal == 3) {
            processOnDriveModeThemeChanged_boolean(bundle);
        } else {
            String simpleName = ApiType_DriveModeListenerProcessor.class.getSimpleName();
            Log.w(simpleName, "Unsupported message: " + apiType_DriveModeListenerMessageType);
        }
    }
}
