package com.example.androidutils.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.androidutils.R;
import com.example.androidutils.base.BaseActivity;
import com.zxm.utils.core.dialog.DialogUtil;
import com.zxm.utils.core.permission.PermissionChecker;

/**
 * Created by ZhangXinmin on 2019/1/4.
 * Copyright (c) 2018 . All rights reserved.
 * 权限测试
 */
public class PermissionActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_EXTERNAL = 1001;
    private static final int REQUEST_PERMISSIONS = 1002;

    private String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private Context mContext;

    @Override
    protected Object setLayout() {
        return R.layout.activity_permission;
    }

    @Override
    protected void initParamsAndValues() {

        mContext = this;
        initActionBar();
    }

    @Override
    protected void initViews() {
        findViewById(R.id.btn_single).setOnClickListener(this);
        findViewById(R.id.btn_multiple).setOnClickListener(this);
    }

    /**
     * check permission
     */
    private void checkSinglePermission() {
        if (!PermissionChecker.checkPersmission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PermissionChecker.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_single:
                checkSinglePermission();
                break;
            case R.id.btn_multiple:
                checkPermissions();
                break;
        }
    }

    private void checkPermissions() {
        if (!PermissionChecker.checkSeriesPermissions(mContext, permissions)) {//存在未被允许的权限
            PermissionChecker.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults != null) {
            final int size = grantResults.length;
            for (int i = 0; i < size; i++) {
                final int grantResult = grantResults[i];
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    final boolean showRequest =
                            ActivityCompat.shouldShowRequestPermissionRationale(
                                    PermissionActivity.this, permissions[i]);
                    if (showRequest) {
                        DialogUtil.showForceDialog(mContext,
                                PermissionChecker.matchRequestPermissionRationale(mContext, permissions[i]),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                    }
                } else {
                    //do something
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
