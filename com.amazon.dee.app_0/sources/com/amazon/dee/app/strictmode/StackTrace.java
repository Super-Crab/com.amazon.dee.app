package com.amazon.dee.app.strictmode;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes12.dex */
public class StackTrace {
    private static final String TAG = Log.tag(StackTrace.class);
    private Integer hashValue;
    private Boolean isValidViolation;
    private List<StackTraceElement> selectedViolationStackTraceElements;
    private List<StackTraceElement> allViolationStackTraceElements = new ArrayList();
    private Integer nonAmazonViolationHashValue = -1;

    public StackTrace(StackTraceElement[] stackTraceElementArr) {
        this.selectedViolationStackTraceElements = new ArrayList();
        this.isValidViolation = false;
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            this.allViolationStackTraceElements.add(stackTraceElement);
        }
        this.selectedViolationStackTraceElements = getSelectedStackTraceLines();
        if (this.selectedViolationStackTraceElements.size() > 0) {
            this.isValidViolation = true;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && obj.getClass() == StackTrace.class && ((StackTrace) obj).allViolationStackTraceElements.equals(this.allViolationStackTraceElements);
    }

    @VisibleForTesting
    List<StackTraceElement> getSelectedStackTraceLines() {
        StackTraceElement stackTraceElement;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.allViolationStackTraceElements.size(); i++) {
            String className = this.allViolationStackTraceElements.get(i).getClassName();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            if (className.startsWith("com.amazon")) {
                if (this.allViolationStackTraceElements.get(i).getMethodName().toString().matches(".*\\d.*")) {
                    sb.append(this.allViolationStackTraceElements.get(i).getMethodName().toString().split("\\$")[0]);
                } else {
                    sb.append(this.allViolationStackTraceElements.get(i).getMethodName());
                }
                if (this.allViolationStackTraceElements.get(i).getFileName() == null) {
                    sb2.append("null");
                } else {
                    sb2.append(this.allViolationStackTraceElements.get(i).getFileName());
                }
                arrayList.add(new StackTraceElement(this.allViolationStackTraceElements.get(i).getClassName(), sb.toString(), sb2.toString(), 0));
                String str = "getSelectedStackTraceLines: " + stackTraceElement.toString();
            }
        }
        return arrayList;
    }

    public int hashCode() {
        if (this.isValidViolation.booleanValue()) {
            this.hashValue = Integer.valueOf(Objects.hashCode(this.selectedViolationStackTraceElements.toString()));
            return this.hashValue.intValue();
        }
        return this.nonAmazonViolationHashValue.intValue();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int i2 = 0; i2 < this.allViolationStackTraceElements.size(); i2++) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113("new StackTraceElement(", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline113.append(this.allViolationStackTraceElements.get(i2).getClassName());
            outline113.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline113.append(", ");
            outline113.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline113.append(this.allViolationStackTraceElements.get(i2).getMethodName());
            outline113.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline113.append(", ");
            outline113.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline113.append(this.allViolationStackTraceElements.get(i2).getFileName());
            outline113.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline113.append(", ");
            outline113.append(Integer.toString(this.allViolationStackTraceElements.get(i2).getLineNumber()));
            if (i2 == this.allViolationStackTraceElements.size() - 1) {
                outline113.append(")");
            } else {
                outline113.append("),\n");
            }
            sb.append((CharSequence) outline113);
        }
        if (sb.toString().length() > 3000) {
            while (i < sb.toString().length()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EncodedStackTraceStart: ");
                int i3 = i + 3000;
                outline107.append(sb.toString().substring(i, Math.min(i3, sb.toString().length())));
                outline107.toString();
                i = i3;
            }
        } else {
            String str = "EncodedStackTraceStart: " + ((Object) sb);
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Hash code of the identified violation [HASHVALUE]: ");
        outline1072.append(this.hashValue);
        outline1072.toString();
        return sb.toString();
    }
}
