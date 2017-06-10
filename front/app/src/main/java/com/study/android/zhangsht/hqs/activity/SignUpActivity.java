package com.study.android.zhangsht.hqs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.study.android.zhangsht.hqs.R;
import com.study.android.zhangsht.hqs.utils.HttpCallbackListener;
import com.study.android.zhangsht.hqs.utils.HttpTool;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    AppCompatSpinner spinner;
    private EditText rUsername;
    private EditText rPassword;
    private EditText rConfirmPwd;

    private TextInputLayout nameLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout confirmLayout;

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
    }

    private void init() {
        nameLayout = (TextInputLayout)findViewById(R.id.rNameLayout);
        passwordLayout = (TextInputLayout)findViewById(R.id.rPasswordLayout);
        confirmLayout = (TextInputLayout)findViewById(R.id.confirmLayout);
        rUsername = (EditText) findViewById(R.id.r_username);
        rPassword = (EditText) findViewById(R.id.r_password);
        rConfirmPwd = (EditText) findViewById(R.id.r_confirm_pwd);
        spinner = (AppCompatSpinner)findViewById(R.id.spinner);
        findViewById(R.id.sign_up_button).setOnClickListener(this);
        handler = new MyHandler();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up_button:
                register();
                break;
        }
    }

    private void register() {
        String nameText = rUsername.getText().toString().trim();
        String passwordText = rPassword.getText().toString().trim();
        String confirmText = rConfirmPwd.getText().toString().trim();
        if (nameText.length() == 0) {
            nameLayout.setError("用户名为空");
            return;
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z]\\w{2,9}$");
        Matcher matcher = pattern.matcher(nameText);
        if (!matcher.matches()) {
            nameLayout.setError("用户名格式错误");
            return;
        }
        nameLayout.setError(null);

        if (passwordText.length() == 0) {
            passwordLayout.setError("密码为空");
            return;
        }
        pattern = Pattern.compile("\\w{4,9}$");
        matcher = pattern.matcher(passwordText);
        if (!matcher.matches()) {
            passwordLayout.setError("密码格式错误");
            return;
        }
        passwordLayout.setError(null);

        if (confirmText.length() == 0) {
            confirmLayout.setError("请确认密码");
            return;
        }
        if (!confirmText.equals(passwordText)) {
            confirmLayout.setError("密码输入不一致");
            return;
        }

        confirmLayout.setError(null);

        String url = "http://192.168.137.1:8080/iQueue/user";
        try {
            String params = "opcode=" + URLEncoder.encode("register", "UTF-8") +
                    "&officeId=" + URLEncoder.encode(spinner.getSelectedItem().toString(), "UTF-8") +
                    "&username=" + URLEncoder.encode(rUsername.getText().toString(), "UTF-8")
                    + "&password=" + URLEncoder.encode(rPassword.getText().toString(), "UTF-8");

            HttpTool.sendRequest(url, params, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Message message = new Message();
                    message.what = 1;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(SignUpActivity.this, "登陆请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("HttpURLConnection.POST", "login 请求失败");
        }
    }

    class MyHandler extends Handler {
        public MyHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String response = (String) msg.obj;
                    StartActivityWithData(response);
                    break;
            }
        }
    }

    private void StartActivityWithData(final String response) {
        String status = "";
        String officeId = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            //第二步：因为单条数据，所以用jsonObject.getString方法直接取出对应键值
            status = jsonObject.getString("status");
            officeId = jsonObject.getString("oid");
        } catch (Exception e) {
            Toast.makeText(SignUpActivity.this, "响应解析失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (status.equals("success")) {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(SignUpActivity.this, status, Toast.LENGTH_SHORT).show();
        }
    }


}
