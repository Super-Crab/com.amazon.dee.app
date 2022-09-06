package com.amazon.alexa;

import java.io.File;
import java.io.FileFilter;
/* compiled from: OfflinePromptsArtifactManager.java */
/* loaded from: classes.dex */
public class anr implements FileFilter {
    public final /* synthetic */ String zZm;

    public anr(kHl khl, String str) {
        this.zZm = str;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return file.isDirectory() && !file.getName().equals(this.zZm);
    }
}
