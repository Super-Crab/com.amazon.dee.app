package com.amazon.alexa.enrollment.unified.speakerid.api.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class StartVoiceEnrollmentRequest {
    private final String adultCommsPersonId;
    private final String childCustomerId;
    private final String deviceSerialNumber;
    private final String deviceType;
    private final String enrollmentContext;
    private final String locale;
    private final String marketplaceId;
    private final String personECID;
    private final String personId;
    private final Boolean withConsent;

    public StartVoiceEnrollmentRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, Boolean bool, String str8, String str9) {
        this.personId = str;
        this.personECID = str2;
        this.deviceType = str3;
        this.deviceSerialNumber = str4;
        this.locale = str5;
        this.marketplaceId = str6;
        this.enrollmentContext = str7;
        this.withConsent = bool;
        this.childCustomerId = str8;
        this.adultCommsPersonId = str9;
    }

    public String getAdultCommsPersonId() {
        return this.adultCommsPersonId;
    }

    public String getChildCustomerId() {
        return this.childCustomerId;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
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

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getPersonECID() {
        return this.personECID;
    }

    public String getPersonId() {
        return this.personId;
    }

    public boolean isWithConsent() {
        return this.withConsent.booleanValue();
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("PersonId: ");
        outline103.append(this.personId);
        outline103.append(" PersonECID: ");
        outline103.append(this.personECID);
        outline103.append(" DeviceType: ");
        outline103.append(this.deviceType);
        outline103.append(" DeviceSerialNumber: ");
        outline103.append(this.deviceSerialNumber);
        outline103.append(" Locale: ");
        outline103.append(this.locale);
        outline103.append(" MarketplaceId: ");
        outline103.append(this.marketplaceId);
        outline103.append(" EnrollmentContext: ");
        outline103.append(this.enrollmentContext);
        outline103.append(" adultCommsPersonId: ");
        outline103.append(this.adultCommsPersonId);
        outline103.append(" childCustomerId: ");
        outline103.append(this.childCustomerId);
        outline103.append(" withConsent: ");
        outline103.append(this.withConsent);
        return outline103.toString();
    }
}
