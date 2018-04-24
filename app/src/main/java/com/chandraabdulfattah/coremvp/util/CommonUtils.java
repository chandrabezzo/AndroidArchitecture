package com.chandraabdulfattah.coremvp.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chandraabdulfattah.coremvp.R;
import com.chandraabdulfattah.coremvp.util.constanta.AppConstans;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bezzo on 26/09/17.
 */

public final class CommonUtils {

    private static final String TAG = "CommonUtils";

    private CommonUtils(){
        // this utility class is not publicy instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null){
            progressDialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings
                .Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email){
        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static String getTimeStamp(){
        return new SimpleDateFormat(AppConstans.TIMESTAMP_FORMAT, Locale.getDefault())
                .format(new Date());
    }

    public static boolean isJSONValid(String test) {

        if (test == null || test.isEmpty()){
            return false;
        }

        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }

        return true;
    }

    public static RequestOptions requestImageHandler() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_launcher);
        requestOptions.error(R.mipmap.ic_launcher);
        // uncomment if you need to clear cache
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);

        return requestOptions;
    }

    public static void autoHideFab(FloatingActionButton fabView, int dy){
        if (dy > 0 && fabView.getVisibility() == View.VISIBLE) {
            fabView.hide();
        } else if (dy < 0 && fabView.getVisibility() != View.VISIBLE) {
            fabView.show();
        }
    }

    public static Locale getLocaleID(){
        return new Locale("in", "ID");
    }

    public static String getPriceRp(Double price){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("in","ID"));

        return currencyFormat.format(price);
    }


    public static String getPriceFormat(Locale locale, double price){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

        return currencyFormat.format(price);
    }

    public static String[] getSplittedString(String text, String regex){
        return text.split(regex);
    }

    public static int getColor(Context context, int id){
        if (Build.VERSION.SDK_INT >= 23){
            return context.getResources().getColor(id, context.getTheme());
        }

        return context.getResources().getColor(id);
    }

    public static Drawable getDrawable(Context context, int id){
        if (Build.VERSION.SDK_INT >= 21){
            return  context.getResources().getDrawable(id, context.getTheme());
        }
        return context.getResources().getDrawable(id);
    }

    public static Spanned getTextFromHtml(String html){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
        }
        else {
            return Html.fromHtml(html);
        }
    }

    public static void changeLanguage(Context context, String language){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            LocaleHelper.onAttach(context, language);
        }
        else {
            Resources res = context.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration configuration = res.getConfiguration();
            configuration.setLocale(new Locale(language));
            res.updateConfiguration(configuration, dm);
        }
    }

    public static Boolean checkPlayServices(Activity activity){
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS){
            if (apiAvailability.isUserResolvableError(resultCode)){
                apiAvailability.getErrorDialog(activity, resultCode, 0).show();
            }
            else {
                AppLogger.e("Not support Google Play Service");
                Toast.makeText(activity, "This device is not supported", Toast.LENGTH_SHORT).show();
                activity.finish();
            }
            return false;
        }
        return true;
    }

    public static boolean isServiceRunning(Activity activity, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static <V> String convertObjectToJson(V object){
        Gson gson =  new Gson();
        return gson.toJson(object);
    }

    public static <V> String convertArrayObjectToJson(ArrayList<V> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
