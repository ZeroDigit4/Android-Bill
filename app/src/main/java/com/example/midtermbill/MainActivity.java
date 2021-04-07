package com.example.midtermbill;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText BillAmount;
    private TextView Percent, Tip, Total,PerPerson;
    private SeekBar SeekBar;
    private RadioButton None, radTip, radTotal;
    private RadioGroup Rounding;
    private Spinner Spinner;
    private String spintext;
    private int radtemp;
    private int spinpos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        final MediaPlayer weed = MediaPlayer.create(this,R.raw.sound);
        BillAmount = (EditText) findViewById(R.id.txbBillAmount);
        Percent = (TextView) findViewById(R.id.txbPercent);
        Tip = (TextView) findViewById(R.id.txbTip);
        Total = (TextView) findViewById(R.id.txbTotal);
        PerPerson = (TextView) findViewById(R.id.txbPerPerson);
        SeekBar = (SeekBar) findViewById(R.id.seekBar);
        None = (RadioButton) findViewById(R.id.radNone);
        radTip = (RadioButton) findViewById(R.id.radTip);
        radTotal = (RadioButton) findViewById(R.id.radTotal);
        Rounding = (RadioGroup) findViewById(R.id.radRounding);
        Spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rounding_array, R.layout.spinner_style);
        adapter.setDropDownViewResource(R.layout.spinne_dropdown);
        Spinner.setAdapter(adapter);
        Spinner.setSelection(0);
        Button Apply = (Button) findViewById(R.id.Apply);
        Shader textShader = new LinearGradient(0, 0, 0,  Percent.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        Percent.getPaint().setShader(textShader);
        if (BillAmount.getText().toString().matches("")) {
            Tip.setText(getApplicationContext().getResources().getString(R.string._0_00));
            Total.setText(getApplicationContext().getResources().getString(R.string._0_00));
        }
        SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Percent.setText(progress + "%");
                if (BillAmount.getText().toString().matches("")) {
                    Tip.setText(getApplicationContext().getResources().getString(R.string._0_00));
                    Total.setText(getApplicationContext().getResources().getString(R.string._0_00));
                } else if(radtemp == 0) {
                    Tip.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                    Total.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) + Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                } else if(radtemp == 1) {
                    Tip.setText("$" + String.valueOf(Math.round(Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100)));
                    Total.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) + Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                } else if(radtemp == 2) {
                    Tip.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                    Total.setText("$" + String.valueOf(Math.round(Double.parseDouble(BillAmount.getText().toString()) + Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100)));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        BillAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (BillAmount.getText().toString().matches("")) {
                    Tip.setText(getApplicationContext().getResources().getString(R.string._0_00));
                    Total.setText(getApplicationContext().getResources().getString(R.string._0_00));
                } else if(radtemp == 0) {
                    Tip.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                    Total.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) + Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                } else if(radtemp == 1) {
                    Tip.setText("$" + String.valueOf(Math.round(Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100)));
                    Total.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) + Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                } else if(radtemp == 2) {
                    Tip.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                    Total.setText("$" + String.valueOf(Math.round(Double.parseDouble(BillAmount.getText().toString()) + Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100)));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Rounding.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radTip:
                        radtemp = 1;
                        if (BillAmount.getText().toString().matches("")) {
                            Tip.setText("$" +String.valueOf(Math.round(Double.parseDouble(Tip.getText().toString().replace("$", "")))));
                            Total.setText(getApplicationContext().getResources().getString(R.string._0_00));
                            break;
                        }
                        else {
                            Tip.setText("$" + String.valueOf(Math.round(Double.parseDouble(Tip.getText().toString().replace("$", "")))));
                            Total.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) + Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                            break;
                        }
                    case R.id.radTotal:
                        radtemp = 2;
                        if (BillAmount.getText().toString().matches("")) {
                            Tip.setText(getApplicationContext().getResources().getString(R.string._0_00));
                            Total.setText("$" + String.valueOf(Math.round(Double.parseDouble(Total.getText().toString().replace("$", "")))));
                            break;
                        }
                        else {
                            Total.setText("$" + String.valueOf(Math.round(Double.parseDouble(Total.getText().toString().replace("$", "")))));
                            Tip.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                            break;
                        }
                    case R.id.radNone:
                        radtemp = 0;
                        if (BillAmount.getText().toString().matches("")) {
                            Tip.setText(getApplicationContext().getResources().getString(R.string._0_00));
                            Total.setText(getApplicationContext().getResources().getString(R.string._0_00));
                            break;
                        }
                        else {
                        Tip.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                        Total.setText("$" + String.valueOf(Double.parseDouble(BillAmount.getText().toString()) + Double.parseDouble(BillAmount.getText().toString()) * SeekBar.getProgress() / 100));
                        break;
                        }
                }
            }
        });
        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        spintext = Total.getText().toString();
                        spinpos = 0;
                        break;
                    case 1:
                        spintext = "$" + String.valueOf(Math.round(Double.parseDouble(Total.getText().toString().replace("$", "")) / 2));
                        spinpos = 1;
                        break;
                    case 2:
                        spintext = "$" + String.valueOf(Math.round(Double.parseDouble(Total.getText().toString().replace("$", "")) / 3));
                        spinpos = 2;
                        break;
                    case 3:
                        spintext = "$" + String.valueOf(Math.round(Double.parseDouble(Total.getText().toString().replace("$", "")) / 4));
                        spinpos = 3;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
    });

        Typeface face=Typeface.createFromAsset(getAssets(), "allan.ttf");
        Toast toast = Toast.makeText(getApplicationContext(), "NICE WEED BRO!" , Toast.LENGTH_SHORT);
        View toastView = toast.getView(); // This'll return the default View of the Toast.
        /* And now you can get the TextView of the default View of the Toast. */
        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(25);
        toastMessage.setWidth(400);
        toastMessage.setPadding(0,500,0,0);
        toastMessage.setTypeface(face,Typeface.BOLD_ITALIC);
        toastMessage.setTextColor(Color.GREEN);
        toastMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_weed, 0, 0, 0);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toastMessage.setCompoundDrawablePadding(0);
        toastView.setBackgroundResource(R.drawable.hand);
    Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weed.start();

                toast.show();
                if (BillAmount.getText().toString().matches(""))
                PerPerson.setText(getApplicationContext().getResources().getString(R.string._0_00));
                else
                PerPerson.setText(spintext);
                if(spinpos == 0)
                    if(BillAmount.getText().toString().matches(""))
                        PerPerson.setText(getApplicationContext().getResources().getString(R.string._0_00));
                    else
                        PerPerson.setText(Total.getText().toString());
                else if (spinpos == 1)
                    if(BillAmount.getText().toString().matches(""))
                        PerPerson.setText(getApplicationContext().getResources().getString(R.string._0_00));
                    else
                        PerPerson.setText("$" + String.valueOf(Math.round(Double.parseDouble(Total.getText().toString().replace("$", "")) / 2)));
                else if (spinpos == 2)
                    if(BillAmount.getText().toString().matches(""))
                        PerPerson.setText(getApplicationContext().getResources().getString(R.string._0_00));
                    else
                        PerPerson.setText("$" + String.valueOf(Math.round(Double.parseDouble(Total.getText().toString().replace("$", "")) / 3)));
                else if (spinpos == 3)
                    if(BillAmount.getText().toString().matches(""))
                        PerPerson.setText(getApplicationContext().getResources().getString(R.string._0_00));
                    else
                        PerPerson.setText("$" + String.valueOf(Math.round(Double.parseDouble(Total.getText().toString().replace("$", "")) / 4)));
            }
    });


    }

}