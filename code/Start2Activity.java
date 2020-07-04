import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Start2Activity extends AppCompatActivity {

    Button rgst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start2);



        rgst = (Button) findViewById(R.id.rgst);
        rgst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start2Activity.this,MenuActivity.class);
                startActivity(intent);

            }
        });
    }
}
