package com.bugsnag.android;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.FileStore;
import java.io.File;
import java.util.Comparator;
import java.util.Locale;
import java.util.UUID;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SessionStore extends FileStore<Session> {
    static final Comparator<File> SESSION_COMPARATOR = new Comparator<File>() { // from class: com.bugsnag.android.SessionStore.1
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            if (file == null && file2 == null) {
                return 0;
            }
            if (file == null) {
                return 1;
            }
            if (file2 != null) {
                return file.getName().compareTo(file2.getName());
            }
            return -1;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public SessionStore(@NonNull Configuration configuration, @NonNull Context context, @Nullable FileStore.Delegate delegate) {
        super(configuration, context, configuration.getSessionStore(), 128, SESSION_COMPARATOR, delegate);
    }

    @Override // com.bugsnag.android.FileStore
    @NonNull
    String getFilename(Object obj) {
        return String.format(Locale.US, "%s%s%d.json", this.storeDirectory, UUID.randomUUID().toString(), Long.valueOf(System.currentTimeMillis()));
    }
}
