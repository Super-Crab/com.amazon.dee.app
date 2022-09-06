package com.amazon.alexa.enrollment.unified.speakerid.api.model;

import android.os.Bundle;
/* loaded from: classes7.dex */
public class EnrollmentMetadata {
    private final String childCustomerId;
    private final String enrollmentContext;
    private final String personECID;
    private final String personId;

    public EnrollmentMetadata(Bundle bundle, String str) {
        this.enrollmentContext = str;
        this.personId = bundle.getString("PERSON_ID");
        this.personECID = bundle.getString("PERSON_ECID");
        this.childCustomerId = bundle.getString("CHILD_CUSTOMER_ID");
    }

    public String getChildCustomerId() {
        return this.childCustomerId;
    }

    public String getEnrollmentContext() {
        return this.enrollmentContext;
    }

    public String getPersonECID() {
        return this.personECID;
    }

    public String getPersonId() {
        return this.personId;
    }

    public EnrollmentMetadata(String str, String str2, String str3, String str4) {
        this.personId = str;
        this.personECID = str2;
        this.enrollmentContext = str3;
        this.childCustomerId = str4;
    }
}
