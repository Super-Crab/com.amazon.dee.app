package com.amazon.alexa.voice.provisioning;
/* loaded from: classes11.dex */
public interface FeatureProvisioner {

    /* loaded from: classes11.dex */
    public interface Callback {
        void onProvisioningFailed(Exception exc);

        void onProvisioningStarted();

        void onProvisioningSucceeded();
    }

    boolean hasProvisioned();

    void provision(Callback callback);
}
