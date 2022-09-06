package amazon.speech.simclient.focus;

import android.util.Log;
import android.view.View;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class SystemVisualFocusUtils {
    private static final String TAG = "SystemVisualFocusUtils";
    private static final Map<View, View.OnAttachStateChangeListener> sViewToListener = new WeakHashMap();

    /* loaded from: classes.dex */
    private static class AutomaticSystemVisualFocusManager implements View.OnAttachStateChangeListener {
        private final SystemVisualFocusResultCallback mAcquireModificationCallback;
        private final ExplicitFocusClient mClient;
        private final int mLayer;
        private final String mNamespace;
        private final SystemVisualFocusResultCallback mReleaseModificationCallback;

        AutomaticSystemVisualFocusManager(ExplicitFocusClient explicitFocusClient, View view, String str, int i, SystemVisualFocusResultCallback systemVisualFocusResultCallback, SystemVisualFocusResultCallback systemVisualFocusResultCallback2) {
            this.mClient = explicitFocusClient;
            this.mNamespace = str;
            this.mLayer = i;
            this.mAcquireModificationCallback = systemVisualFocusResultCallback;
            this.mReleaseModificationCallback = systemVisualFocusResultCallback2;
            if (view.isAttachedToWindow()) {
                onViewAttachedToWindow(view);
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public synchronized void onViewAttachedToWindow(View view) {
            String str = SystemVisualFocusUtils.TAG;
            Log.i(str, "Automatically acquiring system visual focus for " + view);
            this.mClient.acquireSystemVisualFocus(this.mNamespace, this.mLayer, this.mAcquireModificationCallback);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public synchronized void onViewDetachedFromWindow(View view) {
            String str = SystemVisualFocusUtils.TAG;
            Log.i(str, "Automatically releasing system visual focus for " + view);
            this.mClient.releaseSystemVisualFocus(this.mNamespace, this.mLayer, this.mReleaseModificationCallback);
        }
    }

    public static synchronized void addAutomaticSystemVisualFocus(ExplicitFocusClient explicitFocusClient, View view, String str, int i, SystemVisualFocusResultCallback systemVisualFocusResultCallback, SystemVisualFocusResultCallback systemVisualFocusResultCallback2) {
        synchronized (SystemVisualFocusUtils.class) {
            if (!sViewToListener.containsKey(view)) {
                AutomaticSystemVisualFocusManager automaticSystemVisualFocusManager = new AutomaticSystemVisualFocusManager(explicitFocusClient, view, str, i, systemVisualFocusResultCallback, systemVisualFocusResultCallback2);
                sViewToListener.put(view, automaticSystemVisualFocusManager);
                view.addOnAttachStateChangeListener(automaticSystemVisualFocusManager);
                Log.i(TAG, String.format("Added automatic system visual focus handling for view %h with listener %h", view, automaticSystemVisualFocusManager));
            } else {
                throw new IllegalStateException("Cannot automatically handle visual focus for a view more than once");
            }
        }
    }

    public static synchronized void removeAutomaticSystemVisualFocus(View view) {
        synchronized (SystemVisualFocusUtils.class) {
            View.OnAttachStateChangeListener remove = sViewToListener.remove(view);
            if (remove == null) {
                return;
            }
            remove.onViewDetachedFromWindow(view);
            view.removeOnAttachStateChangeListener(remove);
            Log.i(TAG, String.format("Removed automatic system visual focus handling for view %h with listener %h", view, remove));
        }
    }
}
