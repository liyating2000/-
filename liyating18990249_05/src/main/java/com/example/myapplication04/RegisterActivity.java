package com.example.myapplication04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication04.bean.UserInfo;
import com.example.myapplication04.database.UserDBHelper;
import com.example.myapplication04.util.DateUtil;
import com.example.myapplication04.util.ViewUtil;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener {
    private static final String TAG = "RegisterActivity"; //Log提示信息
    private EditText et_name; // 声明一个编辑框对象
    private EditText et_phone; // 声明一个编辑框对象
    private EditText et_password; // 声明一个编辑框对象
    private Button btn_register;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_password;

    private UserDBHelper myHelper; // 声明一个用户数据库的帮助器对象
    private int myRequestCode = 0; // 跳转页面时的请求代码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_name = findViewById(R.id.tv_name);
        et_name = findViewById(R.id.et_name);
        tv_phone = findViewById(R.id.tv_phone);
        et_phone = findViewById(R.id.et_phone);
        tv_password = findViewById(R.id.tv_password);
        et_password = findViewById(R.id.et_password);
        btn_register = findViewById(R.id.btn_register);

        // 给et_phone添加文本变更监听器
        et_phone.addTextChangedListener(new RegisterActivity.HideTextWatcher(et_phone));
        // 给et_password添加文本变更监听器
        et_password.addTextChangedListener(new RegisterActivity.HideTextWatcher(et_password));

        btn_register.setOnClickListener((View.OnClickListener) this);
    }

    // 定义编辑框的文本变化监听器
    private class HideTextWatcher implements TextWatcher {
        private EditText myView;// 声明一个编辑框对象
        private int myMaxLength;// 声明一个最大长度变量
        private CharSequence myStr; // 声明一个文本串

        HideTextWatcher(EditText v) {
            super();
            myView = v;
            myMaxLength = ViewUtil.getMaxLength(v);
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            myStr = s;
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {
            if (myStr == null || myStr.length() == 0)
                return;
            // 输入文本达到11位（如手机号码）时关闭输入法
            if (myStr.length() == 11 && myMaxLength == 11) {
                ViewUtil.hideAllInputMethod(RegisterActivity.this);
            }
            // 输入文本达到6位（如登录密码）时关闭输入法
            if (myStr.length() == 8 && myMaxLength == 8) {
                ViewUtil.hideOneInputMethod(RegisterActivity.this, myView);
            }
            if (myStr.length() == 6 && myMaxLength == 6) {
                ViewUtil.hideOneInputMethod(RegisterActivity.this, myView);
            }
        }
    }


    private void showToast(String desc) {
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_register) {
            String name = et_name.getText().toString();
            String phone = et_phone.getText().toString();
            String pwd = et_password.getText().toString();
            if (TextUtils.isEmpty(name)) {
                showToast("请输入姓名");
                return;
            } else if (TextUtils.isEmpty(phone)) {
                showToast("请输入手机号码");
                return;
            } else if (TextUtils.isEmpty(pwd)) {
                showToast("请输入密码");
                return;
            }

            // 以下声明一个用户信息对象，并填写它的各字段值
            UserInfo info = new UserInfo();
            info.name = name;
            info.phone = phone;
            info.pwd = pwd;
            info.update_time = DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss");

            // 执行数据库帮助器的插入操作
            myHelper.insert(info);
            showToast("数据已写入SQLite数据库");

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    protected void onStart() {
        //Log.d(TAG, "onStart: openLink");
        super.onStart();
        // 获得数据库帮助器的实例
        myHelper = UserDBHelper.getInstance(this, 1);
        // 打开数据库帮助器的写连接
        myHelper.openWriteLink();
        // 打开数据库帮助器的读连接
        myHelper.openReadLink();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: closeLink");
        super.onStop();
        // 关闭数据库连接
        myHelper.closeLink();
    }
}