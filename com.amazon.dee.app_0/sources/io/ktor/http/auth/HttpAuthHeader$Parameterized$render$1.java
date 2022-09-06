package io.ktor.http.auth;

import io.ktor.http.HeaderValueParam;
import io.ktor.http.auth.HttpAuthHeader;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpAuthHeader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/http/HeaderValueParam;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpAuthHeader$Parameterized$render$1 extends Lambda implements Function1<HeaderValueParam, String> {
    final /* synthetic */ HeaderValueEncoding $encoding;
    final /* synthetic */ HttpAuthHeader.Parameterized this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpAuthHeader$Parameterized$render$1(HttpAuthHeader.Parameterized parameterized, HeaderValueEncoding headerValueEncoding) {
        super(1);
        this.this$0 = parameterized;
        this.$encoding = headerValueEncoding;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12165invoke(@NotNull HeaderValueParam it2) {
        String encode;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        StringBuilder sb = new StringBuilder();
        sb.append(it2.getName());
        sb.append(Chars.EQ);
        encode = this.this$0.encode(it2.getValue(), this.$encoding);
        sb.append(encode);
        return sb.toString();
    }
}
