package com.amazon.alexa.voiceui.voice;

import dagger.internal.Factory;
/* loaded from: classes11.dex */
public final class AlexaUserInterfaceOptionsTracker_Factory implements Factory<AlexaUserInterfaceOptionsTracker> {
    private static final AlexaUserInterfaceOptionsTracker_Factory INSTANCE = new AlexaUserInterfaceOptionsTracker_Factory();

    public static AlexaUserInterfaceOptionsTracker_Factory create() {
        return INSTANCE;
    }

    public static AlexaUserInterfaceOptionsTracker newAlexaUserInterfaceOptionsTracker() {
        return new AlexaUserInterfaceOptionsTracker();
    }

    public static AlexaUserInterfaceOptionsTracker provideInstance() {
        return new AlexaUserInterfaceOptionsTracker();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaUserInterfaceOptionsTracker mo10268get() {
        return provideInstance();
    }
}
