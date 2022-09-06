package com.amazon.alexa.voice.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;
/* loaded from: classes11.dex */
public final class CreateShortcutActivity extends AppCompatActivity {
    private static final String ACTION_LAUNCH_FROM_SHORTCUT = "com.amazon.alexa.action.WIDGET_SHORTCUT";

    private void createShortCut() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(getPackageName(), UiComponents.ALEXA_LAUNCH_COMPONENT_NAME));
        intent.setAction("com.amazon.alexa.action.WIDGET_SHORTCUT");
        intent.addFlags(268435456);
        Parcelable fromContext = Intent.ShortcutIconResource.fromContext(this, com.amazon.alexa.voice.R.drawable.voice_ingress);
        Intent intent2 = new Intent();
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.NAME", getString(com.amazon.alexa.voice.R.string.voice_ui_od_ingress));
        intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", fromContext);
        setResult(-1, intent2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        createShortCut();
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        createShortCut();
    }
}
