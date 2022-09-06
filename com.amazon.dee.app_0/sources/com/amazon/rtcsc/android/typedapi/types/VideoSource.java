package com.amazon.rtcsc.android.typedapi.types;

import com.amazon.rtcsc.android.typedapi.constants.VideoSourceType;
import lombok.NonNull;
/* loaded from: classes13.dex */
public class VideoSource {
    private String sourceType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VideoSource(@NonNull VideoSourceType videoSourceType) {
        if (videoSourceType != null) {
            this.sourceType = videoSourceType.name();
            return;
        }
        throw new NullPointerException("videoSourceType");
    }

    public String getSourceType() {
        return this.sourceType;
    }
}
