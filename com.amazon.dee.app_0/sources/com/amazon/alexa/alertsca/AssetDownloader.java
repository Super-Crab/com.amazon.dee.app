package com.amazon.alexa.alertsca;

import android.net.Uri;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.utils.validation.Preconditions;
import dagger.Lazy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.apache.commons.io.IOUtils;
@Singleton
/* loaded from: classes6.dex */
public class AssetDownloader {
    private static final String DOWNLOAD_THREAD_NAME = "download_thread";
    private static final String FILE_SUFFIX = ".mp3";
    private static final String TAG = "AssetDownloader";
    private final Map<AlertsToken, Set<FutureTask>> assetDownloadStatus;
    private final ExecutorService executorService;
    private final Lazy<File> lazyCacheDirectory;

    /* loaded from: classes6.dex */
    private static class DownloadCallable implements Callable<Boolean> {
        private final File assetFile;
        private final List<Uri> assetUrls;
        private InputStream inputStream;
        private OutputStream outputStream;

        DownloadCallable(List<Uri> list, File file) {
            Preconditions.notNull(list, "AssetUrls is null");
            Preconditions.notNull(file, "AssetFile is null");
            this.assetUrls = list;
            this.assetFile = file;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        /* renamed from: call */
        public Boolean mo715call() {
            String unused = AssetDownloader.TAG;
            try {
                try {
                    this.outputStream = new FileOutputStream(this.assetFile);
                    for (Uri uri : this.assetUrls) {
                        URLConnection openConnection = new URL(uri.toString()).openConnection();
                        openConnection.setConnectTimeout(5000);
                        openConnection.setReadTimeout(5000);
                        this.inputStream = openConnection.getInputStream();
                        IOUtils.copy(this.inputStream, this.outputStream);
                    }
                    this.outputStream.flush();
                    return true;
                } catch (IOException e) {
                    Log.e(AssetDownloader.TAG, e.toString());
                    IOUtils.closeQuietly(this.inputStream);
                    IOUtils.closeQuietly(this.outputStream);
                    return false;
                }
            } finally {
                IOUtils.closeQuietly(this.inputStream);
                IOUtils.closeQuietly(this.outputStream);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public AssetDownloader(@Named("CACHE_DIRECTORY") Lazy<File> lazy) {
        this(lazy, ExecutorFactory.newSingleThreadCachedThreadPool(DOWNLOAD_THREAD_NAME));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Uri download(List<Uri> list, AlertsToken alertsToken) {
        String str = "Downloading asset for alert: " + alertsToken;
        Uri uri = Uri.EMPTY;
        try {
            File createTempFile = File.createTempFile(alertsToken.toString(), FILE_SUFFIX, this.lazyCacheDirectory.mo358get());
            uri = Uri.fromFile(createTempFile);
            FutureTask futureTask = new FutureTask(new DownloadCallable(list, createTempFile));
            this.executorService.submit(futureTask);
            HashSet hashSet = new HashSet();
            if (this.assetDownloadStatus.containsKey(alertsToken)) {
                hashSet.addAll(this.assetDownloadStatus.get(alertsToken));
            }
            hashSet.add(futureTask);
            this.assetDownloadStatus.put(alertsToken, hashSet);
        } catch (IOException e) {
            Log.e(TAG, "Unable to download asset for alert token: " + alertsToken, e);
        }
        return uri;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean isAssetsDownloadComplete(com.amazon.alexa.alertsca.AlertsToken r4) {
        /*
            r3 = this;
            java.util.Map<com.amazon.alexa.alertsca.AlertsToken, java.util.Set<java.util.concurrent.FutureTask>> r0 = r3.assetDownloadStatus
            boolean r0 = r0.containsKey(r4)
            r1 = 0
            if (r0 != 0) goto La
            return r1
        La:
            java.util.Map<com.amazon.alexa.alertsca.AlertsToken, java.util.Set<java.util.concurrent.FutureTask>> r0 = r3.assetDownloadStatus
            java.lang.Object r4 = r0.get(r4)
            java.util.Set r4 = (java.util.Set) r4
            java.util.Iterator r4 = r4.iterator()
        L16:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L2f
            java.lang.Object r0 = r4.next()
            java.util.concurrent.FutureTask r0 = (java.util.concurrent.FutureTask) r0
            boolean r2 = r0.isCancelled()
            if (r2 != 0) goto L2e
            boolean r0 = r0.isDone()
            if (r0 != 0) goto L16
        L2e:
            return r1
        L2f:
            r4 = 1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.alertsca.AssetDownloader.isAssetsDownloadComplete(com.amazon.alexa.alertsca.AlertsToken):boolean");
    }

    @VisibleForTesting
    AssetDownloader(Lazy<File> lazy, ExecutorService executorService) {
        this.lazyCacheDirectory = lazy;
        this.assetDownloadStatus = new HashMap();
        this.executorService = executorService;
    }
}
