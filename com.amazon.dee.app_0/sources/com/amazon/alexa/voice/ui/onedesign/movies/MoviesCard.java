package com.amazon.alexa.voice.ui.onedesign.movies;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class MoviesCard implements MoviesCardModel {
    public static final Parcelable.Creator<MoviesCard> CREATOR = new Parcelable.Creator<MoviesCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public MoviesCard mo2710createFromParcel(Parcel parcel) {
            return new MoviesCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public MoviesCard[] mo2711newArray(int i) {
            return new MoviesCard[i];
        }
    };
    private final CharSequence attribution;
    private final CharSequence linkText;
    private final String linkUrl;
    private final List<? extends MoviesCardModel.MovieModel> movieList;
    private final CharSequence subTitle;
    private final CharSequence title;

    /* loaded from: classes11.dex */
    public static final class Builder {
        CharSequence attribution;
        CharSequence linkText;
        String linkUrl;
        List<? extends MoviesCardModel.MovieModel> movieList = new ArrayList();
        CharSequence subTitle;
        CharSequence title;

        public Builder attribution(CharSequence charSequence) {
            this.attribution = charSequence;
            return this;
        }

        public MoviesCard build() {
            if (this.title != null) {
                if (this.subTitle != null) {
                    if (this.linkText != null) {
                        if (this.linkUrl != null) {
                            if (this.movieList != null) {
                                return new MoviesCard(this);
                            }
                            throw new IllegalArgumentException("movieList == null");
                        }
                        throw new IllegalArgumentException("linkUrl == null");
                    }
                    throw new IllegalArgumentException("linkText == null");
                }
                throw new IllegalArgumentException("subTitle == null");
            }
            throw new IllegalArgumentException("title == null");
        }

        public Builder linkText(@NonNull CharSequence charSequence) {
            this.linkText = charSequence;
            return this;
        }

        public Builder linkUrl(@NonNull String str) {
            this.linkUrl = str;
            return this;
        }

        public Builder movieList(@NonNull List<? extends MoviesCardModel.MovieModel> list) {
            this.movieList = list;
            return this;
        }

        public Builder subTitle(@NonNull CharSequence charSequence) {
            this.subTitle = charSequence;
            return this;
        }

        public Builder title(@NonNull CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }
    }

    MoviesCard(Builder builder) {
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.linkText = builder.linkText;
        this.linkUrl = builder.linkUrl;
        this.movieList = builder.movieList;
        this.attribution = builder.attribution;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MoviesCard.class != obj.getClass()) {
            return false;
        }
        MoviesCard moviesCard = (MoviesCard) obj;
        if (!this.title.equals(moviesCard.title) || !this.subTitle.equals(moviesCard.subTitle) || !this.linkText.equals(moviesCard.linkText) || !this.linkUrl.equals(moviesCard.linkUrl) || !this.movieList.equals(moviesCard.movieList)) {
            return false;
        }
        CharSequence charSequence = this.attribution;
        CharSequence charSequence2 = moviesCard.attribution;
        return charSequence == null ? charSequence2 == null : charSequence.equals(charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel
    @Nullable
    public CharSequence getAttribution() {
        return this.attribution;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel
    @NonNull
    public CharSequence getLinkText() {
        return this.linkText;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel
    @NonNull
    public String getLinkUrl() {
        return this.linkUrl;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel
    @NonNull
    public List<? extends MoviesCardModel.MovieModel> getMovieList() {
        return this.movieList;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel
    @NonNull
    public CharSequence getSubTitle() {
        return this.subTitle;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel
    @NonNull
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        int hashCode = (this.movieList.hashCode() + GeneratedOutlineSupport1.outline7(this.linkUrl, GeneratedOutlineSupport1.outline5(this.linkText, GeneratedOutlineSupport1.outline5(this.subTitle, GeneratedOutlineSupport1.outline5(this.title, JfifUtil.MARKER_EOI, 31), 31), 31), 31)) * 31;
        CharSequence charSequence = this.attribution;
        return hashCode + (charSequence != null ? charSequence.hashCode() : 0);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MoviesCard{title=");
        outline107.append((Object) this.title);
        outline107.append(", subTitle=");
        outline107.append((Object) this.subTitle);
        outline107.append(", linkText=");
        outline107.append((Object) this.linkText);
        outline107.append(", linkUrl=");
        outline107.append(this.linkUrl);
        outline107.append(", movieList=");
        outline107.append(this.movieList);
        outline107.append(", attribution=");
        outline107.append((Object) this.attribution);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        TextUtils.writeToParcel(this.subTitle, parcel, i);
        TextUtils.writeToParcel(this.linkText, parcel, i);
        parcel.writeString(this.linkUrl);
        parcel.writeTypedList(this.movieList);
        TextUtils.writeToParcel(this.attribution, parcel, i);
    }

    /* loaded from: classes11.dex */
    public static final class Time implements MoviesCardModel.TimeModel {
        public static final Parcelable.Creator<Time> CREATOR = new Parcelable.Creator<Time>() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard.Time.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Time mo2718createFromParcel(Parcel parcel) {
                return new Time(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Time[] mo2719newArray(int i) {
                return new Time[i];
            }
        };
        private final CharSequence time;

        public Time(@NonNull CharSequence charSequence) {
            this.time = charSequence;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && Time.class == obj.getClass() && this.time.equals(((Time) obj).time);
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.TimeModel
        @NonNull
        public CharSequence getTime() {
            return this.time;
        }

        public int hashCode() {
            return this.time.hashCode() + JfifUtil.MARKER_EOI;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Time{time=");
            outline107.append((Object) this.time);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.time, parcel, i);
        }

        Time(Parcel parcel) {
            this.time = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
    }

    /* loaded from: classes11.dex */
    public static final class Rating implements MoviesCardModel.RatingModel {
        public static final Parcelable.Creator<Rating> CREATOR = new Parcelable.Creator<Rating>() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard.Rating.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Rating mo2714createFromParcel(Parcel parcel) {
                return new Rating(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Rating[] mo2715newArray(int i) {
                return new Rating[i];
            }
        };
        private final double average;
        private final double maximum;
        private final CharSequence source;

        /* loaded from: classes11.dex */
        public static final class Builder {
            double average;
            double maximum;
            CharSequence source;

            public Builder average(double d) {
                this.average = d;
                return this;
            }

            public Rating build() {
                return new Rating(this);
            }

            public Builder maximum(double d) {
                this.maximum = d;
                return this;
            }

            public Builder source(CharSequence charSequence) {
                this.source = charSequence;
                return this;
            }
        }

        Rating(Builder builder) {
            this.average = builder.average;
            this.maximum = builder.maximum;
            this.source = builder.source;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Rating.class != obj.getClass()) {
                return false;
            }
            Rating rating = (Rating) obj;
            if (Double.compare(this.average, rating.average) != 0 || Double.compare(this.maximum, rating.maximum) != 0) {
                return false;
            }
            CharSequence charSequence = this.source;
            CharSequence charSequence2 = rating.source;
            return charSequence == null ? charSequence2 == null : charSequence.equals(charSequence2);
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.RatingModel
        public double getAverage() {
            return this.average;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.RatingModel
        public double getMaximum() {
            return this.maximum;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.RatingModel
        public CharSequence getSource() {
            return this.source;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.average);
            int i = JfifUtil.MARKER_EOI + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
            long doubleToLongBits2 = Double.doubleToLongBits(this.maximum);
            int i2 = ((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
            CharSequence charSequence = this.source;
            return i2 + (charSequence != null ? charSequence.hashCode() : 0);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Rating{average=");
            outline107.append(this.average);
            outline107.append(", maximum=");
            outline107.append(this.maximum);
            outline107.append(", source=");
            outline107.append((Object) this.source);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeDouble(this.average);
            parcel.writeDouble(this.maximum);
            TextUtils.writeToParcel(this.source, parcel, i);
        }

        Rating(Parcel parcel) {
            this.average = parcel.readDouble();
            this.maximum = parcel.readDouble();
            this.source = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
    }

    /* loaded from: classes11.dex */
    public static final class Theater implements MoviesCardModel.TheaterModel {
        public static final Parcelable.Creator<Theater> CREATOR = new Parcelable.Creator<Theater>() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard.Theater.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Theater mo2716createFromParcel(Parcel parcel) {
                return new Theater(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Theater[] mo2717newArray(int i) {
                return new Theater[i];
            }
        };
        private final CharSequence location;
        private final CharSequence name;
        private final List<? extends MoviesCardModel.TimeModel> timeList;

        /* loaded from: classes11.dex */
        public static final class Builder {
            CharSequence location;
            CharSequence name;
            List<? extends MoviesCardModel.TimeModel> timeList = new ArrayList();

            public Theater build() {
                if (this.name != null) {
                    if (this.location != null) {
                        if (this.timeList != null) {
                            return new Theater(this);
                        }
                        throw new IllegalArgumentException("timeList == null");
                    }
                    throw new IllegalArgumentException("location == null");
                }
                throw new IllegalArgumentException("name == null");
            }

            public Builder location(@NonNull CharSequence charSequence) {
                this.location = charSequence;
                return this;
            }

            public Builder name(@NonNull CharSequence charSequence) {
                this.name = charSequence;
                return this;
            }

            public Builder timeList(@NonNull List<? extends MoviesCardModel.TimeModel> list) {
                this.timeList = list;
                return this;
            }
        }

        Theater(Builder builder) {
            this.name = builder.name;
            this.location = builder.location;
            this.timeList = builder.timeList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Theater.class != obj.getClass()) {
                return false;
            }
            Theater theater = (Theater) obj;
            return this.name.equals(theater.name) && this.location.equals(theater.location) && this.timeList.equals(theater.timeList);
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.TheaterModel
        @NonNull
        public CharSequence getLocation() {
            return this.location;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.TheaterModel
        @NonNull
        public CharSequence getName() {
            return this.name;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.TheaterModel
        @NonNull
        public List<? extends MoviesCardModel.TimeModel> getTimeList() {
            return this.timeList;
        }

        public int hashCode() {
            return this.timeList.hashCode() + GeneratedOutlineSupport1.outline5(this.location, GeneratedOutlineSupport1.outline5(this.name, JfifUtil.MARKER_EOI, 31), 31);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Theater{name=");
            outline107.append((Object) this.name);
            outline107.append(", location=");
            outline107.append((Object) this.location);
            outline107.append(", timeList=");
            return GeneratedOutlineSupport1.outline94(outline107, this.timeList, JsonReaderKt.END_OBJ);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.name, parcel, i);
            TextUtils.writeToParcel(this.location, parcel, i);
            parcel.writeTypedList(this.timeList);
        }

        Theater(Parcel parcel) {
            this.name = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.location = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.timeList = parcel.createTypedArrayList(Time.CREATOR);
        }
    }

    MoviesCard(Parcel parcel) {
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.subTitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.linkText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.linkUrl = parcel.readString();
        this.movieList = parcel.createTypedArrayList(Movie.CREATOR);
        this.attribution = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }

    /* loaded from: classes11.dex */
    public static final class Movie implements MoviesCardModel.MovieModel {
        public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard.Movie.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Movie mo2712createFromParcel(Parcel parcel) {
                return new Movie(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Movie[] mo2713newArray(int i) {
                return new Movie[i];
            }
        };
        private final CharSequence about;
        private final List<CharSequence> cast;
        private final int duration;
        private final String imageUrl;
        private final MoviesCardModel.RatingModel imdbRating;
        private final CharSequence linkText;
        private final String linkUrl;
        private final CharSequence name;
        private final CharSequence rating;
        private final int releaseYear;
        private final List<? extends MoviesCardModel.TheaterModel> theaterList;
        private final String videoUrl;

        /* loaded from: classes11.dex */
        public static final class Builder {
            CharSequence about;
            List<CharSequence> cast;
            int duration;
            String imageUrl;
            MoviesCardModel.RatingModel imdbRating;
            CharSequence linkText;
            String linkUrl;
            CharSequence name;
            CharSequence rating;
            int releaseYear;
            List<? extends MoviesCardModel.TheaterModel> theaterList = new ArrayList();
            String videoUrl;

            public Builder about(CharSequence charSequence) {
                this.about = charSequence;
                return this;
            }

            public Movie build() {
                if (this.name != null) {
                    if (this.linkText != null) {
                        if (this.linkUrl != null) {
                            if (this.theaterList != null) {
                                return new Movie(this);
                            }
                            throw new IllegalArgumentException("theaterList == null");
                        }
                        throw new IllegalArgumentException("linkUrl == null");
                    }
                    throw new IllegalArgumentException("linkText == null");
                }
                throw new IllegalArgumentException("name == null");
            }

            public Builder cast(List<CharSequence> list) {
                this.cast = list;
                return this;
            }

            public Builder duration(int i) {
                this.duration = i;
                return this;
            }

            public Builder imageUrl(String str) {
                this.imageUrl = str;
                return this;
            }

            public Builder imdbRating(MoviesCardModel.RatingModel ratingModel) {
                this.imdbRating = ratingModel;
                return this;
            }

            public Builder linkText(@NonNull CharSequence charSequence) {
                this.linkText = charSequence;
                return this;
            }

            public Builder linkUrl(@NonNull String str) {
                this.linkUrl = str;
                return this;
            }

            public Builder name(@NonNull CharSequence charSequence) {
                this.name = charSequence;
                return this;
            }

            public Builder rating(CharSequence charSequence) {
                this.rating = charSequence;
                return this;
            }

            public Builder releaseYear(int i) {
                this.releaseYear = i;
                return this;
            }

            public Builder theaterList(@NonNull List<? extends MoviesCardModel.TheaterModel> list) {
                this.theaterList = list;
                return this;
            }

            public Builder videoUrl(String str) {
                this.videoUrl = str;
                return this;
            }
        }

        Movie(Builder builder) {
            this.name = builder.name;
            this.duration = builder.duration;
            this.releaseYear = builder.releaseYear;
            this.rating = builder.rating;
            this.imdbRating = builder.imdbRating;
            this.about = builder.about;
            this.cast = builder.cast;
            this.linkText = builder.linkText;
            this.linkUrl = builder.linkUrl;
            this.videoUrl = builder.videoUrl;
            this.imageUrl = builder.imageUrl;
            this.theaterList = builder.theaterList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Movie.class != obj.getClass()) {
                return false;
            }
            Movie movie = (Movie) obj;
            if (!this.name.equals(movie.name) || this.duration != movie.duration || this.releaseYear != movie.releaseYear) {
                return false;
            }
            CharSequence charSequence = this.rating;
            if (charSequence == null ? movie.rating != null : !charSequence.equals(movie.rating)) {
                return false;
            }
            MoviesCardModel.RatingModel ratingModel = this.imdbRating;
            if (ratingModel == null ? movie.imdbRating != null : !ratingModel.equals(movie.imdbRating)) {
                return false;
            }
            CharSequence charSequence2 = this.about;
            if (charSequence2 == null ? movie.about != null : !charSequence2.equals(movie.about)) {
                return false;
            }
            List<CharSequence> list = this.cast;
            if (list == null ? movie.cast != null : !list.equals(movie.cast)) {
                return false;
            }
            if (!this.linkText.equals(movie.linkText) || !this.linkUrl.equals(movie.linkUrl)) {
                return false;
            }
            String str = this.videoUrl;
            if (str == null ? movie.videoUrl != null : !str.equals(movie.videoUrl)) {
                return false;
            }
            String str2 = this.imageUrl;
            if (str2 == null ? movie.imageUrl != null : !str2.equals(movie.imageUrl)) {
                return false;
            }
            return this.theaterList.equals(movie.theaterList);
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        public CharSequence getAbout() {
            return this.about;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        public List<CharSequence> getCast() {
            return this.cast;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        public int getDuration() {
            return this.duration;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        public String getImageUrl() {
            return this.imageUrl;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        public MoviesCardModel.RatingModel getImdbRating() {
            return this.imdbRating;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        @NonNull
        public CharSequence getLinkText() {
            return this.linkText;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        @NonNull
        public String getLinkUrl() {
            return this.linkUrl;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        @NonNull
        public CharSequence getName() {
            return this.name;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        public CharSequence getRating() {
            return this.rating;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        public int getReleaseYear() {
            return this.releaseYear;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        @NonNull
        public List<? extends MoviesCardModel.TheaterModel> getTheaterList() {
            return this.theaterList;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel.MovieModel
        public String getVideoUrl() {
            return this.videoUrl;
        }

        public int hashCode() {
            int outline5 = (((GeneratedOutlineSupport1.outline5(this.name, JfifUtil.MARKER_EOI, 31) + this.duration) * 31) + this.releaseYear) * 31;
            CharSequence charSequence = this.rating;
            int i = 0;
            int hashCode = (outline5 + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
            MoviesCardModel.RatingModel ratingModel = this.imdbRating;
            int hashCode2 = (hashCode + (ratingModel != null ? ratingModel.hashCode() : 0)) * 31;
            CharSequence charSequence2 = this.about;
            int hashCode3 = (hashCode2 + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
            List<CharSequence> list = this.cast;
            int outline7 = GeneratedOutlineSupport1.outline7(this.linkUrl, GeneratedOutlineSupport1.outline5(this.linkText, (hashCode3 + (list != null ? list.hashCode() : 0)) * 31, 31), 31);
            String str = this.videoUrl;
            int hashCode4 = (outline7 + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.imageUrl;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return this.theaterList.hashCode() + ((hashCode4 + i) * 31);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Movie{name=");
            outline107.append((Object) this.name);
            outline107.append(", duration=");
            outline107.append(this.duration);
            outline107.append(", releaseYear=");
            outline107.append(this.releaseYear);
            outline107.append(", rating=");
            outline107.append((Object) this.rating);
            outline107.append(", imdbRating=");
            outline107.append(this.imdbRating);
            outline107.append(", about=");
            outline107.append((Object) this.about);
            outline107.append(", cast=");
            outline107.append(this.cast);
            outline107.append(", linkText=");
            outline107.append((Object) this.linkText);
            outline107.append(", linkUrl=");
            outline107.append(this.linkUrl);
            outline107.append(", videoUrl=");
            outline107.append(this.videoUrl);
            outline107.append(", imageUrl=");
            outline107.append(this.imageUrl);
            outline107.append(", theaterList=");
            return GeneratedOutlineSupport1.outline94(outline107, this.theaterList, JsonReaderKt.END_OBJ);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.name, parcel, i);
            parcel.writeInt(this.duration);
            parcel.writeInt(this.releaseYear);
            TextUtils.writeToParcel(this.rating, parcel, i);
            parcel.writeParcelable(this.imdbRating, i);
            TextUtils.writeToParcel(this.about, parcel, i);
            List<CharSequence> list = this.cast;
            if (list != null) {
                parcel.writeInt(list.size());
                for (CharSequence charSequence : this.cast) {
                    TextUtils.writeToParcel(charSequence, parcel, i);
                }
            } else {
                parcel.writeInt(0);
            }
            TextUtils.writeToParcel(this.linkText, parcel, i);
            parcel.writeString(this.linkUrl);
            parcel.writeString(this.videoUrl);
            parcel.writeString(this.imageUrl);
            parcel.writeTypedList(this.theaterList);
        }

        /* JADX WARN: Multi-variable type inference failed */
        Movie(Parcel parcel) {
            this.name = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.duration = parcel.readInt();
            this.releaseYear = parcel.readInt();
            this.rating = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.imdbRating = (MoviesCardModel.RatingModel) parcel.readParcelable(MoviesCardModel.RatingModel.class.getClassLoader());
            this.about = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            int readInt = parcel.readInt();
            this.cast = new ArrayList(readInt);
            for (int i = 0; i < readInt; i++) {
                this.cast.add(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
            }
            this.linkText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.linkUrl = parcel.readString();
            this.videoUrl = parcel.readString();
            this.imageUrl = parcel.readString();
            this.theaterList = parcel.createTypedArrayList(Theater.CREATOR);
        }
    }
}
