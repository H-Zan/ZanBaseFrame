package com.huzan.zanbaseframe.network.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.huzan.zanbaseframe.base.util.normal.TimeUtil;

import java.io.IOException;
import java.util.Date;

/**
 * Created by ZAN on 2017/7/19.
 * 
 */

public class ItemTypeAdapterFactory implements TypeAdapterFactory {
	public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {
		
		final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
		final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
		
		
		return new TypeAdapter<T>() {
			
			@Override
			public T read(JsonReader in) throws IOException {
				JsonElement jsonElement = elementAdapter.read(in);
				if (jsonElement.isJsonObject()) {
					JsonObject jsonObject = jsonElement.getAsJsonObject();
					if (jsonObject != null && !jsonObject.has("error")) {
						String json = jsonObject.toString();
						String todayDate = TimeUtil.getYMD(new Date());
						String liveIndexList;
						if (json.contains("liveIndex") && !json.contains("liveIndexList")) {
							liveIndexList = json.replaceFirst(todayDate, "liveIndexList");
							JsonParser parser = new JsonParser();
							jsonElement = parser.parse(liveIndexList);
						}
					}
				}
				return delegate.fromJsonTree(jsonElement);
			}
			
			@Override
			public void write(JsonWriter out, T value) throws IOException {
				delegate.write(out, value);
				
			}
		}.nullSafe();
	}
}
