package com.example.kut003.a007app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class PaintView extends View {
    // 履歴
    private List<DrawLine> lines;
    // 現在、描いている線の情報
    private Paint paint;
    private Path path;

    // 線の履歴(座標＋色)
    class DrawLine {
        private Paint paint;
        private Path path;

        DrawLine(Path path, Paint paint) {
            this.paint = new Paint(paint);
            this.path = new Path(path);
        }

        void draw(Canvas canvas) {
            canvas.drawPath(this.path, this.paint);
        }
    }

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.path = new Path();

        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(40);

        this.lines = new ArrayList<DrawLine>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // キャンバスをクリア
        canvas.drawColor(Color.WHITE);
        // 履歴から線を描画
        for(DrawLine line : this.lines) {
            line.draw(canvas);
        }
        // 現在、描いている線を描画
        canvas.drawPath(this.path, this.paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                this.path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                this.path.lineTo(x, y);
                // 指を離したので、履歴に追加する
                this.lines.add(new DrawLine(this.path, this.paint));
                // パスをリセットする
                // これを忘れると、全ての線の色が変わってしまう
                this.path.reset();
                break;
        }
        invalidate();
        return true;
    }

    public void delete() {
        // 履歴をクリア
        this.lines.clear();
        // 現在の線をクリア
        this.path.reset();
        invalidate();
    }

    public void setPen(int color){
        this.paint.setColor(color);
    }
}