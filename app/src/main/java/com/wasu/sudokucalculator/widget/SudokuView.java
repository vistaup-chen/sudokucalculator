package com.wasu.sudokucalculator.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.wasu.sudokucalculator.R;
import com.wasu.sudokucalculator.Tools;
import com.wasu.sudokucalculator.dialog.ChooseDialog;
import com.wasu.sudokucalculator.model.CodeDataModel;

import java.util.List;

public class SudokuView extends View {
    private final String TAG = "SudokuView";

    //单元格的宽度和高度
    private float width;
    private float height;

    // 文字画笔
    Paint  mBasePaint; // 默认文字
    Paint  mFillPaint; // 填充文字

    // 选中的位置
    int mSelectedX;
    int mSelectedY;

    // 数据表
    SparseArray<CodeDataModel> mCodeMap = new SparseArray<>();

    public SudokuView(Context context) {
        super(context);
        init();
    }

    public SudokuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        int paintWidth = 1;
        int textSize = 20;

        // 初始化默认文字画笔
        mBasePaint = new Paint();
        mBasePaint.setTextSize(Tools.sp2px(getContext(), textSize));
        mBasePaint.setTextAlign(Paint.Align.CENTER);//设置文字对齐方式
        mBasePaint.setColor(getContext().getResources().getColor(R.color.color_000000));
        mBasePaint.setStrokeWidth(paintWidth);//设置画笔宽度
        mBasePaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置绘图样式，对于设置文字和几何图形都有效，可取值有三种 ：1、Paint.Style.FILL：填充内部 2、Paint.Style.FILL_AND_STROKE：填充内部和描边 3、Paint.Style.STROKE：仅描边

        // 填充画笔
        mFillPaint = new Paint();
        mFillPaint.setTextSize(Tools.sp2px(getContext(), textSize));
        mFillPaint.setTextAlign(Paint.Align.CENTER);//设置文字对齐方式
        mFillPaint.setStrokeWidth(paintWidth);//设置画笔宽度
        mFillPaint.setColor(getContext().getResources().getColor(R.color.colorPrimary));
        mFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置绘图样式，对于设置文字和几何图形都有效，可取值有三种 ：1、Paint.Style.FILL：填充内部 2、Paint.Style.FILL_AND_STROKE：填充内部和描边 3、Paint.Style.STROKE：仅描边

//        addTestData2();
    }

    // 添加测试数据
    private void addTestData1(){
        mCodeMap.put(11,new CodeDataModel(1,1,5,true));
        mCodeMap.put(41,new CodeDataModel(4,1,7,true));
        mCodeMap.put(81,new CodeDataModel(8,1,3,true));
        mCodeMap.put(22,new CodeDataModel(2,2,9,true));
        mCodeMap.put(32,new CodeDataModel(3,2,8,true));
        mCodeMap.put(62,new CodeDataModel(6,2,2,true));
        mCodeMap.put(72,new CodeDataModel(7,2,4,true));
        mCodeMap.put(13,new CodeDataModel(1,3,2,true));
        mCodeMap.put(23,new CodeDataModel(2,3,7,true));
        mCodeMap.put(63,new CodeDataModel(6,3,8,true));
        mCodeMap.put(84,new CodeDataModel(8,4,8,true));
        mCodeMap.put(94,new CodeDataModel(9,4,6,true));
        mCodeMap.put(55,new CodeDataModel(5,5,7,true));
        mCodeMap.put(65,new CodeDataModel(6,5,4,true));
        mCodeMap.put(26,new CodeDataModel(2,6,5,true));
        mCodeMap.put(36,new CodeDataModel(3,6,7,true));
        mCodeMap.put(37,new CodeDataModel(3,7,6,true));
        mCodeMap.put(47,new CodeDataModel(4,7,5,true));
        mCodeMap.put(18,new CodeDataModel(1,8,3,true));
        mCodeMap.put(58,new CodeDataModel(5,8,6,true));
        mCodeMap.put(88,new CodeDataModel(8,8,2,true));
        mCodeMap.put(98,new CodeDataModel(9,8,7,true));
        mCodeMap.put(59,new CodeDataModel(5,9,8,true));
        mCodeMap.put(79,new CodeDataModel(7,9,1,true));
        mCodeMap.put(99,new CodeDataModel(9,9,5,true));
    }

    private void addTestData2(){
        mCodeMap.put(12,new CodeDataModel(1,2,6,true));
        mCodeMap.put(16,new CodeDataModel(1,6,9,true));
        mCodeMap.put(17,new CodeDataModel(1,7,7,true));
        mCodeMap.put(19,new CodeDataModel(1,9,4,true));
        mCodeMap.put(21,new CodeDataModel(2,1,3,true));
        mCodeMap.put(23,new CodeDataModel(2,3,5,true));
        mCodeMap.put(24,new CodeDataModel(2,4,4,true));
        mCodeMap.put(25,new CodeDataModel(2,5,8,true));
        mCodeMap.put(27,new CodeDataModel(2,7,1,true));
        mCodeMap.put(32,new CodeDataModel(3,2,2,true));
        mCodeMap.put(35,new CodeDataModel(3,5,7,true));
        mCodeMap.put(37,new CodeDataModel(3,7,8,true));
        mCodeMap.put(46,new CodeDataModel(4,6,7,true));
        mCodeMap.put(48,new CodeDataModel(4,8,1,true));
        mCodeMap.put(49,new CodeDataModel(4,9,5,true));
        mCodeMap.put(52,new CodeDataModel(5,2,4,true));
        mCodeMap.put(53,new CodeDataModel(5,3,3,true));
        mCodeMap.put(57,new CodeDataModel(5,7,2,true));
        mCodeMap.put(58,new CodeDataModel(5,8,6,true));
        mCodeMap.put(61,new CodeDataModel(6,1,7,true));
        mCodeMap.put(62,new CodeDataModel(6,2,1,true));
        mCodeMap.put(64,new CodeDataModel(6,4,3,true));
        mCodeMap.put(73,new CodeDataModel(7,3,9,true));
        mCodeMap.put(75,new CodeDataModel(7,5,3,true));
        mCodeMap.put(78,new CodeDataModel(7,8,8,true));
        mCodeMap.put(83,new CodeDataModel(8,3,6,true));
        mCodeMap.put(85,new CodeDataModel(8,5,5,true));
        mCodeMap.put(86,new CodeDataModel(8,6,2,true));
        mCodeMap.put(87,new CodeDataModel(8,7,4,true));
        mCodeMap.put(89,new CodeDataModel(8,9,3,true));
        mCodeMap.put(91,new CodeDataModel(9,1,4,true));
        mCodeMap.put(93,new CodeDataModel(9,3,7,true));
        mCodeMap.put(94,new CodeDataModel(9,4,6,true));
        mCodeMap.put(98,new CodeDataModel(9,8,9,true));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w>=h){
            // 横向
            width = h;
            height = h;
        }else {
            width = w;
            height = w;
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 单个格子的长度
        float cellWidth = width /9;
        float cellHeight = height /9;

        //生成用于绘制背景色的画笔
        Paint backgroundPaint = new Paint();
        //设置画笔的颜色
        backgroundPaint.setColor(getResources().getColor(R.color.color_ffffff));
        //绘制背景色
        canvas.drawRect(0,0,getWidth(),getHeight(),backgroundPaint);

        //深色画笔
        Paint drakPaint = new Paint();
        drakPaint.setColor(getResources().getColor(R.color.color_000000));

        //浅色画笔
        Paint lightPaint = new Paint();
        lightPaint.setColor(getResources().getColor(R.color.color_C4C4C4));

        // 线材的额外补宽
        int lineWidth = 0;

        //画出大的米字
        // 因为是方的，加一根底线
        for (int i=1; i<=3;i++){
            canvas.drawLine(0,height/3*i,width,height/3*i+lineWidth,drakPaint);
            canvas.drawLine(width/3*i,0,width/3*i+lineWidth,height,drakPaint);
        }

        // 画出内线
        for (int i=1;i<9;i++){
            if (i % 3 == 0){
                continue;
            }
            canvas.drawLine(0,cellHeight *i, width, cellHeight *i+lineWidth, lightPaint);
            canvas.drawLine(cellWidth *i,0, cellWidth *i+lineWidth, height, lightPaint);
        }

        // 选中的色块填充颜色
        if (mSelectedX>0 && mSelectedX<=9 && mSelectedY > 0 && mSelectedY <= 9) {
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);//设置填满
            p.setColor(getResources().getColor(R.color.color_C4C4C4));
            // 绘制选中的矩形
            float left = (mSelectedX-1)*cellWidth;
            float top = (mSelectedY-1)*cellHeight;
            canvas.drawRect(left, top, left+cellWidth, top+cellHeight, p);// 正方形
        }

        // 填入数字
        for (int i = 0; i < mCodeMap.size(); i++) {
            int key = mCodeMap.keyAt(i);
            CodeDataModel model = mCodeMap.get(key);
            drawText(canvas, model);
        }
    }

    /**
     * 绘制需要的文字
     */
    public void drawText(Canvas canvas, CodeDataModel model) {
        if (model == null || canvas == null) {
            return;
        }

        Paint paint = model.isBase ? mBasePaint: mFillPaint;

        float x = width / 18 + ((model.x -1) * width/9);
        // 补充 Y 线的偏移量
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float y = height / 18 - (fontMetrics.ascent + fontMetrics.descent) / 2  + ((model.y -1) * height/9);

        canvas.drawText(model.filledCode+"", x, y, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //判断用户点击的是哪一个单元格
            mSelectedX= (int)(event.getX() / (width/9) +1) ;
            mSelectedY = (int)(event.getY() / (height/9) + 1);

            if (mSelectedX <=9 && mSelectedY <= 9){
//                Log.d(TAG, "点击了 = "+mSelectedX+","+mSelectedY);
                /**
                 * 计算 / 生成出来的数字为base，不允许点击
                 * */
                if (mCodeMap.get(mSelectedX*10+mSelectedY) != null){
                    CodeDataModel model = mCodeMap.get(mSelectedX*10+mSelectedY);
                    if (model.isBase){
                        Tools.quickToast(getContext(),"基础的数字不能修改");
                        return super.onTouchEvent(event);
                    }
                }

                this.invalidate();
                showChooseDialog();
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 展示选择dialog
     * */
    private void showChooseDialog(){
        ChooseDialog dialog = new ChooseDialog(getContext());
        dialog.initData(mSelectedX,mSelectedY,mCodeMap);
        dialog.setChooseListener(new ChooseDialog.onChooseListener() {
            @Override
            public void onChoose(int i) {
                if (i == 0){
                    mCodeMap.remove(mSelectedX*10+mSelectedY);
                }else {
                    mCodeMap.put(mSelectedX * 10 + mSelectedY, new CodeDataModel(mSelectedX, mSelectedY, i, false));
                }
                mSelectedX = 0;
                mSelectedY = 0;
                invalidate();
            }
        });
        dialog.show();
    }

    /**
     * 计算单元格可行解
     * *******************************************************************************************/
    public void calculatorData(){
        if (!fillCell()){
            // 直接填没填完
            Log.d(TAG, "没填完，开始猜格子");
            if (!guessOneCell()){
                // 猜1个数也没填满
                Log.d(TAG, "1个格子也没猜到");
                if (!guessTwoCell()){
                    guessThreeCell();
                }
            }
        }

        // 一次填完
        dealDone();
    }

    private void dealDone(){
        Toast.makeText(getContext(),"计算结束",Toast.LENGTH_SHORT).show();
    }

    // 测试猜1个数
    private boolean guessOneCell(){
        // 计算完成第一次，但是没有填充完成
        // 找出第一个单元格为空时可以填的数
        int x = 1;
        int y = 0;

        // 缓存数据
        SparseArray<CodeDataModel> tmpCodeMap = new SparseArray<>();
        for (int i = 0; i < mCodeMap.size(); i++) {
            int key = mCodeMap.keyAt(i);
            CodeDataModel model = mCodeMap.get(key);
            tmpCodeMap.put(key, model);
        }

        while (true) {
            // 无限循环往下找
            int cell = findFirstEmptyCell(x, y+1);
            if (cell != -1) {
                x = cell / 10;
                y = cell % 10;

                List<Integer> list = Tools.getCalcualtor(x, y, mCodeMap, true);
                // 进行一次的猜数
                for (Integer i : list) {
                    if (i==0){
                        continue;
                    }
                    Log.d(TAG, "当前测试第 "+x+","+y+" 格子, 填入数据 = "+i);
                    mCodeMap.put(cell , new CodeDataModel(x,  y, i, true));
                    if (fillCell()) {
                        // 已经填满
                        return true;
                    }
                }
                // 到这里说明猜第一个格子没猜到数字，只能猜第二个格子，还原表格
                mCodeMap = new SparseArray<>();
                for (int i = 0; i < tmpCodeMap.size(); i++) {
                    int key = tmpCodeMap.keyAt(i);
                    CodeDataModel model = tmpCodeMap.get(key);
                    mCodeMap.put(key, model);
                }
            }else {
                // cell = -1 往后不存在没填满的格子
                return false;
            }
        }
    }

    // 测试连猜两个单元格
    private boolean guessTwoCell(){
        // 猜一个单元格都没猜到，只能两个格子了
        int x = 1;
        int y = 0;

        // 缓存数据
        SparseArray<CodeDataModel> tmpCodeMap = new SparseArray<>();
        for (int i = 0; i < mCodeMap.size(); i++) {
            int key = mCodeMap.keyAt(i);
            CodeDataModel model = mCodeMap.get(key);
            tmpCodeMap.put(key, model);
        }

        while (true){
            // 无限循环往下找
            int cell1 = findFirstEmptyCell(x, y+1);
            int cell2 = findFirstEmptyCell(cell1/10, cell1%10+1);

            List<Integer> list1 = Tools.getCalcualtor(cell1/10, cell1%10, mCodeMap, true);
            List<Integer> list2 = Tools.getCalcualtor(cell2/10, cell2%10, mCodeMap, true);

            Log.d(TAG, "获取到格子："+cell1+" , "+cell2);

            if (cell1 != -1 && cell2 != -1) {
                // 记录第一个格子的位置
                x = cell1 / 10;
                y = cell1 % 10;

                for (int i = 0; i < list1.size(); i++) {
                    if (list1.get(i) == 0) {
                        continue;
                    }
                    for (int j = 0; j < list2.size(); j++) {
                        if (list2.get(j) == 0) {
                            continue;
                        }

                        // 开始连猜两个数
                        Log.d(TAG, "当前测试第 " + cell1 + "," + cell2 + " 格子, 填入数据 = " + list1.get(i) + " , " + list2.get(j));
                        mCodeMap.put(cell1, new CodeDataModel(cell1 / 10, cell1 % 10, list1.get(i), true));
                        mCodeMap.put(cell2, new CodeDataModel(cell2 / 10, cell2 % 10, list2.get(j), true));
                        if (fillCell()) {
                            // 已经填满
                            return true;
                        }
                    }
                }

                // 到这里说明猜第一个格子没猜到数字，只能猜第二个格子，还原表格
                mCodeMap = new SparseArray<>();
                for (int k = 0; k < tmpCodeMap.size(); k++) {
                    int key = tmpCodeMap.keyAt(k);
                    CodeDataModel model = tmpCodeMap.get(key);
                    mCodeMap.put(key, model);
                }
            }else {
                return false;
            }
        }
    }

    // 测试连猜3个单元格
    private boolean guessThreeCell(){
        // 猜一个单元格都没猜到，只能两个格子了
        int x = 1;
        int y = 0;

        // 缓存数据
        SparseArray<CodeDataModel> tmpCodeMap = new SparseArray<>();
        for (int i = 0; i < mCodeMap.size(); i++) {
            int key = mCodeMap.keyAt(i);
            CodeDataModel model = mCodeMap.get(key);
            tmpCodeMap.put(key, model);
        }

        while (true){
            // 无限循环往下找
            int cell1 = findFirstEmptyCell(x, y+1);
            int cell2 = findFirstEmptyCell(cell1/10, cell1%10+1);
            int cell3 = findFirstEmptyCell(cell2/10, cell2%10+1);

            List<Integer> list1 = Tools.getCalcualtor(cell1/10, cell1%10, mCodeMap, true);
            List<Integer> list2 = Tools.getCalcualtor(cell2/10, cell2%10, mCodeMap, true);
            List<Integer> list3 = Tools.getCalcualtor(cell3/10, cell3%10, mCodeMap, true);

            Log.d(TAG, "获取到格子："+cell1+" , "+cell2+" , "+cell3);

            if (cell1 != -1 && cell2 != -1 && cell3 != -1) {
                // 记录第一个格子的位置
                x = cell1 / 10;
                y = cell1 % 10;

                for (int i = 0; i < list1.size(); i++) {
                    if (list1.get(i) == 0) {
                        continue;
                    }
                    for (int j = 0; j < list2.size(); j++) {
                        if (list2.get(j) == 0) {
                            continue;
                        }

                        for (int m = 0; m < list3.size(); m++){
                            if (list3.get(m) == 0) {
                                continue;
                            }

                            // 开始连猜两个数
                            Log.d(TAG, "当前测试第 " + cell1 + "," + cell2 + "," +cell3 + " 格子, 填入数据 = " + list1.get(i) + " , " + list2.get(j) + " , " + list3.get(m));
                            mCodeMap.put(cell1, new CodeDataModel(cell1 / 10, cell1 % 10, list1.get(i), true));
                            mCodeMap.put(cell2, new CodeDataModel(cell2 / 10, cell2 % 10, list2.get(j), true));
                            mCodeMap.put(cell3, new CodeDataModel(cell3 / 10, cell3 % 10, list3.get(m), true));
                            if (fillCell()) {
                                // 已经填满
                                return true;
                            }

                        }
                    }
                }

                // 到这里说明猜第一个格子没猜到数字，只能猜第二个格子，还原表格
                mCodeMap = new SparseArray<>();
                for (int k = 0; k < tmpCodeMap.size(); k++) {
                    int key = tmpCodeMap.keyAt(k);
                    CodeDataModel model = tmpCodeMap.get(key);
                    mCodeMap.put(key, model);
                }
            }else {
                return false;
            }
        }
    }

    // 测试计算单元格
    private boolean fillCell(){
        int calculatorTime = 0;

        for (int i=1; i<=9; i++){
            for (int j=1; j <= 9; j++){
                if (isCellEmpty(i,j)){
                    // 当前单元格是空的
                    List<Integer> list = Tools.getCalcualtor(i,j,mCodeMap, true);
                    // 等于2的原因是因为有个0
                    if (list.size() == 2){
                        mCodeMap.put(i*10+j, new CodeDataModel(i,j,list.get(1),true));
//                        Log.d(TAG, "计算出单元格 "+i+" , "+j+" 解为 "+list.get(1));
                        // 计算出可行解之后，直接从头计算
                        i=1;
                        // j=1时，1，1没有被计算。直接进入1，2,因为j++
                        j=0;
                    }
                }

                // 免的跑死
                calculatorTime = calculatorTime + 1;
                if (calculatorTime > 1000000){
                    Toast.makeText(getContext(),"计算超过"+calculatorTime+"次，强制退出",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        invalidate();
        if (isAllFilled()){
            return true;
        }else {
            return false;
        }
    }


    // 设置新数据
    public void setCodeMap(SparseArray<CodeDataModel> mCodeMap) {
        this.mCodeMap = mCodeMap;
    }

    // 当前单元格是否为空
    private boolean isCellEmpty(int i, int j){
        return mCodeMap.get(i*10+j) == null;
    }

    /**
     * 横向顺序查找第一个没有填满的格子
     **/
    private int findFirstEmptyCell(int k, int m) {
        int j;
        for (int i = k; i <= 9; i++) {
            if (i == k){
                j = m;
            }else {
                j=1;
            }
            for (; j <= 9; j++) {
                if (mCodeMap.get(i * 10 + j) == null) {
                    return i * 10 + j;
                }
            }
        }

        return -1;
    }

    // 是否已经全部填满
    private boolean isAllFilled(){
        return mCodeMap.size() == 81;
    }
}
