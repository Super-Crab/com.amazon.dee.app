package com.amazon.alexa.voice.ui.onedesign.movies.showtimes;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class MovieShowtimesTheater implements MovieShowtimesTheaterModel {
    private final CharSequence subTitle;
    private final Object tag;
    private final CharSequence title;

    public MovieShowtimesTheater(@NonNull CharSequence charSequence, @NonNull CharSequence charSequence2, Object obj) {
        this.title = charSequence;
        this.subTitle = charSequence2;
        this.tag = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MovieShowtimesTheater.class != obj.getClass()) {
            return false;
        }
        MovieShowtimesTheater movieShowtimesTheater = (MovieShowtimesTheater) obj;
        if (!this.title.equals(movieShowtimesTheater.title) || !this.subTitle.equals(movieShowtimesTheater.subTitle)) {
            return false;
        }
        Object obj2 = this.tag;
        Object obj3 = movieShowtimesTheater.tag;
        return obj2 == null ? obj3 == null : obj2.equals(obj3);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesTheaterModel
    @NonNull
    public CharSequence getSubTitle() {
        return this.subTitle;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesTheaterModel
    public Object getTag() {
        return this.tag;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesTheaterModel
    @NonNull
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        int outline5 = GeneratedOutlineSupport1.outline5(this.subTitle, GeneratedOutlineSupport1.outline5(this.title, JfifUtil.MARKER_EOI, 31), 31);
        Object obj = this.tag;
        return outline5 + (obj != null ? obj.hashCode() : 0);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MovieShowtimesTheater{title=");
        outline107.append((Object) this.title);
        outline107.append(", subTitle=");
        outline107.append((Object) this.subTitle);
        outline107.append(", tag=");
        outline107.append(this.tag);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
