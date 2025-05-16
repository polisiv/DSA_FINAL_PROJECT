package core.view.uiconfig;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Config {
    //color
    public static final Color WHITE = Color.WHITE;
    public static final Color LIGHT_GRAY = new Color(224, 225, 221);
    public static final Color DARKER_GRAY = new Color(204, 205, 201);
    public static final Color TRANSPARENT_BLACK = new Color(0, 0, 0, 0);
    //default color sets
    public static Color DARKEST;
    public static Color DARKER;
    public static Color MIDDLE;
    public static Color LIGHTER;
    //color setter
    public static void setBlueTheme() { 
        DARKEST = new Color(13, 27, 42);
        DARKER = new Color(27, 38, 59);
        MIDDLE = new Color(65, 90, 119);
        LIGHTER = new Color(119, 141, 169);
    }
    public static void setGreenTheme() {
        DARKEST = new Color(33, 104, 105);
        DARKER = new Color(33, 104, 105);
        MIDDLE = new Color(73, 160, 120);
        LIGHTER = new Color(156, 197, 161);
    }
    
    
    // other entities
    public static final Dimension TOP_PREFERRED_SIZE = new Dimension(400, 70);
    
    // buttons and panel
    public static final EmptyBorder WINDOW_CONTROL_BUTTON_BORDER = new EmptyBorder(6, 6, 6, 6);
    public static final Cursor WINDOW_CONTROL_BUTTON_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    public static final int ROUNDING_RADIUS = 15;
    public static final Dimension HEADER_IMAGE_INACTIVE_SIZE = new Dimension(22, 22);
    public static final Dimension HEADER_IMAGE_ACTIVE_SIZE = new Dimension(25, 25);
    
    //text fields and text area
    public static final EmptyBorder TEXT_FIELD_BORDER = new EmptyBorder(1, 5, 1, 5);
    public static final Font TITLE_TEXTFIELD_FONT = new Font("Helvetica Neue", 1, 18);
    public static final String SEARCH_TEXT_FIELD_LABEL = "Search";
    
    // length and space
    public static int HEADER_BLANK_SPACE = 20;
    public static int SEARCH_TEXT_FIELD_LENGTH = 40;
    
    // animator
    public static int ANIMATOR_DURATION = 300;
    public static int ANIMATOR_RESOLUTION = 15;
    public static float ANIMATOR_ACCELERATION = 0.5f;
    public static float ANIMATOR_DECELERATION = 0.5f;
    
    // icon and their corresponding events
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
    
    // note list and list item
    public static int NOTE_LIST_DELETABLE = -50;
    public static int NOTE_LIST_OPTIONABLE = 100;
    public static String EMPTY_LIST_MESSAGE = "There is currently no note";
    public static Font LIST_ITEM_FONT = new Font("Helvetica", Font.PLAIN, 15);
    public static String BIN_IMAGE_RESOURCE = "/core/view/icon/0.png";
    public static MigLayout LIST_ITEM_MIGLAYOUT = new MigLayout("fillx", "0[fill]0", "0[]0");
    public static MigLayout LIST_ITEM_PANEL_MIGLAYOUT = new MigLayout("fill, aligny center", "15[]", "fill");
    

}
