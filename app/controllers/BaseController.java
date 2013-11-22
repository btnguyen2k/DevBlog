package controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import play.Play;
import play.api.templates.Html;
import play.mvc.Controller;

public class BaseController extends Controller {

    protected static String getFrontTemplateName() {
        return Play.application().configuration().getString("template.front");
    }

    protected static Html render(String template, String view, Object... params)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException,
            SecurityException, NoSuchMethodException, IllegalArgumentException,
            InvocationTargetException {
        String clazzName = "views.html." + template + "." + view;
        Class<?> clazz = Class.forName(clazzName);

        Class<?>[] typeList = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            typeList[i] = params[i].getClass();
        }
        Method renderMethod = clazz.getMethod("render", typeList);
        return (Html) renderMethod.invoke(null, params);
    }
}