package com.amazon.deecomms.calling.incallexperiences.reactions.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectsDataStore;
import com.amazon.deecomms.calling.ui.GlideImageLoader;
import com.amazon.deecomms.common.util.AnimUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public class ReactionsMenuButtonFragment extends Fragment {
    private static final int FADING_ANIMATION_TIME = 300;
    private static final CommsLogger LOG = CommsLogger.getLogger(ReactionsMenuButtonFragment.class);
    private LottieAnimationView closeAnimation;
    private ImageView closeBackground;
    private Context context;
    private final Animation fadingAnimationIn;
    private final Animation fadingAnimationOut;
    private final Handler handler;
    private LottieAnimationView reactionAnimationIn;
    private LottieAnimationView reactionAnimationOut;
    private LinearLayout reactionsMenuLayout;
    private ReactionsMenuPresenter reactionsMenuPresenter;
    private LinearLayout reactionsMenuWrapper;
    private RecyclerView reactionsRecyclerView;
    private final Handler uiHandler;

    public ReactionsMenuButtonFragment(Context context) {
        this.handler = new Handler();
        this.context = context;
        this.fadingAnimationIn = AnimUtils.getFadingAnimation(300, true);
        this.fadingAnimationOut = AnimUtils.getFadingAnimation(300, false);
        this.uiHandler = new Handler(Looper.getMainLooper());
    }

    public void disableMenu() {
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$D1FsaG-IHRdqvdSAL_jwbK6AEd0
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuButtonFragment.this.lambda$disableMenu$8$ReactionsMenuButtonFragment();
            }
        });
    }

    public void enableMenu() {
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$zKDnLIDqXzk2-Qw52kHQAy-Mx-w
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuButtonFragment.this.lambda$enableMenu$7$ReactionsMenuButtonFragment();
            }
        });
    }

    public void hideButton() {
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$xqFEik4WEVfGO-y1fXWnPP9HvPc
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuButtonFragment.this.lambda$hideButton$4$ReactionsMenuButtonFragment();
            }
        });
    }

    public void hideMenu() {
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$I6_SSuJA3BvGW6uzWYxkqWUeKtA
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuButtonFragment.this.lambda$hideMenu$6$ReactionsMenuButtonFragment();
            }
        });
    }

    public void initialize(LinearLayout linearLayout) {
        this.reactionsMenuWrapper = linearLayout;
        initializeReactionsUIWidgets();
        setonClickListenersAndViewProperties();
    }

    void initializeReactionsRecyclerView() {
        this.reactionsRecyclerView.setHasFixedSize(true);
        this.reactionsRecyclerView.setLayoutManager(new LinearLayoutManager(this.context, 0, false));
        this.reactionsRecyclerView.setAdapter(new ReactionsRecyclerViewAdapter(this.reactionsMenuPresenter, new GlideImageLoader(), (EffectsDataStore.getReactionsInstance() == null || EffectsDataStore.getReactionsInstance().getEffectData() == null || EffectsDataStore.getReactionsInstance().getEffectData().getEffectIcons() == null) ? new ArrayList<>() : EffectsDataStore.getReactionsInstance().getEffectData().getEffectIcons()));
    }

    void initializeReactionsUIWidgets() {
        this.reactionsMenuLayout = (LinearLayout) this.reactionsMenuWrapper.findViewById(R.id.reactions_layout_menu_button);
        this.reactionAnimationOut = (LottieAnimationView) this.reactionsMenuWrapper.findViewById(R.id.reaction_icon_out);
        this.reactionAnimationOut.pauseAnimation();
        this.closeBackground = (ImageView) this.reactionsMenuLayout.findViewById(R.id.close_background);
        this.closeAnimation = (LottieAnimationView) this.reactionsMenuLayout.findViewById(R.id.x_btn_in);
        this.reactionAnimationIn = (LottieAnimationView) this.reactionsMenuLayout.findViewById(R.id.reaction_icon_in);
        this.reactionsRecyclerView = (RecyclerView) this.reactionsMenuLayout.findViewById(R.id.reactions_recycler_view);
    }

    public /* synthetic */ void lambda$disableMenu$8$ReactionsMenuButtonFragment() {
        this.reactionsMenuLayout.setAlpha(0.5f);
        this.reactionsMenuLayout.setEnabled(false);
    }

    public /* synthetic */ void lambda$enableMenu$7$ReactionsMenuButtonFragment() {
        this.reactionsMenuLayout.setAlpha(1.0f);
        this.reactionsMenuLayout.setEnabled(true);
    }

    public /* synthetic */ void lambda$hideButton$4$ReactionsMenuButtonFragment() {
        this.reactionsMenuWrapper.setVisibility(8);
        this.reactionsMenuLayout.setAnimation(this.fadingAnimationOut);
        this.reactionsMenuLayout.setVisibility(8);
    }

    public /* synthetic */ void lambda$hideMenu$6$ReactionsMenuButtonFragment() {
        this.closeAnimation.setVisibility(4);
        this.closeBackground.setVisibility(4);
        this.reactionsRecyclerView.setVisibility(4);
        this.reactionAnimationIn.setVisibility(0);
        this.reactionAnimationIn.playAnimation();
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$2Zj1E_spd9LaJ4Q0618OHSECixI
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuButtonFragment.this.lambda$null$5$ReactionsMenuButtonFragment();
            }
        }, 1000L);
    }

    public /* synthetic */ void lambda$null$2$ReactionsMenuButtonFragment() {
        this.reactionAnimationOut.setVisibility(4);
        this.reactionAnimationOut.setProgress(0.0f);
        initializeReactionsRecyclerView();
        this.reactionsRecyclerView.setVisibility(0);
        this.closeAnimation.setVisibility(0);
        this.closeAnimation.playAnimation();
        this.closeBackground.setVisibility(0);
    }

    public /* synthetic */ void lambda$null$5$ReactionsMenuButtonFragment() {
        this.reactionAnimationIn.setVisibility(4);
        this.reactionAnimationOut.setVisibility(0);
        this.reactionAnimationOut.pauseAnimation();
    }

    public /* synthetic */ void lambda$refreshMenu$0$ReactionsMenuButtonFragment() {
        RecyclerView recyclerView = this.reactionsRecyclerView;
        if (recyclerView == null || recyclerView.getAdapter() == null) {
            return;
        }
        this.reactionsRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$setonClickListenersAndViewProperties$10$ReactionsMenuButtonFragment(View view) {
        LOG.i("Reaction Close Button Pressed: Reactions Menu Closed");
        hideMenu();
    }

    public /* synthetic */ void lambda$setonClickListenersAndViewProperties$9$ReactionsMenuButtonFragment(View view) {
        LOG.i("Reaction Menu Button Pressed: Reactions Menu Toggle");
        this.reactionsMenuPresenter.onMenuButtonTapped();
    }

    public /* synthetic */ void lambda$showButton$1$ReactionsMenuButtonFragment() {
        this.reactionsMenuWrapper.setVisibility(0);
        this.reactionsMenuLayout.setBackgroundTintList(null);
        this.reactionsMenuLayout.setAnimation(this.fadingAnimationIn);
        this.reactionsMenuLayout.setVisibility(0);
    }

    public /* synthetic */ void lambda$showMenu$3$ReactionsMenuButtonFragment() {
        this.reactionAnimationOut.setVisibility(0);
        this.reactionAnimationOut.playAnimation();
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$TocNbmZYJgqp8L5SvMHRbrRW2u0
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuButtonFragment.this.lambda$null$2$ReactionsMenuButtonFragment();
            }
        }, 700L);
        int i = R.anim.recyclerview_layout_animation;
        this.context = this.reactionsRecyclerView.getContext();
        this.reactionsRecyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this.context, i));
    }

    public void refreshMenu() {
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$Brzm1IbAlybBh0FY-BLTcLFWDec
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuButtonFragment.this.lambda$refreshMenu$0$ReactionsMenuButtonFragment();
            }
        });
    }

    public void setPresenter(ReactionsMenuPresenter reactionsMenuPresenter) {
        this.reactionsMenuPresenter = reactionsMenuPresenter;
    }

    void setonClickListenersAndViewProperties() {
        this.reactionAnimationOut.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$ldI-HZHqNjd5-f4MaoddnXcVNoo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReactionsMenuButtonFragment.this.lambda$setonClickListenersAndViewProperties$9$ReactionsMenuButtonFragment(view);
            }
        });
        this.closeAnimation.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$BBVZRcKQouqa0bteffO83QJs1DE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReactionsMenuButtonFragment.this.lambda$setonClickListenersAndViewProperties$10$ReactionsMenuButtonFragment(view);
            }
        });
    }

    public void showButton() {
        this.reactionsMenuPresenter.fetchScribeSetting();
        if (this.reactionsMenuPresenter.shouldShowMenuButton()) {
            this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$j-iVq2-GqsAUOIPM2YQ7wPdHD0g
                @Override // java.lang.Runnable
                public final void run() {
                    ReactionsMenuButtonFragment.this.lambda$showButton$1$ReactionsMenuButtonFragment();
                }
            });
        }
    }

    public void showMenu() {
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.reactions.ui.-$$Lambda$ReactionsMenuButtonFragment$TPM_Oe8rLXwAjSPWCyHiqoL9U1o
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuButtonFragment.this.lambda$showMenu$3$ReactionsMenuButtonFragment();
            }
        });
    }

    public void showToast(Context context, int i) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(context.getString(i));
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.fiesta_effects_message_toast, (ViewGroup) null);
        ((TextView) linearLayout.getChildAt(0)).setText(outline107);
        Toast toast = new Toast(context);
        toast.setView(linearLayout);
        toast.setGravity(49, 0, 0);
        toast.setDuration(1);
        toast.show();
    }

    @VisibleForTesting
    ReactionsMenuButtonFragment(LinearLayout linearLayout, LottieAnimationView lottieAnimationView, ImageView imageView, LottieAnimationView lottieAnimationView2, LottieAnimationView lottieAnimationView3, RecyclerView recyclerView, LinearLayout linearLayout2, Animation animation, Animation animation2, Handler handler) {
        this.handler = new Handler();
        this.reactionsMenuLayout = linearLayout;
        this.reactionAnimationOut = lottieAnimationView;
        this.closeBackground = imageView;
        this.closeAnimation = lottieAnimationView2;
        this.reactionAnimationIn = lottieAnimationView3;
        this.reactionsRecyclerView = recyclerView;
        this.reactionsMenuWrapper = linearLayout2;
        this.fadingAnimationIn = animation;
        this.fadingAnimationOut = animation2;
        this.uiHandler = handler;
    }

    public void showToast(Context context, String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.fiesta_effects_message_toast, (ViewGroup) null);
        ((TextView) linearLayout.getChildAt(0)).setText(outline107);
        Toast toast = new Toast(context);
        toast.setView(linearLayout);
        toast.setGravity(49, 0, 0);
        toast.setDuration(1);
        toast.show();
    }
}
