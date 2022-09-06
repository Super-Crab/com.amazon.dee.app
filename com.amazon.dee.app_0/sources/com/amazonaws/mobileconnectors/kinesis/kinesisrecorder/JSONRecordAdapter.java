package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import android.util.Base64;
import com.amazonaws.AmazonClientException;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
@Deprecated
/* loaded from: classes13.dex */
public class JSONRecordAdapter {
    static final String DATA_FIELD_KEY = "Data";
    static final String EXPLICIT_HASH_FIELD = "ExplicitHash";
    private static final Log LOGGER = LogFactory.getLog(JSONRecordAdapter.class);
    static final String PARTITION_KEY_FIELD = "PartitionKey";
    static final String SEQUENCE_NUMBER_FIELD = "SequenceNumber";
    static final String STREAM_NAME_FIELD = "StreamName";

    JSONRecordAdapter() {
    }

    public static ByteBuffer getData(JSONObject jSONObject) throws JSONException {
        return ByteBuffer.wrap(Base64.decode(jSONObject.getString("Data"), 0));
    }

    public static String getPartitionKey(JSONObject jSONObject) throws JSONException {
        return jSONObject.getString(PARTITION_KEY_FIELD);
    }

    public static String getStreamName(JSONObject jSONObject) throws JSONException {
        return jSONObject.getString(STREAM_NAME_FIELD);
    }

    public JSONObject translateFromRecord(PutRecordRequest putRecordRequest) {
        if (putRecordRequest == null) {
            LOGGER.warn("The Record provided was null");
            return null;
        } else if (putRecordRequest.getData() != null && putRecordRequest.getPartitionKey() != null && !putRecordRequest.getPartitionKey().isEmpty() && putRecordRequest.getStreamName() != null && !putRecordRequest.getStreamName().isEmpty()) {
            if (putRecordRequest.getData().hasArray()) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("Data", Base64.encodeToString(putRecordRequest.getData().array(), 0));
                    jSONObject.put(STREAM_NAME_FIELD, putRecordRequest.getStreamName());
                    jSONObject.put(PARTITION_KEY_FIELD, putRecordRequest.getPartitionKey());
                    jSONObject.putOpt(EXPLICIT_HASH_FIELD, putRecordRequest.getExplicitHashKey());
                    jSONObject.putOpt(SEQUENCE_NUMBER_FIELD, putRecordRequest.getSequenceNumberForOrdering());
                    return jSONObject;
                } catch (JSONException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to convert KinesisRecord to JSON ");
                    outline107.append(e.getMessage());
                    throw new AmazonClientException(outline107.toString());
                }
            }
            throw new AmazonClientException("ByteBuffer must be based on array for proper storage");
        } else {
            throw new AmazonClientException("RecordRequests must specify a partition key, stream name, and data");
        }
    }
}
