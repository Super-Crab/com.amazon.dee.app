package com.amazon.alexa;

import java.io.File;
import java.io.FileFilter;
/* compiled from: OfflinePromptsArtifactManager.java */
/* loaded from: classes.dex */
public class oQb implements FileFilter {
    public final /* synthetic */ String zZm;

    public oQb(kHl khl, String str) {
        this.zZm = str;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        String name = file.getName();
        return !name.equals(this.zZm + ".zip");
    }
}
