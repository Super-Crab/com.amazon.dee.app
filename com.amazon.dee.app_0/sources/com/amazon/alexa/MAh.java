package com.amazon.alexa;

import android.support.v4.media.RatingCompat;
/* compiled from: MediaOperations.java */
/* loaded from: classes.dex */
public enum MAh {
    FAVORITED,
    UNFAVORITED,
    NOT_RATED;

    public static MAh zZm(RatingCompat ratingCompat) {
        if (ratingCompat != null && ratingCompat.isRated() && ratingCompat.getRatingStyle() == 2) {
            if (ratingCompat.isThumbUp()) {
                return FAVORITED;
            }
            if (!ratingCompat.isThumbUp()) {
                return UNFAVORITED;
            }
        }
        return NOT_RATED;
    }
}
