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

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class Stylist {

    private Stylist() {}

    private static final Map<Class<? extends Annotation>, StyleInjector> INJECTORS = new HashMap<>();
    static {
        for (StyleInjector si : StyleInjector.values()) {
            INJECTORS.put(si.annotation, si);
        }
    }

    public static class StyleInjectException extends RuntimeException {
        public StyleInjectException(String detailMessage) {
            super(detailMessage);
        }
        public StyleInjectException(Throwable throwable) {
            super(throwable);
        }
    }

    public static synchronized void inject(View target, AttributeSet set, int[] attrs) {
        Context c = target.getContext();
        Class<?> clazz = target.getClass();
        TypedArray arr = c.obtainStyledAttributes(set, attrs);
        try {
            for (Field f : clazz.getDeclaredFields()) {
                for (Annotation a : f.getDeclaredAnnotations()) {
                    StyleInjector si = INJECTORS.get(a.annotationType());
                    if (si != null && si.injectField(f, target, arr)) break;
                }
            }
        } catch (IllegalAccessException e) {
            throw new StyleInjectException(e);
        } finally {
            arr.recycle();
        }
    }

}
