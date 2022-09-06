package javax.mail;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
import org.apache.commons.fileupload.FileUploadBase;
/* loaded from: classes3.dex */
public abstract class Multipart {
    protected Part parent;
    protected Vector parts = new Vector();
    protected String contentType = FileUploadBase.MULTIPART_MIXED;

    public synchronized void addBodyPart(BodyPart bodyPart) throws MessagingException {
        if (this.parts == null) {
            this.parts = new Vector();
        }
        this.parts.addElement(bodyPart);
        bodyPart.setParent(this);
    }

    public synchronized BodyPart getBodyPart(int i) throws MessagingException {
        if (this.parts != null) {
        } else {
            throw new IndexOutOfBoundsException("No such BodyPart");
        }
        return (BodyPart) this.parts.elementAt(i);
    }

    public synchronized String getContentType() {
        return this.contentType;
    }

    public synchronized int getCount() throws MessagingException {
        if (this.parts == null) {
            return 0;
        }
        return this.parts.size();
    }

    public synchronized Part getParent() {
        return this.parent;
    }

    public synchronized boolean removeBodyPart(BodyPart bodyPart) throws MessagingException {
        boolean removeElement;
        if (this.parts != null) {
            removeElement = this.parts.removeElement(bodyPart);
            bodyPart.setParent(null);
        } else {
            throw new MessagingException("No such body part");
        }
        return removeElement;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void setMultipartDataSource(MultipartDataSource multipartDataSource) throws MessagingException {
        this.contentType = multipartDataSource.getContentType();
        int count = multipartDataSource.getCount();
        for (int i = 0; i < count; i++) {
            addBodyPart(multipartDataSource.getBodyPart(i));
        }
    }

    public synchronized void setParent(Part part) {
        this.parent = part;
    }

    public abstract void writeTo(OutputStream outputStream) throws IOException, MessagingException;

    public synchronized void addBodyPart(BodyPart bodyPart, int i) throws MessagingException {
        if (this.parts == null) {
            this.parts = new Vector();
        }
        this.parts.insertElementAt(bodyPart, i);
        bodyPart.setParent(this);
    }

    public synchronized void removeBodyPart(int i) throws MessagingException {
        if (this.parts != null) {
            this.parts.removeElementAt(i);
            ((BodyPart) this.parts.elementAt(i)).setParent(null);
        } else {
            throw new IndexOutOfBoundsException("No such BodyPart");
        }
    }
}
