/*
 * Copyright 2015 Level Money, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.levelmoney.stylist;

import android.content.res.TypedArray;
import android.view.View;

import com.levelmoney.stylist.annotations.StyleBoolean;
import com.levelmoney.stylist.annotations.StyleColor;
import com.levelmoney.stylist.annotations.StyleDimen;
import com.levelmoney.stylist.annotations.StyleDrawable;
import com.levelmoney.stylist.annotations.StyleFloat;
import com.levelmoney.stylist.annotations.StyleInt;
import com.levelmoney.stylist.annotations.StyleResource;
import com.levelmoney.stylist.annotations.StyleString;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

enum StyleInjector {
    BOOL(StyleBoolean.class) {
        @Override
        protected int getIndex(Annotation annotation) {
            return ((StyleBoolean) annotation).value();
        }
        @Override
        protected Object get(int index, TypedArray arr, Field field) {
            return arr.getBoolean(index, false);
        }
    },
    COLOR(StyleColor.class) {
        @Override
        protected int getIndex(Annotation annotation) {
            return ((StyleColor) annotation).value();
        }
        @Override
        protected Object get(int index, TypedArray arr, Field field) {
            return arr.getColor(index, 0);
        }
    },
    DIMEN(StyleDimen.class) {
        @Override
        protected int getIndex(Annotation annotation) {
            return ((StyleDimen) annotation).value();
        }
        @Override
        protected Object get(int index, TypedArray arr, Field field) {
            return arr.getDimension(index, 0f);
        }
    },
    DRAWABLE(StyleDrawable.class)  {
        @Override
        protected int getIndex(Annotation annotation) {
            return ((StyleDrawable) annotation).value();
        }
        @Override
        protected Object get(int index, TypedArray arr, Field field) {
            return arr.getDrawable(index);
        }
    },
    FLOAT(StyleFloat.class) {
        @Override
        protected int getIndex(Annotation annotation) {
            return ((StyleFloat) annotation).value();
        }
        @Override
        protected Object get(int index, TypedArray arr, Field field) {
            float value = arr.getFloat(index, 0f);
            Class<?> type = field.getType();
            if (type.equals(Float.class)) {
                return value;
            } else if (type.equals(Double.class)) {
                return (double) value;
            } else {
                return value;
            }
        }
    },
    INT(StyleInt.class) {
        @Override
        protected int getIndex(Annotation annotation) {
            return ((StyleInt) annotation).value();
        }
        @Override
        protected Object get(int index, TypedArray arr, Field field) {
            int value = arr.getInt(index, 0);
            Class<?> type = field.getType();
            if (type.equals(Integer.class)) {
                return value;
            } else if (type.equals(Long.class)) {
                return (long) value;
            } else {
                return value;
            }
        }
    },
    RESOURCE(StyleResource.class) {
        @Override
        protected int getIndex(Annotation annotation) {
            return ((StyleResource) annotation).value();
        }
        @Override
        protected Object get(int index, TypedArray arr, Field field) {
            return arr.getResourceId(index, 0);
        }
    },
    STRING(StyleString.class) {
        @Override
        protected int getIndex(Annotation annotation) {
            return ((StyleString) annotation).value();
        }
        @Override
        protected Object get(int index, TypedArray arr, Field field) {
            return arr.getString(index);
        }
    };

    public final Class<? extends Annotation> annotation;
    StyleInjector(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public boolean injectField(Field f, View target, TypedArray arr) throws IllegalAccessException {
        Annotation a = f.getAnnotation(annotation);
        if (a == null) return false;
        int index = getIndex(a);
        if (!arr.hasValue(index)) return true;
        set(f, target, get(index, arr, f));
        return true;
    }

    protected void set(Field f, View target, Object value) throws IllegalAccessException {
        boolean accessible = f.isAccessible();
        f.setAccessible(true);
        try {
            f.set(target, value);
        } finally {
            f.setAccessible(accessible);
        }
    }

    protected abstract int getIndex(Annotation annotation);
    protected abstract Object get(int index, TypedArray arr, Field field);
}

