package com.amazon.photos.uploader.internal;

import android.app.Notification;
import android.app.NotificationManager;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NotificationUpdatesNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.photos.uploader.internal.NotificationUpdatesNotifier$onNotificationUpdated$1", f = "NotificationUpdatesNotifier.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {96, 112}, m = "invokeSuspend", n = {"$this$launch", "retryCount", "limitation", "$this$launch", "$this$forEach$iv", "element$iv", "it"}, s = {"L$0", "I$0", "J$0", "L$0", "L$1", "L$3", "L$4"})
/* loaded from: classes13.dex */
public final class NotificationUpdatesNotifier$onNotificationUpdated$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isLastNotification;
    final /* synthetic */ Notification $notification;
    final /* synthetic */ int $notificationId;
    final /* synthetic */ NotificationManager $notificationManager;
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ NotificationUpdatesNotifier this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: NotificationUpdatesNotifier.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getEventName"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.photos.uploader.internal.NotificationUpdatesNotifier$onNotificationUpdated$1$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass1 implements MetricName {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
        }

        @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
        @NotNull
        public final String getEventName() {
            return UploadMetrics.NOTIFY_WAIT_FOR_RUNNING_WORKER_TIMEOUT;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationUpdatesNotifier$onNotificationUpdated$1(NotificationUpdatesNotifier notificationUpdatesNotifier, boolean z, NotificationManager notificationManager, int i, Notification notification, Continuation continuation) {
        super(2, continuation);
        this.this$0 = notificationUpdatesNotifier;
        this.$isLastNotification = z;
        this.$notificationManager = notificationManager;
        this.$notificationId = i;
        this.$notification = notification;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        NotificationUpdatesNotifier$onNotificationUpdated$1 notificationUpdatesNotifier$onNotificationUpdated$1 = new NotificationUpdatesNotifier$onNotificationUpdated$1(this.this$0, this.$isLastNotification, this.$notificationManager, this.$notificationId, this.$notification, completion);
        notificationUpdatesNotifier$onNotificationUpdated$1.p$ = (CoroutineScope) obj;
        return notificationUpdatesNotifier$onNotificationUpdated$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NotificationUpdatesNotifier$onNotificationUpdated$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:5|(3:6|7|8)|9|10|11|(7:13|14|(1:16)|9|10|11|(0))|18|19) */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00cc, code lost:
        r8 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ad  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006d -> B:24:0x0070). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00c6 -> B:42:0x00c9). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.internal.NotificationUpdatesNotifier$onNotificationUpdated$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
