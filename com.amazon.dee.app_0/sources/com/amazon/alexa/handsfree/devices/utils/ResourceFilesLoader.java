package com.amazon.alexa.handsfree.devices.utils;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public class ResourceFilesLoader {
    @NonNull
    public String[] getStringArrayResource(@NonNull Context context, @NonNull int i) {
        return context.getResources().getStringArray(i);
    }

    @NonNull
    public String getStringResource(@NonNull Context context, @NonNull int i) {
        return context.getResources().getString(i);
    }
}
