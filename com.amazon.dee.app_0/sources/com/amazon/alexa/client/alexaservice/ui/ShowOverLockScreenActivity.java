package com.amazon.alexa.client.alexaservice.ui;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.C0179Pya;
import com.amazon.identity.auth.device.api.MAPAccountManager;
/* loaded from: classes6.dex */
public class ShowOverLockScreenActivity extends Activity {
    public static final String zZm = "ShowOverLockScreenActivity";
    public KeyguardManager BIo;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        this.BIo = (KeyguardManager) getSystemService("keyguard");
        if (this.BIo.isKeyguardLocked()) {
            if (Build.VERSION.SDK_INT > 26) {
                setShowWhenLocked(true);
                setTurnScreenOn(true);
                this.BIo.requestDismissKeyguard(this, null);
                return;
            }
            getWindow().addFlags(6816768);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Intent intent2 = (intent != null && "com.amazon.intent.action.SHOW_OVER_LOCK_SCREEN".equals(intent.getAction())) ? (Intent) intent.getParcelableExtra(MAPAccountManager.KEY_INTENT) : null;
        if (intent2 != null) {
            try {
                startActivity(intent2);
            } catch (ActivityNotFoundException e) {
                String str = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("Activity not found: ");
                zZm2.append(e.getMessage());
                Log.w(str, zZm2.toString());
            }
        }
        finish();
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        overridePendingTransition(0, 0);
    }
}
