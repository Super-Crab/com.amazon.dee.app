package com.amazon.dee.app.elements;

import android.content.Intent;
import android.view.KeyEvent;
/* loaded from: classes12.dex */
public interface ReactHostLifecycle {
    void onActivityResult(int i, int i2, Intent intent);

    void onDestroy();

    boolean onKeyUp(int i, KeyEvent keyEvent);

    void onPause();

    void onResume();
}
