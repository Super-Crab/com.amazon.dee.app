package com.amazon.photos.uploader.cds;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: UrlConnectionProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/cds/UrlConnectionProvider;", "", "applicationId", "", "applicationName", "createHttpURLConnection", "Ljava/net/HttpURLConnection;", "url", "Ljava/net/URL;", "userAgent", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface UrlConnectionProvider {
    @NotNull
    String applicationId();

    @NotNull
    String applicationName();

    @NotNull
    HttpURLConnection createHttpURLConnection(@NotNull URL url) throws IOException, InterruptedException;

    @NotNull
    String userAgent();
}
