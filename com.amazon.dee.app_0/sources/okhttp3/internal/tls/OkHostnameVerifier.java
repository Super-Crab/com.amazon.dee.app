package okhttp3.internal.tls;

import com.amazon.communication.remotesetting.StageSwitchService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
/* compiled from: OkHostnameVerifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lokhttp3/internal/tls/OkHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "()V", "ALT_DNS_NAME", "", "ALT_IPA_NAME", "allSubjectAltNames", "", "", "certificate", "Ljava/security/cert/X509Certificate;", "getSubjectAltNames", "type", "verify", "", "host", "session", "Ljavax/net/ssl/SSLSession;", "verifyHostname", StageSwitchService.CONFIG_HOSTNAME_KEY, "pattern", "verifyIpAddress", "ipAddress", "okhttp"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class OkHostnameVerifier implements HostnameVerifier {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();

    private OkHostnameVerifier() {
    }

    private final List<String> getSubjectAltNames(X509Certificate x509Certificate, int i) {
        List<String> emptyList;
        List<String> emptyList2;
        Object obj;
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames != null) {
                ArrayList arrayList = new ArrayList();
                for (List<?> list : subjectAlternativeNames) {
                    if (list != null && list.size() >= 2 && !(!Intrinsics.areEqual(list.get(0), Integer.valueOf(i))) && (obj = list.get(1)) != null) {
                        arrayList.add((String) obj);
                    }
                }
                return arrayList;
            }
            emptyList2 = CollectionsKt__CollectionsKt.emptyList();
            return emptyList2;
        } catch (CertificateParsingException unused) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
    }

    private final boolean verifyHostname(String str, X509Certificate x509Certificate) {
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        if (str != null) {
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            List<String> subjectAltNames = getSubjectAltNames(x509Certificate, 2);
            if ((subjectAltNames instanceof Collection) && subjectAltNames.isEmpty()) {
                return false;
            }
            for (String str2 : subjectAltNames) {
                if (INSTANCE.verifyHostname(lowerCase, str2)) {
                    return true;
                }
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final boolean verifyIpAddress(String str, X509Certificate x509Certificate) {
        boolean equals;
        List<String> subjectAltNames = getSubjectAltNames(x509Certificate, 7);
        if (!(subjectAltNames instanceof Collection) || !subjectAltNames.isEmpty()) {
            for (String str2 : subjectAltNames) {
                equals = StringsKt__StringsJVMKt.equals(str, str2, true);
                if (equals) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @NotNull
    public final List<String> allSubjectAltNames(@NotNull X509Certificate certificate) {
        List<String> plus;
        Intrinsics.checkParameterIsNotNull(certificate, "certificate");
        plus = CollectionsKt___CollectionsKt.plus((Collection) getSubjectAltNames(certificate, 7), (Iterable) getSubjectAltNames(certificate, 2));
        return plus;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(@NotNull String host, @NotNull SSLSession session) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(session, "session");
        try {
            Certificate certificate = session.getPeerCertificates()[0];
            if (certificate == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.security.cert.X509Certificate");
            }
            return verify(host, (X509Certificate) certificate);
        } catch (SSLException unused) {
            return false;
        }
    }

    public final boolean verify(@NotNull String host, @NotNull X509Certificate certificate) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(certificate, "certificate");
        return Util.canParseAsIpAddress(host) ? verifyIpAddress(host, certificate) : verifyHostname(host, certificate);
    }

    private final boolean verifyHostname(String str, String str2) {
        boolean startsWith$default;
        boolean endsWith$default;
        boolean startsWith$default2;
        boolean endsWith$default2;
        boolean endsWith$default3;
        boolean endsWith$default4;
        boolean contains$default;
        boolean startsWith$default3;
        int indexOf$default;
        boolean endsWith$default5;
        int lastIndexOf$default;
        if (!(str == null || str.length() == 0)) {
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, ".", false, 2, null);
            if (!startsWith$default) {
                endsWith$default = StringsKt__StringsJVMKt.endsWith$default(str, "..", false, 2, null);
                if (!endsWith$default) {
                    if (!(str2 == null || str2.length() == 0)) {
                        startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(str2, ".", false, 2, null);
                        if (!startsWith$default2) {
                            endsWith$default2 = StringsKt__StringsJVMKt.endsWith$default(str2, "..", false, 2, null);
                            if (!endsWith$default2) {
                                endsWith$default3 = StringsKt__StringsJVMKt.endsWith$default(str, ".", false, 2, null);
                                if (!endsWith$default3) {
                                    str = GeneratedOutlineSupport1.outline72(str, ".");
                                }
                                String str3 = str;
                                endsWith$default4 = StringsKt__StringsJVMKt.endsWith$default(str2, ".", false, 2, null);
                                if (!endsWith$default4) {
                                    str2 = GeneratedOutlineSupport1.outline72(str2, ".");
                                }
                                Locale locale = Locale.US;
                                Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
                                if (str2 != null) {
                                    String lowerCase = str2.toLowerCase(locale);
                                    Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                                    contains$default = StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "*", false, 2, (Object) null);
                                    if (!contains$default) {
                                        return Intrinsics.areEqual(str3, lowerCase);
                                    }
                                    startsWith$default3 = StringsKt__StringsJVMKt.startsWith$default(lowerCase, "*.", false, 2, null);
                                    if (startsWith$default3) {
                                        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) lowerCase, '*', 1, false, 4, (Object) null);
                                        if (indexOf$default != -1 || str3.length() < lowerCase.length() || Intrinsics.areEqual("*.", lowerCase)) {
                                            return false;
                                        }
                                        String substring = lowerCase.substring(1);
                                        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                                        endsWith$default5 = StringsKt__StringsJVMKt.endsWith$default(str3, substring, false, 2, null);
                                        if (!endsWith$default5) {
                                            return false;
                                        }
                                        int length = str3.length() - substring.length();
                                        if (length > 0) {
                                            lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) str3, '.', length - 1, false, 4, (Object) null);
                                            if (lastIndexOf$default != -1) {
                                                return false;
                                            }
                                        }
                                        return true;
                                    }
                                    return false;
                                }
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
