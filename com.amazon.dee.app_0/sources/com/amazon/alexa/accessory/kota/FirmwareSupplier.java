package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.davs.DeviceArtifactContract;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareMetadata;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.io.IOException;
/* loaded from: classes.dex */
public interface FirmwareSupplier {

    /* loaded from: classes.dex */
    public interface StreamablePackage {
        void cancel();

        FirmwareContract.Package complete() throws IOException;

        Sink sink() throws IOException;
    }

    Single<FirmwareMetadata> getMetadata(FirmwareContract.Package r1);

    Maybe<FirmwareContract.Package> getPackage(PackageIdentifier packageIdentifier);

    Single<StreamablePackage> getStreamablePackage(PackageIdentifier packageIdentifier);

    Single<FirmwareComponentSupplier> queryComponent(FirmwareContract.Component component);

    Single<FirmwareComponentSupplier> queryDAVSArtifact(DeviceArtifactContract.ArtifactPackage artifactPackage);

    Observable<FirmwareContract.Package> queryPackage(PackageCandidateIdentifier packageCandidateIdentifier);
}
