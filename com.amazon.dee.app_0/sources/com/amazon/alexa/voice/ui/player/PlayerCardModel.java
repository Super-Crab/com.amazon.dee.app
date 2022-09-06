package com.amazon.alexa.voice.ui.player;

import android.os.Parcelable;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface PlayerCardModel extends Parcelable {
    String getArtImageUrl();

    String getAudioItemId();

    String getBackgroundImageUrl();

    CharSequence getDescription();

    CharSequence getDetails();

    String getPlaybackSource();

    String getProviderLogoId();

    String getProviderName();

    CharSequence getTitle();

    boolean isProgressVisible();
}
