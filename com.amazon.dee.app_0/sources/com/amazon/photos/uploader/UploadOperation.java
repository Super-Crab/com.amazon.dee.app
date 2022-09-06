package com.amazon.photos.uploader;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.photos.uploader.UploadOperation;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadOperation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0012B\u001f\u0012\u0018\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\u0010\u0007J\u001c\u0010\u000b\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000b\u0010\u0010\u001a\u00028\u0000¢\u0006\u0002\u0010\u0011R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00018\u00008\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/photos/uploader/UploadOperation;", ExifInterface.GPS_DIRECTION_TRUE, "", "action", "Lkotlin/Function1;", "Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;", "", "(Lkotlin/jvm/functions/Function1;)V", "responseFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "kotlin.jvm.PlatformType", "setCallback", "callback", "Lcom/amazon/photos/uploader/UploadOperation$UploadOperationCallback;", "executor", "Ljava/util/concurrent/Executor;", "waitForResult", "()Ljava/lang/Object;", "UploadOperationCallback", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadOperation<T> {
    private final ListenableFuture<T> responseFuture;

    /* compiled from: UploadOperation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0015\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0001H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/UploadOperation$UploadOperationCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", "onFailure", "", "error", "", "onSuccess", "value", "(Ljava/lang/Object;)V", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public interface UploadOperationCallback<T> {
        void onFailure(@NotNull Throwable th);

        void onSuccess(T t);
    }

    public UploadOperation(@NotNull final Function1<? super CallbackToFutureAdapter.Completer<T>, Unit> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        ListenableFuture<T> future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver<T>() { // from class: com.amazon.photos.uploader.UploadOperation$responseFuture$1

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: UploadOperation.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
            @DebugMetadata(c = "com.amazon.photos.uploader.UploadOperation$responseFuture$1$1", f = "UploadOperation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.amazon.photos.uploader.UploadOperation$responseFuture$1$1  reason: invalid class name */
            /* loaded from: classes13.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CallbackToFutureAdapter.Completer $completer;
                int label;
                private CoroutineScope p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(CallbackToFutureAdapter.Completer completer, Continuation continuation) {
                    super(2, continuation);
                    this.$completer = completer;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$completer, completion);
                    anonymousClass1.p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                /* renamed from: invoke */
                public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        try {
                            Function1.this.mo12165invoke(this.$completer);
                        } catch (Throwable th) {
                            Boxing.boxBoolean(this.$completer.setException(th));
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            @NotNull
            /* renamed from: attachCompleter  reason: collision with other method in class */
            public final String mo4453attachCompleter(@NotNull CallbackToFutureAdapter.Completer<T> completer) {
                Intrinsics.checkParameterIsNotNull(completer, "completer");
                BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(completer, null), 3, null);
                return "UploadOperation";
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(future, "CallbackToFutureAdapter.…  \"UploadOperation\"\n    }");
        this.responseFuture = future;
    }

    public final void setCallback(@NotNull final UploadOperationCallback<T> callback, @NotNull Executor executor) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Intrinsics.checkParameterIsNotNull(executor, "executor");
        this.responseFuture.addListener(new Runnable() { // from class: com.amazon.photos.uploader.UploadOperation$setCallback$1
            @Override // java.lang.Runnable
            public final void run() {
                ListenableFuture listenableFuture;
                UploadOperation.UploadOperationCallback uploadOperationCallback = callback;
                try {
                    listenableFuture = UploadOperation.this.responseFuture;
                    uploadOperationCallback.onSuccess(listenableFuture.get());
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    uploadOperationCallback.onFailure(th);
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }, executor);
    }

    public final T waitForResult() {
        return this.responseFuture.get();
    }
}
