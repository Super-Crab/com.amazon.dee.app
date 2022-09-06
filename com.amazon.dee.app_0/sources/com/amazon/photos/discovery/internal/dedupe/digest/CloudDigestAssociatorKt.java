package com.amazon.photos.discovery.internal.dedupe.digest;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: CloudDigestAssociator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"BATCH_SIZE", "", "COMPONENT", "", "MD5_FILTER_PREFIX", "TAG", "AndroidPhotosDiscovery_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CloudDigestAssociatorKt {
    private static final int BATCH_SIZE = 10;
    private static final String COMPONENT = "CloudDigestAssociator";
    @NotNull
    public static final String MD5_FILTER_PREFIX = "contentProperties.md5:";
    private static final String TAG = "CloudDigestAssociator";
}
