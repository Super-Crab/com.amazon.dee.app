package com.sun.mail.imap.protocol;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Response;
import com.sun.mail.util.PropUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.mail.internet.ParameterList;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
public class BODYSTRUCTURE implements Item {
    public String attachment;
    public BODYSTRUCTURE[] bodies;
    public ParameterList cParams;
    public ParameterList dParams;
    public String description;
    public String disposition;
    public String encoding;
    public ENVELOPE envelope;
    public String id;
    public String[] language;
    public int lines;
    public String md5;
    public int msgno;
    private int processedType;
    public int size;
    public String subtype;
    public String type;
    static final char[] name = {'B', 'O', 'D', 'Y', 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_REGULAR, Matrix.MATRIX_TYPE_RANDOM_UT, 'C', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'E'};
    private static int SINGLE = 1;
    private static int MULTI = 2;
    private static int NESTED = 3;
    private static final boolean parseDebug = PropUtil.getBooleanSystemProperty("mail.imap.parse.debug", false);

    public BODYSTRUCTURE(FetchResponse fetchResponse) throws ParsingException {
        this.lines = -1;
        this.size = -1;
        if (parseDebug) {
            System.out.println("DEBUG IMAP: parsing BODYSTRUCTURE");
        }
        this.msgno = fetchResponse.getNumber();
        if (parseDebug) {
            PrintStream printStream = System.out;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DEBUG IMAP: msgno ");
            outline107.append(this.msgno);
            printStream.println(outline107.toString());
        }
        fetchResponse.skipSpaces();
        if (fetchResponse.readByte() == 40) {
            if (fetchResponse.peekByte() == 40) {
                if (parseDebug) {
                    System.out.println("DEBUG IMAP: parsing multipart");
                }
                this.type = "multipart";
                this.processedType = MULTI;
                ArrayList arrayList = new ArrayList(1);
                do {
                    arrayList.add(new BODYSTRUCTURE(fetchResponse));
                    fetchResponse.skipSpaces();
                } while (fetchResponse.peekByte() == 40);
                this.bodies = (BODYSTRUCTURE[]) arrayList.toArray(new BODYSTRUCTURE[arrayList.size()]);
                this.subtype = fetchResponse.readString();
                if (parseDebug) {
                    GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("DEBUG IMAP: subtype "), this.subtype, System.out);
                }
                if (fetchResponse.readByte() == 41) {
                    if (!parseDebug) {
                        return;
                    }
                    System.out.println("DEBUG IMAP: parse DONE");
                    return;
                }
                if (parseDebug) {
                    System.out.println("DEBUG IMAP: parsing extension data");
                }
                this.cParams = parseParameters(fetchResponse);
                if (fetchResponse.readByte() == 41) {
                    if (!parseDebug) {
                        return;
                    }
                    System.out.println("DEBUG IMAP: body parameters DONE");
                    return;
                }
                byte readByte = fetchResponse.readByte();
                if (readByte == 40) {
                    if (parseDebug) {
                        System.out.println("DEBUG IMAP: parse disposition");
                    }
                    this.disposition = fetchResponse.readString();
                    if (parseDebug) {
                        GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("DEBUG IMAP: disposition "), this.disposition, System.out);
                    }
                    this.dParams = parseParameters(fetchResponse);
                    if (fetchResponse.readByte() == 41) {
                        if (parseDebug) {
                            System.out.println("DEBUG IMAP: disposition DONE");
                        }
                    } else {
                        throw new ParsingException("BODYSTRUCTURE parse error: missing ``)'' at end of disposition in multipart");
                    }
                } else if (readByte != 78 && readByte != 110) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("BODYSTRUCTURE parse error: ");
                    outline1072.append(this.type);
                    outline1072.append("/");
                    outline1072.append(this.subtype);
                    outline1072.append(": bad multipart disposition, b ");
                    outline1072.append((int) readByte);
                    throw new ParsingException(outline1072.toString());
                } else {
                    if (parseDebug) {
                        System.out.println("DEBUG IMAP: disposition NIL");
                    }
                    fetchResponse.skip(2);
                }
                byte readByte2 = fetchResponse.readByte();
                if (readByte2 == 41) {
                    if (!parseDebug) {
                        return;
                    }
                    System.out.println("DEBUG IMAP: no body-fld-lang");
                    return;
                } else if (readByte2 == 32) {
                    if (fetchResponse.peekByte() == 40) {
                        this.language = fetchResponse.readStringList();
                        if (parseDebug) {
                            PrintStream printStream2 = System.out;
                            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("DEBUG IMAP: language len ");
                            outline1073.append(this.language.length);
                            printStream2.println(outline1073.toString());
                        }
                    } else {
                        String readString = fetchResponse.readString();
                        if (readString != null) {
                            this.language = new String[]{readString};
                            if (parseDebug) {
                                PrintStream printStream3 = System.out;
                                printStream3.println("DEBUG IMAP: language " + readString);
                            }
                        }
                    }
                    while (fetchResponse.readByte() == 32) {
                        parseBodyExtension(fetchResponse);
                    }
                    return;
                } else {
                    throw new ParsingException("BODYSTRUCTURE parse error: missing space after disposition");
                }
            } else if (fetchResponse.peekByte() != 41) {
                if (parseDebug) {
                    System.out.println("DEBUG IMAP: single part");
                }
                this.type = fetchResponse.readString();
                if (parseDebug) {
                    GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("DEBUG IMAP: type "), this.type, System.out);
                }
                this.processedType = SINGLE;
                this.subtype = fetchResponse.readString();
                if (parseDebug) {
                    GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("DEBUG IMAP: subtype "), this.subtype, System.out);
                }
                if (this.type == null) {
                    this.type = "application";
                    this.subtype = "octet-stream";
                }
                this.cParams = parseParameters(fetchResponse);
                if (parseDebug) {
                    PrintStream printStream4 = System.out;
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("DEBUG IMAP: cParams ");
                    outline1074.append(this.cParams);
                    printStream4.println(outline1074.toString());
                }
                this.id = fetchResponse.readString();
                if (parseDebug) {
                    GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("DEBUG IMAP: id "), this.id, System.out);
                }
                this.description = fetchResponse.readString();
                if (parseDebug) {
                    GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("DEBUG IMAP: description "), this.description, System.out);
                }
                this.encoding = fetchResponse.readAtomString();
                String str = this.encoding;
                if (str != null && str.equalsIgnoreCase("NIL")) {
                    this.encoding = null;
                }
                if (parseDebug) {
                    GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("DEBUG IMAP: encoding "), this.encoding, System.out);
                }
                this.size = fetchResponse.readNumber();
                if (parseDebug) {
                    PrintStream printStream5 = System.out;
                    StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("DEBUG IMAP: size ");
                    outline1075.append(this.size);
                    printStream5.println(outline1075.toString());
                }
                if (this.size >= 0) {
                    if (this.type.equalsIgnoreCase("text")) {
                        this.lines = fetchResponse.readNumber();
                        if (parseDebug) {
                            PrintStream printStream6 = System.out;
                            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("DEBUG IMAP: lines ");
                            outline1076.append(this.lines);
                            printStream6.println(outline1076.toString());
                        }
                        if (this.lines < 0) {
                            throw new ParsingException("BODYSTRUCTURE parse error: bad ``lines'' element");
                        }
                    } else if (this.type.equalsIgnoreCase("message") && this.subtype.equalsIgnoreCase("rfc822")) {
                        this.processedType = NESTED;
                        fetchResponse.skipSpaces();
                        if (fetchResponse.peekByte() == 40) {
                            this.envelope = new ENVELOPE(fetchResponse);
                            if (parseDebug) {
                                System.out.println("DEBUG IMAP: got envelope of nested message");
                            }
                            this.bodies = new BODYSTRUCTURE[]{new BODYSTRUCTURE(fetchResponse)};
                            this.lines = fetchResponse.readNumber();
                            if (parseDebug) {
                                PrintStream printStream7 = System.out;
                                StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("DEBUG IMAP: lines ");
                                outline1077.append(this.lines);
                                printStream7.println(outline1077.toString());
                            }
                            if (this.lines < 0) {
                                throw new ParsingException("BODYSTRUCTURE parse error: bad ``lines'' element");
                            }
                        } else if (parseDebug) {
                            System.out.println("DEBUG IMAP: missing envelope and body of nested message");
                        }
                    } else {
                        fetchResponse.skipSpaces();
                        if (Character.isDigit((char) fetchResponse.peekByte())) {
                            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("BODYSTRUCTURE parse error: server erroneously included ``lines'' element with type ");
                            outline1078.append(this.type);
                            outline1078.append("/");
                            outline1078.append(this.subtype);
                            throw new ParsingException(outline1078.toString());
                        }
                    }
                    if (fetchResponse.peekByte() == 41) {
                        fetchResponse.readByte();
                        if (!parseDebug) {
                            return;
                        }
                        System.out.println("DEBUG IMAP: parse DONE");
                        return;
                    }
                    this.md5 = fetchResponse.readString();
                    if (fetchResponse.readByte() == 41) {
                        if (!parseDebug) {
                            return;
                        }
                        System.out.println("DEBUG IMAP: no MD5 DONE");
                        return;
                    }
                    byte readByte3 = fetchResponse.readByte();
                    if (readByte3 == 40) {
                        this.disposition = fetchResponse.readString();
                        if (parseDebug) {
                            GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("DEBUG IMAP: disposition "), this.disposition, System.out);
                        }
                        this.dParams = parseParameters(fetchResponse);
                        if (parseDebug) {
                            PrintStream printStream8 = System.out;
                            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("DEBUG IMAP: dParams ");
                            outline1079.append(this.dParams);
                            printStream8.println(outline1079.toString());
                        }
                        if (fetchResponse.readByte() != 41) {
                            throw new ParsingException("BODYSTRUCTURE parse error: missing ``)'' at end of disposition");
                        }
                    } else if (readByte3 != 78 && readByte3 != 110) {
                        StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("BODYSTRUCTURE parse error: ");
                        outline10710.append(this.type);
                        outline10710.append("/");
                        outline10710.append(this.subtype);
                        outline10710.append(": bad single part disposition, b ");
                        outline10710.append((int) readByte3);
                        throw new ParsingException(outline10710.toString());
                    } else {
                        if (parseDebug) {
                            System.out.println("DEBUG IMAP: disposition NIL");
                        }
                        fetchResponse.skip(2);
                    }
                    if (fetchResponse.readByte() == 41) {
                        if (!parseDebug) {
                            return;
                        }
                        System.out.println("DEBUG IMAP: disposition DONE");
                        return;
                    }
                    if (fetchResponse.peekByte() == 40) {
                        this.language = fetchResponse.readStringList();
                        if (parseDebug) {
                            PrintStream printStream9 = System.out;
                            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("DEBUG IMAP: language len ");
                            outline10711.append(this.language.length);
                            printStream9.println(outline10711.toString());
                        }
                    } else {
                        String readString2 = fetchResponse.readString();
                        if (readString2 != null) {
                            this.language = new String[]{readString2};
                            if (parseDebug) {
                                PrintStream printStream10 = System.out;
                                printStream10.println("DEBUG IMAP: language " + readString2);
                            }
                        }
                    }
                    while (fetchResponse.readByte() == 32) {
                        parseBodyExtension(fetchResponse);
                    }
                    if (!parseDebug) {
                        return;
                    }
                    System.out.println("DEBUG IMAP: all DONE");
                    return;
                }
                throw new ParsingException("BODYSTRUCTURE parse error: bad ``size'' element");
            } else {
                throw new ParsingException("BODYSTRUCTURE parse error: missing body content");
            }
        }
        throw new ParsingException("BODYSTRUCTURE parse error: missing ``('' at start");
    }

    private void parseBodyExtension(Response response) throws ParsingException {
        response.skipSpaces();
        byte peekByte = response.peekByte();
        if (peekByte == 40) {
            response.skip(1);
            do {
                parseBodyExtension(response);
            } while (response.readByte() != 41);
        } else if (Character.isDigit((char) peekByte)) {
            response.readNumber();
        } else {
            response.readString();
        }
    }

    private ParameterList parseParameters(Response response) throws ParsingException {
        response.skipSpaces();
        byte readByte = response.readByte();
        if (readByte != 40) {
            if (readByte != 78 && readByte != 110) {
                throw new ParsingException("Parameter list parse error");
            }
            if (parseDebug) {
                System.out.println("DEBUG IMAP: parameter list NIL");
            }
            response.skip(2);
            return null;
        }
        ParameterList parameterList = new ParameterList();
        do {
            String readString = response.readString();
            if (parseDebug) {
                PrintStream printStream = System.out;
                printStream.println("DEBUG IMAP: parameter name " + readString);
            }
            if (readString != null) {
                String readString2 = response.readString();
                if (parseDebug) {
                    PrintStream printStream2 = System.out;
                    printStream2.println("DEBUG IMAP: parameter value " + readString2);
                }
                parameterList.set(readString, readString2);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BODYSTRUCTURE parse error: ");
                outline107.append(this.type);
                outline107.append("/");
                throw new ParsingException(GeneratedOutlineSupport1.outline91(outline107, this.subtype, ": null name in parameter list"));
            }
        } while (response.readByte() != 41);
        parameterList.combineSegments();
        return parameterList;
    }

    public boolean isMulti() {
        return this.processedType == MULTI;
    }

    public boolean isNested() {
        return this.processedType == NESTED;
    }

    public boolean isSingle() {
        return this.processedType == SINGLE;
    }
}
