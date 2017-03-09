package co.cdmunoz.spotiphoto.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import co.cdmunoz.spotiphoto.R;

/**
 * Some utilities
 * <p>
 * User: cdmunoz
 * Date: 3/8/17
 * Time: 9:44 AM
 */

public class Utility {

  public static String getPreferredTopArtistsNumber(Context context) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getString(context.getString(R.string.pref_number_key),
            context.getString(R.string.pref_number_ten));
  }

  public static String getPreferredTopArtistsCountry(Context context) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getString(context.getString(R.string.pref_country_key),
            context.getString(R.string.pref_country_jp));
  }

  public static String getCountryDescriptionFromValue(Context context, String value) {
    if(value.equals(context.getResources().getString(R.string.pref_country_value_jp))){
      return context.getResources().getString(R.string.pref_country_label_jp);
    } else if(value.equals(context.getResources().getString(R.string.pref_country_value_gb))){
      return context.getResources().getString(R.string.pref_country_label_gb);
    } else if(value.equals(context.getResources().getString(R.string.pref_country_value_us))) {
      return context.getResources().getString(R.string.pref_country_label_us);
    } else if(value.equals(context.getResources().getString(R.string.pref_country_value_co))) {
      return context.getResources().getString(R.string.pref_country_label_co);
    } else if (value.equals(context.getResources().getString(R.string.pref_country_value_es))) {
      return context.getResources().getString(R.string.pref_country_label_es);
    }
    return "";
  }
}
