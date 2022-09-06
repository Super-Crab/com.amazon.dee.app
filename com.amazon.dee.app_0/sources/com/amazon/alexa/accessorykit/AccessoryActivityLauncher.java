package com.amazon.alexa.accessorykit;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
/* loaded from: classes6.dex */
public class AccessoryActivityLauncher {
    public static final int ACCESSORY_ACTIVITY_REQUEST_CODE = 18237;
    private final ReactApplicationContext mReactApplicationContext;

    /* loaded from: classes6.dex */
    private final class ActivityListener extends BaseActivityEventListener {
        private final Promise mPromise;

        @Override // com.facebook.react.bridge.BaseActivityEventListener, com.facebook.react.bridge.ActivityEventListener
        public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
            if (i == 18237) {
                AccessoryActivityLauncher.this.mReactApplicationContext.removeActivityEventListener(this);
                if (i2 != 0) {
                    this.mPromise.resolve(null);
                } else {
                    this.mPromise.reject(new Exception("Activity canceled"));
                }
            }
        }

        private ActivityListener(Promise promise) {
            this.mPromise = promise;
        }
    }

    public AccessoryActivityLauncher(ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }

    public void startActivity(Intent intent, Promise promise) {
        Intent intent2 = new Intent(intent);
        if (!this.mReactApplicationContext.hasCurrentActivity()) {
            intent2.addFlags(268435456);
            this.mReactApplicationContext.startActivity(intent);
            promise.resolve(null);
            return;
        }
        this.mReactApplicationContext.addActivityEventListener(new ActivityListener(promise));
        this.mReactApplicationContext.startActivityForResult(intent2, 18237, null);
    }
}
