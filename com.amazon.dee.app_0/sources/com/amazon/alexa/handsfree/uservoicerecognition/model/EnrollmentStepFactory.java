package com.amazon.alexa.handsfree.uservoicerecognition.model;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceInfo;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class EnrollmentStepFactory {
    public List<EnrollmentStep> createEnrollmentSteps(@NonNull List<UtteranceInfo> list, int i) {
        ArrayList arrayList = new ArrayList();
        for (UtteranceInfo utteranceInfo : list) {
            arrayList.add(new EnrollmentStep(utteranceInfo, i));
        }
        return arrayList;
    }
}
