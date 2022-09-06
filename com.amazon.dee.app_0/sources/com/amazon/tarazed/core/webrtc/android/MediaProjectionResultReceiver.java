package com.amazon.tarazed.core.webrtc.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MediaProjectionResultReceiver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/android/MediaProjectionResultReceiver;", "Landroid/os/ResultReceiver;", "continuation", "Lkotlin/coroutines/Continuation;", "Landroid/content/Intent;", "(Lkotlin/coroutines/Continuation;)V", "getGenericResultReceiver", "onReceiveResult", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MediaProjectionResultReceiver extends ResultReceiver {
    @NotNull
    public static final String BUNDLE_KEY_MEDIA_PROJECTION_INTENT = "com.amazon.tarazed.MEDIA_PROJECTION_INTENT";
    public static final Companion Companion = new Companion(null);
    public static final int RESULT_FAIL = 1;
    public static final int RESULT_OK = 0;
    public static final int RESULT_USER_REJECTION = 2;
    private final Continuation<Intent> continuation;

    /* compiled from: MediaProjectionResultReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/android/MediaProjectionResultReceiver$Companion;", "", "()V", "BUNDLE_KEY_MEDIA_PROJECTION_INTENT", "", "RESULT_FAIL", "", "RESULT_OK", "RESULT_USER_REJECTION", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MediaProjectionResultReceiver(@NotNull Continuation<? super Intent> continuation) {
        super(null);
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        this.continuation = continuation;
    }

    @NotNull
    public final ResultReceiver getGenericResultReceiver() {
        Parcel obtain = Parcel.obtain();
        Intrinsics.checkExpressionValueIsNotNull(obtain, "Parcel.obtain()");
        writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver receiverForSending = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        Intrinsics.checkExpressionValueIsNotNull(receiverForSending, "receiverForSending");
        return receiverForSending;
    }

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int i, @NotNull Bundle resultData) {
        Intrinsics.checkParameterIsNotNull(resultData, "resultData");
        if (i == 0) {
            Intent intent = (Intent) resultData.getParcelable(BUNDLE_KEY_MEDIA_PROJECTION_INTENT);
            if (intent == null) {
                Continuation<Intent> continuation = this.continuation;
                TarazedMediaProjectionException tarazedMediaProjectionException = new TarazedMediaProjectionException("MediaProjection Intent was null", i);
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(tarazedMediaProjectionException)));
                return;
            }
            Continuation<Intent> continuation2 = this.continuation;
            Result.Companion companion2 = Result.Companion;
            continuation2.resumeWith(Result.m10378constructorimpl(intent));
        } else if (2 == i) {
            Continuation<Intent> continuation3 = this.continuation;
            TarazedMediaProjectionException tarazedMediaProjectionException2 = new TarazedMediaProjectionException("MediaProjection Rejected by customer", i);
            Result.Companion companion3 = Result.Companion;
            continuation3.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(tarazedMediaProjectionException2)));
        } else if (1 == i) {
            Continuation<Intent> continuation4 = this.continuation;
            TarazedMediaProjectionException tarazedMediaProjectionException3 = new TarazedMediaProjectionException("MediaProjection result sender indicated failure", i);
            Result.Companion companion4 = Result.Companion;
            continuation4.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(tarazedMediaProjectionException3)));
        } else {
            Continuation<Intent> continuation5 = this.continuation;
            TarazedMediaProjectionException tarazedMediaProjectionException4 = new TarazedMediaProjectionException(GeneratedOutlineSupport1.outline49("Unknown result code: ", i), i);
            Result.Companion companion5 = Result.Companion;
            continuation5.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(tarazedMediaProjectionException4)));
        }
    }
}
