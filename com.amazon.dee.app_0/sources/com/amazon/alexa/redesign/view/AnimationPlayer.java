package com.amazon.alexa.redesign.view;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.utils.Constants;
import java.util.Objects;
/* loaded from: classes10.dex */
public class AnimationPlayer {
    private final Animation fadeIn;
    private final Animation slideUp;

    public AnimationPlayer(Context context) {
        this.fadeIn = AnimationUtils.loadAnimation(context, R.anim.amahc_contents_fade_in);
        this.slideUp = AnimationUtils.loadAnimation(context, R.anim.amahc_contents_slide_up);
    }

    private void prepareAnimations() {
        this.fadeIn.cancel();
        this.fadeIn.reset();
        this.slideUp.cancel();
        this.slideUp.reset();
    }

    private void selectAnimation(boolean z, String str, View view) {
        if (shouldContentFadeIn(z, str)) {
            view.startAnimation(this.fadeIn);
        }
        if (shouldContentSlideUp(z, str)) {
            view.startAnimation(this.slideUp);
        }
    }

    private boolean shouldContentFadeIn(boolean z, String str) {
        return z && Objects.equals(str, Constants.VIEW_UPDATE_TYPE_CACHE);
    }

    private boolean shouldContentSlideUp(boolean z, String str) {
        return Objects.equals(str, Constants.VIEW_UPDATE_TYPE_PULLREFRESH) || Objects.equals(str, Constants.VIEW_UPDATE_TYPE_REFRESH_BUTTON) || (Objects.equals(str, Constants.VIEW_UPDATE_TYPE_CACHE) && !z);
    }

    public void playAnimation(CardModel cardModel, View view) {
        prepareAnimations();
        if (cardModel != null) {
            selectAnimation(cardModel.isColdStart(), cardModel.getViewUpdateType(), view);
        }
    }
}
