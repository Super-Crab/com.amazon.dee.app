package com.amazon.alexa.voice.ui.onedesign.sports.scores;

import androidx.annotation.ColorRes;
import java.util.List;
/* loaded from: classes11.dex */
public interface SportsScoresContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void dismiss();

        SportsScoresCard getCard();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dismiss();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void dismiss();

        void interacted();

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void setAwayTeamLogo(String str);

        void setAwayTeamNickName(CharSequence charSequence);

        void setAwayTeamPoints(CharSequence charSequence, boolean z);

        void setAwayTeamScoreComponents(List<CharSequence> list);

        void setAwayTeamShortName(CharSequence charSequence);

        void setGradientBackgroundColor(@ColorRes int i);

        void setGradientBackgroundColorAndDrawable(@ColorRes int i, int i2);

        void setHomeTeamLogo(String str);

        void setHomeTeamNickName(CharSequence charSequence);

        void setHomeTeamPoints(CharSequence charSequence, boolean z);

        void setHomeTeamScoreComponents(List<CharSequence> list);

        void setHomeTeamShortName(CharSequence charSequence);

        void setNextGameDate(CharSequence charSequence);

        void setNextGameTimeAndOpponent(CharSequence charSequence, boolean z, CharSequence charSequence2);

        void setNextGameTitle(CharSequence charSequence);

        void setScoreComponentsHeadings(List<CharSequence> list);

        void setStatusBarColor(@ColorRes int i);

        void setSubTitle(CharSequence charSequence);

        void setTitle(CharSequence charSequence);
    }
}
