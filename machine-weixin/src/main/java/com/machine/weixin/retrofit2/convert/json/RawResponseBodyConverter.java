package com.machine.weixin.retrofit2.convert.json;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

final class RawResponseBodyConverter implements Converter<ResponseBody, String> {


  @Override public String convert(ResponseBody value) throws IOException {
    try {
        return value.string();

    } finally {
      value.close();
    }
  }
}
