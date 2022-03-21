package com.alpha.mergelanguages.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EntityLanguageUtil {

    private final static String DESCRIPTIONS = "descriptions";
    private final static String LANGUAGE = "language";

    public Object extract(Object obj, String languageCode) {
        Gson gson = new Gson();
        JsonElement src = gson.toJsonTree(obj);
        JsonElement target = new JsonObject();
        extract(src, target, languageCode);
        return gson.fromJson(target, obj.getClass());
    }

    private void extract(JsonElement src, JsonElement target, String languageCode) {
        for (Map.Entry<String, JsonElement> field : src.getAsJsonObject().entrySet()) {
            if (field.getKey().equals(DESCRIPTIONS)) {
                field.getValue().getAsJsonArray().forEach(jsonElement -> {
                    if (languageCode.equals(jsonElement.getAsJsonObject().get(LANGUAGE).getAsString())) {
                        target.getAsJsonObject().add(DESCRIPTIONS, new JsonArray());
                        target.getAsJsonObject().get(DESCRIPTIONS).getAsJsonArray().add(jsonElement);
                    }
                });
            } else if (field.getValue().isJsonObject()) {
                target.getAsJsonObject().add(field.getKey(), new JsonObject());
                extract(field.getValue(), target.getAsJsonObject().get(field.getKey()), languageCode);
            } else if (field.getValue().isJsonArray()) {
                target.getAsJsonObject().add(field.getKey(), new JsonArray());
                for (int i = 0; i < field.getValue().getAsJsonArray().size(); i++) {
                    target.getAsJsonObject().get(field.getKey()).getAsJsonArray().add(new JsonObject());
                    extract(field.getValue().getAsJsonArray().get(i),
                            target.getAsJsonObject().get(field.getKey()).getAsJsonArray().get(i),
                            languageCode);
                }
            }
        }
    }

    public Object remove(Object obj, String languageCode) {
        Gson gson = new Gson();
        JsonElement target = gson.toJsonTree(obj);
        remove(target, languageCode);
        return gson.fromJson(target, obj.getClass());
    }

    private void remove(JsonElement target, String languageCode) {
        for (Map.Entry<String, JsonElement> field : target.getAsJsonObject().entrySet()) {
            if (field.getKey().equals(DESCRIPTIONS)) {
                JsonElement jsonElementToDelete = null;
                for (JsonElement jsonElement : field.getValue().getAsJsonArray()) {
                    if (languageCode.equals(jsonElement.getAsJsonObject().get(LANGUAGE).getAsString()))
                        jsonElementToDelete = jsonElement;
                }
                if (jsonElementToDelete != null) {
                    target.getAsJsonObject().get(DESCRIPTIONS).getAsJsonArray().remove(jsonElementToDelete);
                }
            } else if (field.getValue().isJsonObject()) {
                remove(field.getValue(), languageCode);
            } else if (field.getValue().isJsonArray()) {
                field.getValue().getAsJsonArray().forEach(jsonElement -> remove(jsonElement, languageCode));
            }
        }
    }

    public Object upsert(Object srcObj, Object incomingObj) {
        Gson gson = new Gson();
        JsonElement src = gson.toJsonTree(srcObj);
        JsonElement incoming = gson.toJsonTree(incomingObj);
        upsert(src, incoming);
        return gson.fromJson(src, srcObj.getClass());
    }

    private void upsert(JsonElement src, JsonElement incoming) {
        for (Map.Entry<String, JsonElement> field : incoming.getAsJsonObject().entrySet()) {
            if (field.getKey().equals(DESCRIPTIONS)) {
                field.getValue().getAsJsonArray().forEach(jsonElement -> patch(jsonElement,
                        src.getAsJsonObject().get(field.getKey()).getAsJsonArray()));
            } else if (field.getValue().isJsonObject()) {
                upsert(src.getAsJsonObject().get(field.getKey()), incoming.getAsJsonObject().get(field.getKey()));
            } else if (field.getValue().isJsonArray()) {
                for (int i = 0; i < field.getValue().getAsJsonArray().size(); i++) {
                    upsert(src.getAsJsonObject().get(field.getKey()).getAsJsonArray().get(i),
                            field.getValue().getAsJsonArray().get(i));
                }
            }
        }
    }

    private void patch(JsonElement element, JsonArray array) {
        for (int i = 0; i < array.size(); i++) {
            if (element.getAsJsonObject().get(LANGUAGE).getAsString()
                    .equals(array.get(i).getAsJsonObject().get(LANGUAGE).getAsString())) {
                array.set(i, element);
                return;
            }
        }
        array.add(element);
    }
}