package com.sun.mail.imap;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.Utility;
import com.sun.mail.imap.protocol.BODY;
import com.sun.mail.imap.protocol.BODYSTRUCTURE;
import com.sun.mail.imap.protocol.ENVELOPE;
import com.sun.mail.imap.protocol.FetchItem;
import com.sun.mail.imap.protocol.FetchResponse;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sun.mail.imap.protocol.INTERNALDATE;
import com.sun.mail.imap.protocol.Item;
import com.sun.mail.imap.protocol.MODSEQ;
import com.sun.mail.imap.protocol.RFC822DATA;
import com.sun.mail.imap.protocol.RFC822SIZE;
import com.sun.mail.imap.protocol.UID;
import com.sun.mail.util.ReadableMime;
import io.ktor.http.ContentDisposition;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.Header;
import javax.mail.IllegalWriteException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.UIDFolder;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.ParameterList;
/* loaded from: classes3.dex */
public class IMAPMessage extends MimeMessage implements ReadableMime {
    static final String EnvelopeCmd = "ENVELOPE INTERNALDATE RFC822.SIZE";
    private volatile boolean bodyLoaded;
    protected BODYSTRUCTURE bs;
    private String description;
    protected ENVELOPE envelope;
    private volatile boolean headersLoaded;
    protected Map items;
    private Hashtable loadedHeaders;
    private volatile long modseq;
    private Boolean peek;
    private Date receivedDate;
    protected String sectionId;
    private int size;
    private String subject;
    private String type;
    private volatile long uid;

    /* loaded from: classes3.dex */
    public static class FetchProfileCondition implements Utility.Condition {
        private String[] hdrs;
        private Set need = new HashSet();
        private boolean needBodyStructure;
        private boolean needEnvelope;
        private boolean needFlags;
        private boolean needHeaders;
        private boolean needMessage;
        private boolean needSize;
        private boolean needUID;

        public FetchProfileCondition(FetchProfile fetchProfile, FetchItem[] fetchItemArr) {
            this.needEnvelope = false;
            this.needFlags = false;
            this.needBodyStructure = false;
            this.needUID = false;
            this.needHeaders = false;
            this.needSize = false;
            this.needMessage = false;
            this.hdrs = null;
            if (fetchProfile.contains(FetchProfile.Item.ENVELOPE)) {
                this.needEnvelope = true;
            }
            if (fetchProfile.contains(FetchProfile.Item.FLAGS)) {
                this.needFlags = true;
            }
            if (fetchProfile.contains(FetchProfile.Item.CONTENT_INFO)) {
                this.needBodyStructure = true;
            }
            if (fetchProfile.contains(FetchProfile.Item.SIZE)) {
                this.needSize = true;
            }
            if (fetchProfile.contains(UIDFolder.FetchProfileItem.UID)) {
                this.needUID = true;
            }
            if (fetchProfile.contains(IMAPFolder.FetchProfileItem.HEADERS)) {
                this.needHeaders = true;
            }
            if (fetchProfile.contains(IMAPFolder.FetchProfileItem.SIZE)) {
                this.needSize = true;
            }
            if (fetchProfile.contains(IMAPFolder.FetchProfileItem.MESSAGE)) {
                this.needMessage = true;
            }
            this.hdrs = fetchProfile.getHeaderNames();
            for (int i = 0; i < fetchItemArr.length; i++) {
                if (fetchProfile.contains(fetchItemArr[i].getFetchProfileItem())) {
                    this.need.add(fetchItemArr[i]);
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x0084  */
        @Override // com.sun.mail.imap.Utility.Condition
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean test(com.sun.mail.imap.IMAPMessage r7) {
            /*
                r6 = this;
                boolean r0 = r6.needEnvelope
                r1 = 1
                if (r0 == 0) goto L12
                com.sun.mail.imap.protocol.ENVELOPE r0 = com.sun.mail.imap.IMAPMessage.access$000(r7)
                if (r0 != 0) goto L12
                boolean r0 = com.sun.mail.imap.IMAPMessage.access$100(r7)
                if (r0 != 0) goto L12
                return r1
            L12:
                boolean r0 = r6.needFlags
                if (r0 == 0) goto L1d
                javax.mail.Flags r0 = com.sun.mail.imap.IMAPMessage.access$200(r7)
                if (r0 != 0) goto L1d
                return r1
            L1d:
                boolean r0 = r6.needBodyStructure
                if (r0 == 0) goto L2e
                com.sun.mail.imap.protocol.BODYSTRUCTURE r0 = com.sun.mail.imap.IMAPMessage.access$300(r7)
                if (r0 != 0) goto L2e
                boolean r0 = com.sun.mail.imap.IMAPMessage.access$100(r7)
                if (r0 != 0) goto L2e
                return r1
            L2e:
                boolean r0 = r6.needUID
                if (r0 == 0) goto L3d
                long r2 = r7.getUID()
                r4 = -1
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r0 != 0) goto L3d
                return r1
            L3d:
                boolean r0 = r6.needHeaders
                if (r0 == 0) goto L48
                boolean r0 = com.sun.mail.imap.IMAPMessage.access$400(r7)
                if (r0 != 0) goto L48
                return r1
            L48:
                boolean r0 = r6.needSize
                if (r0 == 0) goto L5a
                int r0 = com.sun.mail.imap.IMAPMessage.access$500(r7)
                r2 = -1
                if (r0 != r2) goto L5a
                boolean r0 = com.sun.mail.imap.IMAPMessage.access$100(r7)
                if (r0 != 0) goto L5a
                return r1
            L5a:
                boolean r0 = r6.needMessage
                if (r0 == 0) goto L65
                boolean r0 = com.sun.mail.imap.IMAPMessage.access$100(r7)
                if (r0 != 0) goto L65
                return r1
            L65:
                r0 = 0
                r2 = r0
            L67:
                java.lang.String[] r3 = r6.hdrs
                int r4 = r3.length
                if (r2 >= r4) goto L78
                r3 = r3[r2]
                boolean r3 = com.sun.mail.imap.IMAPMessage.access$600(r7, r3)
                if (r3 != 0) goto L75
                return r1
            L75:
                int r2 = r2 + 1
                goto L67
            L78:
                java.util.Set r2 = r6.need
                java.util.Iterator r2 = r2.iterator()
            L7e:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L99
                java.lang.Object r3 = r2.next()
                com.sun.mail.imap.protocol.FetchItem r3 = (com.sun.mail.imap.protocol.FetchItem) r3
                java.util.Map r4 = r7.items
                if (r4 == 0) goto L98
                java.lang.String r3 = r3.getName()
                java.lang.Object r3 = r4.get(r3)
                if (r3 != 0) goto L7e
            L98:
                return r1
            L99:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPMessage.FetchProfileCondition.test(com.sun.mail.imap.IMAPMessage):boolean");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPMessage(IMAPFolder iMAPFolder, int i) {
        super(iMAPFolder, i);
        this.size = -1;
        this.uid = -1L;
        this.modseq = -1L;
        this.headersLoaded = false;
        this.bodyLoaded = false;
        this.loadedHeaders = new Hashtable(1);
        this.flags = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BODYSTRUCTURE _getBodyStructure() {
        return this.bs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ENVELOPE _getEnvelope() {
        return this.envelope;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Flags _getFlags() {
        return this.flags;
    }

    private InternetAddress[] aaclone(InternetAddress[] internetAddressArr) {
        if (internetAddressArr == null) {
            return null;
        }
        return (InternetAddress[]) internetAddressArr.clone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean areHeadersLoaded() {
        return this.headersLoaded;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHeaderLoaded(String str) {
        if (this.headersLoaded) {
            return true;
        }
        return this.loadedHeaders.containsKey(str.toUpperCase(Locale.ENGLISH));
    }

    private synchronized void loadBODYSTRUCTURE() throws MessagingException {
        if (this.bs != null) {
            return;
        }
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                this.bs = protocol.fetchBodyStructure(getSequenceNumber());
                if (this.bs == null) {
                    forceCheckExpunged();
                    throw new MessagingException("Unable to load BODYSTRUCTURE");
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    private synchronized void loadEnvelope() throws MessagingException {
        if (this.envelope != null) {
            return;
        }
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                int sequenceNumber = getSequenceNumber();
                Response[] fetch = protocol.fetch(sequenceNumber, EnvelopeCmd);
                for (int i = 0; i < fetch.length; i++) {
                    if (fetch[i] != null && (fetch[i] instanceof FetchResponse) && ((FetchResponse) fetch[i]).getNumber() == sequenceNumber) {
                        FetchResponse fetchResponse = (FetchResponse) fetch[i];
                        int itemCount = fetchResponse.getItemCount();
                        for (int i2 = 0; i2 < itemCount; i2++) {
                            Item item = fetchResponse.getItem(i2);
                            if (item instanceof ENVELOPE) {
                                this.envelope = (ENVELOPE) item;
                            } else if (item instanceof INTERNALDATE) {
                                this.receivedDate = ((INTERNALDATE) item).getDate();
                            } else if (item instanceof RFC822SIZE) {
                                this.size = ((RFC822SIZE) item).size;
                            }
                        }
                    }
                }
                protocol.notifyResponseHandlers(fetch);
                protocol.handleResult(fetch[fetch.length - 1]);
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
        if (this.envelope == null) {
            throw new MessagingException("Failed to load IMAP envelope");
        }
    }

    private synchronized void loadFlags() throws MessagingException {
        if (this.flags != null) {
            return;
        }
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                this.flags = protocol.fetchFlags(getSequenceNumber());
                if (this.flags == null) {
                    this.flags = new Flags();
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    private synchronized void loadHeaders() throws MessagingException {
        if (this.headersLoaded) {
            return;
        }
        ByteArrayInputStream byteArrayInputStream = null;
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                if (protocol.isREV1()) {
                    BODY peekBody = protocol.peekBody(getSequenceNumber(), toSection("HEADER"));
                    if (peekBody != null) {
                        byteArrayInputStream = peekBody.getByteArrayInputStream();
                    }
                } else {
                    RFC822DATA fetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), "HEADER");
                    if (fetchRFC822 != null) {
                        byteArrayInputStream = fetchRFC822.getByteArrayInputStream();
                    }
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
        if (byteArrayInputStream != null) {
            this.headers = new InternetHeaders(byteArrayInputStream);
            this.headersLoaded = true;
            return;
        }
        throw new MessagingException("Cannot load header");
    }

    private void setHeaderLoaded(String str) {
        this.loadedHeaders.put(str.toUpperCase(Locale.ENGLISH), str);
    }

    private void setHeadersLoaded(boolean z) {
        this.headersLoaded = z;
    }

    private String toSection(String str) {
        return this.sectionId == null ? str : GeneratedOutlineSupport1.outline92(new StringBuilder(), this.sectionId, ".", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long _getModSeq() {
        return this.modseq;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session _getSession() {
        return this.session;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _setFlags(Flags flags) {
        this.flags = flags;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void addFrom(Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void addHeader(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public void addHeaderLine(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void addRecipients(Message.RecipientType recipientType, Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkExpunged() throws MessageRemovedException {
        if (!this.expunged) {
            return;
        }
        throw new MessageRemovedException();
    }

    protected Object fetchItem(FetchItem fetchItem) throws MessagingException {
        Object obj;
        Object obj2;
        synchronized (getMessageCacheLock()) {
            obj = null;
            try {
                try {
                    IMAPProtocol protocol = getProtocol();
                    checkExpunged();
                    int sequenceNumber = getSequenceNumber();
                    Response[] fetch = protocol.fetch(sequenceNumber, fetchItem.getName());
                    for (int i = 0; i < fetch.length; i++) {
                        if (fetch[i] != null && (fetch[i] instanceof FetchResponse) && ((FetchResponse) fetch[i]).getNumber() == sequenceNumber) {
                            handleExtensionFetchItems(((FetchResponse) fetch[i]).getExtensionItems());
                            if (this.items != null && (obj2 = this.items.get(fetchItem.getName())) != null) {
                                obj = obj2;
                            }
                        }
                    }
                    protocol.notifyResponseHandlers(fetch);
                    protocol.handleResult(fetch[fetch.length - 1]);
                } catch (ConnectionException e) {
                    throw new FolderClosedException(this.folder, e.getMessage());
                } catch (ProtocolException e2) {
                    forceCheckExpunged();
                    throw new MessagingException(e2.getMessage(), e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    protected void forceCheckExpunged() throws MessageRemovedException, FolderClosedException {
        synchronized (getMessageCacheLock()) {
            try {
                getProtocol().noop();
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException unused) {
            }
        }
        if (!this.expunged) {
            return;
        }
        throw new MessageRemovedException();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getAllHeaderLines() throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getAllHeaderLines();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getAllHeaders() throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getAllHeaders();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getContentID() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getContentID();
        }
        loadBODYSTRUCTURE();
        return this.bs.id;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String[] getContentLanguage() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getContentLanguage();
        }
        loadBODYSTRUCTURE();
        String[] strArr = this.bs.language;
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getContentMD5() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getContentMD5();
        }
        loadBODYSTRUCTURE();
        return this.bs.md5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.internet.MimeMessage
    public InputStream getContentStream() throws MessagingException {
        BODY fetchBody;
        if (this.bodyLoaded) {
            return super.getContentStream();
        }
        ByteArrayInputStream byteArrayInputStream = null;
        boolean peek = getPeek();
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                if (protocol.isREV1()) {
                    int i = -1;
                    if (getFetchBlockSize() != -1) {
                        String section = toSection(Constants.Calling.MEDIA_STREAM_TYPE_TEXT);
                        if (this.bs != null && !ignoreBodyStructureSize()) {
                            i = this.bs.size;
                        }
                        return new IMAPInputStream(this, section, i, peek);
                    }
                }
                if (protocol.isREV1()) {
                    if (peek) {
                        fetchBody = protocol.peekBody(getSequenceNumber(), toSection(Constants.Calling.MEDIA_STREAM_TYPE_TEXT));
                    } else {
                        fetchBody = protocol.fetchBody(getSequenceNumber(), toSection(Constants.Calling.MEDIA_STREAM_TYPE_TEXT));
                    }
                    if (fetchBody != null) {
                        byteArrayInputStream = fetchBody.getByteArrayInputStream();
                    }
                } else {
                    RFC822DATA fetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), Constants.Calling.MEDIA_STREAM_TYPE_TEXT);
                    if (fetchRFC822 != null) {
                        byteArrayInputStream = fetchRFC822.getByteArrayInputStream();
                    }
                }
                if (byteArrayInputStream == null) {
                    throw new MessagingException("No content");
                }
                return byteArrayInputStream;
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public synchronized String getContentType() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getContentType();
        }
        if (this.type == null) {
            loadBODYSTRUCTURE();
            this.type = new ContentType(this.bs.type, this.bs.subtype, this.bs.cParams).toString();
        }
        return this.type;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public synchronized DataHandler getDataHandler() throws MessagingException {
        String str;
        checkExpunged();
        if (this.dh == null && !this.bodyLoaded) {
            loadBODYSTRUCTURE();
            if (this.type == null) {
                this.type = new ContentType(this.bs.type, this.bs.subtype, this.bs.cParams).toString();
            }
            if (this.bs.isMulti()) {
                this.dh = new DataHandler(new IMAPMultipartDataSource(this, this.bs.bodies, this.sectionId, this));
            } else if (this.bs.isNested() && isREV1() && this.bs.envelope != null) {
                BODYSTRUCTURE bodystructure = this.bs.bodies[0];
                ENVELOPE envelope = this.bs.envelope;
                if (this.sectionId == null) {
                    str = "1";
                } else {
                    str = this.sectionId + ".1";
                }
                this.dh = new DataHandler(new IMAPNestedMessage(this, bodystructure, envelope, str), this.type);
            }
        }
        return super.getDataHandler();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getDescription() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getDescription();
        }
        String str = this.description;
        if (str != null) {
            return str;
        }
        loadBODYSTRUCTURE();
        String str2 = this.bs.description;
        if (str2 == null) {
            return null;
        }
        try {
            this.description = MimeUtility.decodeText(str2);
        } catch (UnsupportedEncodingException unused) {
            this.description = this.bs.description;
        }
        return this.description;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getDisposition() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getDisposition();
        }
        loadBODYSTRUCTURE();
        return this.bs.disposition;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getEncoding() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getEncoding();
        }
        loadBODYSTRUCTURE();
        return this.bs.encoding;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getFetchBlockSize() {
        return ((IMAPStore) this.folder.getStore()).getFetchBlockSize();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getFileName() throws MessagingException {
        ParameterList parameterList;
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getFileName();
        }
        String str = null;
        loadBODYSTRUCTURE();
        ParameterList parameterList2 = this.bs.dParams;
        if (parameterList2 != null) {
            str = parameterList2.get(ContentDisposition.Parameters.FileName);
        }
        return (str != null || (parameterList = this.bs.cParams) == null) ? str : parameterList.get("name");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public synchronized Flags getFlags() throws MessagingException {
        checkExpunged();
        loadFlags();
        return super.getFlags();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Address[] getFrom() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getFrom();
        }
        loadEnvelope();
        InternetAddress[] internetAddressArr = this.envelope.from;
        if (internetAddressArr == null || internetAddressArr.length == 0) {
            internetAddressArr = this.envelope.sender;
        }
        return aaclone(internetAddressArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String[] getHeader(String str) throws MessagingException {
        ByteArrayInputStream byteArrayInputStream;
        checkExpunged();
        if (isHeaderLoaded(str)) {
            return this.headers.getHeader(str);
        }
        synchronized (getMessageCacheLock()) {
            try {
                try {
                    IMAPProtocol protocol = getProtocol();
                    checkExpunged();
                    if (protocol.isREV1()) {
                        BODY peekBody = protocol.peekBody(getSequenceNumber(), toSection("HEADER.FIELDS (" + str + ")"));
                        if (peekBody != null) {
                            byteArrayInputStream = peekBody.getByteArrayInputStream();
                        }
                        byteArrayInputStream = null;
                    } else {
                        RFC822DATA fetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), "HEADER.LINES (" + str + ")");
                        if (fetchRFC822 != null) {
                            byteArrayInputStream = fetchRFC822.getByteArrayInputStream();
                        }
                        byteArrayInputStream = null;
                    }
                } catch (ConnectionException e) {
                    throw new FolderClosedException(this.folder, e.getMessage());
                }
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
        if (byteArrayInputStream == null) {
            return null;
        }
        if (this.headers == null) {
            this.headers = new InternetHeaders();
        }
        this.headers.load(byteArrayInputStream);
        setHeaderLoaded(str);
        return this.headers.getHeader(str);
    }

    public String getInReplyTo() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getHeader("In-Reply-To", " ");
        }
        loadEnvelope();
        return this.envelope.inReplyTo;
    }

    public synchronized Object getItem(FetchItem fetchItem) throws MessagingException {
        Object obj;
        obj = this.items == null ? null : this.items.get(fetchItem.getName());
        if (obj == null) {
            obj = fetchItem(fetchItem);
        }
        return obj;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public int getLineCount() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.bs.lines;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getMatchingHeaderLines(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getMatchingHeaders(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getMatchingHeaders(strArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getMessageCacheLock() {
        return ((IMAPFolder) this.folder).messageCacheLock;
    }

    @Override // javax.mail.internet.MimeMessage
    public String getMessageID() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getMessageID();
        }
        loadEnvelope();
        return this.envelope.messageId;
    }

    @Override // com.sun.mail.util.ReadableMime
    public InputStream getMimeStream() throws MessagingException {
        BODY fetchBody;
        boolean peek = getPeek();
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                if (protocol.isREV1() && getFetchBlockSize() != -1) {
                    return new IMAPInputStream(this, this.sectionId, -1, peek);
                }
                ByteArrayInputStream byteArrayInputStream = null;
                if (protocol.isREV1()) {
                    if (peek) {
                        fetchBody = protocol.peekBody(getSequenceNumber(), this.sectionId);
                    } else {
                        fetchBody = protocol.fetchBody(getSequenceNumber(), this.sectionId);
                    }
                    if (fetchBody != null) {
                        byteArrayInputStream = fetchBody.getByteArrayInputStream();
                    }
                } else {
                    RFC822DATA fetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), null);
                    if (fetchRFC822 != null) {
                        byteArrayInputStream = fetchRFC822.getByteArrayInputStream();
                    }
                }
                if (byteArrayInputStream != null) {
                    return byteArrayInputStream;
                }
                forceCheckExpunged();
                throw new MessagingException("No content");
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    public synchronized long getModSeq() throws MessagingException {
        if (this.modseq != -1) {
            return this.modseq;
        }
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                MODSEQ fetchMODSEQ = protocol.fetchMODSEQ(getSequenceNumber());
                if (fetchMODSEQ != null) {
                    this.modseq = fetchMODSEQ.modseq;
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
        return this.modseq;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getNonMatchingHeaderLines(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getNonMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getNonMatchingHeaders(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getNonMatchingHeaders(strArr);
    }

    public synchronized boolean getPeek() {
        if (this.peek == null) {
            return ((IMAPStore) this.folder.getStore()).getPeek();
        }
        return this.peek.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPProtocol getProtocol() throws ProtocolException, FolderClosedException {
        ((IMAPFolder) this.folder).waitIfIdle();
        IMAPProtocol iMAPProtocol = ((IMAPFolder) this.folder).protocol;
        if (iMAPProtocol != null) {
            return iMAPProtocol;
        }
        throw new FolderClosedException(this.folder);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Date getReceivedDate() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        Date date = this.receivedDate;
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Address[] getRecipients(Message.RecipientType recipientType) throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getRecipients(recipientType);
        }
        loadEnvelope();
        if (recipientType == Message.RecipientType.TO) {
            return aaclone(this.envelope.to);
        }
        if (recipientType == Message.RecipientType.CC) {
            return aaclone(this.envelope.cc);
        }
        if (recipientType == Message.RecipientType.BCC) {
            return aaclone(this.envelope.bcc);
        }
        return super.getRecipients(recipientType);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Address[] getReplyTo() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getReplyTo();
        }
        loadEnvelope();
        InternetAddress[] internetAddressArr = this.envelope.replyTo;
        if (internetAddressArr != null && internetAddressArr.length != 0) {
            return aaclone(internetAddressArr);
        }
        return getFrom();
    }

    @Override // javax.mail.internet.MimeMessage
    public Address getSender() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getSender();
        }
        loadEnvelope();
        InternetAddress[] internetAddressArr = this.envelope.sender;
        if (internetAddressArr != null && internetAddressArr.length > 0) {
            return internetAddressArr[0];
        }
        return null;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Date getSentDate() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getSentDate();
        }
        loadEnvelope();
        Date date = this.envelope.date;
        if (date != null) {
            return new Date(date.getTime());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getSequenceNumber() {
        return ((IMAPFolder) this.folder).messageCache.seqnumOf(getMessageNumber());
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public int getSize() throws MessagingException {
        checkExpunged();
        if (this.size == -1) {
            loadEnvelope();
        }
        return this.size;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public String getSubject() throws MessagingException {
        checkExpunged();
        if (this.bodyLoaded) {
            return super.getSubject();
        }
        String str = this.subject;
        if (str != null) {
            return str;
        }
        loadEnvelope();
        String str2 = this.envelope.subject;
        if (str2 == null) {
            return null;
        }
        try {
            this.subject = MimeUtility.decodeText(MimeUtility.unfold(str2));
        } catch (UnsupportedEncodingException unused) {
            this.subject = this.envelope.subject;
        }
        return this.subject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getUID() {
        return this.uid;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleExtensionFetchItems(Map map) throws MessagingException {
        if (this.items == null) {
            this.items = new HashMap();
        }
        this.items.putAll(map);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean handleFetchItem(Item item, String[] strArr, boolean z) throws MessagingException {
        ByteArrayInputStream byteArrayInputStream;
        boolean isHeader;
        if (item instanceof Flags) {
            this.flags = (Flags) item;
        } else if (item instanceof ENVELOPE) {
            this.envelope = (ENVELOPE) item;
        } else if (item instanceof INTERNALDATE) {
            this.receivedDate = ((INTERNALDATE) item).getDate();
        } else if (item instanceof RFC822SIZE) {
            this.size = ((RFC822SIZE) item).size;
        } else if (item instanceof MODSEQ) {
            this.modseq = ((MODSEQ) item).modseq;
        } else if (item instanceof BODYSTRUCTURE) {
            this.bs = (BODYSTRUCTURE) item;
        } else if (item instanceof UID) {
            UID uid = (UID) item;
            this.uid = uid.uid;
            Folder folder = this.folder;
            if (((IMAPFolder) folder).uidTable == null) {
                ((IMAPFolder) folder).uidTable = new Hashtable();
            }
            ((IMAPFolder) this.folder).uidTable.put(Long.valueOf(uid.uid), this);
        } else {
            boolean z2 = item instanceof RFC822DATA;
            if (!z2 && !(item instanceof BODY)) {
                return false;
            }
            if (z2) {
                RFC822DATA rfc822data = (RFC822DATA) item;
                byteArrayInputStream = rfc822data.getByteArrayInputStream();
                isHeader = rfc822data.isHeader();
            } else {
                BODY body = (BODY) item;
                byteArrayInputStream = body.getByteArrayInputStream();
                isHeader = body.isHeader();
            }
            if (!isHeader) {
                try {
                    this.size = byteArrayInputStream.available();
                } catch (IOException unused) {
                }
                parse(byteArrayInputStream);
                this.bodyLoaded = true;
                setHeadersLoaded(true);
            } else {
                InternetHeaders internetHeaders = new InternetHeaders();
                if (byteArrayInputStream != null) {
                    internetHeaders.load(byteArrayInputStream);
                }
                if (this.headers != null && !z) {
                    Enumeration allHeaders = internetHeaders.getAllHeaders();
                    while (allHeaders.hasMoreElements()) {
                        Header header = (Header) allHeaders.nextElement();
                        if (!isHeaderLoaded(header.getName())) {
                            this.headers.addHeader(header.getName(), header.getValue());
                        }
                    }
                } else {
                    this.headers = internetHeaders;
                }
                if (z) {
                    setHeadersLoaded(true);
                } else {
                    for (String str : strArr) {
                        setHeaderLoaded(str);
                    }
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean ignoreBodyStructureSize() {
        return ((IMAPStore) this.folder.getStore()).ignoreBodyStructureSize();
    }

    public synchronized void invalidateHeaders() {
        this.headersLoaded = false;
        this.loadedHeaders.clear();
        this.headers = null;
        this.envelope = null;
        this.bs = null;
        this.receivedDate = null;
        this.size = -1;
        this.type = null;
        this.subject = null;
        this.description = null;
        this.flags = null;
        this.content = null;
        this.contentStream = null;
        this.bodyLoaded = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isREV1() throws FolderClosedException {
        IMAPProtocol iMAPProtocol = ((IMAPFolder) this.folder).protocol;
        if (iMAPProtocol != null) {
            return iMAPProtocol.isREV1();
        }
        throw new FolderClosedException(this.folder);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public synchronized boolean isSet(Flags.Flag flag) throws MessagingException {
        checkExpunged();
        loadFlags();
        return super.isSet(flag);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void removeHeader(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage
    public void setContentID(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public void setContentLanguage(String[] strArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public void setContentMD5(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setDataHandler(DataHandler dataHandler) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage
    public void setDescription(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setDisposition(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Message
    public void setExpunged(boolean z) {
        super.setExpunged(z);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setFileName(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public synchronized void setFlags(Flags flags, boolean z) throws MessagingException {
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                protocol.storeFlags(getSequenceNumber(), flags, z);
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setFrom(Address address) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setHeader(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Message
    public void setMessageNumber(int i) {
        super.setMessageNumber(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setModSeq(long j) {
        this.modseq = j;
    }

    public synchronized void setPeek(boolean z) {
        this.peek = Boolean.valueOf(z);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setRecipients(Message.RecipientType recipientType, Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setReplyTo(Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage
    public void setSender(Address address) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setSentDate(Date date) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage
    public void setSubject(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUID(long j) {
        this.uid = j;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        if (this.bodyLoaded) {
            super.writeTo(outputStream);
            return;
        }
        InputStream mimeStream = getMimeStream();
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int read = mimeStream.read(bArr);
                if (read == -1) {
                    return;
                }
                outputStream.write(bArr, 0, read);
            }
        } finally {
            mimeStream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPMessage(Session session) {
        super(session);
        this.size = -1;
        this.uid = -1L;
        this.modseq = -1L;
        this.headersLoaded = false;
        this.bodyLoaded = false;
        this.loadedHeaders = new Hashtable(1);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getHeader(String str, String str2) throws MessagingException {
        checkExpunged();
        if (getHeader(str) == null) {
            return null;
        }
        return this.headers.getHeader(str, str2);
    }
}
