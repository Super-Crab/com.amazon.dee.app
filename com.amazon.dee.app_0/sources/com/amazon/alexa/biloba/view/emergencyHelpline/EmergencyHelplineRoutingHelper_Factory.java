package com.amazon.alexa.biloba.view.emergencyHelpline;

import dagger.internal.Factory;
/* loaded from: classes6.dex */
public final class EmergencyHelplineRoutingHelper_Factory implements Factory<EmergencyHelplineRoutingHelper> {
    private static final EmergencyHelplineRoutingHelper_Factory INSTANCE = new EmergencyHelplineRoutingHelper_Factory();

    public static EmergencyHelplineRoutingHelper_Factory create() {
        return INSTANCE;
    }

    public static EmergencyHelplineRoutingHelper newEmergencyHelplineRoutingHelper() {
        return new EmergencyHelplineRoutingHelper();
    }

    public static EmergencyHelplineRoutingHelper provideInstance() {
        return new EmergencyHelplineRoutingHelper();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EmergencyHelplineRoutingHelper mo10268get() {
        return provideInstance();
    }
}
