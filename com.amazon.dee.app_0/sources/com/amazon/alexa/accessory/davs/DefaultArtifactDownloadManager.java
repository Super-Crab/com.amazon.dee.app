package com.amazon.alexa.accessory.davs;

import android.content.Context;
import com.amazon.alexa.accessory.davs.ArtifactDownloadManager;
import com.amazon.alexa.accessory.davs.DeviceArtifactContract;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.OutputStreamSink;
import com.amazon.alexa.accessory.io.Sink;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes.dex */
public class DefaultArtifactDownloadManager implements ArtifactDownloadManager {
    private static final String TAG = "DefaultArtifactDownloadManager:";
    private final File directory;

    /* loaded from: classes.dex */
    private final class StreamableArtifactImpl implements ArtifactDownloadManager.StreamableArtifact {
        private final DeviceArtifactContract.ArtifactPackage artifactPackage;
        private boolean finished;
        private Sink sink;

        @Override // com.amazon.alexa.accessory.davs.ArtifactDownloadManager.StreamableArtifact
        public synchronized void cancel() {
            if (this.finished) {
                return;
            }
            this.finished = true;
            IOUtils.closeQuietly(this.sink);
            this.artifactPackage.getFile().delete();
        }

        @Override // com.amazon.alexa.accessory.davs.ArtifactDownloadManager.StreamableArtifact
        public DeviceArtifactContract.ArtifactPackage complete() throws IOException {
            Sink sink = this.sink;
            if (sink != null) {
                if (!this.finished) {
                    this.finished = true;
                    sink.flush();
                    this.sink.close();
                    return this.artifactPackage;
                }
                throw new IOException("Cannot complete, was already complete via cancel or complete.");
            }
            throw new IllegalStateException("Cannot complete package, sink() was not called!");
        }

        @Override // com.amazon.alexa.accessory.davs.ArtifactDownloadManager.StreamableArtifact
        public synchronized Sink getSink() throws IOException {
            if (this.sink == null) {
                if (!DefaultArtifactDownloadManager.this.directory.exists() && !DefaultArtifactDownloadManager.this.directory.mkdirs()) {
                    throw new FileNotFoundException("Failed to create artifact directory");
                }
                this.sink = new OutputStreamSink(new BufferedOutputStream(new FileOutputStream(this.artifactPackage.getFile())));
            }
            return this.sink;
        }

        private StreamableArtifactImpl(DeviceArtifactsResponse deviceArtifactsResponse, DeviceArtifactContract.ArtifactPackageIdentifier artifactPackageIdentifier) {
            this.artifactPackage = DeviceArtifactContract.ArtifactPackage.create(DefaultArtifactDownloadManager.this.directory, artifactPackageIdentifier, deviceArtifactsResponse.getDavsArtifactSignatures().get(0));
        }
    }

    public DefaultArtifactDownloadManager(Context context) {
        Preconditions.notNull(context, "context");
        this.directory = new File(context.getCacheDir(), "accessoryArtifacts");
    }

    private String getChecksum(File file) throws IOException {
        return new MD5ChecksumCalculator().getMD5Signature(file);
    }

    @Override // com.amazon.alexa.accessory.davs.ArtifactDownloadManager
    public Maybe<DeviceArtifactContract.ArtifactPackage> getArtifactPackage(final DeviceArtifactsResponse deviceArtifactsResponse, final DeviceArtifactContract.ArtifactPackageIdentifier artifactPackageIdentifier) {
        return Maybe.defer(new Supplier() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultArtifactDownloadManager$P0VPhh955P36YtxHBcLe4drgkOw
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return DefaultArtifactDownloadManager.this.lambda$getArtifactPackage$2$DefaultArtifactDownloadManager(artifactPackageIdentifier, deviceArtifactsResponse);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.accessory.davs.ArtifactDownloadManager
    public Single<ArtifactDownloadManager.StreamableArtifact> getStreamableArtifact(DeviceArtifactsResponse deviceArtifactsResponse, DeviceArtifactContract.ArtifactPackageIdentifier artifactPackageIdentifier) {
        Preconditions.notNull(deviceArtifactsResponse, "response");
        Preconditions.notNull(artifactPackageIdentifier, "artifactPackageIdentifier");
        Logger.d("%s Getting streamable artifact for identifier: %s", TAG, artifactPackageIdentifier);
        return Single.just(new StreamableArtifactImpl(deviceArtifactsResponse, artifactPackageIdentifier));
    }

    public /* synthetic */ MaybeSource lambda$getArtifactPackage$2$DefaultArtifactDownloadManager(final DeviceArtifactContract.ArtifactPackageIdentifier artifactPackageIdentifier, final DeviceArtifactsResponse deviceArtifactsResponse) throws Throwable {
        if (!this.directory.exists()) {
            Logger.e("%s Artifacts directory does not exist in cache", TAG);
            return Maybe.empty();
        }
        return Observable.fromIterable(Arrays.asList(this.directory.listFiles())).filter(new Predicate() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultArtifactDownloadManager$5AezpLB8WtTRRsEf_XesCGPQLN8
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return DefaultArtifactDownloadManager.this.lambda$null$0$DefaultArtifactDownloadManager(artifactPackageIdentifier, deviceArtifactsResponse, (File) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultArtifactDownloadManager$UALxKrekqBM-rZ3QG9GDJlee1t0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                DeviceArtifactContract.ArtifactPackageIdentifier artifactPackageIdentifier2 = DeviceArtifactContract.ArtifactPackageIdentifier.this;
                DeviceArtifactsResponse deviceArtifactsResponse2 = deviceArtifactsResponse;
                return Logger.d("%s artifact already exist, fileName: %s", DefaultArtifactDownloadManager.TAG, ((File) obj).getName());
            }
        }).firstElement().onErrorResumeWith(Maybe.empty());
    }

    public /* synthetic */ boolean lambda$null$0$DefaultArtifactDownloadManager(DeviceArtifactContract.ArtifactPackageIdentifier artifactPackageIdentifier, DeviceArtifactsResponse deviceArtifactsResponse, File file) throws Throwable {
        return file.getName().equals(artifactPackageIdentifier.getArtifactIdentifier()) || deviceArtifactsResponse.getMd5Value().equals(getChecksum(file));
    }
}
