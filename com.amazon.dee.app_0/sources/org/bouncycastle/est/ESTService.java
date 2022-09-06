package org.bouncycastle.est;

import com.amazonaws.util.DateUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.net.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cmc.CMCException;
import org.bouncycastle.cmc.SimplePKIResponse;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
/* loaded from: classes4.dex */
public class ESTService {
    protected static final String CACERTS = "/cacerts";
    protected static final String CSRATTRS = "/csrattrs";
    protected static final String FULLCMC = "/fullcmc";
    protected static final String SERVERGEN = "/serverkeygen";
    protected static final String SIMPLE_ENROLL = "/simpleenroll";
    protected static final String SIMPLE_REENROLL = "/simplereenroll";
    protected static final Set<String> illegalParts = new HashSet();
    private static final Pattern pathInValid;
    private final ESTClientProvider clientProvider;
    private final String server;

    static {
        illegalParts.add("cacerts");
        illegalParts.add("simpleenroll");
        illegalParts.add("simplereenroll");
        illegalParts.add("fullcmc");
        illegalParts.add("serverkeygen");
        illegalParts.add("csrattrs");
        pathInValid = Pattern.compile("^[0-9a-zA-Z_\\-.~!$&'()*+,;:=]+");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ESTService(String str, String str2, ESTClientProvider eSTClientProvider) {
        String verifyServer = verifyServer(str);
        this.server = str2 != null ? GeneratedOutlineSupport1.outline76("https://", verifyServer, "/.well-known/est/", verifyLabel(str2)) : GeneratedOutlineSupport1.outline75("https://", verifyServer, "/.well-known/est");
        this.clientProvider = eSTClientProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String annotateRequest(byte[] bArr) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        int i = 0;
        do {
            int i2 = i + 48;
            if (i2 < bArr.length) {
                printWriter.print(Base64.toBase64String(bArr, i, 48));
                i = i2;
            } else {
                printWriter.print(Base64.toBase64String(bArr, i, bArr.length - i));
                i = bArr.length;
            }
            printWriter.print('\n');
        } while (i < bArr.length);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store) {
        return storeToArray(store, null);
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store, Selector<X509CertificateHolder> selector) {
        Collection<X509CertificateHolder> matches = store.getMatches(selector);
        return (X509CertificateHolder[]) matches.toArray(new X509CertificateHolder[matches.size()]);
    }

    private String verifyLabel(String str) {
        while (str.endsWith("/") && str.length() > 0) {
            str = GeneratedOutlineSupport1.outline51(str, 1, 0);
        }
        while (str.startsWith("/") && str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() != 0) {
            if (!pathInValid.matcher(str).matches()) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Server path ", str, " contains invalid characters"));
            }
            if (illegalParts.contains(str)) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Label ", str, " is a reserved path segment."));
            }
            return str;
        }
        throw new IllegalArgumentException("Label set but after trimming '/' is not zero length string.");
    }

    private String verifyServer(String str) {
        while (str.endsWith("/") && str.length() > 0) {
            try {
                str = str.substring(0, str.length() - 1);
            } catch (Exception e) {
                if (!(e instanceof IllegalArgumentException)) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Scheme and host is invalid: ")), e);
                }
                throw ((IllegalArgumentException) e);
            }
        }
        if (!str.contains("://")) {
            URL url = new URL("https://" + str);
            if (url.getPath().length() != 0 && !url.getPath().equals("/")) {
                throw new IllegalArgumentException("Server contains path, must only be <dnsname/ipaddress>:port, a path of '/.well-known/est/<label>' will be added arbitrarily.");
            }
            return str;
        }
        throw new IllegalArgumentException("Server contains scheme, must only be <dnsname/ipaddress>:port, https:// will be added arbitrarily.");
    }

    public CACertsResponse getCACerts() throws ESTException {
        URL url;
        ESTRequest build;
        ESTResponse doRequest;
        Store<X509CertificateHolder> store;
        Store<X509CRLHolder> store2;
        Store<X509CertificateHolder> store3;
        Store<X509CRLHolder> store4;
        String str;
        ESTResponse eSTResponse = null;
        try {
            url = new URL(this.server + CACERTS);
            ESTClient makeClient = this.clientProvider.makeClient();
            build = new ESTRequestBuilder("GET", url).withClient(makeClient).build();
            doRequest = makeClient.doRequest(build);
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (doRequest.getStatusCode() == 200) {
                if (!"application/pkcs7-mime".equals(doRequest.getHeaders().getFirstValue("Content-Type"))) {
                    if (doRequest.getHeaders().getFirstValue("Content-Type") != null) {
                        str = " got " + doRequest.getHeaders().getFirstValue("Content-Type");
                    } else {
                        str = " but was not present.";
                    }
                    throw new ESTException("Response : " + url.toString() + "Expecting application/pkcs7-mime " + str, null, doRequest.getStatusCode(), doRequest.getInputStream());
                }
                if (doRequest.getContentLength() == null || doRequest.getContentLength().longValue() <= 0) {
                    store3 = null;
                    store4 = null;
                } else {
                    SimplePKIResponse simplePKIResponse = new SimplePKIResponse(ContentInfo.getInstance((ASN1Sequence) new ASN1InputStream(doRequest.getInputStream()).readObject()));
                    store3 = simplePKIResponse.getCertificates();
                    store4 = simplePKIResponse.getCRLs();
                }
                store = store3;
                store2 = store4;
            } else if (doRequest.getStatusCode() != 204) {
                throw new ESTException("Get CACerts: " + url.toString(), null, doRequest.getStatusCode(), doRequest.getInputStream());
            } else {
                store = null;
                store2 = null;
            }
            CACertsResponse cACertsResponse = new CACertsResponse(store, store2, build, doRequest.getSource(), this.clientProvider.isTrusted());
            try {
                doRequest.close();
                e = null;
            } catch (Exception e) {
                e = e;
            }
            if (e == null) {
                return cACertsResponse;
            }
            if (e instanceof ESTException) {
                throw ((ESTException) e);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Get CACerts: ");
            outline107.append(url.toString());
            throw new ESTException(outline107.toString(), e, doRequest.getStatusCode(), null);
        } catch (Throwable th2) {
            th = th2;
            eSTResponse = doRequest;
            try {
                if (!(th instanceof ESTException)) {
                    throw new ESTException(th.getMessage(), th);
                }
                throw th;
            } catch (Throwable th3) {
                if (eSTResponse != null) {
                    try {
                        eSTResponse.close();
                    } catch (Exception unused) {
                    }
                }
                throw th3;
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:6|7|(2:9|(2:14|15))(2:32|(5:36|18|19|20|(2:22|(2:24|25)(2:26|27))(2:28|29)))|17|18|19|20|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a7, code lost:
        r1 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.bouncycastle.est.CSRRequestResponse getCSRAttributes() throws org.bouncycastle.est.ESTException {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.ESTService.getCSRAttributes():org.bouncycastle.est.CSRRequestResponse");
    }

    protected EnrollmentResponse handleEnrollResponse(ESTResponse eSTResponse) throws IOException {
        long time;
        ESTRequest originalRequest = eSTResponse.getOriginalRequest();
        if (eSTResponse.getStatusCode() != 202) {
            if (eSTResponse.getStatusCode() == 200) {
                try {
                    return new EnrollmentResponse(new SimplePKIResponse(ContentInfo.getInstance(new ASN1InputStream(eSTResponse.getInputStream()).readObject())).getCertificates(), -1L, null, eSTResponse.getSource());
                } catch (CMCException e) {
                    throw new ESTException(e.getMessage(), e.getCause());
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Simple Enroll: ");
            outline107.append(originalRequest.getURL().toString());
            throw new ESTException(outline107.toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
        }
        String header = eSTResponse.getHeader(HttpHeaders.RETRY_AFTER);
        if (header == null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Got Status 202 but not Retry-After header from: ");
            outline1072.append(originalRequest.getURL().toString());
            throw new ESTException(outline1072.toString());
        }
        try {
            try {
                time = (Long.parseLong(header) * 1000) + System.currentTimeMillis();
            } catch (Exception e2) {
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Unable to parse Retry-After header:");
                outline1073.append(originalRequest.getURL().toString());
                outline1073.append(" ");
                outline1073.append(e2.getMessage());
                throw new ESTException(outline1073.toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
            }
        } catch (NumberFormatException unused) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.RFC822_DATE_PATTERN, Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            time = simpleDateFormat.parse(header).getTime();
        }
        return new EnrollmentResponse(null, time, originalRequest, eSTResponse.getSource());
    }

    public EnrollmentResponse simpleEnroll(EnrollmentResponse enrollmentResponse) throws Exception {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                ESTClient makeClient = this.clientProvider.makeClient();
                eSTResponse = makeClient.doRequest(new ESTRequestBuilder(enrollmentResponse.getRequestToRetry()).withClient(makeClient).build());
                return handleEnrollResponse(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (!(th instanceof ESTException)) {
                        throw new ESTException(th.getMessage(), th);
                    }
                    throw th;
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }

    public EnrollmentResponse simpleEnroll(boolean z, PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth) throws IOException {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                byte[] bytes = annotateRequest(pKCS10CertificationRequest.getEncoded()).getBytes();
                StringBuilder sb = new StringBuilder();
                sb.append(this.server);
                sb.append(z ? SIMPLE_REENROLL : SIMPLE_ENROLL);
                URL url = new URL(sb.toString());
                ESTClient makeClient = this.clientProvider.makeClient();
                ESTRequestBuilder withClient = new ESTRequestBuilder("POST", url).withData(bytes).withClient(makeClient);
                withClient.addHeader("Content-Type", "application/pkcs10");
                withClient.addHeader("Content-Length", "" + bytes.length);
                withClient.addHeader("Content-Transfer-Encoding", "base64");
                if (eSTAuth != null) {
                    eSTAuth.applyAuth(withClient);
                }
                eSTResponse = makeClient.doRequest(withClient.build());
                return handleEnrollResponse(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (!(th instanceof ESTException)) {
                        throw new ESTException(th.getMessage(), th);
                    }
                    throw th;
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }

    public EnrollmentResponse simpleEnrollPoP(boolean z, final PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, final ContentSigner contentSigner, ESTAuth eSTAuth) throws IOException {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(this.server);
                sb.append(z ? SIMPLE_REENROLL : SIMPLE_ENROLL);
                URL url = new URL(sb.toString());
                ESTClient makeClient = this.clientProvider.makeClient();
                ESTRequestBuilder withConnectionListener = new ESTRequestBuilder("POST", url).withClient(makeClient).withConnectionListener(new ESTSourceConnectionListener() { // from class: org.bouncycastle.est.ESTService.1
                    @Override // org.bouncycastle.est.ESTSourceConnectionListener
                    public ESTRequest onConnection(Source source, ESTRequest eSTRequest) throws IOException {
                        if (source instanceof TLSUniqueProvider) {
                            TLSUniqueProvider tLSUniqueProvider = (TLSUniqueProvider) source;
                            if (tLSUniqueProvider.isTLSUniqueAvailable()) {
                                PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder2 = new PKCS10CertificationRequestBuilder(pKCS10CertificationRequestBuilder);
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                pKCS10CertificationRequestBuilder2.setAttribute(PKCSObjectIdentifiers.pkcs_9_at_challengePassword, new DERPrintableString(Base64.toBase64String(tLSUniqueProvider.getTLSUnique())));
                                byteArrayOutputStream.write(ESTService.this.annotateRequest(pKCS10CertificationRequestBuilder2.build(contentSigner).getEncoded()).getBytes());
                                byteArrayOutputStream.flush();
                                ESTRequestBuilder withData = new ESTRequestBuilder(eSTRequest).withData(byteArrayOutputStream.toByteArray());
                                withData.setHeader("Content-Type", "application/pkcs10");
                                withData.setHeader("Content-Transfer-Encoding", "base64");
                                withData.setHeader("Content-Length", Long.toString(byteArrayOutputStream.size()));
                                return withData.build();
                            }
                        }
                        throw new IOException("Source does not supply TLS unique.");
                    }
                });
                if (eSTAuth != null) {
                    eSTAuth.applyAuth(withConnectionListener);
                }
                eSTResponse = makeClient.doRequest(withConnectionListener.build());
                return handleEnrollResponse(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (!(th instanceof ESTException)) {
                        throw new ESTException(th.getMessage(), th);
                    }
                    throw th;
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }
}
