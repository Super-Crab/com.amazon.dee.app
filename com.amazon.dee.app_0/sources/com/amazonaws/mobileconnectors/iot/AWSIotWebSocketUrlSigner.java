package com.amazonaws.mobileconnectors.iot;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.SigningAlgorithm;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class AWSIotWebSocketUrlSigner {
    private static final String ALGORITHM = "AWS4-HMAC-SHA256";
    private static final String CANONICAL_URI = "/mqtt";
    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final String KEY_PREFIX = "AWS4";
    private static final String METHOD = "GET";
    private static final String TERMINATOR = "aws4_request";
    private static final String TIME_PATTERN = "yyyyMMdd'T'HHmmss'Z'";
    private String signerServiceName;

    public AWSIotWebSocketUrlSigner(String str) {
        this.signerServiceName = str;
    }

    private String getAmzDate(long j) {
        return DateUtils.format("yyyyMMdd'T'HHmmss'Z'", new Date(j));
    }

    private String getDateStamp(long j) {
        return DateUtils.format(DATE_PATTERN, new Date(j));
    }

    private byte[] getSigningKey(String str, String str2, String str3, AWSCredentials aWSCredentials) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(KEY_PREFIX);
        outline107.append(aWSCredentials.getAWSSecretKey());
        return sign(TERMINATOR, sign(str3, sign(str2, sign(str, outline107.toString().getBytes(), SigningAlgorithm.HmacSHA256), SigningAlgorithm.HmacSHA256), SigningAlgorithm.HmacSHA256), SigningAlgorithm.HmacSHA256);
    }

    public String getSignedUrl(String str, AWSCredentials aWSCredentials, long j) {
        if (!(aWSCredentials instanceof AnonymousAWSCredentials)) {
            String name = AwsIotEndpointUtility.getRegionFromIotEndpoint(str).getName();
            AWSCredentials sanitizeCredentials = sanitizeCredentials(aWSCredentials);
            String amzDate = getAmzDate(j);
            String dateStamp = getDateStamp(j);
            StringBuilder sb = new StringBuilder();
            sb.append(dateStamp);
            sb.append("/");
            sb.append(name);
            sb.append("/");
            String outline91 = GeneratedOutlineSupport1.outline91(sb, this.signerServiceName, "/aws4_request");
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("X-Amz-Algorithm=", ALGORITHM, "&X-Amz-Credential=");
            try {
                outline115.append(URLEncoder.encode(sanitizeCredentials.getAWSAccessKeyId() + "/" + outline91, StringUtils.UTF8.name()));
                outline115.append("&X-Amz-Date=");
                outline115.append(amzDate);
                outline115.append("&X-Amz-SignedHeaders=host");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("host:");
                String outline912 = GeneratedOutlineSupport1.outline91(sb2, str, "\n");
                String hex = BinaryUtils.toHex(hash(""));
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GET\n/mqtt\n");
                outline107.append(outline115.toString());
                outline107.append("\n");
                outline107.append(outline912);
                outline107.append("\nhost\n");
                outline107.append(hex);
                String sb3 = outline107.toString();
                StringBuilder outline116 = GeneratedOutlineSupport1.outline116("AWS4-HMAC-SHA256\n", amzDate, "\n", outline91, "\n");
                outline116.append(BinaryUtils.toHex(hash(sb3)));
                String hex2 = BinaryUtils.toHex(sign(outline116.toString().getBytes(), getSigningKey(dateStamp, name, this.signerServiceName, sanitizeCredentials), SigningAlgorithm.HmacSHA256));
                outline115.append("&X-Amz-Signature=");
                outline115.append(hex2);
                String str2 = "wss://" + str + CANONICAL_URI + WebConstants.UriConstants.QUESTIONMARK_KEY + outline115.toString();
                if (!(aWSCredentials instanceof AWSSessionCredentials)) {
                    return str2;
                }
                try {
                    return GeneratedOutlineSupport1.outline75(str2, "&X-Amz-Security-Token=", URLEncoder.encode(((AWSSessionCredentials) aWSCredentials).getSessionToken(), StringUtils.UTF8.name()));
                } catch (UnsupportedEncodingException e) {
                    throw new AmazonClientException("Error encoding URL when appending session token to URL", e);
                }
            } catch (UnsupportedEncodingException e2) {
                throw new AmazonClientException("Error encoding URL when building WebSocket URL", e2);
            }
        }
        throw new IllegalArgumentException("Credentials cannot be Anonymous");
    }

    byte[] hash(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StringUtils.UTF8));
            return messageDigest.digest();
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to compute hash while signing request: ")), e);
        }
    }

    AWSCredentials sanitizeCredentials(AWSCredentials aWSCredentials) {
        String aWSAccessKeyId = aWSCredentials.getAWSAccessKeyId();
        String aWSSecretKey = aWSCredentials.getAWSSecretKey();
        boolean z = aWSCredentials instanceof AWSSessionCredentials;
        String sessionToken = z ? ((AWSSessionCredentials) aWSCredentials).getSessionToken() : null;
        if (aWSSecretKey != null) {
            aWSSecretKey = aWSSecretKey.trim();
        }
        if (aWSAccessKeyId != null) {
            aWSAccessKeyId = aWSAccessKeyId.trim();
        }
        if (sessionToken != null) {
            sessionToken = sessionToken.trim();
        }
        if (z) {
            return new BasicSessionCredentials(aWSAccessKeyId, aWSSecretKey, sessionToken);
        }
        return new BasicAWSCredentials(aWSAccessKeyId, aWSSecretKey);
    }

    byte[] sign(String str, byte[] bArr, SigningAlgorithm signingAlgorithm) {
        try {
            return sign(str.getBytes(StringUtils.UTF8), bArr, signingAlgorithm);
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to calculate a request signature: ")), e);
        }
    }

    byte[] sign(byte[] bArr, byte[] bArr2, SigningAlgorithm signingAlgorithm) {
        try {
            Mac mac = Mac.getInstance(signingAlgorithm.toString());
            mac.init(new SecretKeySpec(bArr2, signingAlgorithm.toString()));
            return mac.doFinal(bArr);
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to calculate a request signature: ")), e);
        }
    }
}
