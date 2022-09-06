package io.ktor.client.request.forms;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.routing.api.RouteParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.TypeBase;
import io.ktor.client.call.TypeInfo;
import io.ktor.client.call.UtilsKt;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestKt;
import io.ktor.http.HttpMethod;
import io.ktor.http.Parameters;
import io.ktor.http.content.PartData;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
/* compiled from: formBuilders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u001aL\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\f\u001aT\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001at\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001ap\u0010\u0016\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001aN\u0010\u0016\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001aF\u0010\u0016\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"submitForm", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/client/HttpClient;", "formData", "Lio/ktor/http/Parameters;", "encodeInQuery", "", "block", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/client/HttpClient;Lio/ktor/http/Parameters;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "url", "", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Lio/ktor/http/Parameters;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scheme", "host", "port", "", RouteParameter.PATH, "(Lio/ktor/client/HttpClient;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Lio/ktor/http/Parameters;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitFormWithBinaryData", "", "Lio/ktor/http/content/PartData;", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/ktor/client/HttpClient;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class FormBuildersKt {
    private static final <T> Object submitForm(@NotNull HttpClient httpClient, Parameters parameters, boolean z, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
        }
        function1.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitForm$$inlined$request$1
        };
        Type genericSuperclass = FormBuildersKt$submitForm$$inlined$request$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object submitForm$default(HttpClient httpClient, Parameters parameters, boolean z, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            parameters = Parameters.Companion.getEmpty();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function1 = FormBuildersKt$submitForm$2.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
        }
        function1.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitForm$$inlined$request$2
        };
        Type genericSuperclass = FormBuildersKt$submitForm$$inlined$request$2.class.getGenericSuperclass();
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

    private static final <T> Object submitFormWithBinaryData(@NotNull HttpClient httpClient, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list));
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitFormWithBinaryData$$inlined$request$1
        };
        Type genericSuperclass = FormBuildersKt$submitFormWithBinaryData$$inlined$request$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object submitFormWithBinaryData$default(HttpClient httpClient, List list, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = FormBuildersKt$submitFormWithBinaryData$2.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list));
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitFormWithBinaryData$$inlined$request$2
        };
        Type genericSuperclass = FormBuildersKt$submitFormWithBinaryData$$inlined$request$2.class.getGenericSuperclass();
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

    private static final <T> Object submitFormWithBinaryData(@NotNull HttpClient httpClient, String str, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list));
        HttpRequestKt.url(httpRequestBuilder, str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitFormWithBinaryData$$inlined$submitFormWithBinaryData$1
        };
        Type genericSuperclass = FormBuildersKt$submitFormWithBinaryData$$inlined$submitFormWithBinaryData$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object submitFormWithBinaryData$default(HttpClient httpClient, String str, List list, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = FormBuildersKt$submitFormWithBinaryData$5.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list));
        HttpRequestKt.url(httpRequestBuilder, str);
        function1.mo12165invoke(httpRequestBuilder);
        HttpClientCall httpClientCall = (HttpClientCall) GeneratedOutlineSupport1.outline17(0, httpClient, httpRequestBuilder, continuation, 1);
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitFormWithBinaryData$$inlined$submitFormWithBinaryData$2
        };
        Type genericSuperclass = FormBuildersKt$submitFormWithBinaryData$$inlined$submitFormWithBinaryData$2.class.getGenericSuperclass();
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

    private static final <T> Object submitForm(@NotNull HttpClient httpClient, String str, Parameters parameters, boolean z, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
        }
        HttpRequestKt.url(httpRequestBuilder, str);
        function1.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitForm$$inlined$submitForm$1
        };
        Type genericSuperclass = FormBuildersKt$submitForm$$inlined$submitForm$1.class.getGenericSuperclass();
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

    static /* synthetic */ Object submitForm$default(HttpClient httpClient, String str, Parameters parameters, boolean z, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            parameters = Parameters.Companion.getEmpty();
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            function1 = FormBuildersKt$submitForm$5.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
        }
        HttpRequestKt.url(httpRequestBuilder, str);
        function1.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitForm$$inlined$submitForm$2
        };
        Type genericSuperclass = FormBuildersKt$submitForm$$inlined$submitForm$2.class.getGenericSuperclass();
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

    private static final <T> Object submitFormWithBinaryData(@NotNull HttpClient httpClient, String str, String str2, int i, String str3, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list));
        HttpRequestKt.url$default(httpRequestBuilder, str, str2, i, str3, null, 16, null);
        function1.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitFormWithBinaryData$$inlined$submitFormWithBinaryData$3
        };
        Type genericSuperclass = FormBuildersKt$submitFormWithBinaryData$$inlined$submitFormWithBinaryData$3.class.getGenericSuperclass();
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

    static /* synthetic */ Object submitFormWithBinaryData$default(HttpClient httpClient, String str, String str2, int i, String str3, List list, Function1 function1, Continuation continuation, int i2, Object obj) {
        String str4 = (i2 & 1) != 0 ? "http" : str;
        String str5 = (i2 & 2) != 0 ? AndroidInfoHelpers.DEVICE_LOCALHOST : str2;
        int i3 = (i2 & 4) != 0 ? 80 : i;
        String str6 = (i2 & 8) != 0 ? "/" : str3;
        List emptyList = (i2 & 16) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list;
        FormBuildersKt$submitFormWithBinaryData$8 formBuildersKt$submitFormWithBinaryData$8 = (i2 & 32) != 0 ? FormBuildersKt$submitFormWithBinaryData$8.INSTANCE : function1;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(emptyList));
        HttpRequestKt.url$default(httpRequestBuilder, str4, str5, i3, str6, null, 16, null);
        formBuildersKt$submitFormWithBinaryData$8.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitFormWithBinaryData$$inlined$submitFormWithBinaryData$4
        };
        Type genericSuperclass = FormBuildersKt$submitFormWithBinaryData$$inlined$submitFormWithBinaryData$4.class.getGenericSuperclass();
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

    private static final <T> Object submitForm(@NotNull HttpClient httpClient, String str, String str2, int i, String str3, Parameters parameters, boolean z, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super T> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
        }
        HttpRequestKt.url$default(httpRequestBuilder, str, str2, i, str3, null, 16, null);
        function1.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitForm$$inlined$submitForm$3
        };
        Type genericSuperclass = FormBuildersKt$submitForm$$inlined$submitForm$3.class.getGenericSuperclass();
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

    static /* synthetic */ Object submitForm$default(HttpClient httpClient, String str, String str2, int i, String str3, Parameters parameters, boolean z, Function1 function1, Continuation continuation, int i2, Object obj) {
        String str4 = (i2 & 1) != 0 ? "http" : str;
        String str5 = (i2 & 2) != 0 ? AndroidInfoHelpers.DEVICE_LOCALHOST : str2;
        int i3 = (i2 & 4) != 0 ? 80 : i;
        String str6 = (i2 & 8) != 0 ? "/" : str3;
        Parameters empty = (i2 & 16) != 0 ? Parameters.Companion.getEmpty() : parameters;
        boolean z2 = (i2 & 32) != 0 ? false : z;
        FormBuildersKt$submitForm$8 formBuildersKt$submitForm$8 = (i2 & 64) != 0 ? FormBuildersKt$submitForm$8.INSTANCE : function1;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z2) {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(empty);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
            httpRequestBuilder.setBody(new FormDataContent(empty));
        }
        HttpRequestKt.url$default(httpRequestBuilder, str4, str5, i3, str6, null, 16, null);
        formBuildersKt$submitForm$8.mo12165invoke(httpRequestBuilder);
        InlineMarker.mark(0);
        Object call = UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(1);
        HttpClientCall httpClientCall = (HttpClientCall) call;
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitForm$$inlined$submitForm$4
        };
        Type genericSuperclass = FormBuildersKt$submitForm$$inlined$submitForm$4.class.getGenericSuperclass();
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
