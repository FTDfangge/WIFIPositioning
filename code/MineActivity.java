import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MineActivity extends AppCompatActivity {

    ImageButton xinxi;
    ImageButton zhanghu;
    ImageButton guize;
    ImageButton shoucang;
    Button returnbtn15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_layout);

        xinxi = (ImageButton) findViewById(R.id.xinxi);
        zhanghu = (ImageButton) findViewById(R.id.zhanghu);
        guize = (ImageButton) findViewById(R.id.guize);
        shoucang = (ImageButton) findViewById(R.id.shoucang);

        xinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this,MyinforActivity.class);
                startActivity(intent);

            }
        });

        zhanghu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this,AcinforActivity.class);
                startActivity(intent);

            }
        });

        guize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this,RulesActivity.class);
                startActivity(intent);

            }
        });

        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this,MyfavActivity.class);
                startActivity(intent);

            }
        });


        returnbtn15 = (Button) findViewById(R.id.returnbtn15);

        returnbtn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });





    }
}
