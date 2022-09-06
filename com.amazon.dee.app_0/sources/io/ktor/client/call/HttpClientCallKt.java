package io.ktor.client.call;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.client.HttpClient;
import io.ktor.client.response.HttpResponse;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpClientCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a@\u0010\u0000\u001a\u00020\u0001*\u00020\u00022)\b\u0002\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u000b\u001a\u0002H\f\"\u0006\b\u0000\u0010\f\u0018\u0001*\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001d\u0010\u000b\u001a\u0002H\f\"\u0006\b\u0000\u0010\f\u0018\u0001*\u00020\u000eH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000f*D\b\u0007\u0010\u0010\"\u00020\u00112\u00020\u0011B6\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u001c\b\u0015\u0012\u0018\b\u000bB\u0014\b\u0016\u0012\u0006\b\u0017\u0012\u0002\b\f\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\n\b\u001a\u0012\u0006\b\n0\u001b8\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"call", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/HttpClient;", "block", "Lkotlin/Function2;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/client/HttpClient;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receive", ExifInterface.GPS_DIRECTION_TRUE, "(Lio/ktor/client/call/HttpClientCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/client/response/HttpResponse;", "(Lio/ktor/client/response/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "NoTransformationFound", "Lio/ktor/client/call/NoTransformationFoundException;", "Lkotlin/Deprecated;", "message", "[NoTransformationFound] is deprecated. Use [NoTransformationFoundException] instead", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "expression", "NoTransformationFoundException", ModelTransformer.KEY_BATTERY_LEVEL, "Lkotlin/DeprecationLevel;", "ERROR", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpClientCallKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "[NoTransformationFound] is deprecated. Use [NoTransformationFoundException] instead", replaceWith = @ReplaceWith(expression = "NoTransformationFoundException", imports = {}))
    public static /* synthetic */ void NoTransformationFound$annotations() {
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008c A[PHI: r8 
      PHI: (r8v5 java.lang.Object) = (r8v4 java.lang.Object), (r8v1 java.lang.Object) binds: [B:31:0x0089, B:13:0x0032] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object call(@org.jetbrains.annotations.NotNull io.ktor.client.HttpClient r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super io.ktor.client.request.HttpRequestBuilder, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.ktor.client.call.HttpClientCall> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.client.call.HttpClientCallKt$call$1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.client.call.HttpClientCallKt$call$1 r0 = (io.ktor.client.call.HttpClientCallKt$call$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.call.HttpClientCallKt$call$1 r0 = new io.ktor.client.call.HttpClientCallKt$call$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L60
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            java.lang.Object r6 = r0.L$0
            io.ktor.client.HttpClient r6 = (io.ktor.client.HttpClient) r6
            boolean r6 = r8 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L35
            goto L8c
        L35:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L3a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L42:
            java.lang.Object r6 = r0.L$4
            io.ktor.client.HttpClient r6 = (io.ktor.client.HttpClient) r6
            java.lang.Object r7 = r0.L$3
            io.ktor.client.request.HttpRequestBuilder r7 = (io.ktor.client.request.HttpRequestBuilder) r7
            java.lang.Object r7 = r0.L$2
            io.ktor.client.request.HttpRequestBuilder r7 = (io.ktor.client.request.HttpRequestBuilder) r7
            java.lang.Object r2 = r0.L$1
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            java.lang.Object r4 = r0.L$0
            io.ktor.client.HttpClient r4 = (io.ktor.client.HttpClient) r4
            boolean r5 = r8 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L5b
            goto L7f
        L5b:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L60:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L8d
            io.ktor.client.request.HttpRequestBuilder r8 = new io.ktor.client.request.HttpRequestBuilder
            r8.<init>()
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r8
            r0.L$4 = r6
            r0.label = r4
            java.lang.Object r2 = r7.mo12248invoke(r8, r0)
            if (r2 != r1) goto L7c
            return r1
        L7c:
            r4 = r6
            r2 = r7
            r7 = r8
        L7f:
            r0.L$0 = r4
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r8 = r6.execute(r7, r0)
            if (r8 != r1) goto L8c
            return r1
        L8c:
            return r8
        L8d:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.call.HttpClientCallKt.call(io.ktor.client.HttpClient, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static /* synthetic */ Object call$default(HttpClient httpClient, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = new HttpClientCallKt$call$2(null);
        }
        return call(httpClient, function2, continuation);
    }

    private static final <T> Object receive(@NotNull HttpResponse httpResponse, Continuation<? super T> continuation) {
        HttpClientCall call = httpResponse.getCall();
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.call.HttpClientCallKt$receive$$inlined$typeInfo$2
        };
        Type genericSuperclass = HttpClientCallKt$receive$$inlined$typeInfo$2.class.getGenericSuperclass();
        if (genericSuperclass == null) {
            Intrinsics.throwNpe();
        }
        if (genericSuperclass != null) {
            Object outline23 = GeneratedOutlineSupport1.outline23((ParameterizedType) genericSuperclass, "(superType as Parameteri…Type).actualTypeArguments");
            if (outline23 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return GeneratedOutlineSupport1.outline18(0, call, new TypeInfo(Reflection.getOrCreateKotlinClass(Object.class), (Type) outline23), continuation, 1, 1, ExifInterface.GPS_DIRECTION_TRUE);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.reflect.ParameterizedType");
    }

    private static final <T> Object receive(@NotNull HttpClientCall httpClientCall, Continuation<? super T> continuation) {
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.call.HttpClientCallKt$receive$$inlined$typeInfo$1
        };
        Type genericSuperclass = HttpClientCallKt$receive$$inlined$typeInfo$1.class.getGenericSuperclass();
        if (genericSuperclass == null) {
            Intrinsics.throwNpe();
        }
        if (genericSuperclass != null) {
            Object outline23 = GeneratedOutlineSupport1.outline23((ParameterizedType) genericSuperclass, "(superType as Parameteri…Type).actualTypeArguments");
            if (outline23 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return GeneratedOutlineSupport1.outline18(0, httpClientCall, new TypeInfo(Reflection.getOrCreateKotlinClass(Object.class), (Type) outline23), continuation, 1, 1, ExifInterface.GPS_DIRECTION_TRUE);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.reflect.ParameterizedType");
    }
}
