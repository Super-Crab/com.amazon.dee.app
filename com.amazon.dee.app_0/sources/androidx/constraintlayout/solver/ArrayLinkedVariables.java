package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintStream;
import java.util.Arrays;
/* loaded from: classes.dex */
public class ArrayLinkedVariables {
    private static final boolean DEBUG = false;
    private static final boolean FULL_NEW_CHECK = false;
    private static final int NONE = -1;
    private int[] mArrayIndices;
    private int[] mArrayNextIndices;
    private float[] mArrayValues;
    private final Cache mCache;
    private boolean mDidFillOnce;
    private int mHead;
    private int mLast;
    private final ArrayRow mRow;
    int currentSize = 0;
    private int ROW_SIZE = 8;
    private SolverVariable candidate = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        int i = this.ROW_SIZE;
        this.mArrayIndices = new int[i];
        this.mArrayNextIndices = new int[i];
        this.mArrayValues = new float[i];
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.mRow = arrayRow;
        this.mCache = cache;
    }

    private boolean isNew(SolverVariable solverVariable, LinearSystem linearSystem) {
        return solverVariable.usageInRowCount <= 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void add(SolverVariable solverVariable, float f, boolean z) {
        if (f == 0.0f) {
            return;
        }
        int i = this.mHead;
        if (i == -1) {
            this.mHead = 0;
            float[] fArr = this.mArrayValues;
            int i2 = this.mHead;
            fArr[i2] = f;
            this.mArrayIndices[i2] = solverVariable.id;
            this.mArrayNextIndices[i2] = -1;
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.currentSize++;
            if (this.mDidFillOnce) {
                return;
            }
            this.mLast++;
            int i3 = this.mLast;
            int[] iArr = this.mArrayIndices;
            if (i3 < iArr.length) {
                return;
            }
            this.mDidFillOnce = true;
            this.mLast = iArr.length - 1;
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i != -1 && i5 < this.currentSize; i5++) {
            int[] iArr2 = this.mArrayIndices;
            int i6 = iArr2[i];
            int i7 = solverVariable.id;
            if (i6 == i7) {
                float[] fArr2 = this.mArrayValues;
                fArr2[i] = fArr2[i] + f;
                if (fArr2[i] != 0.0f) {
                    return;
                }
                if (i == this.mHead) {
                    this.mHead = this.mArrayNextIndices[i];
                } else {
                    int[] iArr3 = this.mArrayNextIndices;
                    iArr3[i4] = iArr3[i];
                }
                if (z) {
                    solverVariable.removeFromRow(this.mRow);
                }
                if (this.mDidFillOnce) {
                    this.mLast = i;
                }
                solverVariable.usageInRowCount--;
                this.currentSize--;
                return;
            }
            if (iArr2[i] < i7) {
                i4 = i;
            }
            i = this.mArrayNextIndices[i];
        }
        int i8 = this.mLast;
        int i9 = i8 + 1;
        if (this.mDidFillOnce) {
            int[] iArr4 = this.mArrayIndices;
            if (iArr4[i8] != -1) {
                i8 = iArr4.length;
            }
        } else {
            i8 = i9;
        }
        int[] iArr5 = this.mArrayIndices;
        if (i8 >= iArr5.length && this.currentSize < iArr5.length) {
            int i10 = 0;
            while (true) {
                int[] iArr6 = this.mArrayIndices;
                if (i10 >= iArr6.length) {
                    break;
                } else if (iArr6[i10] == -1) {
                    i8 = i10;
                    break;
                } else {
                    i10++;
                }
            }
        }
        int[] iArr7 = this.mArrayIndices;
        if (i8 >= iArr7.length) {
            i8 = iArr7.length;
            this.ROW_SIZE *= 2;
            this.mDidFillOnce = false;
            this.mLast = i8 - 1;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
        }
        this.mArrayIndices[i8] = solverVariable.id;
        this.mArrayValues[i8] = f;
        if (i4 != -1) {
            int[] iArr8 = this.mArrayNextIndices;
            iArr8[i8] = iArr8[i4];
            iArr8[i4] = i8;
        } else {
            this.mArrayNextIndices[i8] = this.mHead;
            this.mHead = i8;
        }
        solverVariable.usageInRowCount++;
        solverVariable.addToRow(this.mRow);
        this.currentSize++;
        if (!this.mDidFillOnce) {
            this.mLast++;
        }
        int i11 = this.mLast;
        int[] iArr9 = this.mArrayIndices;
        if (i11 < iArr9.length) {
            return;
        }
        this.mDidFillOnce = true;
        this.mLast = iArr9.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.constraintlayout.solver.SolverVariable chooseSubject(androidx.constraintlayout.solver.LinearSystem r15) {
        /*
            r14 = this;
            int r0 = r14.mHead
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = r1
            r6 = r2
            r8 = r6
            r5 = r3
            r7 = r5
        La:
            r9 = -1
            if (r0 == r9) goto L96
            int r9 = r14.currentSize
            if (r2 >= r9) goto L96
            float[] r9 = r14.mArrayValues
            r10 = r9[r0]
            r11 = 981668463(0x3a83126f, float:0.001)
            androidx.constraintlayout.solver.Cache r12 = r14.mCache
            androidx.constraintlayout.solver.SolverVariable[] r12 = r12.mIndexedVariables
            int[] r13 = r14.mArrayIndices
            r13 = r13[r0]
            r12 = r12[r13]
            int r13 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r13 >= 0) goto L35
            r11 = -1165815185(0xffffffffba83126f, float:-0.001)
            int r11 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r11 <= 0) goto L41
            r9[r0] = r3
            androidx.constraintlayout.solver.ArrayRow r9 = r14.mRow
            r12.removeFromRow(r9)
            goto L40
        L35:
            int r11 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r11 >= 0) goto L41
            r9[r0] = r3
            androidx.constraintlayout.solver.ArrayRow r9 = r14.mRow
            r12.removeFromRow(r9)
        L40:
            r10 = r3
        L41:
            int r9 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            r11 = 1
            if (r9 == 0) goto L8e
            androidx.constraintlayout.solver.SolverVariable$Type r9 = r12.mType
            androidx.constraintlayout.solver.SolverVariable$Type r13 = androidx.constraintlayout.solver.SolverVariable.Type.UNRESTRICTED
            if (r9 != r13) goto L6a
            if (r4 != 0) goto L56
            boolean r4 = r14.isNew(r12, r15)
        L52:
            r6 = r4
            r5 = r10
        L54:
            r4 = r12
            goto L8e
        L56:
            int r9 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r9 <= 0) goto L5f
            boolean r4 = r14.isNew(r12, r15)
            goto L52
        L5f:
            if (r6 != 0) goto L8e
            boolean r9 = r14.isNew(r12, r15)
            if (r9 == 0) goto L8e
            r5 = r10
            r6 = r11
            goto L54
        L6a:
            if (r4 != 0) goto L8e
            int r9 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r9 >= 0) goto L8e
            if (r1 != 0) goto L7a
            boolean r1 = r14.isNew(r12, r15)
        L76:
            r8 = r1
            r7 = r10
        L78:
            r1 = r12
            goto L8e
        L7a:
            int r9 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r9 <= 0) goto L83
            boolean r1 = r14.isNew(r12, r15)
            goto L76
        L83:
            if (r8 != 0) goto L8e
            boolean r9 = r14.isNew(r12, r15)
            if (r9 == 0) goto L8e
            r7 = r10
            r8 = r11
            goto L78
        L8e:
            int[] r9 = r14.mArrayNextIndices
            r0 = r9[r0]
            int r2 = r2 + 1
            goto La
        L96:
            if (r4 == 0) goto L99
            return r4
        L99:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.ArrayLinkedVariables.chooseSubject(androidx.constraintlayout.solver.LinearSystem):androidx.constraintlayout.solver.SolverVariable");
    }

    public final void clear() {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
            if (solverVariable != null) {
                solverVariable.removeFromRow(this.mRow);
            }
            i = this.mArrayNextIndices[i];
        }
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.currentSize = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean containsKey(SolverVariable solverVariable) {
        int i = this.mHead;
        if (i == -1) {
            return false;
        }
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return true;
            }
            i = this.mArrayNextIndices[i];
        }
        return false;
    }

    public void display() {
        int i = this.currentSize;
        System.out.print("{ ");
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable variable = getVariable(i2);
            if (variable != null) {
                PrintStream printStream = System.out;
                printStream.print(variable + " = " + getVariableValue(i2) + " ");
            }
        }
        System.out.println(" }");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void divideByAmount(float f) {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] / f;
            i = this.mArrayNextIndices[i];
        }
    }

    public final float get(SolverVariable solverVariable) {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return this.mArrayValues[i];
            }
            i = this.mArrayNextIndices[i];
        }
        return 0.0f;
    }

    SolverVariable getPivotCandidate() {
        SolverVariable solverVariable = this.candidate;
        if (solverVariable == null) {
            int i = this.mHead;
            SolverVariable solverVariable2 = null;
            for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
                if (this.mArrayValues[i] < 0.0f) {
                    SolverVariable solverVariable3 = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                    if (solverVariable2 == null || solverVariable2.strength < solverVariable3.strength) {
                        solverVariable2 = solverVariable3;
                    }
                }
                i = this.mArrayNextIndices[i];
            }
            return solverVariable2;
        }
        return solverVariable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SolverVariable getVariable(int i) {
        int i2 = this.mHead;
        for (int i3 = 0; i2 != -1 && i3 < this.currentSize; i3++) {
            if (i3 == i) {
                return this.mCache.mIndexedVariables[this.mArrayIndices[i2]];
            }
            i2 = this.mArrayNextIndices[i2];
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final float getVariableValue(int i) {
        int i2 = this.mHead;
        for (int i3 = 0; i2 != -1 && i3 < this.currentSize; i3++) {
            if (i3 == i) {
                return this.mArrayValues[i2];
            }
            i2 = this.mArrayNextIndices[i2];
        }
        return 0.0f;
    }

    boolean hasAtLeastOnePositiveVariable() {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            if (this.mArrayValues[i] > 0.0f) {
                return true;
            }
            i = this.mArrayNextIndices[i];
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void invert() {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] * (-1.0f);
            i = this.mArrayNextIndices[i];
        }
    }

    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable, true);
            return;
        }
        int i = this.mHead;
        if (i == -1) {
            this.mHead = 0;
            float[] fArr = this.mArrayValues;
            int i2 = this.mHead;
            fArr[i2] = f;
            this.mArrayIndices[i2] = solverVariable.id;
            this.mArrayNextIndices[i2] = -1;
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.currentSize++;
            if (this.mDidFillOnce) {
                return;
            }
            this.mLast++;
            int i3 = this.mLast;
            int[] iArr = this.mArrayIndices;
            if (i3 < iArr.length) {
                return;
            }
            this.mDidFillOnce = true;
            this.mLast = iArr.length - 1;
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i != -1 && i5 < this.currentSize; i5++) {
            int[] iArr2 = this.mArrayIndices;
            int i6 = iArr2[i];
            int i7 = solverVariable.id;
            if (i6 == i7) {
                this.mArrayValues[i] = f;
                return;
            }
            if (iArr2[i] < i7) {
                i4 = i;
            }
            i = this.mArrayNextIndices[i];
        }
        int i8 = this.mLast;
        int i9 = i8 + 1;
        if (this.mDidFillOnce) {
            int[] iArr3 = this.mArrayIndices;
            if (iArr3[i8] != -1) {
                i8 = iArr3.length;
            }
        } else {
            i8 = i9;
        }
        int[] iArr4 = this.mArrayIndices;
        if (i8 >= iArr4.length && this.currentSize < iArr4.length) {
            int i10 = 0;
            while (true) {
                int[] iArr5 = this.mArrayIndices;
                if (i10 >= iArr5.length) {
                    break;
                } else if (iArr5[i10] == -1) {
                    i8 = i10;
                    break;
                } else {
                    i10++;
                }
            }
        }
        int[] iArr6 = this.mArrayIndices;
        if (i8 >= iArr6.length) {
            i8 = iArr6.length;
            this.ROW_SIZE *= 2;
            this.mDidFillOnce = false;
            this.mLast = i8 - 1;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
        }
        this.mArrayIndices[i8] = solverVariable.id;
        this.mArrayValues[i8] = f;
        if (i4 != -1) {
            int[] iArr7 = this.mArrayNextIndices;
            iArr7[i8] = iArr7[i4];
            iArr7[i4] = i8;
        } else {
            this.mArrayNextIndices[i8] = this.mHead;
            this.mHead = i8;
        }
        solverVariable.usageInRowCount++;
        solverVariable.addToRow(this.mRow);
        this.currentSize++;
        if (!this.mDidFillOnce) {
            this.mLast++;
        }
        if (this.currentSize >= this.mArrayIndices.length) {
            this.mDidFillOnce = true;
        }
        int i11 = this.mLast;
        int[] iArr8 = this.mArrayIndices;
        if (i11 < iArr8.length) {
            return;
        }
        this.mDidFillOnce = true;
        this.mLast = iArr8.length - 1;
    }

    public final float remove(SolverVariable solverVariable, boolean z) {
        if (this.candidate == solverVariable) {
            this.candidate = null;
        }
        int i = this.mHead;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                if (i == this.mHead) {
                    this.mHead = this.mArrayNextIndices[i];
                } else {
                    int[] iArr = this.mArrayNextIndices;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    solverVariable.removeFromRow(this.mRow);
                }
                solverVariable.usageInRowCount--;
                this.currentSize--;
                this.mArrayIndices[i] = -1;
                if (this.mDidFillOnce) {
                    this.mLast = i;
                }
                return this.mArrayValues[i];
            }
            i2++;
            i3 = i;
            i = this.mArrayNextIndices[i];
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int sizeInBytes() {
        return (this.mArrayIndices.length * 4 * 3) + 0 + 36;
    }

    public String toString() {
        int i = this.mHead;
        String str = "";
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(GeneratedOutlineSupport1.outline72(str, " -> "));
            outline107.append(this.mArrayValues[i]);
            outline107.append(" : ");
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(outline107.toString());
            outline1072.append(this.mCache.mIndexedVariables[this.mArrayIndices[i]]);
            str = outline1072.toString();
            i = this.mArrayNextIndices[i];
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateFromRow(ArrayRow arrayRow, ArrayRow arrayRow2, boolean z) {
        int i = this.mHead;
        while (true) {
            for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
                int i3 = this.mArrayIndices[i];
                SolverVariable solverVariable = arrayRow2.variable;
                if (i3 == solverVariable.id) {
                    float f = this.mArrayValues[i];
                    remove(solverVariable, z);
                    ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                    int i4 = arrayLinkedVariables.mHead;
                    for (int i5 = 0; i4 != -1 && i5 < arrayLinkedVariables.currentSize; i5++) {
                        add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[i4]], arrayLinkedVariables.mArrayValues[i4] * f, z);
                        i4 = arrayLinkedVariables.mArrayNextIndices[i4];
                    }
                    arrayRow.constantValue = (arrayRow2.constantValue * f) + arrayRow.constantValue;
                    if (z) {
                        arrayRow2.variable.removeFromRow(arrayRow);
                    }
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                }
            }
            return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateFromSystem(ArrayRow arrayRow, ArrayRow[] arrayRowArr) {
        int i = this.mHead;
        while (true) {
            for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
                SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if (solverVariable.definitionId != -1) {
                    float f = this.mArrayValues[i];
                    remove(solverVariable, true);
                    ArrayRow arrayRow2 = arrayRowArr[solverVariable.definitionId];
                    if (!arrayRow2.isSimpleDefinition) {
                        ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                        int i3 = arrayLinkedVariables.mHead;
                        for (int i4 = 0; i3 != -1 && i4 < arrayLinkedVariables.currentSize; i4++) {
                            add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f, true);
                            i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                        }
                    }
                    arrayRow.constantValue = (arrayRow2.constantValue * f) + arrayRow.constantValue;
                    arrayRow2.variable.removeFromRow(arrayRow);
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                }
            }
            return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SolverVariable getPivotCandidate(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int i = this.mHead;
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            if (this.mArrayValues[i] < 0.0f) {
                SolverVariable solverVariable3 = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if ((zArr == null || !zArr[solverVariable3.id]) && solverVariable3 != solverVariable && ((type = solverVariable3.mType) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR)) {
                    float f2 = this.mArrayValues[i];
                    if (f2 < f) {
                        solverVariable2 = solverVariable3;
                        f = f2;
                    }
                }
            }
            i = this.mArrayNextIndices[i];
        }
        return solverVariable2;
    }
}
