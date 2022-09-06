package com.amazon.alexa.comms.mediaInsights.common.impl;

import android.content.Context;
import android.content.Intent;
/* loaded from: classes6.dex */
public class InAppImplicitBindingIntentProvider implements BindingIntentProvider {
    private static final String TRACE_PUBLISHER_SERVICE_BIND_ACTION = "com.amazon.alexa.comms.mediaInsights.service.actions.BIND";
    private static final String TRACE_PUBLISHER_SERVICE_CLASS_NAME = "com.amazon.alexa.comms.mediaInsights.service.TracePublisherService";

    @Override // com.amazon.alexa.comms.mediaInsights.common.impl.BindingIntentProvider
    public Intent provideBindingIntent(Context context) {
        Intent intent = new Intent(TRACE_PUBLISHER_SERVICE_BIND_ACTION);
        intent.setClassName(context.getApplicationContext().getPackageName(), TRACE_PUBLISHER_SERVICE_CLASS_NAME);
        return intent;
    }
}
