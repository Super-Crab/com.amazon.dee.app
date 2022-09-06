package com.amazon.dee.app.ui.main;

import android.animation.Animator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Observable;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.dee.app.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes12.dex */
public class TabSelectionAnimator {
    static final String TAG = "TabSelectionAnimator";
    @VisibleForTesting
    Animator.AnimatorListener animatorListener;
    private final Context context;
    @VisibleForTesting
    Observable.OnPropertyChangedCallback currentTabChangedCallback;
    private final Lazy<EventBus> eventBus;
    private final Lazy<IdentityService> identityService;
    @VisibleForTesting
    MessageHandler messageHandler;
    @VisibleForTesting
    BroadcastReceiver receiver;
    @VisibleForTesting
    final int speed = 300;
    private Subscriber.SubscriberUuid subscriberUuid;
    @VisibleForTesting
    int tabCount;
    private LinearLayout tabLayout;
    private View tabLine;
    @VisibleForTesting
    int tabLineWidth;
    @VisibleForTesting
    int tabWidth;
    @VisibleForTesting
    ViewTreeObserver.OnGlobalLayoutListener viewChangeListener;
    private MainViewModel viewModel;

    public TabSelectionAnimator(Context context, Lazy<EventBus> lazy, Lazy<IdentityService> lazy2) {
        this.context = context;
        this.eventBus = lazy;
        this.identityService = lazy2;
    }

    private void registerTabCountChangeListeners() {
        this.viewChangeListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.dee.app.ui.main.TabSelectionAnimator.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                TabSelectionAnimator.this.updateTabCount();
                TabSelectionAnimator tabSelectionAnimator = TabSelectionAnimator.this;
                if (tabSelectionAnimator.tabCount > 1) {
                    tabSelectionAnimator.calculateAndAdjustPositions();
                    TabSelectionAnimator.this.tabLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        };
        if (this.identityService.mo358get().getUser(TAG) != null) {
            this.tabLayout.getViewTreeObserver().addOnGlobalLayoutListener(this.viewChangeListener);
        }
        MultiFilterSubscriber subscriber = this.eventBus.mo358get().getSubscriber();
        this.subscriberUuid = subscriber.getSubscriberUuid();
        this.messageHandler = new MessageHandler() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$TabSelectionAnimator$hR0GrCGBgfjcLYaje2iigd6-Qjw
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                TabSelectionAnimator.this.lambda$registerTabCountChangeListeners$0$TabSelectionAnimator(message);
            }
        };
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), this.messageHandler);
    }

    private void registerUserEventChangeListeners() {
        IntentFilter outline10 = GeneratedOutlineSupport1.outline10("android.intent.action.CONFIGURATION_CHANGED");
        if (this.receiver == null) {
            this.receiver = new BroadcastReceiver() { // from class: com.amazon.dee.app.ui.main.TabSelectionAnimator.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    TabSelectionAnimator.this.calculateAndAdjustPositions();
                }
            };
        }
        this.context.registerReceiver(this.receiver, outline10);
        if (this.currentTabChangedCallback == null) {
            this.currentTabChangedCallback = new Observable.OnPropertyChangedCallback() { // from class: com.amazon.dee.app.ui.main.TabSelectionAnimator.2
                @Override // androidx.databinding.Observable.OnPropertyChangedCallback
                public void onPropertyChanged(Observable observable, int i) {
                    TabSelectionAnimator tabSelectionAnimator = TabSelectionAnimator.this;
                    tabSelectionAnimator.moveTo(tabSelectionAnimator.viewModel.currentTab.get());
                }
            };
        }
        this.viewModel.currentTab.addOnPropertyChangedCallback(this.currentTabChangedCallback);
    }

    @VisibleForTesting
    void calculateAndAdjustPositions() {
        if (this.tabCount < 1) {
            return;
        }
        this.tabWidth = this.context.getResources().getDisplayMetrics().widthPixels / this.tabCount;
        this.tabLineWidth = this.context.getResources().getDimensionPixelSize(R.dimen.footer_selected_line_width);
        moveWithoutAnimation(getNewPosition(this.viewModel.currentTab.get()));
    }

    public void deregister() {
        BroadcastReceiver broadcastReceiver = this.receiver;
        if (broadcastReceiver != null) {
            this.context.unregisterReceiver(broadcastReceiver);
        }
        Observable.OnPropertyChangedCallback onPropertyChangedCallback = this.currentTabChangedCallback;
        if (onPropertyChangedCallback != null) {
            this.viewModel.currentTab.removeOnPropertyChangedCallback(onPropertyChangedCallback);
        }
        if (this.subscriberUuid != null) {
            this.eventBus.mo358get().unsubscribe(this.subscriberUuid);
        }
    }

    @VisibleForTesting
    int getNewPosition(int i) {
        if (i == 0) {
            return (int) this.tabLine.getX();
        }
        if (this.context.getResources().getConfiguration().getLayoutDirection() == 1) {
            i = (this.tabCount - i) + 1;
        }
        int i2 = this.tabWidth;
        return ((i2 / 2) + ((i - 1) * i2)) - (this.tabLineWidth / 2);
    }

    public void initialize(View view, LinearLayout linearLayout, MainViewModel mainViewModel) {
        this.tabLine = view;
        this.tabLayout = linearLayout;
        this.viewModel = mainViewModel;
        registerTabCountChangeListeners();
        registerUserEventChangeListeners();
    }

    public /* synthetic */ void lambda$registerTabCountChangeListeners$0$TabSelectionAnimator(Message message) {
        if (this.identityService.mo358get().getUser(TAG) != null) {
            this.tabLayout.getViewTreeObserver().addOnGlobalLayoutListener(this.viewChangeListener);
        }
    }

    @VisibleForTesting
    void moveTo(int i) {
        int newPosition = getNewPosition(i);
        if (newPosition == ((int) this.tabLine.getX()) || i > this.tabCount) {
            return;
        }
        if (this.tabLine.getVisibility() == 4) {
            moveWithoutAnimation(newPosition);
        } else {
            moveWithAnimation(newPosition);
        }
    }

    @VisibleForTesting
    void moveWithAnimation(int i) {
        this.viewModel.tabAnimationFinished.set(false);
        if (this.animatorListener == null) {
            this.animatorListener = new Animator.AnimatorListener() { // from class: com.amazon.dee.app.ui.main.TabSelectionAnimator.4
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    TabSelectionAnimator.this.tabLine.setLayerType(0, null);
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    TabSelectionAnimator.this.viewModel.tabAnimationFinished.set(true);
                    TabSelectionAnimator.this.tabLine.setLayerType(0, null);
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    TabSelectionAnimator.this.tabLine.setLayerType(2, null);
                }
            };
        }
        this.tabLine.animate().translationX(i).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(300L).setListener(this.animatorListener);
    }

    public void moveWithoutAnimation(int i) {
        this.tabLine.setX(i);
    }

    @VisibleForTesting
    void updateTabCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.tabLayout.getChildCount(); i2++) {
            if (this.tabLayout.getChildAt(i2).getVisibility() == 0) {
                i++;
            }
        }
        this.tabCount = i;
    }
}
