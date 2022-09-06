package io.ktor.util;

import java.security.SecureRandom;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: CryptoJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/security/SecureRandom;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class CryptoKt__CryptoJvmKt$nonceRandom$2 extends Lambda implements Function0<SecureRandom> {
    public static final CryptoKt__CryptoJvmKt$nonceRandom$2 INSTANCE = new CryptoKt__CryptoJvmKt$nonceRandom$2();

    CryptoKt__CryptoJvmKt$nonceRandom$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final SecureRandom mo12560invoke() {
        return new SecureRandom();
    }
}
