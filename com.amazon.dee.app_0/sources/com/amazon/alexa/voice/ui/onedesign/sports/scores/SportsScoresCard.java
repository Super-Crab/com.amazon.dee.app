package com.amazon.alexa.voice.ui.onedesign.sports.scores;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class SportsScoresCard implements SportsScoresCardModel {
    public static final Parcelable.Creator<SportsScoresCard> CREATOR = new Parcelable.Creator<SportsScoresCard>() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public SportsScoresCard mo2732createFromParcel(Parcel parcel) {
            return new SportsScoresCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public SportsScoresCard[] mo2733newArray(int i) {
            return new SportsScoresCard[i];
        }
    };
    private final SportsScoresCardModel.TeamStandingModel awayTeamStanding;
    private final SportsScoresCardModel.TeamStandingModel homeTeamStanding;
    private final SportsScoresCardModel.NextGameModel nextGame;
    private final SportsScoresCardModel.ScoreComponentsModel scores;
    private final CharSequence sportName;
    private final CharSequence subTitle;
    private final CharSequence title;

    /* loaded from: classes11.dex */
    public static final class Builder {
        SportsScoresCardModel.TeamStandingModel awayTeamStanding;
        SportsScoresCardModel.TeamStandingModel homeTeamStanding;
        SportsScoresCardModel.NextGameModel nextGame;
        SportsScoresCardModel.ScoreComponentsModel scores;
        CharSequence sportName;
        CharSequence subTitle;
        CharSequence title;

        public Builder awayTeamStanding(@NonNull SportsScoresCardModel.TeamStandingModel teamStandingModel) {
            this.awayTeamStanding = teamStandingModel;
            return this;
        }

        public SportsScoresCard build() {
            if (this.title != null) {
                if (this.subTitle != null) {
                    if (this.homeTeamStanding != null) {
                        if (this.awayTeamStanding != null) {
                            if (this.scores != null) {
                                return new SportsScoresCard(this);
                            }
                            throw new IllegalArgumentException("scores == null");
                        }
                        throw new IllegalArgumentException("awayTeamStanding == null");
                    }
                    throw new IllegalArgumentException("homeTeamStanding == null");
                }
                throw new IllegalArgumentException("subTitle == null");
            }
            throw new IllegalArgumentException("title == null");
        }

        public Builder homeTeamStanding(@NonNull SportsScoresCardModel.TeamStandingModel teamStandingModel) {
            this.homeTeamStanding = teamStandingModel;
            return this;
        }

        public Builder nextGame(SportsScoresCardModel.NextGameModel nextGameModel) {
            this.nextGame = nextGameModel;
            return this;
        }

        public Builder scores(@NonNull SportsScoresCardModel.ScoreComponentsModel scoreComponentsModel) {
            this.scores = scoreComponentsModel;
            return this;
        }

        public Builder sportName(CharSequence charSequence) {
            this.sportName = charSequence;
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

    SportsScoresCard(Builder builder) {
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.sportName = builder.sportName;
        this.homeTeamStanding = builder.homeTeamStanding;
        this.awayTeamStanding = builder.awayTeamStanding;
        this.scores = builder.scores;
        this.nextGame = builder.nextGame;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SportsScoresCard.class != obj.getClass()) {
            return false;
        }
        SportsScoresCard sportsScoresCard = (SportsScoresCard) obj;
        if (!this.title.equals(sportsScoresCard.title) || !this.subTitle.equals(sportsScoresCard.subTitle)) {
            return false;
        }
        CharSequence charSequence = this.sportName;
        if (charSequence == null ? sportsScoresCard.sportName != null : !charSequence.equals(sportsScoresCard.sportName)) {
            return false;
        }
        if (!this.homeTeamStanding.equals(sportsScoresCard.homeTeamStanding) || !this.awayTeamStanding.equals(sportsScoresCard.awayTeamStanding) || !this.scores.equals(sportsScoresCard.scores)) {
            return false;
        }
        SportsScoresCardModel.NextGameModel nextGameModel = this.nextGame;
        SportsScoresCardModel.NextGameModel nextGameModel2 = sportsScoresCard.nextGame;
        return nextGameModel == null ? nextGameModel2 == null : nextGameModel.equals(nextGameModel2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel
    @NonNull
    public SportsScoresCardModel.TeamStandingModel getAwayTeamStanding() {
        return this.awayTeamStanding;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel
    @NonNull
    public SportsScoresCardModel.TeamStandingModel getHomeTeamStanding() {
        return this.homeTeamStanding;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel
    public SportsScoresCardModel.NextGameModel getNextGame() {
        return this.nextGame;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel
    @NonNull
    public SportsScoresCardModel.ScoreComponentsModel getScores() {
        return this.scores;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel
    public CharSequence getSportName() {
        return this.sportName;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel
    @NonNull
    public CharSequence getSubTitle() {
        return this.subTitle;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel
    @NonNull
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        int outline5 = GeneratedOutlineSupport1.outline5(this.subTitle, GeneratedOutlineSupport1.outline5(this.title, JfifUtil.MARKER_EOI, 31), 31);
        CharSequence charSequence = this.sportName;
        int i = 0;
        int hashCode = charSequence != null ? charSequence.hashCode() : 0;
        int hashCode2 = this.homeTeamStanding.hashCode();
        int hashCode3 = (this.scores.hashCode() + ((this.awayTeamStanding.hashCode() + ((hashCode2 + ((outline5 + hashCode) * 31)) * 31)) * 31)) * 31;
        SportsScoresCardModel.NextGameModel nextGameModel = this.nextGame;
        if (nextGameModel != null) {
            i = nextGameModel.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SportsScoresCard{title=");
        outline107.append((Object) this.title);
        outline107.append(", subTitle=");
        outline107.append((Object) this.subTitle);
        outline107.append(", sportName=");
        outline107.append((Object) this.sportName);
        outline107.append(", homeTeamStanding=");
        outline107.append(this.homeTeamStanding);
        outline107.append(", awayTeamStanding=");
        outline107.append(this.awayTeamStanding);
        outline107.append(", scores=");
        outline107.append(this.scores);
        outline107.append(", nextGame=");
        outline107.append(this.nextGame);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        TextUtils.writeToParcel(this.subTitle, parcel, i);
        TextUtils.writeToParcel(this.sportName, parcel, i);
        parcel.writeParcelable(this.homeTeamStanding, i);
        parcel.writeParcelable(this.awayTeamStanding, i);
        parcel.writeParcelable(this.scores, i);
        parcel.writeParcelable(this.nextGame, i);
    }

    /* loaded from: classes11.dex */
    public static final class TeamStanding implements SportsScoresCardModel.TeamStandingModel {
        public static final Parcelable.Creator<TeamStanding> CREATOR = new Parcelable.Creator<TeamStanding>() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCard.TeamStanding.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public TeamStanding mo2738createFromParcel(Parcel parcel) {
                return new TeamStanding(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public TeamStanding[] mo2739newArray(int i) {
                return new TeamStanding[i];
            }
        };
        private final String imageUrl;
        private final boolean isAhead;
        private final CharSequence points;
        private final CharSequence teamName;

        /* loaded from: classes11.dex */
        public static final class Builder {
            String imageUrl;
            boolean isAhead;
            CharSequence points;
            CharSequence teamName;

            public TeamStanding build() {
                if (this.imageUrl != null) {
                    if (this.teamName != null) {
                        if (this.points != null) {
                            return new TeamStanding(this);
                        }
                        throw new IllegalArgumentException("points == null");
                    }
                    throw new IllegalArgumentException("teamName == null");
                }
                throw new IllegalArgumentException("imageUrl == null");
            }

            public Builder imageUrl(@NonNull String str) {
                this.imageUrl = str;
                return this;
            }

            public Builder isAhead(boolean z) {
                this.isAhead = z;
                return this;
            }

            public Builder points(@NonNull CharSequence charSequence) {
                this.points = charSequence;
                return this;
            }

            public Builder teamName(@NonNull CharSequence charSequence) {
                this.teamName = charSequence;
                return this;
            }
        }

        TeamStanding(Builder builder) {
            this.imageUrl = builder.imageUrl;
            this.teamName = builder.teamName;
            this.points = builder.points;
            this.isAhead = builder.isAhead;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || TeamStanding.class != obj.getClass()) {
                return false;
            }
            TeamStanding teamStanding = (TeamStanding) obj;
            return this.imageUrl.equals(teamStanding.imageUrl) && this.teamName.equals(teamStanding.teamName) && this.points.equals(teamStanding.points) && this.isAhead == teamStanding.isAhead;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.TeamStandingModel
        @NonNull
        public String getImageUrl() {
            return this.imageUrl;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.TeamStandingModel
        public boolean getIsAhead() {
            return this.isAhead;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.TeamStandingModel
        @NonNull
        public CharSequence getPoints() {
            return this.points;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.TeamStandingModel
        @NonNull
        public CharSequence getTeamName() {
            return this.teamName;
        }

        public int hashCode() {
            return GeneratedOutlineSupport1.outline5(this.points, GeneratedOutlineSupport1.outline5(this.teamName, GeneratedOutlineSupport1.outline7(this.imageUrl, JfifUtil.MARKER_EOI, 31), 31), 31) + (this.isAhead ? 1 : 0);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TeamStanding{imageUrl=");
            outline107.append(this.imageUrl);
            outline107.append(", teamName=");
            outline107.append((Object) this.teamName);
            outline107.append(", points=");
            outline107.append((Object) this.points);
            outline107.append(", isAhead=");
            outline107.append(this.isAhead);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.imageUrl);
            TextUtils.writeToParcel(this.teamName, parcel, i);
            TextUtils.writeToParcel(this.points, parcel, i);
            parcel.writeByte(this.isAhead ? (byte) 1 : (byte) 0);
        }

        TeamStanding(Parcel parcel) {
            this.imageUrl = parcel.readString();
            this.teamName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.points = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.isAhead = parcel.readByte() != 0;
        }
    }

    /* loaded from: classes11.dex */
    public static final class NextGame implements SportsScoresCardModel.NextGameModel {
        public static final Parcelable.Creator<NextGame> CREATOR = new Parcelable.Creator<NextGame>() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCard.NextGame.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public NextGame mo2734createFromParcel(Parcel parcel) {
                return new NextGame(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public NextGame[] mo2735newArray(int i) {
                return new NextGame[i];
            }
        };
        private final CharSequence date;
        private final boolean hostedByHomeTeam;
        private final CharSequence opponent;
        private final CharSequence time;
        private final CharSequence title;

        /* loaded from: classes11.dex */
        public static final class Builder {
            CharSequence date;
            boolean hostedByHomeTeam;
            CharSequence opponent;
            CharSequence time;
            CharSequence title;

            public NextGame build() {
                if (this.title != null) {
                    if (this.date != null) {
                        if (this.time != null) {
                            if (this.opponent != null) {
                                return new NextGame(this);
                            }
                            throw new IllegalArgumentException("opponent == null");
                        }
                        throw new IllegalArgumentException("time == null");
                    }
                    throw new IllegalArgumentException("date == null");
                }
                throw new IllegalArgumentException("title == null");
            }

            public Builder date(@NonNull CharSequence charSequence) {
                this.date = charSequence;
                return this;
            }

            public Builder hostedByHomeTeam(boolean z) {
                this.hostedByHomeTeam = z;
                return this;
            }

            public Builder opponent(@NonNull CharSequence charSequence) {
                this.opponent = charSequence;
                return this;
            }

            public Builder time(@NonNull CharSequence charSequence) {
                this.time = charSequence;
                return this;
            }

            public Builder title(@NonNull CharSequence charSequence) {
                this.title = charSequence;
                return this;
            }
        }

        NextGame(Builder builder) {
            this.title = builder.title;
            this.date = builder.date;
            this.time = builder.time;
            this.hostedByHomeTeam = builder.hostedByHomeTeam;
            this.opponent = builder.opponent;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || NextGame.class != obj.getClass()) {
                return false;
            }
            NextGame nextGame = (NextGame) obj;
            return this.title.equals(nextGame.title) && this.date.equals(nextGame.date) && this.time.equals(nextGame.time) && this.hostedByHomeTeam == nextGame.hostedByHomeTeam && this.opponent.equals(nextGame.opponent);
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.NextGameModel
        @NonNull
        public CharSequence getDate() {
            return this.date;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.NextGameModel
        @NonNull
        public CharSequence getOpponent() {
            return this.opponent;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.NextGameModel
        @NonNull
        public CharSequence getTime() {
            return this.time;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.NextGameModel
        @NonNull
        public CharSequence getTitle() {
            return this.title;
        }

        public int hashCode() {
            int outline5 = GeneratedOutlineSupport1.outline5(this.date, GeneratedOutlineSupport1.outline5(this.title, JfifUtil.MARKER_EOI, 31), 31);
            return this.opponent.hashCode() + ((GeneratedOutlineSupport1.outline5(this.time, outline5, 31) + (this.hostedByHomeTeam ? 1 : 0)) * 31);
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.NextGameModel
        public boolean isHostedByHomeTeam() {
            return this.hostedByHomeTeam;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NextGame{title=");
            outline107.append((Object) this.title);
            outline107.append(", date=");
            outline107.append((Object) this.date);
            outline107.append(", time=");
            outline107.append((Object) this.time);
            outline107.append(", hostedByHomeTeam=");
            outline107.append(this.hostedByHomeTeam);
            outline107.append(", opponent=");
            outline107.append((Object) this.opponent);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.title, parcel, i);
            TextUtils.writeToParcel(this.date, parcel, i);
            TextUtils.writeToParcel(this.time, parcel, i);
            parcel.writeByte(this.hostedByHomeTeam ? (byte) 1 : (byte) 0);
            TextUtils.writeToParcel(this.opponent, parcel, i);
        }

        NextGame(Parcel parcel) {
            this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.date = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.time = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.hostedByHomeTeam = parcel.readByte() != 0;
            this.opponent = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
    }

    /* loaded from: classes11.dex */
    public static final class ScoreComponents implements SportsScoresCardModel.ScoreComponentsModel {
        public static final Parcelable.Creator<ScoreComponents> CREATOR = new Parcelable.Creator<ScoreComponents>() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCard.ScoreComponents.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public ScoreComponents mo2736createFromParcel(Parcel parcel) {
                return new ScoreComponents(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public ScoreComponents[] mo2737newArray(int i) {
                return new ScoreComponents[i];
            }
        };
        private final List<CharSequence> awayTeamScoreComponents;
        private final CharSequence awayTeamShortName;
        private final List<CharSequence> homeTeamScoreComponents;
        private final CharSequence homeTeamShortName;
        private final List<CharSequence> scoreComponentsHeadings;

        /* loaded from: classes11.dex */
        public static final class Builder {
            CharSequence awayTeamShortName;
            CharSequence homeTeamShortName;
            List<CharSequence> homeTeamScoreComponents = new ArrayList();
            List<CharSequence> awayTeamScoreComponents = new ArrayList();
            List<CharSequence> scoreComponentsHeadings = new ArrayList();

            public Builder awayTeamScoreComponents(@NonNull List<CharSequence> list) {
                this.awayTeamScoreComponents = list;
                return this;
            }

            public Builder awayTeamShortName(@NonNull CharSequence charSequence) {
                this.awayTeamShortName = charSequence;
                return this;
            }

            public ScoreComponents build() {
                if (this.homeTeamShortName != null) {
                    if (this.awayTeamShortName != null) {
                        if (this.homeTeamScoreComponents != null) {
                            if (this.awayTeamScoreComponents != null) {
                                if (this.scoreComponentsHeadings != null) {
                                    return new ScoreComponents(this);
                                }
                                throw new IllegalArgumentException("scoreComponentsHeadings == null");
                            }
                            throw new IllegalArgumentException("awayTeamScoreComponents == null");
                        }
                        throw new IllegalArgumentException("homeTeamScoreComponents == null");
                    }
                    throw new IllegalArgumentException("awayTeamShortName == null");
                }
                throw new IllegalArgumentException("homeTeamShortName == null");
            }

            public Builder homeTeamScoreComponents(@NonNull List<CharSequence> list) {
                this.homeTeamScoreComponents = list;
                return this;
            }

            public Builder homeTeamShortName(@NonNull CharSequence charSequence) {
                this.homeTeamShortName = charSequence;
                return this;
            }

            public Builder scoreComponentsHeadings(@NonNull List<CharSequence> list) {
                this.scoreComponentsHeadings = list;
                return this;
            }
        }

        ScoreComponents(Builder builder) {
            this.homeTeamShortName = builder.homeTeamShortName;
            this.awayTeamShortName = builder.awayTeamShortName;
            this.homeTeamScoreComponents = builder.homeTeamScoreComponents;
            this.awayTeamScoreComponents = builder.awayTeamScoreComponents;
            this.scoreComponentsHeadings = builder.scoreComponentsHeadings;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ScoreComponents.class != obj.getClass()) {
                return false;
            }
            ScoreComponents scoreComponents = (ScoreComponents) obj;
            return this.homeTeamShortName.equals(scoreComponents.homeTeamShortName) && this.awayTeamShortName.equals(scoreComponents.awayTeamShortName) && this.homeTeamScoreComponents.equals(scoreComponents.homeTeamScoreComponents) && this.awayTeamScoreComponents.equals(scoreComponents.awayTeamScoreComponents) && this.scoreComponentsHeadings.equals(scoreComponents.scoreComponentsHeadings);
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.ScoreComponentsModel
        @NonNull
        public List<CharSequence> getAwayTeamScoreComponents() {
            return this.awayTeamScoreComponents;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.ScoreComponentsModel
        @NonNull
        public CharSequence getAwayTeamShortName() {
            return this.awayTeamShortName;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.ScoreComponentsModel
        @NonNull
        public List<CharSequence> getHomeTeamScoreComponents() {
            return this.homeTeamScoreComponents;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.ScoreComponentsModel
        @NonNull
        public CharSequence getHomeTeamShortName() {
            return this.homeTeamShortName;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel.ScoreComponentsModel
        @NonNull
        public List<CharSequence> getScoreComponentsHeadings() {
            return this.scoreComponentsHeadings;
        }

        public int hashCode() {
            int outline5 = GeneratedOutlineSupport1.outline5(this.awayTeamShortName, GeneratedOutlineSupport1.outline5(this.homeTeamShortName, JfifUtil.MARKER_EOI, 31), 31);
            int hashCode = this.awayTeamScoreComponents.hashCode();
            return this.scoreComponentsHeadings.hashCode() + ((hashCode + ((this.homeTeamScoreComponents.hashCode() + outline5) * 31)) * 31);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ScoreComponents{homeTeamShortName=");
            outline107.append((Object) this.homeTeamShortName);
            outline107.append(", awayTeamShortName=");
            outline107.append((Object) this.awayTeamShortName);
            outline107.append(", homeTeamScoreComponents=");
            outline107.append(this.homeTeamScoreComponents);
            outline107.append(", awayTeamScoreComponents=");
            outline107.append(this.awayTeamScoreComponents);
            outline107.append(", scoreComponentsHeadings=");
            return GeneratedOutlineSupport1.outline94(outline107, this.scoreComponentsHeadings, JsonReaderKt.END_OBJ);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.homeTeamShortName, parcel, i);
            TextUtils.writeToParcel(this.awayTeamShortName, parcel, i);
            List<CharSequence> list = this.homeTeamScoreComponents;
            if (list != null) {
                parcel.writeInt(list.size());
                for (CharSequence charSequence : this.homeTeamScoreComponents) {
                    TextUtils.writeToParcel(charSequence, parcel, i);
                }
            } else {
                parcel.writeInt(0);
            }
            List<CharSequence> list2 = this.awayTeamScoreComponents;
            if (list2 != null) {
                parcel.writeInt(list2.size());
                for (CharSequence charSequence2 : this.awayTeamScoreComponents) {
                    TextUtils.writeToParcel(charSequence2, parcel, i);
                }
            } else {
                parcel.writeInt(0);
            }
            List<CharSequence> list3 = this.scoreComponentsHeadings;
            if (list3 != null) {
                parcel.writeInt(list3.size());
                for (CharSequence charSequence3 : this.scoreComponentsHeadings) {
                    TextUtils.writeToParcel(charSequence3, parcel, i);
                }
                return;
            }
            parcel.writeInt(0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        ScoreComponents(Parcel parcel) {
            this.homeTeamShortName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.awayTeamShortName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            int readInt = parcel.readInt();
            this.homeTeamScoreComponents = new ArrayList(readInt);
            for (int i = 0; i < readInt; i++) {
                this.homeTeamScoreComponents.add(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
            }
            int readInt2 = parcel.readInt();
            this.awayTeamScoreComponents = new ArrayList(readInt2);
            for (int i2 = 0; i2 < readInt2; i2++) {
                this.awayTeamScoreComponents.add(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
            }
            int readInt3 = parcel.readInt();
            this.scoreComponentsHeadings = new ArrayList(readInt3);
            for (int i3 = 0; i3 < readInt3; i3++) {
                this.scoreComponentsHeadings.add(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
            }
        }
    }

    SportsScoresCard(Parcel parcel) {
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.subTitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.sportName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.homeTeamStanding = (SportsScoresCardModel.TeamStandingModel) parcel.readParcelable(SportsScoresCardModel.TeamStandingModel.class.getClassLoader());
        this.awayTeamStanding = (SportsScoresCardModel.TeamStandingModel) parcel.readParcelable(SportsScoresCardModel.TeamStandingModel.class.getClassLoader());
        this.scores = (SportsScoresCardModel.ScoreComponentsModel) parcel.readParcelable(SportsScoresCardModel.ScoreComponentsModel.class.getClassLoader());
        this.nextGame = (SportsScoresCardModel.NextGameModel) parcel.readParcelable(SportsScoresCardModel.NextGameModel.class.getClassLoader());
    }
}
