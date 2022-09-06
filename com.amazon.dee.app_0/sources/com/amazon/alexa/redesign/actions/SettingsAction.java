package com.amazon.alexa.redesign.actions;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.Map;
/* loaded from: classes10.dex */
public class SettingsAction extends Action {
    private final Context context;
    private final String url;

    public SettingsAction(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Context context) {
        super(ActionFactory.SETTINGS_ACTION, str, str2);
        if (str != null) {
            this.url = str3;
            this.context = context;
            return;
        }
        throw new IllegalArgumentException("SettingsAction: metaActionType must not be null");
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute() {
        routeToAndroidSettings();
    }

    @VisibleForTesting
    void routeToAndroidSettings() {
        Intent intent = new Intent(this.url);
        if (ActionUtils.isIntentSafe(intent, this.context)) {
            intent.setFlags(268435456);
            this.context.startActivity(intent);
        }
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute(Map<String, Object> map) {
        routeToAndroidSettings();
    }
}
