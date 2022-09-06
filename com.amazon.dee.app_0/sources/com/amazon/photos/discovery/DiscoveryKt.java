package com.amazon.photos.discovery;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Discovery.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"DISCOVERY_DESTROYED_MSG", "", "MIN_BATCH_SIZE", "", "TAG", "AndroidPhotosDiscovery_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DiscoveryKt {
    @NotNull
    public static final String DISCOVERY_DESTROYED_MSG = "Discovery instance already created for this account.";
    public static final int MIN_BATCH_SIZE = 10;
    private static final String TAG = "Discovery";
}
