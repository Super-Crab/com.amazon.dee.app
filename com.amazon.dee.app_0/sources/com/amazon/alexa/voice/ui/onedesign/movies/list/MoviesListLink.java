package com.amazon.alexa.voice.ui.onedesign.movies.list;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class MoviesListLink implements MoviesListLinkModel {
    private final Object tag;
    private final CharSequence text;

    public MoviesListLink(@NonNull CharSequence charSequence, Object obj) {
        this.text = charSequence;
        this.tag = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MoviesListLink.class != obj.getClass()) {
            return false;
        }
        MoviesListLink moviesListLink = (MoviesListLink) obj;
        if (!this.text.equals(moviesListLink.text)) {
            return false;
        }
        Object obj2 = this.tag;
        Object obj3 = moviesListLink.tag;
        return obj2 == null ? obj3 == null : obj2.equals(obj3);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListLinkModel
    public Object getTag() {
        return this.tag;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListLinkModel
    @NonNull
    public CharSequence getText() {
        return this.text;
    }

    public int hashCode() {
        int outline5 = GeneratedOutlineSupport1.outline5(this.text, JfifUtil.MARKER_EOI, 31);
        Object obj = this.tag;
        return outline5 + (obj != null ? obj.hashCode() : 0);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MoviesListLink{text=");
        outline107.append((Object) this.text);
        outline107.append(", tag=");
        outline107.append(this.tag);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
