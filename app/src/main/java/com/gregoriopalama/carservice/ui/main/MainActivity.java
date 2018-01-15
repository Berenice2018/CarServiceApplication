package com.gregoriopalama.carservice.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gregoriopalama.carservice.R;
import com.gregoriopalama.carservice.data.Car;
import com.gregoriopalama.carservice.databinding.ActivityMainBinding;
import com.gregoriopalama.carservice.di.ViewModelFactory;
import com.gregoriopalama.carservice.ui.cars.CarAddActivity;
import com.gregoriopalama.carservice.ui.cars.CarFragment;
import com.gregoriopalama.carservice.ui.cars.CarsListFragment;
import com.gregoriopalama.carservice.viewmodel.CarViewModel;
import com.gregoriopalama.carservice.viewmodel.CarsListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.gregoriopalama.carservice.ui.cars.CarAddActivity.CAR_PLATE;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {
    private final static int MIN = 100;
    private final static int MAX = 1000;
    private final static int REQUEST_ADD = 101;

    public ActivityMainBinding binding;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelFactory carServiceViewModelFactory;

    private CarsListViewModel carsListViewModel;
    private CarViewModel carViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.appBarMain.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBarMain.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);

        populateCarsSubMenu(new ArrayList<>());

        setAndObserveViewModel();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, new CarsListFragment()).commit();
    }

    private void populateCarsSubMenu(List<Car> cars) {
        Menu carMenu = binding.navView.getMenu().findItem(R.id.nav_cars_item).getSubMenu();
        carMenu.clear();
        for (Car car : cars) {
            MenuItem item = carMenu.add(R.id.nav_cars_group,
                    generateRandomInt(), Menu.NONE, car.getPlate());
            item.setIcon(R.drawable.ic_car);
        }
        MenuItem addCarItem = carMenu.add(R.id.nav_cars_group,
                R.id.nav_add_car,
                Menu.NONE,
                getString(R.string.menu_add_car));
        addCarItem.setIcon(R.drawable.ic_add_black);
        binding.navView.invalidate();
    }

    private int generateRandomInt() {
        return ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
    }

    private void setAndObserveViewModel() {
        carsListViewModel = ViewModelProviders.of(this, carServiceViewModelFactory)
                .get(CarsListViewModel.class);

        carsListViewModel.getCars().observe(this, cars -> {
            populateCarsSubMenu(cars);
        });

        carViewModel = ViewModelProviders.of(this, carServiceViewModelFactory)
                .get(CarViewModel.class);

        carViewModel.getObservableCar().observe(this, new Observer<Car>() {
            @Override
            public void onChanged(@Nullable Car car) {
                if (car != null) {
                    carViewModel.setCar(car);
                    setTitle(carViewModel.car.get().getPlate());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_add_car:
                startActivityForResult(new Intent(getApplicationContext(), CarAddActivity.class),
                        REQUEST_ADD);
                break;
            case R.id.nav_settings:
                break;
            default:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, new CarFragment())
                        .commit();
                carViewModel.setPlate(item.getTitle().toString());
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_ADD) {
            if (data.hasExtra(CAR_PLATE)) {
                carViewModel.setPlate(data.getStringExtra(CAR_PLATE));
            }
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
