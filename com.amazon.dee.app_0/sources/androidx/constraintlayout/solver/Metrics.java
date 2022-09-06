package androidx.constraintlayout.solver;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class Metrics {
    public long additionalMeasures;
    public long barrierConnectionResolved;
    public long bfs;
    public long centerConnectionResolved;
    public long chainConnectionResolved;
    public long constraints;
    public long errors;
    public long extravariables;
    public long fullySolved;
    public long graphOptimizer;
    public long iterations;
    public long lastTableSize;
    public long matchConnectionResolved;
    public long maxRows;
    public long maxTableSize;
    public long maxVariables;
    public long measures;
    public long minimize;
    public long minimizeGoal;
    public long nonresolvedWidgets;
    public long oldresolvedWidgets;
    public long optimize;
    public long pivots;
    public ArrayList<String> problematicLayouts = new ArrayList<>();
    public long resolutions;
    public long resolvedWidgets;
    public long simpleconstraints;
    public long slackvariables;
    public long tableSizeIncrease;
    public long variables;

    public void reset() {
        this.measures = 0L;
        this.additionalMeasures = 0L;
        this.resolutions = 0L;
        this.tableSizeIncrease = 0L;
        this.maxTableSize = 0L;
        this.lastTableSize = 0L;
        this.maxVariables = 0L;
        this.maxRows = 0L;
        this.minimize = 0L;
        this.minimizeGoal = 0L;
        this.constraints = 0L;
        this.simpleconstraints = 0L;
        this.optimize = 0L;
        this.iterations = 0L;
        this.pivots = 0L;
        this.bfs = 0L;
        this.variables = 0L;
        this.errors = 0L;
        this.slackvariables = 0L;
        this.extravariables = 0L;
        this.fullySolved = 0L;
        this.graphOptimizer = 0L;
        this.resolvedWidgets = 0L;
        this.oldresolvedWidgets = 0L;
        this.nonresolvedWidgets = 0L;
        this.centerConnectionResolved = 0L;
        this.matchConnectionResolved = 0L;
        this.chainConnectionResolved = 0L;
        this.barrierConnectionResolved = 0L;
        this.problematicLayouts.clear();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\n*** Metrics ***\nmeasures: ");
        outline107.append(this.measures);
        outline107.append("\nadditionalMeasures: ");
        outline107.append(this.additionalMeasures);
        outline107.append("\nresolutions passes: ");
        outline107.append(this.resolutions);
        outline107.append("\ntable increases: ");
        outline107.append(this.tableSizeIncrease);
        outline107.append("\nmaxTableSize: ");
        outline107.append(this.maxTableSize);
        outline107.append("\nmaxVariables: ");
        outline107.append(this.maxVariables);
        outline107.append("\nmaxRows: ");
        outline107.append(this.maxRows);
        outline107.append("\n\nminimize: ");
        outline107.append(this.minimize);
        outline107.append("\nminimizeGoal: ");
        outline107.append(this.minimizeGoal);
        outline107.append("\nconstraints: ");
        outline107.append(this.constraints);
        outline107.append("\nsimpleconstraints: ");
        outline107.append(this.simpleconstraints);
        outline107.append("\noptimize: ");
        outline107.append(this.optimize);
        outline107.append("\niterations: ");
        outline107.append(this.iterations);
        outline107.append("\npivots: ");
        outline107.append(this.pivots);
        outline107.append("\nbfs: ");
        outline107.append(this.bfs);
        outline107.append("\nvariables: ");
        outline107.append(this.variables);
        outline107.append("\nerrors: ");
        outline107.append(this.errors);
        outline107.append("\nslackvariables: ");
        outline107.append(this.slackvariables);
        outline107.append("\nextravariables: ");
        outline107.append(this.extravariables);
        outline107.append("\nfullySolved: ");
        outline107.append(this.fullySolved);
        outline107.append("\ngraphOptimizer: ");
        outline107.append(this.graphOptimizer);
        outline107.append("\nresolvedWidgets: ");
        outline107.append(this.resolvedWidgets);
        outline107.append("\noldresolvedWidgets: ");
        outline107.append(this.oldresolvedWidgets);
        outline107.append("\nnonresolvedWidgets: ");
        outline107.append(this.nonresolvedWidgets);
        outline107.append("\ncenterConnectionResolved: ");
        outline107.append(this.centerConnectionResolved);
        outline107.append("\nmatchConnectionResolved: ");
        outline107.append(this.matchConnectionResolved);
        outline107.append("\nchainConnectionResolved: ");
        outline107.append(this.chainConnectionResolved);
        outline107.append("\nbarrierConnectionResolved: ");
        outline107.append(this.barrierConnectionResolved);
        outline107.append("\nproblematicsLayouts: ");
        outline107.append(this.problematicLayouts);
        outline107.append("\n");
        return outline107.toString();
    }
}
