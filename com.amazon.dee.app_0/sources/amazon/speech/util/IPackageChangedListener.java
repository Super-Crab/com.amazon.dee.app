package amazon.speech.util;

import android.content.Intent;
/* loaded from: classes.dex */
public interface IPackageChangedListener {
    void onPackageAdded(Intent intent);

    void onPackageRemoved(Intent intent);
}
