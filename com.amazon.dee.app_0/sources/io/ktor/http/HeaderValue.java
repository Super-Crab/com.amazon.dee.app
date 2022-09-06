package io.ktor.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpHeaderValueParser.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lio/ktor/http/HeaderValue;", "", "value", "", "params", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/util/List;)V", "getParams", "()Ljava/util/List;", "quality", "", "getQuality", "()D", "getValue", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HeaderValue {
    @NotNull
    private final List<HeaderValueParam> params;
    private final double quality;
    @NotNull
    private final String value;

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
        r3 = kotlin.text.StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public HeaderValue(@org.jetbrains.annotations.NotNull java.lang.String r3, @org.jetbrains.annotations.NotNull java.util.List<io.ktor.http.HeaderValueParam> r4) {
        /*
            r2 = this;
            java.lang.String r0 = "value"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            java.lang.String r0 = "params"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            r2.<init>()
            r2.value = r3
            r2.params = r4
            java.util.List<io.ktor.http.HeaderValueParam> r3 = r2.params
            java.util.Iterator r3 = r3.iterator()
        L17:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L31
            java.lang.Object r4 = r3.next()
            r0 = r4
            io.ktor.http.HeaderValueParam r0 = (io.ktor.http.HeaderValueParam) r0
            java.lang.String r0 = r0.getName()
            java.lang.String r1 = "q"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L17
            goto L32
        L31:
            r4 = 0
        L32:
            io.ktor.http.HeaderValueParam r4 = (io.ktor.http.HeaderValueParam) r4
            if (r4 == 0) goto L47
            java.lang.String r3 = r4.getValue()
            if (r3 == 0) goto L47
            java.lang.Double r3 = kotlin.text.StringsKt.toDoubleOrNull(r3)
            if (r3 == 0) goto L47
            double r3 = r3.doubleValue()
            goto L49
        L47:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L49:
            r2.quality = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.HeaderValue.<init>(java.lang.String, java.util.List):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ HeaderValue copy$default(HeaderValue headerValue, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = headerValue.value;
        }
        if ((i & 2) != 0) {
            list = headerValue.params;
        }
        return headerValue.copy(str, list);
    }

    @NotNull
    public final String component1() {
        return this.value;
    }

    @NotNull
    public final List<HeaderValueParam> component2() {
        return this.params;
    }

    @NotNull
    public final HeaderValue copy(@NotNull String value, @NotNull List<HeaderValueParam> params) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(params, "params");
        return new HeaderValue(value, params);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof HeaderValue)) {
                return false;
            }
            HeaderValue headerValue = (HeaderValue) obj;
            return Intrinsics.areEqual(this.value, headerValue.value) && Intrinsics.areEqual(this.params, headerValue.params);
        }
        return true;
    }

    @NotNull
    public final List<HeaderValueParam> getParams() {
        return this.params;
    }

    public final double getQuality() {
        return this.quality;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.value;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<HeaderValueParam> list = this.params;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HeaderValue(value=");
        outline107.append(this.value);
        outline107.append(", params=");
        return GeneratedOutlineSupport1.outline95(outline107, this.params, ")");
    }

    public /* synthetic */ HeaderValue(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }
}
