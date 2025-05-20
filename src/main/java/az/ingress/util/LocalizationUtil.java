package az.ingress.util;

import java.util.ResourceBundle;

import static az.ingress.model.constant.LocalizationConstants.BUNDLE_NAME;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

public enum LocalizationUtil {
    LOCALIZATION_UTIL;

    public String getMessageByKey(String key) {
        var resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, getLocale());
        return resourceBundle.getString(key);
    }
}