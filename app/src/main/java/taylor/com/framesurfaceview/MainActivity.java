package taylor.com.framesurfaceview;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import rapid.decoder.BitmapDecoder;
import taylor.lib.framesurfaceview.NumberUtil;
import taylor.lib.framesurfaceview.FrameSurfaceView;
import taylor.lib.framesurfaceview.MethodUtil;

public class MainActivity extends AppCompatActivity  {

    private FrameSurfaceView frameSurfaceView;

    private List<Integer> normalBitmaps = Arrays.asList(
            R.drawable.watch_reward_1,
            R.drawable.watch_reward_2,
            R.drawable.watch_reward_3,
            R.drawable.watch_reward_4,
            R.drawable.watch_reward_5,
            R.drawable.watch_reward_6,
            R.drawable.watch_reward_7,
            R.drawable.watch_reward_8,
            R.drawable.watch_reward_9,
            R.drawable.watch_reward_10,
            R.drawable.watch_reward_11,
            R.drawable.watch_reward_12,
            R.drawable.watch_reward_13,
            R.drawable.watch_reward_14,
            R.drawable.watch_reward_15,
            R.drawable.watch_reward_16,
            R.drawable.watch_reward_17,
            R.drawable.watch_reward_18,
            R.drawable.watch_reward_19,
            R.drawable.watch_reward_20,
            R.drawable.watch_reward_21,
            R.drawable.watch_reward_22
    );
    private List<Integer> hugeBitmaps = Arrays.asList(
            R.raw.frame0,
            R.raw.frame1,
            R.raw.frame2,
            R.raw.frame3,
            R.raw.frame4,
            R.raw.frame5,
            R.raw.frame6,
            R.raw.frame7,
            R.raw.frame8,
            R.raw.frame9,
            R.raw.frame10,
            R.raw.frame11,
            R.raw.frame12,
            R.raw.frame13,
            R.raw.frame14,
            R.raw.frame15,
            R.raw.frame16,
            R.raw.frame17,
            R.raw.frame18,
            R.raw.frame19
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_decode_resource).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long span = MethodUtil.time(new Runnable() {
                    @Override
                    public void run() {
                        BitmapFactory.decodeResource(getResources(), R.drawable.frame4);
                    }
                });
                NumberUtil.average("decode resource", span);
            }
        });

        findViewById(R.id.btn_decode_stream).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long span = MethodUtil.time(new Runnable() {
                    @Override
                    public void run() {
                        InputStream inputStream = getResources().openRawResource(R.raw.frame4);
                        BitmapFactory.decodeStream(inputStream);
                    }
                });
                NumberUtil.average("decode stream", span);
            }
        });

        findViewById(R.id.btn_rapid_decode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long span = MethodUtil.time(new Runnable() {
                    @Override
                    public void run() {
                        InputStream inputStream = getResources().openRawResource(R.raw.frame4);
                        BitmapDecoder.from(inputStream).decode();
                    }
                });
                NumberUtil.average("rapid decode", span);
            }
        });

        findViewById(R.id.btn_decode_asset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long span = MethodUtil.time(new Runnable() {
                    @Override
                    public void run() {
                        InputStream inputStream = null;
                        try {
                            inputStream = getAssets().open("frame4.png");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        BitmapFactory.decodeStream(inputStream);
                    }
                });
                NumberUtil.average("assets decode", span);
            }
        });

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameSurfaceView.start();
            }
        });


        frameSurfaceView = findViewById(R.id.sv_frame);
        frameSurfaceView.setBitmapIds(hugeBitmaps);
        frameSurfaceView.setDuration(600);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        frameSurfaceView.destroy();
    }
}
