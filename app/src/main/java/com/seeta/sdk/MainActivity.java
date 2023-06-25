package com.seeta.sdk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.seeta.sdk.databinding.ActivityMainBinding;

import java.io.File;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        File cacheDir = getInternalCacheDirectory(this, null);
        String modelPath = cacheDir.getAbsolutePath();
        Log.d("cacheDir", "" + modelPath);

        String fdModel = "face_detector.csta";
        String pdModel = "face_landmarker_pts5.csta";
        String frModel = "face_recognizer.csta";

        if (!isExists(modelPath, fdModel)) {
            File fdFile = new File(cacheDir + "/" + fdModel);
            FileUtils.copyFromAsset(this, fdModel, fdFile, false);
        }
        if (!isExists(modelPath, pdModel)) {
            File pdFile = new File(cacheDir + "/" + pdModel);
            FileUtils.copyFromAsset(this, pdModel, pdFile, false);
        }
        if (!isExists(modelPath, frModel)) {
            File frFile = new File(cacheDir + "/" + frModel);
            FileUtils.copyFromAsset(this, frModel, frFile, false);
        }

        String rootPath = cacheDir + "/";

        try {
            FaceDetector faceDetector = new FaceDetector(new SeetaModelSetting(
                    0,
                    new String[]{rootPath + fdModel},
                    SeetaDevice.SEETA_DEVICE_AUTO
            ));

            new FaceRecognizer(new SeetaModelSetting(
                    0,
                    new String[]{rootPath + frModel},
                    SeetaDevice.SEETA_DEVICE_AUTO
            ));

            new FaceLandmarker(new SeetaModelSetting(
                    0,
                    new String[]{rootPath + pdModel},
                    SeetaDevice.SEETA_DEVICE_AUTO
            ));

            QualityOfPose qualityOfPose = new QualityOfPose();

            QualityOfBrightness qualityOfBirghtness = new QualityOfBrightness();
            QualityOfClarity qualityOfClarity = new QualityOfClarity();
            QualityOfIntegrity qualityOfIntegrity = new QualityOfIntegrity();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File getInternalCacheDirectory(Context context, String type) {
        File appCacheDir;
        if (TextUtils.isEmpty(type)) {
            appCacheDir = context.getCacheDir(); // /data/data/app_package_name/cache
        } else {
            appCacheDir = new File(context.getFilesDir(), type); // /data/data/app_package_name/files/type
        }

        if (!appCacheDir.exists() && !appCacheDir.mkdirs()) {
            Log.e(
                    "getInternalDirectory",
                    "getInternalDirectory fail ,the reason is make directory fail !"
            );
        }
        return appCacheDir;
    }

    private boolean isExists(String path, String modelName) {
        if (path == null) return false;
        File file = new File(path + "/" + modelName);
        return file.exists();
    }
}