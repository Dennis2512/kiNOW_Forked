package frontend;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class AlertDialog extends Dialog implements android.view.View.OnClickListener {

    public Activity activity;
    public Dialog dialog;
    public Button yes, no;
    private OnAlertButtonListener onAlertButtonListener;

    public AlertDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_dialog);
        yes = findViewById(R.id.yes_btn);
        no = findViewById(R.id.no_btn);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        onAlertButtonListener = (OnAlertButtonListener) getContext();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes_btn:
                onAlertButtonListener.onYes();
                activity.finish();
                break;
            case R.id.no_btn:
                activity.finish();
                break;
            default:
                break;
        }
        dismiss();
    }

    public interface OnAlertButtonListener {
       void onYes ();
    }//interface
}//class
