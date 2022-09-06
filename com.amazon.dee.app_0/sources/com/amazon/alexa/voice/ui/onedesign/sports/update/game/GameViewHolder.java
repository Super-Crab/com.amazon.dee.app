package com.amazon.alexa.voice.ui.onedesign.sports.update.game;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameContract;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.bumptech.glide.Glide;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class GameViewHolder extends BindableViewHolder<GameModel> implements GameContract.View {
    private ImageView firstTeamLogoView;
    private TextView firstTeamNameView;
    private TextView firstTeamScoreView;
    private TextView headingView;
    private TextView isFinalView;
    private final Locale locale;
    private ImageView secondTeamLogoView;
    private TextView secondTeamNameView;
    private TextView secondTeamScoreView;

    public GameViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Locale locale) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_sports_update_game, viewGroup, false));
        this.locale = locale;
        this.headingView = (TextView) this.itemView.findViewById(R.id.heading);
        this.firstTeamLogoView = (ImageView) this.itemView.findViewById(R.id.firstTeamLogo);
        this.firstTeamNameView = (TextView) this.itemView.findViewById(R.id.firstTeamName);
        this.firstTeamScoreView = (TextView) this.itemView.findViewById(R.id.firstTeamScore);
        this.secondTeamLogoView = (ImageView) this.itemView.findViewById(R.id.secondTeamLogo);
        this.secondTeamNameView = (TextView) this.itemView.findViewById(R.id.secondTeamName);
        this.secondTeamScoreView = (TextView) this.itemView.findViewById(R.id.secondTeamScore);
        this.isFinalView = (TextView) this.itemView.findViewById(R.id.isFinal);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.headingView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.firstTeamNameView, this.firstTeamScoreView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.secondTeamNameView, this.secondTeamScoreView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.isFinalView);
    }

    private void loadImage(ImageView imageView, String str) {
        Glide.with(imageView.getContext().getApplicationContext()).clear(imageView);
        if (str != null) {
            Glide.with(imageView.getContext().getApplicationContext()).mo6762load(str).into(imageView);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameContract.View
    public void setFirstTeamLogoUrl(String str) {
        loadImage(this.firstTeamLogoView, str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameContract.View
    public void setFirstTeamName(CharSequence charSequence) {
        this.firstTeamNameView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameContract.View
    public void setFirstTeamScore(CharSequence charSequence) {
        this.firstTeamScoreView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameContract.View
    public void setHeading(CharSequence charSequence) {
        this.headingView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameContract.View
    public void setInProgress(boolean z) {
        if (z) {
            this.isFinalView.setVisibility(4);
        } else {
            this.isFinalView.setVisibility(0);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameContract.View
    public void setSecondTeamLogoUrl(String str) {
        loadImage(this.secondTeamLogoView, str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameContract.View
    public void setSecondTeamName(CharSequence charSequence) {
        this.secondTeamNameView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameContract.View
    public void setSecondTeamScore(CharSequence charSequence) {
        this.secondTeamScoreView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder
    public void bind(GameModel gameModel) {
        new GamePresenter(this, gameModel, this.locale).start();
    }
}
