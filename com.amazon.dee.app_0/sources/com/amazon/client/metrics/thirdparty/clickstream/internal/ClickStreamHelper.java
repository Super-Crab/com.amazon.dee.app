package com.amazon.client.metrics.thirdparty.clickstream.internal;

import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.DataPointType;
import com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes11.dex */
public class ClickStreamHelper {
    static final String COLON_DELIMITER = ":";
    static final String COMMA_DELIMITER = ",";

    public static void addDatapoint(List<DataPoint> list, String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        list.add(new DataPoint(str, str2, 1, DataPointType.CK));
    }

    public static void addDatapointsToList(List<DataPoint> list, ClickStreamInfo clickStreamInfo) {
        if (clickStreamInfo == null) {
            return;
        }
        for (DataPoint dataPoint : clickStreamInfo.getDataPoints()) {
            list.add(dataPoint);
        }
    }

    public static String generateRequestId() {
        String replace = UUID.randomUUID().toString().replace(ProcessIdUtil.DEFAULT_PROCESSID, "");
        Random random = new Random();
        random.setSeed(new Date().getTime());
        int nextInt = random.nextInt(replace.length() - 20);
        return replace.subSequence(nextInt, nextInt + 20).toString().toUpperCase(Locale.US);
    }

    public static String listToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0).toString());
        for (int i = 1; i < list.size(); i++) {
            sb.append(",");
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    public static String mapToString(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!entry.getKey().contains(":") && !entry.getKey().contains(",") && !entry.getValue().contains(":") && !entry.getValue().contains(",")) {
                sb.append(entry.getKey());
                sb.append(":");
                sb.append(entry.getValue());
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
