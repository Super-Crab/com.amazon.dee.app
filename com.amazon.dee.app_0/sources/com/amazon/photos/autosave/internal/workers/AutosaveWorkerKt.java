package com.amazon.photos.autosave.internal.workers;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"AUTOSAVE_CHAIN_PHOTOS_UNIQUE_NAME", "", "AUTOSAVE_CHAIN_VIDEOS_UNIQUE_NAME", "BATCH_SIZE", "", AutosaveWorkerKt.DEDUPED_ITEM_IDS, AutosaveWorkerKt.HANDLE_MEDIA_TYPE, "HASHED_DIRECTED_ID_KEY", "AndroidPhotosAutosave_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveWorkerKt {
    @NotNull
    public static final String AUTOSAVE_CHAIN_PHOTOS_UNIQUE_NAME = "AndroidPhotosAutosave_AUTOSAVE_CHAIN";
    @NotNull
    public static final String AUTOSAVE_CHAIN_VIDEOS_UNIQUE_NAME = "AndroidVideosAutosave_AUTOSAVE_CHAIN";
    public static final int BATCH_SIZE = 200;
    @NotNull
    public static final String DEDUPED_ITEM_IDS = "DEDUPED_ITEM_IDS";
    @NotNull
    public static final String HANDLE_MEDIA_TYPE = "HANDLE_MEDIA_TYPE";
    @NotNull
    public static final String HASHED_DIRECTED_ID_KEY = "HASHED_DIRECTED_ID_KEY";
}
