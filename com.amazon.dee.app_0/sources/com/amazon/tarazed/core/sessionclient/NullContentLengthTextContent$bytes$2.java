package com.amazon.tarazed.core.sessionclient;

import io.ktor.http.ContentTypesKt;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import kotlinx.io.charsets.CharsetJVMKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NullContentLengthTextContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class NullContentLengthTextContent$bytes$2 extends Lambda implements Function0<byte[]> {
    final /* synthetic */ NullContentLengthTextContent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NullContentLengthTextContent$bytes$2(NullContentLengthTextContent nullContentLengthTextContent) {
        super(0);
        this.this$0 = nullContentLengthTextContent;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final byte[] mo12560invoke() {
        String text = this.this$0.getText();
        Charset charset = ContentTypesKt.charset(this.this$0.getContentType());
        if (charset == null) {
            charset = Charsets.UTF_8;
        }
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkExpressionValueIsNotNull(newEncoder, "charset.newEncoder()");
        return CharsetJVMKt.encodeToByteArray(newEncoder, text, 0, text.length());
    }
}
