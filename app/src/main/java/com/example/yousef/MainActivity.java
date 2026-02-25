package com.example.yousef;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button buttonAboutme, buttonPassin;
    EditText Et;
    TextView tv;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    String edittext;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        edittext = Et.getText().toString();

        buttonAboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readRawFile();
            }
        });
    }

    private void initComponents() {
        buttonAboutme = findViewById(R.id.buttonAboutme);
        buttonPassin = findViewById(R.id.buttonPassin);
        Et = findViewById(R.id.Et);
        tv = findViewById(R.id.tv);
    }

    public void readRawFile() {
        try {
// פתיחת הקובץ וחיבור שרשרת הצינורות
            is = getResources().openRawResource(R.raw.aboutme);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
// הכנת משתנה הטקסט שיאגור הכל, וקריאת השורה הראשונה
            String allText = "";
            String line = br.readLine();
// כל עוד השורה אינה ריקה (לא סוף הקובץ)
            while (line != null) {
                allText = allText + line + "\n";
                line = br.readLine();
            }
// הצגת הטקסט שנאסף וסגירת ערוץ הקריאה
            tv.setText(allText);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}