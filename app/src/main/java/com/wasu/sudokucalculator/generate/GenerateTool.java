package com.wasu.sudokucalculator.generate;

import android.util.SparseArray;

import com.wasu.sudokucalculator.model.CodeDataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GenerateTool {
    static SparseArray<CodeDataModel> array;

    public static SparseArray<CodeDataModel> getMap(){
        array = new SparseArray<>();
        boolean flag = true;
        while (flag) {
            try {
                start();
                flag = false;
            } catch (ArithmeticException e) {
               e.printStackTrace();
            }
        }
        return array;
    }

    /**
     * 开始生成数独
     */
    private static void start(){
        int[][] source = new int[9][9];
        //第i行
        for (int i=0; i<9; i++){
            // 第i行中的第j个数字
            for (int j=0; j<9; j++){
                //第i行目前的数组
                int[] row = Arrays.copyOf(source[i], j);
                int[] column = new int[i];
                for (int k=0; k<i; k++){
                    column[k] = source[k][j];
                }
                //所在宫
                List<Integer> palaceList = new ArrayList<>();
                //取整,获取宫所在数据
                int palaceRow = i/3;
                int palaceColumn = j/3;
                for (int m=0; m<3; m++){
                    for (int n=0; n<3; n++){
                        palaceList.add(source[palaceRow*3+m][palaceColumn*3+n]);
                    }
                }
                source[i][j] = getNumber(row, column, palaceList.stream().mapToInt(Integer::intValue).toArray());;
            }
        }

        //打印随机生成的数独数组
        for (int i=0; i<source.length; i++){
            for (int j=0; j<source[i].length; j++){
                array.put((i+1)*10+j+1, new CodeDataModel(i+1,j+1,source[i][j], true));
            }
//            System.out.println(Arrays.toString(source[i]));
        }
    }


    /**
     * 从即没有在行也没有在列中，选出一个随机数
     * @param row
     * @param column
     * @return
     */
    private static int getNumber(int[] row, int[] column, int[] palace ){
        //数组合并，并去重，使用Set集合
        Set<Integer> mergeSet = new HashSet<>();
        for (int i=0; i<row.length; i++){
            mergeSet.add(row[i]);
        }
        for (int j=0; j<column.length; j++){
            mergeSet.add(column[j]);
        }

        for (int k=0; k<palace.length; k++){
            mergeSet.add(palace[k]);
        }
        Set<Integer> source  = new HashSet<>();
        for (int m=1; m<10; m++){
            source.add(m);
        }
        //取差集
        source.removeAll(mergeSet);
        int[] merge = source.stream().mapToInt(Integer::intValue).toArray();
        //随机返回一个下标
        return merge[getRandomCursor(merge.length)];
    }

    /**
     * 获取一个随机下标
     * @param length
     * @return
     */
    private static int getRandomCursor(int length) {
        return Math.abs(new Random().nextInt())%length;
    }
}
