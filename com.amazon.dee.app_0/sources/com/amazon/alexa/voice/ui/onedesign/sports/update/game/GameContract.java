package com.amazon.alexa.voice.ui.onedesign.sports.update.game;
/* loaded from: classes11.dex */
public interface GameContract {

    /* loaded from: classes11.dex */
    public interface Presenter {
        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void setFirstTeamLogoUrl(String str);

        void setFirstTeamName(CharSequence charSequence);

        void setFirstTeamScore(CharSequence charSequence);

        void setHeading(CharSequence charSequence);

        void setInProgress(boolean z);

        void setSecondTeamLogoUrl(String str);

        void setSecondTeamName(CharSequence charSequence);

        void setSecondTeamScore(CharSequence charSequence);
    }
}
