package core.view.uiconfig;

import java.awt.Color;
import java.awt.Dimension;

public class Config {
    public static final Color WHITE = Color.WHITE;
    public static final Color TRANSPARENT_BLACK = new Color(0, 0, 0, 0);
    public static final Color DARKEST_BLUE = new Color(13, 27, 42);
    public static final Color DARKER_BLUE = new Color(27, 38, 59);
    public static final Color MIDDLE_BLUE = new Color(65, 90, 119);
    public static final Color LIGHTER_BLUE = new Color(119, 141, 169);
    public static final Color LIGHT_GRAY = new Color(224, 225, 221);
    
    public static final Dimension TOP_PREFERRED_SIZE = new Dimension(400, 70);
    
    public static int HEADER_BLANK_SPACE = 20;
    public static int SEARCH_TEXT_FIELD_LENGTH = 40;
    
    public static String DELETE_ICON = "0";
    public static String ADD_NOTE_ICON = "1";
    public static String SEARCH_ICON = "2";
    public static String FILTER_ICON = "3";
    public static String SETTING_ICON = "4";
    public static String SAVE_ICON = "6";
    public static String GO_BACK_ICON = "7";
    
    public static int DELETE_EVENT_INDEX = 0;
    public static int ADD_NOTE_EVENT_INDEX = 1;
    public static int SEARCH_EVENT_INDEX = 2;
    public static int FILTER_EVENT_INDEX = 3;
    public static int SETTING_EVENT_INDEX = 4;
    public static int SAVE_EVENT_INDEX = 6;
    public static int GO_BACK_EVENT_INDEX = 7;
    
}
