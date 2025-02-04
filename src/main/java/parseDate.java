import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class parseDate {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static String parseDateOrReturnOriginal(String by) {
        try {
            // System.out.println("z");
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")); // 确保返回的字符串格式一致
        } catch (DateTimeParseException e) {
            return by;
        }
    }

    public static boolean judgeStartAndEnd(String start, String end) {
        try {
            LocalDate dateStart = LocalDate.parse(start);
            LocalDate dateEnd = LocalDate.parse(end);
            return dateStart.isBefore(dateEnd);
        } catch (DateTimeParseException e) {
            return true;
        }
    }

}
