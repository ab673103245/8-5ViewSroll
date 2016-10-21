package qianfeng.a8_5viewsroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    private int x;
    private int y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = ((TextView) findViewById(R.id.tv));

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN: // 手指按下事件
                        x = (int)event.getX();
                        y = (int)event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE: // 手指在屏幕上移动的事件
                        //!!!仔细看注释啊！！
                        // 坐标系：安卓的坐标系只有一个，就是我们平时看到的，
                        // 画布绘制的顺序：
                        // 1.只不过这里是画布先移动到控件的左上角的位置，2.再在控件的左上角绘制文字，3.最后再把画布移动到原来的位置。

                        // event得到手指移动的坐标
                        // 想要向下移动，就必须要给相反值啊！！！
                        // 想要向上移动，也要给相反值(相对于平时的坐标)啊！！

                        // 但是只有这行代码，手指一按下去的时候，就马上画布进行了移动，控件的左上方就会是你手指的点击的地方，所以在这里必须处理好偏移量。
                        ((ViewGroup) tv.getParent()).scrollBy(-(int)event.getX() + x,-(int)event.getY() + y);
                        // 上面传过来的x，y肯定是正的值，我现在是想让控件向左上方移动，你说我要给什么值呢？正还是负呢？答案肯定是正啊！因为我想向上移动，原本是要给负的值，现在要相反，就要给正的啊！
                        break;
                }

                return true; // 返回true，表示事件被tv捕捉到并处理
            }
        });
    }

    public void scrollTo2(View view) {
        // 这个方法其实是移动画布，
        // 我想向右下方显示helloworld，应该x，y坐标都是负的
        // 我想向左上方显示helloworld，应该x，y坐标都是正的
        // 并且这个scrollTo方法，是只能移动一次，中文的意思是：移动到，坐标的值是写固定的，就是只能移动一次。
        tv.scrollTo(-100,-100);

        // 移动到左上方 tv.scrollTo(100,100);
    }
}
