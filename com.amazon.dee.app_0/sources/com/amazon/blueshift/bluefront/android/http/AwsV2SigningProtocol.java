package com.amazon.blueshift.bluefront.android.http;

import android.net.Uri;
import android.util.Base64;
import com.amazon.blueshift.bluefront.android.common.BluefrontCredential;
import com.amazon.blueshift.bluefront.android.common.UriUtils;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes11.dex */
public class AwsV2SigningProtocol implements SigningProtocol {
    private static final String AWS_ACCESS_KEY_ID = "AWSAccessKeyId";
    private static final String CHAR_AND = "&";
    private static final String CHAR_COLON = ":";
    private static final String CHAR_EQUAL = "=";
    private static final String CHAR_NEW_LINE = "\n";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final int NON_SECURE_PORT = 80;
    private static final String POST_METHOD = "POST";
    private static final String SCHEME_HTTP = "http";
    private static final String SCHEME_HTTPS = "https";
    private static final int SECURE_PORT = 443;
    private static final String SIGNATURE_KEY = "Signature";
    private static final String SIGNATURE_METHOD_KEY = "SignatureMethod";
    private static final String SIGNATURE_VERSION = "2";
    private static final String SIGNATURE_VERSION_KEY = "SignatureVersion";
    private static final String TIME_STAMP_KEY = "Timestamp";
    private static final TimeZone TIME_ZONE_UTC = TimeZone.getTimeZone(Constants.UTC);

    @VisibleForTesting
    String getDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TIME_ZONE_UTC);
        return simpleDateFormat.format(new Date());
    }

    @Override // com.amazon.blueshift.bluefront.android.http.SigningProtocol
    public URLWrapper sign(Uri uri, BluefrontCredential bluefrontCredential) throws MalformedURLException {
        Preconditions.checkNotNull(bluefrontCredential, "credentials cannot be null");
        Preconditions.checkNotNull(uri, "URI cannot be null");
        TreeMap treeMap = new TreeMap();
        for (String str : UriUtils.getQueryParameterNames(uri)) {
            String queryParameter = uri.getQueryParameter(str);
            if (queryParameter != null) {
                treeMap.put(str, queryParameter);
            }
        }
        treeMap.put(AWS_ACCESS_KEY_ID, Uri.encode(bluefrontCredential.getAccessId()));
        treeMap.put(SIGNATURE_VERSION_KEY, "2");
        treeMap.put(SIGNATURE_METHOD_KEY, "HmacSHA1");
        treeMap.put(TIME_STAMP_KEY, Uri.encode(getDateString()));
        Iterator it2 = treeMap.entrySet().iterator();
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("POST", "\n");
        outline113.append(uri.getHost());
        if (uri.getPort() > 0 && ((!uri.getScheme().equals("http") || uri.getPort() != 80) && (!uri.getScheme().equals("https") || uri.getPort() != SECURE_PORT))) {
            outline113.append(":");
            outline113.append(uri.getPort());
        }
        outline113.append("\n");
        outline113.append(uri.getPath());
        outline113.append("\n");
        StringBuilder sb = new StringBuilder();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            sb.append((String) entry.getKey());
            sb.append("=");
            sb.append((String) entry.getValue());
            if (it2.hasNext()) {
                sb.append("&");
            }
        }
        outline113.append(sb.toString());
        byte[] bytes = outline113.toString().getBytes(StandardCharsets.UTF_8);
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(new SecretKeySpec(bluefrontCredential.getAccessKey().getBytes(StandardCharsets.UTF_8), "HmacSHA1"));
            String encode = Uri.encode(Base64.encodeToString(mac.doFinal(bytes), 0).replace("\n", ""));
            sb.append("&Signature=");
            sb.append(encode);
            Uri.Builder buildUpon = uri.buildUpon();
            buildUpon.encodedQuery(sb.toString());
            return new URLWrapper(buildUpon.build().toString());
        } catch (InvalidKeyException | NoSuchAlgorithmException unused) {
            return null;
        }
    }
}
