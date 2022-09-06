package com.amazon.alexa.voice.ui.onedesign.sports.scores;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface SportsScoresCardModel extends Parcelable {

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface NextGameModel extends Parcelable {
        @NonNull
        CharSequence getDate();

        @NonNull
        CharSequence getOpponent();

        @NonNull
        CharSequence getTime();

        @NonNull
        CharSequence getTitle();

        boolean isHostedByHomeTeam();
    }

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface ScoreComponentsModel extends Parcelable {
        @NonNull
        List<CharSequence> getAwayTeamScoreComponents();

        @NonNull
        CharSequence getAwayTeamShortName();

        @NonNull
        List<CharSequence> getHomeTeamScoreComponents();

        @NonNull
        CharSequence getHomeTeamShortName();

        @NonNull
        List<CharSequence> getScoreComponentsHeadings();
    }

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface TeamStandingModel extends Parcelable {
        @NonNull
        String getImageUrl();

        boolean getIsAhead();

        @NonNull
        CharSequence getPoints();

        @NonNull
        CharSequence getTeamName();
    }

    @NonNull
    TeamStandingModel getAwayTeamStanding();

    @NonNull
    TeamStandingModel getHomeTeamStanding();

    NextGameModel getNextGame();

    @NonNull
    ScoreComponentsModel getScores();

    CharSequence getSportName();

    @NonNull
    CharSequence getSubTitle();

    @NonNull
    CharSequence getTitle();
}
