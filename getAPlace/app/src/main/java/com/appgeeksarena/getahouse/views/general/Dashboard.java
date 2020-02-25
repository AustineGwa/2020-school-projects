package com.appgeeksarena.getahouse.views.general;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.appgeeksarena.getahouse.R;
import com.appgeeksarena.getahouse.adapters.SectionsAdapter;
import com.appgeeksarena.getahouse.models.Section;
import com.appgeeksarena.getahouse.utils.db.FirebaseUtils;
import com.appgeeksarena.getahouse.utils.listeners.OnSectionClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dashboard extends AppCompatActivity {

    private RecyclerView dashboard;
    private List<Section> sectionList;

    private FirebaseUtils firebaseUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dashboard = findViewById(R.id.dashboard_rv);
        dashboard.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        dashboard.setHasFixedSize(true);

        firebaseUtils = new FirebaseUtils();


        sectionList = new ArrayList<>();
        sectionList.addAll(Arrays.asList(
                new Section(R.drawable.logo, "Organ Centers"),
                new Section(R.drawable.logo, "Donation Request"),
                new Section(R.drawable.logo, "Available Organs"),
                new Section(R.drawable.logo, "Testimonials"),
                new Section(R.drawable.logo, "Organ Specialist"),
                new Section(R.drawable.logo, "Educational Content"),
                new Section(R.drawable.logo, "Terms And Conditions")));


        SectionsAdapter sectionsAdapter = new SectionsAdapter(sectionList, new OnSectionClickListener() {
            @Override
            public void onSectionClicked(String sectionName) {

                switch (sectionName) {
                    case "Organ Centers":
                        goToSelectedSection(new Intent(getApplicationContext(), OrganCentersActivity.class));

                        break;
                    case "Donation Request":
                        goToSelectedSection(new Intent(getApplicationContext(), DonationRequestActivity.class));

                        break;
                    case "Available Organs":
                        goToSelectedSection(new Intent(getApplicationContext(), AvailableOrgansActivity.class));
                        break;
                    case "Testimonials":
                        goToSelectedSection(new Intent(getApplicationContext(), TestimonialsActivity.class));
                        break;
                    case "Organ Specialist":
                        goToSelectedSection(new Intent(getApplicationContext(), OrganSpecialistActivity.class));
                        break;
                    case "Educational Content":
                        goToSelectedSection(new Intent(getApplicationContext(), EducationalContentActivity.class));
                        break;

                    case "Terms And Conditions" :
                        goToSelectedSection(new Intent(getApplicationContext(),TermsAndConditionsActivity.class));
                        break;
                    default:
                        break;
                }

            }
        });

        dashboard.setAdapter(sectionsAdapter);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.nav_notifications){
            goToSelectedSection(new Intent(getApplicationContext(), NotificationsActivity.class));
        }else if(item.getItemId() == R.id.nav_sign_out){
            firebaseUtils.getFirebaseAuth().signOut();
            startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToSelectedSection(Intent intent) {
        startActivity(intent);
    }
}
