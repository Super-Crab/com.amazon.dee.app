package com.amazon.alexa.wakeword.davs;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.io.IOUtils;
/* loaded from: classes11.dex */
public class ArtifactDownloader {
    private static final String TAG = "ArtifactDownloader";
    private final ArtifactManager artifactManager;
    private final OkHttpClient httpClient;

    public ArtifactDownloader(OkHttpClient okHttpClient, ArtifactManager artifactManager) {
        Preconditions.notNull(okHttpClient, "http Client is null");
        Preconditions.notNull(artifactManager, "ArtifactManager is null");
        this.httpClient = okHttpClient;
        this.artifactManager = artifactManager;
    }

    private ServerResponse makeRequest(String str) throws ArtifactDownloadException {
        if (HttpUrl.parse(str) != null) {
            try {
                return new ServerResponse(this.httpClient.newCall(new Request.Builder().url(str).build()).execute());
            } catch (SocketTimeoutException e) {
                Log.e(TAG, "ServerCall failed - time out", e);
                throw ArtifactDownloadException.create(ArtifactDownloadFailure.CONNECTION_TIMED_OUT);
            } catch (IOException e2) {
                Log.e(TAG, "ServerCall failed", e2);
                throw ArtifactDownloadException.create(ArtifactDownloadFailure.CONNECTION_FAILED);
            }
        }
        throw ArtifactDownloadException.create(ArtifactDownloadFailure.INVALID_URL);
    }

    @VisibleForTesting
    byte[] convertStreamToByteArray(InputStream inputStream) throws IOException {
        return IOUtils.toByteArray(inputStream);
    }

    public ArtifactFile download(String str, String str2, String str3) throws ArtifactDownloadException {
        Preconditions.isFalse(TextUtils.isEmpty(str), "url is empty");
        Preconditions.isFalse(TextUtils.isEmpty(str2), "filename is empty");
        Preconditions.isFalse(TextUtils.isEmpty(str3), "expected MD5 is empty");
        ServerResponse makeRequest = makeRequest(str);
        if (makeRequest.isSuccessful()) {
            if (makeRequest.getContentLength() != -1) {
                try {
                    InputStream bodyStream = makeRequest.getBodyStream();
                    byte[] convertStreamToByteArray = convertStreamToByteArray(bodyStream);
                    if (CheckSumUtils.verifyChecksum(convertStreamToByteArray, str3)) {
                        ArtifactFile writeArtifact = this.artifactManager.writeArtifact(str2, convertStreamToByteArray);
                        String str4 = TAG;
                        Log.i(str4, "finish writing artifact to storage: " + str2);
                        if (bodyStream != null) {
                            bodyStream.close();
                        }
                        return writeArtifact;
                    }
                    throw ArtifactDownloadException.create(ArtifactDownloadFailure.CHECKSUM_MISMATCH);
                } catch (IOException e) {
                    this.artifactManager.removeArtifact(str2);
                    String str5 = TAG;
                    Log.e(str5, "failed to download the artifact. removing file: " + str2, e);
                    throw ArtifactDownloadException.create(ArtifactDownloadFailure.ARTIFACT_FILE_WRITE_FAILURE);
                }
            }
            throw ArtifactDownloadException.create(ArtifactDownloadFailure.INVALID_CONTENT_LENGTH);
        }
        ArtifactDownloadFailure artifactDownloadFailure = ArtifactDownloadFailure.HTTP_RESPONSE_ERROR;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to pull artifact from the cloud: ");
        outline107.append(makeRequest.getDavsResponse());
        throw ArtifactDownloadException.create(artifactDownloadFailure, new IOException(outline107.toString()));
    }
}
