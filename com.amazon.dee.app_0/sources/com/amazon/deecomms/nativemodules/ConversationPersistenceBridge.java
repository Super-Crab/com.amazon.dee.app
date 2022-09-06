package com.amazon.deecomms.nativemodules;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.notifications.util.OfflinePushNotificationRepository;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
/* loaded from: classes12.dex */
public class ConversationPersistenceBridge extends ReactContextBaseJavaModule {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ConversationPersistenceBridge.class);
    private final OfflinePushNotificationRepository mOfflinePushNotificationRespository;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConversationPersistenceBridge(@NonNull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        OfflinePushNotificationRepository offlinePushNotificationRepository = CommsDaggerWrapper.getComponent().getOfflinePushNotificationRepository();
        this.mOfflinePushNotificationRespository = offlinePushNotificationRepository;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsConversationPersistence";
    }

    @ReactMethod
    public void getOfflinePushNotifications(Promise promise) {
        WritableArray createArray = Arguments.createArray();
        for (String str : this.mOfflinePushNotificationRespository.getAndDeleteNotifications(getReactApplicationContext())) {
            createArray.pushString(str);
        }
        promise.resolve(createArray);
    }

    public ConversationPersistenceBridge(@NonNull ReactApplicationContext reactApplicationContext, @NonNull OfflinePushNotificationRepository offlinePushNotificationRepository) {
        super(reactApplicationContext);
        this.mOfflinePushNotificationRespository = offlinePushNotificationRepository;
    }
}
