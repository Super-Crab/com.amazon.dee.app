package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Intent;
/* loaded from: classes6.dex */
class AlexaServiceIntentFactory {
    static final String ACTION_CLIENT_STARTING_SERVICE = "ACTION_CLIENT_STARTING_SERVICE";
    static final String EXTRA_EXTENDED_CLIENT = "EXTRA_EXTENDED_CLIENT";
    static final String EXTRA_REQUIRES_FOREGROUND = "EXTRA_REQUIRES_FOREGROUND";

    AlexaServiceIntentFactory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent createIntent(ExtendedClient extendedClient, ComponentName componentName, boolean z) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setAction(ACTION_CLIENT_STARTING_SERVICE);
        intent.putExtra(EXTRA_EXTENDED_CLIENT, extendedClient.getBundle());
        intent.putExtra(EXTRA_REQUIRES_FOREGROUND, z);
        return intent;
    }
}
