package com.lwapp.luowang.test_glide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Glide的使用
 */
public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private ImageView mGifView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mGifView= (ImageView) findViewById(R.id.iv_gif);

        Glide.with(this)//绑定Context的周期
                .load("http://pic51.nipic.com/file/20141022/19779658_171157758000_2.jpg")
                .listener(new RequestListener<String, GlideDrawable>() {//加载监听
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Toast.makeText(MainActivity.this, e.getMessage() + "," + model, Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .placeholder(R.mipmap.ic_launcher)//加载中的图片
                .error(R.mipmap.ic_launcher)//加载失败的图片
//                .thumbnail(0.01f)//先显示缩略图，然后再显示原图
                .override(800, 800)//调整在缓存中的图片大小
                .centerCrop()//裁剪，还有一个fitCrop,也可以进行自定义（比如圆形的图片）
                .into(mImageView);

        Glide.with(this)
//                .load("http://s1.dwstatic.com/group1/M00/FD/1C/8b19378d1f274d99a208a14f5194a5da.gif")//少儿不宜的动图。。。
                .load("http://s1.dwstatic.com/group1/M00/C3/38/d5eb5ebdb686854d17175fb1f1f5043b.gif")
                .asGif()
                .override(800,800)
                .placeholder(R.mipmap.ic_launcher)//加载中的图片
                .error(R.mipmap.ic_launcher)//加载失败的图片
                .centerCrop()
                .into(mGifView);
    }
}
