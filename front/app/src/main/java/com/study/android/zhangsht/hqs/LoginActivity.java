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

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_up).setOnClickListener(this);
        handler = new MyHandler();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                login();
                break;
            case R.id.sign_up:
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void login() {
        String url = "http://192.168.137.1:8080/iQueue/user";
        try {
            String params = "opcode=" + URLEncoder.encode("login", "UTF-8") +
                    "&username=" + URLEncoder.encode(username.getText().toString(), "UTF-8")
                    + "&password=" + URLEncoder.encode(password.getText().toString(), "UTF-8");
            HttpTool.sendRequest(url, params, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Message message = new Message();
                    message.what = 0;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(LoginActivity.this, "登陆请求失败", Toast.LENGTH_SHORT).show();
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
                case 0:
                    String response = (String) msg.obj;
                    String status = parseItemJSONWithJSONObject(response);
                    if (status.equals("success")) {
                        StartActivityWithData();
                    } else {
                        Toast.makeText(LoginActivity.this, status, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    private void StartActivityWithData() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("status", "login");
                startActivity(intent);
                finish();
            }
        });
    }

    private String parseItemJSONWithJSONObject(String jsonData) {
        String status = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            //第二步：因为单条数据，所以用jsonObject.getString方法直接取出对应键值
            status = jsonObject.getString("status");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status == null ? "" : status;


        /*StringBuffer sb =new StringBuffer();
        try {
            //第一步：将从网络字符串jsonData字符串装入JSONObject，即JSONObject
            JSONObject jsonObject = new JSONObject(jsonData);
            //第二步：因为多条数据，所以将"取出来的、要遍历的"字段装入JSONArray（这里要遍历data字段）
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            //第三步：循环遍历，依次取出JSONObject对象
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String time = jsonObject2.getString("time");
                String ftime = jsonObject2.getString("ftime");
                String context = jsonObject2.getString("context");
                sb.append("time: " + time+"  "+"ftime: " + ftime+"\n"+"context: " + context+"\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();*/
    }
}

