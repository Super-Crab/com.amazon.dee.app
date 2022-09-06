package amazon.speech.util;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
/* loaded from: classes.dex */
public class ContentResolverWrapper {
    private final ContentResolver mResolver;

    public ContentResolverWrapper(ContentResolver contentResolver) {
        this.mResolver = contentResolver;
    }

    public ContentResolver getResolver() {
        return this.mResolver;
    }

    public void registerContentObserver(Uri uri, boolean z, ContentObserver contentObserver) {
        this.mResolver.registerContentObserver(uri, z, contentObserver);
    }
}
