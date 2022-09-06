package com.amazon.alexa.drive.orientation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.OrientationEventListener;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.drive.orientation.LandscapeErrorFragment;
import com.amazon.alexa.drive.orientation.OrientationConstants;
import com.amazon.alexa.mode.ModeService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes7.dex */
public class LandscapeErrorProvider {
    private static final String MARION_DEVICE_TYPE = "A1388YOZ88W373";
    private static final int ORIENTATION_CHANGE_TIMER_DELAY = 700;
    private static final String TAG = "LandscapeErrorProvider";
    private boolean isAppInForeground;
    private boolean isErrorScheduled;
    private Disposable mAccessoryDisposable;
    private Context mContext;
    private LandscapeErrorFragment.LandscapeErrorFragmentListener mErrorFragmentListener;
    private Handler mHandler;
    private LandscapeErrorFragment mLandscapeErrorFragment;
    private LifecycleEventObserver mLifecycleObserver;
    private ModeService mModeService;
    private OrientationEventListener mOrientationListener;
    private final Runnable showErrorRunnable = new Runnable() { // from class: com.amazon.alexa.drive.orientation.LandscapeErrorProvider.1
        @Override // java.lang.Runnable
        public void run() {
            Log.i(LandscapeErrorProvider.TAG, "showErrorRunnable starting");
            LandscapeErrorProvider.this.isErrorScheduled = false;
            LandscapeErrorProvider.this.showErrorBanner();
        }
    };
    private OrientationConstants.Orientation mCurrentOrientation = OrientationConstants.Orientation.PORTRAIT;

    /* renamed from: com.amazon.alexa.drive.orientation.LandscapeErrorProvider$5  reason: invalid class name */
    /* loaded from: classes7.dex */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$Event = new int[Lifecycle.Event.values().length];

        static {
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public LandscapeErrorProvider(Context context, ModeService modeService) {
        this.mContext = context;
        this.mModeService = modeService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableOrientationListener() {
        if (getOrientationEventListener() != null) {
            getOrientationEventListener().disable();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissErrorFragment() {
        if (getLandscapeErrorFragment() != null && getLandscapeErrorFragment().isVisible()) {
            getLandscapeErrorFragment().dismiss();
        }
        this.mLandscapeErrorFragment = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableOrientationListener() {
        if (getOrientationEventListener() != null) {
            getOrientationEventListener().enable();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeListeners() {
        disableOrientationListener();
        this.mOrientationListener = null;
        if (getLifecycleObserver() != null) {
            getCurrentLifecycle().removeObserver(getLifecycleObserver());
            this.mLifecycleObserver = null;
        }
        if (getAccessoryDisposable() != null) {
            getAccessoryDisposable().dispose();
            this.mAccessoryDisposable = null;
        }
    }

    void checkConnectedToLandscapeDevice() {
        this.mAccessoryDisposable = this.mModeService.getConnectedAutomotiveAccessories().subscribe(new Consumer() { // from class: com.amazon.alexa.drive.orientation.-$$Lambda$LandscapeErrorProvider$8LcRPNQqn9yV9mNkSUGw2pz-Pfs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LandscapeErrorProvider.this.lambda$checkConnectedToLandscapeDevice$0$LandscapeErrorProvider((List) obj);
            }
        }, $$Lambda$LandscapeErrorProvider$Z6z3koDYafrnshsvWTRJXwzc4Do.INSTANCE);
    }

    public void destroy() {
        dismissErrorFragment();
        removeListeners();
        if (getHandler() != null) {
            getHandler().removeCallbacks(this.showErrorRunnable);
            this.mHandler = null;
        }
        if (getErrorFragmentListener() != null) {
            this.mErrorFragmentListener = null;
        }
    }

    Disposable getAccessoryDisposable() {
        return this.mAccessoryDisposable;
    }

    Lifecycle getCurrentLifecycle() {
        return ProcessLifecycleOwner.get().getLifecycle();
    }

    OrientationConstants.Orientation getCurrentOrientation() {
        return this.mCurrentOrientation;
    }

    LandscapeErrorFragment.LandscapeErrorFragmentListener getErrorFragmentListener() {
        return this.mErrorFragmentListener;
    }

    Runnable getErrorRunnable() {
        return this.showErrorRunnable;
    }

    Handler getHandler() {
        return this.mHandler;
    }

    LandscapeErrorFragment getLandscapeErrorFragment() {
        return this.mLandscapeErrorFragment;
    }

    LifecycleEventObserver getLifecycleObserver() {
        return this.mLifecycleObserver;
    }

    OrientationEventListener getOrientationEventListener() {
        return this.mOrientationListener;
    }

    public void init() {
        checkConnectedToLandscapeDevice();
    }

    void initLifecycleObserver() {
        this.mLifecycleObserver = new LifecycleEventObserver() { // from class: com.amazon.alexa.drive.orientation.LandscapeErrorProvider.4
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                int ordinal = event.ordinal();
                if (ordinal == 1) {
                    String unused = LandscapeErrorProvider.TAG;
                    LandscapeErrorProvider.this.isAppInForeground = true;
                    LandscapeErrorProvider.this.enableOrientationListener();
                } else if (ordinal != 4) {
                } else {
                    String unused2 = LandscapeErrorProvider.TAG;
                    LandscapeErrorProvider.this.isAppInForeground = false;
                    LandscapeErrorProvider.this.disableOrientationListener();
                }
            }
        };
        getCurrentLifecycle().addObserver(getLifecycleObserver());
    }

    void initOnLandscapeDeviceConnected() {
        this.mHandler = new Handler();
        initOrientationListener();
        initLifecycleObserver();
        this.mErrorFragmentListener = new LandscapeErrorFragment.LandscapeErrorFragmentListener() { // from class: com.amazon.alexa.drive.orientation.LandscapeErrorProvider.2
            @Override // com.amazon.alexa.drive.orientation.LandscapeErrorFragment.LandscapeErrorFragmentListener
            public void onErrorDismiss() {
                LandscapeErrorProvider.this.removeListeners();
            }
        };
    }

    void initOrientationListener() {
        this.mOrientationListener = new OrientationEventListener(this.mContext) { // from class: com.amazon.alexa.drive.orientation.LandscapeErrorProvider.3
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i) {
                if (i > 85 && i < 95) {
                    String unused = LandscapeErrorProvider.TAG;
                    GeneratedOutlineSupport1.outline149("device in landscape right orientation, degree of rotation: ", i);
                    LandscapeErrorProvider.this.mCurrentOrientation = OrientationConstants.Orientation.LANDSCAPE_RIGHT;
                } else if (i > 265 && i < 275) {
                    String unused2 = LandscapeErrorProvider.TAG;
                    GeneratedOutlineSupport1.outline149("device in landscape left orientation, degree of rotation: ", i);
                    LandscapeErrorProvider.this.mCurrentOrientation = OrientationConstants.Orientation.LANDSCAPE_LEFT;
                } else {
                    LandscapeErrorProvider.this.mCurrentOrientation = OrientationConstants.Orientation.PORTRAIT;
                    LandscapeErrorProvider.this.dismissErrorFragment();
                    if (!LandscapeErrorProvider.this.isErrorScheduled || LandscapeErrorProvider.this.getHandler() == null) {
                        return;
                    }
                    LandscapeErrorProvider.this.getHandler().removeCallbacks(LandscapeErrorProvider.this.showErrorRunnable);
                    LandscapeErrorProvider.this.isErrorScheduled = false;
                    return;
                }
                if (LandscapeErrorProvider.this.getLandscapeErrorFragment() != null || LandscapeErrorProvider.this.isErrorScheduled || LandscapeErrorProvider.this.getHandler() == null) {
                    return;
                }
                LandscapeErrorProvider.this.getHandler().postDelayed(LandscapeErrorProvider.this.showErrorRunnable, 700L);
                LandscapeErrorProvider.this.isErrorScheduled = true;
            }
        };
        enableOrientationListener();
    }

    boolean isAppInForeground() {
        return this.isAppInForeground;
    }

    public /* synthetic */ void lambda$checkConnectedToLandscapeDevice$0$LandscapeErrorProvider(List list) throws Throwable {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Device device = ((DeviceGroup) it2.next()).getDevice();
            if (device != null && "A1388YOZ88W373".equals(device.getType())) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("connected to landscape device: ");
                outline107.append(device.getType());
                outline107.toString();
                initOnLandscapeDeviceConnected();
            }
        }
    }

    public /* synthetic */ void lambda$showErrorBanner$2$LandscapeErrorProvider() {
        Activity activity = (Activity) this.mContext;
        FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
        Fragment findFragmentByTag = activity.getFragmentManager().findFragmentByTag(OrientationConstants.LANDSCAPE_ERROR_DIALOG_TAG);
        if (!(findFragmentByTag instanceof LandscapeErrorFragment) || !findFragmentByTag.isVisible()) {
            if (findFragmentByTag != null) {
                beginTransaction.remove(findFragmentByTag);
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(OrientationConstants.LANDSCAPE_ERROR_DIALOG_ORIENTATION, getCurrentOrientation());
            beginTransaction.addToBackStack(null);
            this.mLandscapeErrorFragment = new LandscapeErrorFragment();
            getLandscapeErrorFragment().setLandscapeErrorFragmentListener(getErrorFragmentListener());
            getLandscapeErrorFragment().setArguments(bundle);
            beginTransaction.add(getLandscapeErrorFragment(), OrientationConstants.LANDSCAPE_ERROR_DIALOG_TAG).commitAllowingStateLoss();
        }
    }

    void showErrorBanner() {
        if (getCurrentOrientation() == null || getCurrentOrientation().equals(OrientationConstants.Orientation.PORTRAIT) || !(this.mContext instanceof Activity) || getHandler() == null) {
            return;
        }
        getHandler().post(new Runnable() { // from class: com.amazon.alexa.drive.orientation.-$$Lambda$LandscapeErrorProvider$tD7JRzKlFt7aa138Rx8jVqPt8iQ
            @Override // java.lang.Runnable
            public final void run() {
                LandscapeErrorProvider.this.lambda$showErrorBanner$2$LandscapeErrorProvider();
            }
        });
    }
}
