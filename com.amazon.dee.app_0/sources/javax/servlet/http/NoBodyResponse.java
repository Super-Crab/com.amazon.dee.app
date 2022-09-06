package javax.servlet.http;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletOutputStream;
/* compiled from: HttpServlet.java */
/* loaded from: classes3.dex */
class NoBodyResponse extends HttpServletResponseWrapper {
    private boolean didSetContentLength;
    private NoBodyOutputStream noBody;
    private PrintWriter writer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NoBodyResponse(HttpServletResponse httpServletResponse) {
        super(httpServletResponse);
        this.noBody = new NoBodyOutputStream();
    }

    private void checkHeader(String str) {
        if ("content-length".equalsIgnoreCase(str)) {
            this.didSetContentLength = true;
        }
    }

    @Override // javax.servlet.http.HttpServletResponseWrapper, javax.servlet.http.HttpServletResponse
    public void addHeader(String str, String str2) {
        super.addHeader(str, str2);
        checkHeader(str);
    }

    @Override // javax.servlet.http.HttpServletResponseWrapper, javax.servlet.http.HttpServletResponse
    public void addIntHeader(String str, int i) {
        super.addIntHeader(str, i);
        checkHeader(str);
    }

    @Override // javax.servlet.ServletResponseWrapper, javax.servlet.ServletResponse
    public ServletOutputStream getOutputStream() throws IOException {
        return this.noBody;
    }

    @Override // javax.servlet.ServletResponseWrapper, javax.servlet.ServletResponse
    public PrintWriter getWriter() throws UnsupportedEncodingException {
        if (this.writer == null) {
            this.writer = new PrintWriter(new OutputStreamWriter(this.noBody, getCharacterEncoding()));
        }
        return this.writer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContentLength() {
        if (!this.didSetContentLength) {
            super.setContentLength(this.noBody.getContentLength());
        }
    }

    @Override // javax.servlet.http.HttpServletResponseWrapper, javax.servlet.http.HttpServletResponse
    public void setHeader(String str, String str2) {
        super.setHeader(str, str2);
        checkHeader(str);
    }

    @Override // javax.servlet.http.HttpServletResponseWrapper, javax.servlet.http.HttpServletResponse
    public void setIntHeader(String str, int i) {
        super.setIntHeader(str, i);
        checkHeader(str);
    }

    @Override // javax.servlet.ServletResponseWrapper, javax.servlet.ServletResponse
    public void setContentLength(int i) {
        super.setContentLength(i);
        this.didSetContentLength = true;
    }
}
