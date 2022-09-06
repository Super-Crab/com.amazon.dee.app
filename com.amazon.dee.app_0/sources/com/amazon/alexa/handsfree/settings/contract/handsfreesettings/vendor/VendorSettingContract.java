package com.amazon.alexa.handsfree.settings.contract.handsfreesettings.vendor;

import android.content.Intent;
import androidx.annotation.Nullable;
/* loaded from: classes8.dex */
public interface VendorSettingContract {
    @Nullable
    Intent getCustomSettingsIntent();

    @Nullable
    String getVendorSettingsSummary();

    @Nullable
    String getVendorSettingsTitle();
}
