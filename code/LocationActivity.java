import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolwifipositioning.CLASSES.Point;
import com.example.schoolwifipositioning.CLASSES.Positioning;
import com.example.schoolwifipositioning.CLASSES.WIFIScanner;

public class LocationActivity extends AppCompatActivity {

    Button returnbtn9;
    ImageButton positionButton,positionButton2;
    Point currentPoint;
    int roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_layout);

        returnbtn9 = (Button) findViewById(R.id.returnbtn9);

        returnbtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
        
        positionButton = (ImageButton) findViewById(R.id.imageButton);
        positionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPoint.setAPList(WIFIScanner.getInstance().getResultList());

                roomId = Positioning.positionTool().getMyPositionByDis(currentPoint);


            }
        });

        positionButton2 = (ImageButton) findViewById(R.id.imageButton2);
        positionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPoint.setAPList(WIFIScanner.getInstance().getResultList());

                roomId = Positioning.positionTool().getMyPositionByCos(currentPoint);


            }
        });

    }
}
