package com.amazon.alexa.voiceui.lockscreen;

import android.app.Activity;
import android.app.KeyguardManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voiceui.R;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class LockscreenController {
    private static final String TAG = "LockscreenController";
    private final Activity activity;
    private final KeyguardManager keyguardManager;
    private Boolean wasScreenLocked;
    private boolean keyguardUnlockRequested = false;
    private boolean playedKeyguardUnlockSound = false;
    private boolean showOverLockscreen = false;
    private boolean lockscreenFlagsAddedToWindow = false;
    private int onStartCount = 0;
    private final BehaviorSubject<Boolean> canDisplayVoiceUiObservable = BehaviorSubject.createDefault(Boolean.valueOf(canDisplayVoiceUI()));
    private final List<LockscreenCallbacks> lockscreenCallbacks = new ArrayList();

    /* loaded from: classes11.dex */
    public interface LockscreenCallbacks {
        void onUnlockFailure();

        void onUnlockSuccess(boolean z, boolean z2);
    }

    @Inject
    public LockscreenController(KeyguardManager keyguardManager, Activity activity) {
        this.keyguardManager = keyguardManager;
        this.activity = activity;
    }

    private void bringUpKeyguard() {
        int i = Build.VERSION.SDK_INT;
        if (this.keyguardUnlockRequested) {
            return;
        }
        this.keyguardUnlockRequested = true;
        final boolean isDeviceLocked = this.keyguardManager.isDeviceLocked();
        final boolean isKeyguardLocked = this.keyguardManager.isKeyguardLocked();
        this.keyguardManager.requestDismissKeyguard(this.activity, new KeyguardManager.KeyguardDismissCallback() { // from class: com.amazon.alexa.voiceui.lockscreen.LockscreenController.1
            @Override // android.app.KeyguardManager.KeyguardDismissCallback
            public void onDismissCancelled() {
                LockscreenController.this.keyguardUnlockRequested = false;
                LockscreenController.this.canDisplayVoiceUiObservable.onNext(Boolean.valueOf(LockscreenController.this.canDisplayVoiceUI()));
                synchronized (LockscreenController.this.lockscreenCallbacks) {
                    for (LockscreenCallbacks lockscreenCallbacks : LockscreenController.this.lockscreenCallbacks) {
                        lockscreenCallbacks.onUnlockFailure();
                    }
                }
            }

            @Override // android.app.KeyguardManager.KeyguardDismissCallback
            public void onDismissError() {
                LockscreenController.this.keyguardUnlockRequested = false;
                LockscreenController.this.canDisplayVoiceUiObservable.onNext(Boolean.valueOf(LockscreenController.this.canDisplayVoiceUI()));
                synchronized (LockscreenController.this.lockscreenCallbacks) {
                    for (LockscreenCallbacks lockscreenCallbacks : LockscreenController.this.lockscreenCallbacks) {
                        lockscreenCallbacks.onUnlockFailure();
                    }
                }
            }

            @Override // android.app.KeyguardManager.KeyguardDismissCallback
            public void onDismissSucceeded() {
                LockscreenController.this.keyguardUnlockRequested = false;
                LockscreenController.this.canDisplayVoiceUiObservable.onNext(Boolean.valueOf(LockscreenController.this.canDisplayVoiceUI()));
                synchronized (LockscreenController.this.lockscreenCallbacks) {
                    for (LockscreenCallbacks lockscreenCallbacks : LockscreenController.this.lockscreenCallbacks) {
                        lockscreenCallbacks.onUnlockSuccess(isDeviceLocked, isKeyguardLocked);
                    }
                }
            }
        });
    }

    private void informUserToUnlockPhone() {
        if (this.playedKeyguardUnlockSound) {
            return;
        }
        this.playedKeyguardUnlockSound = true;
        playUnlockSound();
    }

    private void playUnlockSound() {
        MediaPlayer mediaPlayer = getMediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.amazon.alexa.voiceui.lockscreen.LockscreenController.2
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer2) {
                mediaPlayer2.release();
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.amazon.alexa.voiceui.lockscreen.LockscreenController.3
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                String str = LockscreenController.TAG;
                Log.w(str, "Unable to play unlock sound. Error code = " + i);
                return false;
            }
        });
        mediaPlayer.start();
    }

    public void addLockscreenCallbacks(LockscreenCallbacks lockscreenCallbacks) {
        synchronized (this.lockscreenCallbacks) {
            this.lockscreenCallbacks.add(lockscreenCallbacks);
        }
    }

    public boolean canDisplayVoiceUI() {
        return this.showOverLockscreen || !isOnLockScreen();
    }

    @VisibleForTesting
    MediaPlayer getMediaPlayer() {
        return MediaPlayer.create(this.activity, R.raw.unlock_device_to_make_request);
    }

    public boolean isExpectingSubsequentLifecycleCallbacks() {
        if (isOnLockScreen()) {
            if (this.lockscreenFlagsAddedToWindow && this.onStartCount == 1) {
                return true;
            }
            if (!this.lockscreenFlagsAddedToWindow) {
                int i = Build.VERSION.SDK_INT;
                if (!this.keyguardManager.isDeviceLocked()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isKeyguardSecure() {
        return this.keyguardManager.isKeyguardSecure();
    }

    public boolean isOnLockScreen() {
        return this.keyguardManager.isKeyguardLocked();
    }

    public boolean isOnSecureLockScreen() {
        return this.keyguardManager.inKeyguardRestrictedInputMode() && isOnLockScreen() && isKeyguardSecure();
    }

    public Observable<Boolean> onCanDisplayVoiceUI() {
        return this.canDisplayVoiceUiObservable.distinctUntilChanged();
    }

    public void promptUserToUnlockPhoneIfRequired() {
        if (!isOnSecureLockScreen() || this.showOverLockscreen) {
            return;
        }
        informUserToUnlockPhone();
    }

    public boolean requestPhoneUnlockIfRequired() {
        this.onStartCount++;
        if (!isOnLockScreen() || !wasScreenLocked() || this.showOverLockscreen) {
            return false;
        }
        Log.i(TAG, "Bringing up keyguard");
        bringUpKeyguard();
        return true;
    }

    public void setShouldShowOverLockscreen(boolean z) {
        this.showOverLockscreen = z;
        this.canDisplayVoiceUiObservable.onNext(Boolean.valueOf(canDisplayVoiceUI()));
    }

    public void setWasScreenLocked(boolean z) {
        this.wasScreenLocked = Boolean.valueOf(z);
    }

    public boolean showKeyguardIfDeviceLocked() {
        int i = Build.VERSION.SDK_INT;
        if (this.keyguardManager.isDeviceLocked()) {
            bringUpKeyguard();
            return true;
        }
        return false;
    }

    public void showOverLockscreenIfRequired() {
        if (!isOnLockScreen() || !wasScreenLocked() || !this.showOverLockscreen) {
            return;
        }
        this.lockscreenFlagsAddedToWindow = true;
        this.activity.getWindow().addFlags(525312);
    }

    @VisibleForTesting
    boolean wasScreenLocked() {
        Boolean bool = this.wasScreenLocked;
        return bool == null ? this.keyguardManager.isKeyguardLocked() : bool.booleanValue();
    }
}
