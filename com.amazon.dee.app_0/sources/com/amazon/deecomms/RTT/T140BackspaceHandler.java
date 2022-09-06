package com.amazon.deecomms.RTT;
/* loaded from: classes12.dex */
public class T140BackspaceHandler {
    private static final String BACKSPACE = "\b";
    private static final byte[] BACKSPACE_DATA = {8};
    private static RTTOutputProtocol rttOutputProtocol;

    public T140BackspaceHandler(RTTOutputProtocol rTTOutputProtocol) {
        rttOutputProtocol = rTTOutputProtocol;
    }

    public byte[] encodeBackspace() {
        return BACKSPACE_DATA;
    }

    public void handleBackspaces(String str) {
        int i;
        if (!str.contains(BACKSPACE)) {
            rttOutputProtocol.appendText(str);
            return;
        }
        StringBuilder sb = new StringBuilder(str);
        for (int indexOf = str.indexOf(BACKSPACE); indexOf != -1; indexOf = sb.indexOf(BACKSPACE)) {
            if (indexOf == 0) {
                rttOutputProtocol.deleteLastChar();
                i = indexOf;
            } else {
                i = indexOf - 1;
            }
            sb.delete(i, indexOf + 1);
        }
        rttOutputProtocol.appendText(sb.toString());
    }
}
