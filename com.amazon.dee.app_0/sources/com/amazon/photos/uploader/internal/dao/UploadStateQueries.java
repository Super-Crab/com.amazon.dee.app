package com.amazon.photos.uploader.internal.dao;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadStateQueries.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/photos/uploader/internal/dao/UploadStateQueries;", "", "()V", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadStateQueries {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String GET_REQUESTS_FOR_STATES_QUERY = "SELECT * FROM upload_request WHERE state IN(:states) ORDER BY id DESC";
    @NotNull
    public static final String GET_REQUESTS_FOR_STATES_WITH_QUEUE = "SELECT * FROM upload_request WHERE state IN(:states) AND queue IN(:queues) ORDER BY id DESC";
    @NotNull
    public static final String GET_REQUESTS_FOR_STATE_QUERY = "SELECT * FROM upload_request WHERE state = :state ORDER BY id DESC";
    @NotNull
    public static final String GET_REQUESTS_FOR_STATE_WITH_QUEUE = "SELECT * FROM upload_request WHERE state = :state AND queue IN(:queues) ORDER BY id DESC";
    @NotNull
    public static final String PENDING_STATES = "('RUNNING', 'BLOCKED', 'ENQUEUED')";

    /* compiled from: UploadStateQueries.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/photos/uploader/internal/dao/UploadStateQueries$Companion;", "", "()V", "GET_REQUESTS_FOR_STATES_QUERY", "", "GET_REQUESTS_FOR_STATES_WITH_QUEUE", "GET_REQUESTS_FOR_STATE_QUERY", "GET_REQUESTS_FOR_STATE_WITH_QUEUE", "PENDING_STATES", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
