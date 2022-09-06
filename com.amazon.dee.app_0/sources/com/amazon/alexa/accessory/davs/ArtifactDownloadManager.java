package com.amazon.alexa.accessory.davs;

import com.amazon.alexa.accessory.davs.DeviceArtifactContract;
import com.amazon.alexa.accessory.io.Sink;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import java.io.IOException;
/* loaded from: classes.dex */
public interface ArtifactDownloadManager {

    /* loaded from: classes.dex */
    public interface StreamableArtifact {
        void cancel();

        DeviceArtifactContract.ArtifactPackage complete() throws IOException;

        Sink getSink() throws IOException;
    }

    Maybe<DeviceArtifactContract.ArtifactPackage> getArtifactPackage(DeviceArtifactsResponse deviceArtifactsResponse, DeviceArtifactContract.ArtifactPackageIdentifier artifactPackageIdentifier);

    Single<StreamableArtifact> getStreamableArtifact(DeviceArtifactsResponse deviceArtifactsResponse, DeviceArtifactContract.ArtifactPackageIdentifier artifactPackageIdentifier);
}
