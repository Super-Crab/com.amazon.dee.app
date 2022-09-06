package com.facebook.imagepipeline.cache;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.imagepipeline.cache.MemoryCache;
/* loaded from: classes2.dex */
public class NativeMemoryCacheTrimStrategy implements MemoryCache.CacheTrimStrategy {
    private static final String TAG = "NativeMemoryCacheTrimStrategy";

    /* renamed from: com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$memory$MemoryTrimType = new int[MemoryTrimType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnCloseToDalvikHeapLimit.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnAppBackgrounded.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemMemoryCriticallyLowWhileAppInForeground.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache.CacheTrimStrategy
    public double getTrimRatio(MemoryTrimType trimType) {
        int ordinal = trimType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1 || ordinal == 2 || ordinal == 3 || ordinal == 4) {
                return 1.0d;
            }
            FLog.wtf(TAG, "unknown trim type: %s", trimType);
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }
}
