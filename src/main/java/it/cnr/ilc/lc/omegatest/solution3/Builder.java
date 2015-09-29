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
public interface Builder<T extends Annotation.Extension> {
    
    public T build(T extension);
    
}
