package com.sun.mail.imap.protocol;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Protocol;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.util.ASCIIUtility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
public class FetchResponse extends IMAPResponse {
    private static final char[] HEADER = {'.', 'H', 'E', 'A', 'D', 'E', Matrix.MATRIX_TYPE_RANDOM_REGULAR};
    private static final char[] TEXT = {'.', 'T', 'E', 'X', 'T'};
    private Map extensionItems;
    private final FetchItem[] fitems;
    private Item[] items;

    public FetchResponse(Protocol protocol) throws IOException, ProtocolException {
        super(protocol);
        this.fitems = null;
        parse();
    }

    public static <T extends Item> List<T> getItems(Response[] responseArr, int i, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        if (responseArr == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < responseArr.length; i2++) {
            if (responseArr[i2] != null && (responseArr[i2] instanceof FetchResponse) && ((FetchResponse) responseArr[i2]).getNumber() == i) {
                FetchResponse fetchResponse = (FetchResponse) responseArr[i2];
                int i3 = 0;
                while (true) {
                    Item[] itemArr = fetchResponse.items;
                    if (i3 < itemArr.length) {
                        if (cls.isInstance(itemArr[i3])) {
                            arrayList.add(cls.cast(fetchResponse.items[i3]));
                        }
                        i3++;
                    }
                }
            }
        }
        return arrayList;
    }

    private boolean match(char[] cArr) {
        int length = cArr.length;
        int i = this.index;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i + 1;
            int i4 = i2 + 1;
            if (Character.toUpperCase((char) this.buffer[i]) != cArr[i2]) {
                return false;
            }
            i2 = i4;
            i = i3;
        }
        this.index += length;
        return true;
    }

    private String next20() {
        int i = this.index;
        int i2 = i + 20;
        int i3 = this.size;
        if (i2 > i3) {
            return ASCIIUtility.toString(this.buffer, i, i3 + i);
        }
        StringBuilder sb = new StringBuilder();
        byte[] bArr = this.buffer;
        int i4 = this.index;
        sb.append(ASCIIUtility.toString(bArr, i4, i4 + 20));
        sb.append("...");
        return sb.toString();
    }

    private void parse() throws ParsingException {
        byte[] bArr;
        int i;
        skipSpaces();
        if (this.buffer[this.index] == 40) {
            ArrayList arrayList = new ArrayList();
            do {
                this.index++;
                if (this.index < this.size) {
                    Item parseItem = parseItem();
                    if (parseItem != null) {
                        arrayList.add(parseItem);
                    } else if (!parseExtensionItem()) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("error in FETCH parsing, unrecognized item at index ");
                        outline107.append(this.index);
                        outline107.append(", starts with \"");
                        throw new ParsingException(GeneratedOutlineSupport1.outline91(outline107, next20(), EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED));
                    }
                    bArr = this.buffer;
                    i = this.index;
                } else {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("error in FETCH parsing, ran off end of buffer, size ");
                    outline1072.append(this.size);
                    throw new ParsingException(outline1072.toString());
                }
            } while (bArr[i] != 41);
            this.index = i + 1;
            this.items = (Item[]) arrayList.toArray(new Item[arrayList.size()]);
            return;
        }
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("error in FETCH parsing, missing '(' at index ");
        outline1073.append(this.index);
        throw new ParsingException(outline1073.toString());
    }

    private boolean parseExtensionItem() throws ParsingException {
        if (this.fitems == null) {
            return false;
        }
        int i = 0;
        while (true) {
            FetchItem[] fetchItemArr = this.fitems;
            if (i >= fetchItemArr.length) {
                return false;
            }
            if (match(fetchItemArr[i].getName())) {
                getExtensionItems().put(this.fitems[i].getName(), this.fitems[i].parseItem(this));
                return true;
            }
            i++;
        }
    }

    private Item parseItem() throws ParsingException {
        byte b = this.buffer[this.index];
        if (b != 66) {
            if (b != 73) {
                if (b != 77) {
                    if (b != 82) {
                        if (b != 85) {
                            if (b != 98) {
                                if (b != 105) {
                                    if (b != 109) {
                                        if (b != 114) {
                                            if (b != 117) {
                                                if (b != 69) {
                                                    if (b != 70) {
                                                        if (b != 101) {
                                                            if (b != 102) {
                                                                return null;
                                                            }
                                                        }
                                                    }
                                                    if (!match(FLAGS.name)) {
                                                        return null;
                                                    }
                                                    return new FLAGS(this);
                                                }
                                                if (!match(ENVELOPE.name)) {
                                                    return null;
                                                }
                                                return new ENVELOPE(this);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!match(UID.name)) {
                            return null;
                        }
                        return new UID(this);
                    }
                    if (match(RFC822SIZE.name)) {
                        return new RFC822SIZE(this);
                    }
                    if (!match(RFC822DATA.name)) {
                        return null;
                    }
                    boolean z = false;
                    if (match(HEADER)) {
                        z = true;
                    } else {
                        match(TEXT);
                    }
                    return new RFC822DATA(this, z);
                }
                if (!match(MODSEQ.name)) {
                    return null;
                }
                return new MODSEQ(this);
            }
            if (!match(INTERNALDATE.name)) {
                return null;
            }
            return new INTERNALDATE(this);
        }
        if (match(BODYSTRUCTURE.name)) {
            return new BODYSTRUCTURE(this);
        }
        if (!match(BODY.name)) {
            return null;
        }
        if (this.buffer[this.index] == 91) {
            return new BODY(this);
        }
        return new BODYSTRUCTURE(this);
    }

    public Map getExtensionItems() {
        if (this.extensionItems == null) {
            this.extensionItems = new HashMap();
        }
        return this.extensionItems;
    }

    public Item getItem(int i) {
        return this.items[i];
    }

    public int getItemCount() {
        return this.items.length;
    }

    public <T extends Item> T getItem(Class<T> cls) {
        int i = 0;
        while (true) {
            Item[] itemArr = this.items;
            if (i < itemArr.length) {
                if (cls.isInstance(itemArr[i])) {
                    return cls.cast(this.items[i]);
                }
                i++;
            } else {
                return null;
            }
        }
    }

    public FetchResponse(IMAPResponse iMAPResponse) throws IOException, ProtocolException {
        this(iMAPResponse, null);
    }

    public FetchResponse(IMAPResponse iMAPResponse, FetchItem[] fetchItemArr) throws IOException, ProtocolException {
        super(iMAPResponse);
        this.fitems = fetchItemArr;
        parse();
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x003e, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T extends com.sun.mail.imap.protocol.Item> T getItem(com.sun.mail.iap.Response[] r7, int r8, java.lang.Class<T> r9) {
        /*
            r0 = 0
            if (r7 != 0) goto L4
            return r0
        L4:
            r1 = 0
            r2 = r1
        L6:
            int r3 = r7.length
            if (r2 >= r3) goto L41
            r3 = r7[r2]
            if (r3 == 0) goto L3e
            r3 = r7[r2]
            boolean r3 = r3 instanceof com.sun.mail.imap.protocol.FetchResponse
            if (r3 == 0) goto L3e
            r3 = r7[r2]
            com.sun.mail.imap.protocol.FetchResponse r3 = (com.sun.mail.imap.protocol.FetchResponse) r3
            int r3 = r3.getNumber()
            if (r3 == r8) goto L1e
            goto L3e
        L1e:
            r3 = r7[r2]
            com.sun.mail.imap.protocol.FetchResponse r3 = (com.sun.mail.imap.protocol.FetchResponse) r3
            r4 = r1
        L23:
            com.sun.mail.imap.protocol.Item[] r5 = r3.items
            int r6 = r5.length
            if (r4 >= r6) goto L3e
            r5 = r5[r4]
            boolean r5 = r9.isInstance(r5)
            if (r5 == 0) goto L3b
            com.sun.mail.imap.protocol.Item[] r7 = r3.items
            r7 = r7[r4]
            java.lang.Object r7 = r9.cast(r7)
            com.sun.mail.imap.protocol.Item r7 = (com.sun.mail.imap.protocol.Item) r7
            return r7
        L3b:
            int r4 = r4 + 1
            goto L23
        L3e:
            int r2 = r2 + 1
            goto L6
        L41:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.protocol.FetchResponse.getItem(com.sun.mail.iap.Response[], int, java.lang.Class):com.sun.mail.imap.protocol.Item");
    }

    private boolean match(String str) {
        int length = str.length();
        int i = this.index;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i + 1;
            int i4 = i2 + 1;
            if (Character.toUpperCase((char) this.buffer[i]) != str.charAt(i2)) {
                return false;
            }
            i2 = i4;
            i = i3;
        }
        this.index += length;
        return true;
    }
}
