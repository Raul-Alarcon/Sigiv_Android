package com.example.planme.utils;

import android.util.Log;

public class ExceptionHelper {
    public static void log(Exception e) {
        if (e != null) {
            StackTraceElement traceElement = e.getStackTrace()[0];
            String className = traceElement.getClassName();
            String methodName = traceElement.getMethodName();
            int lineNumber = traceElement.getLineNumber();

            Log.e("ExceptionHelper",
                    "Error en " + className + "." + methodName + "() [Línea: " + lineNumber + "]: "
                            + e.getMessage(),
                    e);

        } else {
            Log.e("ExceptionHelper", "Excepción nula proporcionada.");
        }
    }
}
