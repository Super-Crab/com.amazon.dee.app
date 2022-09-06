package com.sun.mail.handlers;

import com.amazonaws.services.s3.util.Mimetypes;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.OutputStream;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataSource;
import javax.mail.internet.ContentType;
import javax.mail.internet.ParseException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
/* loaded from: classes3.dex */
public class text_xml extends text_plain {
    private final DataFlavor[] flavors = {new ActivationDataFlavor(String.class, "text/xml", "XML String"), new ActivationDataFlavor(String.class, Mimetypes.MIMETYPE_XML, "XML String"), new ActivationDataFlavor(StreamSource.class, "text/xml", "XML"), new ActivationDataFlavor(StreamSource.class, Mimetypes.MIMETYPE_XML, "XML")};

    private boolean isXmlType(String str) {
        try {
            ContentType contentType = new ContentType(str);
            if (!contentType.getSubType().equals("xml")) {
                return false;
            }
            if (!contentType.getPrimaryType().equals("text")) {
                if (!contentType.getPrimaryType().equals("application")) {
                    return false;
                }
            }
            return true;
        } catch (RuntimeException | ParseException unused) {
            return false;
        }
    }

    @Override // com.sun.mail.handlers.text_plain
    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        int i = 0;
        while (true) {
            DataFlavor[] dataFlavorArr = this.flavors;
            if (i < dataFlavorArr.length) {
                DataFlavor dataFlavor2 = dataFlavorArr[i];
                if (dataFlavor2.equals(dataFlavor)) {
                    if (dataFlavor2.getRepresentationClass() == String.class) {
                        return super.getContent(dataSource);
                    }
                    if (dataFlavor2.getRepresentationClass() != StreamSource.class) {
                        return null;
                    }
                    return new StreamSource(dataSource.getInputStream());
                }
                i++;
            } else {
                return null;
            }
        }
    }

    @Override // com.sun.mail.handlers.text_plain
    public DataFlavor[] getTransferDataFlavors() {
        return (DataFlavor[]) this.flavors.clone();
    }

    @Override // com.sun.mail.handlers.text_plain
    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        if (isXmlType(str)) {
            if (obj instanceof String) {
                super.writeTo(obj, str, outputStream);
                return;
            } else if (!(obj instanceof DataSource) && !(obj instanceof Source)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid Object type = ");
                outline107.append(obj.getClass());
                outline107.append(". XmlDCH can only convert DataSource or Source to XML.");
                throw new IOException(outline107.toString());
            } else {
                try {
                    Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
                    StreamResult streamResult = new StreamResult(outputStream);
                    if (obj instanceof DataSource) {
                        newTransformer.transform(new StreamSource(((DataSource) obj).getInputStream()), streamResult);
                        return;
                    } else {
                        newTransformer.transform((Source) obj, streamResult);
                        return;
                    }
                } catch (RuntimeException e) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unable to run the JAXP transformer on a stream ");
                    outline1072.append(e.getMessage());
                    IOException iOException = new IOException(outline1072.toString());
                    iOException.initCause(e);
                    throw iOException;
                } catch (TransformerException e2) {
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Unable to run the JAXP transformer on a stream ");
                    outline1073.append(e2.getMessage());
                    IOException iOException2 = new IOException(outline1073.toString());
                    iOException2.initCause(e2);
                    throw iOException2;
                }
            }
        }
        throw new IOException(GeneratedOutlineSupport1.outline75("Invalid content type \"", str, "\" for text/xml DCH"));
    }
}
