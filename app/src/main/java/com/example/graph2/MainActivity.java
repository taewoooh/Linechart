package com.example.graph2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //선 그래프
    private final String BASE_URL = "https://taewoooh88.cafe24.com/";
    Retrofit retrofit;
    private LineChart chart;
    LineData chartData;
    ArrayList<Entry> pricelist;
    LineDataSet set;
    int pc;
    XAxis xAxis;
    YAxis yLAxis;
    Description des;
    Legend l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Buytongsinset1();


        Buyminitongsin("래미안대치팰리스", "34", "84.99", "11680", "대치동");


    }


    public void Buytongsinset1() {
        pricelist = new ArrayList<>(); // 데이터를 담을 Arraylist
        chart = (LineChart) findViewById(R.id.chart);

        xAxis = chart.getXAxis();
        yLAxis = chart.getAxisLeft();

        yLAxis.setLabelCount(2, false);
        xAxis.setLabelCount(2, false);

        chart.setTouchEnabled(false);
        chart.setClickable(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setDrawBorders(false);
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setDrawLabels(false);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisRight().setDrawAxisLine(false);

        des = chart.getDescription();
        des.setEnabled(false);
        l = chart.getLegend();
        l.setEnabled(false);


        chartData = new LineData(); // 차트에 담길 데이터


    }

    public void Buytongsinset2() {
        set = new LineDataSet(pricelist, "LineGraph1"); // 데이터가 담긴 Arraylist 를 LineDataSet 으로 변환한다.

        set.setFillAlpha(110);
        set.setFillColor(Color.parseColor("#d7e7fa"));
        set.setColor(Color.parseColor("#0B80C9"));
        set.setCircleColor(Color.parseColor("#FFA1B4DC"));
        set.setCircleColorHole(Color.BLUE);
        set.setValueTextColor(Color.WHITE);
        set.setDrawValues(false);
        set.setLineWidth(1);
        set.setCircleRadius(6);
        set.setDrawCircleHole(false);
        set.setDrawCircles(false);
        set.setValueTextSize(9f);
        set.setDrawFilled(true);


        chartData.addDataSet(set); // 해당 LineDataSet 을 적용될 차트에 들어갈 DataSet 에 넣는다.
        chart.setData(chartData); // 차트에 위의 DataSet을 넣는다.

        chart.invalidate(); // 차트 업데이트
        chart.setTouchEnabled(false); // 차트 터치 disable


    }

    public void init() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void Buyminitongsin(String name, String pyeungsu, String area, String jiyeokcode, String dong) { // 서버로 전달할 파라미터



        init();
        Upgithub BgitHub2 = retrofit.create(Upgithub.class);
        Call<List<Upitem>> call = BgitHub2.contributors(name, pyeungsu, area, jiyeokcode, dong);
        call.enqueue(new Callback<List<Upitem>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            // 성공시
            public void onResponse(Call<List<Upitem>> call, Response<List<Upitem>> result) {

                List<Upitem> contributors = result.body();


                // 받아온 리스트를 순회하면서


                for (Upitem contributor : contributors) {
                    pc++;
                    int price = contributor.getPrice();
                    int Ymd = contributor.getYmd();

                    Log.e("데이터확인", " - > " + price + " / " + pc);

                    pricelist.add(new Entry(pc, price));


                }


                Buytongsinset2();


            }


            @Override
            public void onFailure(Call<List<Upitem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "price 정보 받아오기 실패 다시 시도해주세요.", Toast.LENGTH_LONG)
                        .show();


            }

        });


    }

}