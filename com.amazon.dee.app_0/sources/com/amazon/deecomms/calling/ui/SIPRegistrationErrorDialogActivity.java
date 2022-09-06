package com.amazon.deecomms.calling.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public class SIPRegistrationErrorDialogActivity extends AppCompatActivity {
    public /* synthetic */ void lambda$onCreate$0$SIPRegistrationErrorDialogActivity(DialogInterface dialogInterface, int i) {
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(Constants.CALL_ENDED_BECAUSE_OF_ERROR));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = R.string.generic_error_title;
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlexaCustomDialogStyle)).setTitle(i).setMessage(getString(R.string.generic_error_msg)).setPositiveButton(R.string.dialog_ok_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$SIPRegistrationErrorDialogActivity$PICNDX2i699AP-x98koE61A-9do
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                SIPRegistrationErrorDialogActivity.this.lambda$onCreate$0$SIPRegistrationErrorDialogActivity(dialogInterface, i2);
            }
        }).show().setCanceledOnTouchOutside(false);
    }
}
