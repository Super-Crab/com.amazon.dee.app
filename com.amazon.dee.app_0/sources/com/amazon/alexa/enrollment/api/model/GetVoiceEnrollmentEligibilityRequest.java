package com.amazon.alexa.enrollment.api.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class GetVoiceEnrollmentEligibilityRequest {
    private final String deviceType;
    private final String enrollmentContext;
    private final String locale;
    private final String personECID;
    private final String personId;

    public GetVoiceEnrollmentEligibilityRequest(String str, String str2, String str3, String str4, String str5) {
        this.personId = str;
        this.personECID = str2;
        this.deviceType = str3;
        this.locale = str4;
        this.enrollmentContext = str5;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getEnrollmentContext() {
        return this.enrollmentContext;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getPersonECID() {
        return this.personECID;
    }

    public String getPersonId() {
        return this.personId;
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("PersonId: ");
        outline103.append(this.personId);
        outline103.append(" PersonECID: ");
        outline103.append(this.personECID);
        outline103.append(" DeviceType: ");
        outline103.append(this.deviceType);
        outline103.append(" Locale: ");
        outline103.append(this.locale);
        outline103.append(" EnrollmentContext: ");
        outline103.append(this.enrollmentContext);
        return outline103.toString();
    }
}
