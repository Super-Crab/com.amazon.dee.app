package com.amazon.device.utils.det;
/* loaded from: classes12.dex */
public interface StatusNotifier {
    void broadcastOffloadStatus(CharSequence charSequence);

    void broadcastUploadStatus(CharSequence charSequence);

    void broadcastUploadTag(CharSequence charSequence);

    void fireNotification(CharSequence charSequence, int i);

    void updateFilesCount(int i);

    void updateProgress(int i);
}
