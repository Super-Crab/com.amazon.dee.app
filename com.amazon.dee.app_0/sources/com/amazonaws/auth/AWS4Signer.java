package com.amazonaws.auth;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.util.AwsHostNameUtils;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.HttpUtils;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.imap.IMAPStore;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
/* loaded from: classes13.dex */
public class AWS4Signer extends AbstractAWSSigner implements ServiceAwareSigner, RegionAwareSigner, Presigner {
    protected static final String ALGORITHM = "AWS4-HMAC-SHA256";
    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final long MAX_EXPIRATION_TIME_IN_SECONDS = 604800;
    private static final long MILLISEC = 1000;
    protected static final String TERMINATOR = "aws4_request";
    private static final String TIME_PATTERN = "yyyyMMdd'T'HHmmss'Z'";
    protected static final Log log = LogFactory.getLog(AWS4Signer.class);
    protected boolean doubleUrlEncode;
    protected Date overriddenDate;
    protected String regionName;
    protected String serviceName;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes13.dex */
    public static class HeaderSigningResult {
        private final String dateTime;
        private final byte[] kSigning;
        private final String scope;
        private final byte[] signature;

        public HeaderSigningResult(String str, String str2, byte[] bArr, byte[] bArr2) {
            this.dateTime = str;
            this.scope = str2;
            this.kSigning = bArr;
            this.signature = bArr2;
        }

        public String getDateTime() {
            return this.dateTime;
        }

        public byte[] getKSigning() {
            byte[] bArr = this.kSigning;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }

        public String getScope() {
            return this.scope;
        }

        public byte[] getSignature() {
            byte[] bArr = this.signature;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
    }

    public AWS4Signer() {
        this(true);
    }

    protected void addHostHeader(Request<?> request) {
        String host = request.getEndpoint().getHost();
        if (HttpUtils.isUsingNonDefaultPort(request.getEndpoint())) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(host, ":");
            outline113.append(request.getEndpoint().getPort());
            host = outline113.toString();
        }
        request.addHeader("Host", host);
    }

    @Override // com.amazonaws.auth.AbstractAWSSigner
    protected void addSessionCredentials(Request<?> request, AWSSessionCredentials aWSSessionCredentials) {
        request.addHeader(Headers.SECURITY_TOKEN, aWSSessionCredentials.getSessionToken());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String calculateContentHash(Request<?> request) {
        InputStream binaryRequestPayloadStream = getBinaryRequestPayloadStream(request);
        binaryRequestPayloadStream.mark(-1);
        String hex = BinaryUtils.toHex(hash(binaryRequestPayloadStream));
        try {
            binaryRequestPayloadStream.reset();
            return hex;
        } catch (IOException e) {
            throw new AmazonClientException("Unable to reset stream after calculating AWS4 signature", e);
        }
    }

    protected String calculateContentHashPresign(Request<?> request) {
        return calculateContentHash(request);
    }

    protected final HeaderSigningResult computeSignature(Request<?> request, String str, String str2, String str3, String str4, AWSCredentials aWSCredentials) {
        String extractRegionName = extractRegionName(request.getEndpoint());
        String extractServiceName = extractServiceName(request.getEndpoint());
        String outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline116(str, "/", extractRegionName, "/", extractServiceName), "/", TERMINATOR);
        String stringToSign = getStringToSign(str3, str2, outline91, getCanonicalRequest(request, str4));
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AWS4");
        outline107.append(aWSCredentials.getAWSSecretKey());
        byte[] sign = sign(TERMINATOR, sign(extractServiceName, sign(extractRegionName, sign(str, outline107.toString().getBytes(StringUtils.UTF8), SigningAlgorithm.HmacSHA256), SigningAlgorithm.HmacSHA256), SigningAlgorithm.HmacSHA256), SigningAlgorithm.HmacSHA256);
        return new HeaderSigningResult(str2, outline91, sign, sign(stringToSign.getBytes(StringUtils.UTF8), sign, SigningAlgorithm.HmacSHA256));
    }

    protected String extractRegionName(URI uri) {
        String str = this.regionName;
        return str != null ? str : AwsHostNameUtils.parseRegionName(uri.getHost(), this.serviceName);
    }

    protected String extractServiceName(URI uri) {
        String str = this.serviceName;
        return str != null ? str : AwsHostNameUtils.parseServiceName(uri);
    }

    protected String getCanonicalRequest(Request<?> request, String str) {
        String appendUri = HttpUtils.appendUri(request.getEndpoint().getPath(), request.getResourcePath());
        StringBuilder sb = new StringBuilder();
        sb.append(request.getHttpMethod().toString());
        sb.append("\n");
        sb.append(getCanonicalizedResourcePath(appendUri, this.doubleUrlEncode));
        sb.append("\n");
        sb.append(getCanonicalizedQueryString(request));
        sb.append("\n");
        sb.append(getCanonicalizedHeaderString(request));
        sb.append("\n");
        String outline92 = GeneratedOutlineSupport1.outline92(sb, getSignedHeadersString(request), "\n", str);
        Log log2 = log;
        log2.debug("AWS4 Canonical Request: '\"" + outline92 + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        return outline92;
    }

    protected String getCanonicalizedHeaderString(Request<?> request) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.addAll(request.getHeaders().keySet());
        Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (String str : arrayList) {
            if (needsSign(str)) {
                String replaceAll = StringUtils.lowerCase(str).replaceAll("\\s+", " ");
                String str2 = request.getHeaders().get(str);
                sb.append(replaceAll);
                sb.append(":");
                if (str2 != null) {
                    sb.append(str2.replaceAll("\\s+", " "));
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    protected final long getDateFromRequest(Request<?> request) {
        Date signatureDate = getSignatureDate(getTimeOffset(request));
        Date date = this.overriddenDate;
        if (date != null) {
            signatureDate = date;
        }
        return signatureDate.getTime();
    }

    protected final String getDateStamp(long j) {
        return DateUtils.format(DATE_PATTERN, new Date(j));
    }

    protected String getScope(Request<?> request, String str) {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline116(str, "/", extractRegionName(request.getEndpoint()), "/", extractServiceName(request.getEndpoint())), "/", TERMINATOR);
    }

    protected String getSignedHeadersString(Request<?> request) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.addAll(request.getHeaders().keySet());
        Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (String str : arrayList) {
            if (needsSign(str)) {
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(StringUtils.lowerCase(str));
            }
        }
        return sb.toString();
    }

    protected String getStringToSign(String str, String str2, String str3, String str4) {
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116(str, "\n", str2, "\n", str3);
        outline116.append("\n");
        outline116.append(BinaryUtils.toHex(hash(str4)));
        String sb = outline116.toString();
        Log log2 = log;
        log2.debug("AWS4 String to Sign: '\"" + sb + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        return sb;
    }

    protected final String getTimeStamp(long j) {
        return DateUtils.format("yyyyMMdd'T'HHmmss'Z'", new Date(j));
    }

    boolean needsSign(String str) {
        return IMAPStore.ID_DATE.equalsIgnoreCase(str) || "Content-MD5".equalsIgnoreCase(str) || "host".equalsIgnoreCase(str) || str.startsWith("x-amz") || str.startsWith("X-Amz");
    }

    void overrideDate(Date date) {
        this.overriddenDate = date;
    }

    @Override // com.amazonaws.auth.Presigner
    public void presignRequest(Request<?> request, AWSCredentials aWSCredentials, Date date) {
        if (aWSCredentials instanceof AnonymousAWSCredentials) {
            return;
        }
        long time = date != null ? (date.getTime() - System.currentTimeMillis()) / 1000 : 604800L;
        if (time <= MAX_EXPIRATION_TIME_IN_SECONDS) {
            addHostHeader(request);
            AWSCredentials sanitizeCredentials = sanitizeCredentials(aWSCredentials);
            if (sanitizeCredentials instanceof AWSSessionCredentials) {
                request.addParameter("X-Amz-Security-Token", ((AWSSessionCredentials) sanitizeCredentials).getSessionToken());
            }
            long dateFromRequest = getDateFromRequest(request);
            String dateStamp = getDateStamp(dateFromRequest);
            String timeStamp = getTimeStamp(dateFromRequest);
            request.addParameter("X-Amz-Algorithm", ALGORITHM);
            request.addParameter("X-Amz-Date", timeStamp);
            request.addParameter("X-Amz-SignedHeaders", getSignedHeadersString(request));
            request.addParameter("X-Amz-Expires", Long.toString(time));
            request.addParameter("X-Amz-Credential", sanitizeCredentials.getAWSAccessKeyId() + "/" + getScope(request, dateStamp));
            request.addParameter("X-Amz-Signature", BinaryUtils.toHex(computeSignature(request, dateStamp, timeStamp, ALGORITHM, calculateContentHashPresign(request), sanitizeCredentials).getSignature()));
            return;
        }
        throw new AmazonClientException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Requests that are pre-signed by SigV4 algorithm are valid for at most 7 days. The expiration date set on the current request ["), getTimeStamp(date.getTime()), "] has exceeded this limit."));
    }

    protected void processRequestPayload(Request<?> request, HeaderSigningResult headerSigningResult) {
    }

    @Override // com.amazonaws.auth.RegionAwareSigner
    public void setRegionName(String str) {
        this.regionName = str;
    }

    @Override // com.amazonaws.auth.ServiceAwareSigner
    public void setServiceName(String str) {
        this.serviceName = str;
    }

    @Override // com.amazonaws.auth.Signer
    public void sign(Request<?> request, AWSCredentials aWSCredentials) {
        if (aWSCredentials instanceof AnonymousAWSCredentials) {
            return;
        }
        AWSCredentials sanitizeCredentials = sanitizeCredentials(aWSCredentials);
        if (sanitizeCredentials instanceof AWSSessionCredentials) {
            addSessionCredentials(request, (AWSSessionCredentials) sanitizeCredentials);
        }
        addHostHeader(request);
        long dateFromRequest = getDateFromRequest(request);
        String dateStamp = getDateStamp(dateFromRequest);
        String scope = getScope(request, dateStamp);
        String calculateContentHash = calculateContentHash(request);
        String timeStamp = getTimeStamp(dateFromRequest);
        request.addHeader("X-Amz-Date", timeStamp);
        if (request.getHeaders().get("x-amz-content-sha256") != null && "required".equals(request.getHeaders().get("x-amz-content-sha256"))) {
            request.addHeader("x-amz-content-sha256", calculateContentHash);
        }
        HeaderSigningResult computeSignature = computeSignature(request, dateStamp, timeStamp, ALGORITHM, calculateContentHash, sanitizeCredentials);
        String outline72 = GeneratedOutlineSupport1.outline72("Credential=", sanitizeCredentials.getAWSAccessKeyId() + "/" + scope);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SignedHeaders=");
        outline107.append(getSignedHeadersString(request));
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Signature=");
        outline1072.append(BinaryUtils.toHex(computeSignature.getSignature()));
        String sb2 = outline1072.toString();
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("AWS4-HMAC-SHA256 ", outline72, ", ", sb, ", ");
        outline116.append(sb2);
        request.addHeader("Authorization", outline116.toString());
        processRequestPayload(request, computeSignature);
    }

    public AWS4Signer(boolean z) {
        this.doubleUrlEncode = z;
    }
}
