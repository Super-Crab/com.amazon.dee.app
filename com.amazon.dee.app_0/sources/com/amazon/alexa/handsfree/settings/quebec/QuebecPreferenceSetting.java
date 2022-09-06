package com.amazon.alexa.handsfree.settings.quebec;

import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.CustomPreferenceSetting;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.vendor.VendorSettingContract;
/* loaded from: classes8.dex */
public class QuebecPreferenceSetting implements CustomPreferenceSetting {
    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.CustomPreferenceSetting
    @Nullable
    public String getMainSectionTitle() {
        return null;
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.CustomPreferenceSetting
    @Nullable
    public VendorSettingContract getVendorSettings() {
        return null;
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.CustomPreferenceSetting
    public boolean isLegalNoticeNeeded() {
        return false;
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.CustomPreferenceSetting
    public boolean isShowOnLockScreenAvailable() {
        return UVRModule.INSTANCE.isUVRAvailable();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.CustomPreferenceSetting
    public boolean isUVRAvailable() {
        return UVRModule.INSTANCE.isUVRAvailable();
    }
}
