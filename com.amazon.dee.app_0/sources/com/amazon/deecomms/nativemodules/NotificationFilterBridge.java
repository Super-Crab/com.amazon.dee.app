package com.amazon.deecomms.nativemodules;

import androidx.annotation.NonNull;
import com.amazon.deecomms.util.Consumer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes12.dex */
public class NotificationFilterBridge extends ReactContextBaseJavaModule {
    public NotificationFilterBridge(@NonNull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private String getRequiredString(@NonNull ReadableMap readableMap, @NonNull String str) {
        if (readableMap.hasKey(str)) {
            return readableMap.getString(str);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Cannot find key '", str, "' in the map."));
    }

    private void handleNotificationFilter(@NonNull ReadableMap readableMap, @NonNull Promise promise, @NonNull Consumer<String> consumer) {
        try {
            String requiredString = getRequiredString(readableMap, "type");
            if ("ConversationId".equals(requiredString)) {
                consumer.accept(getRequiredString(readableMap, "conversationId"));
                promise.resolve(null);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("NotificationFilterBridge: Invalid conversation type ");
            sb.append(requiredString);
            throw new IllegalArgumentException(sb.toString());
        } catch (IllegalArgumentException e) {
            promise.reject(e);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsNotificationFilter";
    }

    @ReactMethod
    public void resumeNotifications(@NonNull ReadableMap readableMap, @NonNull Promise promise) {
        handleNotificationFilter(readableMap, promise, $$Lambda$NotificationFilterBridge$VxVkRxma_fYUscBBWS5HqCcr6UQ.INSTANCE);
    }

    @ReactMethod
    public void suppressNotifications(@NonNull ReadableMap readableMap, @NonNull Promise promise) {
        handleNotificationFilter(readableMap, promise, $$Lambda$NotificationFilterBridge$N5yekZjBghZ5v1Dqev0zhaPWeY.INSTANCE);
    }
}
