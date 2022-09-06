package com.amazon.leaderselection;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.Locale;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class Candidate implements Parcelable {
    static final String UNKNOWN_PACKAGE_NAME = "unknown";
    static final String UNKNOWN_SERVICE_NAME = "unknown";
    private final AccountIdentifier accountIdentifier;
    private final CandidateRegistration candidateRegistration;
    private final ComponentName componentName;
    private final PreferredOpinion preferredOpinion;
    private static final String TAG = Candidate.class.getSimpleName();
    public static final Candidate UNKNOWN = create(CandidateRegistration.UNKNOWN, new ComponentName("unknown", "unknown"), AccountIdentifier.UNKNOWN, PreferredOpinion.UNKNOWN);
    public static final Parcelable.Creator<Candidate> CREATOR = new a();

    /* loaded from: classes12.dex */
    static class a implements Parcelable.Creator<Candidate> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Candidate mo4078createFromParcel(Parcel parcel) {
            return new Candidate(parcel, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Candidate[] mo4079newArray(int i) {
            return new Candidate[i];
        }
    }

    private Candidate(Parcel parcel) {
        this.candidateRegistration = (CandidateRegistration) parcel.readParcelable(CandidateRegistration.class.getClassLoader());
        this.componentName = (ComponentName) parcel.readParcelable(ComponentName.class.getClassLoader());
        this.accountIdentifier = (AccountIdentifier) parcel.readParcelable(AccountIdentifier.class.getClassLoader());
        this.preferredOpinion = (PreferredOpinion) parcel.readSerializable();
    }

    /* synthetic */ Candidate(Parcel parcel, a aVar) {
        this(parcel);
    }

    private Candidate(CandidateRegistration candidateRegistration, ComponentName componentName, AccountIdentifier accountIdentifier, PreferredOpinion preferredOpinion) {
        this.candidateRegistration = candidateRegistration;
        this.componentName = componentName;
        this.accountIdentifier = accountIdentifier;
        this.preferredOpinion = preferredOpinion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Candidate create(CandidateRegistration candidateRegistration, ComponentName componentName, AccountIdentifier accountIdentifier, PreferredOpinion preferredOpinion) {
        return (candidateRegistration == null || componentName == null || accountIdentifier == null) ? UNKNOWN : new Candidate(candidateRegistration, componentName, accountIdentifier, preferredOpinion);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Candidate create(c cVar, AccountIdentifier accountIdentifier, PreferredOpinion preferredOpinion) {
        return create(cVar.a(), cVar.b(), accountIdentifier, preferredOpinion);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Candidate createThisCandidate(Context context, f fVar, v vVar) {
        ComponentName componentName = new ComponentName(context, LeaderSelectionService.class);
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(componentName, 128);
            int i = serviceInfo.applicationInfo.metaData.getInt(CandidateRegistration.METADATA_REGISTRATION_KEY, 0);
            if (i == 0) {
                String str = TAG;
                Log.e(str, "This candidate is missing a resource for registration: " + componentName);
                return UNKNOWN;
            }
            CandidateRegistration a2 = fVar.a(i, serviceInfo);
            if (a2.isValid()) {
                return create(a2, componentName, AccountIdentifier.UNKNOWN, vVar.a(componentName, a2) ? PreferredOpinion.PREFERRED : PreferredOpinion.NOT_PREFERRED);
            }
            String str2 = TAG;
            Log.e(str2, "This candidate registration was invalid: " + componentName);
            return UNKNOWN;
        } catch (PackageManager.NameNotFoundException unused) {
            String str3 = TAG;
            Log.e(str3, "This candidate no longer exists on the system: " + componentName);
            return UNKNOWN;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Candidate.class == obj.getClass()) {
            return Objects.equals(getComponentName(), ((Candidate) obj).getComponentName());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccountIdentifier getAccountIdentifier() {
        return this.accountIdentifier;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CandidateRole getCandidateRole() {
        return this.candidateRegistration.getRole();
    }

    public ComponentName getComponentName() {
        return this.componentName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LeaderSelectionServiceVersion getLeaderSelectionServiceVersion() {
        return this.candidateRegistration.getVersion();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPackageName() {
        return this.componentName.getPackageName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreferredOpinion getPreferredOpinion() {
        return this.preferredOpinion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getServiceName() {
        return this.componentName.getClassName();
    }

    public int hashCode() {
        return Objects.hash(getComponentName());
    }

    public String toString() {
        return String.format(Locale.US, "%s: %s.%s.%s", Candidate.class.getSimpleName(), this.componentName, this.accountIdentifier, this.candidateRegistration);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.candidateRegistration, i);
        parcel.writeParcelable(this.componentName, i);
        parcel.writeParcelable(this.accountIdentifier, i);
        parcel.writeSerializable(this.preferredOpinion);
    }
}
