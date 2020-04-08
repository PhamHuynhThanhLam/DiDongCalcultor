package hcmute.edu.vn.calculator_12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private Button btn7, btn8, btn9 ,btn4, btn5, btn6, btn1, btn2, btn3, btn0, btn_chia, btn_nhan, btn_tru, btn_cong;
    private Button btn_C;
    private Button btn_bang;
    private Button btn_cham;
    private EditText editText;

    private String[] array = new String[100];
    private int n =0;

    private int clear_flag = 0;
    private int flag = 0;
    private int flag_tinh = 0;
    private int Trangthai = 0;

    private double val1=Double.NaN;
    private double val2;

    private char ACTION;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '×';
    private final char DIVISION = '÷';
    private final char EQU = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUiView();


        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "7";
                shownum(t);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "8";
                shownum(t);

            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "9";
                shownum(t);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "4";
                shownum(t);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "5";
                shownum(t);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "6";
                shownum(t);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "1";
                shownum(t);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "2";
                shownum(t);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "3";
                shownum(t);
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "0";
                shownum(t);
            }
        });


        btn_chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "÷";
                flag = 0;
                kiemtra(t);
            }
        });

        btn_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "×";
                flag = 0;
                kiemtra(t);
            }
        });
        btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "-";
                flag = 0;
                kiemtra(t);
            }
        });
        btn_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "+";
                flag = 0;
                kiemtra(t);
            }
        });


        btn_cham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = ".";
                if(editText.getText().toString().equals("")){
                    editText.setText("0.");
                }
                else if(isOperator(editText.getText().toString().substring(editText.getText().toString().length()-1))){
                    editText.setText(editText.getText() + "0.");
                }
                else{
                    editText.setText(editText.getText() + t);
                }
            }
        });
        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_flag = 0;
                flag = 0;
                val1 = Double.NaN;
                val2 = Double.NaN;
                Trangthai = 0;
                flag_tinh = 0;
                editText.setText("");
            }
        });
        btn_bang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 0)
                {
                    tinhtoan();
                    flag = 1;
                }
                else{
                    editText.setText("0");
                    flag = 0;
                }
                flag_tinh = 0;
                clear_flag = 0;
            }
        });
    }

    private void setupUiView(){
        btn7=(Button)findViewById(R.id.btn_7);
        btn8=(Button)findViewById(R.id.btn_8);
        btn9=(Button)findViewById(R.id.btn_9);
        btn4=(Button)findViewById(R.id.btn_4);
        btn5=(Button)findViewById(R.id.btn_5);
        btn6=(Button)findViewById(R.id.btn_6);
        btn1=(Button)findViewById(R.id.btn_1);
        btn2=(Button)findViewById(R.id.btn_2);
        btn3=(Button)findViewById(R.id.btn_3);
        btn0=(Button)findViewById(R.id.btn_0);
        btn_chia=(Button)findViewById(R.id.btn_chia);
        btn_nhan=(Button)findViewById(R.id.btn_x);
        btn_tru=(Button)findViewById(R.id.btn_tru);
        btn_cong=(Button)findViewById(R.id.btn_cong);
        btn_cham=(Button)findViewById(R.id.btn_cham);
        btn_C=(Button)findViewById(R.id.btn_C);
        btn_bang=(Button)findViewById(R.id.btn_bang);
        editText=(EditText)findViewById(R.id.edit_text);
    }


    private void thuchien(){
        val1 = Double.parseDouble(array[0]);
        val2 = Double.parseDouble(array[2]);
        char operator = array[1].charAt(0);
        switch(operator){
            case ADDITION:
                val1 = val1 + val2;
                break;
            case SUBTRACTION:
                val1 = val1 - val2;
                break;
            case MULTIPLICATION:
                val1 = val1 * val2;
                break;
            case DIVISION:
                val1 = val1 / val2;
                break;
            case EQU:
                break;
        }
    }

    private void chuanhoa(String edit){
        String chuoi = edit;
        for(int i=0;i<chuoi.length();i++)
        {
            char c = chuoi.charAt(i);
            String d = Character.toString(c);
            if(!isOperator(d))
            {
                int j = i;
                while ((chuoi.charAt(i) >= '0' && chuoi.charAt(i) <= '9') || chuoi.charAt(i) == '.')
                {
                    i++;
                    if(chuoi.length() <= i)
                    {
                        break;
                    }
                }
                d = chuoi.substring(j, i);
                array[n++] = d;
                i--;
            }
            else   array[n++] = d;
        }
    }

    private static boolean isOperator(String c)
    {
        String operator[] = { "+", "-", "×", "÷"};
        Arrays.sort(operator);
        if (Arrays.binarySearch(operator, c) > -1)
            return true;
        else return false;
    }


    public void shownum(String num) {
        String lCurrentValue = editText.getText().toString();
        String lNewValue = num;
        editText.setText(lCurrentValue+lNewValue);
    }

    public void tinhtoan() {
        chuanhoa(editText.getText().toString());
        thuchien();
        n=0;
        String t = String.valueOf(val1);
        String[] arStr = t.split("\\.");
        if(arStr[1].equals("0"))
        {
            editText.setText(arStr[0]);
        }
        else {
            editText.setText(String.valueOf(val1));
        }
    }

    public void kiemtra(String operator){
        if(clear_flag == 1) {
            String name = editText.getText().toString();
            String name2 = name.substring(0, name.length() - 1);
            editText.setText(name2 + operator);
        }
        else{
            editText.setText(editText.getText() + operator);
            clear_flag = 1;
        }

    }
}
