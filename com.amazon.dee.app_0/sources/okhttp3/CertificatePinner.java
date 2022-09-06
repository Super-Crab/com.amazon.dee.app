package okhttp3;

import com.amazon.alexa.redesign.view.carousel.ChipIconTitleViewHolder;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.amazon.communication.remotesetting.StageSwitchService;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CertificatePinner.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 !2\u00020\u0001:\u0003 !\"B\u001f\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J)\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\u0000¢\u0006\u0002\b\u0012J)\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0016J\u001c\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0017\u0010\u001e\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\b\u001fR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lokhttp3/CertificatePinner;", "", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "(Ljava/util/Set;Lokhttp3/internal/tls/CertificateChainCleaner;)V", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "check", "", StageSwitchService.CONFIG_HOSTNAME_KEY, "", "cleanedPeerCertificatesFn", "Lkotlin/Function0;", "", "Ljava/security/cert/X509Certificate;", "check$okhttp", "peerCertificates", "", "Ljava/security/cert/Certificate;", "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "equals", "", "other", "findMatchingPins", "findMatchingPins$okhttp", "hashCode", "", "withCertificateChainCleaner", "withCertificateChainCleaner$okhttp", "Builder", "Companion", "Pin", "okhttp"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CertificatePinner {
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final CertificatePinner DEFAULT = new Builder().build();
    @Nullable
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    /* compiled from: CertificatePinner.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\t\"\u00020\b¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/CertificatePinner$Builder;", "", "()V", "pins", "", "Lokhttp3/CertificatePinner$Pin;", BulkOperationType.add, "pattern", "", "", "(Ljava/lang/String;[Ljava/lang/String;)Lokhttp3/CertificatePinner$Builder;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lokhttp3/CertificatePinner;", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Builder {
        private final List<Pin> pins = new ArrayList();

        @NotNull
        public final Builder add(@NotNull String pattern, @NotNull String... pins) {
            Intrinsics.checkParameterIsNotNull(pattern, "pattern");
            Intrinsics.checkParameterIsNotNull(pins, "pins");
            for (String str : pins) {
                this.pins.add(CertificatePinner.Companion.newPin$okhttp(pattern, str));
            }
            return this;
        }

        @NotNull
        public final CertificatePinner build() {
            Set set;
            set = CollectionsKt___CollectionsKt.toSet(this.pins);
            return new CertificatePinner(set, null);
        }
    }

    /* compiled from: CertificatePinner.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0002\b\nJ\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0011\u0010\r\u001a\u00020\u000e*\u00020\u000fH\u0000¢\u0006\u0002\b\u0010J\u0011\u0010\u0011\u001a\u00020\u000e*\u00020\u000fH\u0000¢\u0006\u0002\b\u0012R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lokhttp3/CertificatePinner$Companion;", "", "()V", ChipIconTitleViewHolder.STATE_DEFAULT, "Lokhttp3/CertificatePinner;", "newPin", "Lokhttp3/CertificatePinner$Pin;", "pattern", "", "pin", "newPin$okhttp", "certificate", "Ljava/security/cert/Certificate;", "toSha1ByteString", "Lokio/ByteString;", "Ljava/security/cert/X509Certificate;", "toSha1ByteString$okhttp", "toSha256ByteString", "toSha256ByteString$okhttp", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:5:0x0021, code lost:
            if (r3 != (-1)) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0036, code lost:
            if (r3 != (-1)) goto L34;
         */
        /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00ac  */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final okhttp3.CertificatePinner.Pin newPin$okhttp(@org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull java.lang.String r13) {
            /*
                r11 = this;
                java.lang.String r0 = "pattern"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
                java.lang.String r0 = "pin"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r0)
                r0 = 0
                r1 = 2
                r2 = 0
                java.lang.String r3 = "*."
                boolean r3 = kotlin.text.StringsKt.startsWith$default(r12, r3, r2, r1, r0)
                r4 = -1
                if (r3 == 0) goto L23
                r7 = 1
                r8 = 0
                r9 = 4
                r10 = 0
                java.lang.String r6 = "*"
                r5 = r12
                int r3 = kotlin.text.StringsKt.indexOf$default(r5, r6, r7, r8, r9, r10)
                if (r3 == r4) goto L45
            L23:
                java.lang.String r3 = "**."
                boolean r3 = kotlin.text.StringsKt.startsWith$default(r12, r3, r2, r1, r0)
                if (r3 == 0) goto L38
                r7 = 2
                r8 = 0
                r9 = 4
                r10 = 0
                java.lang.String r6 = "*"
                r5 = r12
                int r3 = kotlin.text.StringsKt.indexOf$default(r5, r6, r7, r8, r9, r10)
                if (r3 == r4) goto L45
            L38:
                r7 = 0
                r8 = 0
                r9 = 6
                r10 = 0
                java.lang.String r6 = "*"
                r5 = r12
                int r3 = kotlin.text.StringsKt.indexOf$default(r5, r6, r7, r8, r9, r10)
                if (r3 != r4) goto L47
            L45:
                r3 = 1
                goto L48
            L47:
                r3 = r2
            L48:
                if (r3 == 0) goto Lac
                java.lang.String r3 = okhttp3.internal.HostnamesKt.toCanonicalHost(r12)
                if (r3 == 0) goto La0
                java.lang.String r12 = "sha1/"
                boolean r4 = kotlin.text.StringsKt.startsWith$default(r13, r12, r2, r1, r0)
                java.lang.String r5 = "(this as java.lang.String).substring(startIndex)"
                if (r4 == 0) goto L73
                okio.ByteString$Companion r0 = okio.ByteString.Companion
                r1 = 5
                java.lang.String r13 = r13.substring(r1)
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r5)
                okio.ByteString r13 = r0.decodeBase64(r13)
                if (r13 != 0) goto L6d
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L6d:
                okhttp3.CertificatePinner$Pin r0 = new okhttp3.CertificatePinner$Pin
                r0.<init>(r3, r12, r13)
                goto L93
            L73:
                java.lang.String r12 = "sha256/"
                boolean r0 = kotlin.text.StringsKt.startsWith$default(r13, r12, r2, r1, r0)
                if (r0 == 0) goto L94
                okio.ByteString$Companion r0 = okio.ByteString.Companion
                r1 = 7
                java.lang.String r13 = r13.substring(r1)
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r5)
                okio.ByteString r13 = r0.decodeBase64(r13)
                if (r13 != 0) goto L8e
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L8e:
                okhttp3.CertificatePinner$Pin r0 = new okhttp3.CertificatePinner$Pin
                r0.<init>(r3, r12, r13)
            L93:
                return r0
            L94:
                java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "pins must start with 'sha256/' or 'sha1/': "
                java.lang.String r13 = com.android.tools.r8.GeneratedOutlineSupport1.outline72(r0, r13)
                r12.<init>(r13)
                throw r12
            La0:
                java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "Invalid pattern: "
                java.lang.String r12 = com.android.tools.r8.GeneratedOutlineSupport1.outline72(r0, r12)
                r13.<init>(r12)
                throw r13
            Lac:
                java.lang.String r13 = "Unexpected pattern: "
                java.lang.String r12 = com.android.tools.r8.GeneratedOutlineSupport1.outline72(r13, r12)
                java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
                java.lang.String r12 = r12.toString()
                r13.<init>(r12)
                throw r13
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.CertificatePinner.Companion.newPin$okhttp(java.lang.String, java.lang.String):okhttp3.CertificatePinner$Pin");
        }

        @JvmStatic
        @NotNull
        public final String pin(@NotNull Certificate certificate) {
            Intrinsics.checkParameterIsNotNull(certificate, "certificate");
            if (certificate instanceof X509Certificate) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sha256/");
                outline107.append(toSha256ByteString$okhttp((X509Certificate) certificate).base64());
                return outline107.toString();
            }
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates".toString());
        }

        @NotNull
        public final ByteString toSha1ByteString$okhttp(@NotNull X509Certificate toSha1ByteString) {
            Intrinsics.checkParameterIsNotNull(toSha1ByteString, "$this$toSha1ByteString");
            ByteString.Companion companion = ByteString.Companion;
            PublicKey publicKey = toSha1ByteString.getPublicKey();
            Intrinsics.checkExpressionValueIsNotNull(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkExpressionValueIsNotNull(encoded, "publicKey.encoded");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null).sha1();
        }

        @NotNull
        public final ByteString toSha256ByteString$okhttp(@NotNull X509Certificate toSha256ByteString) {
            Intrinsics.checkParameterIsNotNull(toSha256ByteString, "$this$toSha256ByteString");
            ByteString.Companion companion = ByteString.Companion;
            PublicKey publicKey = toSha256ByteString.getPublicKey();
            Intrinsics.checkExpressionValueIsNotNull(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkExpressionValueIsNotNull(encoded, "publicKey.encoded");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null).sha256();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: CertificatePinner.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÂ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0003J\b\u0010\u0017\u001a\u00020\u0003H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokhttp3/CertificatePinner$Pin;", "", "pattern", "", "hashAlgorithm", "hash", "Lokio/ByteString;", "(Ljava/lang/String;Ljava/lang/String;Lokio/ByteString;)V", "getHash", "()Lokio/ByteString;", "getHashAlgorithm", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "matches", StageSwitchService.CONFIG_HOSTNAME_KEY, "toString", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Pin {
        @NotNull
        private final ByteString hash;
        @NotNull
        private final String hashAlgorithm;
        private final String pattern;

        public Pin(@NotNull String pattern, @NotNull String hashAlgorithm, @NotNull ByteString hash) {
            Intrinsics.checkParameterIsNotNull(pattern, "pattern");
            Intrinsics.checkParameterIsNotNull(hashAlgorithm, "hashAlgorithm");
            Intrinsics.checkParameterIsNotNull(hash, "hash");
            this.pattern = pattern;
            this.hashAlgorithm = hashAlgorithm;
            this.hash = hash;
        }

        private final String component1() {
            return this.pattern;
        }

        public static /* synthetic */ Pin copy$default(Pin pin, String str, String str2, ByteString byteString, int i, Object obj) {
            if ((i & 1) != 0) {
                str = pin.pattern;
            }
            if ((i & 2) != 0) {
                str2 = pin.hashAlgorithm;
            }
            if ((i & 4) != 0) {
                byteString = pin.hash;
            }
            return pin.copy(str, str2, byteString);
        }

        @NotNull
        public final String component2() {
            return this.hashAlgorithm;
        }

        @NotNull
        public final ByteString component3() {
            return this.hash;
        }

        @NotNull
        public final Pin copy(@NotNull String pattern, @NotNull String hashAlgorithm, @NotNull ByteString hash) {
            Intrinsics.checkParameterIsNotNull(pattern, "pattern");
            Intrinsics.checkParameterIsNotNull(hashAlgorithm, "hashAlgorithm");
            Intrinsics.checkParameterIsNotNull(hash, "hash");
            return new Pin(pattern, hashAlgorithm, hash);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof Pin)) {
                    return false;
                }
                Pin pin = (Pin) obj;
                return Intrinsics.areEqual(this.pattern, pin.pattern) && Intrinsics.areEqual(this.hashAlgorithm, pin.hashAlgorithm) && Intrinsics.areEqual(this.hash, pin.hash);
            }
            return true;
        }

        @NotNull
        public final ByteString getHash() {
            return this.hash;
        }

        @NotNull
        public final String getHashAlgorithm() {
            return this.hashAlgorithm;
        }

        public int hashCode() {
            String str = this.pattern;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.hashAlgorithm;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            ByteString byteString = this.hash;
            if (byteString != null) {
                i = byteString.hashCode();
            }
            return hashCode2 + i;
        }

        public final boolean matches(@NotNull String hostname) {
            boolean startsWith$default;
            boolean startsWith$default2;
            boolean regionMatches$default;
            int lastIndexOf$default;
            boolean regionMatches$default2;
            Intrinsics.checkParameterIsNotNull(hostname, "hostname");
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(this.pattern, "**.", false, 2, null);
            if (startsWith$default) {
                int length = this.pattern.length() - 3;
                int length2 = hostname.length() - length;
                regionMatches$default2 = StringsKt__StringsJVMKt.regionMatches$default(hostname, hostname.length() - length, this.pattern, 3, length, false, 16, (Object) null);
                if (!regionMatches$default2) {
                    return false;
                }
                if (length2 != 0 && hostname.charAt(length2 - 1) != '.') {
                    return false;
                }
            } else {
                startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(this.pattern, "*.", false, 2, null);
                if (startsWith$default2) {
                    int length3 = this.pattern.length() - 1;
                    int length4 = hostname.length() - length3;
                    regionMatches$default = StringsKt__StringsJVMKt.regionMatches$default(hostname, hostname.length() - length3, this.pattern, 1, length3, false, 16, (Object) null);
                    if (!regionMatches$default) {
                        return false;
                    }
                    lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) hostname, '.', length4 - 1, false, 4, (Object) null);
                    if (lastIndexOf$default != -1) {
                        return false;
                    }
                } else {
                    return Intrinsics.areEqual(hostname, this.pattern);
                }
            }
            return true;
        }

        @NotNull
        public String toString() {
            return this.hashAlgorithm + this.hash.base64();
        }
    }

    public CertificatePinner(@NotNull Set<Pin> pins, @Nullable CertificateChainCleaner certificateChainCleaner) {
        Intrinsics.checkParameterIsNotNull(pins, "pins");
        this.pins = pins;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    @JvmStatic
    @NotNull
    public static final String pin(@NotNull Certificate certificate) {
        return Companion.pin(certificate);
    }

    public final void check(@NotNull String hostname, @NotNull List<? extends Certificate> peerCertificates) throws SSLPeerUnverifiedException {
        Intrinsics.checkParameterIsNotNull(hostname, "hostname");
        Intrinsics.checkParameterIsNotNull(peerCertificates, "peerCertificates");
        check$okhttp(hostname, new CertificatePinner$check$1(this, peerCertificates, hostname));
    }

    public final void check$okhttp(@NotNull String hostname, @NotNull Function0<? extends List<? extends X509Certificate>> cleanedPeerCertificatesFn) {
        Intrinsics.checkParameterIsNotNull(hostname, "hostname");
        Intrinsics.checkParameterIsNotNull(cleanedPeerCertificatesFn, "cleanedPeerCertificatesFn");
        List<Pin> findMatchingPins$okhttp = findMatchingPins$okhttp(hostname);
        if (findMatchingPins$okhttp.isEmpty()) {
            return;
        }
        List<? extends X509Certificate> mo12560invoke = cleanedPeerCertificatesFn.mo12560invoke();
        for (X509Certificate x509Certificate : mo12560invoke) {
            ByteString byteString = null;
            ByteString byteString2 = null;
            for (Pin pin : findMatchingPins$okhttp) {
                String hashAlgorithm = pin.getHashAlgorithm();
                int hashCode = hashAlgorithm.hashCode();
                if (hashCode != 109397962) {
                    if (hashCode == 2052263656 && hashAlgorithm.equals("sha256/")) {
                        if (byteString2 == null) {
                            byteString2 = Companion.toSha256ByteString$okhttp(x509Certificate);
                        }
                        if (Intrinsics.areEqual(pin.getHash(), byteString2)) {
                            return;
                        }
                    }
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unsupported hashAlgorithm: ");
                    outline107.append(pin.getHashAlgorithm());
                    throw new AssertionError(outline107.toString());
                } else if (hashAlgorithm.equals("sha1/")) {
                    if (byteString == null) {
                        byteString = Companion.toSha1ByteString$okhttp(x509Certificate);
                    }
                    if (Intrinsics.areEqual(pin.getHash(), byteString)) {
                        return;
                    }
                } else {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("unsupported hashAlgorithm: ");
                    outline1072.append(pin.getHashAlgorithm());
                    throw new AssertionError(outline1072.toString());
                }
            }
        }
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("Certificate pinning failure!", "\n  Peer certificate chain:");
        for (X509Certificate x509Certificate2 : mo12560invoke) {
            outline113.append("\n    ");
            outline113.append(Companion.pin(x509Certificate2));
            outline113.append(RealTimeTextConstants.COLON_SPACE);
            Principal subjectDN = x509Certificate2.getSubjectDN();
            Intrinsics.checkExpressionValueIsNotNull(subjectDN, "element.subjectDN");
            outline113.append(subjectDN.getName());
        }
        outline113.append("\n  Pinned certificates for ");
        outline113.append(hostname);
        outline113.append(":");
        for (Pin pin2 : findMatchingPins$okhttp) {
            outline113.append("\n    ");
            outline113.append(pin2);
        }
        String sb = outline113.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "StringBuilder().apply(builderAction).toString()");
        throw new SSLPeerUnverifiedException(sb);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            if (Intrinsics.areEqual(certificatePinner.pins, this.pins) && Intrinsics.areEqual(certificatePinner.certificateChainCleaner, this.certificateChainCleaner)) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public final List<Pin> findMatchingPins$okhttp(@NotNull String hostname) {
        List<Pin> emptyList;
        Intrinsics.checkParameterIsNotNull(hostname, "hostname");
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        for (Pin pin : this.pins) {
            if (pin.matches(hostname)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList<>();
                }
                TypeIntrinsics.asMutableList(emptyList).add(pin);
            }
        }
        return emptyList;
    }

    @Nullable
    public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
        return this.certificateChainCleaner;
    }

    public int hashCode() {
        int hashCode = (this.pins.hashCode() + 1517) * 41;
        CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        return hashCode + (certificateChainCleaner != null ? certificateChainCleaner.hashCode() : 0);
    }

    @NotNull
    public final CertificatePinner withCertificateChainCleaner$okhttp(@Nullable CertificateChainCleaner certificateChainCleaner) {
        return Intrinsics.areEqual(this.certificateChainCleaner, certificateChainCleaner) ? this : new CertificatePinner(this.pins, certificateChainCleaner);
    }

    @Deprecated(message = "replaced with {@link #check(String, List)}.", replaceWith = @ReplaceWith(expression = "check(hostname, peerCertificates.toList())", imports = {}))
    public final void check(@NotNull String hostname, @NotNull Certificate... peerCertificates) throws SSLPeerUnverifiedException {
        List<? extends Certificate> list;
        Intrinsics.checkParameterIsNotNull(hostname, "hostname");
        Intrinsics.checkParameterIsNotNull(peerCertificates, "peerCertificates");
        list = ArraysKt___ArraysKt.toList(peerCertificates);
        check(hostname, list);
    }
}
