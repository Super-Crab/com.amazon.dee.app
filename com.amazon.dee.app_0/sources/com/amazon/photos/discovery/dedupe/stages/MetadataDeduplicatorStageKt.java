package com.amazon.photos.discovery.dedupe.stages;

import kotlin.Metadata;
/* compiled from: MetadataDeduplicatorStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"FILTER_DATE_RANGE", "", "FILTER_START", "INVALID_PARAM_ERROR_CODE", "", "KEEP_ALIVE_TIME", "", "TAG", "AndroidPhotosDiscovery_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MetadataDeduplicatorStageKt {
    private static final String FILTER_DATE_RANGE = " AND contentProperties.contentDate:[\"%s\" TO \"%s\"]\n";
    private static final String FILTER_START = "status:AVAILABLE AND kind:FILE";
    private static final int INVALID_PARAM_ERROR_CODE = 400;
    private static final long KEEP_ALIVE_TIME = 10;
    private static final String TAG = "MetadataDeduplicatorStage";
}
