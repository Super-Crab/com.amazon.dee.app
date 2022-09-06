package com.amazon.alexa.voiceui.driveMode;

import com.amazon.alexa.voice.ui.onedesign.util.Properties;
import com.amazon.alexa.voice.ui.util.BooleanProperty;
import io.reactivex.rxjava3.core.Observable;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class DriveModeStateProvider {
    private BooleanProperty driveModeEnabled = new BooleanProperty(false);
    private final Observable<Boolean> driveModeEnabledObservable = Properties.toObservable(this.driveModeEnabled);

    public boolean getDriveModeEnabled() {
        return this.driveModeEnabled.get();
    }

    public Observable<Boolean> onDriveModeEnabled() {
        return this.driveModeEnabledObservable;
    }

    public void setDriveModeEnabled(boolean z) {
        this.driveModeEnabled.set(z);
    }
}
