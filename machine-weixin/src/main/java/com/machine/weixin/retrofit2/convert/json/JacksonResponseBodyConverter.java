package com.machine.weixin.retrofit2.convert.json;

import com.fasterxml.jackson.databind.ObjectReader;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

final class JacksonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
  private final ObjectReader adapter;

  JacksonResponseBodyConverter(ObjectReader adapter) {
    this.adapter = adapter;
  }

  @Override public T convert(ResponseBody value) throws IOException {
    try {
        String entity = value.string();
        if (entity.length() > 0) {
            return adapter.readValue(entity);
        } else {
            return null;
        }
    } finally {
      value.close();
    }
  }
}
