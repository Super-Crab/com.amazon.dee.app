package com.amazon.alexa.accessory.notificationpublisher.urldownloader;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class DownloadResponseHandler {
    public static final int ANNOUNCEMENT_TYPE = 1;
    public static final int CONTENT_MERGED_TYPE = 4;
    public static final int CONTENT_PREFIX_TYPE = 3;
    public static final int CONTENT_TYPE = 2;
    public static final int READ_BACK_TYPE = 5;
    public static final ImmutableMap<Integer, String> TYPE_TO_NAME_MAP = new ImmutableMap.Builder().mo7828put(1, "Announcement").mo7828put(2, "Content").mo7828put(3, "ContentPrefix").mo7828put(4, "ContentMerged").mo7828put(5, "ReadBack").mo7826build();

    public abstract void handleDownloadResponse(boolean z, String str, int i, boolean z2, Map<String, Object> map);
}
