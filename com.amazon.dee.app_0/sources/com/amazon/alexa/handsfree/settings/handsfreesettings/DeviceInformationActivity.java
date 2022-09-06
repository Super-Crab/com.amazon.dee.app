package com.amazon.alexa.handsfree.settings.handsfreesettings;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.settings.R;
import com.amazon.alexa.handsfree.settings.SettingsModule;
/* loaded from: classes8.dex */
public class DeviceInformationActivity extends Activity {
    private Initializer mInitializer;

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mInitializer = InitializerProvider.getInitializer();
        this.mInitializer.initialize(this);
        setContentView(R.layout.activity_device_information);
        setActionBar((Toolbar) findViewById(R.id.settings_toolbar));
        getActionBar().setDisplayHomeAsUpEnabled(true);
        ((TextView) findViewById(R.id.device)).setText(getString(R.string.alexa_settings_activity_information_device, new Object[]{Build.MODEL}));
        ((TextView) findViewById(R.id.os)).setText(getString(R.string.alexa_settings_activity_information_os, new Object[]{Build.VERSION.RELEASE}));
        ((TextView) findViewById(R.id.device_id)).setText(getString(R.string.alexa_settings_activity_information_device_id, new Object[]{SettingsModule.INSTANCE.getAnonymousDeviceIdProvider().getAnonymousDeviceId()}));
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
