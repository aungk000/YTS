package me.aungkooo.yts.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.aungkooo.yts.Base;
import me.aungkooo.yts.R;
import me.aungkooo.yts.Utility;
import me.aungkooo.yts.adapter.MovieAdapter;
import me.aungkooo.yts.api.ApiClient;
import me.aungkooo.yts.listener.OnNavigationItemClickedListener;
import me.aungkooo.yts.model.DataResponse;
import me.aungkooo.yts.model.Movie;
import me.aungkooo.yts.model.MovieResponse;
import me.aungkooo.yts.view.NavigationRecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Base.Activity implements SwipeRefreshLayout.OnRefreshListener,
        OnNavigationItemClickedListener, ApiClient.OnApiResponseListener
{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_popular)
    RecyclerView recyclerViewPopular;
    @BindView(R.id.recycler_view_movie)
    RecyclerView recyclerViewMovie;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.navigation_recycler_view)
    NavigationRecyclerView navigationRecyclerView;
    @BindView(R.id.linear_layout_navigation)
    LinearLayout linearLayoutNavigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ArrayList<Movie> movieList, popularList;
    private MovieAdapter adapterMain, adapterPopular;
    private String category = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLightTheme = preferences.getBoolean(getString(R.string.pref_key_light_theme), false);

        setSupportActionBar(toolbar);
        setActionBarLogo(R.drawable.ic_yts_logo_green);
        setActionBarDrawerToggle(drawerLayout, toolbar);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(getColor(R.color.colorAccent));

        popularList = new ArrayList<>();
        adapterPopular = new MovieAdapter(this, popularList);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(
                this, LinearLayout.HORIZONTAL, false));
        recyclerViewPopular.setHasFixedSize(true);
        recyclerViewPopular.setAdapter(adapterPopular);

        movieList = new ArrayList<>();
        adapterMain = new MovieAdapter(this, movieList);
        recyclerViewMovie.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewMovie.setAdapter(adapterMain);

        navigationRecyclerView.setOnNavigationItemClickedListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (movieList.isEmpty() || popularList.isEmpty()) {
            loadMovieList();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(linearLayoutNavigation)) {
            drawerLayout.closeDrawer(linearLayoutNavigation);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRefresh()
    {
        if(Utility.isNetworkAvailable(this)) {
            fetchPopularMovieList();
            fetchMovieList();
        }
        else {
            makeShortSnackbar(refreshLayout, "No internet connection");

            if(refreshLayout.isRefreshing()) {
                refreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void onNavigationItemClicked(int position)
    {
        switch (position)
        {
            case 0:
                category = "all";
                loadMovieList();
                break;

            case 1:
                category = "3d";
                loadMovieList();
                break;

            case 2:
                changeActivity(SettingsActivity.class);
                break;

            case 3:
                changeActivity(AboutActivity.class);
                break;
        }

        drawerLayout.closeDrawer(linearLayoutNavigation);
    }

    public void fetchPopularMovieList()
    {
        ApiClient.getService().getPopularMovieList().enqueue(new Callback<DataResponse>()
        {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response)
            {
                if(response.isSuccessful())
                {
                    MovieResponse movieResponse = response.body().getMovieResponses();
                    ArrayList<Movie> resultList =  movieResponse.getResults();

                    adapterPopular.setMovieList(resultList);
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
    }

    public void fetchMovieList()
    {
        switch (category)
        {
            case "all":
                ApiClient.getService().getAllMovieList().enqueue(this);
                break;

            case "3d":
                ApiClient.getService().get3DMovieList().enqueue(this);

                break;
        }
    }

    public void loadMovieList()
    {
        if(Utility.isNetworkAvailable(this))
        {
            showProgressDialog("Loading movies...");

            fetchPopularMovieList();
            fetchMovieList();
        }
        else {
            makeShortSnackbar(refreshLayout, "No internet connection");
        }
    }

    @Override
    public void onResponse(Call<DataResponse> call, Response<DataResponse> response)
    {
        if(response.isSuccessful())
        {
            MovieResponse movieResponse = response.body().getMovieResponses();
            ArrayList<Movie> resultList =  movieResponse.getResults();

            adapterMain.setMovieList(resultList);

            if (refreshLayout.isRefreshing()) {
                refreshLayout.setRefreshing(false);
            }

            stopProgressDialog();
        }
    }

    @Override
    public void onFailure(Call<DataResponse> call, Throwable t) {

    }
}
