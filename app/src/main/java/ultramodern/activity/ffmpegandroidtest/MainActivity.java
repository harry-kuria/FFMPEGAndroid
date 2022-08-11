package ultramodern.activity.ffmpegandroidtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.FFmpegLoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;


public class MainActivity extends AppCompatActivity {

    FFmpeg fFmpeg;
    Uri selectedUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            loadFFmpegLibrary();
        } catch (FFmpegNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void openVideo(View v){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("video/*");
        startActivityForResult(i,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode==RESULT_OK){
            selectedUri=data.getData();
            Intent i = new Intent(MainActivity.this, TrimActivity.class);
            i.putExtra("uri",selectedUri.toString());
            startActivity(i);
        }
    }

    public void loadFFmpegLibrary() throws FFmpegNotSupportedException {
        if (fFmpeg == null){
            fFmpeg=FFmpeg.getInstance(this);
            fFmpeg.loadBinary(new FFmpegLoadBinaryResponseHandler() {
                @Override
                public void onFailure() {
                    Toast.makeText(MainActivity.this, "Library Failed to load ", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess() {
                    Toast.makeText(MainActivity.this, "Library loaded successfully ", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onFinish() {

                }
            });
        }
    }

}