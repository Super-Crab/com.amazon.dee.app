package com.amazon.clouddrive.cdasdk.dps.event;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface DPSEventCalls {
    @NonNull
    Single<RecordEventResponse> recordEvent(@NonNull RecordEventRequest recordEventRequest);
}
