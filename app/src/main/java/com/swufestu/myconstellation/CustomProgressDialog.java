package com.swufestu.myconstellation;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class CustomProgressDialog extends Dialog {

    public TextView messageTv;

    public CustomProgressDialog(Context context) {
        this(context, R.style.MyDialogStyle, "");
    }

    public CustomProgressDialog(Context context, String string) {
        this(context, R.style.MyDialogStyle, string);
    }

    public CustomProgressDialog(Context context, int theme, String string) {
        super(context, theme);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog);
        messageTv = (TextView) findViewById(R.id.tv_message);
        messageTv.setText(string);
        getWindow().getAttributes().gravity = Gravity.CENTER;
        getWindow().getAttributes().dimAmount = 0f;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
