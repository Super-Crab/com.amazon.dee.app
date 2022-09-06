package io.ktor.http;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Codecs.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "io/ktor/http/CodecsKt$encodeURLQueryComponent$1$1"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CodecsKt$encodeURLQueryComponent$$inlined$buildString$lambda$1 extends Lambda implements Function1<Byte, Unit> {
    final /* synthetic */ Charset $charset$inlined;
    final /* synthetic */ boolean $encodeFull$inlined;
    final /* synthetic */ boolean $spaceToPlus$inlined;
    final /* synthetic */ StringBuilder $this_buildString;
    final /* synthetic */ String $this_encodeURLQueryComponent$inlined;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CodecsKt$encodeURLQueryComponent$$inlined$buildString$lambda$1(StringBuilder sb, String str, Charset charset, boolean z, boolean z2) {
        super(1);
        this.$this_buildString = sb;
        this.$this_encodeURLQueryComponent$inlined = str;
        this.$charset$inlined = charset;
        this.$spaceToPlus$inlined = z;
        this.$encodeFull$inlined = z2;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Byte b) {
        invoke(b.byteValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
        if (r0.contains(java.lang.Byte.valueOf(r3)) != false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void invoke(byte r3) {
        /*
            r2 = this;
            r0 = 32
            byte r0 = (byte) r0
            if (r3 != r0) goto L19
            boolean r3 = r2.$spaceToPlus$inlined
            if (r3 == 0) goto L11
            java.lang.StringBuilder r3 = r2.$this_buildString
            r0 = 43
            r3.append(r0)
            goto L4a
        L11:
            java.lang.StringBuilder r3 = r2.$this_buildString
            java.lang.String r0 = "%20"
            r3.append(r0)
            goto L4a
        L19:
            java.util.List r0 = io.ktor.http.CodecsKt.access$getURL_ALPHABET$p()
            java.lang.Byte r1 = java.lang.Byte.valueOf(r3)
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L44
            boolean r0 = r2.$encodeFull$inlined
            if (r0 != 0) goto L3a
            java.util.List r0 = io.ktor.http.CodecsKt.access$getURL_PROTOCOL_PART$p()
            java.lang.Byte r1 = java.lang.Byte.valueOf(r3)
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L3a
            goto L44
        L3a:
            java.lang.StringBuilder r0 = r2.$this_buildString
            java.lang.String r3 = io.ktor.http.CodecsKt.access$percentEncode(r3)
            r0.append(r3)
            goto L4a
        L44:
            java.lang.StringBuilder r0 = r2.$this_buildString
            char r3 = (char) r3
            r0.append(r3)
        L4a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.CodecsKt$encodeURLQueryComponent$$inlined$buildString$lambda$1.invoke(byte):void");
    }
}
