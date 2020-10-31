package com.example.myapplication04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication04.bean.UserInfo;
import com.example.myapplication04.database.UserDBHelper;
import com.example.myapplication04.util.DateUtil;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener {
    private EditText et_name; // 声明一个编辑框对象
    private EditText et_phone; // 声明一个编辑框对象
    private EditText et_password; // 声明一个编辑框对象
    private Button btn_register; // 声明一个登录按钮控件对象
    private UserDBHelper mHelper; // 声明一个用户数据库的帮助器对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        btn_register = findViewById(R.id.btn_register);


        btn_register.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 获得用户数据库帮助器的一个实例
        mHelper = UserDBHelper.getInstance(this, 2);
        // 恢复页面，则打开数据库连接
        mHelper.openWriteLink();

    }

    @Override
    protected void onPause() {
        super.onPause();
        // 暂停页面，则关闭数据库连接
        mHelper.closeLink();

    }

    @Override
    public void onClick(View v) {
        String phone = et_phone.getText().toString();
        if (v.getId() == R.id.btn_register) { // 点击了“注册”按钮
            if (phone.length() < 11) { // 手机号码不足11位
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            } else {
                UserInfo info = new UserInfo();
                info.name = et_name.getText().toString();
                info.phone=et_phone.getText().toString();
                info.pwd = et_password.getText().toString();
                info.update_time = DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss");
                mHelper.insert(info);//往数据库添加用户信息

                Intent intent = new Intent(this, MainActivity.class);

                startActivity(intent);

            }
        }
    }
}