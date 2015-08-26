package com.example.miriam.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple dialog.
 */
public class MainActivityFragment extends DialogFragment {

    static private final String URL = "http://www.moma.org/collection/artists/4057?locale=en";
    static private final String CHOOSER_TEXT = "Open www.moma.org with:";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom));
        builder.setMessage(R.string.more_info)
                .setPositiveButton(R.string.visit_moma, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        visitMoma();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.show();
        TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);

        return dialog;
    }

    private void visitMoma(){

        Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        Intent chooserIntent = Intent.createChooser(baseIntent, CHOOSER_TEXT);

        startActivity(chooserIntent);

    }
}
