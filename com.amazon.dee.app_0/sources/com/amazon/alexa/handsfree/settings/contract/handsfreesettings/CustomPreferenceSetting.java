package com.amazon.alexa.handsfree.settings.contract.handsfreesettings;

import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.vendor.VendorSettingContract;
/* loaded from: classes8.dex */
public interface CustomPreferenceSetting {
    @Nullable
    String getMainSectionTitle();

    @Nullable
    VendorSettingContract getVendorSettings();

    boolean isLegalNoticeNeeded();

    boolean isShowOnLockScreenAvailable();

    boolean isUVRAvailable();
}
