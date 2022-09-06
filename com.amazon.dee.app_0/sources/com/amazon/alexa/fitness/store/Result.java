package com.amazon.alexa.fitness.store;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Result.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0003\n\u000b\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0003\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/store/Result;", "R", "", "()V", "succeeded", "", "getSucceeded", "()Z", "toString", "", "Error", "Loading", "Success", "Lcom/amazon/alexa/fitness/store/Result$Success;", "Lcom/amazon/alexa/fitness/store/Result$Error;", "Lcom/amazon/alexa/fitness/store/Result$Loading;", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class Result<R> {

    /* compiled from: Result.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\t\u001a\u00060\u0004j\u0002`\u0005HÆ\u0003J\u0017\u0010\n\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0015\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/store/Result$Error;", "Lcom/amazon/alexa/fitness/store/Result;", "", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "getException", "()Ljava/lang/Exception;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Error extends Result {
        @NotNull
        private final Exception exception;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(@NotNull Exception exception) {
            super(null);
            Intrinsics.checkParameterIsNotNull(exception, "exception");
            this.exception = exception;
        }

        public static /* synthetic */ Error copy$default(Error error, Exception exc, int i, Object obj) {
            if ((i & 1) != 0) {
                exc = error.exception;
            }
            return error.copy(exc);
        }

        @NotNull
        public final Exception component1() {
            return this.exception;
        }

        @NotNull
        public final Error copy(@NotNull Exception exception) {
            Intrinsics.checkParameterIsNotNull(exception, "exception");
            return new Error(exception);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof Error) && Intrinsics.areEqual(this.exception, ((Error) obj).exception);
            }
            return true;
        }

        @NotNull
        public final Exception getException() {
            return this.exception;
        }

        public int hashCode() {
            Exception exc = this.exception;
            if (exc != null) {
                return exc.hashCode();
            }
            return 0;
        }

        @Override // com.amazon.alexa.fitness.store.Result
        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error(exception=");
            outline107.append(this.exception);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* compiled from: Result.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/store/Result$Loading;", "Lcom/amazon/alexa/fitness/store/Result;", "", "()V", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Loading extends Result {
        public static final Loading INSTANCE = new Loading();

        private Loading() {
            super(null);
        }
    }

    /* compiled from: Result.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/store/Result$Success;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/fitness/store/Result;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/amazon/alexa/fitness/store/Result$Success;", "equals", "", "other", "", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Success<T> extends Result<T> {
        private final T data;

        public Success(T t) {
            super(null);
            this.data = t;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Success copy$default(Success success, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = success.data;
            }
            return success.copy(obj);
        }

        public final T component1() {
            return this.data;
        }

        @NotNull
        public final Success<T> copy(T t) {
            return new Success<>(t);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof Success) && Intrinsics.areEqual(this.data, ((Success) obj).data);
            }
            return true;
        }

        public final T getData() {
            return this.data;
        }

        public int hashCode() {
            T t = this.data;
            if (t != null) {
                return t.hashCode();
            }
            return 0;
        }

        @Override // com.amazon.alexa.fitness.store.Result
        @NotNull
        public String toString() {
            return GeneratedOutlineSupport1.outline88(GeneratedOutlineSupport1.outline107("Success(data="), this.data, ")");
        }
    }

    private Result() {
    }

    public final boolean getSucceeded() {
        return (this instanceof Success) && ((Success) this).getData() != null;
    }

    @NotNull
    public String toString() {
        if (this instanceof Success) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Success[data=");
            outline107.append(((Success) this).getData());
            outline107.append(JsonReaderKt.END_LIST);
            return outline107.toString();
        } else if (!(this instanceof Error)) {
            if (!(this instanceof Loading)) {
                throw new NoWhenBranchMatchedException();
            }
            return "Loading";
        } else {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Error[exception=");
            outline1072.append(((Error) this).getException());
            outline1072.append(JsonReaderKt.END_LIST);
            return outline1072.toString();
        }
    }

    public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
