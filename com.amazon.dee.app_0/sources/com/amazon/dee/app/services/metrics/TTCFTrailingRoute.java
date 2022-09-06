package com.amazon.dee.app.services.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.ttcf.api.TTCFRoute;
/* loaded from: classes12.dex */
public interface TTCFTrailingRoute extends TTCFRoute {
    @NonNull
    String getMetricName();

    long getStartTime();
}
