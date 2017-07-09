
package com.tonymontes.comicvinesearch.fragments;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tonymontes.comicvinesearch.R;

public class MessageDialogFragment
    extends
        DialogFragment {

    public static final String TAG = MessageDialogFragment.class.getSimpleName();
    public static final String ARG_ICON = "icon";
    public static final String ARG_TITLE = "title";
    public static final String ARG_MESSAGE = "message";

    /**
     * Use from Activities.
     *
     * @param icon    dialog icon
     * @param title   dialog title
     * @param message dialog message
     * @return a new instance of MessageDialogFragment
     */
    public static MessageDialogFragment newInstance(int icon, String title, String message) {
        return newInstance(icon, title, message, null);
    }

    /**
     * Use from Activities.
     *
     * @param icon     dialog icon
     * @param title    dialog title
     * @param message  dialog message
     * @param fragment target fragment
     * @return a new instance of MessageDialogFragment
     */
    public static MessageDialogFragment newInstance(int icon, String title, String message, Fragment fragment) {
        MessageDialogFragment confirmDialog = new MessageDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ICON, icon);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        confirmDialog.setArguments(args);
        if (fragment != null) confirmDialog.setTargetFragment(fragment, 0);
        return confirmDialog;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("InflateParams")
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        View dialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_message, null);

        TextView messageView = dialogView.findViewById(R.id.message);
        messageView.setMovementMethod(LinkMovementMethod.getInstance());
        String html = getArguments().getString(ARG_MESSAGE);
        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            messageView.setText(Html.fromHtml(html));
        //else
        //    messageView.setText(html);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppTheme_AlertDialog);
        builder.setTitle(getArguments().getString(ARG_TITLE))
                .setIcon(getArguments().getInt(ARG_ICON))
                .setView(dialogView)
                .setPositiveButton(R.string.OK, (dialog, which) -> dialog.dismiss());

        return builder.create();
    }
}