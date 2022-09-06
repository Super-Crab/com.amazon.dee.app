package com.amazon.alexa.handsfree.uservoicerecognition.ui;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.magiear.handsfree.util.Common;
/* loaded from: classes8.dex */
public enum PartnerDisclaimerProvider {
    QUEBEC("com.quicinc.voice.activation", "Qualcomm", R.string.quebec_disclaimer_uri),
    METRO(Common.HANDSFREE_ASSISTANT_PACKAGE_NAME, "MediaTek", R.string.metro_disclaimer_uri);
    
    @StringRes
    private final int mDisclaimerUriResId;
    private final String mPartnerName;
    private final String mVoiceAppPackageName;

    PartnerDisclaimerProvider(@NonNull String str, @NonNull String str2, @StringRes int i) {
        this.mVoiceAppPackageName = str;
        this.mPartnerName = str2;
        this.mDisclaimerUriResId = i;
    }

    @StringRes
    public int getDisclaimerUriID() {
        return this.mDisclaimerUriResId;
    }

    @NonNull
    public String getPartnerName() {
        return this.mPartnerName;
    }

    @NonNull
    public String getVoiceAppPackageName() {
        return this.mVoiceAppPackageName;
    }
}
