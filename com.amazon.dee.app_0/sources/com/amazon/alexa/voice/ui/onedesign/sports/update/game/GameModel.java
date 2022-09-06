package com.amazon.alexa.voice.ui.onedesign.sports.update.game;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface GameModel extends Parcelable {

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface TeamScoreModel extends Parcelable {
        String getLogoUrl();

        @NonNull
        CharSequence getName();

        int getScore();
    }

    TeamScoreModel getFirstTeamScore();

    boolean getInProgress();

    @NonNull
    CharSequence getLeagueName();

    TeamScoreModel getSecondTeamScore();

    @NonNull
    CharSequence getTemporalIndicator();
}
