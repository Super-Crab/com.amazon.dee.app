package com.amazon.alexa.drive.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.amazon.alexa.drive.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.Locale;
/* loaded from: classes7.dex */
public final class DriveModeAnimationUtils {
    private static final String TAG = "DriveModeAnimationUtils";

    private DriveModeAnimationUtils() {
    }

    public static void attachFooterIndicator(BottomNavigationView bottomNavigationView, ContextThemeWrapper contextThemeWrapper) {
        LayoutInflater.from(contextThemeWrapper).inflate(R.layout.indicator_view, (ViewGroup) bottomNavigationView, true);
        int menuItemWidth = getMenuItemWidth(bottomNavigationView);
        ((ViewGroup.MarginLayoutParams) ((AppCompatImageView) bottomNavigationView.findViewById(R.id.indicator_image)).getLayoutParams()).setMarginStart((menuItemWidth - ((int) contextThemeWrapper.getResources().getDimension(R.dimen.dm_bottom_navigation_icon_size))) / 2);
    }

    private static void clearExistingAnimation(View view) {
        Animation animation = view.getAnimation();
        if (animation == null || animation.hasEnded()) {
            return;
        }
        view.clearAnimation();
    }

    private static ImageView getMenuImageView(BottomNavigationView bottomNavigationView, int i) {
        return (ImageView) ((BottomNavigationItemView) ((BottomNavigationMenuView) bottomNavigationView.getChildAt(0)).findViewById(i)).findViewById(com.google.android.material.R.id.icon);
    }

    private static int getMenuItemWidth(BottomNavigationView bottomNavigationView) {
        DisplayMetrics displayMetrics = bottomNavigationView.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels / bottomNavigationView.getMenu().size();
    }

    public static AnimatorSet loadLottieAndTitle(final LottieAnimationView lottieAnimationView, final View view) {
        if (lottieAnimationView == null || view == null) {
            return null;
        }
        clearExistingAnimation(lottieAnimationView);
        clearExistingAnimation(view);
        lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.drive.util.DriveModeAnimationUtils.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator, boolean z) {
                LottieAnimationView.this.setVisibility(8);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator, boolean z) {
            }
        });
        lottieAnimationView.setVisibility(0);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(AnimationConstants.LOTTIE_DURATION_MS).setStartDelay(166L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.amazon.alexa.drive.util.-$$Lambda$DriveModeAnimationUtils$LvrY0p-4jgIEQcsE99XQnPN_xdE
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                LottieAnimationView.this.setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        view.setAlpha(0.0f);
        view.setVisibility(0);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ofFloat2.setDuration(150L).setInterpolator(new LinearInterpolator());
        ofFloat2.setStartDelay(AnimationConstants.FIRST_TIME_TITLE_DELAY_MS);
        ofFloat2.addListener(new Animator.AnimatorListener() { // from class: com.amazon.alexa.drive.util.DriveModeAnimationUtils.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setTag(AnimationConstants.LOTTIE_ANIMATION_DONE);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat2, ofFloat);
        animatorSet.start();
        return animatorSet;
    }

    public static void loadingPulseAnimation(View view) {
        String str = TAG;
        Log.i(str, "loadingPulseAnimation " + view);
        if (view == null || view.getContext() == null) {
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(view.getResources().getFraction(R.fraction.entertainment_list_load_animation_start_alpha, 1, 1), view.getResources().getFraction(R.fraction.entertainment_list_load_animation_end_alpha, 1, 1));
        alphaAnimation.setDuration(view.getResources().getInteger(R.integer.entertainment_list_load_animation_duration));
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setStartOffset(view.getResources().getInteger(R.integer.nps_scale_animation_start_offset));
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(2);
        view.startAnimation(alphaAnimation);
    }

    public static void performDomainAnimation(final View view, ContextThemeWrapper contextThemeWrapper) {
        if (view == null || view.getContext() == null) {
            return;
        }
        clearExistingAnimation(view);
        Context context = view.getContext();
        float f = context.getResources() != null ? context.getResources().getDisplayMetrics().widthPixels * 0.06f : 0.0f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", f, 0.0f);
        ofFloat.setDuration(366L).setStartDelay(166L);
        ofFloat.setInterpolator(AnimationUtils.loadInterpolator(context, R.anim.decelerate_interpolator));
        view.setAlpha(0.0f);
        view.setVisibility(0);
        view.setTranslationY(f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ofFloat2.setDuration(366L).setInterpolator(new LinearInterpolator());
        TypedValue typedValue = new TypedValue();
        contextThemeWrapper.getTheme().resolveAttribute(R.attr.mosaicBackground, typedValue, true);
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(context.getResources().getColor(R.color.DriveMode_Background_Transparent)), Integer.valueOf(typedValue.data));
        ofObject.setInterpolator(new LinearInterpolator());
        ofObject.setDuration(166L);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.amazon.alexa.drive.util.-$$Lambda$DriveModeAnimationUtils$6m0w7opWIi1lDr0bO8X_HzE0GNM
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofObject, ofFloat2, ofFloat);
        animatorSet.start();
    }

    public static AnimatorSet performTabSelectedAnimation(ContextThemeWrapper contextThemeWrapper, BottomNavigationView bottomNavigationView, @NonNull MenuItem menuItem, AnimatorSet animatorSet) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("performTabSelectedAnimation ");
        outline107.append(menuItem.getItemId());
        Log.i(str, outline107.toString());
        if (animatorSet != null && animatorSet.isRunning()) {
            Log.i(TAG, "Cancelling existing animation");
            animatorSet.end();
        }
        int selectedItemId = bottomNavigationView.getSelectedItemId();
        Menu menu = bottomNavigationView.getMenu();
        int i = 0;
        while (true) {
            if (i >= menu.size()) {
                i = 0;
                break;
            } else if (menu.getItem(i).equals(menuItem)) {
                break;
            } else {
                i++;
            }
        }
        boolean z = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        AppCompatImageView appCompatImageView = (AppCompatImageView) bottomNavigationView.findViewById(R.id.indicator_image);
        Interpolator loadInterpolator = AnimationUtils.loadInterpolator(contextThemeWrapper, R.anim.bottom_bar_animation);
        float[] fArr = new float[1];
        fArr[0] = i * getMenuItemWidth(bottomNavigationView) * (z ? -1 : 1);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(appCompatImageView, "translationX", fArr);
        ofFloat.setInterpolator(loadInterpolator);
        ofFloat.setDuration(333L);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = contextThemeWrapper.getTheme();
        theme.resolveAttribute(R.attr.mosaicAction40, typedValue, true);
        int i2 = typedValue.data;
        theme.resolveAttribute(R.attr.mosaicIcon90, typedValue, true);
        int i3 = typedValue.data;
        ObjectAnimator duration = ObjectAnimator.ofObject(getMenuImageView(bottomNavigationView, selectedItemId), "colorFilter", new ArgbEvaluator(), Integer.valueOf(i2), Integer.valueOf(i3)).setDuration(133L);
        ObjectAnimator duration2 = ObjectAnimator.ofObject(getMenuImageView(bottomNavigationView, menuItem.getItemId()), "colorFilter", new ArgbEvaluator(), Integer.valueOf(i3), Integer.valueOf(i2)).setDuration(333L);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(ofFloat, duration2, duration);
        animatorSet2.start();
        menuItem.setChecked(true);
        return animatorSet2;
    }

    public static void titleFadeInAnimation(View view) {
        if (view == null || view.getTag() == null) {
            return;
        }
        clearExistingAnimation(view);
        view.setAlpha(0.0f);
        view.setVisibility(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ofFloat.setDuration(333L).setInterpolator(new LinearInterpolator());
        ofFloat.setStartDelay(166L);
        ofFloat.start();
    }
}
