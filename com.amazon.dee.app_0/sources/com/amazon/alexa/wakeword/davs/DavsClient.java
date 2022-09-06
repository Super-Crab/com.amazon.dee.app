package com.amazon.alexa.wakeword.davs;

import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.validation.Assertions;
import com.amazon.alexa.utils.validation.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class DavsClient {
    private static final String DAVS_BASE_URL = "https://api.amazonalexa.com/v2/deviceArtifacts/?artifactFilter=";
    private static final String TAG = "DavsClient";
    private final ArtifactDownloader artifactDownloader;
    private final Gson gson;
    private final OkHttpClient httpClient;

    public DavsClient(OkHttpClient okHttpClient, ArtifactDownloader artifactDownloader) {
        Preconditions.notNull(okHttpClient, "httpClient is null");
        Preconditions.notNull(artifactDownloader, "artifactDownloader is null");
        this.httpClient = okHttpClient;
        this.gson = createGson();
        this.artifactDownloader = artifactDownloader;
    }

    private static Gson createGson() {
        return new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create();
    }

    private String encode(String str) {
        return Base64.encodeToString(str.getBytes(), 10);
    }

    private String prepareArtifactManifestRequestUrl(ArtifactRequest artifactRequest) {
        String str = "artifact request: " + artifactRequest;
        String serialize = serialize(artifactRequest);
        Assertions.notEmpty(serialize, "invalid request");
        String str2 = "artifact request json: " + serialize;
        String encode = encode(serialize);
        String str3 = "encoded artifact request: " + encode;
        return DAVS_BASE_URL + encode;
    }

    private String requestArtifactManifest(ArtifactRequest artifactRequest) throws IOException {
        try {
            String prepareArtifactManifestRequestUrl = prepareArtifactManifestRequestUrl(artifactRequest);
            r0 = "artifact request url: " + prepareArtifactManifestRequestUrl;
            Request build = new Request.Builder().url(prepareArtifactManifestRequestUrl).build();
            String str = "sending a request: " + build;
            Response execute = this.httpClient.newCall(build).execute();
            DavsResponse valueOf = DavsResponse.valueOf(execute.code());
            String str2 = "got a response: " + execute;
            Assertions.isFalse(valueOf.isError(), "Got an error response: " + valueOf);
            return execute.body().string();
        } catch (Exception e) {
            throw new IOException("Failed to get artifact manifest", e);
        }
    }

    @VisibleForTesting
    ArtifactManifest deserialize(String str) {
        return (ArtifactManifest) this.gson.fromJson(str, (Class<Object>) ArtifactManifest.class);
    }

    public synchronized ArtifactFile getArtifact(ArtifactManifest artifactManifest, String str) throws ArtifactDownloadException {
        ArtifactFile download;
        Preconditions.notNull(artifactManifest, "Artifact manifest is null");
        Preconditions.notNull(str, "artifact name is null");
        String downloadUrl = artifactManifest.getDownloadUrl();
        String md5 = artifactManifest.getChecksum().getMd5();
        String str2 = "got downloadUrl: " + downloadUrl + ", artifact md5: " + md5;
        Assertions.notEmpty(downloadUrl, "invalid download url");
        Assertions.notEmpty(md5, "invalid md5 checksum");
        download = this.artifactDownloader.download(downloadUrl, str, md5);
        String str3 = "artifact " + str + " was downloaded successfully";
        return download;
    }

    public synchronized ArtifactManifest getArtifactManifest(ArtifactRequest artifactRequest) throws ArtifactDownloadException {
        String requestArtifactManifest;
        try {
            try {
                requestArtifactManifest = requestArtifactManifest(artifactRequest);
                String str = "got manifest: " + requestArtifactManifest;
                Assertions.notEmpty(requestArtifactManifest, "invalid artifact manifest");
            } catch (IOException e) {
                throw ArtifactDownloadException.create(ArtifactDownloadFailure.ARTIFACT_MANIFEST_DOWNLOAD_FAILURE, e);
            }
        } catch (JsonSyntaxException e2) {
            throw ArtifactDownloadException.create(ArtifactDownloadFailure.ARTIFACT_MANIFEST_PARSE_FAILURE, e2);
        }
        return deserialize(requestArtifactManifest);
    }

    @VisibleForTesting
    String serialize(ArtifactRequest artifactRequest) {
        return this.gson.toJson(artifactRequest);
    }
}
