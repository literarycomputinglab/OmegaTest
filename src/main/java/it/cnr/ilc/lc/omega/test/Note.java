package it.cnr.ilc.lc.omega.test;

import it.cnr.ilc.lc.omega.entity.Annotation;

/**
 *
 * @author oakgen
 */
public class Note extends Annotation.Extension {

    private String field1;
    private String field2;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

}