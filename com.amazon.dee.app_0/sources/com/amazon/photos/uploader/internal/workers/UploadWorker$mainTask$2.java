package com.amazon.photos.uploader.internal.workers;

import androidx.work.ListenableWorker;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.internal.dagger.UploadManagerMap;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploadWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$2", f = "UploadWorker.kt", i = {0}, l = {159}, m = "invokeSuspend", n = {"$this$coroutineScope"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class UploadWorker$mainTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ UploadWorker this$0;

    /* compiled from: UploadWorker.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$2$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    final /* synthetic */ class AnonymousClass1 extends MutablePropertyReference0 {
        AnonymousClass1(UploadWorker uploadWorker) {
            super(uploadWorker);
        }

        @Override // kotlin.reflect.KProperty0
        @Nullable
        public Object get() {
            return UploadWorker.access$getRequest$p((UploadWorker) this.receiver);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "request";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(UploadWorker.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getRequest()Lcom/amazon/photos/uploader/UploadRequest;";
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(@Nullable Object obj) {
            ((UploadWorker) this.receiver).request = (UploadRequest) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UploadWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getEventName"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$2$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass2 implements MetricName {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
        }

        @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
        @NotNull
        public final String getEventName() {
            return UploadMetrics.BAIL_UPLOAD_REQUEST_NOT_FOUND;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UploadWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getEventName"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$2$3  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass3 implements MetricName {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
        }

        @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
        @NotNull
        public final String getEventName() {
            return UploadMetrics.BAIL_UPLOAD_ACCOUNT_NOT_LOGIN;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UploadWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.photos.uploader.internal.workers.UploadWorker$mainTask$2$4  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass4 extends Lambda implements Function1<Throwable, Unit> {
        AnonymousClass4() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            long j;
            ListenableFuture listenableFuture;
            if (th instanceof CancellationException) {
                PersistentLogger logger = UploadWorker$mainTask$2.this.this$0.getLogger();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Upload request ");
                j = UploadWorker$mainTask$2.this.this$0.requestId;
                outline107.append(j);
                outline107.append(" was cancelled.");
                logger.i(UploadWorker.TAG, outline107.toString());
                listenableFuture = UploadWorker$mainTask$2.this.this$0.inProgressUploadFuture;
                if (listenableFuture != null) {
                    listenableFuture.cancel(true);
                }
                UploadWorker$mainTask$2.this.this$0.reportUploadBlocked();
            }
            UploadWorker$mainTask$2.this.this$0.unregisterNotificationObserver$AndroidPhotosUploader_release();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadWorker$mainTask$2(UploadWorker uploadWorker, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uploadWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UploadWorker$mainTask$2 uploadWorker$mainTask$2 = new UploadWorker$mainTask$2(this.this$0, completion);
        uploadWorker$mainTask$2.p$ = (CoroutineScope) obj;
        return uploadWorker$mainTask$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
        return ((UploadWorker$mainTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        UploadRequest uploadRequest;
        String str;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            uploadRequest = this.this$0.request;
            if (uploadRequest != null && UploadWorker.access$getRequest$p(this.this$0) != null) {
                UploadManagerMap uploadManagerMap = UploadManagerMap.INSTANCE;
                str = this.this$0.hashedDirectedId;
                if (!uploadManagerMap.isAccountRegistered$AndroidPhotosUploader_release(str)) {
                    this.this$0.getLogger().i(UploadWorker.TAG, "Bail the upload since the target account is not logged in.");
                    this.this$0.getMetrics().recordSimpleEvent(UploadWorker.TAG, AnonymousClass3.INSTANCE, new MetricRecordingType[0]);
                } else {
                    Job job = (Job) coroutineScope.getCoroutineContext().get(Job.Key);
                    if (job != null) {
                        job.invokeOnCompletion(new AnonymousClass4());
                    }
                    UploadWorker uploadWorker = this.this$0;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (uploadWorker.startUpload(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                this.this$0.getLogger().i(UploadWorker.TAG, "Bail the upload since the request was not found.");
                this.this$0.getMetrics().recordSimpleEvent(UploadWorker.TAG, AnonymousClass2.INSTANCE, new MetricRecordingType[0]);
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return ListenableWorker.Result.success();
    }
}
