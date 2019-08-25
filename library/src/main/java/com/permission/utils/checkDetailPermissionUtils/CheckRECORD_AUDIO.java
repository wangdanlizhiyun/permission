package com.permission.utils.checkDetailPermissionUtils;
import android.content.Context;
import android.media.MediaRecorder;

import java.io.File;

public class CheckRECORD_AUDIO implements Check {

    private File mTempFile = null;

    @Override
    public Boolean check(Context context) throws Exception {
        MediaRecorder mediaRecorder = new MediaRecorder();
        try {
            mTempFile = File.createTempFile("permission", "test");
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(mTempFile.getAbsolutePath());
            mediaRecorder.prepare();
            mediaRecorder.start();
            return true;
        } finally {
            if (mediaRecorder != null) {
                try {
                    mediaRecorder.stop();
                } catch (Exception ignored) {
                }
                try {
                    mediaRecorder.release();
                } catch (Exception ignored) {
                }
            }
            if (mTempFile != null && mTempFile.exists()) {
                mTempFile.delete();
            }
        }
    }
}
