package com.sun.mail.imap.protocol;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.clouddrive.model.ConflictResolution;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.auth.Ntlm;
import com.sun.mail.iap.Argument;
import com.sun.mail.iap.BadCommandException;
import com.sun.mail.iap.ByteArray;
import com.sun.mail.iap.CommandFailedException;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.Literal;
import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Protocol;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.imap.ACL;
import com.sun.mail.imap.AppendUID;
import com.sun.mail.imap.CopyUID;
import com.sun.mail.imap.ResyncData;
import com.sun.mail.imap.Rights;
import com.sun.mail.imap.SortTerm;
import com.sun.mail.imap.Utility;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.MailLogger;
import com.sun.mail.util.PropUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import javax.mail.Flags;
import javax.mail.Quota;
import javax.mail.internet.MimeUtility;
import javax.mail.search.SearchException;
import javax.mail.search.SearchTerm;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public class IMAPProtocol extends Protocol {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean authenticated;
    private List authmechs;
    private ByteArray ba;
    private Map capabilities;
    private boolean connected;
    protected Set<String> enabled;
    private volatile String idleTag;
    private String name;
    private boolean noauthdebug;
    private String proxyAuthUser;
    private boolean rev1;
    private SaslAuthenticator saslAuthenticator;
    protected String[] searchCharsets;
    protected SearchSequence searchSequence;
    private static final byte[] CRLF = {13, 10};
    private static final FetchItem[] fetchItems = new FetchItem[0];
    private static final byte[] DONE = {68, 79, 78, 69, 13, 10};

    public IMAPProtocol(String str, String str2, int i, Properties properties, boolean z, MailLogger mailLogger) throws IOException, ProtocolException {
        super(str2, i, properties, GeneratedOutlineSupport1.outline72("mail.", str), z, mailLogger);
        this.connected = false;
        this.rev1 = false;
        this.noauthdebug = true;
        try {
            this.name = str;
            this.noauthdebug = !PropUtil.getBooleanProperty(properties, "mail.debug.auth", false);
            if (this.capabilities == null) {
                capability();
            }
            if (hasCapability("IMAP4rev1")) {
                this.rev1 = true;
            }
            this.searchCharsets = new String[2];
            this.searchCharsets[0] = "UTF-8";
            this.searchCharsets[1] = MimeUtility.mimeCharset(MimeUtility.getDefaultJavaCharset());
            this.connected = true;
        } finally {
            if (!this.connected) {
                disconnect();
            }
        }
    }

    private AppendUID getAppendUID(Response response) {
        byte readByte;
        if (!response.isOK()) {
            return null;
        }
        do {
            readByte = response.readByte();
            if (readByte <= 0) {
                break;
            }
        } while (readByte != 91);
        if (readByte == 0 || !response.readAtom().equalsIgnoreCase("APPENDUID")) {
            return null;
        }
        return new AppendUID(response.readLong(), response.readLong());
    }

    private int[] issueSearch(String str, SearchTerm searchTerm, String str2) throws ProtocolException, SearchException, IOException {
        int[] iArr = null;
        Argument generateSequence = getSearchSequence().generateSequence(searchTerm, str2 == null ? null : MimeUtility.javaCharset(str2));
        generateSequence.writeAtom(str);
        Response[] command = str2 == null ? command("SEARCH", generateSequence) : command("SEARCH CHARSET " + str2, generateSequence);
        Response response = command[command.length - 1];
        if (response.isOK()) {
            ArrayList arrayList = new ArrayList();
            int length = command.length;
            for (int i = 0; i < length; i++) {
                if (command[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                    if (iMAPResponse.keyEquals("SEARCH")) {
                        while (true) {
                            int readNumber = iMAPResponse.readNumber();
                            if (readNumber == -1) {
                                break;
                            }
                            arrayList.add(Integer.valueOf(readNumber));
                        }
                        command[i] = null;
                    }
                }
            }
            int size = arrayList.size();
            iArr = new int[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
            }
        }
        notifyResponseHandlers(command);
        handleResult(response);
        return iArr;
    }

    private Quota parseQuota(Response response) throws ParsingException {
        Quota quota = new Quota(response.readAtomString());
        response.skipSpaces();
        if (response.readByte() == 40) {
            ArrayList arrayList = new ArrayList();
            while (response.peekByte() != 41) {
                String readAtom = response.readAtom();
                if (readAtom != null) {
                    arrayList.add(new Quota.Resource(readAtom, response.readLong(), response.readLong()));
                }
            }
            response.readByte();
            quota.resources = (Quota.Resource[]) arrayList.toArray(new Quota.Resource[arrayList.size()]);
            return quota;
        }
        throw new ParsingException("parse error in QUOTA");
    }

    private static Argument resyncArgs(ResyncData resyncData) {
        Argument argument = new Argument();
        argument.writeAtom("QRESYNC");
        Argument argument2 = new Argument();
        argument2.writeNumber(resyncData.getUIDValidity());
        argument2.writeNumber(resyncData.getModSeq());
        UIDSet[] resyncUIDSet = Utility.getResyncUIDSet(resyncData);
        if (resyncUIDSet != null) {
            argument2.writeString(UIDSet.toString(resyncUIDSet));
        }
        argument.writeArgument(argument2);
        return argument;
    }

    public void append(String str, Flags flags, Date date, Literal literal) throws ProtocolException {
        appenduid(str, flags, date, literal, false);
    }

    public AppendUID appenduid(String str, Flags flags, Date date, Literal literal) throws ProtocolException {
        return appenduid(str, flags, date, literal, true);
    }

    public synchronized void authlogin(String str, String str2) throws ProtocolException {
        Response byeResponse;
        String str3;
        boolean z;
        boolean z2;
        String str4;
        ArrayList arrayList = new ArrayList();
        if (this.noauthdebug && isTracing()) {
            this.logger.fine("AUTHENTICATE LOGIN command trace suppressed");
            suspendTracing();
        }
        try {
            str3 = writeCommand("AUTHENTICATE LOGIN", null);
            byeResponse = null;
            z = false;
        } catch (Exception e) {
            byeResponse = Response.byeResponse(e);
            str3 = null;
            z = true;
        }
        OutputStream outputStream = getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, Integer.MAX_VALUE);
        Response response = byeResponse;
        boolean z3 = true;
        while (!z) {
            try {
                response = readResponse();
            } catch (Exception e2) {
                e = e2;
            }
            if (response.isContinuation()) {
                if (z3) {
                    str4 = str;
                    z2 = false;
                } else {
                    z2 = z3;
                    str4 = str2;
                }
                try {
                    bASE64EncoderStream.write(ASCIIUtility.getBytes(str4));
                    bASE64EncoderStream.flush();
                    byteArrayOutputStream.write(CRLF);
                    outputStream.write(byteArrayOutputStream.toByteArray());
                    outputStream.flush();
                    byteArrayOutputStream.reset();
                    z3 = z2;
                } catch (Exception e3) {
                    e = e3;
                    z3 = z2;
                    response = Response.byeResponse(e);
                    z = true;
                }
            } else {
                if ((!response.isTagged() || !response.getTag().equals(str3)) && !response.isBYE()) {
                    arrayList.add(response);
                }
                z = true;
            }
        }
        resumeTracing();
        notifyResponseHandlers((Response[]) arrayList.toArray(new Response[arrayList.size()]));
        if (this.noauthdebug && isTracing()) {
            this.logger.fine("AUTHENTICATE LOGIN command result: " + response);
        }
        handleResult(response);
        setCapabilities(response);
        this.authenticated = true;
    }

    public synchronized void authntlm(String str, String str2, String str3) throws ProtocolException {
        Response byeResponse;
        boolean z;
        boolean z2;
        String generateType3Msg;
        ArrayList arrayList = new ArrayList();
        int intProperty = PropUtil.getIntProperty(this.props, "mail." + this.name + ".auth.ntlm.flags", 0);
        Ntlm ntlm = new Ntlm(this.props.getProperty("mail." + this.name + ".auth.ntlm.domain", ""), getLocalHost(), str2, str3, this.logger);
        if (this.noauthdebug && isTracing()) {
            this.logger.fine("AUTHENTICATE NTLM command trace suppressed");
            suspendTracing();
        }
        String str4 = null;
        try {
            byeResponse = null;
            str4 = writeCommand("AUTHENTICATE NTLM", null);
            z = false;
        } catch (Exception e) {
            byeResponse = Response.byeResponse(e);
            z = true;
        }
        OutputStream outputStream = getOutputStream();
        Response response = byeResponse;
        boolean z3 = true;
        while (!z) {
            try {
                response = readResponse();
            } catch (Exception e2) {
                e = e2;
            }
            if (response.isContinuation()) {
                if (z3) {
                    generateType3Msg = ntlm.generateType1Msg(intProperty);
                    z2 = false;
                } else {
                    z2 = z3;
                    generateType3Msg = ntlm.generateType3Msg(response.getRest());
                }
                try {
                    outputStream.write(ASCIIUtility.getBytes(generateType3Msg));
                    outputStream.write(CRLF);
                    outputStream.flush();
                    z3 = z2;
                } catch (Exception e3) {
                    e = e3;
                    z3 = z2;
                    response = Response.byeResponse(e);
                    z = true;
                }
            } else {
                if ((!response.isTagged() || !response.getTag().equals(str4)) && !response.isBYE()) {
                    arrayList.add(response);
                }
                z = true;
            }
        }
        resumeTracing();
        notifyResponseHandlers((Response[]) arrayList.toArray(new Response[arrayList.size()]));
        if (this.noauthdebug && isTracing()) {
            this.logger.fine("AUTHENTICATE NTLM command result: " + response);
        }
        handleResult(response);
        setCapabilities(response);
        this.authenticated = true;
    }

    public synchronized void authplain(String str, String str2, String str3) throws ProtocolException {
        Response byeResponse;
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        if (this.noauthdebug && isTracing()) {
            this.logger.fine("AUTHENTICATE PLAIN command trace suppressed");
            suspendTracing();
        }
        String str4 = null;
        try {
            byeResponse = null;
            str4 = writeCommand("AUTHENTICATE PLAIN", null);
        } catch (Exception e) {
            byeResponse = Response.byeResponse(e);
            z = true;
        }
        OutputStream outputStream = getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, Integer.MAX_VALUE);
        while (!z) {
            try {
                byeResponse = readResponse();
            } catch (Exception e2) {
                byeResponse = Response.byeResponse(e2);
            }
            if (byeResponse.isContinuation()) {
                StringBuilder sb = new StringBuilder();
                sb.append(str == null ? "" : str);
                sb.append("\u0000");
                sb.append(str2);
                sb.append("\u0000");
                sb.append(str3);
                bASE64EncoderStream.write(ASCIIUtility.getBytes(sb.toString()));
                bASE64EncoderStream.flush();
                byteArrayOutputStream.write(CRLF);
                outputStream.write(byteArrayOutputStream.toByteArray());
                outputStream.flush();
                byteArrayOutputStream.reset();
            } else {
                if ((!byeResponse.isTagged() || !byeResponse.getTag().equals(str4)) && !byeResponse.isBYE()) {
                    arrayList.add(byeResponse);
                }
                z = true;
            }
        }
        resumeTracing();
        notifyResponseHandlers((Response[]) arrayList.toArray(new Response[arrayList.size()]));
        if (this.noauthdebug && isTracing()) {
            this.logger.fine("AUTHENTICATE PLAIN command result: " + byeResponse);
        }
        handleResult(byeResponse);
        setCapabilities(byeResponse);
        this.authenticated = true;
    }

    public void capability() throws ProtocolException {
        Response[] command = command("CAPABILITY", null);
        if (command[command.length - 1].isOK()) {
            this.capabilities = new HashMap(10);
            this.authmechs = new ArrayList(5);
            int length = command.length;
            for (int i = 0; i < length; i++) {
                if (command[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                    if (iMAPResponse.keyEquals("CAPABILITY")) {
                        parseCapabilities(iMAPResponse);
                    }
                }
            }
            return;
        }
        throw new ProtocolException(command[command.length - 1].toString());
    }

    public void check() throws ProtocolException {
        simpleCommand("CHECK", null);
    }

    public void close() throws ProtocolException {
        simpleCommand("CLOSE", null);
    }

    public void copy(MessageSet[] messageSetArr, String str) throws ProtocolException {
        copyuid(MessageSet.toString(messageSetArr), str, false);
    }

    public CopyUID copyuid(MessageSet[] messageSetArr, String str) throws ProtocolException {
        return copyuid(MessageSet.toString(messageSetArr), str, true);
    }

    public void create(String str) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        simpleCommand("CREATE", argument);
    }

    protected String createFlagList(Flags flags) {
        Flags.Flag[] systemFlags;
        String str;
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("(");
        boolean z = true;
        for (Flags.Flag flag : flags.getSystemFlags()) {
            if (flag == Flags.Flag.ANSWERED) {
                str = "\\Answered";
            } else if (flag == Flags.Flag.DELETED) {
                str = "\\Deleted";
            } else if (flag == Flags.Flag.DRAFT) {
                str = "\\Draft";
            } else if (flag == Flags.Flag.FLAGGED) {
                str = "\\Flagged";
            } else if (flag == Flags.Flag.RECENT) {
                str = "\\Recent";
            } else if (flag == Flags.Flag.SEEN) {
                str = "\\Seen";
            }
            if (z) {
                z = false;
            } else {
                outline103.append(Chars.SPACE);
            }
            outline103.append(str);
        }
        for (String str2 : flags.getUserFlags()) {
            if (z) {
                z = false;
            } else {
                outline103.append(Chars.SPACE);
            }
            outline103.append(str2);
        }
        outline103.append(")");
        return outline103.toString();
    }

    public void delete(String str) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        simpleCommand(Constants.REQUEST_METHOD_DELETE, argument);
    }

    public void deleteACL(String str, String str2) throws ProtocolException {
        if (hasCapability("ACL")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            argument.writeString(str2);
            Response[] command = command("DELETEACL", argument);
            Response response = command[command.length - 1];
            notifyResponseHandlers(command);
            handleResult(response);
            return;
        }
        throw new BadCommandException("ACL not supported");
    }

    @Override // com.sun.mail.iap.Protocol
    public void disconnect() {
        super.disconnect();
        this.authenticated = false;
    }

    protected ListInfo[] doList(String str, String str2, String str3) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str2);
        String encode2 = BASE64MailboxEncoder.encode(str3);
        Argument argument = new Argument();
        argument.writeString(encode);
        argument.writeString(encode2);
        Response[] command = command(str, argument);
        Response response = command[command.length - 1];
        ListInfo[] listInfoArr = null;
        if (response.isOK()) {
            ArrayList arrayList = new ArrayList(1);
            int length = command.length;
            for (int i = 0; i < length; i++) {
                if (command[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                    if (iMAPResponse.keyEquals(str)) {
                        arrayList.add(new ListInfo(iMAPResponse));
                        command[i] = null;
                    }
                }
            }
            if (arrayList.size() > 0) {
                listInfoArr = (ListInfo[]) arrayList.toArray(new ListInfo[arrayList.size()]);
            }
        }
        notifyResponseHandlers(command);
        handleResult(response);
        return listInfoArr;
    }

    public void enable(String str) throws ProtocolException {
        if (hasCapability("ENABLE")) {
            Argument argument = new Argument();
            argument.writeAtom(str);
            simpleCommand("ENABLE", argument);
            if (this.enabled == null) {
                this.enabled = new HashSet();
            }
            this.enabled.add(str.toUpperCase(Locale.ENGLISH));
            return;
        }
        throw new BadCommandException("ENABLE not supported");
    }

    public MailboxInfo examine(String str) throws ProtocolException {
        return examine(str, null);
    }

    public void expunge() throws ProtocolException {
        simpleCommand("EXPUNGE", null);
    }

    public Response[] fetch(MessageSet[] messageSetArr, String str) throws ProtocolException {
        return fetch(MessageSet.toString(messageSetArr), str, false);
    }

    public BODY fetchBody(int i, String str) throws ProtocolException {
        return fetchBody(i, str, false);
    }

    public BODYSTRUCTURE fetchBodyStructure(int i) throws ProtocolException {
        Response[] fetch = fetch(i, "BODYSTRUCTURE");
        notifyResponseHandlers(fetch);
        Response response = fetch[fetch.length - 1];
        if (response.isOK()) {
            return (BODYSTRUCTURE) FetchResponse.getItem(fetch, i, BODYSTRUCTURE.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public Flags fetchFlags(int i) throws ProtocolException {
        Response[] fetch = fetch(i, "FLAGS");
        int length = fetch.length;
        int i2 = 0;
        Flags flags = null;
        while (true) {
            if (i2 < length) {
                if (fetch[i2] != null && (fetch[i2] instanceof FetchResponse) && ((FetchResponse) fetch[i2]).getNumber() == i && (flags = (Flags) ((FetchResponse) fetch[i2]).getItem(FLAGS.class)) != null) {
                    fetch[i2] = null;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        notifyResponseHandlers(fetch);
        handleResult(fetch[fetch.length - 1]);
        return flags;
    }

    public MODSEQ fetchMODSEQ(int i) throws ProtocolException {
        Response[] fetch = fetch(i, "MODSEQ");
        notifyResponseHandlers(fetch);
        Response response = fetch[fetch.length - 1];
        if (response.isOK()) {
            return (MODSEQ) FetchResponse.getItem(fetch, i, MODSEQ.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public RFC822DATA fetchRFC822(int i, String str) throws ProtocolException {
        Response[] fetch = fetch(i, str == null ? "RFC822" : GeneratedOutlineSupport1.outline72("RFC822.", str));
        notifyResponseHandlers(fetch);
        Response response = fetch[fetch.length - 1];
        if (response.isOK()) {
            return (RFC822DATA) FetchResponse.getItem(fetch, i, RFC822DATA.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    protected BODY fetchSectionBody(int i, String str, String str2) throws ProtocolException {
        Response[] fetch = fetch(i, str2);
        notifyResponseHandlers(fetch);
        Response response = fetch[fetch.length - 1];
        if (response.isOK()) {
            List<BODY> items = FetchResponse.getItems(fetch, i, BODY.class);
            if (items.size() == 1) {
                return (BODY) items.get(0);
            }
            if (this.logger.isLoggable(Level.FINEST)) {
                MailLogger mailLogger = this.logger;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("got ");
                outline107.append(items.size());
                outline107.append(" BODY responses for section ");
                outline107.append(str);
                mailLogger.finest(outline107.toString());
            }
            for (BODY body : items) {
                if (this.logger.isLoggable(Level.FINEST)) {
                    MailLogger mailLogger2 = this.logger;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("got BODY section ");
                    outline1072.append(body.getSection());
                    mailLogger2.finest(outline1072.toString());
                }
                if (body.getSection().equalsIgnoreCase(str)) {
                    return body;
                }
            }
            return null;
        } else if (response.isNO()) {
            return null;
        } else {
            handleResult(response);
            return null;
        }
    }

    public void fetchSequenceNumber(long j) throws ProtocolException {
        Response[] fetch = fetch(String.valueOf(j), "UID", true);
        notifyResponseHandlers(fetch);
        handleResult(fetch[fetch.length - 1]);
    }

    public long[] fetchSequenceNumbers(long j, long j2) throws ProtocolException {
        UID uid;
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(j));
        sb.append(":");
        sb.append(j2 == -1 ? "*" : String.valueOf(j2));
        Response[] fetch = fetch(sb.toString(), "UID", true);
        ArrayList arrayList = new ArrayList();
        int length = fetch.length;
        for (int i = 0; i < length; i++) {
            if (fetch[i] != null && (fetch[i] instanceof FetchResponse) && (uid = (UID) ((FetchResponse) fetch[i]).getItem(UID.class)) != null) {
                arrayList.add(uid);
            }
        }
        notifyResponseHandlers(fetch);
        handleResult(fetch[fetch.length - 1]);
        long[] jArr = new long[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            jArr[i2] = ((UID) arrayList.get(i2)).uid;
        }
        return jArr;
    }

    public UID fetchUID(int i) throws ProtocolException {
        Response[] fetch = fetch(i, "UID");
        notifyResponseHandlers(fetch);
        Response response = fetch[fetch.length - 1];
        if (response.isOK()) {
            return (UID) FetchResponse.getItem(fetch, i, UID.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public ACL[] getACL(String str) throws ProtocolException {
        String readAtomString;
        if (hasCapability("ACL")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            Response[] command = command("GETACL", argument);
            Response response = command[command.length - 1];
            ArrayList arrayList = new ArrayList();
            if (response.isOK()) {
                int length = command.length;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("ACL")) {
                            iMAPResponse.readAtomString();
                            while (true) {
                                String readAtomString2 = iMAPResponse.readAtomString();
                                if (readAtomString2 == null || (readAtomString = iMAPResponse.readAtomString()) == null) {
                                    break;
                                }
                                arrayList.add(new ACL(readAtomString2, new Rights(readAtomString)));
                            }
                            command[i] = null;
                        }
                    }
                }
            }
            notifyResponseHandlers(command);
            handleResult(response);
            return (ACL[]) arrayList.toArray(new ACL[arrayList.size()]);
        }
        throw new BadCommandException("ACL not supported");
    }

    public Map getCapabilities() {
        return this.capabilities;
    }

    protected CopyUID getCopyUID(Response[] responseArr) {
        byte readByte;
        for (int length = responseArr.length - 1; length >= 0; length--) {
            Response response = responseArr[length];
            if (response != null && response.isOK()) {
                do {
                    readByte = response.readByte();
                    if (readByte <= 0) {
                        break;
                    }
                } while (readByte != 91);
                if (readByte != 0 && response.readAtom().equalsIgnoreCase("COPYUID")) {
                    return new CopyUID(response.readLong(), UIDSet.parseUIDSets(response.readAtom()), UIDSet.parseUIDSets(response.readAtom()));
                }
            }
        }
        return null;
    }

    public FetchItem[] getFetchItems() {
        return fetchItems;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputStream getIMAPOutputStream() {
        return getOutputStream();
    }

    public String getProxyAuthUser() {
        return this.proxyAuthUser;
    }

    public Quota[] getQuota(String str) throws ProtocolException {
        if (hasCapability("QUOTA")) {
            Argument argument = new Argument();
            argument.writeString(str);
            Response[] command = command("GETQUOTA", argument);
            ArrayList arrayList = new ArrayList();
            Response response = command[command.length - 1];
            if (response.isOK()) {
                int length = command.length;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("QUOTA")) {
                            arrayList.add(parseQuota(iMAPResponse));
                            command[i] = null;
                        }
                    }
                }
            }
            notifyResponseHandlers(command);
            handleResult(response);
            return (Quota[]) arrayList.toArray(new Quota[arrayList.size()]);
        }
        throw new BadCommandException("QUOTA not supported");
    }

    public Quota[] getQuotaRoot(String str) throws ProtocolException {
        Quota.Resource[] resourceArr;
        if (hasCapability("QUOTA")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            Response[] command = command("GETQUOTAROOT", argument);
            Response response = command[command.length - 1];
            Hashtable hashtable = new Hashtable();
            int i = 0;
            if (response.isOK()) {
                int length = command.length;
                for (int i2 = 0; i2 < length; i2++) {
                    if (command[i2] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i2];
                        if (iMAPResponse.keyEquals("QUOTAROOT")) {
                            iMAPResponse.readAtomString();
                            while (true) {
                                String readAtomString = iMAPResponse.readAtomString();
                                if (readAtomString == null || readAtomString.length() <= 0) {
                                    break;
                                }
                                hashtable.put(readAtomString, new Quota(readAtomString));
                            }
                            command[i2] = null;
                        } else if (iMAPResponse.keyEquals("QUOTA")) {
                            Quota parseQuota = parseQuota(iMAPResponse);
                            Quota quota = (Quota) hashtable.get(parseQuota.quotaRoot);
                            if (quota != null && (resourceArr = quota.resources) != null) {
                                Quota.Resource[] resourceArr2 = new Quota.Resource[resourceArr.length + parseQuota.resources.length];
                                System.arraycopy(resourceArr, 0, resourceArr2, 0, resourceArr.length);
                                Quota.Resource[] resourceArr3 = parseQuota.resources;
                                System.arraycopy(resourceArr3, 0, resourceArr2, quota.resources.length, resourceArr3.length);
                                parseQuota.resources = resourceArr2;
                            }
                            hashtable.put(parseQuota.quotaRoot, parseQuota);
                            command[i2] = null;
                        }
                    }
                }
            }
            notifyResponseHandlers(command);
            handleResult(response);
            Quota[] quotaArr = new Quota[hashtable.size()];
            Enumeration elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                quotaArr[i] = (Quota) elements.nextElement();
                i++;
            }
            return quotaArr;
        }
        throw new BadCommandException("GETQUOTAROOT not supported");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.mail.iap.Protocol
    public ByteArray getResponseBuffer() {
        ByteArray byteArray = this.ba;
        this.ba = null;
        return byteArray;
    }

    protected SearchSequence getSearchSequence() {
        if (this.searchSequence == null) {
            this.searchSequence = new SearchSequence();
        }
        return this.searchSequence;
    }

    public boolean hasCapability(String str) {
        if (str.endsWith("*")) {
            String upperCase = GeneratedOutlineSupport1.outline51(str, 1, 0).toUpperCase(Locale.ENGLISH);
            for (String str2 : this.capabilities.keySet()) {
                if (str2.startsWith(upperCase)) {
                    return true;
                }
            }
            return false;
        }
        return this.capabilities.containsKey(str.toUpperCase(Locale.ENGLISH));
    }

    @Deprecated
    public void id(String str) throws ProtocolException {
        HashMap hashMap = new HashMap();
        hashMap.put("GUID", str);
        id(hashMap);
    }

    public void idleAbort() {
        OutputStream outputStream = getOutputStream();
        try {
            outputStream.write(DONE);
            outputStream.flush();
        } catch (Exception e) {
            this.logger.log(Level.FINEST, "Exception aborting IDLE", (Throwable) e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x002e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x002b -> B:14:0x002c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void idleStart() throws com.sun.mail.iap.ProtocolException {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = "IDLE"
            boolean r0 = r5.hasCapability(r0)     // Catch: java.lang.Throwable -> L6e
            if (r0 == 0) goto L66
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L6e
            r0.<init>()     // Catch: java.lang.Throwable -> L6e
            r1 = 0
            r2 = 1
            java.lang.String r3 = "IDLE"
            r4 = 0
            java.lang.String r3 = r5.writeCommand(r3, r4)     // Catch: java.lang.Exception -> L1a com.sun.mail.iap.LiteralException -> L23 java.lang.Throwable -> L6e
            r5.idleTag = r3     // Catch: java.lang.Exception -> L1a com.sun.mail.iap.LiteralException -> L23 java.lang.Throwable -> L6e
            goto L2c
        L1a:
            r1 = move-exception
            com.sun.mail.iap.Response r1 = com.sun.mail.iap.Response.byeResponse(r1)     // Catch: java.lang.Throwable -> L6e
            r0.add(r1)     // Catch: java.lang.Throwable -> L6e
            goto L2b
        L23:
            r1 = move-exception
            com.sun.mail.iap.Response r1 = r1.getResponse()     // Catch: java.lang.Throwable -> L6e
            r0.add(r1)     // Catch: java.lang.Throwable -> L6e
        L2b:
            r1 = r2
        L2c:
            if (r1 != 0) goto L48
            com.sun.mail.iap.Response r3 = r5.readResponse()     // Catch: com.sun.mail.iap.ProtocolException -> L2c java.io.IOException -> L33 java.lang.Throwable -> L6e
            goto L38
        L33:
            r3 = move-exception
            com.sun.mail.iap.Response r3 = com.sun.mail.iap.Response.byeResponse(r3)     // Catch: java.lang.Throwable -> L6e
        L38:
            r0.add(r3)     // Catch: java.lang.Throwable -> L6e
            boolean r4 = r3.isContinuation()     // Catch: java.lang.Throwable -> L6e
            if (r4 != 0) goto L2b
            boolean r3 = r3.isBYE()     // Catch: java.lang.Throwable -> L6e
            if (r3 == 0) goto L2c
            goto L2b
        L48:
            int r1 = r0.size()     // Catch: java.lang.Throwable -> L6e
            com.sun.mail.iap.Response[] r1 = new com.sun.mail.iap.Response[r1]     // Catch: java.lang.Throwable -> L6e
            java.lang.Object[] r0 = r0.toArray(r1)     // Catch: java.lang.Throwable -> L6e
            com.sun.mail.iap.Response[] r0 = (com.sun.mail.iap.Response[]) r0     // Catch: java.lang.Throwable -> L6e
            int r1 = r0.length     // Catch: java.lang.Throwable -> L6e
            int r1 = r1 - r2
            r1 = r0[r1]     // Catch: java.lang.Throwable -> L6e
            r5.notifyResponseHandlers(r0)     // Catch: java.lang.Throwable -> L6e
            boolean r0 = r1.isContinuation()     // Catch: java.lang.Throwable -> L6e
            if (r0 != 0) goto L64
            r5.handleResult(r1)     // Catch: java.lang.Throwable -> L6e
        L64:
            monitor-exit(r5)
            return
        L66:
            com.sun.mail.iap.BadCommandException r0 = new com.sun.mail.iap.BadCommandException     // Catch: java.lang.Throwable -> L6e
            java.lang.String r1 = "IDLE not supported"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L6e
            throw r0     // Catch: java.lang.Throwable -> L6e
        L6e:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.protocol.IMAPProtocol.idleStart():void");
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public boolean isEnabled(String str) {
        Set<String> set = this.enabled;
        if (set == null) {
            return false;
        }
        return set.contains(str.toUpperCase(Locale.ENGLISH));
    }

    public boolean isREV1() {
        return this.rev1;
    }

    public ListInfo[] list(String str, String str2) throws ProtocolException {
        return doList("LIST", str, str2);
    }

    public Rights[] listRights(String str, String str2) throws ProtocolException {
        if (hasCapability("ACL")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            argument.writeString(str2);
            Response[] command = command("LISTRIGHTS", argument);
            Response response = command[command.length - 1];
            ArrayList arrayList = new ArrayList();
            if (response.isOK()) {
                int length = command.length;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("LISTRIGHTS")) {
                            iMAPResponse.readAtomString();
                            iMAPResponse.readAtomString();
                            while (true) {
                                String readAtomString = iMAPResponse.readAtomString();
                                if (readAtomString == null) {
                                    break;
                                }
                                arrayList.add(new Rights(readAtomString));
                            }
                            command[i] = null;
                        }
                    }
                }
            }
            notifyResponseHandlers(command);
            handleResult(response);
            return (Rights[]) arrayList.toArray(new Rights[arrayList.size()]);
        }
        throw new BadCommandException("ACL not supported");
    }

    public void login(String str, String str2) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(str);
        argument.writeString(str2);
        try {
            if (this.noauthdebug && isTracing()) {
                this.logger.fine("LOGIN command trace suppressed");
                suspendTracing();
            }
            Response[] command = command("LOGIN", argument);
            resumeTracing();
            notifyResponseHandlers(command);
            if (this.noauthdebug && isTracing()) {
                MailLogger mailLogger = this.logger;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LOGIN command result: ");
                outline107.append(command[command.length - 1]);
                mailLogger.fine(outline107.toString());
            }
            handleResult(command[command.length - 1]);
            setCapabilities(command[command.length - 1]);
            this.authenticated = true;
        } catch (Throwable th) {
            resumeTracing();
            throw th;
        }
    }

    public void logout() throws ProtocolException {
        try {
            Response[] command = command("LOGOUT", null);
            this.authenticated = false;
            notifyResponseHandlers(command);
        } finally {
            disconnect();
        }
    }

    public ListInfo[] lsub(String str, String str2) throws ProtocolException {
        return doList("LSUB", str, str2);
    }

    public void move(MessageSet[] messageSetArr, String str) throws ProtocolException {
        moveuid(MessageSet.toString(messageSetArr), str, false);
    }

    public CopyUID moveuid(MessageSet[] messageSetArr, String str) throws ProtocolException {
        return moveuid(MessageSet.toString(messageSetArr), str, true);
    }

    public Rights myRights(String str) throws ProtocolException {
        if (hasCapability("ACL")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            Response[] command = command("MYRIGHTS", argument);
            Response response = command[command.length - 1];
            Rights rights = null;
            if (response.isOK()) {
                int length = command.length;
                Rights rights2 = null;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("MYRIGHTS")) {
                            iMAPResponse.readAtomString();
                            String readAtomString = iMAPResponse.readAtomString();
                            if (rights2 == null) {
                                rights2 = new Rights(readAtomString);
                            }
                            command[i] = null;
                        }
                    }
                }
                rights = rights2;
            }
            notifyResponseHandlers(command);
            handleResult(response);
            return rights;
        }
        throw new BadCommandException("ACL not supported");
    }

    public Namespaces namespace() throws ProtocolException {
        if (hasCapability("NAMESPACE")) {
            Namespaces namespaces = null;
            Response[] command = command("NAMESPACE", null);
            Response response = command[command.length - 1];
            if (response.isOK()) {
                int length = command.length;
                Namespaces namespaces2 = null;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("NAMESPACE")) {
                            if (namespaces2 == null) {
                                namespaces2 = new Namespaces(iMAPResponse);
                            }
                            command[i] = null;
                        }
                    }
                }
                namespaces = namespaces2;
            }
            notifyResponseHandlers(command);
            handleResult(response);
            return namespaces;
        }
        throw new BadCommandException("NAMESPACE not supported");
    }

    public void noop() throws ProtocolException {
        this.logger.fine("IMAPProtocol noop");
        simpleCommand("NOOP", null);
    }

    protected void parseCapabilities(Response response) {
        while (true) {
            String readAtom = response.readAtom();
            if (readAtom != null) {
                if (readAtom.length() == 0) {
                    if (response.peekByte() == 93) {
                        return;
                    }
                    response.skipToken();
                } else {
                    this.capabilities.put(readAtom.toUpperCase(Locale.ENGLISH), readAtom);
                    if (readAtom.regionMatches(true, 0, "AUTH=", 0, 5)) {
                        this.authmechs.add(readAtom.substring(5));
                        if (this.logger.isLoggable(Level.FINE)) {
                            MailLogger mailLogger = this.logger;
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AUTH: ");
                            outline107.append(readAtom.substring(5));
                            mailLogger.fine(outline107.toString());
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    public BODY peekBody(int i, String str) throws ProtocolException {
        return fetchBody(i, str, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.mail.iap.Protocol
    public void processGreeting(Response response) throws ProtocolException {
        super.processGreeting(response);
        if (response.isOK()) {
            setCapabilities(response);
        } else if (((IMAPResponse) response).keyEquals("PREAUTH")) {
            this.authenticated = true;
            setCapabilities(response);
        } else {
            disconnect();
            throw new ConnectionException(this, response);
        }
    }

    public boolean processIdleResponse(Response response) throws ProtocolException {
        notifyResponseHandlers(new Response[]{response});
        boolean isBYE = response.isBYE();
        if (response.isTagged() && response.getTag().equals(this.idleTag)) {
            isBYE = true;
        }
        if (isBYE) {
            this.idleTag = null;
        }
        handleResult(response);
        return !isBYE;
    }

    public void proxyauth(String str) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(str);
        simpleCommand("PROXYAUTH", argument);
        this.proxyAuthUser = str;
    }

    public synchronized Response readIdleResponse() {
        Response byeResponse;
        if (this.idleTag == null) {
            return null;
        }
        try {
            byeResponse = readResponse();
        } catch (ProtocolException e) {
            byeResponse = Response.byeResponse(e);
        } catch (IOException e2) {
            byeResponse = Response.byeResponse(e2);
        }
        return byeResponse;
    }

    @Override // com.sun.mail.iap.Protocol
    public Response readResponse() throws IOException, ProtocolException {
        IMAPResponse iMAPResponse = new IMAPResponse(this);
        return iMAPResponse.keyEquals("FETCH") ? new FetchResponse(iMAPResponse, getFetchItems()) : iMAPResponse;
    }

    public void rename(String str, String str2) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str);
        String encode2 = BASE64MailboxEncoder.encode(str2);
        Argument argument = new Argument();
        argument.writeString(encode);
        argument.writeString(encode2);
        simpleCommand(ConflictResolution.RENAME, argument);
    }

    public void sasllogin(String[] strArr, String str, String str2, String str3, String str4) throws ProtocolException {
        String str5;
        List list;
        Properties properties = this.props;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mail.");
        outline107.append(this.name);
        outline107.append(".sasl.usecanonicalhostname");
        if (PropUtil.getBooleanProperty(properties, outline107.toString(), false)) {
            str5 = getInetAddress().getCanonicalHostName();
        } else {
            str5 = this.host;
        }
        if (this.saslAuthenticator == null) {
            try {
                this.saslAuthenticator = (SaslAuthenticator) Class.forName("com.sun.mail.imap.protocol.IMAPSaslAuthenticator").getConstructor(IMAPProtocol.class, String.class, Properties.class, MailLogger.class, String.class).newInstance(this, this.name, this.props, this.logger, str5);
            } catch (Exception e) {
                this.logger.log(Level.FINE, "Can't load SASL authenticator", (Throwable) e);
                return;
            }
        }
        if (strArr != null && strArr.length > 0) {
            list = new ArrayList(strArr.length);
            for (int i = 0; i < strArr.length; i++) {
                if (this.authmechs.contains(strArr[i])) {
                    list.add(strArr[i]);
                }
            }
        } else {
            list = this.authmechs;
        }
        String[] strArr2 = (String[]) list.toArray(new String[list.size()]);
        try {
            if (this.noauthdebug && isTracing()) {
                this.logger.fine("SASL authentication command trace suppressed");
                suspendTracing();
            }
            if (this.saslAuthenticator.authenticate(strArr2, str, str2, str3, str4)) {
                if (this.noauthdebug && isTracing()) {
                    this.logger.fine("SASL authentication succeeded");
                }
                this.authenticated = true;
            } else if (this.noauthdebug && isTracing()) {
                this.logger.fine("SASL authentication failed");
            }
        } finally {
            resumeTracing();
        }
    }

    public int[] search(MessageSet[] messageSetArr, SearchTerm searchTerm) throws ProtocolException, SearchException {
        return search(MessageSet.toString(messageSetArr), searchTerm);
    }

    public MailboxInfo select(String str) throws ProtocolException {
        return select(str, null);
    }

    public void setACL(String str, char c, ACL acl) throws ProtocolException {
        if (hasCapability("ACL")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            argument.writeString(acl.getName());
            String rights = acl.getRights().toString();
            if (c == '+' || c == '-') {
                rights = c + rights;
            }
            argument.writeString(rights);
            Response[] command = command("SETACL", argument);
            Response response = command[command.length - 1];
            notifyResponseHandlers(command);
            handleResult(response);
            return;
        }
        throw new BadCommandException("ACL not supported");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCapabilities(Response response) {
        byte readByte;
        do {
            readByte = response.readByte();
            if (readByte <= 0) {
                break;
            }
        } while (readByte != 91);
        if (readByte != 0 && response.readAtom().equalsIgnoreCase("CAPABILITY")) {
            this.capabilities = new HashMap(10);
            this.authmechs = new ArrayList(5);
            parseCapabilities(response);
        }
    }

    public void setQuota(Quota quota) throws ProtocolException {
        if (hasCapability("QUOTA")) {
            Argument argument = new Argument();
            argument.writeString(quota.quotaRoot);
            Argument argument2 = new Argument();
            if (quota.resources != null) {
                int i = 0;
                while (true) {
                    Quota.Resource[] resourceArr = quota.resources;
                    if (i >= resourceArr.length) {
                        break;
                    }
                    argument2.writeAtom(resourceArr[i].name);
                    argument2.writeNumber(quota.resources[i].limit);
                    i++;
                }
            }
            argument.writeArgument(argument2);
            Response[] command = command("SETQUOTA", argument);
            Response response = command[command.length - 1];
            notifyResponseHandlers(command);
            handleResult(response);
            return;
        }
        throw new BadCommandException("QUOTA not supported");
    }

    public int[] sort(SortTerm[] sortTermArr, SearchTerm searchTerm) throws ProtocolException, SearchException {
        if (hasCapability("SORT*")) {
            if (sortTermArr != null && sortTermArr.length != 0) {
                Argument argument = new Argument();
                Argument argument2 = new Argument();
                for (SortTerm sortTerm : sortTermArr) {
                    argument2.writeAtom(sortTerm.toString());
                }
                argument.writeArgument(argument2);
                argument.writeAtom("UTF-8");
                if (searchTerm != null) {
                    try {
                        argument.append(getSearchSequence().generateSequence(searchTerm, "UTF-8"));
                    } catch (IOException e) {
                        throw new SearchException(e.toString());
                    }
                } else {
                    argument.writeAtom("ALL");
                }
                Response[] command = command("SORT", argument);
                Response response = command[command.length - 1];
                int[] iArr = null;
                if (response.isOK()) {
                    ArrayList arrayList = new ArrayList();
                    int length = command.length;
                    for (int i = 0; i < length; i++) {
                        if (command[i] instanceof IMAPResponse) {
                            IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                            if (iMAPResponse.keyEquals("SORT")) {
                                while (true) {
                                    int readNumber = iMAPResponse.readNumber();
                                    if (readNumber == -1) {
                                        break;
                                    }
                                    arrayList.add(Integer.valueOf(readNumber));
                                }
                                command[i] = null;
                            }
                        }
                    }
                    int size = arrayList.size();
                    iArr = new int[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
                    }
                }
                notifyResponseHandlers(command);
                handleResult(response);
                return iArr;
            }
            throw new BadCommandException("Must have at least one sort term");
        }
        throw new BadCommandException("SORT not supported");
    }

    public void startTLS() throws ProtocolException {
        try {
            super.startTLS("STARTTLS");
        } catch (ProtocolException e) {
            this.logger.log(Level.FINE, "STARTTLS ProtocolException", (Throwable) e);
            throw e;
        } catch (Exception e2) {
            this.logger.log(Level.FINE, "STARTTLS Exception", (Throwable) e2);
            notifyResponseHandlers(new Response[]{Response.byeResponse(e2)});
            disconnect();
            throw new ProtocolException("STARTTLS failure", e2);
        }
    }

    public Status status(String str, String[] strArr) throws ProtocolException {
        if (!isREV1() && !hasCapability("IMAP4SUNVERSION")) {
            throw new BadCommandException("STATUS not supported");
        }
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        Argument argument2 = new Argument();
        if (strArr == null) {
            strArr = Status.standardItems;
        }
        for (String str2 : strArr) {
            argument2.writeAtom(str2);
        }
        argument.writeArgument(argument2);
        Response[] command = command("STATUS", argument);
        Response response = command[command.length - 1];
        Status status = null;
        if (response.isOK()) {
            int length = command.length;
            Status status2 = null;
            for (int i = 0; i < length; i++) {
                if (command[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                    if (iMAPResponse.keyEquals("STATUS")) {
                        if (status2 == null) {
                            status2 = new Status(iMAPResponse);
                        } else {
                            Status.add(status2, new Status(iMAPResponse));
                        }
                        command[i] = null;
                    }
                }
            }
            status = status2;
        }
        notifyResponseHandlers(command);
        handleResult(response);
        return status;
    }

    public void storeFlags(MessageSet[] messageSetArr, Flags flags, boolean z) throws ProtocolException {
        storeFlags(MessageSet.toString(messageSetArr), flags, z);
    }

    public void subscribe(String str) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(BASE64MailboxEncoder.encode(str));
        simpleCommand("SUBSCRIBE", argument);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.mail.iap.Protocol
    public boolean supportsNonSyncLiterals() {
        return hasCapability("LITERAL+");
    }

    public void uidexpunge(UIDSet[] uIDSetArr) throws ProtocolException {
        if (hasCapability("UIDPLUS")) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UID EXPUNGE ");
            outline107.append(UIDSet.toString(uIDSetArr));
            simpleCommand(outline107.toString(), null);
            return;
        }
        throw new BadCommandException("UID EXPUNGE not supported");
    }

    public int[] uidfetchChangedSince(long j, long j2, long j3) throws ProtocolException {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(j));
        sb.append(":");
        sb.append(j2 == -1 ? "*" : String.valueOf(j2));
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("UID FETCH ", sb.toString(), " (FLAGS) (CHANGEDSINCE ");
        outline115.append(String.valueOf(j3));
        outline115.append(")");
        Response[] command = command(outline115.toString(), null);
        ArrayList arrayList = new ArrayList();
        int length = command.length;
        for (int i = 0; i < length; i++) {
            if (command[i] != null && (command[i] instanceof FetchResponse)) {
                arrayList.add(Integer.valueOf(((FetchResponse) command[i]).getNumber()));
            }
        }
        notifyResponseHandlers(command);
        handleResult(command[command.length - 1]);
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr;
    }

    public void unauthenticate() throws ProtocolException {
        if (hasCapability("X-UNAUTHENTICATE")) {
            simpleCommand("UNAUTHENTICATE", null);
            this.authenticated = false;
            return;
        }
        throw new BadCommandException("UNAUTHENTICATE not supported");
    }

    public void unselect() throws ProtocolException {
        if (hasCapability("UNSELECT")) {
            simpleCommand("UNSELECT", null);
            return;
        }
        throw new BadCommandException("UNSELECT not supported");
    }

    public void unsubscribe(String str) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(BASE64MailboxEncoder.encode(str));
        simpleCommand("UNSUBSCRIBE", argument);
    }

    public AppendUID appenduid(String str, Flags flags, Date date, Literal literal, boolean z) throws ProtocolException {
        Flags flags2;
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        if (flags != null) {
            if (flags.contains(Flags.Flag.RECENT)) {
                flags2 = new Flags(flags);
                flags2.remove(Flags.Flag.RECENT);
            } else {
                flags2 = flags;
            }
            argument.writeAtom(createFlagList(flags2));
        }
        if (date != null) {
            argument.writeString(INTERNALDATE.format(date));
        }
        argument.writeBytes(literal);
        Response[] command = command("APPEND", argument);
        notifyResponseHandlers(command);
        handleResult(command[command.length - 1]);
        if (z) {
            return getAppendUID(command[command.length - 1]);
        }
        return null;
    }

    public void copy(int i, int i2, String str) throws ProtocolException {
        copyuid(String.valueOf(i) + ":" + String.valueOf(i2), str, false);
    }

    public CopyUID copyuid(int i, int i2, String str) throws ProtocolException {
        return copyuid(String.valueOf(i) + ":" + String.valueOf(i2), str, true);
    }

    public MailboxInfo examine(String str, ResyncData resyncData) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        if (resyncData != null) {
            if (resyncData == ResyncData.CONDSTORE) {
                if (hasCapability("CONDSTORE")) {
                    argument.writeArgument(new Argument().writeAtom("CONDSTORE"));
                } else {
                    throw new BadCommandException("CONDSTORE not supported");
                }
            } else if (hasCapability("QRESYNC")) {
                argument.writeArgument(resyncArgs(resyncData));
            } else {
                throw new BadCommandException("QRESYNC not supported");
            }
        }
        Response[] command = command("EXAMINE", argument);
        MailboxInfo mailboxInfo = new MailboxInfo(command);
        mailboxInfo.mode = 1;
        notifyResponseHandlers(command);
        handleResult(command[command.length - 1]);
        return mailboxInfo;
    }

    public Response[] fetch(int i, int i2, String str) throws ProtocolException {
        return fetch(String.valueOf(i) + ":" + String.valueOf(i2), str, false);
    }

    protected BODY fetchBody(int i, String str, boolean z) throws ProtocolException {
        if (str == null) {
            str = "";
        }
        return fetchSectionBody(i, str, GeneratedOutlineSupport1.outline92(new StringBuilder(), z ? "BODY.PEEK[" : "BODY[", str, "]"));
    }

    public void move(int i, int i2, String str) throws ProtocolException {
        moveuid(String.valueOf(i) + ":" + String.valueOf(i2), str, false);
    }

    public CopyUID moveuid(int i, int i2, String str) throws ProtocolException {
        return moveuid(String.valueOf(i) + ":" + String.valueOf(i2), str, true);
    }

    public BODY peekBody(int i, String str, int i2, int i3) throws ProtocolException {
        return fetchBody(i, str, i2, i3, true, null);
    }

    public int[] search(SearchTerm searchTerm) throws ProtocolException, SearchException {
        return search("ALL", searchTerm);
    }

    public MailboxInfo select(String str, ResyncData resyncData) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        if (resyncData != null) {
            if (resyncData == ResyncData.CONDSTORE) {
                if (hasCapability("CONDSTORE")) {
                    argument.writeArgument(new Argument().writeAtom("CONDSTORE"));
                } else {
                    throw new BadCommandException("CONDSTORE not supported");
                }
            } else if (hasCapability("QRESYNC")) {
                argument.writeArgument(resyncArgs(resyncData));
            } else {
                throw new BadCommandException("QRESYNC not supported");
            }
        }
        Response[] command = command("SELECT", argument);
        MailboxInfo mailboxInfo = new MailboxInfo(command);
        notifyResponseHandlers(command);
        Response response = command[command.length - 1];
        if (response.isOK()) {
            if (response.toString().indexOf("READ-ONLY") != -1) {
                mailboxInfo.mode = 1;
            } else {
                mailboxInfo.mode = 2;
            }
        }
        handleResult(response);
        return mailboxInfo;
    }

    public void storeFlags(int i, int i2, Flags flags, boolean z) throws ProtocolException {
        storeFlags(String.valueOf(i) + ":" + String.valueOf(i2), flags, z);
    }

    private CopyUID copyuid(String str, String str2, boolean z) throws ProtocolException {
        if (z && !hasCapability("UIDPLUS")) {
            throw new BadCommandException("UIDPLUS not supported");
        }
        String encode = BASE64MailboxEncoder.encode(str2);
        Argument argument = new Argument();
        argument.writeAtom(str);
        argument.writeString(encode);
        Response[] command = command("COPY", argument);
        notifyResponseHandlers(command);
        handleResult(command[command.length - 1]);
        if (!z) {
            return null;
        }
        return getCopyUID(command);
    }

    private CopyUID moveuid(String str, String str2, boolean z) throws ProtocolException {
        if (hasCapability("MOVE")) {
            if (z && !hasCapability("UIDPLUS")) {
                throw new BadCommandException("UIDPLUS not supported");
            }
            String encode = BASE64MailboxEncoder.encode(str2);
            Argument argument = new Argument();
            argument.writeAtom(str);
            argument.writeString(encode);
            Response[] command = command("MOVE", argument);
            notifyResponseHandlers(command);
            handleResult(command[command.length - 1]);
            if (!z) {
                return null;
            }
            return getCopyUID(command);
        }
        throw new BadCommandException("MOVE not supported");
    }

    private int[] search(String str, SearchTerm searchTerm) throws ProtocolException, SearchException {
        if (SearchSequence.isAscii(searchTerm)) {
            try {
                return issueSearch(str, searchTerm, null);
            } catch (IOException unused) {
            }
        }
        int i = 0;
        while (true) {
            String[] strArr = this.searchCharsets;
            if (i < strArr.length) {
                if (strArr[i] != null) {
                    try {
                        return issueSearch(str, searchTerm, strArr[i]);
                    } catch (CommandFailedException unused2) {
                        this.searchCharsets[i] = null;
                    } catch (ProtocolException e) {
                        throw e;
                    } catch (IOException unused3) {
                    } catch (SearchException e2) {
                        throw e2;
                    }
                }
                i++;
            } else {
                throw new SearchException("Search failed");
            }
        }
    }

    public Response[] fetch(int i, String str) throws ProtocolException {
        return fetch(String.valueOf(i), str, false);
    }

    public BODY peekBody(int i, String str, int i2, int i3, ByteArray byteArray) throws ProtocolException {
        return fetchBody(i, str, i2, i3, true, byteArray);
    }

    public void storeFlags(int i, Flags flags, boolean z) throws ProtocolException {
        storeFlags(String.valueOf(i), flags, z);
    }

    private Response[] fetch(String str, String str2, boolean z) throws ProtocolException {
        if (z) {
            return command(GeneratedOutlineSupport1.outline77("UID FETCH ", str, " (", str2, ")"), null);
        }
        return command(GeneratedOutlineSupport1.outline77("FETCH ", str, " (", str2, ")"), null);
    }

    private void storeFlags(String str, Flags flags, boolean z) throws ProtocolException {
        Response[] command;
        if (z) {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("STORE ", str, " +FLAGS ");
            outline115.append(createFlagList(flags));
            command = command(outline115.toString(), null);
        } else {
            StringBuilder outline1152 = GeneratedOutlineSupport1.outline115("STORE ", str, " -FLAGS ");
            outline1152.append(createFlagList(flags));
            command = command(outline1152.toString(), null);
        }
        notifyResponseHandlers(command);
        handleResult(command[command.length - 1]);
    }

    public BODY fetchBody(int i, String str, int i2, int i3) throws ProtocolException {
        return fetchBody(i, str, i2, i3, false, null);
    }

    public Map<String, String> id(Map<String, String> map) throws ProtocolException {
        ID id;
        if (hasCapability("ID")) {
            Response[] command = command("ID", ID.getArgumentList(map));
            Response response = command[command.length - 1];
            if (response.isOK()) {
                int length = command.length;
                id = null;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("ID")) {
                            if (id == null) {
                                id = new ID(iMAPResponse);
                            }
                            command[i] = null;
                        }
                    }
                }
            } else {
                id = null;
            }
            notifyResponseHandlers(command);
            handleResult(response);
            if (id != null) {
                return id.getServerParams();
            }
            return null;
        }
        throw new BadCommandException("ID not supported");
    }

    public BODY fetchBody(int i, String str, int i2, int i3, ByteArray byteArray) throws ProtocolException {
        return fetchBody(i, str, i2, i3, false, byteArray);
    }

    protected BODY fetchBody(int i, String str, int i2, int i3, boolean z, ByteArray byteArray) throws ProtocolException {
        this.ba = byteArray;
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "BODY.PEEK[" : "BODY[");
        sb.append(str);
        sb.append("]<");
        sb.append(String.valueOf(i2));
        sb.append(".");
        sb.append(String.valueOf(i3));
        sb.append(Config.Compare.GREATER_THAN);
        return fetchSectionBody(i, str, sb.toString());
    }

    public void fetchSequenceNumbers(long[] jArr) throws ProtocolException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < jArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(String.valueOf(jArr[i]));
        }
        Response[] fetch = fetch(stringBuffer.toString(), "UID", true);
        notifyResponseHandlers(fetch);
        handleResult(fetch[fetch.length - 1]);
    }

    public IMAPProtocol(InputStream inputStream, PrintStream printStream, Properties properties, boolean z) throws IOException {
        super(inputStream, printStream, properties, z);
        this.connected = false;
        this.rev1 = false;
        this.noauthdebug = true;
        this.name = "imap";
        this.noauthdebug = !PropUtil.getBooleanProperty(properties, "mail.debug.auth", false);
        if (this.capabilities == null) {
            this.capabilities = new HashMap();
        }
        this.searchCharsets = new String[2];
        String[] strArr = this.searchCharsets;
        strArr[0] = "UTF-8";
        strArr[1] = MimeUtility.mimeCharset(MimeUtility.getDefaultJavaCharset());
        this.connected = true;
    }
}
