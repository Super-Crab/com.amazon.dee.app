package io.ktor.client.request.forms;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.StringsKt___StringsKt;
/* compiled from: FormDataContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002¨\u0006\u0002"}, d2 = {"generateBoundary", "", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class FormDataContentKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String generateBoundary() {
        String take;
        int checkRadix;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            int nextInt = Random.Default.nextInt();
            checkRadix = CharsKt__CharJVMKt.checkRadix(16);
            String num = Integer.toString(nextInt, checkRadix);
            Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
            sb.append(num);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        take = StringsKt___StringsKt.take(sb2, 70);
        return take;
    }
}
