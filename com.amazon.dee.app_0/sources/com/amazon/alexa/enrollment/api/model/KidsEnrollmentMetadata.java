package com.amazon.alexa.enrollment.api.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class KidsEnrollmentMetadata {
    private final String backRoute;
    private final String childCustomerId;
    private final String childName;
    private final String failureRoute;
    private final String personECID;
    private final String personId;
    private final boolean showConsentCollectedToast;
    private final String successRoute;

    public KidsEnrollmentMetadata(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        this.childCustomerId = str;
        this.personId = str2;
        this.personECID = str3;
        this.childName = str4;
        this.successRoute = str5;
        this.failureRoute = str6;
        this.backRoute = str7;
        this.showConsentCollectedToast = z;
    }

    public String getBackRoute() {
        return this.backRoute;
    }

    public String getChildCustomerId() {
        return this.childCustomerId;
    }

    public String getChildName() {
        return this.childName;
    }

    public String getFailureRoute() {
        return this.failureRoute;
    }

    public String getPersonECID() {
        return this.personECID;
    }

    public String getPersonId() {
        return this.personId;
    }

    public boolean getShowConsentCollectedToast() {
        return this.showConsentCollectedToast;
    }

    public String getSuccessRoute() {
        return this.successRoute;
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("ChildCustomerID: ");
        outline103.append(this.childCustomerId);
        outline103.append(" PersonID: ");
        outline103.append(this.personId);
        outline103.append(" PersonECID: ");
        outline103.append(this.personECID);
        outline103.append(" SuccessRoute: ");
        outline103.append(this.successRoute);
        outline103.append(" FailureRoute: ");
        outline103.append(this.failureRoute);
        outline103.append(" BackRoute: ");
        outline103.append(this.backRoute);
        outline103.append(" ShowConsentCollectedToast: ");
        outline103.append(this.showConsentCollectedToast);
        return outline103.toString();
    }
}
