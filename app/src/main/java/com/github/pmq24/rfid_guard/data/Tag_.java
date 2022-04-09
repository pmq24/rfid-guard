package com.github.pmq24.rfid_guard.data;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Tag.class)
public class Tag_ {
    public static volatile SingularAttribute<Tag, String> rfid;
    public static volatile SingularAttribute<Tag, Boolean> isPurchased;
}
