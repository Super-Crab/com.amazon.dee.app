package io.ktor.util;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StatelessHmacNonceManager.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B3\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\u0002\u0010\nB1\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\u0002\u0010\rJ\u0011\u0010\u0018\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/util/StatelessHmacNonceManager;", "Lio/ktor/util/NonceManager;", "key", "", "algorithm", "", "timeoutMillis", "", "nonceGenerator", "Lkotlin/Function0;", "([BLjava/lang/String;JLkotlin/jvm/functions/Function0;)V", "keySpec", "Ljavax/crypto/spec/SecretKeySpec;", "(Ljavax/crypto/spec/SecretKeySpec;Ljava/lang/String;JLkotlin/jvm/functions/Function0;)V", "getAlgorithm", "()Ljava/lang/String;", "getKeySpec", "()Ljavax/crypto/spec/SecretKeySpec;", "macLength", "", "getNonceGenerator", "()Lkotlin/jvm/functions/Function0;", "getTimeoutMillis", "()J", "newNonce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyNonce", "", "nonce", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class StatelessHmacNonceManager implements NonceManager {
    @NotNull
    private final String algorithm;
    @NotNull
    private final SecretKeySpec keySpec;
    private final int macLength;
    @NotNull
    private final Function0<String> nonceGenerator;
    private final long timeoutMillis;

    /* compiled from: StatelessHmacNonceManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.ktor.util.StatelessHmacNonceManager$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass1 extends Lambda implements Function0<String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* renamed from: invoke  reason: collision with other method in class */
        public final String mo12560invoke() {
            return CryptoKt.generateNonce();
        }
    }

    /* compiled from: StatelessHmacNonceManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.ktor.util.StatelessHmacNonceManager$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass2 extends Lambda implements Function0<String> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* renamed from: invoke  reason: collision with other method in class */
        public final String mo12560invoke() {
            return CryptoKt.generateNonce();
        }
    }

    public StatelessHmacNonceManager(@NotNull SecretKeySpec keySpec, @NotNull String algorithm, long j, @NotNull Function0<String> nonceGenerator) {
        Intrinsics.checkParameterIsNotNull(keySpec, "keySpec");
        Intrinsics.checkParameterIsNotNull(algorithm, "algorithm");
        Intrinsics.checkParameterIsNotNull(nonceGenerator, "nonceGenerator");
        this.keySpec = keySpec;
        this.algorithm = algorithm;
        this.timeoutMillis = j;
        this.nonceGenerator = nonceGenerator;
        Mac mac = Mac.getInstance(this.algorithm);
        mac.init(this.keySpec);
        Intrinsics.checkExpressionValueIsNotNull(mac, "mac");
        this.macLength = mac.getMacLength();
    }

    @NotNull
    public final String getAlgorithm() {
        return this.algorithm;
    }

    @NotNull
    public final SecretKeySpec getKeySpec() {
        return this.keySpec;
    }

    @NotNull
    public final Function0<String> getNonceGenerator() {
        return this.nonceGenerator;
    }

    public final long getTimeoutMillis() {
        return this.timeoutMillis;
    }

    @Override // io.ktor.util.NonceManager
    @Nullable
    public Object newNonce(@NotNull Continuation<? super String> continuation) {
        int checkRadix;
        String padStart;
        String mo12560invoke = this.nonceGenerator.mo12560invoke();
        long nanoTime = System.nanoTime();
        checkRadix = CharsKt__CharJVMKt.checkRadix(16);
        String l = Long.toString(nanoTime, checkRadix);
        Intrinsics.checkExpressionValueIsNotNull(l, "java.lang.Long.toString(this, checkRadix(radix))");
        padStart = StringsKt__StringsKt.padStart(l, 16, '0');
        Mac mac = Mac.getInstance(this.algorithm);
        mac.init(this.keySpec);
        String str = mo12560invoke + JsonReaderKt.COLON + padStart;
        Charset charset = Charsets.ISO_8859_1;
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            mac.update(bytes);
            byte[] doFinal = mac.doFinal();
            Intrinsics.checkExpressionValueIsNotNull(doFinal, "Mac.getInstance(algorith…9_1))\n        }.doFinal()");
            return mo12560invoke + '+' + padStart + '+' + CryptoKt.hex(doFinal);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @Override // io.ktor.util.NonceManager
    @Nullable
    public Object verifyNonce(@NotNull String str, @NotNull Continuation<? super Boolean> continuation) {
        List split$default;
        int checkRadix;
        boolean z = true;
        split$default = StringsKt__StringsKt.split$default((CharSequence) str, new char[]{'+'}, false, 0, 6, (Object) null);
        if (split$default.size() != 3) {
            return Boxing.boxBoolean(false);
        }
        String str2 = (String) split$default.get(0);
        String str3 = (String) split$default.get(1);
        String str4 = (String) split$default.get(2);
        if (str2.length() >= 8 && str4.length() == this.macLength * 2 && str3.length() == 16) {
            checkRadix = CharsKt__CharJVMKt.checkRadix(16);
            if (TimeUnit.MILLISECONDS.toNanos(this.timeoutMillis) + Long.parseLong(str3, checkRadix) < System.nanoTime()) {
                return Boxing.boxBoolean(false);
            }
            Mac mac = Mac.getInstance(this.algorithm);
            mac.init(this.keySpec);
            String str5 = str2 + JsonReaderKt.COLON + str3;
            Charset charset = Charsets.ISO_8859_1;
            if (str5 != null) {
                byte[] bytes = str5.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                mac.update(bytes);
                byte[] doFinal = mac.doFinal();
                Intrinsics.checkExpressionValueIsNotNull(doFinal, "Mac.getInstance(algorith…9_1))\n        }.doFinal()");
                String hex = CryptoKt.hex(doFinal);
                int min = Math.min(hex.length(), str4.length());
                int i = 0;
                for (int i2 = 0; i2 < min; i2++) {
                    if (hex.charAt(i2) == str4.charAt(i2)) {
                        i++;
                    }
                }
                if (i != this.macLength * 2) {
                    z = false;
                }
                return Boxing.boxBoolean(z);
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        return Boxing.boxBoolean(false);
    }

    public /* synthetic */ StatelessHmacNonceManager(SecretKeySpec secretKeySpec, String str, long j, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(secretKeySpec, (i & 2) != 0 ? "HmacSHA256" : str, (i & 4) != 0 ? 60000L : j, (i & 8) != 0 ? AnonymousClass1.INSTANCE : function0);
    }

    public /* synthetic */ StatelessHmacNonceManager(byte[] bArr, String str, long j, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, (i & 2) != 0 ? "HmacSHA256" : str, (i & 4) != 0 ? 60000L : j, (i & 8) != 0 ? AnonymousClass2.INSTANCE : function0);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StatelessHmacNonceManager(@NotNull byte[] key, @NotNull String algorithm, long j, @NotNull Function0<String> nonceGenerator) {
        this(new SecretKeySpec(key, algorithm), algorithm, j, nonceGenerator);
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(algorithm, "algorithm");
        Intrinsics.checkParameterIsNotNull(nonceGenerator, "nonceGenerator");
    }
}
