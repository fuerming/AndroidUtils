package com.example.androidutils.activity;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidutils.R;
import com.example.androidutils.base.BaseActivity;
import com.zxm.utils.core.text.ClickableMovementMethod;
import com.zxm.utils.core.text.SpanUtils;

/**
 * Created by Administrator on 2018/1/16.
 * Copyright (c) 2018 . All rights reserved.
 * <p>
 * Display how to use {@link SpanUtils} to
 * create series of styles {@link android.widget.TextView}.
 * </p>
 */

public class SpanActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected Object setLayout() {
        return R.layout.activity_span;
    }

    @Override
    protected void initParamsAndValues() {
        mContext = this;

        initActionBar();
    }

    @Override
    protected void initViews() {
        //引线
        TextView quoteTv = findViewById(R.id.tv_quote);

        SpannableStringBuilder quoteBuilder =
                SpanUtils.getBuilder(mContext, "这是引线示例！", true)
                        .setQuoteColor(Color.BLUE)
                        .create();
        quoteTv.setText(quoteBuilder);

        //列表标记
        TextView bulletTv = findViewById(R.id.tv_bullet);

        SpannableStringBuilder bulletBuilder =
                SpanUtils.getBuilder(mContext, "这是列表示例！", true)
                        .setBullet(10, Color.RED)
                        .create();
        bulletTv.setText(bulletBuilder);

        //删除线和下划线
        TextView lineTv = findViewById(R.id.tv_line);

        SpannableStringBuilder lineBuilder =
                SpanUtils.getBuilder(mContext, "这是删除线和下划线！", true)
                        .setBullet(10, Color.RED)
                        .setUnderline()
                        .setStrikethrough()
                        .create();
        lineTv.setText(lineBuilder);

        //上标
        TextView scriptTv = findViewById(R.id.tv_script);

        SpannableStringBuilder scriptBuilder =
                SpanUtils.getBuilder(mContext, "这是上标", false)
                        .setSuperscript()
                        .append("th", true)
                        .create();
        scriptTv.setText(scriptBuilder);

        //粗体和斜体
        TextView boldTv = findViewById(R.id.tv_bold);

        SpannableStringBuilder boldBuilder =
                SpanUtils.getBuilder(mContext, "这是粗体和斜体", true)
                        .setBold()
                        .setBullet(10, Color.RED)
                        .setItalic()
                        .create();
        boldTv.setText(boldBuilder);

        //点击
        TextView clickedTv = findViewById(R.id.tv_click);
        SpannableStringBuilder clickBuilder =
                SpanUtils.getBuilder(mContext, "这是点击的情况:", true)
                        .setBullet(10, Color.RED)
                        .setBackgroundColor(Color.parseColor("#ededed"))
                        .append("追加第一个点击,", true)
                        .setClickSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View widget) {
                                Toast.makeText(mContext, "追加第一个点击！", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                ds.setColor(mContext.getResources().getColor(R.color.clickspan_color));
                                ds.setUnderlineText(true);
                            }
                        })
                        .setBackgroundColor(mContext.getResources().getColor(R.color.clickspan_color))
                        .append("追加第二个点击", true)
                        .setClickSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View widget) {
                                Toast.makeText(mContext, "追加第二个点击！", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                ds.setColor(mContext.getResources().getColor(R.color.colorAccent));
                                ds.setUnderlineText(true);
                            }
                        })
                        .create();

        clickedTv.setText(clickBuilder);
        clickedTv.setMovementMethod(ClickableMovementMethod.getInstance());
        clickedTv.setClickable(false);
        clickedTv.setHighlightColor(Color.TRANSPARENT);

        //中间设置样式
        TextView complexTv = findViewById(R.id.tv_complex);
        SpannableStringBuilder complexBuilder =
                SpanUtils.getBuilder(mContext, "此示例展示复杂的情况：", false)
                        .setFlag(Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        .setBackgroundColor(Color.parseColor("#ededed"))
                        .setTextColor(Color.RED)
                        .setBold()
                        .setBullet(10, Color.RED)
                        .setStrikethrough()
                        .setUnderline()
                        .append("中间文字有样式,", true)
                        .append("后面文字没有样式！", false)
                        .create();

        complexTv.setText(complexBuilder);

        //富文本应用示例
        TextView exampleTv = findViewById(R.id.tv_example);
        SpannableStringBuilder exampleBuilder = SpanUtils
                .showBeautfulText(mContext, "富文本应用示例：看过来，看过来，看过来，看过来，看过来！", "过来");
        exampleTv.setText(exampleBuilder);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
