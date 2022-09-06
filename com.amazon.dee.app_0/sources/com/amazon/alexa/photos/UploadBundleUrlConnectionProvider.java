package com.amazon.alexa.photos;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.auth.AuthenticatedURLConnectionFactory;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.photos.uploader.cds.UrlConnectionProvider;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class UploadBundleUrlConnectionProvider implements UrlConnectionProvider {
    @NonNull
    private final AuthenticatedURLConnectionFactory connectionFactory;
    @NonNull
    private final PhotosAppInfoProvider photosAppInfoProvider;

    public UploadBundleUrlConnectionProvider(@NonNull PhotosAppInfoProvider photosAppInfoProvider, @NonNull AuthenticatedURLConnectionFactory authenticatedURLConnectionFactory) {
        this.photosAppInfoProvider = photosAppInfoProvider;
        this.connectionFactory = authenticatedURLConnectionFactory;
    }

    @Override // com.amazon.photos.uploader.cds.UrlConnectionProvider
    @NotNull
    public String applicationId() {
        return this.photosAppInfoProvider.getApplicationId();
    }

    @Override // com.amazon.photos.uploader.cds.UrlConnectionProvider
    @NotNull
    public String applicationName() {
        return this.photosAppInfoProvider.getApplicationName();
    }

    @Override // com.amazon.photos.uploader.cds.UrlConnectionProvider
    @NotNull
    public HttpURLConnection createHttpURLConnection(@NotNull URL url) throws IOException, InterruptedException {
        try {
            return this.connectionFactory.createHttpURLConnection(url);
        } catch (CloudDriveException e) {
            throw new IOException(e);
        }
    }

    @Override // com.amazon.photos.uploader.cds.UrlConnectionProvider
    @NotNull
    public String userAgent() {
        return this.photosAppInfoProvider.getUserAgent();
    }
}
