package com.amazonaws.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.util.XpathUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.w3c.dom.Node;
/* loaded from: classes13.dex */
public class StandardErrorUnmarshaller extends AbstractErrorUnmarshaller<Node> {
    public StandardErrorUnmarshaller() {
    }

    public String getErrorPropertyPath(String str) {
        return GeneratedOutlineSupport1.outline72("ErrorResponse/Error/", str);
    }

    public String parseErrorCode(Node node) throws Exception {
        return XpathUtils.asString("ErrorResponse/Error/Code", node);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StandardErrorUnmarshaller(Class<? extends AmazonServiceException> cls) {
        super(cls);
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(Node node) throws Exception {
        String parseErrorCode = parseErrorCode(node);
        String asString = XpathUtils.asString("ErrorResponse/Error/Type", node);
        String asString2 = XpathUtils.asString("ErrorResponse/RequestId", node);
        AmazonServiceException newException = newException(XpathUtils.asString("ErrorResponse/Error/Message", node));
        newException.setErrorCode(parseErrorCode);
        newException.setRequestId(asString2);
        if (asString == null) {
            newException.setErrorType(AmazonServiceException.ErrorType.Unknown);
        } else if ("Receiver".equalsIgnoreCase(asString)) {
            newException.setErrorType(AmazonServiceException.ErrorType.Service);
        } else if ("Sender".equalsIgnoreCase(asString)) {
            newException.setErrorType(AmazonServiceException.ErrorType.Client);
        }
        return newException;
    }
}
