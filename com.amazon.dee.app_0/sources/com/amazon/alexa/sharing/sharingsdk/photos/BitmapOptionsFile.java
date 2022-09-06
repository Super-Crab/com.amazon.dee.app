package com.amazon.alexa.sharing.sharingsdk.photos;

import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import java.io.File;
/* loaded from: classes10.dex */
public class BitmapOptionsFile {
    @NonNull
    private File file;
    @NonNull
    private BitmapFactory.Options options;

    public BitmapOptionsFile(@NonNull BitmapFactory.Options options, @NonNull File file) {
        this.options = options;
        this.file = file;
    }

    @NonNull
    public File getFile() {
        return this.file;
    }

    @NonNull
    public BitmapFactory.Options getOptions() {
        return this.options;
    }

    public void setFile(@NonNull File file) {
        this.file = file;
    }

    public void setOptions(@NonNull BitmapFactory.Options options) {
        this.options = options;
    }
}
