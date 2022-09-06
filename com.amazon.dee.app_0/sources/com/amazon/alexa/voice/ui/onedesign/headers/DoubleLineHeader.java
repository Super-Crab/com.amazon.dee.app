package com.amazon.alexa.voice.ui.onedesign.headers;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class DoubleLineHeader implements DoubleLineHeaderModel {
    private final CharSequence subTitle;
    private final CharSequence title;

    public DoubleLineHeader(@NonNull CharSequence charSequence, @NonNull CharSequence charSequence2) {
        this.title = charSequence;
        this.subTitle = charSequence2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DoubleLineHeader.class != obj.getClass()) {
            return false;
        }
        DoubleLineHeader doubleLineHeader = (DoubleLineHeader) obj;
        return this.title.equals(doubleLineHeader.title) && this.subTitle.equals(doubleLineHeader.subTitle);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.headers.DoubleLineHeaderModel
    @NonNull
    public CharSequence getSubTitle() {
        return this.subTitle;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.headers.DoubleLineHeaderModel
    @NonNull
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.subTitle.hashCode() + GeneratedOutlineSupport1.outline5(this.title, JfifUtil.MARKER_EOI, 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DoubleLineHeader{title=");
        outline107.append((Object) this.title);
        outline107.append(", subTitle=");
        outline107.append((Object) this.subTitle);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
