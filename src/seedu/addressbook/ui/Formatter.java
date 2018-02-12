package seedu.addressbook.ui;

import seedu.addressbook.commands.CommandResult;

import java.util.List;

import static seedu.addressbook.common.Messages.*;

/**
 * Formats the messages displayed by the UI
 */
public class Formatter {

    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    public String formatEnterCommandMessage(){
        return LINE_PREFIX + "Enter command: ";
    }
    public String formatWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        return formatMessage(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                version,
                MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                storageFileInfo,
                DIVIDER);
    }

    public String formatGoodbyeMessage(){
        return formatMessage(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }

    public String formatInitFailMessage(){
        return formatMessage(MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);
    }

    public String formatFeedbackMessage(CommandResult result){
        return formatMessage(result.feedbackToUser, DIVIDER);
    }

    public static String formatIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatMessage(formatted.toString());
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
    /**
     * Formats messages with given display figures
     * @param message
     * @return formatted string
     */
    private static String formatMessage(String... message){
        StringBuilder outputLine = new StringBuilder();
        for (int i=0;i<message.length;i++) {
            outputLine.append(LINE_PREFIX + message[i].replace("\n", LS + LINE_PREFIX));
            if(i != message.length-1)
                outputLine.append(LS);
        }
        return outputLine.toString();
    }

}

