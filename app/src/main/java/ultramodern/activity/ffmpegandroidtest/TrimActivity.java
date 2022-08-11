package ultramodern.activity.ffmpegandroidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class TrimActivity extends AppCompatActivity {

    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trim);

        Intent i = getIntent();
            String imgPath = i.getStringExtra("uri");
            uri = Uri.parse(imgPath);

    }
}