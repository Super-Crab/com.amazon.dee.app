package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.SequenceNumberRange;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class SequenceNumberRangeJsonMarshaller {
    private static SequenceNumberRangeJsonMarshaller instance;

    SequenceNumberRangeJsonMarshaller() {
    }

    public static SequenceNumberRangeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SequenceNumberRangeJsonMarshaller();
        }
        return instance;
    }

    public void marshall(SequenceNumberRange sequenceNumberRange, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (sequenceNumberRange.getStartingSequenceNumber() != null) {
            String startingSequenceNumber = sequenceNumberRange.getStartingSequenceNumber();
            awsJsonWriter.name("StartingSequenceNumber");
            awsJsonWriter.value(startingSequenceNumber);
        }
        if (sequenceNumberRange.getEndingSequenceNumber() != null) {
            String endingSequenceNumber = sequenceNumberRange.getEndingSequenceNumber();
            awsJsonWriter.name("EndingSequenceNumber");
            awsJsonWriter.value(endingSequenceNumber);
        }
        awsJsonWriter.endObject();
    }
}
