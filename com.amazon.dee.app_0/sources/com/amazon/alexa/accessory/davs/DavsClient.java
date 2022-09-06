package com.amazon.alexa.accessory.davs;

import com.amazon.alexa.accessory.davs.DeviceArtifactContract;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes.dex */
public interface DavsClient {
    Maybe<DeviceArtifactContract.ArtifactPackage> downloadArtifact(String str, DeviceArtifactsResponse deviceArtifactsResponse, String str2);

    Single<DeviceArtifactsResponse> fetchDavsManifest(DeviceArtifactsRequest deviceArtifactsRequest, String str, String str2);

    Single<DavsI18nConfig> getDavsI18nConfig(DeviceArtifactsRequest deviceArtifactsRequest, String str, String str2);
}
