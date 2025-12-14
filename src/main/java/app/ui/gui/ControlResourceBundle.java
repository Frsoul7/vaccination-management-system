package app.ui.gui;

import app.controller.AuthController;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class ControlResourceBundle extends ResourceBundle {

    AuthController ctrl;





    /**
     * Instance of the AuthController
     * @param ctrl      - controller to access data
     */
    public void setCtrl(AuthController ctrl) {
        this.ctrl = ctrl;
    }


    /**
     * @param key       - the key for the desired object, to have it on current session
     *
     * @return
     */
    @Override
    protected AuthController handleGetObject(String key) {
        return ctrl;
    }

    /**
     * @return null (we don't need this attribute)
     */
    @Override
    public Enumeration<String> getKeys() {
        return null;
    }
}
