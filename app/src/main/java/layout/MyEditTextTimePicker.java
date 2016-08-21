package layout;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.TimeZone;


/**
 * Created by kcerfus on 8/20/2016.
 */
public class MyEditTextTimePicker implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    EditText _editText;
    private int hour = 12, minute = 0;
    private Context _context;

    public MyEditTextTimePicker(Context context, int editTextViewID)
    {
        Activity act = (Activity)context;
        this._editText = (EditText)act.findViewById(editTextViewID);
        this._editText.setOnClickListener(this);
        this._context = context;
    }

    @Override
    public void onTimeSet(TimePicker view, int pickerHour, int pickerMinute) {
        hour = pickerHour;
        minute = pickerMinute;
        updateDisplay();
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        TimePickerDialog dialog = new TimePickerDialog(_context, this,
                calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),true);
        dialog.show();
    }

    private void updateDisplay() {

        if(hour > 9) {
            if(minute > 9) {
                _editText.setText(new StringBuilder()
                        // Month is 0 based so add 1
                        .append(hour).append(minute));
            }
            else {
                _editText.setText(new StringBuilder()
                        // Month is 0 based so add 1
                        .append(hour).append(0).append(minute));
            }
        }
        else {
            if(minute>9) {
                _editText.setText(new StringBuilder()
                        // Month is 0 based so add 1
                        .append(0).append(hour).append(minute));
            }
            else {
                _editText.setText(new StringBuilder()
                        // Month is 0 based so add 1
                        .append(0).append(hour).append(0).append(minute));
            }
        }
    }
}
