package com.typesafe.config.impl;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SubstitutionExpression {
    private final boolean optional;
    private final Path path;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubstitutionExpression(Path path, boolean z) {
        this.path = path;
        this.optional = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubstitutionExpression changePath(Path path) {
        return path == this.path ? this : new SubstitutionExpression(path, this.optional);
    }

    public boolean equals(Object obj) {
        if (obj instanceof SubstitutionExpression) {
            SubstitutionExpression substitutionExpression = (SubstitutionExpression) obj;
            return substitutionExpression.path.equals(this.path) && substitutionExpression.optional == this.optional;
        }
        return false;
    }

    public int hashCode() {
        return (((this.path.hashCode() + 41) * 41) + (this.optional ? 1 : 0)) * 41;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean optional() {
        return this.optional;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path path() {
        return this.path;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("${");
        outline107.append(this.optional ? WebConstants.UriConstants.QUESTIONMARK_KEY : "");
        return GeneratedOutlineSupport1.outline91(outline107, this.path.render(), EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
