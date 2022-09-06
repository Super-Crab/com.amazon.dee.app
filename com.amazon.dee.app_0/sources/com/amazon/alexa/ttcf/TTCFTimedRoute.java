package com.amazon.alexa.ttcf;

import androidx.annotation.Nullable;
import com.amazon.alexa.ttcf.api.TTCFRoute;
/* loaded from: classes10.dex */
public interface TTCFTimedRoute {
    @Nullable
    Long getEndTime();

    TTCFRoute getRoute();

    long getStartTime();
}
