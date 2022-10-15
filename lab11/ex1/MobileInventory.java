package ex1;

import java.util.List;
import java.util.ArrayList;

public class MobileInventory {
    private List<Mobile> mobileList;

    public MobileInventory() {
        mobileList = new ArrayList<>();
    }

    public void addMobile(Mobile m) {
        mobileList.add(m);
    }

    public List<Mobile> getMobileList(Sorting s, SpecType spec) {
        return s.sort(mobileList, spec);
    }
}
