package com.wasu.sudokucalculator.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.wasu.sudokucalculator.R;
import com.wasu.sudokucalculator.Tools;
import com.wasu.sudokucalculator.model.CodeDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseDialog extends Dialog implements View.OnClickListener {
    private final String TAG = "ChooseDialog";

    @BindView(R.id.button0)
    Button mBtn0;
    @BindView(R.id.button1)
    Button mBtn1;
    @BindView(R.id.button2)
    Button mBtn2;
    @BindView(R.id.button3)
    Button mBtn3;
    @BindView(R.id.button4)
    Button mBtn4;
    @BindView(R.id.button5)
    Button mBtn5;
    @BindView(R.id.button6)
    Button mBtn6;
    @BindView(R.id.button7)
    Button mBtn7;
    @BindView(R.id.button8)
    Button mBtn8;
    @BindView(R.id.button9)
    Button mBtn9;

    onChooseListener listener;

    public ChooseDialog(@NonNull Context context) {
        super(context, R.style.chooseDialog);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_choose, null);
        Window window = this.getWindow();
        window.setContentView(view);
        ButterKnife.bind(this);

        mBtn0.setOnClickListener(this);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
        mBtn5.setOnClickListener(this);
        mBtn6.setOnClickListener(this);
        mBtn7.setOnClickListener(this);
        mBtn8.setOnClickListener(this);
        mBtn9.setOnClickListener(this);
    }

    public void initData(int x, int y, SparseArray<CodeDataModel> map) {
        // 获取的是已经存在的数字列表
        List<Integer> list = Tools.getCalcualtor(x, y, map, false);
//        Log.d(TAG, "已经存在的数字:"+ JSON.toJSONString(list));

        for (Integer code: list){
            switch (code){
                case 1:
                    mBtn1.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    mBtn2.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    mBtn3.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    mBtn4.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    mBtn5.setVisibility(View.INVISIBLE);
                    break;
                case 6:
                    mBtn6.setVisibility(View.INVISIBLE);
                    break;
                case 7:
                    mBtn7.setVisibility(View.INVISIBLE);
                    break;
                case 8:
                    mBtn8.setVisibility(View.INVISIBLE);
                    break;
                case 9:
                    mBtn9.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                dealChoose(1);
                break;
            case R.id.button2:
                dealChoose(2);
                break;
            case R.id.button3:
                dealChoose(3);
                break;
            case R.id.button4:
                dealChoose(4);
                break;
            case R.id.button5:
                dealChoose(5);
                break;
            case R.id.button6:
                dealChoose(6);
                break;
            case R.id.button7:
                dealChoose(7);
                break;
            case R.id.button8:
                dealChoose(8);
                break;
            case R.id.button9:
                dealChoose(9);
                break;
            default:
                dealChoose(0);
                break;
        }
    }

    private void dealChoose(int i){
        if (listener != null){
            listener.onChoose(i);
        }
        this.cancel();
    }

    public void setChooseListener(onChooseListener listener) {
        this.listener = listener;
    }

    public interface onChooseListener{
        void onChoose(int i);
    }
}
