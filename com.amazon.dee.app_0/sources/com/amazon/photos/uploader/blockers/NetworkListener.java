package com.amazon.photos.uploader.blockers;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: NetworkListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/uploader/blockers/NetworkListener;", "", "onNetworkChange", "", "networkState", "Lcom/amazon/photos/uploader/blockers/NetworkState;", "onNetworkLoss", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface NetworkListener {
    void onNetworkChange(@NotNull NetworkState networkState);

    void onNetworkLoss();
}
