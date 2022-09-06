package io.ktor.http.auth;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.CodecsKt;
import io.ktor.http.HeaderValueParam;
import io.ktor.http.HeaderValueWithParametersKt;
import io.ktor.util.CryptoKt;
import io.ktor.util.Hash;
import io.ktor.util.KtorExperimentalAPI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.io.charsets.CharsetJVMKt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpAuthHeader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u000b2\u00020\u0001:\u0004\u000b\f\r\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\u000f\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader;", "", "authScheme", "", "(Ljava/lang/String;)V", "getAuthScheme", "()Ljava/lang/String;", "render", "encoding", "Lio/ktor/http/auth/HeaderValueEncoding;", "toString", "Companion", "Parameterized", "Parameters", "Single", "Lio/ktor/http/auth/HttpAuthHeader$Single;", "Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class HttpAuthHeader {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String authScheme;

    /* compiled from: HttpAuthHeader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tJO\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0006¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Companion;", "", "()V", "basicAuthChallenge", "Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", Parameters.Realm, "", Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "digestAuthChallenge", "nonce", "domain", "", "opaque", "stale", "", "algorithm", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public static /* synthetic */ Parameterized digestAuthChallenge$default(Companion companion, String str, String str2, List list, String str3, Boolean bool, String str4, int i, Object obj) {
            List emptyList;
            if ((i & 2) != 0) {
                str2 = CryptoKt.generateNonce();
            }
            String str5 = str2;
            List list2 = list;
            if ((i & 4) != 0) {
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                list2 = emptyList;
            }
            List list3 = list2;
            String str6 = (i & 8) != 0 ? null : str3;
            Boolean bool2 = (i & 16) != 0 ? null : bool;
            if ((i & 32) != 0) {
                str4 = MessageDigestAlgorithms.MD5;
            }
            return companion.digestAuthChallenge(str, str5, list3, str6, bool2, str4);
        }

        @NotNull
        public final Parameterized basicAuthChallenge(@NotNull String realm, @Nullable Charset charset) {
            Intrinsics.checkParameterIsNotNull(realm, "realm");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(Parameters.Realm, realm);
            if (charset != null) {
                linkedHashMap.put(Parameters.Charset, CharsetJVMKt.getName(charset));
            }
            return new Parameterized(AuthScheme.Basic, linkedHashMap, (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null);
        }

        @NotNull
        public final Parameterized digestAuthChallenge(@NotNull String realm, @NotNull String nonce, @NotNull List<String> domain, @Nullable String str, @Nullable Boolean bool, @NotNull String algorithm) {
            LinkedHashMap linkedHashMap;
            Object obj;
            String joinToString$default;
            Intrinsics.checkParameterIsNotNull(realm, "realm");
            Intrinsics.checkParameterIsNotNull(nonce, "nonce");
            Intrinsics.checkParameterIsNotNull(domain, "domain");
            Intrinsics.checkParameterIsNotNull(algorithm, "algorithm");
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put(Parameters.Realm, realm);
            linkedHashMap2.put("nonce", nonce);
            if (!domain.isEmpty()) {
                linkedHashMap = linkedHashMap2;
                obj = "algorithm";
                joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(domain, " ", null, null, 0, null, null, 62, null);
                linkedHashMap.put("domain", joinToString$default);
            } else {
                linkedHashMap = linkedHashMap2;
                obj = "algorithm";
            }
            if (str != null) {
                linkedHashMap.put("opaque", str);
            }
            if (bool != null) {
                linkedHashMap.put("stale", String.valueOf(bool.booleanValue()));
            }
            linkedHashMap.put(obj, algorithm);
            return new Parameterized(AuthScheme.Digest, linkedHashMap, HeaderValueEncoding.QUOTED_ALWAYS);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: HttpAuthHeader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B-\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0017\u001a\u00020\u0003J\b\u0010\u0018\u001a\u00020\u0003H\u0016J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003J\u0018\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\u0007J\u0014\u0010\u001c\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "Lio/ktor/http/auth/HttpAuthHeader;", "authScheme", "", "parameters", "", "encoding", "Lio/ktor/http/auth/HeaderValueEncoding;", "(Ljava/lang/String;Ljava/util/Map;Lio/ktor/http/auth/HeaderValueEncoding;)V", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/util/List;Lio/ktor/http/auth/HeaderValueEncoding;)V", "getEncoding", "()Lio/ktor/http/auth/HeaderValueEncoding;", "getParameters", "()Ljava/util/List;", "equals", "", "other", "", "hashCode", "", "parameter", "name", "render", "withParameter", "value", "withReplacedParameter", "encode", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Parameterized extends HttpAuthHeader {
        @NotNull
        private final HeaderValueEncoding encoding;
        @NotNull
        private final List<HeaderValueParam> parameters;

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
        /* loaded from: classes3.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[HeaderValueEncoding.values().length];

            static {
                $EnumSwitchMapping$0[HeaderValueEncoding.QUOTED_WHEN_REQUIRED.ordinal()] = 1;
                $EnumSwitchMapping$0[HeaderValueEncoding.QUOTED_ALWAYS.ordinal()] = 2;
                $EnumSwitchMapping$0[HeaderValueEncoding.URI_ENCODE.ordinal()] = 3;
            }
        }

        public /* synthetic */ Parameterized(String str, List list, HeaderValueEncoding headerValueEncoding, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, list, (i & 4) != 0 ? HeaderValueEncoding.QUOTED_WHEN_REQUIRED : headerValueEncoding);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String encode(@NotNull String str, HeaderValueEncoding headerValueEncoding) {
            int i = WhenMappings.$EnumSwitchMapping$0[headerValueEncoding.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    return HeaderValueWithParametersKt.quote(str);
                }
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                return CodecsKt.encodeURLParameter$default(str, false, 1, null);
            }
            return HeaderValueWithParametersKt.escapeIfNeeded(str);
        }

        public boolean equals(@Nullable Object obj) {
            boolean equals;
            if (!(obj instanceof Parameterized)) {
                return false;
            }
            Parameterized parameterized = (Parameterized) obj;
            equals = StringsKt__StringsJVMKt.equals(parameterized.getAuthScheme(), getAuthScheme(), true);
            return equals && Intrinsics.areEqual(parameterized.parameters, this.parameters);
        }

        @NotNull
        public final HeaderValueEncoding getEncoding() {
            return this.encoding;
        }

        @NotNull
        public final List<HeaderValueParam> getParameters() {
            return this.parameters;
        }

        public int hashCode() {
            Hash hash = Hash.INSTANCE;
            Object[] objArr = new Object[2];
            String authScheme = getAuthScheme();
            if (authScheme != null) {
                String lowerCase = authScheme.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                objArr[0] = lowerCase;
                objArr[1] = this.parameters;
                return hash.combine(objArr);
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }

        @Nullable
        public final String parameter(@NotNull String name) {
            Object obj;
            Intrinsics.checkParameterIsNotNull(name, "name");
            Iterator<T> it2 = this.parameters.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (Intrinsics.areEqual(((HeaderValueParam) obj).getName(), name)) {
                    break;
                }
            }
            HeaderValueParam headerValueParam = (HeaderValueParam) obj;
            if (headerValueParam != null) {
                return headerValueParam.getValue();
            }
            return null;
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        @NotNull
        public String render(@NotNull HeaderValueEncoding encoding) {
            String joinToString$default;
            Intrinsics.checkParameterIsNotNull(encoding, "encoding");
            List<HeaderValueParam> list = this.parameters;
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(list, ", ", getAuthScheme() + Chars.SPACE, null, 0, null, new HttpAuthHeader$Parameterized$render$1(this, encoding), 28, null);
            return joinToString$default;
        }

        @NotNull
        public final Parameterized withParameter(@NotNull String name, @NotNull String value) {
            List plus;
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            String authScheme = getAuthScheme();
            plus = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) this.parameters), (Object) new HeaderValueParam(name, value));
            return new Parameterized(authScheme, plus, this.encoding);
        }

        @KtorExperimentalAPI
        @NotNull
        public final Parameterized withReplacedParameter(@NotNull String name, @NotNull String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            Iterator<HeaderValueParam> it2 = this.parameters.iterator();
            boolean z = false;
            int i = 0;
            while (true) {
                if (!it2.hasNext()) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual(it2.next().getName(), name)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return withParameter(name, value);
            }
            List<HeaderValueParam> list = this.parameters;
            ArrayList arrayList = new ArrayList();
            for (HeaderValueParam headerValueParam : list) {
                if (!(!Intrinsics.areEqual(headerValueParam.getName(), name))) {
                    if (!z) {
                        headerValueParam = new HeaderValueParam(name, value);
                        z = true;
                    } else {
                        headerValueParam = null;
                    }
                }
                if (headerValueParam != null) {
                    arrayList.add(headerValueParam);
                }
            }
            return new Parameterized(getAuthScheme(), arrayList, this.encoding);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Parameterized(@NotNull String authScheme, @NotNull List<HeaderValueParam> parameters, @NotNull HeaderValueEncoding encoding) {
            super(authScheme, null);
            Intrinsics.checkParameterIsNotNull(authScheme, "authScheme");
            Intrinsics.checkParameterIsNotNull(parameters, "parameters");
            Intrinsics.checkParameterIsNotNull(encoding, "encoding");
            this.parameters = parameters;
            this.encoding = encoding;
            for (HeaderValueParam headerValueParam : this.parameters) {
                if (!HttpAuthHeaderKt.access$getToken68Pattern$p().matches(headerValueParam.getName())) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("parameter name should be a token but it is ");
                    outline107.append(headerValueParam.getName());
                    throw new IllegalArgumentException(outline107.toString().toString());
                }
            }
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        @NotNull
        public String render() {
            return render(this.encoding);
        }

        public /* synthetic */ Parameterized(String str, Map map, HeaderValueEncoding headerValueEncoding, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, map, (i & 4) != 0 ? HeaderValueEncoding.QUOTED_WHEN_REQUIRED : headerValueEncoding);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public Parameterized(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull java.util.Map<java.lang.String, java.lang.String> r6, @org.jetbrains.annotations.NotNull io.ktor.http.auth.HeaderValueEncoding r7) {
            /*
                r4 = this;
                java.lang.String r0 = "authScheme"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
                java.lang.String r0 = "parameters"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
                java.lang.String r0 = "encoding"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
                java.util.Set r6 = r6.entrySet()
                java.util.ArrayList r0 = new java.util.ArrayList
                r1 = 10
                int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r6, r1)
                r0.<init>(r1)
                java.util.Iterator r6 = r6.iterator()
            L22:
                boolean r1 = r6.hasNext()
                if (r1 == 0) goto L43
                java.lang.Object r1 = r6.next()
                java.util.Map$Entry r1 = (java.util.Map.Entry) r1
                io.ktor.http.HeaderValueParam r2 = new io.ktor.http.HeaderValueParam
                java.lang.Object r3 = r1.getKey()
                java.lang.String r3 = (java.lang.String) r3
                java.lang.Object r1 = r1.getValue()
                java.lang.String r1 = (java.lang.String) r1
                r2.<init>(r3, r1)
                r0.add(r2)
                goto L22
            L43:
                r4.<init>(r5, r0, r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.auth.HttpAuthHeader.Parameterized.<init>(java.lang.String, java.util.Map, io.ktor.http.auth.HeaderValueEncoding):void");
        }
    }

    /* compiled from: HttpAuthHeader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Parameters;", "", "()V", "Charset", "", "OAuthCallback", "OAuthCallbackConfirmed", "OAuthConsumerKey", "OAuthNonce", "OAuthSignature", "OAuthSignatureMethod", "OAuthTimestamp", "OAuthToken", "OAuthTokenSecret", "OAuthVerifier", "OAuthVersion", "Realm", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Parameters {
        @NotNull
        public static final String Charset = "charset";
        public static final Parameters INSTANCE = new Parameters();
        @NotNull
        public static final String OAuthCallback = "oauth_callback";
        @NotNull
        public static final String OAuthCallbackConfirmed = "oauth_callback_confirmed";
        @NotNull
        public static final String OAuthConsumerKey = "oauth_consumer_key";
        @NotNull
        public static final String OAuthNonce = "oauth_nonce";
        @NotNull
        public static final String OAuthSignature = "oauth_signature";
        @NotNull
        public static final String OAuthSignatureMethod = "oauth_signature_method";
        @NotNull
        public static final String OAuthTimestamp = "oauth_timestamp";
        @NotNull
        public static final String OAuthToken = "oauth_token";
        @NotNull
        public static final String OAuthTokenSecret = "oauth_token_secret";
        @NotNull
        public static final String OAuthVerifier = "oauth_verifier";
        @NotNull
        public static final String OAuthVersion = "oauth_version";
        @NotNull
        public static final String Realm = "realm";

        private Parameters() {
        }
    }

    /* compiled from: HttpAuthHeader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Single;", "Lio/ktor/http/auth/HttpAuthHeader;", "authScheme", "", "blob", "(Ljava/lang/String;Ljava/lang/String;)V", "getBlob", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "render", "encoding", "Lio/ktor/http/auth/HeaderValueEncoding;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Single extends HttpAuthHeader {
        @NotNull
        private final String blob;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Single(@NotNull String authScheme, @NotNull String blob) {
            super(authScheme, null);
            Intrinsics.checkParameterIsNotNull(authScheme, "authScheme");
            Intrinsics.checkParameterIsNotNull(blob, "blob");
            this.blob = blob;
            if (HttpAuthHeaderKt.access$getToken68Pattern$p().matches(this.blob)) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid blob value: it should be token68 but it is ");
            outline107.append(this.blob);
            throw new IllegalArgumentException(outline107.toString().toString());
        }

        public boolean equals(@Nullable Object obj) {
            boolean equals;
            boolean equals2;
            if (!(obj instanceof Single)) {
                return false;
            }
            Single single = (Single) obj;
            equals = StringsKt__StringsJVMKt.equals(single.getAuthScheme(), getAuthScheme(), true);
            if (!equals) {
                return false;
            }
            equals2 = StringsKt__StringsJVMKt.equals(single.blob, this.blob, true);
            return equals2;
        }

        @NotNull
        public final String getBlob() {
            return this.blob;
        }

        public int hashCode() {
            Hash hash = Hash.INSTANCE;
            Object[] objArr = new Object[2];
            String authScheme = getAuthScheme();
            if (authScheme != null) {
                String lowerCase = authScheme.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                objArr[0] = lowerCase;
                String str = this.blob;
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase2 = str.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase2, "(this as java.lang.String).toLowerCase()");
                objArr[1] = lowerCase2;
                return hash.combine(objArr);
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        @NotNull
        public String render() {
            return getAuthScheme() + Chars.SPACE + this.blob;
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        @NotNull
        public String render(@NotNull HeaderValueEncoding encoding) {
            Intrinsics.checkParameterIsNotNull(encoding, "encoding");
            return render();
        }
    }

    private HttpAuthHeader(String str) {
        this.authScheme = str;
        if (HttpAuthHeaderKt.access$getToken68Pattern$p().matches(this.authScheme)) {
            return;
        }
        throw new IllegalArgumentException("invalid authScheme value: it should be token".toString());
    }

    @NotNull
    public final String getAuthScheme() {
        return this.authScheme;
    }

    @NotNull
    public abstract String render();

    @NotNull
    public abstract String render(@NotNull HeaderValueEncoding headerValueEncoding);

    @NotNull
    public String toString() {
        return render();
    }

    public /* synthetic */ HttpAuthHeader(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
