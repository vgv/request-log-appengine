package me.vgv.requestlog.handler;

/**
 * @author Vasily Vasilkov (vasily.vasilkov@gmail.com)
 */
public final class HandlerUtils {

	private static final String META = "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>";
	private static final String FAVICON = "<link href=\"data:image/x-icon;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQEAYAAABPYyMiAAAABmJLR0T///////8JWPfcAAAACXBIWXMAAABIAAAASABGyWs+AAAAF0lEQVRIx2NgGAWjYBSMglEwCkbBSAcACBAAAeaR9cIAAAAASUVORK5CYII=\" rel=\"icon\" type=\"image/x-icon\" />";

	public static String getFavicon() {
		return FAVICON;
	}

	public static String getMeta() {
		return META;
	}

}
