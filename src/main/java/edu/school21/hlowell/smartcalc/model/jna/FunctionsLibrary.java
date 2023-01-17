package edu.school21.hlowell.smartcalc.model.jna;

import com.sun.jna.Library;

public interface FunctionsLibrary extends Library {

  String getResultString(String some);

}
