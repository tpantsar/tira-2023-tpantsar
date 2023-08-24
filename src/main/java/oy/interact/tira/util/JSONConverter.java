package oy.interact.tira.util;

import org.json.JSONArray;
import org.json.JSONObject;

import oy.interact.tira.model.Coder;

public class JSONConverter {
	private JSONConverter() {
		// Nada
	}

	public static String coderToJSON(Coder coder) {
		JSONObject object = new JSONObject();
		object.put("id", coder.getId());
		object.put("lastName", coder.getLastName());
		object.put("firstName", coder.getFirstName());
		object.put("coderName", coder.getCoderName());
		object.put("phoneNumber", coder.getPhoneNumber());
		if (coder.hasLanguages()) {
			JSONArray languages = new JSONArray();
			for (String language : coder.getLanguages()) {
				languages.put(language);
			}
			object.put("languages", languages);	
		}
		if (coder.hasFriends()) {
			JSONArray friends = new JSONArray();
			for (String language : coder.getFriendIDs()) {
				friends.put(language);
			}
			object.put("friends", friends);	
		}
		return object.toString();
	}

	public static Coder coderFromJSONString(String jsonString) {
		JSONObject jsonObject = new JSONObject(jsonString);
		return coderFromJSONObject(jsonObject);
	}

	public static Coder coderFromJSONObject(JSONObject jsonObject) {
		Coder coder = null;
		coder = new Coder(jsonObject.getString("id"));
		coder.setFirstName(jsonObject.getString("firstName"));
		coder.setLastName(jsonObject.getString("lastName"));
		coder.setPhoneNumber(jsonObject.getString("phoneNumber"));
		coder.setCoderName(jsonObject.getString("coderName"));
		if (jsonObject.has("languages")) {
			JSONArray languages = jsonObject.getJSONArray("languages");
			for (int index = 0; index < languages.length(); index++) {
				String language = languages.getString(index);
				coder.addLanguage(language);
			}
		}
		if (jsonObject.has("friends")) {
			JSONArray friends = jsonObject.getJSONArray("friends");
			for (int index = 0; index < friends.length(); index++) {
				String friendID = friends.getString(index);
				coder.addFriend(friendID);
			}
		}
		return coder;
	}

	public static Coder [] codersFromString(String jsonArrayString) {
		JSONArray arrayObjects = new JSONArray(jsonArrayString);
		return codersFromJSONArray(arrayObjects);
	}

	public static Coder [] codersFromJSONArray(JSONArray array) {
		Coder [] coders = null;
		if (array.length() > 0) {
			coders = (Coder [])new Coder[array.length()];
			for (int index = 0; index < array.length(); index++) {
				JSONObject object = array.getJSONObject(index);
				coders[index] = coderFromJSONObject(object);
				array.put(index, (Object)null);
			}
		}
		return coders;
	}

}
