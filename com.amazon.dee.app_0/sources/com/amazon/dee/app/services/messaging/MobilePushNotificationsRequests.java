package com.amazon.dee.app.services.messaging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes12.dex */
final class MobilePushNotificationsRequests {
    private static final String REGISTER_DEVICE_ID_KEY = "deviceId";
    private static final String REGISTER_DEVICE_TOKEN_KEY = "deviceToken";
    private static final String REGISTER_ENCRYPTION_PUBLIC_KEY_KEY = "publicKey";
    private static final String REGISTER_ENCRYPTION_STANDARD_KEY = "standard";
    private static final String REGISTER_MESSAGE_ENCRYPTION_KEY = "messageEncryption";
    private static final String REGISTER_PERSON_ID_KEY = "personId";
    private static final String REGISTER_PLATFORM_KEY = "platform";
    private static final String SET_PERMISSION_DEVICE_ID_KEY = "deviceId";
    private static final String SET_PERMISSION_HAS_ACCESS_KEY = "hasAccess";
    private static final String SET_PERMISSION_NOTIFICATION_TYPE_KEY = "notificationType";
    private static final String UNREGISTER_DEVICE_ID_KEY = "deviceId";
    private static final String UNREGISTER_PLATFORM_KEY = "platform";

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    @interface EncryptionStandard {
        public static final String RSA_2048 = "RSA_2048";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    @interface PushNotificationPlatform {
        public static final String ADM = "ADM";
        public static final String FIREBASE = "FIREBASE";
    }

    private MobilePushNotificationsRequests() {
        throw new UnsupportedOperationException("Instantiation of this utility class is not supported.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JsonObject getRegistrationData(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @Nullable String str5) throws IllegalArgumentException, NullPointerException {
        Preconditions.checkNotNull(str, "dsn argument must not null.");
        Preconditions.checkNotNull(str2, "platform argument must not be null.");
        Preconditions.checkNotNull(str3, "platformToken argument must not be null.");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(MetricsConfiguration.PLATFORM, str2);
        jsonObject.addProperty("deviceId", str);
        jsonObject.addProperty(REGISTER_DEVICE_TOKEN_KEY, str3);
        if (str4 != null) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty(REGISTER_ENCRYPTION_STANDARD_KEY, EncryptionStandard.RSA_2048);
            jsonObject2.addProperty(REGISTER_ENCRYPTION_PUBLIC_KEY_KEY, str4);
            jsonObject.add(REGISTER_MESSAGE_ENCRYPTION_KEY, jsonObject2);
        }
        if (str5 != null) {
            jsonObject.addProperty("personId", str5);
        }
        return jsonObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JsonObject getSetPermissionData(@NonNull String str, @NonNull String str2, boolean z) {
        Preconditions.checkNotNull(str, "dsn argument must not null.");
        Preconditions.checkNotNull(str2, "notificationType argument must not null.");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceId", str);
        jsonObject.addProperty("notificationType", str2);
        jsonObject.addProperty("hasAccess", Boolean.valueOf(z));
        return jsonObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JsonObject getUnregistrationData(@NonNull String str, @NonNull String str2) throws NullPointerException {
        Preconditions.checkNotNull(str, "dsn argument must not be null.");
        Preconditions.checkNotNull(str2, "platform argument must not be null.");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(MetricsConfiguration.PLATFORM, str2);
        jsonObject.addProperty("deviceId", str);
        return jsonObject;
    }
}
