package com.example.bal_esspasova.compostingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.string.ok;

/**
 * Created by mykal on 6/14/2017.
 */

public class newSearchActivity extends AppCompatActivity {
    Button   mButton;
    EditText mEdit;
    TextView mText;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mButton = (Button)findViewById(R.id.enterButton);
        mEdit   = (EditText)findViewById(R.id.editText);
        mText = (TextView) findViewById(R.id.textView);




        mButton.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String txt = mEdit.getText().toString(); //Get txt from et when button is clicked
                        mText.setText(txt);

                    }
                });
    }
}
