package com.amazon.alexa.photos;

import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$CdsRequestHelperV2$29WsGlU9p_BmlB9pTacjKSYprD0  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$CdsRequestHelperV2$29WsGlU9p_BmlB9pTacjKSYprD0 implements MetricName {
    public static final /* synthetic */ $$Lambda$CdsRequestHelperV2$29WsGlU9p_BmlB9pTacjKSYprD0 INSTANCE = new $$Lambda$CdsRequestHelperV2$29WsGlU9p_BmlB9pTacjKSYprD0();

    private /* synthetic */ $$Lambda$CdsRequestHelperV2$29WsGlU9p_BmlB9pTacjKSYprD0() {
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    public final String getEventName() {
        return PhotosMetricsConstants.ALBUM_MISSING_FOLDER_PROPERTY;
    }
}
