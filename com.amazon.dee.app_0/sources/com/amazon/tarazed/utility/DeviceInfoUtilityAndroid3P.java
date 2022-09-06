package com.amazon.tarazed.utility;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.tarazed.core.notification.client.model.NotificationPlatform;
import com.amazon.tarazed.core.registry.TarazedComponentRegistry;
import com.amazon.tarazed.core.registry.component.AccountMetadataProvider;
import com.amazon.tarazed.core.type.Account;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeviceInfoUtilityAndroid3P.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007R\u0014\u0010\t\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u0014\u0010\u000b\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid3P;", "Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "is1PDevice", "", "()Z", "isAtLeastFos6", "isFireTV", "isFireTVEdition", "isTouchableDevice", "name", "", "getName", "()Ljava/lang/String;", "notificationPlatform", "Lcom/amazon/tarazed/core/notification/client/model/NotificationPlatform;", "getNotificationPlatform", "()Lcom/amazon/tarazed/core/notification/client/model/NotificationPlatform;", Constants.BUNDLE_SERIAL_NUMBER, "getSerialNumber", "softwareVersion", "getSoftwareVersion", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DeviceInfoUtilityAndroid3P extends DeviceInfoUtilityAndroid {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String DEFAULT_VALUE_UNKNOWN = "Unknown";
    private final boolean is1PDevice;
    private final boolean isAtLeastFos6;
    private final boolean isFireTV;
    private final boolean isFireTVEdition;
    private final boolean isTouchableDevice;
    @NotNull
    private final String name;
    @NotNull
    private final NotificationPlatform notificationPlatform;

    /* compiled from: DeviceInfoUtilityAndroid3P.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid3P$Companion;", "", "()V", "DEFAULT_VALUE_UNKNOWN", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceInfoUtilityAndroid3P(@NotNull Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.name = "3P";
        this.notificationPlatform = NotificationPlatform.FCM;
        this.isTouchableDevice = true;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getName() {
        return this.name;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public NotificationPlatform getNotificationPlatform() {
        return this.notificationPlatform;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getSerialNumber() {
        Account accountMetadata;
        AccountMetadataProvider accountMetadataProvider = (AccountMetadataProvider) TarazedComponentRegistry.INSTANCE.getComponent(AccountMetadataProvider.class);
        String deviceSerialNumber = (accountMetadataProvider == null || (accountMetadata = accountMetadataProvider.getAccountMetadata()) == null) ? null : accountMetadata.getDeviceSerialNumber();
        return !(deviceSerialNumber == null || deviceSerialNumber.length() == 0) ? deviceSerialNumber : "Unknown";
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getSoftwareVersion() {
        try {
            String str = getAppContext$TarazedAndroidLibrary_release().getPackageManager().getPackageInfo(getAppContext$TarazedAndroidLibrary_release().getPackageName(), 0).versionName;
            Intrinsics.checkExpressionValueIsNotNull(str, "info.versionName");
            return str;
        } catch (PackageManager.NameNotFoundException unused) {
            return "Unknown";
        }
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    public boolean is1PDevice() {
        return this.is1PDevice;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    public boolean isAtLeastFos6() {
        return this.isAtLeastFos6;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    public boolean isFireTV() {
        return this.isFireTV;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid
    public boolean isFireTVEdition() {
        return this.isFireTVEdition;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    public boolean isTouchableDevice() {
        return this.isTouchableDevice;
    }
}
