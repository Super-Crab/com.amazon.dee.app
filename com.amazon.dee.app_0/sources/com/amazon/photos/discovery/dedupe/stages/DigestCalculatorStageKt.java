package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.alexa.voice.ui.onedesign.util.image.ImageType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
/* compiled from: DigestCalculatorStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"EXTENSION_JPEG", "", "", "KEEP_ALIVE_TIME", "", "MAX_THREAD_CALCULATE_DIGEST", "", "TAG", "AndroidPhotosDiscovery_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DigestCalculatorStageKt {
    private static final Set<String> EXTENSION_JPEG;
    private static final long KEEP_ALIVE_TIME = 10;
    private static final int MAX_THREAD_CALCULATE_DIGEST = 2;
    private static final String TAG = "DigestCalculatorStage";

    static {
        Set<String> of;
        of = SetsKt__SetsKt.setOf((Object[]) new String[]{"jpeg", ImageType.JPG});
        EXTENSION_JPEG = of;
    }
}
