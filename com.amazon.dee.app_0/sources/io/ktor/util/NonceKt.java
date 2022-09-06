package io.ktor.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.NonCancellable;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* compiled from: Nonce.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0010\u001a\u00020\u0011H\u0000\u001a\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0007H\u0002\u001a\b\u0010\u0015\u001a\u00020\u0013H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"INSECURE_NONCE_COUNT_FACTOR", "", "NONCE_SIZE_IN_BYTES", "NonceGeneratorCoroutineName", "Lkotlinx/coroutines/CoroutineName;", "SECURE_NONCE_COUNT", "SECURE_RANDOM_PROVIDER_NAME", "", "SECURE_RESEED_PERIOD", NonceKt.SHA1PRNG, "nonceGeneratorJob", "Lkotlinx/coroutines/Job;", "seedChannel", "Lkotlinx/coroutines/channels/Channel;", "getSeedChannel", "()Lkotlinx/coroutines/channels/Channel;", "ensureNonceGeneratorRunning", "", "getInstanceOrNull", "Ljava/security/SecureRandom;", "name", "lookupSecureRandom", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class NonceKt {
    private static final int INSECURE_NONCE_COUNT_FACTOR = 4;
    private static final int NONCE_SIZE_IN_BYTES = 8;
    private static final CoroutineName NonceGeneratorCoroutineName;
    private static final int SECURE_NONCE_COUNT = 8;
    private static final String SECURE_RANDOM_PROVIDER_NAME;
    private static final int SECURE_RESEED_PERIOD = 30000;
    private static final String SHA1PRNG = "SHA1PRNG";
    private static final Job nonceGeneratorJob;
    @NotNull
    private static final Channel<String> seedChannel;

    static {
        String property = System.getProperty("io.ktor.random.secure.random.provider");
        if (property == null) {
            property = "NativePRNGNonBlocking";
        }
        SECURE_RANDOM_PROVIDER_NAME = property;
        seedChannel = ChannelKt.Channel(1024);
        NonceGeneratorCoroutineName = new CoroutineName("nonce-generator");
        nonceGeneratorJob = BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getIO().plus(NonCancellable.INSTANCE).plus(NonceGeneratorCoroutineName), CoroutineStart.LAZY, new NonceKt$nonceGeneratorJob$1(null));
    }

    public static final void ensureNonceGeneratorRunning() {
        nonceGeneratorJob.start();
    }

    private static final SecureRandom getInstanceOrNull(String str) {
        try {
            return SecureRandom.getInstance(str);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    @NotNull
    public static final Channel<String> getSeedChannel() {
        return seedChannel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SecureRandom lookupSecureRandom() {
        SecureRandom instanceOrNull = getInstanceOrNull(SECURE_RANDOM_PROVIDER_NAME);
        if (instanceOrNull != null) {
            return instanceOrNull;
        }
        Logger logger = LoggerFactory.getLogger("io.ktor.util.random");
        logger.warn(SECURE_RANDOM_PROVIDER_NAME + " is not found, fallback to SHA1PRNG");
        SecureRandom instanceOrNull2 = getInstanceOrNull(SHA1PRNG);
        if (instanceOrNull2 == null) {
            throw new IllegalStateException("No SecureRandom implementation found".toString());
        }
        return instanceOrNull2;
    }
}
