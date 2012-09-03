package me.vgv.requestlog.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Vasily Vasilkov (vasily.vasilkov@gmail.com)
 */
public final class GsonUtils {

	private static final GsonBuilder GSON_BUILDER = new GsonBuilder();

	static {
		GSON_BUILDER.setPrettyPrinting();
	}

	public static Gson createGson() {
		return GSON_BUILDER.create();
	}

}
