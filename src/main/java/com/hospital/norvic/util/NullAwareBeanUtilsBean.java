package com.hospital.norvic.util;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

public class NullAwareBeanUtilsBean extends BeanUtilsBean {

    @Override
    public void copyProperty(Object dest, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if (value == null) return;
        if (value instanceof String) {
            value = String.valueOf(value).trim();
        }
        super.copyProperties(dest, value);
    }
}
