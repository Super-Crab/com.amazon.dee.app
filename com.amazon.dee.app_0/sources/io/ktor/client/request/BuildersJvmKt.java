package io.ktor.client.request;

import androidx.exifinterface.media.ExifInterface;
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
import io.ktor.http.URLUtilsJvmKt;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
/* compiled from: buildersJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a@\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u000b\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\f\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\r\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u000e\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u000f\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001a@\u0010\u0010\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"delete", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/client/HttpClient;", "url", "Ljava/net/URL;", "block", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/client/HttpClient;Ljava/net/URL;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", MetricsConstants.Method.CACHE_GET, TtmlNode.TAG_HEAD, "options", "patch", "post", MetricsConstants.Method.CACHE_PUT, "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class BuildersJvmKt {
    private static final <T> Object delete(@NotNull HttpClient httpClient, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getDelete());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$delete$$inlined$delete$1
        };
        Type genericSuperclass = BuildersJvmKt$delete$$inlined$delete$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object delete$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersJvmKt$delete$2.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getDelete());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$delete$$inlined$delete$2
        };
        Type genericSuperclass = BuildersJvmKt$delete$$inlined$delete$2.class.getGenericSuperclass();
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

    private static final <T> Object get(@NotNull HttpClient httpClient, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$get$$inlined$get$1
        };
        Type genericSuperclass = BuildersJvmKt$get$$inlined$get$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object get$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersJvmKt$get$2.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$get$$inlined$get$2
        };
        Type genericSuperclass = BuildersJvmKt$get$$inlined$get$2.class.getGenericSuperclass();
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

    private static final <T> Object head(@NotNull HttpClient httpClient, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getHead());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$head$$inlined$head$1
        };
        Type genericSuperclass = BuildersJvmKt$head$$inlined$head$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object head$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersJvmKt$head$2.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getHead());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$head$$inlined$head$2
        };
        Type genericSuperclass = BuildersJvmKt$head$$inlined$head$2.class.getGenericSuperclass();
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

    private static final <T> Object options(@NotNull HttpClient httpClient, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getOptions());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$options$$inlined$options$1
        };
        Type genericSuperclass = BuildersJvmKt$options$$inlined$options$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object options$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersJvmKt$options$2.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getOptions());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$options$$inlined$options$2
        };
        Type genericSuperclass = BuildersJvmKt$options$$inlined$options$2.class.getGenericSuperclass();
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

    private static final <T> Object patch(@NotNull HttpClient httpClient, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPatch());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$patch$$inlined$patch$1
        };
        Type genericSuperclass = BuildersJvmKt$patch$$inlined$patch$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object patch$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersJvmKt$patch$2.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPatch());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$patch$$inlined$patch$2
        };
        Type genericSuperclass = BuildersJvmKt$patch$$inlined$patch$2.class.getGenericSuperclass();
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

    private static final <T> Object post(@NotNull HttpClient httpClient, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$post$$inlined$post$1
        };
        Type genericSuperclass = BuildersJvmKt$post$$inlined$post$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object post$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersJvmKt$post$2.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$post$$inlined$post$2
        };
        Type genericSuperclass = BuildersJvmKt$post$$inlined$post$2.class.getGenericSuperclass();
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

    private static final <T> Object put(@NotNull HttpClient httpClient, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPut());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$put$$inlined$put$1
        };
        Type genericSuperclass = BuildersJvmKt$put$$inlined$put$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object put$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = BuildersJvmKt$put$2.INSTANCE;
        }
        EmptyContent emptyContent = EmptyContent.INSTANCE;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url$default(httpRequestBuilder, "http", AndroidInfoHelpers.DEVICE_LOCALHOST, 0, "/", null, 16, null);
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPut());
        httpRequestBuilder.setBody(emptyContent);
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.BuildersJvmKt$put$$inlined$put$2
        };
        Type genericSuperclass = BuildersJvmKt$put$$inlined$put$2.class.getGenericSuperclass();
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
