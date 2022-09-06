package com.amazon.alexa.drive.entertainment.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.amazon.alexa.drive.entertainment.model.TransportControlCommand;
import com.amazon.alexa.drive.entertainment.util.ImageUtils;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.drive.userstudy.LogConstants;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes7.dex */
public class NowPlayingScreenViewController extends ViewManagerViewController implements NowPlayingScreenContract.View, NowPlayingScreenContract.View.MediaErrorDialogListener {
    private static final int PROGRESS_BAR_UPDATE_SECONDS = 1;
    private static final int REQUEST_PLAYER_INFO_DELAY_MS = 2000;
    private static final String TAG = "NowPlayingScreenViewController";
    private String mCurrentNPSBgUrl = "";
    @Inject
    DriveModeThemeManager mDriveModeThemeManager;
    @Inject
    EntertainmentMetrics mEntertainmentMetrics;
    private Handler mHandler;
    private boolean mIsFirstNetworkStatusCallback;
    private boolean mIsMediaPlaying;
    @Inject
    NetworkConnectivityManager mNetworkConnectivityManager;
    private Subscription mNetworkStatusSubscription;
    @Inject
    NowPlayingScreenContract.Presenter mNowPlayingScreenPresenter;
    private Runnable mProgressBarRunnable;
    private Handler mProgressHandler;
    @Inject
    TTCFRecordOnce mTTCFRecordOnce;
    private boolean mWasNPSLaunchedWithDefaultData;
    @Inject
    WeblabManager mWeblabManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$toggleOverrideStatusAndNavBarBackground$2(View view, int i, Activity activity, int i2) {
        Log.i(TAG, "onSystemUiVisibilityChange");
        if ((i2 & 4) == 0) {
            view.setSystemUiVisibility(i);
            activity.getWindow().setStatusBarColor(0);
            activity.getWindow().setNavigationBarColor(0);
        }
    }

    private void loadNPSBackground(final JSONObject jSONObject) {
        Log.i(TAG, "updateAlbumArt");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$S0htEjs2wNkUWLlMR3ErqjSgHGw
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$loadNPSBackground$7$NowPlayingScreenViewController(jSONObject);
            }
        });
    }

    RequestListener<Bitmap> createBitmapRequestListener() {
        return new RequestListener<Bitmap>() { // from class: com.amazon.alexa.drive.entertainment.view.NowPlayingScreenViewController.3
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
                String str = NowPlayingScreenViewController.TAG;
                Log.e(str, "onLoadFailed " + glideException);
                if (NowPlayingScreenViewController.this.getView() != null) {
                    ((ConstraintLayout) NowPlayingScreenViewController.this.getView().findViewById(R.id.nps_container)).setBackgroundColor(NowPlayingScreenViewController.this.getView().getResources().getColor(R.color.DriveMode_Background));
                    NowPlayingScreenViewController.this.getView().findViewById(R.id.nps_scrim).setVisibility(8);
                    return false;
                }
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
                String str = NowPlayingScreenViewController.TAG;
                Log.i(str, "onResourceReady " + bitmap);
                if (NowPlayingScreenViewController.this.getView() != null) {
                    NowPlayingScreenViewController.this.updateNPSBackground(bitmap);
                    return false;
                }
                return false;
            }
        };
    }

    RequestListener<Drawable> createDrawableRequestListener() {
        return new RequestListener<Drawable>() { // from class: com.amazon.alexa.drive.entertainment.view.NowPlayingScreenViewController.2
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                String str = NowPlayingScreenViewController.TAG;
                Log.e(str, "onLoadFailed " + glideException);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                String str = NowPlayingScreenViewController.TAG;
                Log.i(str, "onResourceReady " + drawable);
                if (NowPlayingScreenViewController.this.getView() != null) {
                    NowPlayingScreenViewController.this.getView().findViewById(R.id.nps_album_art_default).setVisibility(4);
                    NowPlayingScreenViewController.this.getView().findViewById(R.id.nps_album_art).setVisibility(0);
                }
                return false;
            }
        };
    }

    @VisibleForTesting
    MediaErrorDialogFragment createMediaErrorDialogFragment() {
        return new MediaErrorDialogFragment();
    }

    Bitmap getBlurredAlbumArt(Bitmap bitmap) {
        return ImageUtils.applyFastGaussianBlurToBitmap(bitmap, getView().getResources().getInteger(R.integer.nps_background_blur_radius));
    }

    String getCurrentNPSBgUrl() {
        return this.mCurrentNPSBgUrl;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController
    protected DriveModeThemeManager getDriveModeThemeManager() {
        return this.mDriveModeThemeManager;
    }

    Point getInteractableScreenSize(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    Subscription getNetworkStatusSubscription() {
        return this.mNetworkStatusSubscription;
    }

    Handler getProgressHandler() {
        return this.mProgressHandler;
    }

    Point getRealScreenSize(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        return point;
    }

    int getStatusBarHeight() {
        Context context = getView().getContext();
        if (context instanceof ContextThemeWrapper) {
            context = ((ContextThemeWrapper) context).getBaseContext();
        }
        int i = 0;
        if (context instanceof Activity) {
            Rect rect = new Rect();
            ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            i = rect.top;
        }
        String str = TAG;
        Log.i(str, "getStatusBarHeight " + i);
        return i;
    }

    Point getSystemNavigationBarSize(Context context) {
        Point interactableScreenSize = getInteractableScreenSize(context);
        Point realScreenSize = getRealScreenSize(context);
        int i = interactableScreenSize.x;
        int i2 = realScreenSize.x;
        if (i < i2) {
            return new Point(i2 - i, interactableScreenSize.y);
        }
        int i3 = interactableScreenSize.y;
        int i4 = realScreenSize.y;
        if (i3 < i4) {
            return new Point(i, i4 - i3);
        }
        return new Point();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getString(R.string.dm_title_entertainment);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.View.MediaErrorDialogListener
    public void handleMediaErrorDialogClose() {
        Log.i(TAG, "handleMediaErrorDialogClose");
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.ACTION_CLOSE_ERROR_DIALOG);
        navigateToPreviousScreen();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleNetworkConnectionChange(boolean z) {
        GeneratedOutlineSupport1.outline173("handleNetworkConnectionChange ", z, TAG);
        if (isFirstNetworkStatusCallback()) {
            Log.i(TAG, "isFirstNetworkStatusCallback");
            this.mIsFirstNetworkStatusCallback = false;
        } else if (z) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$aIV1gm85hS-l-Kn99c0uR2bTAJ0
                @Override // java.lang.Runnable
                public final void run() {
                    NowPlayingScreenViewController.this.lambda$handleNetworkConnectionChange$1$NowPlayingScreenViewController();
                }
            }, 2000L);
            if (getView() == null) {
                return;
            }
            Button button = (Button) getView().findViewById(R.id.play_button);
            if (button.getVisibility() != 0) {
                return;
            }
            button.setEnabled(true);
        } else if (getView() == null) {
        } else {
            View view = getView();
            getView().findViewById(R.id.nps_album_art_no_network).setVisibility(0);
            getView().findViewById(R.id.nps_album_art).setVisibility(4);
            Button button2 = (Button) view.findViewById(R.id.play_button);
            if (button2.getVisibility() == 0) {
                button2.setEnabled(false);
            }
            Button button3 = (Button) view.findViewById(R.id.previous_button);
            if (button3.getVisibility() == 0) {
                button3.setEnabled(false);
            }
            Button button4 = (Button) view.findViewById(R.id.next_button);
            if (button4.getVisibility() == 0) {
                button4.setEnabled(false);
            }
            Button button5 = (Button) view.findViewById(R.id.jump_back_30_button);
            if (button5.getVisibility() == 0) {
                button5.setEnabled(false);
            }
            Button button6 = (Button) view.findViewById(R.id.jump_forward_30_button);
            if (button6.getVisibility() == 0) {
                button6.setEnabled(false);
            }
            Button button7 = (Button) view.findViewById(R.id.shuffle_button);
            if (button7.getVisibility() == 0) {
                button7.setEnabled(false);
            }
            Button button8 = (Button) view.findViewById(R.id.repeat_button);
            if (button8.getVisibility() == 0) {
                button8.setEnabled(false);
            }
            Button button9 = (Button) view.findViewById(R.id.like_button);
            if (button9.getVisibility() == 0) {
                button9.setEnabled(false);
            }
            Button button10 = (Button) view.findViewById(R.id.dislike_button);
            if (button10.getVisibility() != 0) {
                return;
            }
            button10.setEnabled(false);
        }
    }

    void initNPSUI(View view) {
        String str = TAG;
        Log.i(str, "initNPSUI " + view);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.ACTION_SWITCH_TO_MUSIC);
        if (view != null) {
            final String autoModeType = DriveModeMetricsHelper.getAutoModeType();
            final Button button = (Button) view.findViewById(R.id.play_button);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$O0dr8P05_tjqh8KQcP3iyQZPM9c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NowPlayingScreenViewController.this.lambda$initNPSUI$15$NowPlayingScreenViewController(autoModeType, button, view2);
                }
            });
            final Button button2 = (Button) view.findViewById(R.id.pause_button);
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$MkcnGbaLl7NyqAUwEK0yhGx5mKo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NowPlayingScreenViewController.this.lambda$initNPSUI$16$NowPlayingScreenViewController(autoModeType, button2, view2);
                }
            });
            final Button button3 = (Button) view.findViewById(R.id.previous_button);
            button3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$ZAT7DFzXYOPi_0OZey_RAVTA1ZY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NowPlayingScreenViewController.this.lambda$initNPSUI$17$NowPlayingScreenViewController(autoModeType, button3, view2);
                }
            });
            final Button button4 = (Button) view.findViewById(R.id.next_button);
            button4.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$essu8wRbw2j6607Qw7jNAVMYkWc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NowPlayingScreenViewController.this.lambda$initNPSUI$18$NowPlayingScreenViewController(autoModeType, button4, view2);
                }
            });
            final Button button5 = (Button) view.findViewById(R.id.jump_back_30_button);
            button5.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$hq5Kp0CyiJUDPeOmxe7LeSP2t6U
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NowPlayingScreenViewController.this.lambda$initNPSUI$19$NowPlayingScreenViewController(autoModeType, button5, view2);
                }
            });
            final Button button6 = (Button) view.findViewById(R.id.jump_forward_30_button);
            button6.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$ffZJ1Zzv6202bQgEXRf85SJnkXs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NowPlayingScreenViewController.this.lambda$initNPSUI$20$NowPlayingScreenViewController(autoModeType, button6, view2);
                }
            });
            final Button button7 = (Button) view.findViewById(R.id.shuffle_button);
            button7.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$8GdcpVmYy0lmrFstAQ7-QMcZGPc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NowPlayingScreenViewController.this.lambda$initNPSUI$21$NowPlayingScreenViewController(autoModeType, button7, view2);
                }
            });
            final Button button8 = (Button) view.findViewById(R.id.repeat_button);
            button8.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$c6nUW4r0cpf4y7UKL4RgkKKo2Mw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NowPlayingScreenViewController.this.lambda$initNPSUI$22$NowPlayingScreenViewController(autoModeType, button8, view2);
                }
            });
            final Button button9 = (Button) view.findViewById(R.id.like_button);
            button9.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$gE3qUvJolsUvS7xF2ydRTRO5oPM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NowPlayingScreenViewController.this.lambda$initNPSUI$23$NowPlayingScreenViewController(autoModeType, button9, view2);
                }
            });
            final Button button10 = (Button) view.findViewById(R.id.dislike_button);
            button10.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$infTXl8HUn3X-X1ylfXi_KHHP00
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    NowPlayingScreenViewController.this.lambda$initNPSUI$24$NowPlayingScreenViewController(autoModeType, button10, view2);
                }
            });
        }
    }

    void initNetworkConnectionManager() {
        this.mIsFirstNetworkStatusCallback = true;
        this.mNetworkStatusSubscription = this.mNetworkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$efEmL3301WDVoIYq7XFIw1CbJHA
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NowPlayingScreenViewController.this.handleNetworkConnectionChange(((Boolean) obj).booleanValue());
            }
        });
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController
    protected boolean isDomainView() {
        return false;
    }

    boolean isFirstNetworkStatusCallback() {
        return this.mIsFirstNetworkStatusCallback;
    }

    boolean isMediaPlaying() {
        return this.mIsMediaPlaying;
    }

    public /* synthetic */ void lambda$handleNetworkConnectionChange$1$NowPlayingScreenViewController() {
        this.mNowPlayingScreenPresenter.requestPlayerInfo();
    }

    public /* synthetic */ void lambda$initNPSUI$15$NowPlayingScreenViewController(String str, Button button, View view) {
        this.mEntertainmentMetrics.logNPSButtonSelected(str, EntertainmentMetrics.Button.PLAY);
        this.mNowPlayingScreenPresenter.sendPrimaryTransportControlCommand(TransportControlCommand.PLAY);
        button.setEnabled(false);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.PLAY_BUTTON);
    }

    public /* synthetic */ void lambda$initNPSUI$16$NowPlayingScreenViewController(String str, Button button, View view) {
        this.mEntertainmentMetrics.logNPSButtonSelected(str, EntertainmentMetrics.Button.PAUSE);
        this.mNowPlayingScreenPresenter.sendPrimaryTransportControlCommand(TransportControlCommand.PAUSE);
        button.setEnabled(false);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.PAUSE_BUTTON);
    }

    public /* synthetic */ void lambda$initNPSUI$17$NowPlayingScreenViewController(String str, Button button, View view) {
        this.mEntertainmentMetrics.logNPSButtonSelected(str, EntertainmentMetrics.Button.PREV);
        this.mNowPlayingScreenPresenter.sendPrimaryTransportControlCommand(TransportControlCommand.PREV);
        button.setEnabled(false);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.PREV_SONG);
    }

    public /* synthetic */ void lambda$initNPSUI$18$NowPlayingScreenViewController(String str, Button button, View view) {
        this.mEntertainmentMetrics.logNPSButtonSelected(str, EntertainmentMetrics.Button.NEXT);
        this.mNowPlayingScreenPresenter.sendPrimaryTransportControlCommand(TransportControlCommand.NEXT);
        button.setEnabled(false);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.NEXT_SONG);
    }

    public /* synthetic */ void lambda$initNPSUI$19$NowPlayingScreenViewController(String str, Button button, View view) {
        this.mEntertainmentMetrics.logNPSButtonSelected(str, EntertainmentMetrics.Button.PREV30);
        this.mNowPlayingScreenPresenter.sendPrimaryTransportControlCommand(TransportControlCommand.JUMP_BACK_30);
        button.setEnabled(false);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.JUMP_BACK_BUTTON);
    }

    public /* synthetic */ void lambda$initNPSUI$20$NowPlayingScreenViewController(String str, Button button, View view) {
        this.mEntertainmentMetrics.logNPSButtonSelected(str, EntertainmentMetrics.Button.SKIP30);
        this.mNowPlayingScreenPresenter.sendPrimaryTransportControlCommand(TransportControlCommand.JUMP_FORWARD_30);
        button.setEnabled(false);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.JUMP_FORWARD_BUTTON);
    }

    public /* synthetic */ void lambda$initNPSUI$21$NowPlayingScreenViewController(String str, Button button, View view) {
        this.mEntertainmentMetrics.logNPSButtonSelected(str, EntertainmentMetrics.Button.SHUFFLE);
        this.mNowPlayingScreenPresenter.sendSecondaryTransportControlCommand(TransportControlCommand.SHUFFLE, !view.isSelected());
        button.setEnabled(false);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.SHUFFLE_BUTTON);
    }

    public /* synthetic */ void lambda$initNPSUI$22$NowPlayingScreenViewController(String str, Button button, View view) {
        this.mEntertainmentMetrics.logNPSButtonSelected(str, EntertainmentMetrics.Button.REPEAT);
        this.mNowPlayingScreenPresenter.sendSecondaryTransportControlCommand(TransportControlCommand.REPEAT, !view.isSelected());
        button.setEnabled(false);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.REPEAT_BUTTON);
    }

    public /* synthetic */ void lambda$initNPSUI$23$NowPlayingScreenViewController(String str, Button button, View view) {
        this.mEntertainmentMetrics.logNPSButtonSelected(str, EntertainmentMetrics.Button.THUMBSUP);
        this.mNowPlayingScreenPresenter.sendSecondaryTransportControlCommand(TransportControlCommand.LIKE, !view.isSelected());
        button.setEnabled(false);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.LIKE_BUTTON);
    }

    public /* synthetic */ void lambda$initNPSUI$24$NowPlayingScreenViewController(String str, Button button, View view) {
        this.mEntertainmentMetrics.logNPSButtonSelected(str, EntertainmentMetrics.Button.THUMBSDOWN);
        this.mNowPlayingScreenPresenter.sendSecondaryTransportControlCommand(TransportControlCommand.DISLIKE, !view.isSelected());
        button.setEnabled(false);
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.DISLIKE_BUTTON);
    }

    public /* synthetic */ void lambda$loadNPSBackground$7$NowPlayingScreenViewController(JSONObject jSONObject) {
        if (jSONObject == null || getView() == null) {
            return;
        }
        try {
            String string = jSONObject.getString("url");
            if (string == null) {
                return;
            }
            loadImageToBitmap(string);
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "Error parsing album art JSON" + e);
        }
    }

    public /* synthetic */ void lambda$makeView$0$NowPlayingScreenViewController(View view) {
        Log.i(TAG, "onClick");
        DriverDistractionLog.logTouch(LogConstants.NOW_PLAYING, LogConstants.ACTION_EXIT);
        navigateToPreviousScreen();
    }

    public /* synthetic */ void lambda$onMediaError$4$NowPlayingScreenViewController(MediaErrorPayload mediaErrorPayload) {
        if (getView() != null) {
            Context context = getView().getContext();
            if (context instanceof ContextThemeWrapper) {
                context = ((ContextThemeWrapper) context).getBaseContext();
            }
            if (!(context instanceof Activity) || !isAttached()) {
                return;
            }
            Log.i(TAG, "Show media error dialog");
            Activity activity = (Activity) context;
            FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
            Fragment findFragmentByTag = activity.getFragmentManager().findFragmentByTag(EntertainmentConstants.ENTERTAINMENT_ITEM_ATTR_MEDIA_ERROR_DIALOG_TAG);
            if ((findFragmentByTag instanceof MediaErrorDialogFragment) && findFragmentByTag.isVisible()) {
                return;
            }
            if (findFragmentByTag != null) {
                beginTransaction.remove(findFragmentByTag);
            }
            beginTransaction.addToBackStack(null);
            Bundle bundle = new Bundle();
            bundle.putSerializable(EntertainmentConstants.ENTERTAINMENT_ITEM_ATTR_MEDIA_ERROR_DIALOG_PAYLOAD, mediaErrorPayload);
            MediaErrorDialogFragment createMediaErrorDialogFragment = createMediaErrorDialogFragment();
            createMediaErrorDialogFragment.setMediaErrorDialogListener(this);
            createMediaErrorDialogFragment.setArguments(bundle);
            beginTransaction.add(createMediaErrorDialogFragment, EntertainmentConstants.ENTERTAINMENT_ITEM_ATTR_MEDIA_ERROR_DIALOG_TAG).commitAllowingStateLoss();
        }
    }

    public /* synthetic */ void lambda$resetNPSUI$25$NowPlayingScreenViewController() {
        if (getView() != null) {
            View view = getView();
            ((LinearLayout) view.findViewById(R.id.nps_secondary_playback_controls)).setVisibility(8);
            ((ImageView) view.findViewById(R.id.msp_logo_view)).setImageDrawable(null);
            getView().findViewById(R.id.nps_album_art_default).setVisibility(0);
            getView().findViewById(R.id.nps_album_art).setVisibility(4);
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.nps_progress_bar);
            getProgressHandler().removeCallbacksAndMessages(null);
            progressBar.setProgress(0);
            progressBar.setMax(0);
            progressBar.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$updateAlbumArt$6$NowPlayingScreenViewController(JSONObject jSONObject) {
        if (jSONObject == null || getView() == null) {
            return;
        }
        loadImageToView(jSONObject.optString("url", ""), (ImageView) getView().findViewById(R.id.nps_album_art));
    }

    public /* synthetic */ void lambda$updateMSPLogo$5$NowPlayingScreenViewController(JSONObject jSONObject) {
        if (jSONObject == null || getView() == null) {
            return;
        }
        try {
            ImageView imageView = (ImageView) getView().findViewById(R.id.msp_logo_view);
            String string = jSONObject.getString("providerName");
            if (string == null) {
                return;
            }
            imageView.setContentDescription(string);
            char c = 65535;
            switch (string.hashCode()) {
                case -2008868758:
                    if (string.equals(EntertainmentConstants.PROVIDER_TUNE_IN)) {
                        c = 4;
                        break;
                    }
                    break;
                case -1836394231:
                    if (string.equals(EntertainmentConstants.PROVIDER_AMAZON_MUSIC)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1109675895:
                    if (string.equals(EntertainmentConstants.PROVIDER_IHEART_RADIO)) {
                        c = 5;
                        break;
                    }
                    break;
                case -334070118:
                    if (string.equals(EntertainmentConstants.PROVIDER_SPOTIFY)) {
                        c = 2;
                        break;
                    }
                    break;
                case 560484567:
                    if (string.equals(EntertainmentConstants.PROVIDER_KINDLE)) {
                        c = '\b';
                        break;
                    }
                    break;
                case 867554807:
                    if (string.equals(EntertainmentConstants.PROVIDER_PANDORA)) {
                        c = 3;
                        break;
                    }
                    break;
                case 1003385570:
                    if (string.equals(EntertainmentConstants.PROVIDER_AUDIBLE)) {
                        c = 1;
                        break;
                    }
                    break;
                case 2030490015:
                    if (string.equals(EntertainmentConstants.PROVIDER_APPLE_MUSIC)) {
                        c = 6;
                        break;
                    }
                    break;
                case 2043187267:
                    if (string.equals(EntertainmentConstants.PROVIDER_DEEZER)) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    imageView.setImageResource(R.drawable.ic_provider_amazon_music);
                    return;
                case 1:
                    imageView.setImageResource(R.drawable.ic_provider_audible);
                    return;
                case 2:
                    imageView.setImageResource(R.drawable.ic_provider_spotify);
                    return;
                case 3:
                    imageView.setImageResource(R.drawable.ic_provider_pandora);
                    return;
                case 4:
                    imageView.setImageResource(R.drawable.ic_provider_tunein);
                    return;
                case 5:
                    imageView.setImageResource(R.drawable.ic_provider_iheart_radio);
                    return;
                case 6:
                    imageView.setImageResource(R.drawable.ic_provider_apple_music);
                    return;
                case 7:
                    imageView.setImageResource(R.drawable.ic_provider_deezer);
                    return;
                case '\b':
                    imageView.setImageResource(R.drawable.ic_provider_kindle);
                    return;
                default:
                    Log.e(TAG, "Provider not recognized");
                    return;
            }
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "Error parsing msp provider JSON" + e);
        }
    }

    public /* synthetic */ void lambda$updateMediaText$9$NowPlayingScreenViewController(JSONObject jSONObject, String str) {
        if (getView() != null) {
            View view = getView();
            TextView textView = (TextView) view.findViewById(R.id.nps_title);
            if (jSONObject != null) {
                try {
                    TextView textView2 = (TextView) view.findViewById(R.id.nps_subtitle);
                    String optString = jSONObject.optString("title", "");
                    if (optString.toLowerCase().equals("null")) {
                        optString = "";
                    }
                    String optString2 = jSONObject.optString(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_SUB_TEXT_1, "");
                    if (optString2.toLowerCase().equals("null")) {
                        optString2 = "";
                    }
                    textView.setText(optString);
                    textView2.setText(optString2);
                    return;
                } catch (Exception e) {
                    GeneratedOutlineSupport1.outline156("Error parsing info text JSON", e, TAG);
                    return;
                }
            }
            textView.setText(str);
        }
    }

    public /* synthetic */ void lambda$updateNPSBackground$8$NowPlayingScreenViewController(Bitmap bitmap) {
        if (getView() == null || bitmap == null) {
            return;
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) getView().findViewById(R.id.nps_container);
        Bitmap blurredAlbumArt = getBlurredAlbumArt(bitmap);
        if (blurredAlbumArt != null) {
            constraintLayout.setBackground(new BitmapDrawable(getView().getResources(), blurredAlbumArt));
            getView().findViewById(R.id.nps_scrim).setVisibility(0);
            return;
        }
        constraintLayout.setBackgroundColor(getView().getResources().getColor(R.color.DriveMode_Background));
        getView().findViewById(R.id.nps_scrim).setVisibility(8);
    }

    public /* synthetic */ void lambda$updatePlaybackState$14$NowPlayingScreenViewController(String str) {
        if (((str.hashCode() == 224418830 && str.equals("PLAYING")) ? (char) 0 : (char) 65535) != 0) {
            updateNowPlayingPlaybackState(false);
        } else {
            updateNowPlayingPlaybackState(true);
        }
    }

    public /* synthetic */ void lambda$updatePrimaryPlaybackControls$11$NowPlayingScreenViewController(JSONObject jSONObject) {
        if (jSONObject == null || getView() == null) {
            return;
        }
        try {
            View view = getView();
            updateButtonState(jSONObject.optString("previous", ""), (Button) view.findViewById(R.id.previous_button));
            updateButtonState(jSONObject.optString("next", ""), (Button) view.findViewById(R.id.next_button));
            updateButtonState(jSONObject.optString(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CONTROL_FORWARD, ""), (Button) view.findViewById(R.id.jump_forward_30_button));
            updateButtonState(jSONObject.optString(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CONTROL_REWIND, ""), (Button) view.findViewById(R.id.jump_back_30_button));
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Error parsing progress bar JSON", e, TAG);
        }
    }

    public /* synthetic */ void lambda$updateProgressBar$10$NowPlayingScreenViewController(JSONObject jSONObject) {
        if (jSONObject == null || getView() == null) {
            return;
        }
        try {
            View view = getView();
            if (!jSONObject.optBoolean("visible")) {
                return;
            }
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.nps_progress_bar);
            progressBar.setVisibility(0);
            progressBar.setMax(jSONObject.optInt(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MEDIA_LENGTH));
            progressBar.setProgress(jSONObject.optInt(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MEDIA_PROGRESS));
            startProgressUpdate();
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Error parsing progress bar JSON", e, TAG);
        }
    }

    public /* synthetic */ void lambda$updateSecondaryPlaybackControlButtons$13$NowPlayingScreenViewController(JSONObject jSONObject) {
        if (jSONObject == null || getView() == null) {
            return;
        }
        View view = getView();
        updateButtonState(jSONObject.optString("repeat", ""), (Button) view.findViewById(R.id.repeat_button));
        updateButtonState(jSONObject.optString("shuffle", ""), (Button) view.findViewById(R.id.shuffle_button));
        updateButtonState(jSONObject.optString(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CONTROL_LIKE, ""), (Button) view.findViewById(R.id.like_button));
        updateButtonState(jSONObject.optString(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CONTROL_DISLIKE, ""), (Button) view.findViewById(R.id.dislike_button));
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0033, code lost:
        r0.setVisibility(0);
        updateSecondaryPlaybackControlButtons(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$updateSecondaryPlaybackControls$12$NowPlayingScreenViewController(org.json.JSONObject r6, org.json.JSONObject r7) {
        /*
            r5 = this;
            if (r6 == 0) goto L57
            android.view.View r0 = r5.getView()
            if (r0 == 0) goto L57
            android.view.View r0 = r5.getView()     // Catch: org.json.JSONException -> L40
            java.lang.String r1 = "providerName"
            java.lang.String r6 = r6.getString(r1)     // Catch: org.json.JSONException -> L40
            int r1 = com.amazon.alexa.drive.R.id.nps_secondary_playback_controls     // Catch: org.json.JSONException -> L40
            android.view.View r0 = r0.findViewById(r1)     // Catch: org.json.JSONException -> L40
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0     // Catch: org.json.JSONException -> L40
            if (r6 == 0) goto L57
            r1 = -1
            int r2 = r6.hashCode()     // Catch: org.json.JSONException -> L40
            r3 = -1836394231(0xffffffff928ad909, float:-8.762528E-28)
            r4 = 0
            if (r2 == r3) goto L28
            goto L31
        L28:
            java.lang.String r2 = "Amazon Music"
            boolean r6 = r6.equals(r2)     // Catch: org.json.JSONException -> L40
            if (r6 == 0) goto L31
            r1 = r4
        L31:
            if (r1 == 0) goto L3a
            r0.setVisibility(r4)     // Catch: org.json.JSONException -> L40
            r5.updateSecondaryPlaybackControlButtons(r7)     // Catch: org.json.JSONException -> L40
            goto L57
        L3a:
            r6 = 8
            r0.setVisibility(r6)     // Catch: org.json.JSONException -> L40
            goto L57
        L40:
            r6 = move-exception
            java.lang.String r7 = com.amazon.alexa.drive.entertainment.view.NowPlayingScreenViewController.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Error parsing msp provider JSON"
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            android.util.Log.e(r7, r6)
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.drive.entertainment.view.NowPlayingScreenViewController.lambda$updateSecondaryPlaybackControls$12$NowPlayingScreenViewController(org.json.JSONObject, org.json.JSONObject):void");
    }

    void loadImageToBitmap(String str) {
        Log.i(TAG, "loadImageToBitmap ");
        if (str.equals(getCurrentNPSBgUrl())) {
            return;
        }
        this.mCurrentNPSBgUrl = str;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.mo1578diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(getView().getContext().getApplicationContext()).mo1635asBitmap().mo6762load(str).mo1615apply(requestOptions).mo1619listener(createBitmapRequestListener()).submit();
    }

    void loadImageToView(String str, ImageView imageView) {
        Log.i(TAG, "loadImageToView ");
        RequestOptions requestOptions = new RequestOptions();
        DrawableCrossFadeFactory build = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        requestOptions.mo1612transforms(new RoundedCorners((int) getView().getContext().getResources().getDimension(R.dimen.now_playing_screen_album_art_corner_radius)));
        getView().findViewById(R.id.nps_album_art_default).setVisibility(0);
        getView().findViewById(R.id.nps_album_art).setVisibility(4);
        getView().findViewById(R.id.nps_album_art_no_network).setVisibility(4);
        requestOptions.mo1578diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(getView().getContext().getApplicationContext()).mo6762load(str).mo1615apply(requestOptions).mo1619listener(createDrawableRequestListener()).mo1632transition(DrawableTransitionOptions.withCrossFade(build)).into(imageView);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        Log.i(TAG, "onCreateView");
        this.mHandler = new Handler();
        this.mProgressHandler = new Handler();
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(new ContextThemeWrapper(layoutInflater.getContext(), this.mDriveModeThemeManager.getTheme()));
        View inflate = cloneInContext.inflate(R.layout.view_controller_now_playing_screen, viewGroup, false);
        super.makeView(cloneInContext, viewGroup);
        ((ConstraintLayout) inflate.findViewById(R.id.nps_container)).setOnTouchListener(new NowPlayingScreenConstraintLayoutTouchListener(this));
        if (!this.mWeblabManager.isAutoMode_2_0_WeblabEnabled()) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone((ConstraintLayout) inflate.findViewById(R.id.nps_header));
            constraintSet.connect(inflate.findViewById(R.id.close_now_playing_screen_view).getId(), 6, 0, 6);
        }
        inflate.findViewById(R.id.close_now_playing_screen_view).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$Vf_2GII52KhDefqMEaIRvpPPANg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NowPlayingScreenViewController.this.lambda$makeView$0$NowPlayingScreenViewController(view);
            }
        });
        initNPSUI(inflate);
        return inflate;
    }

    public void navigateToPreviousScreen() {
        Log.i(TAG, "navigateToEntertainmentScreen");
        scaleOutView(getView().findViewById(R.id.nps_container));
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        Log.i(TAG, "onAttach");
        super.onAttach(view);
        this.mEntertainmentMetrics.logNPSDisplayed();
        scaleInView(view.findViewById(R.id.nps_container));
        initNetworkConnectionManager();
        toggleOverrideStatusAndNavBarBackground(true);
        this.mNowPlayingScreenPresenter.initialize(this);
        Log.i(TAG, "getCurrentPlayerInfo");
        if (this.mNowPlayingScreenPresenter.getCurrentPlayerInfo() != null) {
            updateNowPlayingScreen(this.mNowPlayingScreenPresenter.getCurrentPlayerInfo());
        }
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        super.onCreate(context);
        Log.i(TAG, "onCreate");
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        Log.i(TAG, "onDetach");
        super.onDetach(view);
        this.mEntertainmentMetrics.logNPSClosed(DriveModeMetricsHelper.getAutoModeType());
        this.mNowPlayingScreenPresenter.cleanUp();
        if (!getNetworkStatusSubscription().isUnsubscribed()) {
            getNetworkStatusSubscription().unsubscribe();
        }
        toggleOverrideStatusAndNavBarBackground(false);
        if (getProgressHandler() != null) {
            getProgressHandler().removeCallbacksAndMessages(null);
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.View
    public void onMediaError(final MediaErrorPayload mediaErrorPayload) {
        Log.i(TAG, "onMediaError");
        DriverDistractionLog.logError(LogConstants.NOW_PLAYING, LogConstants.MEDIA_ERROR);
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$KUmlLgLoVFeS_Jxf2BPT-3EB3cM
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$onMediaError$4$NowPlayingScreenViewController(mediaErrorPayload);
            }
        });
    }

    void resetNPSUI() {
        Log.i(TAG, "resetNPSUI");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$ZkYeOBqRP5DLqb5aO0ePhimE43o
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$resetNPSUI$25$NowPlayingScreenViewController();
            }
        });
    }

    void scaleInView(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(view.getResources().getInteger(R.integer.nps_scale_animation_alpha_start), view.getResources().getInteger(R.integer.nps_scale_animation_alpha_end));
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setStartOffset(view.getResources().getInteger(R.integer.nps_scale_animation_start_offset));
        alphaAnimation.setDuration(view.getResources().getInteger(R.integer.nps_scale_animation_duration));
        ScaleAnimation scaleAnimation = new ScaleAnimation(view.getResources().getFraction(R.fraction.nps_scale_animation_start, 1, 1), view.getResources().getFraction(R.fraction.nps_scale_animation_end, 1, 1), view.getResources().getFraction(R.fraction.nps_scale_animation_start, 1, 1), view.getResources().getFraction(R.fraction.nps_scale_animation_end, 1, 1), 1, view.getResources().getFraction(R.fraction.nps_scale_animation_pivot, 1, 1), 1, view.getResources().getFraction(R.fraction.nps_scale_animation_pivot, 1, 1));
        scaleAnimation.setInterpolator(getView().getContext(), R.anim.decelerate_interpolator);
        scaleAnimation.setStartOffset(view.getResources().getInteger(R.integer.nps_scale_animation_start_offset));
        scaleAnimation.setDuration(view.getResources().getInteger(R.integer.nps_scale_animation_duration));
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        view.startAnimation(animationSet);
    }

    void scaleOutView(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(view.getResources().getInteger(R.integer.nps_scale_animation_alpha_end), view.getResources().getInteger(R.integer.nps_scale_animation_alpha_start));
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setStartOffset(view.getResources().getInteger(R.integer.nps_scale_animation_start_offset));
        alphaAnimation.setDuration(view.getResources().getInteger(R.integer.nps_scale_animation_duration));
        ScaleAnimation scaleAnimation = new ScaleAnimation(view.getResources().getFraction(R.fraction.nps_scale_animation_end, 1, 1), view.getResources().getFraction(R.fraction.nps_scale_animation_start, 1, 1), view.getResources().getFraction(R.fraction.nps_scale_animation_end, 1, 1), view.getResources().getFraction(R.fraction.nps_scale_animation_start, 1, 1), 1, view.getResources().getFraction(R.fraction.nps_scale_animation_pivot, 1, 1), 1, view.getResources().getFraction(R.fraction.nps_scale_animation_pivot, 1, 1));
        scaleAnimation.setInterpolator(getView().getContext(), R.anim.accelerate_interpolator);
        scaleAnimation.setStartOffset(view.getResources().getInteger(R.integer.nps_scale_animation_start_offset));
        scaleAnimation.setDuration(view.getResources().getInteger(R.integer.nps_scale_animation_duration));
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        view.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.amazon.alexa.drive.entertainment.view.NowPlayingScreenViewController.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
                if (routingService != null) {
                    routingService.navigateBackward();
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        view.startAnimation(animationSet);
    }

    void startProgressUpdate() {
        if (getView() != null) {
            final ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.nps_progress_bar);
            String str = TAG;
            Log.i(str, "startProgressUpdate " + progressBar + " length " + progressBar.getMax() + " progress " + progressBar.getProgress());
            final int max = progressBar.getMax();
            getProgressHandler().removeCallbacksAndMessages(null);
            this.mProgressBarRunnable = new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.NowPlayingScreenViewController.1
                @Override // java.lang.Runnable
                public void run() {
                    Log.i(NowPlayingScreenViewController.TAG, "Progress bar runnable start");
                    if (progressBar.getVisibility() != 0 || progressBar.getProgress() > max || !NowPlayingScreenViewController.this.isMediaPlaying()) {
                        return;
                    }
                    String str2 = NowPlayingScreenViewController.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Updating progress ");
                    outline107.append(progressBar.getProgress());
                    Log.i(str2, outline107.toString());
                    progressBar.incrementProgressBy(1);
                    NowPlayingScreenViewController.this.getProgressHandler().postDelayed(this, TimeUnit.SECONDS.toMillis(1L));
                }
            };
        }
    }

    void toggleOverrideStatusAndNavBarBackground(boolean z) {
        Log.i(TAG, "toggleOverrideStatusAndNavBarBackground " + z);
        if (getView() != null) {
            Context context = getView().getContext();
            if (context instanceof ContextThemeWrapper) {
                context = ((ContextThemeWrapper) context).getBaseContext();
            }
            if (!(context instanceof Activity)) {
                return;
            }
            final Activity activity = (Activity) context;
            final View decorView = activity.getWindow().getDecorView();
            if (z) {
                ConstraintLayout constraintLayout = (ConstraintLayout) getView().findViewById(R.id.nps_header);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
                int statusBarHeight = getStatusBarHeight();
                GeneratedOutlineSupport1.outline149("statusBarHeight ", statusBarHeight);
                ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = statusBarHeight;
                constraintLayout.setLayoutParams(layoutParams);
                decorView.setSystemUiVisibility(1536);
                activity.getWindow().setStatusBarColor(0);
                activity.getWindow().setNavigationBarColor(0);
                LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.nps_playback_controls_container);
                ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();
                ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = ((int) context.getResources().getDimension(R.dimen.nps_playback_controls_container_mb)) + getSystemNavigationBarSize(context).y;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getSystemNavigationBarSize ");
                outline107.append(getSystemNavigationBarSize(context).y);
                outline107.toString();
                String str = "botomMargin | " + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin;
                linearLayout.setLayoutParams(layoutParams2);
                decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$a0IYK3x5lQy1bkIc4VMZATJP44s
                    @Override // android.view.View.OnSystemUiVisibilityChangeListener
                    public final void onSystemUiVisibilityChange(int i) {
                        NowPlayingScreenViewController.lambda$toggleOverrideStatusAndNavBarBackground$2(decorView, r2, activity, i);
                    }
                });
                return;
            }
            decorView.setOnSystemUiVisibilityChangeListener(null);
            this.mDriveModeThemeManager.setSystemBarTheme(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: togglePlayPause */
    public void lambda$updateNowPlayingPlaybackState$3$NowPlayingScreenViewController(boolean z) {
        String str = TAG;
        Log.i(str, "togglePlayPause " + z + " isNetworkConnected " + this.mNetworkConnectivityManager.isNetworkConnected());
        if (getView() != null) {
            View view = getView();
            Button button = (Button) view.findViewById(R.id.play_button);
            Button button2 = (Button) view.findViewById(R.id.pause_button);
            getProgressHandler().removeCallbacksAndMessages(null);
            if (z) {
                getProgressHandler().post(this.mProgressBarRunnable);
                Log.i(TAG, "togglePlayPause setting pause button visible");
                if (button2.getVisibility() == 0) {
                    return;
                }
                button2.setVisibility(0);
                button2.setEnabled(this.mNetworkConnectivityManager.isNetworkConnected());
                button.setEnabled(false);
                button.setVisibility(8);
                return;
            }
            Log.i(TAG, "togglePlayPause setting play button visible");
            if (button.getVisibility() == 0) {
                return;
            }
            button2.setEnabled(false);
            button2.setVisibility(4);
            button.setEnabled(this.mNetworkConnectivityManager.isNetworkConnected());
            button.setVisibility(0);
        }
    }

    void updateAlbumArt(final JSONObject jSONObject) {
        Log.i(TAG, "updateAlbumArt");
        loadNPSBackground(jSONObject);
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$svo5q4w-iIwcaaSXRNx54mF0ZOg
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$updateAlbumArt$6$NowPlayingScreenViewController(jSONObject);
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    void updateButtonState(String str, Button button) {
        char c;
        Log.i(TAG, "updateButtonState " + str + " : " + button);
        switch (str.hashCode()) {
            case -891611359:
                if (str.equals("ENABLED")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1053567612:
                if (str.equals("DISABLED")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1974198939:
                if (str.equals("SELECTED")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2130809258:
                if (str.equals("HIDDEN")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            button.setVisibility(4);
            button.setEnabled(false);
            button.setSelected(false);
        } else if (c == 1) {
            button.setVisibility(0);
            button.setEnabled(false);
            button.setSelected(false);
        } else if (c == 2) {
            button.setVisibility(0);
            button.setEnabled(true);
            button.setSelected(false);
        } else if (c != 3) {
            Log.e(TAG, "Unknown button state");
            button.setVisibility(4);
            button.setEnabled(false);
            button.setSelected(false);
        } else {
            button.setVisibility(0);
            if (button.getId() != R.id.like_button && button.getId() != R.id.dislike_button) {
                button.setEnabled(true);
            } else {
                button.setEnabled(false);
            }
            button.setSelected(true);
        }
    }

    void updateMSPLogo(final JSONObject jSONObject) {
        Log.i(TAG, "updateMSPLogo ");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$xWLB_RIa8-GuH02fjukhk-iKBuU
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$updateMSPLogo$5$NowPlayingScreenViewController(jSONObject);
            }
        });
    }

    void updateMediaText(final JSONObject jSONObject, final String str) {
        Log.i(TAG, "updateMediaText");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$n6uyUYZV6JYI7WDj3O_9pg2vd_M
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$updateMediaText$9$NowPlayingScreenViewController(jSONObject, str);
            }
        });
    }

    void updateNPSBackground(final Bitmap bitmap) {
        String str = TAG;
        Log.i(str, "updateNPSBackground" + bitmap);
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$yjuQvsu1R6Qog8DJb-_bnp753Co
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$updateNPSBackground$8$NowPlayingScreenViewController(bitmap);
            }
        });
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.View
    public void updateNowPlayingPlaybackState(final boolean z) {
        GeneratedOutlineSupport1.outline173("updateNowPlayingPlaybackState ", z, TAG);
        this.mIsMediaPlaying = z;
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$XvOYQhZXHAgbUP-5GpaWYT6Qz1w
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$updateNowPlayingPlaybackState$3$NowPlayingScreenViewController(z);
            }
        });
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.View
    public void updateNowPlayingScreen(JSONObject jSONObject) {
        String str = "";
        Log.i(TAG, "updateNowPlayingScreen");
        resetNPSUI();
        if (jSONObject != null) {
            try {
                if (!jSONObject.optString("mediaId", str).isEmpty() && !jSONObject.optString("mediaId", str).equals("null")) {
                    if (jSONObject.optJSONObject("transport") != null) {
                        if (wasNPSLaunchedWithDefaultData()) {
                            this.mWasNPSLaunchedWithDefaultData = false;
                            this.mTTCFRecordOnce.markComplete(TTCFRecordOnce.ENTERTAINMENT_NOWPLAYING_COLD);
                        } else {
                            this.mTTCFRecordOnce.markComplete(TTCFRecordOnce.ENTERTAINMENT_NOWPLAYING_WARM);
                        }
                    } else {
                        this.mWasNPSLaunchedWithDefaultData = true;
                    }
                    if (jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MAIN_ART) != null) {
                        updateAlbumArt(jSONObject.getJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MAIN_ART));
                    } else if (jSONObject.optJSONObject("template") != null) {
                        updateAlbumArt(jSONObject.getJSONObject("template").optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_ART));
                    }
                    updateMSPLogo(jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER));
                    if (jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT) == null && jSONObject.optJSONObject("template") != null) {
                        str = jSONObject.optJSONObject("template").optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CARD).optString("title", str);
                    }
                    updateMediaText(jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT), str);
                    updateProgressBar(jSONObject.optJSONObject("progress"));
                    updatePrimaryPlaybackControls(jSONObject.optJSONObject("transport"));
                    updateSecondaryPlaybackControls(jSONObject.optJSONObject("transport"), jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER));
                    updatePlaybackState(jSONObject.getString("state"));
                    return;
                }
            } catch (JSONException e) {
                String str2 = TAG;
                Log.e(str2, "Error parsing JSON " + e);
                return;
            }
        }
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$I--WdWMMLrL7prYZgMON01_cxZo
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.navigateToPreviousScreen();
            }
        });
    }

    void updatePlaybackState(final String str) {
        GeneratedOutlineSupport1.outline163("updatePlaybackState ", str, TAG);
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$I2hlf7VZSllBKzDFKPvqlxHZIH0
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$updatePlaybackState$14$NowPlayingScreenViewController(str);
            }
        });
    }

    void updatePrimaryPlaybackControls(final JSONObject jSONObject) {
        Log.i(TAG, "updatePrimaryPlaybackControls");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$jYKrVQ9Oyv4YdDZRIPgEivjDO-w
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$updatePrimaryPlaybackControls$11$NowPlayingScreenViewController(jSONObject);
            }
        });
    }

    void updateProgressBar(final JSONObject jSONObject) {
        Log.i(TAG, "updateProgressBar");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$4iGslbr_-rM3a_eIRUhFYt2zhA0
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$updateProgressBar$10$NowPlayingScreenViewController(jSONObject);
            }
        });
    }

    void updateSecondaryPlaybackControlButtons(final JSONObject jSONObject) {
        Log.i(TAG, "updateSecondaryPlaybackControlButtons ");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$qQnpHs2xzUB8WWhVJAG8YxHCEFQ
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$updateSecondaryPlaybackControlButtons$13$NowPlayingScreenViewController(jSONObject);
            }
        });
    }

    void updateSecondaryPlaybackControls(final JSONObject jSONObject, final JSONObject jSONObject2) {
        Log.i(TAG, "updateSecondaryPlaybackControls ");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$NowPlayingScreenViewController$VB_aGMTAiMGo2cUxmg4BKVJ8HV8
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingScreenViewController.this.lambda$updateSecondaryPlaybackControls$12$NowPlayingScreenViewController(jSONObject2, jSONObject);
            }
        });
    }

    boolean wasNPSLaunchedWithDefaultData() {
        return this.mWasNPSLaunchedWithDefaultData;
    }
}
