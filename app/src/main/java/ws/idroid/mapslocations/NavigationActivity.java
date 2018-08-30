package ws.idroid.mapslocations;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Button btnNavigate = findViewById(R.id.btn_navigate);
        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate();
            }
        });
    }

    private void navigate() {
        String packageName = "com.google.android.apps.maps";
        String query = "google.navigation:q=49.4839509,8.4903999";

        Intent intent = this.getPackageManager().getLaunchIntentForPackage(packageName);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(query));
        startActivity(intent);
    }
}
