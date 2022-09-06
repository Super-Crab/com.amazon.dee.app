package com.airbnb.lottie.network;

import com.airbnb.lottie.utils.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public enum FileExtension {
    JSON(".json"),
    ZIP(".zip");
    
    public final String extension;

    FileExtension(String str) {
        this.extension = str;
    }

    public static FileExtension forFile(String str) {
        FileExtension[] values;
        for (FileExtension fileExtension : values()) {
            if (str.endsWith(fileExtension.extension)) {
                return fileExtension;
            }
        }
        Logger.warning("Unable to find correct extension for " + str);
        return JSON;
    }

    public String tempExtension() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(".temp");
        outline107.append(this.extension);
        return outline107.toString();
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.extension;
    }
}
