package com.seeta.sdk;


//import com.seeta.SeetaModelSetting;


public class FaceRecognizer {

    static {
        System.loadLibrary("SeetaFaceRecognizer610_java");
    }


    public enum Property {
        PROPERTY_NUMBER_THREADS(4),
        PROPERTY_ARM_CPU_MODE(5);

        private int value;
        private Property(int value) {
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public long impl = 0;

    private native void construct(SeetaModelSetting setting);
    private native void construct(String model, String device, int id);

    public FaceRecognizer(SeetaModelSetting setting) {
        this.construct(setting);
    }

    public FaceRecognizer(String model, String device, int id) {
        this.construct(model, device, id);
    }

    public native void dispose();
    protected void finalize()throws Throwable{
        super.finalize();
        this.dispose();
    }

    //public static native int SetLogLevel(int level);
    //public static native void SetSingleCalculationThreads(int num);

    public native int GetCropFaceWidthV2();
    public native int GetCropFaceHeightV2();
    public native int GetCropFaceChannelsV2();

    public native int GetExtractFeatureSize();

    public native boolean CropFaceV2(SeetaImageData image, SeetaPointF[] points, SeetaImageData face);

    public native boolean ExtractCroppedFace(SeetaImageData face, float[] features);

    public native boolean Extract(SeetaImageData image, SeetaPointF[] points, float[] features);

    public native float CalculateSimilarity(float[] features1, float[] features2);

    public native void set(Property property, double value);
    public native double get(Property property);
}
