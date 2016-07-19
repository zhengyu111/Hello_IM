package com.zhengyu.Hello_IM.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.zhengyu.Hello_IM.R;
import com.zhengyu.Hello_IM.ui.HomeActivity;


/**
 * Created by Administrator on 2016/5/3 0003.
 */
public class MainLoginFragment extends Fragment {
    private View view;
    private CheckBox cb2;
    private Button btn;
    private TextView username;
    private TextView password;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_main_fragment2,container,false);
        init();
        return view;
    }

    private void init() {
        username= (TextView) view.findViewById(R.id.username_login);
        password= (TextView) view.findViewById(R.id.password_login);
        cb2= (CheckBox) view.findViewById(R.id.cb_f2);
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EditText et= (EditText) view.findViewById(R.id.password_login);
                if(isChecked==true){
                    et.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    et.setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                }
            }
        });
        btn= (Button) view.findViewById(R.id.login_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname= username.getText().toString();
                String pwd= password.getText().toString();
                if(!uname.isEmpty()&&!pwd.isEmpty()){
                    //注册失败会抛出HyphenateException
                    EMClient.getInstance().login(uname,pwd,new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            EMClient.getInstance().groupManager().loadAllGroups();
                            EMClient.getInstance().chatManager().loadAllConversations();
                            startActivity(new Intent(view.getContext(), HomeActivity.class));
                        }

                        @Override
                        public void onError(int i, String s) {

                        }

                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });
                }else{
                    Toast.makeText(view.getContext(),"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
