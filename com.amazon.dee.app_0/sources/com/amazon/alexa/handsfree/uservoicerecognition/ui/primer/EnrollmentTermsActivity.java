package com.amazon.alexa.handsfree.uservoicerecognition.ui.primer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
/* loaded from: classes8.dex */
public class EnrollmentTermsActivity extends AppCompatActivity {
    private static final String TAG = EnrollmentTermsActivity.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_enrollment);
        Log.i(TAG, "EnrollmentTermsActivity onCreate()");
        getSupportFragmentManager().beginTransaction().add(R.id.container, new EnrollmentTermsFragment()).commit();
    }
}
