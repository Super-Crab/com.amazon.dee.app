package com.amazon.alexa.voice.ui.onedesign.sports.update.game;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class Game implements GameModel {
    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.update.game.Game.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Game mo2744createFromParcel(Parcel parcel) {
            return new Game(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Game[] mo2745newArray(int i) {
            return new Game[i];
        }
    };
    private final GameModel.TeamScoreModel firstTeamScore;
    private final boolean inProgress;
    private final CharSequence leagueName;
    private final GameModel.TeamScoreModel secondTeamScore;
    private final CharSequence temporalIndicator;

    /* loaded from: classes11.dex */
    public static final class Builder {
        GameModel.TeamScoreModel firstTeamScore;
        boolean inProgress;
        CharSequence leagueName;
        GameModel.TeamScoreModel secondTeamScore;
        CharSequence temporalIndicator;

        public Game build() {
            if (this.leagueName != null) {
                if (this.temporalIndicator != null) {
                    return new Game(this);
                }
                throw new IllegalArgumentException("temporalIndicator == null");
            }
            throw new IllegalArgumentException("leagueName == null");
        }

        public Builder firstTeamScore(GameModel.TeamScoreModel teamScoreModel) {
            this.firstTeamScore = teamScoreModel;
            return this;
        }

        public Builder inProgress(boolean z) {
            this.inProgress = z;
            return this;
        }

        public Builder leagueName(@NonNull CharSequence charSequence) {
            this.leagueName = charSequence;
            return this;
        }

        public Builder secondTeamScore(GameModel.TeamScoreModel teamScoreModel) {
            this.secondTeamScore = teamScoreModel;
            return this;
        }

        public Builder temporalIndicator(@NonNull CharSequence charSequence) {
            this.temporalIndicator = charSequence;
            return this;
        }
    }

    Game(Builder builder) {
        this.leagueName = builder.leagueName;
        this.temporalIndicator = builder.temporalIndicator;
        this.firstTeamScore = builder.firstTeamScore;
        this.secondTeamScore = builder.secondTeamScore;
        this.inProgress = builder.inProgress;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Game.class != obj.getClass()) {
            return false;
        }
        Game game = (Game) obj;
        if (!this.leagueName.equals(game.leagueName) || !this.temporalIndicator.equals(game.temporalIndicator)) {
            return false;
        }
        GameModel.TeamScoreModel teamScoreModel = this.firstTeamScore;
        if (teamScoreModel == null ? game.firstTeamScore != null : !teamScoreModel.equals(game.firstTeamScore)) {
            return false;
        }
        GameModel.TeamScoreModel teamScoreModel2 = this.secondTeamScore;
        if (teamScoreModel2 == null ? game.secondTeamScore != null : !teamScoreModel2.equals(game.secondTeamScore)) {
            return false;
        }
        return this.inProgress == game.inProgress;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel
    public GameModel.TeamScoreModel getFirstTeamScore() {
        return this.firstTeamScore;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel
    public boolean getInProgress() {
        return this.inProgress;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel
    @NonNull
    public CharSequence getLeagueName() {
        return this.leagueName;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel
    public GameModel.TeamScoreModel getSecondTeamScore() {
        return this.secondTeamScore;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel
    @NonNull
    public CharSequence getTemporalIndicator() {
        return this.temporalIndicator;
    }

    public int hashCode() {
        int outline5 = GeneratedOutlineSupport1.outline5(this.temporalIndicator, GeneratedOutlineSupport1.outline5(this.leagueName, JfifUtil.MARKER_EOI, 31), 31);
        GameModel.TeamScoreModel teamScoreModel = this.firstTeamScore;
        int i = 0;
        int hashCode = (outline5 + (teamScoreModel != null ? teamScoreModel.hashCode() : 0)) * 31;
        GameModel.TeamScoreModel teamScoreModel2 = this.secondTeamScore;
        if (teamScoreModel2 != null) {
            i = teamScoreModel2.hashCode();
        }
        return ((hashCode + i) * 31) + (this.inProgress ? 1 : 0);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Game{leagueName=");
        outline107.append((Object) this.leagueName);
        outline107.append(", temporalIndicator=");
        outline107.append((Object) this.temporalIndicator);
        outline107.append(", firstTeamScore=");
        outline107.append(this.firstTeamScore);
        outline107.append(", secondTeamScore=");
        outline107.append(this.secondTeamScore);
        outline107.append(", inProgress=");
        outline107.append(this.inProgress);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.leagueName, parcel, i);
        TextUtils.writeToParcel(this.temporalIndicator, parcel, i);
        parcel.writeParcelable(this.firstTeamScore, i);
        parcel.writeParcelable(this.secondTeamScore, i);
        parcel.writeByte(this.inProgress ? (byte) 1 : (byte) 0);
    }

    /* loaded from: classes11.dex */
    public static final class TeamScore implements GameModel.TeamScoreModel {
        public static final Parcelable.Creator<TeamScore> CREATOR = new Parcelable.Creator<TeamScore>() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.update.game.Game.TeamScore.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public TeamScore mo2746createFromParcel(Parcel parcel) {
                return new TeamScore(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public TeamScore[] mo2747newArray(int i) {
                return new TeamScore[i];
            }
        };
        private final String logoUrl;
        private final CharSequence name;
        private final int score;

        /* loaded from: classes11.dex */
        public static final class Builder {
            String logoUrl;
            CharSequence name;
            int score;

            public TeamScore build() {
                if (this.name != null) {
                    return new TeamScore(this);
                }
                throw new IllegalArgumentException("name == null");
            }

            public Builder logoUrl(String str) {
                this.logoUrl = str;
                return this;
            }

            public Builder name(@NonNull CharSequence charSequence) {
                this.name = charSequence;
                return this;
            }

            public Builder score(int i) {
                this.score = i;
                return this;
            }
        }

        TeamScore(Builder builder) {
            this.name = builder.name;
            this.logoUrl = builder.logoUrl;
            this.score = builder.score;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || TeamScore.class != obj.getClass()) {
                return false;
            }
            TeamScore teamScore = (TeamScore) obj;
            if (!this.name.equals(teamScore.name)) {
                return false;
            }
            String str = this.logoUrl;
            if (str == null ? teamScore.logoUrl != null : !str.equals(teamScore.logoUrl)) {
                return false;
            }
            return this.score == teamScore.score;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel.TeamScoreModel
        public String getLogoUrl() {
            return this.logoUrl;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel.TeamScoreModel
        @NonNull
        public CharSequence getName() {
            return this.name;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel.TeamScoreModel
        public int getScore() {
            return this.score;
        }

        public int hashCode() {
            int outline5 = GeneratedOutlineSupport1.outline5(this.name, JfifUtil.MARKER_EOI, 31);
            String str = this.logoUrl;
            return ((outline5 + (str != null ? str.hashCode() : 0)) * 31) + this.score;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TeamScore{name=");
            outline107.append((Object) this.name);
            outline107.append(", logoUrl=");
            outline107.append(this.logoUrl);
            outline107.append(", score=");
            return GeneratedOutlineSupport1.outline85(outline107, this.score, JsonReaderKt.END_OBJ);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.name, parcel, i);
            parcel.writeString(this.logoUrl);
            parcel.writeInt(this.score);
        }

        TeamScore(Parcel parcel) {
            this.name = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.logoUrl = parcel.readString();
            this.score = parcel.readInt();
        }
    }

    Game(Parcel parcel) {
        this.leagueName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.temporalIndicator = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.firstTeamScore = (GameModel.TeamScoreModel) parcel.readParcelable(GameModel.TeamScoreModel.class.getClassLoader());
        this.secondTeamScore = (GameModel.TeamScoreModel) parcel.readParcelable(GameModel.TeamScoreModel.class.getClassLoader());
        this.inProgress = parcel.readByte() != 0;
    }
}
