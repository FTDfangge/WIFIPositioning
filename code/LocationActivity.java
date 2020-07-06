package com.example.schoolwifipositioning;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.OverScroller;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolwifipositioning.CLASSES.Point;
import com.example.schoolwifipositioning.CLASSES.Positioning;
import com.example.schoolwifipositioning.CLASSES.WIFIScanner;

public class LocationActivity extends AppCompatActivity {

    Button returnbtn9;
    ImageButton positionButton,positionButton2;
    Point currentPoint;
    int roomId;
    ImageView point1,point2,point3,point4,point5,point6,point7,point8,point9,point10;
    ImageView point11,point12,point13,point14,point15,point16,point17,point18,point19;

    private OverScroller scroller;
    private int mCurrentX, mCurrentY;
    private ValueAnimator translationAnimation;
    private GestureDetector gestureDetector;

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

        positionButton = (ImageButton) findViewById(R.id.disposition);
        positionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPoint.setAPList(WIFIScanner.getInstance().getResultList());

                roomId = Positioning.positionTool().getMyPositionByDis(currentPoint);


            }
        });

        positionButton2 = (ImageButton) findViewById(R.id.cosposition);
        positionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPoint.setAPList(WIFIScanner.getInstance().getResultList());

                roomId = Positioning.positionTool().getMyPositionByCos(currentPoint);


            }
        });

        point1 = (ImageView) findViewById(R.id.point1);
        point2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);
        point4 = (ImageView) findViewById(R.id.point4);
        point5 = (ImageView) findViewById(R.id.point5);
        point6 = (ImageView) findViewById(R.id.point6);
        point7 = (ImageView) findViewById(R.id.point7);
        point8 = (ImageView) findViewById(R.id.point8);
        point9 = (ImageView) findViewById(R.id.point9);
        point10 = (ImageView) findViewById(R.id.point10);
        point11 = (ImageView) findViewById(R.id.point11);
        point12 = (ImageView) findViewById(R.id.point12);
        point13 = (ImageView) findViewById(R.id.point13);
        point14 = (ImageView) findViewById(R.id.point14);
        point15 = (ImageView) findViewById(R.id.point15);
        point16 = (ImageView) findViewById(R.id.point16);
        point17 = (ImageView) findViewById(R.id.point17);
        point18 = (ImageView) findViewById(R.id.point18);
        point19 = (ImageView) findViewById(R.id.point19);


        //point显示隐藏
        //roomId = 2;
        if(roomId == 1){
            point1.setVisibility(View.VISIBLE);
        }else if (roomId == 2){
            point2.setVisibility(View.VISIBLE);
        }else if (roomId == 3){
            point3.setVisibility(View.VISIBLE);
        }else if (roomId == 4){
            point4.setVisibility(View.VISIBLE);
        }else if (roomId == 5){
            point5.setVisibility(View.VISIBLE);
        }else if (roomId == 6){
            point6.setVisibility(View.VISIBLE);
        }else if (roomId == 7){
            point7.setVisibility(View.VISIBLE);
        }else if (roomId == 8){
            point8.setVisibility(View.VISIBLE);
        }else if (roomId == 9){
            point9.setVisibility(View.VISIBLE);
        }else if (roomId == 10){
            point10.setVisibility(View.VISIBLE);
        }else if (roomId == 11){
            point11.setVisibility(View.VISIBLE);
        }else if (roomId == 12){
            point12.setVisibility(View.VISIBLE);
        }else if (roomId == 13){
            point13.setVisibility(View.VISIBLE);
        }else if (roomId == 14){
            point14.setVisibility(View.VISIBLE);
        }else if (roomId == 15){
            point15.setVisibility(View.VISIBLE);
        }else if (roomId == 16){
            point16.setVisibility(View.VISIBLE);
        }else if (roomId == 17){
            point17.setVisibility(View.VISIBLE);
        }else if (roomId == 18){
            point18.setVisibility(View.VISIBLE);
        }else if (roomId == 19) {
            point19.setVisibility(View.VISIBLE);
        }
    }
}
