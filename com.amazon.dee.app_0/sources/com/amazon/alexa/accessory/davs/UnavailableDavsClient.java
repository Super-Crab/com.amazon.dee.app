package com.amazon.alexa.accessory.davs;

import com.amazon.alexa.accessory.davs.DeviceArtifactContract;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes.dex */
public class UnavailableDavsClient implements DavsClient {
    @Override // com.amazon.alexa.accessory.davs.DavsClient
    public Maybe<DeviceArtifactContract.ArtifactPackage> downloadArtifact(String str, DeviceArtifactsResponse deviceArtifactsResponse, String str2) {
        return Maybe.error(new UnsupportedOperationException("DavsClient unavailable. UnsupportedOperation downloadArtifact."));
    }

    @Override // com.amazon.alexa.accessory.davs.DavsClient
    public Single<DeviceArtifactsResponse> fetchDavsManifest(DeviceArtifactsRequest deviceArtifactsRequest, String str, String str2) {
        return Single.error(new UnsupportedOperationException("DavsClient unavailable. UnsupportedOperation fetchDavsManifest."));
    }

    @Override // com.amazon.alexa.accessory.davs.DavsClient
    public Single<DavsI18nConfig> getDavsI18nConfig(DeviceArtifactsRequest deviceArtifactsRequest, String str, String str2) {
        return Single.error(new UnsupportedOperationException("DAVSClient unavailable. UnsupportedOperation getDavsI18nConfig."));
    }
}
