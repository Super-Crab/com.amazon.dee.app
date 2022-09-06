package io.ktor.client.request.forms;

import androidx.exifinterface.media.ExifInterface;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.http.Headers;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: formDsl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0006\u001a\u00020\u0007\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u0005J/\u0010\u0006\u001a\u00020\u0007\"\b\b\u0000\u0010\b*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u0002H\b2\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0017\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0011H\u0000¢\u0006\u0002\b\u0012R\u0018\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lio/ktor/client/request/forms/FormBuilder;", "", "()V", "parts", "", "Lio/ktor/client/request/forms/FormPart;", "append", "", ExifInterface.GPS_DIRECTION_TRUE, "part", "key", "", "value", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/Headers;", "(Ljava/lang/String;Ljava/lang/Object;Lio/ktor/http/Headers;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "build$ktor_client_core", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class FormBuilder {
    private final List<FormPart<?>> parts = new ArrayList();

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, Object obj, Headers headers, int i, Object obj2) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, obj, headers);
    }

    public final <T> void append(@NotNull String key, @NotNull T value, @NotNull Headers headers) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        this.parts.add(new FormPart<>(key, value, headers));
    }

    @NotNull
    public final List<FormPart<?>> build$ktor_client_core() {
        return this.parts;
    }

    public final <T> void append(@NotNull FormPart<T> part) {
        Intrinsics.checkParameterIsNotNull(part, "part");
        this.parts.add(part);
    }
}
