package com.soleren.pythonsamples.model;

import com.soleren.pythonsamples.data.Const;
import java.util.Comparator;

/**
 * Created by laktionov on 26.08.2017.
 * Класс служит для сортировки сабменю так, что бы заметки всегда были вверху
 */

public class SubMenuComparator implements Comparator<String> {

    @Override
    public int compare(String subMenuOne, String subMenuTwo) {
        if (subMenuOne.equals(Const.NOTES) && !subMenuTwo.equals(Const.NOTES)) {
            return -1;
        } else if (!subMenuOne.equals(Const.NOTES) && subMenuTwo.equals(Const.NOTES)) {
            return 1;
        } else {
            return 0;
        }
    }
}
