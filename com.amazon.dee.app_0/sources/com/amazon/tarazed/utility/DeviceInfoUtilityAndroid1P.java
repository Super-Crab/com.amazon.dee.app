package com.amazon.tarazed.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.amazon.deecomms.common.Constants;
import com.amazon.device.messaging.ADMConstants;
import com.amazon.tarazed.core.notification.client.model.NotificationPlatform;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeviceInfoUtilityAndroid1P.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000bR\u0014\u0010\f\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000bR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\bR\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\bR\u0014\u0010\u0018\u001a\u00020\u00068WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\bR\u0014\u0010\u001a\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\b¨\u0006\u001d"}, d2 = {"Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid1P;", "Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT, "", "getApp", "()Ljava/lang/String;", "is1PDevice", "", "()Z", "isAtLeastFos6", "isFireTV", "isFireTVEdition", "isTouchableDevice", "name", "getName", "notificationPlatform", "Lcom/amazon/tarazed/core/notification/client/model/NotificationPlatform;", "getNotificationPlatform", "()Lcom/amazon/tarazed/core/notification/client/model/NotificationPlatform;", "operatingSystemName", "getOperatingSystemName", Constants.BUNDLE_SERIAL_NUMBER, "getSerialNumber", "softwareVersion", "getSoftwareVersion", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DeviceInfoUtilityAndroid1P extends DeviceInfoUtilityAndroid {
    private static final String AMAZON_FEATURE_FIRE_TV = "amazon.hardware.fire_tv";
    private static final String AMAZON_FEATURE_TV_SCREEN = "com.amazon.hardware.tv_screen";
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String OPERATING_SYSTEM = "FireOS";
    private final boolean is1PDevice;

    /* compiled from: DeviceInfoUtilityAndroid1P.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid1P$Companion;", "", "()V", "AMAZON_FEATURE_FIRE_TV", "", "AMAZON_FEATURE_TV_SCREEN", "OPERATING_SYSTEM", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceInfoUtilityAndroid1P(@NotNull Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.is1PDevice = true;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getApp() {
        String packageName = getAppContext$TarazedAndroidLibrary_release().getPackageName();
        Intrinsics.checkExpressionValueIsNotNull(packageName, "appContext.packageName");
        return packageName;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getName() {
        String str = Build.DEVICE;
        Intrinsics.checkExpressionValueIsNotNull(str, "Build.DEVICE");
        return str;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public NotificationPlatform getNotificationPlatform() {
        return NotificationPlatform.ADM;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getOperatingSystemName() {
        return "FireOS";
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    @SuppressLint({"HardwareIds"})
    @NotNull
    public String getSerialNumber() {
        String str = Build.SERIAL;
        Intrinsics.checkExpressionValueIsNotNull(str, "Build.SERIAL");
        return str;
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
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    public boolean isFireTV() {
        return !isTouchableDevice();
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid
    public boolean isFireTVEdition() {
        return getAppContext$TarazedAndroidLibrary_release().getPackageManager().hasSystemFeature(AMAZON_FEATURE_TV_SCREEN);
    }

    @Override // com.amazon.tarazed.utility.DeviceInfoUtilityAndroid, com.amazon.tarazed.core.utility.DeviceInfoUtility
    public boolean isTouchableDevice() {
        return !getAppContext$TarazedAndroidLibrary_release().getPackageManager().hasSystemFeature(AMAZON_FEATURE_FIRE_TV);
    }
}
