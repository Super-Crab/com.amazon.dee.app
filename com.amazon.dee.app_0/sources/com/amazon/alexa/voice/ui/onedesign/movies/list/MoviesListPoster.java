package com.amazon.alexa.voice.ui.onedesign.movies.list;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class MoviesListPoster implements MoviesListPosterModel {
    private final String imageUrl;
    private final CharSequence rating;
    private final CharSequence ratingContentDescription;
    private final CharSequence reviewScore;
    private final CharSequence reviewScoreContentDescription;
    private final Object tag;
    private final CharSequence title;

    /* loaded from: classes11.dex */
    public static final class Builder {
        String imageUrl;
        CharSequence rating;
        CharSequence ratingContentDescription;
        CharSequence reviewScore;
        CharSequence reviewScoreContentDescription;
        Object tag;
        CharSequence title;

        public MoviesListPoster build() {
            if (this.title != null) {
                return new MoviesListPoster(this);
            }
            throw new IllegalArgumentException("title == null");
        }

        public Builder imageUrl(String str) {
            this.imageUrl = str;
            return this;
        }

        public Builder rating(CharSequence charSequence) {
            this.rating = charSequence;
            return this;
        }

        public Builder ratingContentDescription(CharSequence charSequence) {
            this.ratingContentDescription = charSequence;
            return this;
        }

        public Builder reviewScore(CharSequence charSequence) {
            this.reviewScore = charSequence;
            return this;
        }

        public Builder reviewScoreContentDescription(CharSequence charSequence) {
            this.reviewScoreContentDescription = charSequence;
            return this;
        }

        public Builder tag(Object obj) {
            this.tag = obj;
            return this;
        }

        public Builder title(@NonNull CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }
    }

    MoviesListPoster(Builder builder) {
        this.title = builder.title;
        this.rating = builder.rating;
        this.reviewScore = builder.reviewScore;
        this.ratingContentDescription = builder.ratingContentDescription;
        this.reviewScoreContentDescription = builder.reviewScoreContentDescription;
        this.imageUrl = builder.imageUrl;
        this.tag = builder.tag;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MoviesListPoster.class != obj.getClass()) {
            return false;
        }
        MoviesListPoster moviesListPoster = (MoviesListPoster) obj;
        if (!this.title.equals(moviesListPoster.title)) {
            return false;
        }
        CharSequence charSequence = this.rating;
        if (charSequence == null ? moviesListPoster.rating != null : !charSequence.equals(moviesListPoster.rating)) {
            return false;
        }
        CharSequence charSequence2 = this.reviewScore;
        if (charSequence2 == null ? moviesListPoster.reviewScore != null : !charSequence2.equals(moviesListPoster.reviewScore)) {
            return false;
        }
        CharSequence charSequence3 = this.ratingContentDescription;
        if (charSequence3 == null ? moviesListPoster.ratingContentDescription != null : !charSequence3.equals(moviesListPoster.ratingContentDescription)) {
            return false;
        }
        CharSequence charSequence4 = this.reviewScoreContentDescription;
        if (charSequence4 == null ? moviesListPoster.reviewScoreContentDescription != null : !charSequence4.equals(moviesListPoster.reviewScoreContentDescription)) {
            return false;
        }
        String str = this.imageUrl;
        if (str == null ? moviesListPoster.imageUrl != null : !str.equals(moviesListPoster.imageUrl)) {
            return false;
        }
        Object obj2 = this.tag;
        Object obj3 = moviesListPoster.tag;
        return obj2 == null ? obj3 == null : obj2.equals(obj3);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListPosterModel
    public String getImageUrl() {
        return this.imageUrl;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListPosterModel
    public CharSequence getRating() {
        return this.rating;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListPosterModel
    public CharSequence getRatingContentDescription() {
        return this.ratingContentDescription;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListPosterModel
    public CharSequence getReviewScore() {
        return this.reviewScore;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListPosterModel
    public CharSequence getReviewScoreContentDescription() {
        return this.reviewScoreContentDescription;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListPosterModel
    public Object getTag() {
        return this.tag;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListPosterModel
    @NonNull
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        int outline5 = GeneratedOutlineSupport1.outline5(this.title, JfifUtil.MARKER_EOI, 31);
        CharSequence charSequence = this.rating;
        int i = 0;
        int hashCode = (outline5 + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.reviewScore;
        int hashCode2 = (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
        CharSequence charSequence3 = this.ratingContentDescription;
        int hashCode3 = (hashCode2 + (charSequence3 != null ? charSequence3.hashCode() : 0)) * 31;
        CharSequence charSequence4 = this.reviewScoreContentDescription;
        int hashCode4 = (hashCode3 + (charSequence4 != null ? charSequence4.hashCode() : 0)) * 31;
        String str = this.imageUrl;
        int hashCode5 = (hashCode4 + (str != null ? str.hashCode() : 0)) * 31;
        Object obj = this.tag;
        if (obj != null) {
            i = obj.hashCode();
        }
        return hashCode5 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MoviesListPoster{title=");
        outline107.append((Object) this.title);
        outline107.append(", rating=");
        outline107.append((Object) this.rating);
        outline107.append(", reviewScore=");
        outline107.append((Object) this.reviewScore);
        outline107.append(", ratingContentDescription=");
        outline107.append((Object) this.ratingContentDescription);
        outline107.append(", reviewScoreContentDescription=");
        outline107.append((Object) this.reviewScoreContentDescription);
        outline107.append(", imageUrl=");
        outline107.append(this.imageUrl);
        outline107.append(", tag=");
        outline107.append(this.tag);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
