package com.android.test.runner;

import android.os.Bundle;
import android.test.InstrumentationTestRunner;
import androidx.multidex.MultiDex;
/* loaded from: classes.dex */
public class MultiDexTestRunner extends InstrumentationTestRunner {
    public void onCreate(Bundle bundle) {
        MultiDex.installInstrumentation(getContext(), getTargetContext());
        super.onCreate(bundle);
    }
}
