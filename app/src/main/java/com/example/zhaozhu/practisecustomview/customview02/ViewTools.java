package com.example.zhaozhu.practisecustomview.customview02;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by zhaozhu on 16/8/24.
 * wing的博客
 * 关于Android自定义view 你所需要知道的基本函数
 * http://blog.csdn.net/wingichoy/article/details/50487009
 *
 * 考虑如何改成博客中出现的错误：1，overdraw 2，在onDraw中 new对象
 */
public class ViewTools {

    void main() {
        Paint p = new Paint();
        //设置画笔的颜色
        p.setColor(Color.parseColor("#2EA4F2"));
        //设置画笔的风格：全部填充FILL   只画轮廓STROKE
        p.setStyle(Paint.Style.STROKE);
        //设置画笔的宽度
        p.setStrokeWidth(8);
        //设置是否抗锯齿
        p.setAntiAlias(true);

        //设置文字大小
        p.setTextSize(30);
        //测量字符串的长度
        float size = p.measureText("Hello World");

        //当我们有了画笔后，就可以绘制基本图形。
        //绘制一条从0,0到100,100的线
        Canvas canvas = new Canvas();
        canvas.drawLine(0, 0, 100, 100, p);

        //三角形&多边形
        //是用Path类实现的。Path类提供了点绘制线的功能，看例子
        Path path = new Path();
        path.moveTo(0, 0);//给定path的起点
        path.lineTo(10, 10);//从0,0往10，10绘制一条路径
        path.lineTo(5, 3);//继续从10，10往5,3绘制一条路径
        path.close();//将绘制的线形成封闭空间

        //用canvas画出刚刚的path
        canvas.drawPath(path, p);

        //矩形：
        //画一个矩形，左上角的坐标为0,0   右下角的坐标为100，50
        canvas.drawRect(0, 0, 100, 50, p);

        //圆角矩形：
        //方法1
        canvas.drawRoundRect(0, 0, 200, 150, 20, 15, p);
        //方法2
        //一个矩形
        RectF rectF = new RectF(0, 0, 200, 150);
        canvas.drawRoundRect(rectF, 20, 15, p);

        //圆形
        //画一个圆，圆心为50，50  半径为100
        canvas.drawCircle(50, 50, 100, p);

        //弧形
        //注意这里第二个参数，是从三点钟方向为0°计算，所以想从12点中方向开始绘制，那么就是270°。第四个参数是决定是否经过圆心（自己改变一下这个参数就知道区别了）。
        //画一个弧，弧所在矩形为rectF  从270°开始，画90° 不经过圆心
        canvas.drawArc(rectF, 270, 90, false, p);

    }

}
