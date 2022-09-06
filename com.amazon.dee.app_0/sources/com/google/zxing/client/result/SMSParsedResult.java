package com.google.zxing.client.result;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes3.dex */
public final class SMSParsedResult extends ParsedResult {
    private final String body;
    private final String[] numbers;
    private final String subject;
    private final String[] vias;

    public SMSParsedResult(String str, String str2, String str3, String str4) {
        super(ParsedResultType.SMS);
        this.numbers = new String[]{str};
        this.vias = new String[]{str2};
        this.subject = str3;
        this.body = str4;
    }

    public String getBody() {
        return this.body;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(100);
        ParsedResult.maybeAppend(this.numbers, sb);
        ParsedResult.maybeAppend(this.subject, sb);
        ParsedResult.maybeAppend(this.body, sb);
        return sb.toString();
    }

    public String[] getNumbers() {
        return this.numbers;
    }

    public String getSMSURI() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sms:");
        boolean z = false;
        boolean z2 = true;
        for (int i = 0; i < this.numbers.length; i++) {
            if (z2) {
                z2 = false;
            } else {
                outline107.append(JsonReaderKt.COMMA);
            }
            outline107.append(this.numbers[i]);
            String[] strArr = this.vias;
            if (strArr != null && strArr[i] != null) {
                outline107.append(";via=");
                outline107.append(this.vias[i]);
            }
        }
        boolean z3 = this.body != null;
        if (this.subject != null) {
            z = true;
        }
        if (z3 || z) {
            outline107.append(Constants.DEFAULT_IMAGE_CHAR);
            if (z3) {
                outline107.append("body=");
                outline107.append(this.body);
            }
            if (z) {
                if (z3) {
                    outline107.append(Typography.amp);
                }
                outline107.append("subject=");
                outline107.append(this.subject);
            }
        }
        return outline107.toString();
    }

    public String getSubject() {
        return this.subject;
    }

    public String[] getVias() {
        return this.vias;
    }

    public SMSParsedResult(String[] strArr, String[] strArr2, String str, String str2) {
        super(ParsedResultType.SMS);
        this.numbers = strArr;
        this.vias = strArr2;
        this.subject = str;
        this.body = str2;
    }
}
