package com.amazon.alexa.redesign.entity;

import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class AlertBannerModel extends CardModel {
    public static final String ALERT_BANNER = "AlertBanner";
    @SerializedName("alertMessage")
    private String alertMessage;

    public String getAlertMessage() {
        return this.alertMessage;
    }

    public boolean isEqual(@Nullable AlertBannerModel alertBannerModel) {
        if (alertBannerModel != null) {
            return this.alertMessage.equals(alertBannerModel.getAlertMessage());
        }
        return false;
    }

    public void setAlertMessage(String str) {
        this.alertMessage = str;
    }
}
