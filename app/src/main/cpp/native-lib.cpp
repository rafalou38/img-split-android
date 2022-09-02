//#include <jni.h>
//#include <string>
//#include <android/log.h>
//#include <android/bitmap.h>
//
//#define  LOG_TAG    "barcodeme"
//#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
//#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)


//extern "C" JNIEXPORT void JNICALL
//Java_com_rafaelmc_imgsplit_MainActivity_code39(
//        JNIEnv *env,
//        jobject obj /* this */,
//        jint count) {
//
//
//
//}

//extern "C" JNIEXPORT void JNICALL
//Java_com_rafaelmc_imgsplit_MainActivity_code39(
//        JNIEnv *env,
//        jobject obj /* this */,
//        jstring dataToEncode,
//        jobject bitmapbarcode) {
//
//    AndroidBitmapInfo infobarcode;
//    void *pixelsbarcode;
//    uint8_t *imagedata;
//    int ret;
//    int y;
//    int x;
//    const int NARROW = 1;
//    const int WIDE = 3;
//
//    LOGI("code39");
//    const char *szData = env->GetStringUTFChars(dataToEncode, NULL);
//
//    // LOGI("data to print [%s]",szData);
//
//    if ((
//                ret = AndroidBitmap_getInfo(env, bitmapbarcode, &infobarcode)
//        ) < 0) {
//        LOGE("AndroidBitmap_getInfo() failed ! error=%d", ret);
//        return;
//    }
//
//
//    if ((
//                ret = AndroidBitmap_lockPixels(env, bitmapbarcode, &pixelsbarcode)
//        ) < 0) {
//        LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
//    }
//
//
//
//    //    imagedata = (uint8_t *) pixelsbarcode;
//    //    int left = 20; // start here
//    //    const char *p = szData; // walk the string via a pointer
//    //    int k = 0;
//    //    while (*p != 0) {
//    //        const char *pattern = getSequence(*p);
//    //        if (pattern != NULL) {
//    //            int thiswidth = 0;
//    //
//    //            for (
//    //                    int k = 0;
//    //                    k < 4; k += 1) {
//    //                LOGI("k is [%d]", k);
//    //                // bar
//    //                if (pattern[k * 2] == 0)
//    //                    thiswidth = NARROW;
//    //                if (pattern[k * 2] == '1')
//    //                    thiswidth = WIDE;
//    //                LOGI("thiswidth is [%d]\t[%d]", thiswidth, left);
//    //                for (
//    //                        y = 10;
//    //                        y <= infobarcode.height - 10 - 1; y++) {
//    //                    for (
//    //                            x = left;
//    //                            x < (left + thiswidth); x++) {
//    //                        *(imagedata + x + (
//    //                                y * infobarcode
//    //                                        .width)) = 255;
//    //                    }
//    //                }
//    //                left +=
//    //                        thiswidth;
//    //                // space
//    //                if (pattern[k * 2 + 1] == '0')
//    //                    thiswidth = NARROW;
//    //                if (pattern[k * 2 + 1] == '1')
//    //                    thiswidth = WIDE;
//    //                left +=
//    //                        thiswidth;
//    //                LOGI("thiswidth is [%d]\t[%d]", thiswidth, left);
//    //            }
//    //            // final bar
//    //            if (pattern[8] == '0')
//    //                thiswidth = NARROW;
//    //            if (pattern[8] == '1')
//    //                thiswidth = WIDE;
//    //            LOGI("thiswidth is [%d]\t[%d]", thiswidth, left);
//    //            for (
//    //                    y = 10;
//    //                    y <= infobarcode.height - 10 - 1; y++) {
//    //                for (
//    //                        x = left;
//    //                        x < (left + thiswidth); x++) {
//    //                    *(imagedata + x + (
//    //                            y * infobarcode
//    //                                    .width)) = 255;
//    //                }
//    //            }
//    //            // inter character gap
//    //            left += WIDE * 2;
//    //        }
//    //        p++;
//    //    }
//    AndroidBitmap_unlockPixels(env, bitmapbarcode);
//
//}
