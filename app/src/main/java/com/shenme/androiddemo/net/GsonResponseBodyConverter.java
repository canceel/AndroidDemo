package com.shenme.androiddemo.net;

import com.google.gson.Gson;
import com.shenme.androiddemo.base.BaseResultMember;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by admin on 2016/6/30.
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        BaseResultMember baseResultMember = gson.fromJson(response, BaseResultMember.class);
        if (baseResultMember.getError_code() == 0) {
            return gson.fromJson(response, type);
        } else {
            throw new ResultException(baseResultMember.getError_code(), baseResultMember.getReason());
        }
    }
}
