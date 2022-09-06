package com.amazon.alexa.voice.ui.onedesign.sports.scores;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.voice.ui.onedesign.widget.background.CardLayout;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.Glide;
import java.util.List;
/* loaded from: classes11.dex */
public final class SportsScoresController extends ViewController implements SportsScoresContract.View {
    private static final String EXTRA_CARD = "card";
    private ImageView awayTeamLogoView;
    private TextView awayTeamNameView;
    private TextView awayTeamStandingView;
    private CardLayout cardLayout;
    private ImageView homeTeamLogoView;
    private TextView homeTeamNameView;
    private TextView homeTeamStandingView;
    private TextView nextGameDateView;
    private LinearLayout nextGameLayout;
    private TextView nextGameTimeAndHostView;
    private TextView nextGameTitleView;
    private SportsScoresContract.Presenter presenter;
    private SportsScoresComponentsView scoresComponentsView;
    private TextView subTitleView;
    private TextView titleView;

    private void bindContentViews(View view) {
        this.titleView = (TextView) view.findViewById(R.id.title);
        this.subTitleView = (TextView) view.findViewById(R.id.subTitle);
        HeaderUtils.applyFontStyles(this.titleView, this.subTitleView);
        this.homeTeamLogoView = (ImageView) view.findViewById(R.id.homeTeamLogo);
        this.homeTeamNameView = (TextView) view.findViewById(R.id.homeTeamName);
        this.homeTeamStandingView = (TextView) view.findViewById(R.id.homeTeamStanding);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.homeTeamNameView);
        this.awayTeamLogoView = (ImageView) view.findViewById(R.id.awayTeamLogo);
        this.awayTeamNameView = (TextView) view.findViewById(R.id.awayTeamName);
        this.awayTeamStandingView = (TextView) view.findViewById(R.id.awayTeamStanding);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.awayTeamNameView);
        this.scoresComponentsView = (SportsScoresComponentsView) view.findViewById(R.id.scoreComponents);
        this.nextGameLayout = (LinearLayout) view.findViewById(R.id.nextGame);
        this.nextGameTitleView = (TextView) view.findViewById(R.id.nextGameTitle);
        this.nextGameDateView = (TextView) view.findViewById(R.id.nextGameDate);
        this.nextGameTimeAndHostView = (TextView) view.findViewById(R.id.nextGameTimeAndHost);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.nextGameDateView, this.nextGameTimeAndHostView);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.nextGameTitleView);
        this.cardLayout = (CardLayout) view.findViewById(R.id.cardLayout);
    }

    public static SportsScoresController create(SportsScoresCard sportsScoresCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", sportsScoresCard);
        SportsScoresController sportsScoresController = new SportsScoresController();
        sportsScoresController.setArguments(bundle);
        return sportsScoresController;
    }

    private CharSequence getHomeOrAwayIdentifier(boolean z) {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(" "), z ? getContext().getString(R.string.voice_ui_od_sports_home_against) : "@", " ");
    }

    private void setPoints(TextView textView, CharSequence charSequence, boolean z) {
        textView.setText(charSequence);
        if (z) {
            FontUtils.apply(Font.AMAZON_EMBER_BOLD, textView);
        } else {
            FontUtils.apply(Font.AMAZON_EMBER_LIGHT, textView);
        }
    }

    private void updateNextGameVisibility() {
        if (!TextUtils.isEmpty(this.nextGameDateView.getText()) && !TextUtils.isEmpty(this.nextGameTimeAndHostView.getText())) {
            this.nextGameLayout.setVisibility(0);
        } else {
            this.nextGameLayout.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$onCreateView$0$SportsScoresController(View view) {
        this.presenter.closeClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        Bundle arguments = getArguments();
        SportsScoresCard sportsScoresCard = arguments != null ? (SportsScoresCard) arguments.getParcelable("card") : null;
        if (sportsScoresCard != null) {
            this.presenter = new SportsScoresPresenter(this, new SportsScoresInteractor(sportsScoresCard, new SportsScoresMediator(this)), new AndroidResources(getContext(), ComponentUtils.getLocale(getComponent())), (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class));
            if ("hockey".equals(sportsScoresCard.getSportName())) {
                overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Light);
                return;
            } else {
                overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark);
                return;
            }
        }
        throw new IllegalStateException("Use SportsScoresController.create(SportsScoresCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_sports_scores, viewGroup, false);
        SpeechControlView speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate.findViewById(R.id.cardLayout), speechControlView);
        bindContentViews(inflate);
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.scores.-$$Lambda$SportsScoresController$87RLJ3urPOZgKAOuDIzff0Bc3ss
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsScoresController.this.lambda$onCreateView$0$SportsScoresController(view);
            }
        });
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                SportsScoresController.this.presenter.dismiss();
            }
        });
        speechControlView.setComponent(getComponent());
        ((NestedScrollView) inflate.findViewById(R.id.scrollView)).setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresController.2
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                SportsScoresController.this.presenter.interacted();
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.titleView = null;
        this.subTitleView = null;
        Glide.with(getContext().getApplicationContext()).clear(this.homeTeamLogoView);
        this.homeTeamLogoView = null;
        this.homeTeamNameView = null;
        this.homeTeamStandingView = null;
        Glide.with(getContext().getApplicationContext()).clear(this.awayTeamLogoView);
        this.awayTeamLogoView = null;
        this.awayTeamNameView = null;
        this.awayTeamStandingView = null;
        this.scoresComponentsView = null;
        this.nextGameLayout = null;
        this.nextGameTitleView = null;
        this.nextGameDateView = null;
        this.nextGameTimeAndHostView = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setAwayTeamLogo(String str) {
        Glide.with(this.awayTeamLogoView.getContext().getApplicationContext()).mo6762load(str).into(this.awayTeamLogoView);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setAwayTeamNickName(CharSequence charSequence) {
        this.awayTeamNameView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setAwayTeamPoints(CharSequence charSequence, boolean z) {
        setPoints(this.awayTeamStandingView, charSequence, z);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setAwayTeamScoreComponents(List<CharSequence> list) {
        this.scoresComponentsView.setAwayTeamComponents(list);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setAwayTeamShortName(CharSequence charSequence) {
        this.scoresComponentsView.setAwayTeamName(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setGradientBackgroundColor(int i) {
        this.cardLayout.setBackgroundColorAndDrawable(ContextCompat.getColor(getContext(), i), (Drawable) null);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setGradientBackgroundColorAndDrawable(int i, int i2) {
        this.cardLayout.setBackgroundColorAndDrawable(ContextCompat.getColor(getContext(), i), ContextCompat.getDrawable(getContext(), i2));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setHomeTeamLogo(String str) {
        Glide.with(this.homeTeamLogoView.getContext().getApplicationContext()).mo6762load(str).into(this.homeTeamLogoView);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setHomeTeamNickName(CharSequence charSequence) {
        this.homeTeamNameView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setHomeTeamPoints(CharSequence charSequence, boolean z) {
        setPoints(this.homeTeamStandingView, charSequence, z);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setHomeTeamScoreComponents(List<CharSequence> list) {
        this.scoresComponentsView.setHomeTeamComponents(list);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setHomeTeamShortName(CharSequence charSequence) {
        this.scoresComponentsView.setHomeTeamName(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setNextGameDate(CharSequence charSequence) {
        this.nextGameDateView.setText(charSequence);
        updateNextGameVisibility();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setNextGameTimeAndOpponent(CharSequence charSequence, boolean z, CharSequence charSequence2) {
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence);
        sb.append(getHomeOrAwayIdentifier(z));
        sb.append(charSequence2);
        this.nextGameTimeAndHostView.setText(sb);
        updateNextGameVisibility();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setNextGameTitle(CharSequence charSequence) {
        this.nextGameTitleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setScoreComponentsHeadings(List<CharSequence> list) {
        this.scoresComponentsView.setHeadings(list);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setStatusBarColor(@ColorRes int i) {
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setSubTitle(CharSequence charSequence) {
        this.subTitleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.View
    public void setTitle(CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }
}
