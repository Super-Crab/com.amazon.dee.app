package com.amazon.alexa.hho.stickynotes;

import android.util.Log;
import com.amazon.alexa.hho.metrics.HHOMetricsConstants;
import com.amazon.alexa.hho.metrics.HHOMetricsService;
import com.amazon.alexa.hho.utils.FileManager;
import com.amazon.alexa.hho.utils.IOUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.common.base.Predicate;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.Callable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.ResponseBody;
/* loaded from: classes8.dex */
public class StickyNotesMediaDownloader {
    private static final int MEMORY_BUFFER_SIZE = 1024;
    private static final String METRICS_DOMAIN = "hho";
    private static final String METRICS_THUMBNAIL_OPERATION = "DownloadVideoThumbnail";
    private static final String METRICS_VIDEO_OPERATION = "DownloadVideo";
    private static final String TAG = "StickyNotesMediaDownloader";
    private static final String THUMBNAIL_ENDPOINT_FORMAT = "api/memories/videothumbnails/%s";
    private static final String VIDEO_ENDPOINT_FORMAT = "api/memories/videos/%s";
    private final CoralService coralService;
    private final FileManager fileManager;
    private final HHOMetricsService metrics;
    private final String tempFilesDirectory;

    public StickyNotesMediaDownloader(@Nonnull CoralService coralService, @Nonnull String str, @Nonnull FileManager fileManager, @Nonnull HHOMetricsService hHOMetricsService) {
        this.coralService = coralService;
        this.tempFilesDirectory = str;
        this.fileManager = fileManager;
        this.metrics = hHOMetricsService;
        fileManager.deleteDirectory(str);
        fileManager.createDirectoryIfNeeded(str);
    }

    @Nonnull
    private Single<String> downloadFileContent(@Nonnull final String str, @Nonnull final String str2, @Nullable final Predicate<Long> predicate) {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaDownloader$Y_1xAHYWF6V6zXa2-6-z-vz9mPY
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return StickyNotesMediaDownloader.this.lambda$downloadFileContent$0$StickyNotesMediaDownloader(str, str2, predicate);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.computation());
    }

    @Nonnull
    public Single<String> downloadVideo(@Nonnull String str, @Nullable Predicate<Long> predicate) {
        this.metrics.recordOperationalEvent(HHOMetricsConstants.Event.GET_VIDEO, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.DOWNLOADER);
        return downloadFileContent(String.format(VIDEO_ENDPOINT_FORMAT, str), METRICS_VIDEO_OPERATION, predicate);
    }

    @Nonnull
    public Single<String> downloadVideoThumbnail(@Nonnull String str) {
        this.metrics.recordOperationalEvent(HHOMetricsConstants.Event.GET_THUMBNAIL, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.DOWNLOADER);
        return downloadFileContent(String.format(THUMBNAIL_ENDPOINT_FORMAT, str), METRICS_THUMBNAIL_OPERATION, null);
    }

    public /* synthetic */ String lambda$downloadFileContent$0$StickyNotesMediaDownloader(String str, String str2, Predicate predicate) throws Exception {
        Throwable th;
        OutputStream outputStream;
        ResponseBody responseBody;
        GeneratedOutlineSupport1.outline158("Start media download: ", str);
        CoralService.Request request = this.coralService.request(str);
        request.getMetadata().mo6800setDomain(METRICS_DOMAIN);
        request.getMetadata().mo6804setOperationName(str2);
        File file = new File(this.tempFilesDirectory + "/" + UUID.randomUUID().toString());
        OutputStream outputStream2 = null;
        try {
            responseBody = request.get().asRaw().execute().body();
            if (predicate != null) {
                try {
                    if (!predicate.apply(Long.valueOf(responseBody.contentLength()))) {
                        Log.e(TAG, "Download canceled: " + str);
                        throw new DownloadCanceledException();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = null;
                    IOUtils.closeQuietly(outputStream2);
                    IOUtils.closeQuietly(outputStream);
                    IOUtils.closeQuietly(responseBody);
                    throw th;
                }
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(responseBody.byteStream());
            try {
                OutputStream createFileOutputStream = this.fileManager.createFileOutputStream(file.getAbsolutePath());
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        try {
                            int read = bufferedInputStream.read(bArr, 0, 1024);
                            if (read != -1) {
                                try {
                                    createFileOutputStream.write(bArr, 0, read);
                                } catch (IOException e) {
                                    Log.e(TAG, "Write to file failed: ", e);
                                    throw new StorageException(e);
                                }
                            } else {
                                IOUtils.closeQuietly(bufferedInputStream);
                                IOUtils.closeQuietly(createFileOutputStream);
                                IOUtils.closeQuietly(responseBody);
                                return file.getAbsolutePath();
                            }
                        } catch (IOException e2) {
                            Log.e(TAG, "Read from stream failed: ", e2);
                            throw new NetworkException(e2);
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream2 = createFileOutputStream;
                    OutputStream outputStream3 = outputStream2;
                    outputStream2 = bufferedInputStream;
                    outputStream = outputStream3;
                    IOUtils.closeQuietly(outputStream2);
                    IOUtils.closeQuietly(outputStream);
                    IOUtils.closeQuietly(responseBody);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
            outputStream = null;
            responseBody = null;
        }
    }
}
