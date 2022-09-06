package androidx.constraintlayout.solver;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
/* loaded from: classes.dex */
public class SolverVariable {
    private static final boolean INTERNAL_DEBUG = false;
    static final int MAX_STRENGTH = 7;
    public static final int STRENGTH_BARRIER = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 6;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static int uniqueConstantId = 1;
    private static int uniqueErrorId = 1;
    private static int uniqueId = 1;
    private static int uniqueSlackId = 1;
    private static int uniqueUnrestrictedId = 1;
    public float computedValue;
    int definitionId;
    public int id;
    ArrayRow[] mClientEquations;
    int mClientEquationsCount;
    private String mName;
    Type mType;
    public int strength;
    float[] strengthVector;
    public int usageInRowCount;

    /* renamed from: androidx.constraintlayout.solver.SolverVariable$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$SolverVariable$Type = new int[Type.values().length];

        static {
            try {
                $SwitchMap$androidx$constraintlayout$solver$SolverVariable$Type[Type.UNRESTRICTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$SolverVariable$Type[Type.CONSTANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$SolverVariable$Type[Type.SLACK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$SolverVariable$Type[Type.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$SolverVariable$Type[Type.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(String str, Type type) {
        this.id = -1;
        this.definitionId = -1;
        this.strength = 0;
        this.strengthVector = new float[7];
        this.mClientEquations = new ArrayRow[8];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.mName = str;
        this.mType = type;
    }

    private static String getUniqueName(Type type, String str) {
        if (str != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
            outline107.append(uniqueErrorId);
            return outline107.toString();
        }
        int ordinal = type.ordinal();
        if (ordinal == 0) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("U");
            int i = uniqueUnrestrictedId + 1;
            uniqueUnrestrictedId = i;
            outline1072.append(i);
            return outline1072.toString();
        } else if (ordinal == 1) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("C");
            int i2 = uniqueConstantId + 1;
            uniqueConstantId = i2;
            outline1073.append(i2);
            return outline1073.toString();
        } else if (ordinal == 2) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107(ExifInterface.LATITUDE_SOUTH);
            int i3 = uniqueSlackId + 1;
            uniqueSlackId = i3;
            outline1074.append(i3);
            return outline1074.toString();
        } else if (ordinal == 3) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("e");
            int i4 = uniqueErrorId + 1;
            uniqueErrorId = i4;
            outline1075.append(i4);
            return outline1075.toString();
        } else if (ordinal == 4) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
            int i5 = uniqueId + 1;
            uniqueId = i5;
            outline1076.append(i5);
            return outline1076.toString();
        } else {
            throw new AssertionError(type.name());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void increaseErrorId() {
        uniqueErrorId++;
    }

    public final void addToRow(ArrayRow arrayRow) {
        int i = 0;
        while (true) {
            int i2 = this.mClientEquationsCount;
            if (i < i2) {
                if (this.mClientEquations[i] == arrayRow) {
                    return;
                }
                i++;
            } else {
                ArrayRow[] arrayRowArr = this.mClientEquations;
                if (i2 >= arrayRowArr.length) {
                    this.mClientEquations = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.mClientEquations;
                int i3 = this.mClientEquationsCount;
                arrayRowArr2[i3] = arrayRow;
                this.mClientEquationsCount = i3 + 1;
                return;
            }
        }
    }

    void clearStrengths() {
        for (int i = 0; i < 7; i++) {
            this.strengthVector[i] = 0.0f;
        }
    }

    public String getName() {
        return this.mName;
    }

    public final void removeFromRow(ArrayRow arrayRow) {
        int i = this.mClientEquationsCount;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.mClientEquations[i2] == arrayRow) {
                for (int i3 = 0; i3 < (i - i2) - 1; i3++) {
                    ArrayRow[] arrayRowArr = this.mClientEquations;
                    int i4 = i2 + i3;
                    arrayRowArr[i4] = arrayRowArr[i4 + 1];
                }
                this.mClientEquationsCount--;
                return;
            }
        }
    }

    public void reset() {
        this.mName = null;
        this.mType = Type.UNKNOWN;
        this.strength = 0;
        this.id = -1;
        this.definitionId = -1;
        this.computedValue = 0.0f;
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setType(Type type, String str) {
        this.mType = type;
    }

    String strengthsToString() {
        String str = this + "[";
        boolean z = true;
        boolean z2 = false;
        for (int i = 0; i < this.strengthVector.length; i++) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
            outline107.append(this.strengthVector[i]);
            String sb = outline107.toString();
            float[] fArr = this.strengthVector;
            if (fArr[i] > 0.0f) {
                z2 = false;
            } else if (fArr[i] < 0.0f) {
                z2 = true;
            }
            if (this.strengthVector[i] != 0.0f) {
                z = false;
            }
            if (i < this.strengthVector.length - 1) {
                str = GeneratedOutlineSupport1.outline72(sb, ", ");
            } else {
                str = GeneratedOutlineSupport1.outline72(sb, "] ");
            }
        }
        if (z2) {
            str = GeneratedOutlineSupport1.outline72(str, " (-)");
        }
        return z ? GeneratedOutlineSupport1.outline72(str, " (*)") : str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
        outline107.append(this.mName);
        return outline107.toString();
    }

    public final void updateReferencesWithNewDefinition(ArrayRow arrayRow) {
        int i = this.mClientEquationsCount;
        for (int i2 = 0; i2 < i; i2++) {
            ArrayRow[] arrayRowArr = this.mClientEquations;
            arrayRowArr[i2].variables.updateFromRow(arrayRowArr[i2], arrayRow, false);
        }
        this.mClientEquationsCount = 0;
    }

    public SolverVariable(Type type, String str) {
        this.id = -1;
        this.definitionId = -1;
        this.strength = 0;
        this.strengthVector = new float[7];
        this.mClientEquations = new ArrayRow[8];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.mType = type;
    }
}
