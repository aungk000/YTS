package me.aungkooo.yts.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.aungkooo.thunder.NetworkListener;
import me.aungkooo.thunder.Thunder;
import me.aungkooo.yts.Base;
import me.aungkooo.yts.R;
import me.aungkooo.yts.Utility;
import me.aungkooo.yts.adapter.MovieAdapter;
import me.aungkooo.yts.api.ApiClient;
import me.aungkooo.yts.api.entry.value.Quality;
import me.aungkooo.yts.listener.OnNavigationItemClickedListener;
import me.aungkooo.yts.api.entry.Movie;
import me.aungkooo.yts.api.entry.MovieListResponse;
import me.aungkooo.yts.view.NavigationRecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Base.Activity implements SwipeRefreshLayout.OnRefreshListener,
        OnNavigationItemClickedListener, ApiClient.OnApiResponseListener, NetworkListener
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
    @BindView(R.id.txt_popular_downloads)
    TextView txtPopularDownloads;
    @BindView(R.id.txt_latest_movies)
    TextView txtLatestMovies;

    private MovieAdapter adapterMain, adapterPopular;
    private String category = Quality.QUALITY_DEFAULT.getValue();
    private boolean isCategoryChanged = false;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLightTheme = preferences.getBoolean(getString(R.string.pref_key_light_theme), false);

        setupOnCreate();

        refreshLayout.setOnRefreshListener(this);
        navigationRecyclerView.setOnNavigationItemClickedListener(this);

        Thunder.with(this).listen(this);
    }

    public void setupOnCreate()
    {
        setSupportActionBar(toolbar);
        setActionBarLogo(R.drawable.ic_yts_logo_green);
        setActionBarDrawerToggle(drawerLayout, toolbar);

        refreshLayout.setColorSchemeColors(getColor(R.color.colorAccent));
        refreshLayout.setProgressBackgroundColorSchemeColor(getColor(R.color.colorPrimaryDark));

        adapterPopular = new MovieAdapter(this);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(
                this, LinearLayout.HORIZONTAL, false));
        recyclerViewPopular.setHasFixedSize(true);
        recyclerViewPopular.setAdapter(adapterPopular);

        adapterMain = new MovieAdapter(this);
        recyclerViewMovie.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewMovie.setAdapter(adapterMain);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Thunder.with(this).start();

        if (adapterMain.isEmpty() || adapterPopular.isEmpty()) {
            loadMovieList();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        Thunder.with(this).stop();
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
    public void onRefresh() {
        if (isNetworkAvailable()) {
            fetchPopularMovieList();
            fetchMovieList();
        }
        else {
            if (refreshLayout.isRefreshing()) {
                refreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void onNavigationItemClicked(int position) {
        switch (position) {
            case 0:
                category = "all";
                isCategoryChanged = true;
                loadMovieList();
                break;

            case 1:
                category = "3D";
                isCategoryChanged = true;
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

    public void fetchPopularMovieList() {
        ApiClient.getClient().getService()
                .getMovieList("download_count", "desc").enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<Movie> resultList = response.body().getData().getMovies();

                    adapterPopular.setItemList(resultList);
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {

            }
        });
    }

    public void fetchMovieList() {
        ApiClient.getClient().getService().getMovieList(category).enqueue(this);
    }

    public void loadMovieList() {
        if (isNetworkAvailable()) {
            showProgressDialog("Loading movies...");

            fetchPopularMovieList();
            fetchMovieList();
        }
    }

    @Override
    public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
        if (response.isSuccessful()) {
            ArrayList<Movie> resultList = response.body().getData().getMovies();

            adapterMain.setItemList(resultList);

            if (refreshLayout.isRefreshing()) {
                refreshLayout.setRefreshing(false);
            }

            stopProgressDialog();
        }
    }

    @Override
    public void onFailure(Call<MovieListResponse> call, Throwable t) {

    }

    @Override
    public void onNetworkAvailable() {
        toggleSnackbar(false);
        toggleLabel(true);
        
        if(adapterPopular.isEmpty()) {
            fetchPopularMovieList();
            fetchMovieList();
        }

        if(isCategoryChanged) {
            fetchPopularMovieList();
            fetchMovieList();

            isCategoryChanged = false;
        }
    }

    @Override
    public void onNetworkUnavailable() {
        toggleSnackbar(true);

        if (adapterMain.isEmpty() && adapterPopular.isEmpty()) {
            toggleLabel(false);
        }
    }

    public void toggleSnackbar(boolean show)
    {
        if(snackbar == null) {
            snackbar = Snackbar.make(refreshLayout, R.string.no_internet_connection,
                    Snackbar.LENGTH_INDEFINITE);
            snackbar.getView().setBackgroundColor(getColor(R.color.colorAccentDark));
        }

        if (show) {
            if (snackbar != null && !snackbar.isShown()) {
                snackbar.show();
            }
        }
        else {
            if (snackbar.isShown()) {
                snackbar.dismiss();
            }
        }
    }

    public void toggleLabel(boolean show)
    {
        if(show) {
            if (txtPopularDownloads.getVisibility() == View.GONE
                    && txtLatestMovies.getVisibility() == View.GONE)
            {
                txtPopularDownloads.setVisibility(View.VISIBLE);
                txtLatestMovies.setVisibility(View.VISIBLE);
            }
        }
        else {
            if (txtPopularDownloads.getVisibility() == View.VISIBLE
                    && txtLatestMovies.getVisibility() == View.VISIBLE)
            {
                txtPopularDownloads.setVisibility(View.GONE);
                txtLatestMovies.setVisibility(View.GONE);
            }
        }
    }
}
