package com.amazonaws.auth;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.HttpUtils;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
/* loaded from: classes13.dex */
public class AWS3Signer extends AbstractAWSSigner {
    private static final String AUTHORIZATION_HEADER = "X-Amzn-Authorization";
    private static final String HTTPS_SCHEME = "AWS3-HTTPS";
    private static final String HTTP_SCHEME = "AWS3";
    private static final String NONCE_HEADER = "x-amz-nonce";
    private static final Log log = LogFactory.getLog(AWS3Signer.class);
    private String overriddenDate;

    private String getSignedHeadersComponent(Request<?> request) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SignedHeaders=");
        boolean z = true;
        for (String str : getHeadersForStringToSign(request)) {
            if (!z) {
                outline107.append(";");
            }
            outline107.append(str);
            z = false;
        }
        return outline107.toString();
    }

    @Override // com.amazonaws.auth.AbstractAWSSigner
    protected void addSessionCredentials(Request<?> request, AWSSessionCredentials aWSSessionCredentials) {
        request.addHeader(Headers.SECURITY_TOKEN, aWSSessionCredentials.getSessionToken());
    }

    protected String getCanonicalizedHeadersForStringToSign(Request<?> request) {
        List<String> headersForStringToSign = getHeadersForStringToSign(request);
        for (int i = 0; i < headersForStringToSign.size(); i++) {
            headersForStringToSign.set(i, StringUtils.lowerCase(headersForStringToSign.get(i)));
        }
        TreeMap treeMap = new TreeMap();
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            if (headersForStringToSign.contains(StringUtils.lowerCase(entry.getKey()))) {
                treeMap.put(StringUtils.lowerCase(entry.getKey()), entry.getValue());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry2 : treeMap.entrySet()) {
            sb.append(StringUtils.lowerCase((String) entry2.getKey()));
            sb.append(":");
            sb.append((String) entry2.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    protected List<String> getHeadersForStringToSign(Request<?> request) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            String key = entry.getKey();
            String lowerCase = StringUtils.lowerCase(key);
            if (lowerCase.startsWith("x-amz") || "host".equals(lowerCase)) {
                arrayList.add(key);
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    void overrideDate(String str) {
        this.overriddenDate = str;
    }

    boolean shouldUseHttpsScheme(Request<?> request) {
        try {
            String lowerCase = StringUtils.lowerCase(request.getEndpoint().toURL().getProtocol());
            if ("http".equals(lowerCase)) {
                return false;
            }
            if ("https".equals(lowerCase)) {
                return true;
            }
            throw new AmazonClientException("Unknown request endpoint protocol encountered while signing request: " + lowerCase);
        } catch (MalformedURLException e) {
            throw new AmazonClientException("Unable to parse request endpoint during signing", e);
        }
    }

    @Override // com.amazonaws.auth.Signer
    public void sign(Request<?> request, AWSCredentials aWSCredentials) {
        if (aWSCredentials instanceof AnonymousAWSCredentials) {
            return;
        }
        AWSCredentials sanitizeCredentials = sanitizeCredentials(aWSCredentials);
        SigningAlgorithm signingAlgorithm = SigningAlgorithm.HmacSHA256;
        UUID.randomUUID().toString();
        String formatRFC822Date = DateUtils.formatRFC822Date(getSignatureDate(getTimeOffset(request)));
        String str = this.overriddenDate;
        if (str != null) {
            formatRFC822Date = str;
        }
        request.addHeader("Date", formatRFC822Date);
        request.addHeader("X-Amz-Date", formatRFC822Date);
        String host = request.getEndpoint().getHost();
        if (HttpUtils.isUsingNonDefaultPort(request.getEndpoint())) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(host, ":");
            outline113.append(request.getEndpoint().getPort());
            host = outline113.toString();
        }
        request.addHeader("Host", host);
        if (sanitizeCredentials instanceof AWSSessionCredentials) {
            addSessionCredentials(request, (AWSSessionCredentials) sanitizeCredentials);
        }
        String appendUri = HttpUtils.appendUri(request.getEndpoint().getPath(), request.getResourcePath());
        String str2 = request.getHttpMethod().toString() + "\n" + getCanonicalizedResourcePath(appendUri) + "\n" + getCanonicalizedQueryString(request.getParameters()) + "\n" + getCanonicalizedHeadersForStringToSign(request) + "\n" + getRequestPayloadWithoutQueryParams(request);
        byte[] hash = hash(str2);
        log.debug("Calculated StringToSign: " + str2);
        String signAndBase64Encode = signAndBase64Encode(hash, sanitizeCredentials.getAWSSecretKey(), signingAlgorithm);
        StringBuilder outline1132 = GeneratedOutlineSupport1.outline113(HTTP_SCHEME, " ");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AWSAccessKeyId=");
        outline107.append(sanitizeCredentials.getAWSAccessKeyId());
        outline107.append(",");
        outline1132.append(outline107.toString());
        outline1132.append("Algorithm=" + signingAlgorithm.toString() + ",");
        StringBuilder sb = new StringBuilder();
        sb.append(getSignedHeadersComponent(request));
        sb.append(",");
        outline1132.append(sb.toString());
        outline1132.append("Signature=" + signAndBase64Encode);
        request.addHeader(AUTHORIZATION_HEADER, outline1132.toString());
    }
}
