package io.ktor.util;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CryptoJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "e", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CryptoKt__CryptoJvmKt$getDigestFunction$1 extends Lambda implements Function1<String, byte[]> {
    final /* synthetic */ String $algorithm;
    final /* synthetic */ String $salt;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CryptoKt__CryptoJvmKt$getDigestFunction$1(String str, String str2) {
        super(1);
        this.$algorithm = str;
        this.$salt = str2;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final byte[] mo12165invoke(@NotNull String e) {
        byte[] digest$CryptoKt__CryptoJvmKt;
        Intrinsics.checkParameterIsNotNull(e, "e");
        digest$CryptoKt__CryptoJvmKt = CryptoKt__CryptoJvmKt.getDigest$CryptoKt__CryptoJvmKt(e, this.$algorithm, this.$salt);
        return digest$CryptoKt__CryptoJvmKt;
    }
}
