package lesson28;

import java.util.Comparator;

public class FullComparator implements Comparator<Capability> {

    @Override
    public int compare(Capability o1, Capability o2) {

        int resultCompare1 = checkString(o1.getChannelName(), o2.getChannelName());
        int resultCompare2 = checkString(o1.getFingerprint(), o2.getFingerprint());

        return resultCompare1 != 0 ? resultCompare1 : (resultCompare2 != 0 ? resultCompare2 : o1.getDateCreated().compareTo(o2.getDateCreated()));
    }

    private int checkString(String str1, String str2) {

        return str1 == null ? 1 : (str2 == null ? -1 : str1.compareTo(str2));
    }
}
