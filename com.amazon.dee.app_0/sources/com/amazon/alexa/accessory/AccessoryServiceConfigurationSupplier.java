package com.amazon.alexa.accessory;

import android.graphics.Bitmap;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public interface AccessoryServiceConfigurationSupplier {
    @NonNull
    default List<String> blockedRfcommNamePrefixes() {
        return Collections.emptyList();
    }

    @NonNull
    CharSequence getCompanionDeviceNotificationBodyText(String str, String str2);

    @ColorInt
    int getCompanionDeviceNotificationColor();

    @Nullable
    Bitmap getCompanionDeviceNotificationLargeIcon();

    @DrawableRes
    int getCompanionDeviceNotificationLargeIconRes();

    @DrawableRes
    int getCompanionDeviceNotificationSmallIconRes();

    @NonNull
    CharSequence getCompanionDeviceNotificationTitleText(String str, String str2);

    @ColorInt
    int getNotificationColor();

    @NonNull
    CharSequence getNotificationContentText(int i);

    @NonNull
    CharSequence getNotificationContentTitle();

    @Nullable
    Bitmap getNotificationLargeIcon();

    @DrawableRes
    int getNotificationLargeIconRes();

    @DrawableRes
    int getNotificationSmallIcon();

    @NonNull
    CharSequence highPriorityChannelDescription();

    String highPriorityChannelId();

    @NonNull
    CharSequence highPriorityChannelName();

    @NonNull
    CharSequence lowPriorityChannelDescription();

    String lowPriorityChannelId();

    @NonNull
    CharSequence lowPriorityChannelName();

    default boolean shouldExcludeInquirySession() {
        return false;
    }

    default boolean shouldShowHighPriorityChannelBadge() {
        return true;
    }

    default boolean shouldShowLowPriorityChannelBadge() {
        return true;
    }

    boolean showCompanionDeviceNotification();

    boolean showCompanionDeviceNotificationBeforeSetupComplete();

    int showCompanionDeviceNotificationForApiLevelAndAbove();
}
