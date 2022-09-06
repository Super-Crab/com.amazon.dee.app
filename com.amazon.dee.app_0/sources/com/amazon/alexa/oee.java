package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.DataDirectoryProvider;
import java.io.File;
/* compiled from: WakeWordModule.java */
/* loaded from: classes.dex */
public class oee implements DataDirectoryProvider {
    public final /* synthetic */ Context zZm;

    public oee(iPU ipu, Context context) {
        this.zZm = context;
    }

    @Override // com.amazon.alexa.utils.DataDirectoryProvider
    public File getDataDir() {
        return this.zZm.getFilesDir();
    }
}
