package com.amazon.rtcsc.capabilityagent.common.dependencies;

import android.content.Context;
import com.amazon.rtcsc.capabilityagent.common.util.RtcscLogger;
/* loaded from: classes13.dex */
public final class RtcscDaggerWrapper {
    static RtcscComponent mRtcscLibraryComponent;

    private RtcscDaggerWrapper() {
    }

    public static RtcscComponent getComponent() {
        if (mRtcscLibraryComponent == null) {
            RtcscLogger.i("RtcscDaggerWrapper", "getComponent -- mRtcscLibraryComponent is NULL. ", new Object[0]);
        }
        return mRtcscLibraryComponent;
    }

    public static RtcscComponent initialize(Context context) {
        RtcscLogger.i("RtcscDaggerWrapper", "initialize -- Initializing the Library Components ", new Object[0]);
        mRtcscLibraryComponent = DaggerRtcscComponent.builder().applicationModule(new ApplicationModule(context)).build();
        return mRtcscLibraryComponent;
    }
}
