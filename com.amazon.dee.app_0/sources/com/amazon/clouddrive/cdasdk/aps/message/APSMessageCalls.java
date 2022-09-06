package com.amazon.clouddrive.cdasdk.aps.message;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface APSMessageCalls {
    @NonNull
    Single<SendNotificationOutput> sendNotification(@NonNull SendNotificationInput sendNotificationInput);
}
