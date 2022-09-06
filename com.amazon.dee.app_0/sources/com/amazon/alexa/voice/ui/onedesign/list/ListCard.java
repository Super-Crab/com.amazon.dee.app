package com.amazon.alexa.voice.ui.onedesign.list;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.list.ListCardModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class ListCard implements ListCardModel {
    public static final Parcelable.Creator<ListCard> CREATOR = new Parcelable.Creator<ListCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.list.ListCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ListCard mo2700createFromParcel(Parcel parcel) {
            return new ListCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ListCard[] mo2701newArray(int i) {
            return new ListCard[i];
        }
    };
    private final List<? extends ListCardModel.ItemModel> itemList;
    private final String listType;

    /* loaded from: classes11.dex */
    public static final class Builder {
        List<? extends ListCardModel.ItemModel> itemList = new ArrayList();
        String listType;

        public ListCard build() {
            if (this.listType != null) {
                if (this.itemList != null) {
                    return new ListCard(this);
                }
                throw new IllegalArgumentException("itemList == null");
            }
            throw new IllegalArgumentException("listType == null");
        }

        public Builder itemList(@NonNull List<? extends ListCardModel.ItemModel> list) {
            this.itemList = list;
            return this;
        }

        public Builder listType(@NonNull String str) {
            this.listType = str;
            return this;
        }
    }

    ListCard(Builder builder) {
        this.listType = builder.listType;
        this.itemList = builder.itemList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ListCard.class != obj.getClass()) {
            return false;
        }
        ListCard listCard = (ListCard) obj;
        return this.listType.equals(listCard.listType) && this.itemList.equals(listCard.itemList);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListCardModel
    @NonNull
    public List<? extends ListCardModel.ItemModel> getItemList() {
        return this.itemList;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListCardModel
    @NonNull
    public String getListType() {
        return this.listType;
    }

    public int hashCode() {
        return this.itemList.hashCode() + GeneratedOutlineSupport1.outline7(this.listType, JfifUtil.MARKER_EOI, 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListCard{listType=");
        outline107.append(this.listType);
        outline107.append(", itemList=");
        return GeneratedOutlineSupport1.outline94(outline107, this.itemList, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.listType);
        parcel.writeTypedList(this.itemList);
    }

    /* loaded from: classes11.dex */
    public static final class Item implements ListCardModel.ItemModel {
        public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() { // from class: com.amazon.alexa.voice.ui.onedesign.list.ListCard.Item.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Item mo2702createFromParcel(Parcel parcel) {
                return new Item(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Item[] mo2703newArray(int i) {
                return new Item[i];
            }
        };
        private final CharSequence name;

        /* loaded from: classes11.dex */
        public static final class Builder {
            CharSequence name;

            public Item build() {
                if (this.name != null) {
                    return new Item(this);
                }
                throw new IllegalArgumentException("name == null");
            }

            public Builder name(@NonNull CharSequence charSequence) {
                this.name = charSequence;
                return this;
            }
        }

        Item(Builder builder) {
            this.name = builder.name;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && Item.class == obj.getClass() && this.name.equals(((Item) obj).name);
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.list.ListCardModel.ItemModel
        @NonNull
        public CharSequence getName() {
            return this.name;
        }

        public int hashCode() {
            return this.name.hashCode() + JfifUtil.MARKER_EOI;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Item{name=");
            outline107.append((Object) this.name);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.name, parcel, i);
        }

        Item(Parcel parcel) {
            this.name = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
    }

    ListCard(Parcel parcel) {
        this.listType = parcel.readString();
        this.itemList = parcel.createTypedArrayList(Item.CREATOR);
    }
}
