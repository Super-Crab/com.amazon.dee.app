package com.amazon.identity.platform.weblab;

import android.content.Context;
import com.amazon.identity.auth.device.eg;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.na;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public enum Weblab {
    MAP_ANDROID_SECONDARY_PANDA_93840("MAP_ANDROID_SECONDARY_PANDA_93840", Treatment.C_Default),
    MAP_DUMMY_WEBLAB_FOR_UNIT_TESTS("MAP_DUMMY_WEBLAB_FOR_UNIT_TESTS", Treatment.C_Default);
    
    private static final String TAG = Weblab.class.getName();
    private final Treatment mDefaultTreatment;
    private final String mName;
    private Treatment mOverrideTreatment;
    private eg mSystemPropertiesWrapper = new eg();
    private MAPWeblabClient mWeblabClient;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum Treatment {
        C_Default,
        C,
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10
    }

    Weblab(String str, Treatment treatment) {
        this.mName = str;
        this.mDefaultTreatment = treatment;
    }

    public Treatment bu(Context context) {
        iX();
        Treatment treatment = this.mOverrideTreatment;
        if (treatment != null) {
            return treatment;
        }
        if (this.mWeblabClient == null) {
            if (this == MAP_ANDROID_SECONDARY_PANDA_93840) {
                return Treatment.C;
            }
            this.mWeblabClient = na.bp(context);
        }
        MAPWeblabClient mAPWeblabClient = this.mWeblabClient;
        if (mAPWeblabClient != null) {
            try {
                return Treatment.valueOf(mAPWeblabClient.getTreatmentAndRecordTrigger(toString()));
            } catch (Exception e) {
                io.e(TAG, "Exception when trying to get treatment", e);
                return iW();
            }
        }
        String str = TAG;
        new StringBuilder("MobileWeblabAndroidClient is not supported, use default treatment: ").append(iW().name());
        io.dm(str);
        return iW();
    }

    public String getName() {
        return this.mName;
    }

    public Treatment iW() {
        return this.mDefaultTreatment;
    }

    void iX() {
        this.mOverrideTreatment = null;
    }
}
