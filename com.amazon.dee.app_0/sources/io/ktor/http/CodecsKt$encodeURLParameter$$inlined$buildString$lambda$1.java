package io.ktor.http;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Codecs.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "io/ktor/http/CodecsKt$encodeURLParameter$1$1"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CodecsKt$encodeURLParameter$$inlined$buildString$lambda$1 extends Lambda implements Function1<Byte, Unit> {
    final /* synthetic */ boolean $spaceToPlus$inlined;
    final /* synthetic */ StringBuilder $this_buildString;
    final /* synthetic */ String $this_encodeURLParameter$inlined;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CodecsKt$encodeURLParameter$$inlined$buildString$lambda$1(StringBuilder sb, String str, boolean z) {
        super(1);
        this.$this_buildString = sb;
        this.$this_encodeURLParameter$inlined = str;
        this.$spaceToPlus$inlined = z;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Byte b) {
        invoke(b.byteValue());
        return Unit.INSTANCE;
    }

    public final void invoke(byte b) {
        List list;
        List list2;
        String percentEncode;
        list = CodecsKt.URL_ALPHABET;
        if (!list.contains(Byte.valueOf(b))) {
            list2 = CodecsKt.OAUTH_SYMBOLS;
            if (!list2.contains(Byte.valueOf(b))) {
                if (this.$spaceToPlus$inlined && b == ((byte) 32)) {
                    this.$this_buildString.append('+');
                    return;
                }
                StringBuilder sb = this.$this_buildString;
                percentEncode = CodecsKt.percentEncode(b);
                sb.append(percentEncode);
                return;
            }
        }
        this.$this_buildString.append((char) b);
    }
}
