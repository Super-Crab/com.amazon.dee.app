package org.junit.experimental.theories;

import java.util.List;
/* loaded from: classes5.dex */
public abstract class ParameterSupplier {
    public abstract List<PotentialAssignment> getValueSources(ParameterSignature parameterSignature) throws Throwable;
}
