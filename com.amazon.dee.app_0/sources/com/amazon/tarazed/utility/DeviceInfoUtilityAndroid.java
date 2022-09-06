package com.amazon.tarazed.utility;

import android.content.Context;
import android.os.Build;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.deecomms.common.Constants;
import com.amazon.device.messaging.ADMConstants;
import com.amazon.tarazed.BuildConfig;
import com.amazon.tarazed.core.notification.client.model.NotificationPlatform;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeviceInfoUtilityAndroid.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000e\b&\u0018\u0000 -2\u00020\u0001:\u0001-B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R\u0012\u0010\u0015\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0014R\u0012\u0010\u0016\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0012\u0010\u0017\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0014R\u0012\u0010\u0018\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014R\u0014\u0010\u0019\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\fR\u0014\u0010\u001b\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\fR\u0012\u0010\u001d\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\fR\u0012\u0010\u001f\u001a\u00020 X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\fR\u0014\u0010%\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\fR\u0012\u0010'\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\fR\u0012\u0010)\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\fR\u0014\u0010+\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\f¨\u0006."}, d2 = {"Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "apiLevel", "", "getApiLevel", "()I", ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT, "", "getApp", "()Ljava/lang/String;", "appContext", "getAppContext$TarazedAndroidLibrary_release", "()Landroid/content/Context;", MetricsConfiguration.DEVICE_LANGUAGE, "getDeviceLanguage", "is1PDevice", "", "()Z", "isAtLeastFos6", "isFireTV", "isFireTVEdition", "isTouchableDevice", "manufacturer", "getManufacturer", "model", "getModel", "name", "getName", "notificationPlatform", "Lcom/amazon/tarazed/core/notification/client/model/NotificationPlatform;", "getNotificationPlatform", "()Lcom/amazon/tarazed/core/notification/client/model/NotificationPlatform;", "operatingSystemName", "getOperatingSystemName", "operatingSystemVersion", "getOperatingSystemVersion", Constants.BUNDLE_SERIAL_NUMBER, "getSerialNumber", "softwareVersion", "getSoftwareVersion", "tarazedVersion", "getTarazedVersion", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class DeviceInfoUtilityAndroid implements DeviceInfoUtility {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String OPERATING_SYSTEM = "Android";
    @NotNull
    private final Context appContext;
    @NotNull
    private final String operatingSystemName;

    /* compiled from: DeviceInfoUtilityAndroid.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid$Companion;", "", "()V", "OPERATING_SYSTEM", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DeviceInfoUtilityAndroid(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        this.appContext = applicationContext;
        this.operatingSystemName = "Android";
    }

    public final int getApiLevel() {
        return Build.VERSION.SDK_INT;
    }

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getApp() {
        String packageName = this.appContext.getPackageName();
        Intrinsics.checkExpressionValueIsNotNull(packageName, "appContext.packageName");
        return packageName;
    }

    @NotNull
    public final Context getAppContext$TarazedAndroidLibrary_release() {
        return this.appContext;
    }

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getDeviceLanguage() {
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        String language = locale.getLanguage();
        Intrinsics.checkExpressionValueIsNotNull(language, "Locale.getDefault().language");
        return language;
    }

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getManufacturer() {
        String str = Build.MANUFACTURER;
        Intrinsics.checkExpressionValueIsNotNull(str, "Build.MANUFACTURER");
        return str;
    }

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getModel() {
        String str = Build.MODEL;
        Intrinsics.checkExpressionValueIsNotNull(str, "Build.MODEL");
        return str;
    }

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public abstract String getName();

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public abstract NotificationPlatform getNotificationPlatform();

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getOperatingSystemName() {
        return this.operatingSystemName;
    }

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getOperatingSystemVersion() {
        String str = Build.VERSION.RELEASE;
        Intrinsics.checkExpressionValueIsNotNull(str, "Build.VERSION.RELEASE");
        return str;
    }

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public abstract String getSerialNumber();

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public abstract String getSoftwareVersion();

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    @NotNull
    public String getTarazedVersion() {
        return BuildConfig.VERSION_NAME;
    }

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    public abstract boolean is1PDevice();

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    public abstract boolean isAtLeastFos6();

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    public abstract boolean isFireTV();

    public abstract boolean isFireTVEdition();

    @Override // com.amazon.tarazed.core.utility.DeviceInfoUtility
    public abstract boolean isTouchableDevice();
}
