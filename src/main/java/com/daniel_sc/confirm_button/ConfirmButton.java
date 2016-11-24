package com.daniel_sc.confirm_button;

import com.daniel_sc.confirm_button.client.ConfirmButtonState;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

@SuppressWarnings("serial")
public class ConfirmButton extends Button {

    public ConfirmButton() {
        this(null, null, null);
    }

    public ConfirmButton(Resource icon) {
        this(null, icon, null);
    }

    public ConfirmButton(String caption, ClickListener listener) {
        this(caption, null, listener);
    }

    public ConfirmButton(String caption, Resource icon) {
        this(caption, icon, null);
    }

    public ConfirmButton(String caption) {
        this(caption, null, null);
    }

    public ConfirmButton(String caption, Resource icon, ClickListener listener) {
        super(caption, icon);
        if (listener != null) {
            addClickListener(listener);
        }
        setConfirmButtonIcon(FontAwesome.CHECK);
    }

    public void setConfirmButtonIcon(Resource icon) {
        setResource(ConfirmButtonState.CONFIRM_ICON, icon);
    }

    public void setCancelButtonIcon(Resource icon) {
        setResource(ConfirmButtonState.CANCEL_ICON, icon);
    }

    public void setConfirmQuestion(String question) {
        getState().confirmQuestion = question;
    }

    public String getConfirmQuestion() {
        return getState(false).confirmQuestion;
    }

    public void setCancelText(String text) {
        getState().cancelText = text;
    }

    public String getCancelText() {
        return getState(false).cancelText;
    }

    public void setConfirmText(String text) {
        getState().confirmText = text;
    }

    public String getConfirmText() {
        return getState(false).confirmText;
    }

    @Override
    protected ConfirmButtonState getState() {
        return (ConfirmButtonState) super.getState();
    }

    @Override
    protected ConfirmButtonState getState(boolean markAsDirty) {
        return (ConfirmButtonState) super.getState(markAsDirty);
    }

}
