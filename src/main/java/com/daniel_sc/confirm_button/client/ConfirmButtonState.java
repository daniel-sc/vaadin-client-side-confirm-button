package com.daniel_sc.confirm_button.client;

import com.vaadin.shared.annotations.NoLayout;
import com.vaadin.shared.ui.button.ButtonState;

@SuppressWarnings("serial")
public class ConfirmButtonState extends ButtonState {

    public static final String CONFIRM_ICON = "icon_confirm";
    public static final String CANCEL_ICON = "icon_cancel";

    @NoLayout
    public String confirmQuestion = "Sure?";

    @NoLayout
    public String confirmText = "Yes";

    @NoLayout
    public String cancelText = "No";

}
