package com.wasu.sudokucalculator.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.wasu.sudokucalculator.R;
import com.wasu.sudokucalculator.Tools;
import com.wasu.sudokucalculator.generate.GenerateTool;
import com.wasu.sudokucalculator.model.CodeDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenerateDialog extends Dialog implements View.OnClickListener {
    private final String TAG = "GenerateDialog";

    private final int reduceCount = 15;

    @BindView(R.id.sub)
    Button mSub;

    @BindView(R.id.count)
    EditText editText;

    onClickListener listener;
    SparseArray<CodeDataModel> array;

    public GenerateDialog(@NonNull Context context, @NonNull onClickListener listener) {
        super(context, R.style.chooseDialog);
        this.listener = listener;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_generate, null);
        Window window = this.getWindow();
        window.setContentView(view);
        ButterKnife.bind(this);

        mSub.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sub:
                int count = reduceCount;

                try {
                    String s = editText.getText().toString();
                    count = Integer.parseInt(s);
                    if (count <=0 || count >=99){
                        Tools.quickToast(getContext(),"非法的数字");
                        count = reduceCount;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                // 获取到列表
                array =  GenerateTool.getMap();
                reduceCount(count);

                if (listener!= null){
                    listener.onClick(array);
                }

                // 隐藏
                cancel();
                break;
        }
    }

    private void reduceCount(int count){
        List<Integer> list = new ArrayList<>();

        for (;1==1;){
            int num=(int)(Math.random()*100);
            if (num>=1 && num <=99 && !list.contains(num)){
                list.add(num);
                if (list.size() == count){
                    break;
                }
            }
        }

        for (Integer i:list){
            array.remove(i);
        }
    }



    public interface onClickListener{
        void onClick(SparseArray<CodeDataModel> array);
    }
}
