package com.amazon.alexa.voice.ui.onedesign.header;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class SingleLineHeader implements SingleLineHeaderModel {
    private final CharSequence title;

    public SingleLineHeader(@NonNull CharSequence charSequence) {
        this.title = charSequence;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && SingleLineHeader.class == obj.getClass() && this.title.equals(((SingleLineHeader) obj).title);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.header.SingleLineHeaderModel
    @NonNull
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.title.hashCode() + JfifUtil.MARKER_EOI;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SingleLineHeader{title=");
        outline107.append((Object) this.title);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
