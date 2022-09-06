package com.amazon.alexa.voice.ui.onedesign.movies.list;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface MoviesListPosterModel {
    String getImageUrl();

    CharSequence getRating();

    CharSequence getRatingContentDescription();

    CharSequence getReviewScore();

    CharSequence getReviewScoreContentDescription();

    Object getTag();

    @NonNull
    CharSequence getTitle();
}
