package io.ktor.http.content;

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
/* compiled from: TextContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class TextContent$bytes$2 extends Lambda implements Function0<byte[]> {
    final /* synthetic */ TextContent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextContent$bytes$2(TextContent textContent) {
        super(0);
        this.this$0 = textContent;
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
