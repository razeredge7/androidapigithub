package id.ac.umn.kevin;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_details);

        generateRepositoryDetail();

    }

    public void generateRepositoryDetail() {

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addView = inflater.inflate(R.layout.data_detail,null);
        TextView appNameText = addView.findViewById(R.id.fullappname);
        TextView descriptionText = addView.findViewById(R.id.appdescription);
        TextView languageText = addView.findViewById(R.id.programmingLanguage);
        TextView favoritesText =addView.findViewById(R.id.totalFavorites);
        TextView forkText = addView.findViewById(R.id.forksTotal);
        TextView scoresText =addView.findViewById(R.id.totalScore);
        FloatingActionButton registerButtonVar = addView.findViewById(R.id.registerButton);

        LinearLayout containerView = findViewById(R.id.idcontainer);;

        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("full_name");
        String desc = bundle.getString("description");
        Integer fav = bundle.getInt("favorites");
        Integer fork = bundle.getInt("fork");
        Double scores = bundle.getDouble("scores");
        String  language= bundle.getString("language");

        appNameText.setText(name);
        descriptionText.setText(desc);
        languageText.setText(language);
        forkText.setText(fork.toString());
        scoresText.setText(scores.toString());
        favoritesText.setText(fav.toString());

        registerButtonVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Data Registered", Toast.LENGTH_SHORT).show();
            }
        });

        containerView.addView(addView);
    }
}
