package com.amazon.deecomms.notifications.util;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public class TextMessageTranslator {
    private final Context context;

    public TextMessageTranslator(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    public String translate(@NonNull String str, @NonNull String str2) {
        int identifier = this.context.getResources().getIdentifier(str, "string", this.context.getPackageName());
        return identifier == 0 ? str2 : this.context.getResources().getString(identifier);
    }
}
