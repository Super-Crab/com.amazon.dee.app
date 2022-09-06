package io.ktor.client.request.forms;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import io.ktor.http.Headers;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: formDsl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\u000e\u0010\u0011\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J2\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00028\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lio/ktor/client/request/forms/FormPart;", ExifInterface.GPS_DIRECTION_TRUE, "", "key", "", "value", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/Headers;", "(Ljava/lang/String;Ljava/lang/Object;Lio/ktor/http/Headers;)V", "getHeaders", "()Lio/ktor/http/Headers;", "getKey", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Object;Lio/ktor/http/Headers;)Lio/ktor/client/request/forms/FormPart;", "equals", "", "other", "hashCode", "", "toString", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class FormPart<T> {
    @NotNull
    private final Headers headers;
    @NotNull
    private final String key;
    @NotNull
    private final T value;

    public FormPart(@NotNull String key, @NotNull T value, @NotNull Headers headers) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        this.key = key;
        this.value = value;
        this.headers = headers;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ FormPart copy$default(FormPart formPart, String str, Object obj, Headers headers, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = formPart.key;
        }
        if ((i & 2) != 0) {
            obj = formPart.value;
        }
        if ((i & 4) != 0) {
            headers = formPart.headers;
        }
        return formPart.copy(str, obj, headers);
    }

    @NotNull
    public final String component1() {
        return this.key;
    }

    @NotNull
    public final T component2() {
        return this.value;
    }

    @NotNull
    public final Headers component3() {
        return this.headers;
    }

    @NotNull
    public final FormPart<T> copy(@NotNull String key, @NotNull T value, @NotNull Headers headers) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        return new FormPart<>(key, value, headers);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof FormPart)) {
                return false;
            }
            FormPart formPart = (FormPart) obj;
            return Intrinsics.areEqual(this.key, formPart.key) && Intrinsics.areEqual(this.value, formPart.value) && Intrinsics.areEqual(this.headers, formPart.headers);
        }
        return true;
    }

    @NotNull
    public final Headers getHeaders() {
        return this.headers;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    @NotNull
    public final T getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.key;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        T t = this.value;
        int hashCode2 = (hashCode + (t != null ? t.hashCode() : 0)) * 31;
        Headers headers = this.headers;
        if (headers != null) {
            i = headers.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FormPart(key=");
        outline107.append(this.key);
        outline107.append(", value=");
        outline107.append(this.value);
        outline107.append(", headers=");
        outline107.append(this.headers);
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ FormPart(String str, Object obj, Headers headers, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, obj, (i & 4) != 0 ? Headers.Companion.getEmpty() : headers);
    }
}
