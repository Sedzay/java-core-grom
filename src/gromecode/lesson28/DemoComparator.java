package gromecode.lesson28;

import java.util.ArrayList;
import java.util.Date;

public class DemoComparator {
    public static void main(String[] args) {

        Capability capability1 = new Capability(1001, null, "rrrr", true, new Date());
        Capability capability2 = new Capability(1005, "test", "rrrr2", false, new Date());
        Capability capability3 = new Capability(800, null, "rrrr", true, new Date());
        Capability capability4 = new Capability(900, "test", "rrrr", false, new Date());



        ArrayList<Capability> capabilities = new ArrayList<>();
        capabilities.add(capability1);
        capabilities.add(capability2);
        capabilities.add(capability3);
        capabilities.add(capability4);

        System.out.println(capabilities);

        capabilities.sort(new IsActiveComparator());

        System.out.println(capabilities);

        capabilities.sort(new FullComparator());
        System.out.println(capabilities);

    }
}
