package org.junit.experimental.theories.internal;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import java.util.Arrays;
import java.util.List;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;
/* loaded from: classes5.dex */
public class BooleanSupplier extends ParameterSupplier {
    @Override // org.junit.experimental.theories.ParameterSupplier
    public List<PotentialAssignment> getValueSources(ParameterSignature parameterSignature) {
        return Arrays.asList(PotentialAssignment.forValue("true", true), PotentialAssignment.forValue(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE, false));
    }
}
