package com.amazon.deecomms.accessories;
/* loaded from: classes12.dex */
public enum ATCommand {
    DIAL("ATD"),
    ANSWER_CALL("ATA"),
    END_CALL("AT+CHUP");
    
    private final String mCommand;

    ATCommand(String str) {
        this.mCommand = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mCommand;
    }
}
