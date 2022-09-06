package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.BuildConfig;
import com.amazon.alexa.mobilytics.auth.CredentialsProvider;
import com.amazon.alexa.mobilytics.s3.S3ClientProvider;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.whispercloak.KeyUtils;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.Base64;
import com.google.common.base.Preconditions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class S3ConfigPuller implements ConfigPuller {
    private static final String BUCKET_NAME = "alexa-mobile-analytics-configuration";
    private static final String OBJECT_KEY = "configuration";
    private static final String TAG = Log.tag(S3ConfigPuller.class);
    private AmazonS3 amazonS3;
    private String bucketName;
    private final S3ClientProvider clientProvider;
    private String s3ObjectTimeString;

    @Inject
    public S3ConfigPuller(@NonNull S3ClientProvider s3ClientProvider) {
        this.clientProvider = (S3ClientProvider) Preconditions.checkNotNull(s3ClientProvider);
    }

    private static String bytesToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private PublicKey publicKey() {
        try {
            return KeyFactory.getInstance(KeyUtils.ALGORITHM_RSA).generatePublic(new X509EncodedKeySpec(Base64.decode(BuildConfig.PUBLIC_KEY)));
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, e, "Error generating public key", new Object[0]);
            return null;
        } catch (InvalidKeySpecException e2) {
            Log.e(TAG, e2, "Error encoding public key", new Object[0]);
            return null;
        }
    }

    @Nullable
    private String streamToString(@NonNull InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String property = System.getProperty("line.separator");
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append(property);
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                    String sb2 = sb.toString();
                    bufferedReader.close();
                    return sb2;
                }
            }
        } catch (IOException e) {
            Log.e(TAG, e, "Unable to parse s3 data", new Object[0]);
            return null;
        }
    }

    private boolean verify(@NonNull String str, @NonNull String str2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            Signature signature = Signature.getInstance("NONEwithRSA");
            signature.initVerify(publicKey());
            signature.update(bytesToHex(digest).getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64.decode(str2));
        } catch (InvalidKeyException e) {
            Log.e(TAG, e, "Error in key", new Object[0]);
            return false;
        } catch (NoSuchAlgorithmException e2) {
            Log.e(TAG, e2, "Error verifying with RSA", new Object[0]);
            return false;
        } catch (SignatureException e3) {
            Log.e(TAG, e3, "Error in signature", new Object[0]);
            return false;
        }
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ConfigPuller
    public void initialize(@NonNull CredentialsProvider credentialsProvider, @NonNull Regions regions, int i) {
        this.amazonS3 = this.clientProvider.create(credentialsProvider, regions);
        if (i == 2) {
            this.bucketName = "alexa-mobile-analytics-configuration-prod";
        } else {
            this.bucketName = "alexa-mobile-analytics-configuration-gamma";
        }
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ConfigPuller
    @Nullable
    public String read(@Nullable String str) {
        String str2;
        AmazonS3 amazonS3 = this.amazonS3;
        if (amazonS3 == null) {
            return null;
        }
        try {
            S3Object object = amazonS3.getObject(new GetObjectRequest(this.bucketName, "configuration"));
            Date lastModified = object.getObjectMetadata().getLastModified();
            if (lastModified == null) {
                return null;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss ZZ", Locale.ENGLISH);
            if (str != null) {
                try {
                    Date parse = simpleDateFormat.parse(str);
                    if (parse != null) {
                        if (!lastModified.after(parse)) {
                            return null;
                        }
                    }
                } catch (ParseException e) {
                    Log.e(TAG, e, "Unable to parse date", new Object[0]);
                }
            }
            try {
                str2 = streamToString(object.getObjectContent());
            } catch (Exception e2) {
                Log.e(TAG, e2, "Unable to get data from S3 for bucket [%s] and key [%s]", this.bucketName, "configuration");
                str2 = null;
            }
            try {
                object.close();
            } catch (IOException e3) {
                Log.e(TAG, e3, "Unable to close S3 Object", new Object[0]);
            }
            if (str2 != null) {
                String[] split = str2.split("%%");
                if (split.length == 2 && verify(split[0], split[1])) {
                    this.s3ObjectTimeString = simpleDateFormat.format(lastModified);
                    return split[0];
                }
            }
            return null;
        } catch (Exception e4) {
            Log.e(TAG, e4, "Unable to access S3 Object", new Object[0]);
            return null;
        }
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ConfigPuller
    @NonNull
    public String updatedTime() {
        return this.s3ObjectTimeString;
    }
}
