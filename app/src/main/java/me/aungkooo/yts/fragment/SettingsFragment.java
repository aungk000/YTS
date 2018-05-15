package me.aungkooo.yts.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;

import me.aungkooo.yts.R;


public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        SwitchPreference switchLightTheme = (SwitchPreference) findPreference(getString(R.string.pref_key_light_theme));
        switchLightTheme.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue)
    {
        return true;
    }
}
