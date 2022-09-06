package com.amazon.comms.calling.service;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableList;
import java.util.List;
/* loaded from: classes11.dex */
public class AcousticParams {
    private final Param[] paramsList = new Param[Constraint.values().length];

    /* loaded from: classes11.dex */
    public enum Constraint {
        ECHO_CANCELLATION,
        AUTO_GAIN_CONTROL,
        HIGH_PASS_FILTER,
        NOISE_SUPPRESSION,
        AUDIO_NB_FILTER
    }

    /* loaded from: classes11.dex */
    public static class Param {
        private final boolean enabled;
        private final Constraint key;

        public Param(Constraint constraint, boolean z) {
            this.key = constraint;
            this.enabled = z;
        }

        public Constraint getKey() {
            return this.key;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AcousticParams.Param(key=");
            outline107.append(getKey());
            outline107.append(", enabled=");
            outline107.append(isEnabled());
            outline107.append(")");
            return outline107.toString();
        }
    }

    public AcousticParams() {
        initializeAllConstraints(this, true);
    }

    public static AcousticParams disabled() {
        AcousticParams acousticParams = new AcousticParams();
        initializeAllConstraints(acousticParams, false);
        return acousticParams;
    }

    public static AcousticParams enabled() {
        AcousticParams acousticParams = new AcousticParams();
        initializeAllConstraints(acousticParams, true);
        return acousticParams;
    }

    private static void initializeAllConstraints(AcousticParams acousticParams, boolean z) {
        for (Constraint constraint : Constraint.values()) {
            acousticParams.setConstraint(constraint, z);
        }
    }

    public List<Param> getAllParams() {
        return ImmutableList.copyOf(this.paramsList);
    }

    public Param getConstraint(Constraint constraint) {
        return this.paramsList[constraint.ordinal()];
    }

    public void setConstraint(Constraint constraint, boolean z) {
        this.paramsList[constraint.ordinal()] = new Param(constraint, z);
    }
}
