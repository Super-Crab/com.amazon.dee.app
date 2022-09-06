package com.facebook.react.devsupport;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.SpannedString;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.StackFrame;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class RedBoxDialog extends Dialog implements AdapterView.OnItemClickListener {
    private boolean isReporting;
    private final DevSupportManager mDevSupportManager;
    private Button mDismissButton;
    private final DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    @Nullable
    private View mLineSeparator;
    @Nullable
    private ProgressBar mLoadingIndicator;
    @Nullable
    private final RedBoxHandler mRedBoxHandler;
    private Button mReloadJsButton;
    @Nullable
    private Button mReportButton;
    private View.OnClickListener mReportButtonOnClickListener;
    private RedBoxHandler.ReportCompletedListener mReportCompletedListener;
    @Nullable
    private TextView mReportTextView;
    private ListView mStackView;

    /* loaded from: classes2.dex */
    private static class OpenStackFrameTask extends AsyncTask<StackFrame, Void, Void> {
        private static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
        private final DevSupportManager mDevSupportManager;

        private static JSONObject stackFrameToJson(StackFrame stackFrame) {
            return new JSONObject(MapBuilder.of("file", stackFrame.getFile(), "methodName", stackFrame.getMethod(), StackTraceHelper.LINE_NUMBER_KEY, Integer.valueOf(stackFrame.getLine()), StackTraceHelper.COLUMN_KEY, Integer.valueOf(stackFrame.getColumn())));
        }

        private OpenStackFrameTask(DevSupportManager devSupportManager) {
            this.mDevSupportManager = devSupportManager;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(StackFrame... stackFrameArr) {
            try {
                String uri = Uri.parse(this.mDevSupportManager.getSourceUrl()).buildUpon().path("/open-stack-frame").query(null).build().toString();
                OkHttpClient okHttpClient = new OkHttpClient();
                for (StackFrame stackFrame : stackFrameArr) {
                    okHttpClient.newCall(new Request.Builder().url(uri).post(RequestBody.create(JSON, stackFrameToJson(stackFrame).toString())).build()).execute();
                }
            } catch (Exception e) {
                FLog.e(ReactConstants.TAG, "Could not open stack frame", e);
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    private static class StackAdapter extends BaseAdapter {
        private static final int VIEW_TYPE_COUNT = 2;
        private static final int VIEW_TYPE_STACKFRAME = 1;
        private static final int VIEW_TYPE_TITLE = 0;
        private final StackFrame[] mStack;
        private final String mTitle;

        /* loaded from: classes2.dex */
        private static class FrameViewHolder {
            private final TextView mFileView;
            private final TextView mMethodView;

            private FrameViewHolder(View view) {
                this.mMethodView = (TextView) view.findViewById(R.id.rn_frame_method);
                this.mFileView = (TextView) view.findViewById(R.id.rn_frame_file);
            }
        }

        public StackAdapter(String str, StackFrame[] stackFrameArr) {
            this.mTitle = str;
            this.mStack = stackFrameArr;
            Assertions.assertNotNull(this.mTitle);
            Assertions.assertNotNull(this.mStack);
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mStack.length + 1;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return i == 0 ? this.mTitle : this.mStack[i - 1];
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return i == 0 ? 0 : 1;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView;
            if (i == 0) {
                if (view != null) {
                    textView = (TextView) view;
                } else {
                    textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.redbox_item_title, viewGroup, false);
                }
                String str = this.mTitle;
                if (str == null) {
                    str = "<unknown title>";
                }
                textView.setText(str.replaceAll("\\x1b\\[[0-9;]*m", ""));
                return textView;
            }
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.redbox_item_frame, viewGroup, false);
                view.setTag(new FrameViewHolder(view));
            }
            StackFrame stackFrame = this.mStack[i - 1];
            FrameViewHolder frameViewHolder = (FrameViewHolder) view.getTag();
            frameViewHolder.mMethodView.setText(stackFrame.getMethod());
            frameViewHolder.mFileView.setText(StackTraceHelper.formatFrameSource(stackFrame));
            frameViewHolder.mMethodView.setTextColor(stackFrame.isCollapsed() ? -5592406 : -1);
            frameViewHolder.mFileView.setTextColor(stackFrame.isCollapsed() ? -8355712 : -5000269);
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 2;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return i > 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RedBoxDialog(Context context, DevSupportManager devSupportManager, @Nullable RedBoxHandler redBoxHandler) {
        super(context, R.style.Theme_Catalyst_RedBox);
        this.isReporting = false;
        this.mReportCompletedListener = new RedBoxHandler.ReportCompletedListener() { // from class: com.facebook.react.devsupport.RedBoxDialog.1
            @Override // com.facebook.react.devsupport.RedBoxHandler.ReportCompletedListener
            public void onReportError(SpannedString spannedString) {
                RedBoxDialog.this.isReporting = false;
                ((Button) Assertions.assertNotNull(RedBoxDialog.this.mReportButton)).setEnabled(true);
                ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.this.mLoadingIndicator)).setVisibility(8);
                ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setText(spannedString);
            }

            @Override // com.facebook.react.devsupport.RedBoxHandler.ReportCompletedListener
            public void onReportSuccess(SpannedString spannedString) {
                RedBoxDialog.this.isReporting = false;
                ((Button) Assertions.assertNotNull(RedBoxDialog.this.mReportButton)).setEnabled(true);
                ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.this.mLoadingIndicator)).setVisibility(8);
                ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setText(spannedString);
            }
        };
        this.mReportButtonOnClickListener = new View.OnClickListener() { // from class: com.facebook.react.devsupport.RedBoxDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RedBoxDialog.this.mRedBoxHandler == null || !RedBoxDialog.this.mRedBoxHandler.isReportEnabled() || RedBoxDialog.this.isReporting) {
                    return;
                }
                RedBoxDialog.this.isReporting = true;
                ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setText("Reporting...");
                ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setVisibility(0);
                ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.this.mLoadingIndicator)).setVisibility(0);
                ((View) Assertions.assertNotNull(RedBoxDialog.this.mLineSeparator)).setVisibility(0);
                ((Button) Assertions.assertNotNull(RedBoxDialog.this.mReportButton)).setEnabled(false);
                RedBoxDialog.this.mRedBoxHandler.reportRedbox(view.getContext(), (String) Assertions.assertNotNull(RedBoxDialog.this.mDevSupportManager.getLastErrorTitle()), (StackFrame[]) Assertions.assertNotNull(RedBoxDialog.this.mDevSupportManager.getLastErrorStack()), RedBoxDialog.this.mDevSupportManager.getSourceUrl(), (RedBoxHandler.ReportCompletedListener) Assertions.assertNotNull(RedBoxDialog.this.mReportCompletedListener));
            }
        };
        requestWindowFeature(1);
        setContentView(R.layout.redbox_view);
        this.mDevSupportManager = devSupportManager;
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mRedBoxHandler = redBoxHandler;
        this.mStackView = (ListView) findViewById(R.id.rn_redbox_stack);
        this.mStackView.setOnItemClickListener(this);
        this.mReloadJsButton = (Button) findViewById(R.id.rn_redbox_reload_button);
        this.mReloadJsButton.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.react.devsupport.RedBoxDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RedBoxDialog.this.mDevSupportManager.handleReloadJS();
            }
        });
        this.mDismissButton = (Button) findViewById(R.id.rn_redbox_dismiss_button);
        this.mDismissButton.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.react.devsupport.RedBoxDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RedBoxDialog.this.dismiss();
            }
        });
        RedBoxHandler redBoxHandler2 = this.mRedBoxHandler;
        if (redBoxHandler2 == null || !redBoxHandler2.isReportEnabled()) {
            return;
        }
        this.mLoadingIndicator = (ProgressBar) findViewById(R.id.rn_redbox_loading_indicator);
        this.mLineSeparator = findViewById(R.id.rn_redbox_line_separator);
        this.mReportTextView = (TextView) findViewById(R.id.rn_redbox_report_label);
        this.mReportTextView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mReportTextView.setHighlightColor(0);
        this.mReportButton = (Button) findViewById(R.id.rn_redbox_report_button);
        this.mReportButton.setOnClickListener(this.mReportButtonOnClickListener);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        new OpenStackFrameTask(this.mDevSupportManager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (StackFrame) this.mStackView.getAdapter().getItem(i));
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 82) {
            this.mDevSupportManager.showDevOptionsDialog();
            return true;
        }
        if (this.mDoubleTapReloadRecognizer.didDoubleTapR(i, getCurrentFocus())) {
            this.mDevSupportManager.handleReloadJS();
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void resetReporting() {
        RedBoxHandler redBoxHandler = this.mRedBoxHandler;
        if (redBoxHandler == null || !redBoxHandler.isReportEnabled()) {
            return;
        }
        this.isReporting = false;
        ((TextView) Assertions.assertNotNull(this.mReportTextView)).setVisibility(8);
        ((ProgressBar) Assertions.assertNotNull(this.mLoadingIndicator)).setVisibility(8);
        ((View) Assertions.assertNotNull(this.mLineSeparator)).setVisibility(8);
        ((Button) Assertions.assertNotNull(this.mReportButton)).setVisibility(0);
        ((Button) Assertions.assertNotNull(this.mReportButton)).setEnabled(true);
    }

    public void setExceptionDetails(String str, StackFrame[] stackFrameArr) {
        this.mStackView.setAdapter((ListAdapter) new StackAdapter(str, stackFrameArr));
    }
}
