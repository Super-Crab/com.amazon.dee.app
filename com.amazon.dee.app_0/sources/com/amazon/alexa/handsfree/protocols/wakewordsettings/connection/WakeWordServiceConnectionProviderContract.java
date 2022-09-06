package com.amazon.alexa.handsfree.protocols.wakewordsettings.connection;

import android.content.Context;
/* loaded from: classes8.dex */
public interface WakeWordServiceConnectionProviderContract {
    WakeWordSettingsServiceConnection getWakeWordSettingsServiceConnection(WakeWordSettingsServiceStateCallback wakeWordSettingsServiceStateCallback, Context context);
}
