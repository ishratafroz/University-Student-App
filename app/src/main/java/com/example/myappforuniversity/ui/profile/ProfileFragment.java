package com.example.myappforuniversity.ui.profile;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myappforuniversity.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class ProfileFragment extends Fragment {
       TextView t1,t2,t3,t4,t5,t6;
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        t1 = view.findViewById(R.id.pro1);t2 = view.findViewById(R.id.pro2);
        t3 = view.findViewById(R.id.pro3);
        t4 = view.findViewById(R.id.pro4);
        t5 = view.findViewById(R.id.pro5);t6 = view.findViewById(R.id.pro6);
        findwe(); return view; }
    public void findwe() {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Khulna,BD&appid=2804c487b9c749c1fadc256e2d593464&units=Imperial";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
          try{JSONObject main_object=response.getJSONObject("main");
              JSONArray array=response.getJSONArray("weather");
              JSONObject object=array.getJSONObject(0);
              String temp=String.valueOf(main_object.getDouble("temp"));
              String description=object.getString("description");
              String city=response.getString("name");
            Double temp_int=Double.parseDouble(temp); double centi=(temp_int-32)/1.8000;
            centi=Math.round(centi); int i=(int) centi;
            t4.setText(String.valueOf(i));
              t3.setText(city);
              t6.setText(description);
              Calendar calendar=Calendar.getInstance();
              SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
              String formatted_date=sdf.format(calendar.getTime());
              t2.setText(formatted_date);
          }
          catch (JSONException e)
          {
              e.printStackTrace();
              Toast.makeText(getActivity(), "cant be parsed", Toast.LENGTH_SHORT).show();
          }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
 Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(jor);
    }// {"coord":{"lon":-0.1257,"lat":51.5085},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"base":"stations","main":{"temp":291.07,"feels_like":290.9,"temp_min":288.7,"temp_max":292.64,"pressure":1024,"humidity":76},"visibility":10000,"wind":{"speed":1.09,"deg":261,"gust":1.28},"clouds":{"all":98},"dt":1623139508,"sys":{"type":2,"id":2019646,"country":"GB","sunrise":1623123883,"sunset":1623183282},"timezone":3600,"id":2643743,"name":"London","cod":200}
        }
    //{"coord":{"lon":89.5672,"lat":22.8135},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"base":"stations","main":{"temp":310.03,"feels_like":316.22,"temp_min":310.03,"temp_max":310.03,"pressure":1000,"humidity":45,"sea_level":1000,"grnd_level":999},"visibility":10000,"wind":{"speed":6.93,"deg":198,"gust":6.41},"clouds":{"all":91},"dt":1623139999,"sys":{"country":"BD","sunrise":1623107765,"sunset":1623156341},"timezone":21600,"id":1336135,"name":"Khulna","cod":200}

