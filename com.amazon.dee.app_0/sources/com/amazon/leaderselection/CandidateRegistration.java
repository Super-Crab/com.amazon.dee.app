package com.amazon.leaderselection;

import android.os.Parcel;
import android.os.Parcelable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class CandidateRegistration implements Parcelable {
    public static final String METADATA_REGISTRATION_KEY = "com.amazon.leaderselection.registration";
    public static final int NO_REGISTRATION_RESOUCE_ID = 0;
    private CandidateRole role;
    private LeaderSelectionServiceVersion version;
    private static final String TAG = CandidateRegistration.class.getSimpleName();
    public static final CandidateRegistration UNKNOWN = new CandidateRegistration(CandidateRole.NONE, LeaderSelectionServiceVersion.UNKNOWN);
    public static final Parcelable.Creator<CandidateRegistration> CREATOR = new a();

    /* loaded from: classes12.dex */
    static class a implements Parcelable.Creator<CandidateRegistration> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public CandidateRegistration mo4080createFromParcel(Parcel parcel) {
            return new CandidateRegistration(parcel, (a) null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public CandidateRegistration[] mo4081newArray(int i) {
            return new CandidateRegistration[i];
        }
    }

    private CandidateRegistration(Parcel parcel) {
        this.role = (CandidateRole) parcel.readSerializable();
        this.version = (LeaderSelectionServiceVersion) parcel.readParcelable(LeaderSelectionServiceVersion.class.getClassLoader());
    }

    /* synthetic */ CandidateRegistration(Parcel parcel, a aVar) {
        this(parcel);
    }

    private CandidateRegistration(CandidateRole candidateRole, LeaderSelectionServiceVersion leaderSelectionServiceVersion) {
        this.role = candidateRole;
        this.version = leaderSelectionServiceVersion;
    }

    public static CandidateRegistration create(CandidateRole candidateRole, LeaderSelectionServiceVersion leaderSelectionServiceVersion) {
        return new CandidateRegistration(candidateRole, leaderSelectionServiceVersion);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CandidateRole getRole() {
        return this.role;
    }

    public LeaderSelectionServiceVersion getVersion() {
        return this.version;
    }

    public boolean isValid() {
        LeaderSelectionServiceVersion leaderSelectionServiceVersion;
        CandidateRole candidateRole = this.role;
        return (candidateRole == null || (leaderSelectionServiceVersion = this.version) == null || candidateRole == CandidateRole.NONE || !leaderSelectionServiceVersion.isValid()) ? false : true;
    }

    public String toString() {
        return CandidateRegistration.class.getSimpleName() + ":" + this.role + "." + this.version;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.role);
        parcel.writeParcelable(this.version, i);
    }
}
