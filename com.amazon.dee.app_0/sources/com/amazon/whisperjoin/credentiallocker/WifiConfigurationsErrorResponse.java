package com.amazon.whisperjoin.credentiallocker;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
class WifiConfigurationsErrorResponse {
    private String message;

    protected boolean canEqual(Object obj) {
        return obj instanceof WifiConfigurationsErrorResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiConfigurationsErrorResponse)) {
            return false;
        }
        WifiConfigurationsErrorResponse wifiConfigurationsErrorResponse = (WifiConfigurationsErrorResponse) obj;
        if (!wifiConfigurationsErrorResponse.canEqual(this)) {
            return false;
        }
        String message = getMessage();
        String message2 = wifiConfigurationsErrorResponse.getMessage();
        return message != null ? message.equals(message2) : message2 == null;
    }

    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        String message = getMessage();
        return 59 + (message == null ? 43 : message.hashCode());
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("WifiConfigurationsErrorResponse(message="), getMessage(), ")");
    }
}
