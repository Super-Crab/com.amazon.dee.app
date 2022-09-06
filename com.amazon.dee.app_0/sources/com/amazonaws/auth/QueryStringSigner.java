package com.amazonaws.auth;

import com.amazon.dee.sdk.iotsoftap.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
/* loaded from: classes13.dex */
public class QueryStringSigner extends AbstractAWSSigner implements Signer {
    private Date overriddenDate;

    private String calculateStringToSignV1(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        treeMap.putAll(map);
        for (Map.Entry entry : treeMap.entrySet()) {
            sb.append((String) entry.getKey());
            sb.append((String) entry.getValue());
        }
        return sb.toString();
    }

    private String calculateStringToSignV2(Request<?> request) {
        URI endpoint = request.getEndpoint();
        Map<String, String> parameters = request.getParameters();
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("POST", "\n");
        outline113.append(getCanonicalizedEndpoint(endpoint));
        outline113.append("\n");
        outline113.append(getCanonicalizedResourcePath(request));
        outline113.append("\n");
        outline113.append(getCanonicalizedQueryString(parameters));
        return outline113.toString();
    }

    private String getCanonicalizedResourcePath(Request<?> request) {
        String str = "";
        if (request.getEndpoint().getPath() != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
            outline107.append(request.getEndpoint().getPath());
            str = outline107.toString();
        }
        if (request.getResourcePath() != null) {
            if (str.length() > 0 && !str.endsWith("/") && !request.getResourcePath().startsWith("/")) {
                str = GeneratedOutlineSupport1.outline72(str, "/");
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(str);
            outline1072.append(request.getResourcePath());
            str = outline1072.toString();
        } else if (!str.endsWith("/")) {
            str = GeneratedOutlineSupport1.outline72(str, "/");
        }
        if (!str.startsWith("/")) {
            str = GeneratedOutlineSupport1.outline72("/", str);
        }
        return str.startsWith("//") ? str.substring(1) : str;
    }

    private String getFormattedTimestamp(int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        Date date = this.overriddenDate;
        if (date != null) {
            return simpleDateFormat.format(date);
        }
        return simpleDateFormat.format(getSignatureDate(i));
    }

    @Override // com.amazonaws.auth.AbstractAWSSigner
    protected void addSessionCredentials(Request<?> request, AWSSessionCredentials aWSSessionCredentials) {
        request.addParameter("SecurityToken", aWSSessionCredentials.getSessionToken());
    }

    void overrideDate(Date date) {
        this.overriddenDate = date;
    }

    @Override // com.amazonaws.auth.Signer
    public void sign(Request<?> request, AWSCredentials aWSCredentials) {
        sign(request, SignatureVersion.V2, SigningAlgorithm.HmacSHA256, aWSCredentials);
    }

    public void sign(Request<?> request, SignatureVersion signatureVersion, SigningAlgorithm signingAlgorithm, AWSCredentials aWSCredentials) {
        String calculateStringToSignV2;
        if (aWSCredentials instanceof AnonymousAWSCredentials) {
            return;
        }
        AWSCredentials sanitizeCredentials = sanitizeCredentials(aWSCredentials);
        request.addParameter("AWSAccessKeyId", sanitizeCredentials.getAWSAccessKeyId());
        request.addParameter("SignatureVersion", signatureVersion.toString());
        request.addParameter("Timestamp", getFormattedTimestamp(getTimeOffset(request)));
        if (sanitizeCredentials instanceof AWSSessionCredentials) {
            addSessionCredentials(request, (AWSSessionCredentials) sanitizeCredentials);
        }
        if (signatureVersion.equals(SignatureVersion.V1)) {
            calculateStringToSignV2 = calculateStringToSignV1(request.getParameters());
        } else if (signatureVersion.equals(SignatureVersion.V2)) {
            request.addParameter("SignatureMethod", signingAlgorithm.toString());
            calculateStringToSignV2 = calculateStringToSignV2(request);
        } else {
            throw new AmazonClientException("Invalid Signature Version specified");
        }
        request.addParameter("Signature", signAndBase64Encode(calculateStringToSignV2, sanitizeCredentials.getAWSSecretKey(), signingAlgorithm));
    }
}
