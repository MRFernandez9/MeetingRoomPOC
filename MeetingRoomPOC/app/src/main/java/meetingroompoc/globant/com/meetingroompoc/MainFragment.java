package meetingroompoc.globant.com.meetingroompoc;

import android.app.Fragment;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static meetingroompoc.globant.com.meetingroompoc.R.layout.*;

/**
 * Created by mr.fernandez on 6/30/15.
 */
public class MainFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(MainFragment.class.getName(), "On Resume");

        Intent intent = getActivity().getIntent();
        NdefMessage msgs []= null;

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {

            Log.i(MainFragment.class.getName(), "NDEF Discovered");

            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if (rawMsgs != null) {

                msgs = new NdefMessage[rawMsgs.length];

                for (int i = 0; i < rawMsgs.length; i++) {
                    Log.i(MainFragment.class.getName(), rawMsgs[i].toString());

                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
        }

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
    }
}
