package io.ktor.http;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.util.StringValuesBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: Headers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lio/ktor/http/HeadersBuilder;", "Lio/ktor/util/StringValuesBuilder;", "size", "", "(I)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lio/ktor/http/Headers;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HeadersBuilder extends StringValuesBuilder {
    public HeadersBuilder() {
        this(0, 1, null);
    }

    public HeadersBuilder(int i) {
        super(true, i);
    }

    public /* synthetic */ HeadersBuilder(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 8 : i);
    }

    @Override // io.ktor.util.StringValuesBuilder
    @NotNull
    /* renamed from: build */
    public Headers mo10292build() {
        if (!getBuilt()) {
            setBuilt(true);
            return new HeadersImpl(getValues());
        }
        throw new IllegalArgumentException("HeadersBuilder can only build a single Headers instance".toString());
    }
}
