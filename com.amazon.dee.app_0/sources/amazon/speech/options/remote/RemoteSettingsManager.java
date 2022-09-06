package amazon.speech.options.remote;

import amazon.speech.util.DebugUtil;
import amazon.speech.util.HandlerWrapper;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class RemoteSettingsManager {
    public static final String REMOTE_MANAGER_THREAD_NAME = "RemoteSettingsManagerThread";
    JSONObject mCurrentRemoteSettings;
    private final HandlerWrapper mHandler;
    final Set<RemoteSettingsListener> mListeners;
    private final Runnable mNotifyListenersRunnable = new Runnable() { // from class: amazon.speech.options.remote.RemoteSettingsManager.1
        @Override // java.lang.Runnable
        public void run() {
            for (RemoteSettingsListener remoteSettingsListener : RemoteSettingsManager.this.mListeners) {
                remoteSettingsListener.onChanged();
            }
        }
    };
    private final RemoteSettingsStrategy mStrategy;
    protected static final boolean DEBUG = DebugUtil.getShouldDebug(DebugUtil.Module.CONF);
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.CONF, RemoteSettingsManager.class);

    /* loaded from: classes.dex */
    public interface RemoteSettingsListener {
        void onChanged();
    }

    public RemoteSettingsManager(RemoteSettingsStrategy remoteSettingsStrategy) {
        if (remoteSettingsStrategy != null) {
            this.mStrategy = remoteSettingsStrategy;
            this.mCurrentRemoteSettings = null;
            this.mListeners = new HashSet();
            this.mHandler = newHandler();
            this.mStrategy.init(new RemoteSettingsListener() { // from class: amazon.speech.options.remote.RemoteSettingsManager.2
                @Override // amazon.speech.options.remote.RemoteSettingsManager.RemoteSettingsListener
                public void onChanged() {
                    RemoteSettingsManager.this.loadRemoteSettings();
                    RemoteSettingsManager.this.notifyListeners();
                }
            });
            return;
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void loadRemoteSettings() {
        this.mCurrentRemoteSettings = this.mStrategy.getRemoteSettingsObj();
        if (DEBUG) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("loadRemoteSettings | mCurrentRemoteSettings ");
            sb.append(this.mCurrentRemoteSettings != null ? this.mCurrentRemoteSettings.toString() : "null");
            Log.i(str, sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListeners() {
        this.mHandler.post(this.mNotifyListenersRunnable);
    }

    public void addListener(final RemoteSettingsListener remoteSettingsListener) {
        if (remoteSettingsListener != null) {
            this.mHandler.post(new Runnable() { // from class: amazon.speech.options.remote.RemoteSettingsManager.3
                @Override // java.lang.Runnable
                public void run() {
                    RemoteSettingsManager.this.mListeners.add(remoteSettingsListener);
                    if (RemoteSettingsManager.this.mCurrentRemoteSettings != null) {
                        remoteSettingsListener.onChanged();
                    }
                }
            });
            return;
        }
        throw new IllegalArgumentException();
    }

    public void destroy() {
        this.mHandler.post(new Runnable() { // from class: amazon.speech.options.remote.RemoteSettingsManager.5
            @Override // java.lang.Runnable
            public void run() {
                RemoteSettingsManager.this.mListeners.clear();
            }
        });
        this.mStrategy.destroy();
    }

    public synchronized boolean getBoolean(String str, boolean z) {
        if (this.mCurrentRemoteSettings != null) {
            return this.mCurrentRemoteSettings.optBoolean(str, z);
        }
        return z;
    }

    public synchronized int getInteger(String str, int i) {
        if (this.mCurrentRemoteSettings != null) {
            return this.mCurrentRemoteSettings.optInt(str, i);
        }
        return i;
    }

    public synchronized long getLong(String str, long j) {
        if (this.mCurrentRemoteSettings != null) {
            return this.mCurrentRemoteSettings.optLong(str, j);
        }
        return j;
    }

    public synchronized String getString(String str, String str2) {
        if (this.mCurrentRemoteSettings != null) {
            return this.mCurrentRemoteSettings.optString(str, str2);
        }
        return str2;
    }

    protected HandlerWrapper newHandler() {
        return new HandlerWrapper(REMOTE_MANAGER_THREAD_NAME);
    }

    public void removeListener(final RemoteSettingsListener remoteSettingsListener) {
        if (remoteSettingsListener != null) {
            this.mHandler.post(new Runnable() { // from class: amazon.speech.options.remote.RemoteSettingsManager.4
                @Override // java.lang.Runnable
                public void run() {
                    RemoteSettingsManager.this.mListeners.remove(remoteSettingsListener);
                }
            });
            return;
        }
        throw new IllegalArgumentException();
    }

    public void sync() {
        this.mStrategy.sync();
        Log.i(TAG, "Remote sync job was scheduled successfully");
    }
}
