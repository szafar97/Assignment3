package com.szafar97.assignment3.Calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.szafar97.assignment3.MainActivity;
import com.szafar97.assignment3.R;

public class CalculatorActivity extends MainActivity {

    double answer;
    boolean flag;
    String calculation;
    TextView expression, recent;
    Button all_clear, clear, btn_equal;
    Button[] numbtn, oprbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_calculator);

        LayoutInflater inflater = getLayoutInflater();
        View contentView = inflater.inflate(R.layout.activity_calculator, null, false);
        toolbar.setTitle("Calculator");
        navigationView.getMenu().getItem(0).setEnabled(false);
        drawerLayout.addView(contentView, 0);

        answer = 0;
        flag = false;
        calculation = "";
        numbtn = new Button[11];
        oprbtn = new Button[4];

        expression = (TextView)findViewById(R.id._expression);
        recent = (TextView)findViewById(R.id._recent);
        all_clear = (Button)findViewById(R.id.all_clear);
        clear = (Button)findViewById(R.id.clear);
        numbtn[0] = (Button)findViewById(R.id._0);
        numbtn[1] = (Button)findViewById(R.id._1);
        numbtn[2] = (Button)findViewById(R.id._2);
        numbtn[3] = (Button)findViewById(R.id._3);
        numbtn[4] = (Button)findViewById(R.id._4);
        numbtn[5] = (Button)findViewById(R.id._5);
        numbtn[6] = (Button)findViewById(R.id._6);
        numbtn[7] = (Button)findViewById(R.id._7);
        numbtn[8] = (Button)findViewById(R.id._8);
        numbtn[9] = (Button)findViewById(R.id._9);
        numbtn[10] = (Button)findViewById(R.id._decimal);
        btn_equal = (Button)findViewById(R.id.btn_equal);
        oprbtn[0] = (Button)findViewById(R.id.btn_add);
        oprbtn[1] = (Button)findViewById(R.id.btn_sub);
        oprbtn[2] = (Button)findViewById(R.id.btn_mul);
        oprbtn[3] = (Button)findViewById(R.id.btn_div);

        View.OnClickListener all_clear_function = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.setText("");
                recent.setText("");
                answer = 0;
            }
        };

        View.OnClickListener clear_function = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.setText("");
            }
        };

        View.OnClickListener numbtn_function = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = expression.getText().toString();

                if (flag || exp.contains("Error") || exp.equals("0"))
                {
                    expression.setText("");
                    recent.setText("");
                    exp = "";
                }

                flag = false;
                Button btn = (Button)v;
                String num = btn.getText().toString();

                if (num.equals("."))
                {
                    if(!exp.contains("."))
                    {
                        expression.setText(exp + num);
                    }
                }
                else
                {
                    expression.setText(exp + num);
                }
            }
        };

        View.OnClickListener operator_function = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;

                try
                {
                    if (answer != 0)
                        btn_equal.performClick();
                    else
                        answer = Double.parseDouble(expression.getText().toString());

                    calculation = b.getText().toString();
                    recent.setText(answer + " " + calculation);
                    flag = true;
                }
                catch (NumberFormatException e)
                {
                    expression.setText("Syntax Error");
                }
            }
        };

        View.OnClickListener equal_function = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    switch(calculation)
                    {
                        case "+":
                            expression.setText(Double.toString(answer + Double.parseDouble(expression.getText().toString())));
                            break;

                        case "-":
                            expression.setText(Double.toString(answer - Double.parseDouble(expression.getText().toString())));
                            break;

                        case "÷":
                            expression.setText(Double.toString(answer / Double.parseDouble(expression.getText().toString())));
                            break;

                        case "x":
                            expression.setText(Double.toString(answer * Double.parseDouble(expression.getText().toString())));
                            break;

                        default:
                            break;
                    }

                    if (expression.getText().toString().equals("∞"))
                    {
                        answer = 0;
                        expression.setText("Math Error");
                    }
                    else
                    {
                        answer = Double.parseDouble(expression.getText().toString());
                    }
                    recent.setText("");
                }
                catch (ArithmeticException e)
                {
                    expression.setText("Math Error");
                }
                catch(Exception e)
                {
                    expression.setText("Syntax Error");
                }
            }
        };

        all_clear.setOnClickListener(all_clear_function);
        clear.setOnClickListener(clear_function);
        btn_equal.setOnClickListener(equal_function);

        for (int i = 0; i <= 10; i++)
        {
            numbtn[i].setOnClickListener(numbtn_function);
        }

        for (int i = 0; i < 4; i++)
        {
            oprbtn[i].setOnClickListener(operator_function);
        }
    }
}