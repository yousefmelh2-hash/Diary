package com.example.yousef;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button buttonRead, buttonPassin, buttonCreds;
    EditText Et;
    TextView tv;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    String inputText;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();


        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText = Et.getText().toString();
                readFromInternal();

            }
        });
        buttonPassin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText = Et.getText().toString();
                writeToInternal(inputText);

            }
        });
        buttonCreds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText = Et.getText().toString();
                readRawFile();

            }
        });
    }

    private void initComponents() {
        buttonRead = findViewById(R.id.buttonRead);
        buttonPassin = findViewById(R.id.buttonPassin);
        buttonCreds=findViewById(R.id.buttonCreds);
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
    // 2. פונקציית כתיבה - מקבלת את הטקסט שצריך לשמור
    public void writeToInternal(String textToSave) {
        try {
// יצירה או פתיחה של הקובץ במצב פרטי ונעול
            fos = openFileOutput("diary.txt", MODE_PRIVATE);
// כתיבת הטקסט (חובה להשתמש ב-getBytes)
            fos.write(textToSave.getBytes());
// סגירת הערוץ לשמירה סופית
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 2. פונקציית הקריאה (זהה כמעט ל-Raw)
    public void readFromInternal() {
        FileInputStream fis;

        try {
// ההבדל: משתמשים ב-openFileInput
            fis = openFileInput("diary.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
// שאר הקוד זהה למה שלמדנו...
            String allText = "";
            String line = br.readLine();
            while (line != null) {
                allText = allText + line + "\n";
                line = br.readLine();
            }
            tv.setText(allText);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}