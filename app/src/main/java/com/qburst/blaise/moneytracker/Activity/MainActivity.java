package com.qburst.blaise.moneytracker.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.qburst.blaise.moneytracker.Fragment.BackupFragment;
import com.qburst.blaise.moneytracker.Fragment.BalanceFragment;
import com.qburst.blaise.moneytracker.Fragment.CategoryFragment;
import com.qburst.blaise.moneytracker.Fragment.RecurringFragment;
import com.qburst.blaise.moneytracker.Fragment.SavingFragment;
import com.qburst.blaise.moneytracker.Fragment.TransactionFragment;
import com.qburst.blaise.moneytracker.R;

import java.util.Calendar;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    public static int fragment_ID;
    public static final int BALANCES = 0;
    public static final int CATEGORY = 2;
    public static final int TRANSACTION = 1;
    public static final int SAVINGS=3;
    public static int currentMonth;

    @Override
    protected void onResume() {
        if(fragment_ID == CATEGORY) {
            displayCategories();
        }
        else if(fragment_ID == TRANSACTION) {
            displayTransaction();
        }
        else if(fragment_ID == SAVINGS) {
            displaySavings();
        }
        else if(fragment_ID == BALANCES) {
            displayBalances();
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment_ID == CATEGORY) {
                    Intent intent = new Intent(MainActivity.this, AddCategory.class);
                    startActivity(intent);
                }
                else if(fragment_ID == TRANSACTION) {
                    Intent intent = new Intent(MainActivity.this, AddTransaction.class);
                    startActivity(intent);
                }
                else if(fragment_ID == SAVINGS) {
                    Intent intent = new Intent(MainActivity.this,AddSavings.class);
                    startActivity(intent);
                }
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        checkPermission();
        displayBalances();
        navigationView.getMenu().findItem(R.id.nav_balances).setChecked(true);
        Calendar calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH)+1;
    }

    public boolean check(String[] permission) {
        return EasyPermissions.hasPermissions(this, permission);
    }

    public void checkPermission(){
        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(!check(permission)) {
            EasyPermissions.requestPermissions(this,
                    "Storage is needed for backing up your data",
                    1, permission);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("RestrictedApi")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_transactions) {
            fab.setVisibility(View.VISIBLE);
            displayTransaction();
        } else if (id == R.id.nav_savings) {
            fab.setVisibility(View.VISIBLE);
            displaySavings();
        } else if (id == R.id.nav_backup) {
            fab.setVisibility(View.INVISIBLE);
            importExport();
        } else if (id == R.id.nav_settings) {
            fab.setVisibility(View.INVISIBLE);
            displaySettings();
        } else if (id == R.id.nav_categories) {
            fab.setVisibility(View.VISIBLE);
            displayCategories();
        } else if (id == R.id.nav_recurrings) {
            fab.setVisibility(View.VISIBLE);
            displayRecurring();
        } else if (id == R.id.nav_balances) {
            fab.setVisibility(View.VISIBLE);
            displayBalances();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayBalances() {
        setActionBarTitle("Balances");
        BalanceFragment f = new BalanceFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
    }

    private void displayCategories() {
        setActionBarTitle("Categories");
        CategoryFragment f = new CategoryFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
    }

    private void displayRecurring() {
        setActionBarTitle("Recurring Transactions");
        RecurringFragment f = new RecurringFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
    }

    private void displaySettings() {
    }

    public void setActionBarTitle(String title) {
        this.setTitle(title);
    }

    private void displaySavings() {
        setActionBarTitle("Monthly Savings");
        SavingFragment f= new SavingFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
    }

    private void displayTransaction() {
        setActionBarTitle("Transactions");
        TransactionFragment f= new TransactionFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
    }

    private void importExport() {
        setActionBarTitle("Backup and Restore");
        BackupFragment f= new BackupFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
    }
}
