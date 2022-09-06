package com.amazon.alexa.voice.ui.tta;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Map;
/* loaded from: classes11.dex */
public interface TtaNavigator {
    void navigateInApp(@NonNull Context context, @NonNull String str, @Nullable Map<String, String> map);

    void navigateToExternalUrl(@NonNull Context context, @NonNull String str);

    void openVoiceScrim(@NonNull Context context);
}
