// Generated code from Butter Knife. Do not modify!
package com.selema.newproject;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target, View source) {
    this.target = target;

    target.etNama = Utils.findRequiredViewAsType(source, R.id.etNama, "field 'etNama'", EditText.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'etPassword'", EditText.class);
    target.Lastname = Utils.findRequiredViewAsType(source, R.id.LastNama, "field 'Lastname'", EditText.class);
    target.Adress = Utils.findRequiredViewAsType(source, R.id.Adress, "field 'Adress'", EditText.class);
    target.home = Utils.findRequiredViewAsType(source, R.id.home, "field 'home'", EditText.class);
    target.bio = Utils.findRequiredViewAsType(source, R.id.bio, "field 'bio'", EditText.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.etPhone, "field 'phone'", EditText.class);
    target.btnRegister = Utils.findRequiredViewAsType(source, R.id.btnRegister, "field 'btnRegister'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etNama = null;
    target.etEmail = null;
    target.etPassword = null;
    target.Lastname = null;
    target.Adress = null;
    target.home = null;
    target.bio = null;
    target.phone = null;
    target.btnRegister = null;
  }
}
