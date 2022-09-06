package org.aspectj.internal.lang.reflect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.StringTokenizer;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.DeclarePrecedence;
import org.aspectj.lang.reflect.TypePattern;
/* loaded from: classes4.dex */
public class DeclarePrecedenceImpl implements DeclarePrecedence {
    private AjType<?> declaringType;
    private TypePattern[] precedenceList;
    private String precedenceString;

    public DeclarePrecedenceImpl(String str, AjType ajType) {
        this.declaringType = ajType;
        this.precedenceString = str;
        StringTokenizer stringTokenizer = new StringTokenizer(str.startsWith("(") ? GeneratedOutlineSupport1.outline51(str, 1, 1) : str, ",");
        this.precedenceList = new TypePattern[stringTokenizer.countTokens()];
        int i = 0;
        while (true) {
            TypePattern[] typePatternArr = this.precedenceList;
            if (i < typePatternArr.length) {
                typePatternArr[i] = new TypePatternImpl(stringTokenizer.nextToken().trim());
                i++;
            } else {
                return;
            }
        }
    }

    @Override // org.aspectj.lang.reflect.DeclarePrecedence
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // org.aspectj.lang.reflect.DeclarePrecedence
    public TypePattern[] getPrecedenceOrder() {
        return this.precedenceList;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("declare precedence : ");
        outline107.append(this.precedenceString);
        return outline107.toString();
    }
}
