package com.amazon.alexa.voice.ui.onedesign.movies;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.text.DecimalFormat;
/* loaded from: classes11.dex */
public final class MoviesRatingText {
    private MoviesRatingText() {
        throw new IllegalStateException("No instances!");
    }

    public static CharSequence fromRating(MoviesCardModel.RatingModel ratingModel) {
        return !isValidRating(ratingModel) ? "" : String.format("%s/%s", new DecimalFormat("0.0").format(ratingModel.getAverage()), Integer.toString((int) ratingModel.getMaximum()));
    }

    private static boolean isValidRating(MoviesCardModel.RatingModel ratingModel) {
        return ratingModel != null && ratingModel.getAverage() > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR && ratingModel.getMaximum() > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR && ratingModel.getAverage() <= ratingModel.getMaximum();
    }
}
