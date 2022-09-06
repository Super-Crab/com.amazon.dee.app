package com.amazon.alexa.voice.ui.onedesign.list;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class ListItem implements ListItemModel {
    private final CharSequence name;
    private final Object tag;

    /* loaded from: classes11.dex */
    public static final class Builder {
        CharSequence name;
        Object tag;

        public ListItem build() {
            if (this.tag != null) {
                if (this.name != null) {
                    return new ListItem(this);
                }
                throw new IllegalArgumentException("name == null");
            }
            throw new IllegalArgumentException("tag == null");
        }

        public Builder name(@NonNull CharSequence charSequence) {
            this.name = charSequence;
            return this;
        }

        public Builder tag(@NonNull Object obj) {
            this.tag = obj;
            return this;
        }
    }

    ListItem(Builder builder) {
        this.tag = builder.tag;
        this.name = builder.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ListItem.class != obj.getClass()) {
            return false;
        }
        ListItem listItem = (ListItem) obj;
        return this.tag.equals(listItem.tag) && this.name.equals(listItem.name);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListItemModel
    @NonNull
    public CharSequence getName() {
        return this.name;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListItemModel
    @NonNull
    public Object getTag() {
        return this.tag;
    }

    public int hashCode() {
        return this.name.hashCode() + ((this.tag.hashCode() + JfifUtil.MARKER_EOI) * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListItem{tag=");
        outline107.append(this.tag);
        outline107.append(", name=");
        outline107.append((Object) this.name);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
