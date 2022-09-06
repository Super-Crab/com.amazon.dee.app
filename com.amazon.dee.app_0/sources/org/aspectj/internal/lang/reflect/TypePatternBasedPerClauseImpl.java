package org.aspectj.internal.lang.reflect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.aspectj.lang.reflect.PerClauseKind;
import org.aspectj.lang.reflect.TypePattern;
import org.aspectj.lang.reflect.TypePatternBasedPerClause;
/* loaded from: classes4.dex */
public class TypePatternBasedPerClauseImpl extends PerClauseImpl implements TypePatternBasedPerClause {
    private TypePattern typePattern;

    public TypePatternBasedPerClauseImpl(PerClauseKind perClauseKind, String str) {
        super(perClauseKind);
        this.typePattern = new TypePatternImpl(str);
    }

    @Override // org.aspectj.lang.reflect.TypePatternBasedPerClause
    public TypePattern getTypePattern() {
        return this.typePattern;
    }

    @Override // org.aspectj.internal.lang.reflect.PerClauseImpl
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("pertypewithin(");
        outline107.append(this.typePattern.asString());
        outline107.append(")");
        return outline107.toString();
    }
}
