package com.amazon.dee.app.services.clouddrive;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.dee.app.services.photos.PhotoService;
/* loaded from: classes12.dex */
public interface CloudDriveService extends PhotoService {

    /* loaded from: classes12.dex */
    public interface CloudDriveFileActionListener {
        void onError(Exception exc);

        void onSuccess(@NonNull String str, @Nullable String str2);
    }

    /* loaded from: classes12.dex */
    public interface InitCompletedListener {
        void onError(Exception exc);

        void onSuccess();
    }

    String getAlexaFolderId();

    String getRootNodeId();

    void init(@NonNull InitCompletedListener initCompletedListener);

    void removeSingleFile(@NonNull String str, @NonNull CloudDriveFileActionListener cloudDriveFileActionListener);

    void uploadSingleFile(@NonNull Uri uri, @NonNull CloudDriveFileActionListener cloudDriveFileActionListener);
}
