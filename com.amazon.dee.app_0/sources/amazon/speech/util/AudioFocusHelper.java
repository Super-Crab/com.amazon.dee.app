package amazon.speech.util;

import amazon.speech.util.DebugUtil;
import android.media.AudioManager;
import android.os.Trace;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class AudioFocusHelper {
    AudioManager.OnAudioFocusChangeListener mAfChangeListener;
    final AudioManagerWrapper mAudioManagerWrapper;
    private final ScheduledExecutorService mExecService;
    AudioManager.OnAudioFocusChangeListener mExternalAfCallback;
    private boolean mHeld;
    private boolean mLocked;
    private String mPackageFocusTakenFrom;
    ScheduledFuture mScheduledFuture;
    ScheduledFuture mScheduledUnlock;
    private Runnable mUnlockTask;
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.SIM, AudioFocusHelper.class);
    private static final boolean DEBUG = DebugUtil.getShouldDebug(DebugUtil.Module.SIM);

    public AudioFocusHelper(AudioManagerWrapper audioManagerWrapper) {
        this(audioManagerWrapper, null, Executors.newSingleThreadScheduledExecutor());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void acquireAudioFocus(int i, boolean z, long j) {
        Log.i(TAG, String.format("acquireAudioFocus durationHint (%d) lock (%s) lockDuration (%d)", Integer.valueOf(i), Boolean.valueOf(z), Long.valueOf(j)));
        Trace.beginSection("AudioFocusHelper_acquireAudioFocus");
        String str = TAG;
        Log.i(str, "acquireAudioFocus() with params " + i + " mHeld = " + this.mHeld);
        if (this.mHeld) {
            if (z) {
                lockFocus(j);
            }
            if (DEBUG) {
                Log.d(TAG, "trying to acquire audio focus but it's already held");
            }
            Trace.endSection();
            return;
        }
        this.mPackageFocusTakenFrom = this.mAudioManagerWrapper.getPackageInFocus();
        String str2 = TAG;
        Log.i(str2, "Taking focus from: " + this.mPackageFocusTakenFrom);
        if (this.mAudioManagerWrapper.acquireAudioFocus(this.mAfChangeListener, i) == 1) {
            if (DEBUG) {
                Log.d(TAG, "Audio Focus Acquired successfully!");
            }
            this.mHeld = true;
            if (z) {
                lockFocus(j);
            }
        } else {
            this.mHeld = false;
            Log.e(TAG, "Audio Focus not granted.");
        }
        Trace.endSection();
    }

    private void lockFocus(long j) {
        String str = TAG;
        Log.d(str, "lockFocus: " + j);
        this.mLocked = true;
        ScheduledFuture scheduledFuture = this.mScheduledUnlock;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.mScheduledUnlock = this.mExecService.schedule(this.mUnlockTask, j, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void releaseAudioFocusIfHeld(boolean z) {
        releaseAudioFocusIfHeld(z, true);
    }

    private void unlockFocus(boolean z) {
        Log.d(TAG, "unlockFocus");
        if (this.mLocked) {
            Log.i(TAG, "Focus Unlocked");
            this.mLocked = false;
            ScheduledFuture scheduledFuture = this.mScheduledUnlock;
            if (scheduledFuture == null || !z) {
                return;
            }
            scheduledFuture.cancel(false);
        }
    }

    public void changeAudioFocus(boolean z) {
        changeAudioFocus(z, 0L);
    }

    AudioManager.OnAudioFocusChangeListener getAudioFocusChangeListener() {
        return this.mAfChangeListener;
    }

    public String getPackageFocusTakenFrom() {
        return this.mPackageFocusTakenFrom;
    }

    public String getPackageInFocus() {
        return this.mAudioManagerWrapper.getPackageInFocus();
    }

    boolean isHeld() {
        return this.mHeld;
    }

    boolean isLocked() {
        return this.mLocked;
    }

    public void refreshPackageFocusTakenFrom() {
        this.mPackageFocusTakenFrom = this.mAudioManagerWrapper.getPackageInFocus();
    }

    void setHeld(boolean z) {
        this.mHeld = z;
    }

    public AudioFocusHelper(AudioManagerWrapper audioManagerWrapper, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this(audioManagerWrapper, onAudioFocusChangeListener, Executors.newSingleThreadScheduledExecutor());
    }

    public void changeAudioFocus(boolean z, long j) {
        changeAudioFocus(z, j, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void releaseAudioFocusIfHeld(boolean z, boolean z2) {
        Log.d(TAG, String.format("releaseAudioFocusIfHeld lock (%s) clear (%s)", Boolean.valueOf(z), Boolean.valueOf(z2)));
        Trace.beginSection("releaseAudioFocusIfHeld");
        if (this.mLocked && !z) {
            Log.d(TAG, "Trying to release focus but state locked");
            Trace.endSection();
        } else if (!this.mHeld) {
            Log.i(TAG, "Focus is not Held!");
            Trace.endSection();
        } else {
            Log.i(TAG, "Releasing Audio Focus!");
            this.mAudioManagerWrapper.abandonAudioFocus(this.mAfChangeListener);
            this.mHeld = false;
            unlockFocus(z2);
            Trace.endSection();
        }
    }

    public void changeAudioFocus(boolean z, long j, int i) {
        changeAudioFocus(z, j, i, false, 0L);
    }

    public AudioFocusHelper(AudioManagerWrapper audioManagerWrapper, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, ScheduledExecutorService scheduledExecutorService) {
        this.mUnlockTask = new Runnable() { // from class: amazon.speech.util.AudioFocusHelper.1
            @Override // java.lang.Runnable
            public void run() {
                Log.i(AudioFocusHelper.TAG, "Focus Unlocking on Timeout");
                AudioFocusHelper.this.releaseAudioFocusIfHeld(true, false);
            }
        };
        this.mAfChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: amazon.speech.util.AudioFocusHelper.2
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                if (AudioFocusHelper.DEBUG) {
                    String str = AudioFocusHelper.TAG;
                    Log.d(str, " got focus change " + i);
                }
                if (i == 1 || i == 2 || i == 4 || i == 3 || (RuntimeDeviceTypeHelper.isDeviceATablet() && i == -3)) {
                    AudioFocusHelper.this.mHeld = true;
                } else {
                    ScheduledFuture scheduledFuture = AudioFocusHelper.this.mScheduledFuture;
                    if (scheduledFuture != null) {
                        scheduledFuture.cancel(false);
                    }
                    AudioFocusHelper.this.releaseAudioFocusIfHeld(false);
                }
                AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener2 = AudioFocusHelper.this.mExternalAfCallback;
                if (onAudioFocusChangeListener2 != null) {
                    onAudioFocusChangeListener2.onAudioFocusChange(i);
                }
            }
        };
        this.mAudioManagerWrapper = audioManagerWrapper;
        this.mExecService = scheduledExecutorService;
        if (onAudioFocusChangeListener != null) {
            this.mExternalAfCallback = onAudioFocusChangeListener;
        }
    }

    public void changeAudioFocus(final boolean z, final long j, final int i, final boolean z2, final long j2) {
        Log.i(TAG, String.format("changeAudioFocus acquire (%s) delay (%d) duration (%d) lock (%s) duration (%d)", Boolean.valueOf(z), Long.valueOf(j), Integer.valueOf(i), Boolean.valueOf(z2), Long.valueOf(j2)));
        try {
            Trace.beginSection("AudioFocusHelper_changeAudioFocus");
            if (this.mScheduledFuture != null) {
                this.mScheduledFuture.cancel(false);
            }
            Runnable runnable = new Runnable() { // from class: amazon.speech.util.AudioFocusHelper.3
                @Override // java.lang.Runnable
                public void run() {
                    Log.i(AudioFocusHelper.TAG, String.format("changeAudioFocus RUN acquire (%s) delay (%d) duration (%d) lock (%s) duration (%d)", Boolean.valueOf(z), Long.valueOf(j), Integer.valueOf(i), Boolean.valueOf(z2), Long.valueOf(j2)));
                    try {
                        Trace.beginSection("changeAudioFocusRunnable_run");
                        if (z) {
                            AudioFocusHelper.this.acquireAudioFocus(i, z2, j2);
                        } else {
                            AudioFocusHelper.this.releaseAudioFocusIfHeld(z2);
                        }
                        Trace.endSection();
                    } catch (Throwable th) {
                        Log.wtf(AudioFocusHelper.TAG, "Error executing runnable!", th);
                    }
                }
            };
            if (0 == j) {
                runnable.run();
            } else {
                this.mScheduledFuture = this.mExecService.schedule(runnable, j, TimeUnit.MILLISECONDS);
            }
        } finally {
            Trace.endSection();
        }
    }
}
