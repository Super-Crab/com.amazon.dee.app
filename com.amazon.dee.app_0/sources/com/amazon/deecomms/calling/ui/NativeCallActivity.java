package com.amazon.deecomms.calling.ui;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import com.amazon.alexa.accessorykit.ApplicationLifecycleObserverEventEmitter;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.phonecallcontroller.AcceptNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.EndNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.NoCallPermissionHandler;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.TimeoutHelper;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class NativeCallActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, NativeCallActivity.class);
    @Inject
    AcceptNativeCallHandler acceptNativeCallHandler;
    @Inject
    EndNativeCallHandler endNativeCallHandler;
    @Inject
    MakeNativeCallHandler makeNativeCallHandler;
    private NativeCallPresenter nativeCallPresenter;
    @Inject
    NoCallPermissionHandler noCallPermissionHandler;
    @Inject
    TimeoutHelper timeoutHelper;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        LOG.i("onCreate");
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        LOG.i("onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        LOG.i("onPause");
        super.onPause();
    }

    @Override // android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        LOG.i("onRequestPermissionsResult");
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 335) {
            this.nativeCallPresenter.onPermissionResult();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        LOG.i("onResume");
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStart() {
        LOG.i(ApplicationLifecycleObserverEventEmitter.LIFECYCLE_EVENT_START);
        super.onStart();
        this.nativeCallPresenter = new NativeCallPresenter(this, this.makeNativeCallHandler, this.acceptNativeCallHandler, this.endNativeCallHandler, this.noCallPermissionHandler, this.timeoutHelper);
        this.nativeCallPresenter.showCallingPermissions();
    }

    @Override // android.app.Activity
    public void onStop() {
        LOG.i(ApplicationLifecycleObserverEventEmitter.LIFECYCLE_EVENT_STOP);
        super.onStop();
        finish();
    }
}
