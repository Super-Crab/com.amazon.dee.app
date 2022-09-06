package com.amazon.alexa.protocols.jobs;

import androidx.annotation.NonNull;
@FunctionalInterface
/* loaded from: classes9.dex */
public interface JobIDProvider {
    public static final int NO_JOB_ID_FOUND = -1;

    int getJobId(@NonNull Class cls);
}
