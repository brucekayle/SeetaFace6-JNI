/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_seeta_sdk_QualityOfPose */

#ifndef _Included_com_seeta_sdk_QualityOfPose
#define _Included_com_seeta_sdk_QualityOfPose
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_seeta_sdk_QualityOfPose
 * Method:    construct
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_seeta_sdk_QualityOfPose_construct
  (JNIEnv *, jobject);

/*
 * Class:     com_seeta_sdk_QualityOfPose
 * Method:    dispose
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_seeta_sdk_QualityOfPose_dispose
  (JNIEnv *, jobject);

/*
 * Class:     com_seeta_sdk_QualityOfPose
 * Method:    checkCore
 * Signature: (Lcom/seeta/sdk/SeetaImageData;Lcom/seeta/sdk/SeetaRect;[Lcom/seeta/sdk/SeetaPointF;[F)I
 */
JNIEXPORT jint JNICALL Java_com_seeta_sdk_QualityOfPose_checkCore
  (JNIEnv *, jobject, jobject, jobject, jobjectArray, jfloatArray);

#ifdef __cplusplus
}
#endif
#endif