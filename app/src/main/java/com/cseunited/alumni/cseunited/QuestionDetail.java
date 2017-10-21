package com.cseunited.alumni.cseunited;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuestionDetail extends AppCompatActivity {

    private ConstraintLayout mLayout;
    private EditText mEditText;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLayout = (ConstraintLayout) findViewById(R.id.question_detail);
        mEditText = (EditText) findViewById(R.id.reply);
        mButton = (Button) findViewById(R.id.addReply);
        mButton.setOnClickListener(onClick());
        TextView textView = new TextView(this);
        textView.setText("New text");
    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLayout.addView(createNewTextView(mEditText.getText().toString()));
            }
        };
    }
    private TextView createNewTextView(String text) {
        final ConstraintLayout.LayoutParams lparams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(text);
        return textView;
    }


}
