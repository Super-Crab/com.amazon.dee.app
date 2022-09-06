package com.amazon.tarazed.core.utility;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.deecomms.common.Constants;
import com.amazon.device.messaging.ADMConstants;
import com.amazon.tarazed.core.notification.client.model.NotificationPlatform;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeviceInfoUtility.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0012\u0010\u000b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u0012\u0010\f\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0012\u0010\r\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\nR\u0012\u0010\u000e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0012\u0010\u0010\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0012\u0010\u0012\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0012\u0010\u0014\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0018\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0012\u0010\u001a\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u0012\u0010\u001c\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0005R\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0005R\u0012\u0010 \u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0005¨\u0006\""}, d2 = {"Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "", ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT, "", "getApp", "()Ljava/lang/String;", MetricsConfiguration.DEVICE_LANGUAGE, "getDeviceLanguage", "is1PDevice", "", "()Z", "isAtLeastFos6", "isFireTV", "isTouchableDevice", "manufacturer", "getManufacturer", "model", "getModel", "name", "getName", "notificationPlatform", "Lcom/amazon/tarazed/core/notification/client/model/NotificationPlatform;", "getNotificationPlatform", "()Lcom/amazon/tarazed/core/notification/client/model/NotificationPlatform;", "operatingSystemName", "getOperatingSystemName", "operatingSystemVersion", "getOperatingSystemVersion", Constants.BUNDLE_SERIAL_NUMBER, "getSerialNumber", "softwareVersion", "getSoftwareVersion", "tarazedVersion", "getTarazedVersion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface DeviceInfoUtility {
    @NotNull
    String getApp();

    @NotNull
    String getDeviceLanguage();

    @NotNull
    String getManufacturer();

    @NotNull
    String getModel();

    @NotNull
    String getName();

    @NotNull
    NotificationPlatform getNotificationPlatform();

    @NotNull
    String getOperatingSystemName();

    @NotNull
    String getOperatingSystemVersion();

    @NotNull
    String getSerialNumber();

    @Nullable
    String getSoftwareVersion();

    @NotNull
    String getTarazedVersion();

    boolean is1PDevice();

    boolean isAtLeastFos6();

    boolean isFireTV();

    boolean isTouchableDevice();
}
