package com.google.android.exoplayer2.source.hls;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes2.dex */
public final class SampleQueueMappingException extends IOException {
    public SampleQueueMappingException(@Nullable String str) {
        super(GeneratedOutlineSupport1.outline75("Unable to bind a sample queue to TrackGroup with mime type ", str, "."));
    }
}
