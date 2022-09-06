package io.ktor.client.request.forms;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import io.ktor.http.ContentDisposition;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HeadersKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.PartData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.Input;
import kotlinx.io.core.PacketJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: formDsl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u001a\u0010\b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\n0\t\"\u0006\u0012\u0002\b\u00030\n¢\u0006\u0002\u0010\u000b\u001aE\u0010\f\u001a\u00020\u0006*\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001\u001a@\u0010\f\u001a\u00020\u0006*\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¨\u0006\u0014"}, d2 = {"formData", "", "Lio/ktor/http/content/PartData;", "block", "Lkotlin/Function1;", "Lio/ktor/client/request/forms/FormBuilder;", "", "Lkotlin/ExtensionFunctionType;", "values", "", "Lio/ktor/client/request/forms/FormPart;", "([Lio/ktor/client/request/forms/FormPart;)Ljava/util/List;", "append", "key", "", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/Headers;", "bodyBuilder", "Lkotlinx/io/core/BytePacketBuilder;", ContentDisposition.Parameters.FileName, "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class FormDslKt {
    public static final void append(@NotNull FormBuilder receiver$0, @NotNull String key, @NotNull String filename, @NotNull Function1<? super BytePacketBuilder, Unit> bodyBuilder) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(filename, "filename");
        Intrinsics.checkParameterIsNotNull(bodyBuilder, "bodyBuilder");
        String contentDisposition = HttpHeaders.INSTANCE.getContentDisposition();
        Headers headersOf = HeadersKt.headersOf(contentDisposition, "filename=" + filename);
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            bodyBuilder.mo12165invoke(BytePacketBuilder);
            receiver$0.append(new FormPart(key, BytePacketBuilder.build(), headersOf));
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    public static /* synthetic */ void append$default(FormBuilder receiver$0, String key, Headers headers, Function1 bodyBuilder, int i, Object obj) {
        if ((i & 2) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(bodyBuilder, "bodyBuilder");
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            bodyBuilder.mo12165invoke(BytePacketBuilder);
            receiver$0.append(new FormPart(key, BytePacketBuilder.build(), headers));
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    @NotNull
    public static final List<PartData> formData(@NotNull FormPart<?>... values) {
        PartData binaryItem;
        Intrinsics.checkParameterIsNotNull(values, "values");
        ArrayList arrayList = new ArrayList();
        for (FormPart<?> formPart : values) {
            String component1 = formPart.component1();
            Object component2 = formPart.component2();
            Headers component3 = formPart.component3();
            Headers.Companion companion = Headers.Companion;
            HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
            headersBuilder.append(HttpHeaders.INSTANCE.getContentDisposition(), "form-data;name=" + component1);
            headersBuilder.appendAll(component3);
            Headers mo10292build = headersBuilder.mo10292build();
            if (component2 instanceof String) {
                binaryItem = new PartData.FormItem((String) component2, FormDslKt$formData$1$part$1.INSTANCE, mo10292build);
            } else if (component2 instanceof Number) {
                binaryItem = new PartData.FormItem(component2.toString(), FormDslKt$formData$1$part$2.INSTANCE, mo10292build);
            } else if (component2 instanceof byte[]) {
                binaryItem = new PartData.BinaryItem(new FormDslKt$formData$1$part$3(component2), FormDslKt$formData$1$part$4.INSTANCE, mo10292build);
            } else if (!(component2 instanceof Input)) {
                throw new IllegalStateException(GeneratedOutlineSupport1.outline70("Unknown form content type: ", component2).toString());
            } else {
                binaryItem = new PartData.BinaryItem(new FormDslKt$formData$1$part$5(component2), FormDslKt$formData$1$part$6.INSTANCE, mo10292build);
            }
            arrayList.add(binaryItem);
        }
        return arrayList;
    }

    public static final void append(@NotNull FormBuilder receiver$0, @NotNull String key, @NotNull Headers headers, @NotNull Function1<? super BytePacketBuilder, Unit> bodyBuilder) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(bodyBuilder, "bodyBuilder");
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            bodyBuilder.mo12165invoke(BytePacketBuilder);
            receiver$0.append(new FormPart(key, BytePacketBuilder.build(), headers));
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    @NotNull
    public static final List<PartData> formData(@NotNull Function1<? super FormBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        FormBuilder formBuilder = new FormBuilder();
        block.mo12165invoke(formBuilder);
        List<FormPart<?>> build$ktor_client_core = formBuilder.build$ktor_client_core();
        if (build$ktor_client_core != null) {
            Object[] array = build$ktor_client_core.toArray(new FormPart[0]);
            if (array != null) {
                FormPart[] formPartArr = (FormPart[]) array;
                return formData((FormPart[]) Arrays.copyOf(formPartArr, formPartArr.length));
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
    }
}
