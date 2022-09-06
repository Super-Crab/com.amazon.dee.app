package amazon.speech.tts;

import amazon.speech.util.Log;
import android.content.Context;
import android.os.PowerManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class DefaultWakeLockManager implements WakeLockManager {
    private final WakeLockWrapper mWakeLock;
    private static final String WAKE_LOCK_TAG = TtsMediaPlayer.class.getSimpleName();
    private static final String TAG = DefaultWakeLockManager.class.getSimpleName();

    /* loaded from: classes.dex */
    protected interface WakeLockWrapper {
        void acquire();

        boolean isHeld();

        void release();
    }

    /* loaded from: classes.dex */
    private static class WakeLockWrapperImpl implements WakeLockWrapper {
        private final PowerManager.WakeLock mWakeLock;

        public WakeLockWrapperImpl(PowerManager.WakeLock wakeLock) {
            this.mWakeLock = wakeLock;
        }

        @Override // amazon.speech.tts.DefaultWakeLockManager.WakeLockWrapper
        public void acquire() {
            this.mWakeLock.acquire();
        }

        @Override // amazon.speech.tts.DefaultWakeLockManager.WakeLockWrapper
        public boolean isHeld() {
            return this.mWakeLock.isHeld();
        }

        @Override // amazon.speech.tts.DefaultWakeLockManager.WakeLockWrapper
        public void release() {
            this.mWakeLock.release();
        }
    }

    public DefaultWakeLockManager(Context context) {
        this(new WakeLockWrapperImpl(((PowerManager) context.getSystemService("power")).newWakeLock(268435462, WAKE_LOCK_TAG)));
    }

    @Override // amazon.speech.tts.WakeLockManager
    public void acquire() {
        try {
            if (this.mWakeLock.isHeld()) {
                return;
            }
            this.mWakeLock.acquire();
        } catch (SecurityException unused) {
            Log.w(TAG, "acquireWakeLock failed - does the app have android.permission.WAKE_LOCK?");
        } catch (Exception e) {
            Log.w(TAG, "acquireWakeLock failed.", e);
        }
    }

    @Override // amazon.speech.tts.WakeLockManager
    public void release() {
        try {
            if (!this.mWakeLock.isHeld()) {
                return;
            }
            this.mWakeLock.release();
        } catch (Exception e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("releaseWakeLock failed: ");
            outline107.append(e.getLocalizedMessage());
            Log.w(str, outline107.toString());
        }
    }

    DefaultWakeLockManager(WakeLockWrapper wakeLockWrapper) {
        this.mWakeLock = wakeLockWrapper;
    }
}
