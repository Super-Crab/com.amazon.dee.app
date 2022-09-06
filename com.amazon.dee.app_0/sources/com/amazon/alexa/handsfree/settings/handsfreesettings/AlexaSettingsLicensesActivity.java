package com.amazon.alexa.handsfree.settings.handsfreesettings;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.settings.R;
/* loaded from: classes8.dex */
public class AlexaSettingsLicensesActivity extends Activity {
    private Initializer mInitializer;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mInitializer = InitializerProvider.getInitializer();
        this.mInitializer.initialize(this);
        setContentView(R.layout.settings_licenses_activity);
        setActionBar((Toolbar) findViewById(R.id.settings_toolbar));
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
}
