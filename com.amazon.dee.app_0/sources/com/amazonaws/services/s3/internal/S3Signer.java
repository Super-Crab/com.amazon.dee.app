package com.amazonaws.services.s3.internal;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.Request;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AbstractAWSSigner;
import com.amazonaws.auth.SigningAlgorithm;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.util.HttpUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes13.dex */
public class S3Signer extends AbstractAWSSigner {
    private static final Log log = LogFactory.getLog(S3Signer.class);
    private final Set<String> additionalQueryParamsToSign;
    private final String httpVerb;
    private final String resourcePath;

    public S3Signer() {
        this.httpVerb = null;
        this.resourcePath = null;
        this.additionalQueryParamsToSign = null;
    }

    @Override // com.amazonaws.auth.AbstractAWSSigner
    protected void addSessionCredentials(Request<?> request, AWSSessionCredentials aWSSessionCredentials) {
        request.addHeader(Headers.SECURITY_TOKEN, aWSSessionCredentials.getSessionToken());
    }

    void sign(Request<?> request, AWSCredentials aWSCredentials, Date date) {
        if (this.resourcePath != null) {
            if (aWSCredentials != null && aWSCredentials.getAWSSecretKey() != null) {
                AWSCredentials sanitizeCredentials = sanitizeCredentials(aWSCredentials);
                if (sanitizeCredentials instanceof AWSSessionCredentials) {
                    addSessionCredentials(request, (AWSSessionCredentials) sanitizeCredentials);
                }
                String appendUri = HttpUtils.appendUri(request.getEndpoint().getPath(), this.resourcePath, true);
                Date signatureDate = getSignatureDate(getTimeOffset(request));
                if (date == null) {
                    date = signatureDate;
                }
                request.addHeader("Date", ServiceUtils.formatRfc822Date(date));
                String makeS3CanonicalString = RestUtils.makeS3CanonicalString(this.httpVerb, appendUri, request, null, this.additionalQueryParamsToSign);
                Log log2 = log;
                log2.debug("Calculated string to sign:\n\"" + makeS3CanonicalString + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                String signAndBase64Encode = super.signAndBase64Encode(makeS3CanonicalString, sanitizeCredentials.getAWSSecretKey(), SigningAlgorithm.HmacSHA1);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AWS ");
                outline107.append(sanitizeCredentials.getAWSAccessKeyId());
                outline107.append(":");
                outline107.append(signAndBase64Encode);
                request.addHeader("Authorization", outline107.toString());
                return;
            }
            log.debug("Canonical string will not be signed, as no AWS Secret Key was provided");
            return;
        }
        throw new UnsupportedOperationException("Cannot sign a request using a dummy S3Signer instance with no resource path");
    }

    public S3Signer(String str, String str2) {
        this(str, str2, null);
    }

    public S3Signer(String str, String str2, Collection<String> collection) {
        if (str2 != null) {
            this.httpVerb = str;
            this.resourcePath = str2;
            this.additionalQueryParamsToSign = collection == null ? null : Collections.unmodifiableSet(new HashSet(collection));
            return;
        }
        throw new IllegalArgumentException("Parameter resourcePath is empty");
    }

    @Override // com.amazonaws.auth.Signer
    public void sign(Request<?> request, AWSCredentials aWSCredentials) {
        sign(request, aWSCredentials, (Date) null);
    }
}
