package com.amazon.alexa.voice.ui.onedesign.movies.theater;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class MovieTheaterTime implements MovieTheaterTimeModel {
    private final CharSequence title;

    public MovieTheaterTime(CharSequence charSequence) {
        this.title = charSequence;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MovieTheaterTime.class != obj.getClass()) {
            return false;
        }
        CharSequence charSequence = this.title;
        CharSequence charSequence2 = ((MovieTheaterTime) obj).title;
        return charSequence == null ? charSequence2 == null : charSequence.equals(charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterTimeModel
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        CharSequence charSequence = this.title;
        return JfifUtil.MARKER_EOI + (charSequence != null ? charSequence.hashCode() : 0);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MovieTheaterTime{title=");
        outline107.append((Object) this.title);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
