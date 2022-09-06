package com.facebook.react.modules.vibration;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import com.facebook.fbreact.specs.NativeVibrationSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = VibrationModule.NAME)
@SuppressLint({"MissingPermission"})
/* loaded from: classes2.dex */
public class VibrationModule extends NativeVibrationSpec {
    public static final String NAME = "Vibration";

    public VibrationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.fbreact.specs.NativeVibrationSpec
    public void cancel() {
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeVibrationSpec
    public void vibrate(double d) {
        int i = (int) d;
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator == null) {
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        vibrator.vibrate(VibrationEffect.createOneShot(i, -1));
    }

    @Override // com.facebook.fbreact.specs.NativeVibrationSpec
    public void vibrateByPattern(ReadableArray readableArray, double d) {
        int i = (int) d;
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator == null) {
            return;
        }
        long[] jArr = new long[readableArray.size()];
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            jArr[i2] = readableArray.getInt(i2);
        }
        int i3 = Build.VERSION.SDK_INT;
        vibrator.vibrate(VibrationEffect.createWaveform(jArr, i));
    }
}
