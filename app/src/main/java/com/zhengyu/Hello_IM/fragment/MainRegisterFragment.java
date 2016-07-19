package com.zhengyu.Hello_IM.fragment;

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

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.zhengyu.Hello_IM.R;


/**
 * Created by Administrator on 2016/5/3 0003.
 */
public class MainRegisterFragment extends Fragment {
    private View view;

    private TextView username;
    private TextView password;

    private Button button;

    private CheckBox cbf1;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_main_fragment1,container,false);
        init();
        return view;
    }

    private void init() {
        username= (TextView) view.findViewById(R.id.username_zhuce);
        password= (TextView) view.findViewById(R.id.password_zhuce);
        button= (Button) view.findViewById(R.id.registed_btn);
        cbf1= (CheckBox) view.findViewById(R.id.cb_f1);

        cbf1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EditText et= (EditText) view.findViewById(R.id.password_zhuce);
                if(isChecked==true){
                    et.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    et.setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                }
            }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname= username.getText().toString().trim();
                String pwd= password.getText().toString().trim();
                if(!uname.isEmpty()&&!pwd.isEmpty()){
                    //注册失败会抛出HyphenateException
                    try {
                        EMClient.getInstance().createAccount(uname, pwd);//同步方法
                        Toast.makeText(view.getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(view.getContext(),"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
