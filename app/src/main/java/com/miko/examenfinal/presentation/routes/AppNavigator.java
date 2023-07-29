package com.miko.examenfinal.presentation.routes;

import android.content.Context;
import android.content.Intent;

import com.miko.examenfinal.presentation.views.ContactDetailView;
import com.miko.examenfinal.presentation.views.ContactFormView;
import com.miko.examenfinal.presentation.views.ContactListView;

public class AppNavigator {

    private final Context context;

    public AppNavigator(Context context) {
        this.context = context;
    }

    public void navigateToContactList() {
        Intent intent = new Intent(context, ContactListView.class);
        context.startActivity(intent);
    }

    public void navigateToContactDetail(String contactId) {
        Intent intent = new Intent(context, ContactDetailView.class);
        intent.putExtra("contact_id", contactId);
        context.startActivity(intent);
    }

    public void navigateToContactForm() {
        Intent intent = new Intent(context, ContactFormView.class);
        context.startActivity(intent);
    }
}
