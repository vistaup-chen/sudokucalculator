package com.wasu.sudokucalculator;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.TypedValue;
import android.widget.Toast;

import com.wasu.sudokucalculator.model.CodeDataModel;

import java.util.ArrayList;
import java.util.List;

public class Tools {
    private final static String TAG = "Tools";

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static void quickToast(Context context, String s){
        if (context == null || TextUtils.isEmpty(s)){
            return;
        }
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }


    /**
     * 计算在当前格子中有哪些数字可以填写
     * @param isreverse true：找出的是可以填写的数
     * */
    public static List<Integer> getCalcualtor(int x, int y , SparseArray<CodeDataModel> map, boolean isreverse) {
        // 计算的是包含列表
        List<CodeDataModel> containList = new ArrayList<>();

        // 先计算在 9 * 9 的哪个格子
        int cellX = x/3 + (x % 3 == 0? 0:1);
        int cellY = y/3 + (y % 3 == 0? 0:1);
//        Log.d(TAG,"在大格子 X= "+cellX+" , Y = "+cellY);

        // 找出需要的model列表
        for (int i = 0; i < map.size(); i++) {
            CodeDataModel model = map.valueAt(i);
            // 左边界 3*(cellX-1)+1
            // 上边界 3*(cellY-1)+1
            // 右边界 3*cellX
            // 下边界 3*cellY

            if (model == null) {
                continue;
            }

            if (model.x == x || model.y == y ||
                    (model.x >= (3 * (cellX - 1) + 1) && model.x <= 3 * cellX && model.y >= 3 * (cellY - 1) + 1 && model.y <= 3 * cellY)) {
                containList.add(model);
            }
        }

        // 找出需要的格子之后，计算出反序（不存在的数字列表）
        if (isreverse) {
            return getNonExistentList(containList);
        }else {
            List<Integer> list = new ArrayList<>();
            for (CodeDataModel model:containList){
                if (!list.contains(model.filledCode)){
                    list.add(model.filledCode);
                }
            }
            return list;
        }
    }

    // 计算出已有列表下不存在的数字列表
    public static List<Integer> getNonExistentList(List<CodeDataModel> map){
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<=9;i++){
            list.add(i);
        }

        if (map != null && map.size()>0){
            for (CodeDataModel model:map){
                if (list.contains(model.filledCode)){
                    // 查找正确下标
                    for (int i=0; i<list.size();i++){
                        if (list.get(i) == model.filledCode){
                            // 移除指定
                            list.remove(i);
                            break;
                        }
                    }
                }
            }
        }

        return list;
    }

}
