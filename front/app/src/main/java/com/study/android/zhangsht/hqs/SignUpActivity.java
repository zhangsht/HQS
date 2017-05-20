package com.study.android.zhangsht.hqs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URLEncoder;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText rOffice;
    private EditText rUsername;
    private EditText rPassword;
    private EditText rConfirmPwd;

    private MyHandler handler;

    private String parseItemJSONWithJSONObject(String jsonData) {
        String status = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            status = jsonObject.getString("status");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status == null ? "" : status;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
    }

    private void init() {
        rOffice = (EditText) findViewById(R.id.officeId);
        rUsername = (EditText) findViewById(R.id.r_username);
        rPassword = (EditText) findViewById(R.id.r_password);
        rConfirmPwd = (EditText) findViewById(R.id.r_confirm_pwd);
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
        String url = "http://192.168.137.1:8080/iQueue/user";
        try {
            String params = "opcode=" + URLEncoder.encode("register", "UTF-8") +
                    "&officeId=" + URLEncoder.encode(rOffice.getText().toString(), "UTF-8") +
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
                    String status = parseItemJSONWithJSONObject(response);
                    if (status.equals("success")) {
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.putExtra("status", "register");
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, status, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
