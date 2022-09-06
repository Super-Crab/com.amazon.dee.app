package io.ktor.client.request;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.routing.api.RouteParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.TypeBase;
import io.ktor.client.call.TypeInfo;
import io.ktor.client.utils.EmptyContent;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLParserKt;
import io.ktor.http.Url;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
/* compiled from: builders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005\u001a%\u0010\u0006\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u0006\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001aj\u0010\u0006\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a%\u0010\u0016\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u0016\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001aj\u0010\u0016\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a%\u0010\u0017\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u0017\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001aj\u0010\u0017\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a%\u0010\u0018\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u0018\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001aj\u0010\u0018\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a%\u0010\u0019\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u0019\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001aj\u0010\u0019\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a%\u0010\u001a\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u001a\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001aj\u0010\u001a\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a%\u0010\u001b\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u001b\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001aj\u0010\u001b\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a'\u0010\u0000\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a6\u0010\u0000\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a>\u0010\u0000\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001a>\u0010\u0000\u001a\u0002H\u0007\"\u0006\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"request", "Lio/ktor/client/request/HttpRequestBuilder;", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "delete", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/client/HttpClient;", "builder", "(Lio/ktor/client/HttpClient;Lio/ktor/client/request/HttpRequestBuilder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "urlString", "", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scheme", "host", "port", "", RouteParameter.PATH, "body", "", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", MetricsConstants.Method.CACHE_GET, TtmlNode.TAG_HEAD, "options", "patch", "post", MetricsConstants.Method.CACHE_PUT, "(Lio/ktor/client/HttpClient;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "url", "Lio/ktor/http/Url;", "(Lio/ktor/client/HttpClient;Lio/ktor/http/Url;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class BuildersKt {
    private static final <T> Object delete(@NotNull HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, Continuation<? super T> continuation) {
        httpRequestBuilder.setMethod(HttpMethod.Companion.getDelete());
        InlineMarker.mark(0);
        Object call = io.ktor.client.call.UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$delete$$inlined$request$1
        };
        Type genericSuperclass = BuildersKt$delete$$inlined$request$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object delete$default(HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1 function1, Continuation continuation, int i2, Object obj2) {
        String str4 = (i2 & 1) != 0 ? "http" : str;
        String str5 = (i2 & 2) != 0 ? AndroidInfoHelpers.DEVICE_LOCALHOST : str2;
        int i3 = (i2 & 4) != 0 ? 0 : i;
        String str6 = (i2 & 8) != 0 ? "/" : str3;
        Object obj3 = (i2 & 16) != 0 ? EmptyContent.INSTANCE : obj;
        BuildersKt$delete$3 buildersKt$delete$3 = (i2 & 32) != 0 ? BuildersKt$delete$3.INSTANCE : function1;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str4, str5, i3, str6, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getDelete());
        httpRequestBuilder.setBody(obj3);
        buildersKt$delete$3.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$delete$$inlined$request$3
        };
        Type genericSuperclass = BuildersKt$delete$$inlined$request$3.class.getGenericSuperclass();
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

    private static final <T> Object get(@NotNull HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, Continuation<? super T> continuation) {
        httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
        InlineMarker.mark(0);
        Object call = io.ktor.client.call.UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$get$$inlined$request$1
        };
        Type genericSuperclass = BuildersKt$get$$inlined$request$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object get$default(HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1 function1, Continuation continuation, int i2, Object obj2) {
        String str4 = (i2 & 1) != 0 ? "http" : str;
        String str5 = (i2 & 2) != 0 ? AndroidInfoHelpers.DEVICE_LOCALHOST : str2;
        int i3 = (i2 & 4) != 0 ? 0 : i;
        String str6 = (i2 & 8) != 0 ? "/" : str3;
        Object obj3 = (i2 & 16) != 0 ? EmptyContent.INSTANCE : obj;
        BuildersKt$get$3 buildersKt$get$3 = (i2 & 32) != 0 ? BuildersKt$get$3.INSTANCE : function1;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str4, str5, i3, str6, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
        httpRequestBuilder.setBody(obj3);
        buildersKt$get$3.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$get$$inlined$request$3
        };
        Type genericSuperclass = BuildersKt$get$$inlined$request$3.class.getGenericSuperclass();
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

    private static final <T> Object head(@NotNull HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, Continuation<? super T> continuation) {
        httpRequestBuilder.setMethod(HttpMethod.Companion.getHead());
        InlineMarker.mark(0);
        Object call = io.ktor.client.call.UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$head$$inlined$request$1
        };
        Type genericSuperclass = BuildersKt$head$$inlined$request$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object head$default(HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1 function1, Continuation continuation, int i2, Object obj2) {
        String str4 = (i2 & 1) != 0 ? "http" : str;
        String str5 = (i2 & 2) != 0 ? AndroidInfoHelpers.DEVICE_LOCALHOST : str2;
        int i3 = (i2 & 4) != 0 ? 0 : i;
        String str6 = (i2 & 8) != 0 ? "/" : str3;
        Object obj3 = (i2 & 16) != 0 ? EmptyContent.INSTANCE : obj;
        BuildersKt$head$3 buildersKt$head$3 = (i2 & 32) != 0 ? BuildersKt$head$3.INSTANCE : function1;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str4, str5, i3, str6, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getHead());
        httpRequestBuilder.setBody(obj3);
        buildersKt$head$3.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$head$$inlined$request$3
        };
        Type genericSuperclass = BuildersKt$head$$inlined$request$3.class.getGenericSuperclass();
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

    private static final <T> Object options(@NotNull HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, Continuation<? super T> continuation) {
        httpRequestBuilder.setMethod(HttpMethod.Companion.getOptions());
        InlineMarker.mark(0);
        Object call = io.ktor.client.call.UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$options$$inlined$request$1
        };
        Type genericSuperclass = BuildersKt$options$$inlined$request$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object options$default(HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1 function1, Continuation continuation, int i2, Object obj2) {
        String str4 = (i2 & 1) != 0 ? "http" : str;
        String str5 = (i2 & 2) != 0 ? AndroidInfoHelpers.DEVICE_LOCALHOST : str2;
        int i3 = (i2 & 4) != 0 ? 0 : i;
        String str6 = (i2 & 8) != 0 ? "/" : str3;
        Object obj3 = (i2 & 16) != 0 ? EmptyContent.INSTANCE : obj;
        BuildersKt$options$3 buildersKt$options$3 = (i2 & 32) != 0 ? BuildersKt$options$3.INSTANCE : function1;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str4, str5, i3, str6, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getOptions());
        httpRequestBuilder.setBody(obj3);
        buildersKt$options$3.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$options$$inlined$request$3
        };
        Type genericSuperclass = BuildersKt$options$$inlined$request$3.class.getGenericSuperclass();
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

    private static final <T> Object patch(@NotNull HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, Continuation<? super T> continuation) {
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPatch());
        InlineMarker.mark(0);
        Object call = io.ktor.client.call.UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$patch$$inlined$request$1
        };
        Type genericSuperclass = BuildersKt$patch$$inlined$request$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object patch$default(HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1 function1, Continuation continuation, int i2, Object obj2) {
        String str4 = (i2 & 1) != 0 ? "http" : str;
        String str5 = (i2 & 2) != 0 ? AndroidInfoHelpers.DEVICE_LOCALHOST : str2;
        int i3 = (i2 & 4) != 0 ? 0 : i;
        String str6 = (i2 & 8) != 0 ? "/" : str3;
        Object obj3 = (i2 & 16) != 0 ? EmptyContent.INSTANCE : obj;
        BuildersKt$patch$3 buildersKt$patch$3 = (i2 & 32) != 0 ? BuildersKt$patch$3.INSTANCE : function1;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str4, str5, i3, str6, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPatch());
        httpRequestBuilder.setBody(obj3);
        buildersKt$patch$3.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$patch$$inlined$request$3
        };
        Type genericSuperclass = BuildersKt$patch$$inlined$request$3.class.getGenericSuperclass();
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

    private static final <T> Object post(@NotNull HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, Continuation<? super T> continuation) {
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        InlineMarker.mark(0);
        Object call = io.ktor.client.call.UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$post$$inlined$request$1
        };
        Type genericSuperclass = BuildersKt$post$$inlined$request$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object post$default(HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1 function1, Continuation continuation, int i2, Object obj2) {
        String str4 = (i2 & 1) != 0 ? "http" : str;
        String str5 = (i2 & 2) != 0 ? AndroidInfoHelpers.DEVICE_LOCALHOST : str2;
        int i3 = (i2 & 4) != 0 ? 0 : i;
        String str6 = (i2 & 8) != 0 ? "/" : str3;
        Object obj3 = (i2 & 16) != 0 ? EmptyContent.INSTANCE : obj;
        BuildersKt$post$3 buildersKt$post$3 = (i2 & 32) != 0 ? BuildersKt$post$3.INSTANCE : function1;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str4, str5, i3, str6, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(obj3);
        buildersKt$post$3.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$post$$inlined$request$3
        };
        Type genericSuperclass = BuildersKt$post$$inlined$request$3.class.getGenericSuperclass();
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

    private static final <T> Object put(@NotNull HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, Continuation<? super T> continuation) {
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPut());
        InlineMarker.mark(0);
        Object call = io.ktor.client.call.UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$put$$inlined$request$1
        };
        Type genericSuperclass = BuildersKt$put$$inlined$request$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object put$default(HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1 function1, Continuation continuation, int i2, Object obj2) {
        String str4 = (i2 & 1) != 0 ? "http" : str;
        String str5 = (i2 & 2) != 0 ? AndroidInfoHelpers.DEVICE_LOCALHOST : str2;
        int i3 = (i2 & 4) != 0 ? 0 : i;
        String str6 = (i2 & 8) != 0 ? "/" : str3;
        Object obj3 = (i2 & 16) != 0 ? EmptyContent.INSTANCE : obj;
        BuildersKt$put$3 buildersKt$put$3 = (i2 & 32) != 0 ? BuildersKt$put$3.INSTANCE : function1;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str4, str5, i3, str6, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPut());
        httpRequestBuilder.setBody(obj3);
        buildersKt$put$3.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$put$$inlined$request$3
        };
        Type genericSuperclass = BuildersKt$put$$inlined$request$3.class.getGenericSuperclass();
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

    private static final <T> Object request(@NotNull HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, Continuation<? super T> continuation) {
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$request$$inlined$receive$1
        };
        Type genericSuperclass = BuildersKt$request$$inlined$receive$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object request$default(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            httpRequestBuilder = new HttpRequestBuilder();
        }
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$request$$inlined$receive$2
        };
        Type genericSuperclass = BuildersKt$request$$inlined$receive$2.class.getGenericSuperclass();
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

    private static final <T> Object request(@NotNull HttpClient httpClient, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        function1.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = io.ktor.client.call.UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$request$$inlined$request$1
        };
        Type genericSuperclass = BuildersKt$request$$inlined$request$1.class.getGenericSuperclass();
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

    private static final <T> Object delete(@NotNull HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str, str2, i, str3, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getDelete());
        httpRequestBuilder.setBody(obj);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$delete$$inlined$request$2
        };
        Type genericSuperclass = BuildersKt$delete$$inlined$request$2.class.getGenericSuperclass();
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

    private static final <T> Object get(@NotNull HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str, str2, i, str3, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
        httpRequestBuilder.setBody(obj);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$get$$inlined$request$2
        };
        Type genericSuperclass = BuildersKt$get$$inlined$request$2.class.getGenericSuperclass();
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

    private static final <T> Object head(@NotNull HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str, str2, i, str3, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getHead());
        httpRequestBuilder.setBody(obj);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$head$$inlined$request$2
        };
        Type genericSuperclass = BuildersKt$head$$inlined$request$2.class.getGenericSuperclass();
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

    private static final <T> Object options(@NotNull HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str, str2, i, str3, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getOptions());
        httpRequestBuilder.setBody(obj);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$options$$inlined$request$2
        };
        Type genericSuperclass = BuildersKt$options$$inlined$request$2.class.getGenericSuperclass();
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

    private static final <T> Object patch(@NotNull HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str, str2, i, str3, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPatch());
        httpRequestBuilder.setBody(obj);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$patch$$inlined$request$2
        };
        Type genericSuperclass = BuildersKt$patch$$inlined$request$2.class.getGenericSuperclass();
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

    private static final <T> Object post(@NotNull HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str, str2, i, str3, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(obj);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$post$$inlined$request$2
        };
        Type genericSuperclass = BuildersKt$post$$inlined$request$2.class.getGenericSuperclass();
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

    private static final <T> Object put(@NotNull HttpClient httpClient, String str, String str2, int i, String str3, Object obj, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, str, str2, i, str3, null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPut());
        httpRequestBuilder.setBody(obj);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$put$$inlined$request$2
        };
        Type genericSuperclass = BuildersKt$put$$inlined$request$2.class.getGenericSuperclass();
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

    static /* synthetic */ Object delete$default(HttpClient httpClient, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersKt$delete$6.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getDelete());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$delete$$inlined$delete$2
        };
        Type genericSuperclass = BuildersKt$delete$$inlined$delete$2.class.getGenericSuperclass();
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

    static /* synthetic */ Object get$default(HttpClient httpClient, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersKt$get$6.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$get$$inlined$get$2
        };
        Type genericSuperclass = BuildersKt$get$$inlined$get$2.class.getGenericSuperclass();
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

    static /* synthetic */ Object head$default(HttpClient httpClient, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersKt$head$6.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getHead());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$head$$inlined$head$2
        };
        Type genericSuperclass = BuildersKt$head$$inlined$head$2.class.getGenericSuperclass();
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

    static /* synthetic */ Object options$default(HttpClient httpClient, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersKt$options$6.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getOptions());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$options$$inlined$options$2
        };
        Type genericSuperclass = BuildersKt$options$$inlined$options$2.class.getGenericSuperclass();
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

    static /* synthetic */ Object patch$default(HttpClient httpClient, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersKt$patch$6.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPatch());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$patch$$inlined$patch$2
        };
        Type genericSuperclass = BuildersKt$patch$$inlined$patch$2.class.getGenericSuperclass();
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

    static /* synthetic */ Object post$default(HttpClient httpClient, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersKt$post$6.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$post$$inlined$post$2
        };
        Type genericSuperclass = BuildersKt$post$$inlined$post$2.class.getGenericSuperclass();
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

    static /* synthetic */ Object put$default(HttpClient httpClient, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersKt$put$6.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPut());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$put$$inlined$put$2
        };
        Type genericSuperclass = BuildersKt$put$$inlined$put$2.class.getGenericSuperclass();
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

    private static final <T> Object request(@NotNull HttpClient httpClient, String str, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url(httpRequestBuilder, str);
        function1.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = io.ktor.client.call.UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$request$$inlined$request$2
        };
        Type genericSuperclass = BuildersKt$request$$inlined$request$2.class.getGenericSuperclass();
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

    private static final <T> Object delete(@NotNull HttpClient httpClient, String str, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getDelete());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$delete$$inlined$delete$1
        };
        Type genericSuperclass = BuildersKt$delete$$inlined$delete$1.class.getGenericSuperclass();
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

    private static final <T> Object get(@NotNull HttpClient httpClient, String str, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$get$$inlined$get$1
        };
        Type genericSuperclass = BuildersKt$get$$inlined$get$1.class.getGenericSuperclass();
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

    private static final <T> Object head(@NotNull HttpClient httpClient, String str, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getHead());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$head$$inlined$head$1
        };
        Type genericSuperclass = BuildersKt$head$$inlined$head$1.class.getGenericSuperclass();
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

    private static final <T> Object options(@NotNull HttpClient httpClient, String str, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getOptions());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$options$$inlined$options$1
        };
        Type genericSuperclass = BuildersKt$options$$inlined$options$1.class.getGenericSuperclass();
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

    private static final <T> Object patch(@NotNull HttpClient httpClient, String str, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPatch());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$patch$$inlined$patch$1
        };
        Type genericSuperclass = BuildersKt$patch$$inlined$patch$1.class.getGenericSuperclass();
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

    private static final <T> Object post(@NotNull HttpClient httpClient, String str, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$post$$inlined$post$1
        };
        Type genericSuperclass = BuildersKt$post$$inlined$post$1.class.getGenericSuperclass();
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

    private static final <T> Object put(@NotNull HttpClient httpClient, String str, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPut());
        httpRequestBuilder.setBody(emptyContent);
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$put$$inlined$put$1
        };
        Type genericSuperclass = BuildersKt$put$$inlined$put$1.class.getGenericSuperclass();
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

    private static final <T> Object request(@NotNull HttpClient httpClient, Url url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        BuildersWithUrlKt.url(httpRequestBuilder, url);
        function1.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = io.ktor.client.call.UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersKt$request$$inlined$request$3
        };
        Type genericSuperclass = BuildersKt$request$$inlined$request$3.class.getGenericSuperclass();
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

    @NotNull
    public static final HttpRequestBuilder request(@NotNull Function1<? super HttpRequestBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        block.mo12165invoke(httpRequestBuilder);
        return httpRequestBuilder;
    }
}
