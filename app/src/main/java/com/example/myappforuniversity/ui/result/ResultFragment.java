package com.example.myappforuniversity.ui.result;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myappforuniversity.PointValue;
import com.example.myappforuniversity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultFragment extends Fragment {
    EditText  yValue;
    Button b1;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
    GraphView graphView;LineGraphSeries series;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);
        yValue = (EditText)view.findViewById(R.id.resultct1);
        b1 =(Button) view.findViewById(R.id.resultbutton1);
 graphView = (GraphView)view.findViewById(R.id.graphresult1);series = new LineGraphSeries();graphView.addSeries(series);
 graphView.getViewport().setXAxisBoundsManual(true); graphView.getViewport().setMinX(3);graphView.getViewport().setMaxX(6);
  graphView.getViewport().setYAxisBoundsManual(true); graphView.getViewport().setMinY(2);graphView.getViewport().setMaxY(7);
        graphView.getViewport().setScalable(true); graphView.getViewport().setScalableY(true);
        series.setColor(Color.rgb(226,91,34)); series.setThickness(8);
        series.setDrawBackground(true);series.setBackgroundColor(Color.WHITE);
        series.setDrawDataPoints(true); series.setDataPointsRadius(6);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chartable");
        setListeners();
        //graphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {
                    return sdf.format(new Date((long)value)); }
               else { return super.formatLabel(value, isValueX);
            }}
        });return view; }
    private void setListeners(){

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"added",Toast.LENGTH_SHORT).show();
                String id=databaseReference.push().getKey();
                long x=new Date().getTime();
                long y=Integer.parseInt(yValue.getText().toString());
                PointValue pointValue=new PointValue(x,y);
                databaseReference.child(id).setValue(pointValue);
                Toast.makeText(getActivity(),"Added",Toast.LENGTH_SHORT).show(); }
        }); }
    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot datasnapshot) {
                DataPoint[] dp = new DataPoint[(int) datasnapshot.getChildrenCount()];
               int index=0;
               for(DataSnapshot myDataSnapshot: datasnapshot.getChildren())
               {
                   PointValue pointValue=myDataSnapshot.getValue(PointValue.class);
                   dp[index]=new DataPoint(pointValue.getxValue(),pointValue.getyValue());index++;
               }
               series.resetData(dp);
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }
}


