package id.ac.umn.kevin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GitHubService service = RetrofitClient.getRetrofitInstance().create(GitHubService.class);
        Call<GitHubRepository> call = service.getAllRepo();

        call.enqueue(new Callback<GitHubRepository>() {
            @Override
            public void onResponse(Call<GitHubRepository> call, Response<GitHubRepository> response) {
                if (response.code() == 200){
                    generateRepository(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<GitHubRepository> call, Throwable t) {
                Log.d("GITHUB", t.getMessage());
            }
        });
    }

    public void generateRepository(List<ItemsItem> items) {
        for (final ItemsItem item: items){
            Log.d("ITEM", item.toString());

            LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View addView = inflater.inflate(R.layout.data,null);
            TextView fullName = addView.findViewById(R.id.full_name);
            TextView descriptionView =addView.findViewById(R.id.description);
            ImageView imageAvatar = addView.findViewById(R.id.avatar);

            fullName.setText(item.getFullName());
            descriptionView.setText(item.getDescription());
            Picasso.get().load(item.getOwner().getAvatarUrl()).into(imageAvatar);

            LinearLayout containerView = findViewById(R.id.idcontainer);

            addView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(getApplicationContext(),"Data Registered", Toast.LENGTH_SHORT).show();
                    Log.d("clivck", "onLongClick: aaaaaa");

                    return true;
                }
            });

            addView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this ,DataDetails.class);
                    intent.putExtra("full_name", item.getFullName());
                    intent.putExtra("description", item.getDescription());
                    intent.putExtra("language", item.getLanguage());
                    intent.putExtra("favorites", item.getStargazersCount());
                    intent.putExtra("fork", item.getForks());
                    intent.putExtra("scores", item.getScore());
                    startActivity(intent);
                }
            });

            containerView.addView(addView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.aboutme:
                openAboutMeActivity();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void openAboutMeActivity(){
        Intent intent = new Intent(this, AboutMe.class);
        startActivity(intent);
    }
}
