package com.amazonaws.auth;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.internal.SdkDigestInputStream;
import com.amazonaws.util.Base64;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.HttpUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes13.dex */
public abstract class AbstractAWSSigner implements Signer {
    private static final int BUFFER_SIZE_MULTIPLIER = 5;
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final int TIME_MILLISEC = 1000;
    private static final ThreadLocal<MessageDigest> SHA256_MESSAGE_DIGEST = new ThreadLocal<MessageDigest>() { // from class: com.amazonaws.auth.AbstractAWSSigner.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to get SHA256 Function");
                outline107.append(e.getMessage());
                throw new AmazonClientException(outline107.toString(), e);
            }
        }
    };
    public static final String EMPTY_STRING_SHA256_HEX = BinaryUtils.toHex(doHash(""));

    private static byte[] doHash(String str) {
        try {
            MessageDigest messageDigestInstance = getMessageDigestInstance();
            messageDigestInstance.update(str.getBytes(StringUtils.UTF8));
            return messageDigestInstance.digest();
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to compute hash while signing request: ")), e);
        }
    }

    private static MessageDigest getMessageDigestInstance() {
        MessageDigest messageDigest = SHA256_MESSAGE_DIGEST.get();
        messageDigest.reset();
        return messageDigest;
    }

    protected abstract void addSessionCredentials(Request<?> request, AWSSessionCredentials aWSSessionCredentials);

    protected byte[] getBinaryRequestPayload(Request<?> request) {
        if (HttpUtils.usePayloadForQueryParameters(request)) {
            String encodeParameters = HttpUtils.encodeParameters(request);
            return encodeParameters == null ? new byte[0] : encodeParameters.getBytes(StringUtils.UTF8);
        }
        return getBinaryRequestPayloadWithoutQueryParams(request);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public InputStream getBinaryRequestPayloadStream(Request<?> request) {
        if (HttpUtils.usePayloadForQueryParameters(request)) {
            String encodeParameters = HttpUtils.encodeParameters(request);
            if (encodeParameters == null) {
                return new ByteArrayInputStream(new byte[0]);
            }
            return new ByteArrayInputStream(encodeParameters.getBytes(StringUtils.UTF8));
        }
        return getBinaryRequestPayloadStreamWithoutQueryParams(request);
    }

    protected InputStream getBinaryRequestPayloadStreamWithoutQueryParams(Request<?> request) {
        try {
            InputStream content = request.getContent();
            if (content == null) {
                return new ByteArrayInputStream(new byte[0]);
            }
            if (content instanceof StringInputStream) {
                return content;
            }
            if (content.markSupported()) {
                return request.getContent();
            }
            throw new AmazonClientException("Unable to read request payload to sign request.");
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to read request payload to sign request: ")), e);
        }
    }

    protected byte[] getBinaryRequestPayloadWithoutQueryParams(Request<?> request) {
        InputStream binaryRequestPayloadStreamWithoutQueryParams = getBinaryRequestPayloadStreamWithoutQueryParams(request);
        try {
            binaryRequestPayloadStreamWithoutQueryParams.mark(-1);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[FujifilmMakernoteDirectory.TAG_DYNAMIC_RANGE];
            while (true) {
                int read = binaryRequestPayloadStreamWithoutQueryParams.read(bArr);
                if (read == -1) {
                    byteArrayOutputStream.close();
                    binaryRequestPayloadStreamWithoutQueryParams.reset();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to read request payload to sign request: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCanonicalizedEndpoint(URI uri) {
        String lowerCase = StringUtils.lowerCase(uri.getHost());
        if (HttpUtils.isUsingNonDefaultPort(uri)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(lowerCase, ":");
            outline113.append(uri.getPort());
            return outline113.toString();
        }
        return lowerCase;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCanonicalizedQueryString(Map<String, String> map) {
        TreeMap treeMap = new TreeMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            treeMap.put(HttpUtils.urlEncode(entry.getKey(), false), HttpUtils.urlEncode(entry.getValue(), false));
        }
        StringBuilder sb = new StringBuilder();
        Iterator it2 = treeMap.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it2.next();
            sb.append((String) entry2.getKey());
            sb.append(Config.Compare.EQUAL_TO);
            sb.append((String) entry2.getValue());
            if (it2.hasNext()) {
                sb.append(WebConstants.UriConstants.AMPERSAND_KEY);
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCanonicalizedResourcePath(String str) {
        return getCanonicalizedResourcePath(str, true);
    }

    protected String getRequestPayload(Request<?> request) {
        return newString(getBinaryRequestPayload(request));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getRequestPayloadWithoutQueryParams(Request<?> request) {
        return newString(getBinaryRequestPayloadWithoutQueryParams(request));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Date getSignatureDate(int i) {
        Date date = new Date();
        return i != 0 ? new Date(date.getTime() - (i * 1000)) : date;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getTimeOffset(Request<?> request) {
        return SDKGlobalConfiguration.getGlobalTimeOffset() != 0 ? SDKGlobalConfiguration.getGlobalTimeOffset() : request.getTimeOffset();
    }

    public byte[] hash(String str) {
        return doHash(str);
    }

    protected String newString(byte[] bArr) {
        return new String(bArr, StringUtils.UTF8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AWSCredentials sanitizeCredentials(AWSCredentials aWSCredentials) {
        String aWSAccessKeyId;
        String aWSSecretKey;
        String sessionToken;
        synchronized (aWSCredentials) {
            aWSAccessKeyId = aWSCredentials.getAWSAccessKeyId();
            aWSSecretKey = aWSCredentials.getAWSSecretKey();
            sessionToken = aWSCredentials instanceof AWSSessionCredentials ? ((AWSSessionCredentials) aWSCredentials).getSessionToken() : null;
        }
        if (aWSSecretKey != null) {
            aWSSecretKey = aWSSecretKey.trim();
        }
        if (aWSAccessKeyId != null) {
            aWSAccessKeyId = aWSAccessKeyId.trim();
        }
        if (sessionToken != null) {
            sessionToken = sessionToken.trim();
        }
        if (aWSCredentials instanceof AWSSessionCredentials) {
            return new BasicSessionCredentials(aWSAccessKeyId, aWSSecretKey, sessionToken);
        }
        return new BasicAWSCredentials(aWSAccessKeyId, aWSSecretKey);
    }

    public byte[] sign(String str, byte[] bArr, SigningAlgorithm signingAlgorithm) {
        try {
            return sign(str.getBytes(StringUtils.UTF8), bArr, signingAlgorithm);
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to calculate a request signature: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String signAndBase64Encode(String str, String str2, SigningAlgorithm signingAlgorithm) {
        return signAndBase64Encode(str.getBytes(StringUtils.UTF8), str2, signingAlgorithm);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCanonicalizedResourcePath(String str, boolean z) {
        if (str == null || str.length() == 0) {
            return "/";
        }
        if (z) {
            str = HttpUtils.urlEncode(str, true);
        }
        return str.startsWith("/") ? str : "/".concat(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] hash(InputStream inputStream) {
        try {
            SdkDigestInputStream sdkDigestInputStream = new SdkDigestInputStream(inputStream, getMessageDigestInstance());
            while (sdkDigestInputStream.read(new byte[1024]) > -1) {
            }
            return sdkDigestInputStream.getMessageDigest().digest();
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to compute hash while signing request: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String signAndBase64Encode(byte[] bArr, String str, SigningAlgorithm signingAlgorithm) {
        try {
            return Base64.encodeAsString(sign(bArr, str.getBytes(StringUtils.UTF8), signingAlgorithm));
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to calculate a request signature: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] sign(byte[] bArr, byte[] bArr2, SigningAlgorithm signingAlgorithm) {
        try {
            Mac mac = Mac.getInstance(signingAlgorithm.toString());
            mac.init(new SecretKeySpec(bArr2, signingAlgorithm.toString()));
            return mac.doFinal(bArr);
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to calculate a request signature: ")), e);
        }
    }

    public byte[] hash(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to compute hash while signing request: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCanonicalizedQueryString(Request<?> request) {
        return HttpUtils.usePayloadForQueryParameters(request) ? "" : getCanonicalizedQueryString(request.getParameters());
    }
}
