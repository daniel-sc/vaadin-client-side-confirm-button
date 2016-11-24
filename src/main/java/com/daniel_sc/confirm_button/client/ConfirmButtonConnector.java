package com.daniel_sc.confirm_button.client;

import com.daniel_sc.confirm_button.ConfirmButton;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel.AnimationType;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.vaadin.client.StyleConstants;
import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.ui.Icon;
import com.vaadin.client.ui.VButton;
import com.vaadin.client.ui.VHorizontalLayout;
import com.vaadin.client.ui.VLabel;
import com.vaadin.client.ui.VPanel;
import com.vaadin.client.ui.button.ButtonConnector;
import com.vaadin.client.widgets.Overlay;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@Connect(ConfirmButton.class)
public class ConfirmButtonConnector extends ButtonConnector {

    protected static final int CONFIRM_PANEL_OFFSET = 2;

    protected final VButton okButton = new VButton();
    protected final VButton cancelButton = new VButton();
    protected final Overlay overlay = GWT.create(Overlay.class);
    protected final VLabel confirmQuestion = new VLabel();

    public ConfirmButtonConnector() {
        VHorizontalLayout layout = new VHorizontalLayout();
        layout.addOrMoveSlot(layout.getSlot(cancelButton), 0, false);
        layout.addOrMoveSlot(layout.getSlot(okButton), 0, false);
        layout.addOrMoveSlot(layout.getSlot(confirmQuestion), 0, false);
        /*
         * TODO overwrite .v-slot .v-spacing { vertical-align: top; } on
         * confirmQuestionSlot
         */
        layout.setSpacing(true);
        layout.setMargin(new MarginInfo(true));
        VPanel confirmPanel = new VPanel();
        confirmPanel.setWidget(layout);
        overlay.setAnimationType(AnimationType.ROLL_DOWN);
        overlay.setAnimationEnabled(true);
        overlay.add(confirmPanel);

        cancelButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                enableButton(true);
                overlay.hide();
            }
        });
        okButton.addStyleDependentName(ValoTheme.BUTTON_PRIMARY);
        okButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                enableButton(true);
                overlay.hide();
                ConfirmButtonConnector.super.onClick(event);
            }
        });
    }

    @Override
    public void onClick(ClickEvent event) {
        showConfirm();
    }

    private void showConfirm() {
        enableButton(false);
        overlay.setOwner(getWidget());
        overlay.setPopupPositionAndShow(new PositionCallback() {

            @Override
            public void setPosition(int offsetWidth, int offsetHeight) {
                int top = getWidget().getAbsoluteTop() + getWidget().getOffsetHeight() + CONFIRM_PANEL_OFFSET;
                int left = getWidget().getAbsoluteLeft() + getWidget().getOffsetWidth() / 2 - offsetWidth / 2;
                overlay.setPopupPosition(left, top);
            }
        });
    }

    protected void enableButton(boolean enable) {
        getWidget().setEnabled(enable);
        getWidget().setStyleName(StyleConstants.DISABLED, !enable);
    }

    protected void setIcon(VButton button, Icon icon) {
        if (button.icon != null) {
            button.wrapper.removeChild(button.icon.getElement());
            button.icon = null;
        }
        if (icon != null) {
            button.icon = icon;
            button.wrapper.insertBefore(icon.getElement(), button.captionElement);
        }
    }

    @OnStateChange("resources")
    void onResourceChange2() {
        setIcon(okButton, getConnection().getIcon(getResourceUrl(ConfirmButtonState.CONFIRM_ICON)));
        setIcon(cancelButton, getConnection().getIcon(getResourceUrl(ConfirmButtonState.CANCEL_ICON)));
    }

    @OnStateChange("confirmQuestion")
    void setConfirmQuestion() {
        confirmQuestion.setText(getState().confirmQuestion);
    }

    @OnStateChange("confirmText")
    void setConfirmText() {
        okButton.setText(getState().confirmText);
    }

    @OnStateChange("cancelText")
    void setCancelText() {
        cancelButton.setText(getState().cancelText);
    }

    @Override
    public ConfirmButtonState getState() {
        return (ConfirmButtonState) super.getState();
    }

    @Override
    public com.daniel_sc.confirm_button.client.ConfirmButton getWidget() {
        return (com.daniel_sc.confirm_button.client.ConfirmButton) super.getWidget();
    }
}
