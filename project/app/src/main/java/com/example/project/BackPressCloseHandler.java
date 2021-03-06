package com.example.project;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class BackPressCloseHandler {

        private long backKeyPressedTime = 0;
        private Toast toast;

        private Activity activity;

        public BackPressCloseHandler(Activity context) {
            this.activity = context;
        }

        public void onBackPressed() {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                showGuide();
                return;
            }

            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                toast.cancel();
                activity.finish();
            }

        }


        private void showGuide() {
            toast = Toast.makeText(activity, "뒤로 가기 버튼을 한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT);
            toast.show();

        }


}
