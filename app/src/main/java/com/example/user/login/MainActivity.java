package com.example.user.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText loginid,password;
    Button submit;
    CheckBox checkBox1,checkBox2;
    String myLoginid, myPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        findview();


    }



    public void init(){
        loginid = (EditText)findViewById(R.id.loginid);
        password = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.submit);
        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);

        SharedPreferences setting = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences check = getSharedPreferences("login",MODE_PRIVATE);

        checkBox1.setChecked(check.getBoolean("b1",false));
        checkBox2.setChecked(check.getBoolean("b2",false));
        myLoginid = setting.getString("loginid", "");
        myPassword = setting.getString("password", "");


    }

    public void findview(){


        loginid.setText(myLoginid);
        password.setText(myPassword);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });
    }

    public void doSubmit(){


        if(loginid.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("登入失敗")
                    .setMessage("帳號不得為空")
                    .setPositiveButton("ok",null)
                    .show();

            loginid.setFocusableInTouchMode(true);
            loginid.requestFocus();

        }else if (password.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("登入失敗")
                    .setMessage("密碼不得為空")
                    .setPositiveButton("ok",null)
                    .show();

            password.setFocusableInTouchMode(true);
            password.requestFocus();

        }else {



            SharedPreferences check = getSharedPreferences("login",MODE_PRIVATE);
            check.edit().putBoolean("b1",checkBox1.isChecked()).commit();
            check.edit().putBoolean("b2",checkBox2.isChecked()).commit();

            Intent intent = new Intent(this, CheckActivity.class);

            Bundle bag = new Bundle();
            bag.putString("loginid", loginid.getText().toString());
            bag.putString("password", password.getText().toString());
            bag.putBoolean("b1",checkBox1.isChecked());
            bag.putBoolean("b2",checkBox2.isChecked());

            intent.putExtras(bag);

            startActivity(intent);


            if (checkBox1.isChecked() == false){
                loginid.setText("");
                password.setText("");
            }


        }
    }
}
