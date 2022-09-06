package com.amazon.alexa.smarthomecameras.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.dependencies.components.SmartAlertEventComponent;
import com.amazon.alexa.smarthomecameras.dependencies.components.SmartAlertEventComponentProvider;
import com.amazon.alexa.smarthomecameras.dependencies.components.SmartAlertEventViewScope;
import com.amazon.alexa.smarthomecameras.dependencies.modules.SmartAlertEventModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.SmartAlertEventViewModule;
import dagger.Component;
import javax.inject.Inject;
/* loaded from: classes10.dex */
public class SmartAlertEventActivity extends Activity {
    public static final String EXTRA_IMAGE_URL = "imageUrl";
    private static final String TAG = SmartAlertEventActivity.class.getSimpleName();
    private String imageUrl;
    @Inject
    RoutingService routingService;
    @Inject
    SmartAlertEventView smartAlertEventView;

    /* JADX INFO: Access modifiers changed from: package-private */
    @SmartAlertEventViewScope
    @Component(dependencies = {SmartAlertEventComponent.class}, modules = {SmartAlertEventModule.class, SmartAlertEventViewModule.class})
    /* loaded from: classes10.dex */
    public interface Injector {
        void inject(SmartAlertEventActivity smartAlertEventActivity);
    }

    @VisibleForTesting
    void injectDependencies() {
        SmartAlertEventComponent smartAlertEventComponent = SmartAlertEventComponentProvider.getSmartAlertEventComponent();
        if (smartAlertEventComponent == null) {
            Log.e(TAG, "Cannot start activity with a null smart alert event component");
            finish();
            return;
        }
        DaggerSmartAlertEventActivity_Injector.builder().smartAlertEventComponent(smartAlertEventComponent).smartAlertEventModule(new SmartAlertEventModule(this.imageUrl)).smartAlertEventViewModule(new SmartAlertEventViewModule(this)).build().inject(this);
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.imageUrl = getIntent().getStringExtra("imageUrl");
        if (this.imageUrl == null) {
            Log.e(TAG, "imageUrl cannot be null");
            finish();
        }
        injectDependencies();
        getWindow().getDecorView().setSystemUiVisibility(2822);
        this.smartAlertEventView.initialize();
        setContentView(this.smartAlertEventView);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.routingService.navigateBackward();
    }
}
