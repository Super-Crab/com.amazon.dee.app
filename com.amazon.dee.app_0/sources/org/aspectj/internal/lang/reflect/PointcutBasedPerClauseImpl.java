package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.PerClauseKind;
import org.aspectj.lang.reflect.PointcutBasedPerClause;
import org.aspectj.lang.reflect.PointcutExpression;
/* loaded from: classes4.dex */
public class PointcutBasedPerClauseImpl extends PerClauseImpl implements PointcutBasedPerClause {
    private final PointcutExpression pointcutExpression;

    /* renamed from: org.aspectj.internal.lang.reflect.PointcutBasedPerClauseImpl$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$aspectj$lang$reflect$PerClauseKind = new int[PerClauseKind.values().length];

        static {
            try {
                $SwitchMap$org$aspectj$lang$reflect$PerClauseKind[PerClauseKind.PERCFLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$aspectj$lang$reflect$PerClauseKind[PerClauseKind.PERCFLOWBELOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$aspectj$lang$reflect$PerClauseKind[PerClauseKind.PERTARGET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$aspectj$lang$reflect$PerClauseKind[PerClauseKind.PERTHIS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public PointcutBasedPerClauseImpl(PerClauseKind perClauseKind, String str) {
        super(perClauseKind);
        this.pointcutExpression = new PointcutExpressionImpl(str);
    }

    @Override // org.aspectj.lang.reflect.PointcutBasedPerClause
    public PointcutExpression getPointcutExpression() {
        return this.pointcutExpression;
    }

    @Override // org.aspectj.internal.lang.reflect.PerClauseImpl
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        int ordinal = getKind().ordinal();
        if (ordinal == 1) {
            stringBuffer.append("perthis(");
        } else if (ordinal == 2) {
            stringBuffer.append("pertarget(");
        } else if (ordinal == 3) {
            stringBuffer.append("percflow(");
        } else if (ordinal == 4) {
            stringBuffer.append("percflowbelow(");
        }
        stringBuffer.append(this.pointcutExpression.asString());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
