package it.cnr.ilc.lc.omegatest.solution3;

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

    @Override
    public <T extends Annotation.Extension> T build(Builder<T> builder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
