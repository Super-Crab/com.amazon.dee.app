package io.ktor.http;

import com.amazon.alexa.mobilytics.configuration.Config;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HeaderValueWithParameters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u0003J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lio/ktor/http/HeaderValueWithParameters;", "", "content", "", "parameters", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/util/List;)V", "getContent", "()Ljava/lang/String;", "getParameters", "()Ljava/util/List;", "parameter", "name", "toString", "Companion", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class HeaderValueWithParameters {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String content;
    @NotNull
    private final List<HeaderValueParam> parameters;

    /* compiled from: HeaderValueWithParameters.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J<\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u001e\u0010\u0007\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0004\u0012\u0002H\u00040\bH\u0086\b¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/http/HeaderValueWithParameters$Companion;", "", "()V", "parse", "R", "value", "", "init", "Lkotlin/Function2;", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public final <R> R parse(@NotNull String value, @NotNull Function2<? super String, ? super List<HeaderValueParam>, ? extends R> init) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            Intrinsics.checkParameterIsNotNull(init, "init");
            HeaderValue headerValue = (HeaderValue) CollectionsKt.single((List<? extends Object>) HttpHeaderValueParserKt.parseHeaderValue(value));
            return init.mo12248invoke(headerValue.getValue(), headerValue.getParams());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public HeaderValueWithParameters(@NotNull String content, @NotNull List<HeaderValueParam> parameters) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        this.content = content;
        this.parameters = parameters;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final String getContent() {
        return this.content;
    }

    @NotNull
    public final List<HeaderValueParam> getParameters() {
        return this.parameters;
    }

    @Nullable
    public final String parameter(@NotNull String name) {
        Object obj;
        boolean equals;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Iterator<T> it2 = this.parameters.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            equals = StringsKt__StringsJVMKt.equals(((HeaderValueParam) obj).getName(), name, true);
            if (equals) {
                break;
            }
        }
        HeaderValueParam headerValueParam = (HeaderValueParam) obj;
        if (headerValueParam != null) {
            return headerValueParam.getValue();
        }
        return null;
    }

    @NotNull
    public String toString() {
        if (this.parameters.isEmpty()) {
            return this.content;
        }
        int length = this.content.length();
        int i = 0;
        for (HeaderValueParam headerValueParam : this.parameters) {
            i += headerValueParam.getValue().length() + headerValueParam.getName().length() + 3;
        }
        StringBuilder sb = new StringBuilder(length + i);
        sb.append(this.content);
        int size = this.parameters.size();
        for (int i2 = 0; i2 < size; i2++) {
            HeaderValueParam headerValueParam2 = this.parameters.get(i2);
            String component1 = headerValueParam2.component1();
            String component2 = headerValueParam2.component2();
            sb.append("; ");
            sb.append(component1);
            sb.append(Config.Compare.EQUAL_TO);
            if (HeaderValueWithParametersKt.checkNeedEscape(component2)) {
                sb.append(HeaderValueWithParametersKt.quote(component2));
            } else {
                sb.append(component2);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder(size).appl…\n            }.toString()");
        return sb2;
    }

    public /* synthetic */ HeaderValueWithParameters(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }
}
