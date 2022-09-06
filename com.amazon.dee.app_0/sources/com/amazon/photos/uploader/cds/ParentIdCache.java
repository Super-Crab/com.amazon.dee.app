package com.amazon.photos.uploader.cds;

import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ParentIdCache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H&¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/cds/ParentIdCache;", "", MetricsConstants.Method.CACHE_CLEAR, "", "destroy", MetricsConstants.Method.CACHE_GET, "", RouteParameter.PATH, MetricsConstants.Method.CACHE_PUT, AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface ParentIdCache {
    void clear();

    void destroy();

    @Nullable
    String get(@NotNull String str);

    void put(@NotNull String str, @NotNull String str2);
}
