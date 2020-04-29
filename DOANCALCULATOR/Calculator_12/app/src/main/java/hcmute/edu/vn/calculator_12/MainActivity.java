package hcmute.edu.vn.calculator_12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private Button btn7, btn8, btn9 ,btn4, btn5, btn6, btn1, btn2, btn3, btn0, btn_chia, btn_nhan, btn_tru, btn_cong;
    private Button btn_C;
    private Button btn_bang;
    private Button btn_cham;
    private EditText editText;


    private String[] array = new String[100];
    private int n = 0;

    private double val1=Double.NaN;
    private double val2;

    private char ACTION = '0';
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '×';
    private final char DIVISION = '÷';
    private final char EQU = 0;

    private boolean trangthai = false ;
    private boolean temp = true;

    SharedPreferences luugiatri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUiView();

        //Tạo luu giá trị
        luugiatri = getSharedPreferences("GiaTri",MODE_PRIVATE);

        trangthai = luugiatri.getBoolean("Trangthaidau", false);

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "7";
                NhapSo(t);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "8";
                NhapSo(t);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "9";
                NhapSo(t);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "4";
                NhapSo(t);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "5";
                NhapSo(t);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "6";
                NhapSo(t);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "1";
                NhapSo(t);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "2";
                NhapSo(t);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "3";
                NhapSo(t);
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "0";
                NhapSo(t);
            }
        });


        btn_chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "÷";
                NhapOperator(t,DIVISION);
            }
        });

        btn_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "×";
                NhapOperator(t,MULTIPLICATION);
            }
        });
        btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "-";
                NhapOperator(t,SUBTRACTION);
            }
        });
        btn_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "+";
                NhapOperator(t,ADDITION);
            }
        });


        btn_cham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = ".";
                String chuoi = editText.getText().toString();
                int demcham = 0, demdau = 0;
                for(int i=0; i<chuoi.length();i++)
                {
                    char c = chuoi.charAt(i);
                    String d = Character.toString(c);
                    if(d.equals(".")){
                        demcham ++;
                    }
                    else if(isOperator(d)){
                        demdau ++;
                    }
                }
                if(demcham == 1){
                    if(demdau == 1){
                        if(isOperator(editText.getText().toString().substring(editText.getText().toString().length()-1))){
                            editText.setText(editText.getText() + "0.");
                        }
                        else{
                            editText.setText(editText.getText() + t);
                        }
                    }
                    else {
                        editText.setText(editText.getText());
                    }
                }
                else if(demcham == 0){
                    if(editText.getText().toString().equals("")){
                        editText.setText("0.");
                    }
                    else if(demdau == 1){
                        editText.setText(editText.getText() + "0.");
                    }
                    else {
                        editText.setText(editText.getText() + t);
                    }
                }
                else{
                    editText.setText(editText.getText());
                }

            }
        });

        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                editText.setText("");
            }
        });

        btn_bang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuanhoa(editText.getText().toString()); // 1+1 => 1 + 1
                if(n == 3){
                    thuchientinhtoan(array[0],array[2],array[1]);// val1=1;val2=1
                    reset();
                    xuatkq();
                    ACTION = '0';
                    LuuGiaTri();
                    //flag = 0;
                    LuuCo.setflag(0);
                    n=0;
                }
                else if(!isOperator(array[0])){
                    editText.setText(editText.getText());
                    n=0;
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Định dạng không hợp lệ";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text,duration).show();
                }
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

    private void reset(){
        n=0;
        //flag_tinh = 0;
        LuuCo.setflag(0);
        array[0] = "0";
        array[1] = "0";
        array[2] = "0";
        SharedPreferences.Editor editor = luugiatri.edit();
        editor.remove("dau");
        editor.remove("Codau");
        editor.remove("Trangthaidau");
        editor.remove("giatrival1");
        editor.commit();
    }

    private boolean kiemtradau(){
        String text = editText.getText().toString().substring(editText.getText().toString().length()-1);
        if(text.equals("+") || text.equals("-") || text.equals("×") || text.equals("÷")){
            return true;
        }
        return false;
    }

    private void thuchientinhtoan(String t1, String t2, String dau){
        val1 = Double.parseDouble(t1);
        val2 = Double.parseDouble(t2);
        char operator = dau.charAt(0);
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
                if(val2 == 0){
                    temp = false;
                }
                else {
                    val1 = val1 / val2;
                }
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
            else{
                array[n++] = d;
            }
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

    public void tinhtoan() {
        chuanhoa(editText.getText().toString()); // 1+1 => 1 + 1
        thuchientinhtoan(array[0],array[2],array[1]);// val1=1;val2=1
        reset();
        xuatkq();
    }

    public void xuatkq(){
        trangthai = true;
        String t = String.valueOf(val1);
        String[] arStr = t.split("\\.");
        if(temp == false)
        {
            editText.setText("");
            Toast.makeText(this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show();
            temp = true;
        }
        else if(arStr[1].equals("0"))
        {
            editText.setText(arStr[0]);
        }
        else {
            editText.setText(String.valueOf(val1));
        }
    }

    public void shownum(String num) {
        String lCurrentValue = editText.getText().toString();
        String lNewValue = num;
        if(trangthai == true){
            editText.setText(lNewValue);
            trangthai = false;
            LuuGiaTri();
        }
        else{
            editText.setText(lCurrentValue+lNewValue);
        }
    }

    public void NhapSo(String t){
        ACTION = luugiatri.getString("dau","0").charAt(0);
        if(ACTION == '0'){
            shownum(t);
        }
        else{
            val1 = Double.parseDouble(luugiatri.getString("giatrival1","0"));
            thuchientinhtoan(String.valueOf(val1),t,String.valueOf(ACTION));
            xuatkq();
            ACTION = '0';
            LuuGiaTri();
        }
    }

    public void NhapOperator(String t,char operator){
        if(editText.getText().toString().equals("")){
            editText.setText("");
        }
        else{
            if(/*flag_tinh == 0*/LuuCo.getFlag() == 0){
                xuatdau(t);
                //flag_tinh = 1;
                LuuCo.setflag(1);
                trangthai = false;
                LuuGiaTri();
            }
            else if(kiemtradau() == true){
                String chuoimoi = editText.getText().toString().substring(0,editText.getText().toString().length()-1);
                editText.setText(chuoimoi + t);
            }
            else{
                tinhtoan();
                ACTION = operator;
                //flag_tinh = 0;
                LuuCo.setflag(0);
                LuuGiaTri();
            }

        }
    }

    public void xuatdau(String operator) {
        editText.setText(editText.getText() + operator);
        //flag_tinh = 1;
        LuuCo.setflag(1);
    }

    private void LuuGiaTri(){
        SharedPreferences.Editor editor = luugiatri.edit();
        editor.putString("dau",String.valueOf(ACTION));
        //editor.putInt("Codau",flag_tinh);
        editor.putBoolean("Trangthaidau",trangthai);
        editor.putString("giatrival1",String.valueOf(val1));
        editor.commit();
    }



    @Override
    protected void onDestroy() {
       // flag_tinh = luugiatri.getInt("Codau",0);
        super.onDestroy();
    }
}
