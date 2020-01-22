package com.wasu.sudokucalculator;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wasu.sudokucalculator.dialog.GenerateDialog;
import com.wasu.sudokucalculator.model.CodeDataModel;
import com.wasu.sudokucalculator.widget.SudokuView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.sudokuView)
    SudokuView sudokuView;

    @BindView(R.id.calcalator)
    Button mCalculator;

    @BindView(R.id.generate)
    Button mGenerate;

    @BindView(R.id.clear)
    Button mClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCalculator.setOnClickListener(this::onClick);
        mGenerate.setOnClickListener(this::onClick);
        mClear.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.calcalator:
                sudokuView.calculatorData();
                break;
            case R.id.generate:
                GenerateDialog dialog = new GenerateDialog(this, new GenerateDialog.onClickListener() {
                    @Override
                    public void onClick(SparseArray<CodeDataModel> array) {
                        sudokuView.setCodeMap(array);
                        sudokuView.invalidate();
                    }
                });
                dialog.show();
                break;
            case R.id.clear:
                SparseArray<CodeDataModel> array = new SparseArray<>();
                sudokuView.setCodeMap(array);
                sudokuView.invalidate();
                break;
        }
    }
}
