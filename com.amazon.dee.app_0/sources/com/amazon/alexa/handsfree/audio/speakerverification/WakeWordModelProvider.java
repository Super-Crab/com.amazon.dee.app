package com.amazon.alexa.handsfree.audio.speakerverification;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.utils.PackageNameProvider;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
/* loaded from: classes8.dex */
class WakeWordModelProvider {
    private static final String TAG = "WakeWordModelProvider";
    private final WakeWordModelContentProviderHelper mContentProviderHelper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WakeWordModelProvider(@NonNull final Context context) {
        this(new WakeWordModelContentProviderHelper(context.getContentResolver(), new PackageNameProvider() { // from class: com.amazon.alexa.handsfree.audio.speakerverification.WakeWordModelProvider.1
            @Override // com.amazon.alexa.utils.PackageNameProvider
            public String getPackageName() {
                return context.getPackageName();
            }
        }));
    }

    @Nullable
    private byte[] readModelFromContentProvider() {
        try {
            InputStream openWakeWordModelInputStream = this.mContentProviderHelper.openWakeWordModelInputStream();
            if (openWakeWordModelInputStream == null) {
                Log.e(TAG, "got null InputStream from contentResolver");
                if (openWakeWordModelInputStream != null) {
                    openWakeWordModelInputStream.close();
                }
                return null;
            }
            byte[] byteArray = IOUtils.toByteArray(openWakeWordModelInputStream);
            openWakeWordModelInputStream.close();
            return byteArray;
        } catch (IOException e) {
            Log.e(TAG, "error reading the model from content provider", e, new Object[0]);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public byte[] getModelAsBytes() {
        return readModelFromContentProvider();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getModelID() {
        return this.mContentProviderHelper.readExistingModelArtifactId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getModelLocale() {
        return this.mContentProviderHelper.readLastUsedLocale();
    }

    @VisibleForTesting
    WakeWordModelProvider(@NonNull WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper) {
        this.mContentProviderHelper = wakeWordModelContentProviderHelper;
    }
}
