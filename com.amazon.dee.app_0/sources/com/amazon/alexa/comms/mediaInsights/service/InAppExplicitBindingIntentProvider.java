package com.amazon.alexa.comms.mediaInsights.service;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.comms.mediaInsights.common.impl.BindingIntentProvider;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class InAppExplicitBindingIntentProvider implements BindingIntentProvider {
    @Override // com.amazon.alexa.comms.mediaInsights.common.impl.BindingIntentProvider
    public Intent provideBindingIntent(@NonNull Context context) {
        if (context != null) {
            return new Intent(context.getApplicationContext(), TracePublisherService.class);
        }
        throw new IllegalArgumentException("context is null");
    }
}
