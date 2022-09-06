package com.amazon.leaderselection;

import android.content.ComponentName;
import android.content.Context;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class v {
    private final Set<w> a;

    private v(Set<w> set) {
        this.a = set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static v a(Context context) {
        String[] stringArray = context.getResources().getStringArray(R.array.preferred_candidate_information_package_names);
        String[] stringArray2 = context.getResources().getStringArray(R.array.preferred_candidate_information_versions);
        if (stringArray.length == stringArray2.length) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (int i = 0; i < stringArray.length; i++) {
                linkedHashSet.add(w.b(stringArray[i], LeaderSelectionServiceVersion.create(stringArray2[i])));
            }
            return a(linkedHashSet);
        }
        throw new IllegalStateException("Mismatched number of preferred package names and versions");
    }

    static v a(Set<w> set) {
        return new v(set);
    }

    public boolean a(ComponentName componentName, CandidateRegistration candidateRegistration) {
        for (w wVar : this.a) {
            if (wVar.a(componentName.getPackageName(), candidateRegistration.getVersion())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Candidate candidate) {
        for (w wVar : this.a) {
            if (wVar.a(candidate)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Candidate candidate, Candidate candidate2) {
        if (candidate == null || !CandidateRole.canLead(candidate.getCandidateRole())) {
            return false;
        }
        if (candidate2 == null || !CandidateRole.canLead(candidate2.getCandidateRole())) {
            return true;
        }
        Iterator<w> it2 = this.a.iterator();
        if (!it2.hasNext()) {
            return false;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        do {
            w next = it2.next();
            if (next.a(candidate)) {
                i = i3;
            }
            if (next.a(candidate2)) {
                i2 = i3;
            }
            i3++;
        } while (it2.hasNext());
        return i < i2;
    }
}
