package com.example.chat;

import static android.content.ContentValues.TAG;
import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;


import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=findViewById(R.id.linearLayout);


    }
    public void sender(String msg){
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String time = localTime.format(dateTimeFormatter);
        String s=msg+"\n"+time;
        SpannableString string = new SpannableString(s);
        string.setSpan(new AbsoluteSizeSpan(16, true), msg.length()+1,s.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        string.setSpan(new AlignmentSpan.Standard(
                        Layout.Alignment.ALIGN_OPPOSITE),
                msg.length()+1, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        string.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.grey)), msg.length(), s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView text=new TextView(this);
        LinearLayout.LayoutParams textparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        textparams.gravity=Gravity.RIGHT;
        textparams.setMargins(0, 0,0 , (int)dpToPx(this, 10));
        text.setLayoutParams(textparams);
        text.setBackground(ContextCompat.getDrawable(this, R.drawable.outgoing));
        text.setMaxWidth((int)dpToPx(this, 300));
        text.setTextSize(18);
        text.setTextColor(Color.BLACK);
        text.setPadding((int)dpToPx(this, 10), (int)dpToPx(this,5 ), (int)dpToPx(this,25 ), (int)dpToPx(this,5 ));
        text.setText(string);
        layout.addView(text);
    }
    public void reciever(String msg){
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String time = localTime.format(dateTimeFormatter);
        String s=msg+"\n"+time;
        SpannableString string = new SpannableString(s);
        string.setSpan(new AbsoluteSizeSpan(16, true), msg.length()+1,s.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        string.setSpan(new AlignmentSpan.Standard(
                        Layout.Alignment.ALIGN_OPPOSITE),
                msg.length()+1, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        string.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.grey)), msg.length(), s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView text=new TextView(this);
        LinearLayout.LayoutParams textparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        textparams.gravity=Gravity.LEFT;
        textparams.setMargins(0, 0,0 , (int)dpToPx(this, 10));
        text.setLayoutParams(textparams);
        text.setBackground(ContextCompat.getDrawable(this, R.drawable.incoming));
        text.setMaxWidth((int)dpToPx(this, 300));
        text.setTextSize(18);
        text.setTextColor(Color.BLACK);
        text.setPadding((int)dpToPx(this, 25), (int)dpToPx(this,5 ), (int)dpToPx(this,10 ), (int)dpToPx(this,5 ));
        text.setText(string);
        layout.addView(text);

    }
    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }
}