package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes3.dex */
interface AssetsLoader {
    @NonNull
    List<String> getSubfolderNames(@NonNull String str);

    @NonNull
    String loadAsset(@NonNull String str);

    @NonNull
    byte[] loadAssetBlob(@NonNull String str);
}
