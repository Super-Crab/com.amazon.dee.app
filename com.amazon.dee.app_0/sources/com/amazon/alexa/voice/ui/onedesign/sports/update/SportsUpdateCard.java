package com.amazon.alexa.voice.ui.onedesign.sports.update;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.sports.update.game.Game;
import com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class SportsUpdateCard implements SportsUpdateCardModel {
    public static final Parcelable.Creator<SportsUpdateCard> CREATOR = new Parcelable.Creator<SportsUpdateCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public SportsUpdateCard mo2742createFromParcel(Parcel parcel) {
            return new SportsUpdateCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public SportsUpdateCard[] mo2743newArray(int i) {
            return new SportsUpdateCard[i];
        }
    };
    private final List<? extends GameModel> gameList;
    private final CharSequence title;

    /* loaded from: classes11.dex */
    public static final class Builder {
        List<? extends GameModel> gameList = new ArrayList();
        CharSequence title;

        public SportsUpdateCard build() {
            if (this.title != null) {
                if (this.gameList != null) {
                    return new SportsUpdateCard(this);
                }
                throw new IllegalArgumentException("gameList == null");
            }
            throw new IllegalArgumentException("title == null");
        }

        public Builder gameList(@NonNull List<? extends GameModel> list) {
            this.gameList = list;
            return this;
        }

        public Builder title(@NonNull CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }
    }

    SportsUpdateCard(Builder builder) {
        this.title = builder.title;
        this.gameList = builder.gameList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SportsUpdateCard.class != obj.getClass()) {
            return false;
        }
        SportsUpdateCard sportsUpdateCard = (SportsUpdateCard) obj;
        return this.title.equals(sportsUpdateCard.title) && this.gameList.equals(sportsUpdateCard.gameList);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateCardModel
    @NonNull
    public List<? extends GameModel> getGameList() {
        return this.gameList;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateCardModel
    @NonNull
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.gameList.hashCode() + GeneratedOutlineSupport1.outline5(this.title, JfifUtil.MARKER_EOI, 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SportsUpdateCard{title=");
        outline107.append((Object) this.title);
        outline107.append(", gameList=");
        return GeneratedOutlineSupport1.outline94(outline107, this.gameList, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        parcel.writeTypedList(this.gameList);
    }

    SportsUpdateCard(Parcel parcel) {
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.gameList = parcel.createTypedArrayList(Game.CREATOR);
    }
}
