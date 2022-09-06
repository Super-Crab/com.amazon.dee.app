package com.amazon.alexa.voice.ui.onedesign.sports.scores;

import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel;
import com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class SportsScoresPresenter implements SportsScoresContract.Presenter {
    static final String SPORTS_BASEBALL = "baseball";
    static final String SPORTS_BASKETBALL = "basketball";
    static final String SPORTS_CRICKET = "cricket";
    static final String SPORTS_FOOTBALL = "football";
    static final String SPORTS_HOCKEY = "hockey";
    static final String SPORTS_SOCCER = "soccer";
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private final SportsScoresContract.Interactor interactor;
    private final CardMetricsInteractor metricsInteractor;
    private final Resources resources;
    private final SportsScoresContract.View view;

    public SportsScoresPresenter(SportsScoresContract.View view, SportsScoresContract.Interactor interactor, Resources resources, CardMetricsInteractor cardMetricsInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
        this.metricsInteractor = cardMetricsInteractor;
    }

    private String getCardName() {
        return this.interactor.getCard().getClass().getSimpleName();
    }

    private void renderCard() {
        int i;
        int i2;
        SportsScoresCard card = this.interactor.getCard();
        this.view.setTitle(card.getTitle());
        this.view.setSubTitle(card.getSubTitle());
        SportsScoresCardModel.TeamStandingModel homeTeamStanding = card.getHomeTeamStanding();
        this.view.setHomeTeamLogo(homeTeamStanding.getImageUrl());
        this.view.setHomeTeamNickName(homeTeamStanding.getTeamName());
        this.view.setHomeTeamPoints(homeTeamStanding.getPoints(), homeTeamStanding.getIsAhead());
        SportsScoresCardModel.TeamStandingModel awayTeamStanding = card.getAwayTeamStanding();
        this.view.setAwayTeamLogo(awayTeamStanding.getImageUrl());
        this.view.setAwayTeamNickName(awayTeamStanding.getTeamName());
        this.view.setAwayTeamPoints(awayTeamStanding.getPoints(), awayTeamStanding.getIsAhead());
        SportsScoresCardModel.ScoreComponentsModel scores = card.getScores();
        this.view.setScoreComponentsHeadings(scores.getScoreComponentsHeadings());
        this.view.setHomeTeamScoreComponents(scores.getHomeTeamScoreComponents());
        this.view.setAwayTeamScoreComponents(scores.getAwayTeamScoreComponents());
        this.view.setHomeTeamShortName(scores.getHomeTeamShortName());
        this.view.setAwayTeamShortName(scores.getAwayTeamShortName());
        SportsScoresCardModel.NextGameModel nextGame = card.getNextGame();
        this.view.setNextGameTitle(this.resources.getString(R.string.voice_ui_od_sports_next_game_title));
        this.view.setNextGameDate(nextGame == null ? null : nextGame.getDate());
        setTimeAndOpponentText(nextGame);
        if (SPORTS_HOCKEY.equals(card.getSportName())) {
            i = R.color.voice_ui_od_sports_hockey_background;
        } else if (!SPORTS_BASKETBALL.equals(card.getSportName()) && !SPORTS_BASEBALL.equals(card.getSportName())) {
            i = R.color.voice_ui_od_sports_other_background;
        } else {
            i = R.color.voice_ui_od_sports_basketball_or_baseball_background;
        }
        if (SPORTS_HOCKEY.equals(card.getSportName())) {
            i2 = R.drawable.ic_od_bg_hockey;
        } else if (SPORTS_BASEBALL.equals(card.getSportName())) {
            i2 = R.drawable.ic_od_bg_baseball;
        } else if (SPORTS_BASKETBALL.equals(card.getSportName())) {
            i2 = R.drawable.ic_od_bg_basketball;
        } else if (SPORTS_FOOTBALL.equals(card.getSportName())) {
            i2 = R.drawable.ic_od_bg_football;
        } else if (SPORTS_SOCCER.equals(card.getSportName())) {
            i2 = R.drawable.ic_od_bg_soccer;
        } else {
            i2 = SPORTS_CRICKET.equals(card.getSportName()) ? R.drawable.ic_od_bg_cricket : 0;
        }
        if (i2 != 0) {
            this.view.setStatusBarColor(i);
            this.view.setGradientBackgroundColorAndDrawable(i, i2);
            return;
        }
        this.view.setStatusBarColor(i);
        this.view.setGradientBackgroundColor(i);
    }

    private void setTimeAndOpponentText(SportsScoresCardModel.NextGameModel nextGameModel) {
        if (nextGameModel == null) {
            return;
        }
        CharSequence time = nextGameModel.getTime();
        CharSequence opponent = nextGameModel.getOpponent();
        if (time == null || opponent == null) {
            return;
        }
        this.view.setNextGameTimeAndOpponent(time, nextGameModel.isHostedByHomeTeam(), opponent);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.Presenter
    public void start() {
        renderCard();
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(this.interactor.getCard().getClass().getSimpleName(), this.interactionTracker.wasInteracted());
    }
}
