package jjto.com.workportal.modules;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import jjto.com.workportal.Constants.Utils;
import jjto.com.workportal.R;
import jjto.com.workportal.modules.home.MainActivity;

/**
 * Created by jhon on 23/02/2018.
 */

public class SettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SortPreferenceFragment())
                .commit();
    }

    public static class SortPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_general);
            bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_sort_list_name)));
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            setPreferenceSummary(preference, newValue);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Utils.LIST_ODER_PREFERENCE, newValue.toString()).apply();
            Intent intent = new Intent();
            intent.setClass(getActivity().getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference){
            preference.setOnPreferenceChangeListener(this);
            setPreferenceSummary(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(),""));
        }

        private void setPreferenceSummary(Preference preference, Object value){
            String stringValue = value.toString();

            if (preference instanceof ListPreference){
                ListPreference listPreference = (ListPreference) preference;
                int preferenceIndex = listPreference.findIndexOfValue(stringValue);

                if (preferenceIndex >= 0){
                    preference.setSummary(listPreference.getEntries()[preferenceIndex]);
                }
            }
        }
    }

}
