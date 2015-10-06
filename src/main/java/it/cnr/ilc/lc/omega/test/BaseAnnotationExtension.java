/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.test;

import it.cnr.ilc.lc.omega.entity.Annotation;

/**
 *
 * @author angelo
 */
public class BaseAnnotationExtension extends Annotation.Extension {

    private String field1;
    
    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

//    @Override
//    public <T extends Annotation.Extension> T build(Builder<T> builder) {
//        this.builder = (Builder<BaseAnnotationExtension>) builder;
//        return (T) this.builder.build(this);
//    }

}
