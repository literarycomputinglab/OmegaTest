/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omegatest.solution3;

/**
 *
 * @author angelo
 */
public class BaseExtensionBuilder implements Builder<BaseAnnotationExtension>{

    private String field1 = "";
        
    public BaseExtensionBuilder field1(String f){
        this.field1 = f;
        return this;
    }
        
    @Override
    public BaseAnnotationExtension build(BaseAnnotationExtension extension) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        extension.setField1(this.field1);
        return extension;
    }
    
}
