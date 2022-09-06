package com.amazon.photos.uploader.observables;

import com.amazon.deecomms.common.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NotificationObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/uploader/observables/NotificationObserver;", "", "onNotificationUpdate", "", Constants.BUNDLE_KEY_NOTIFICATION_ID, "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface NotificationObserver {
    @Nullable
    Object onNotificationUpdate(int i, @NotNull Continuation<? super Unit> continuation);
}
