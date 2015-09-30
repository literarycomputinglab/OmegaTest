/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omegatest.solution3;

/**
 *
 * @author angelo
 * @param <T>
 */
public interface Builder<T extends Annotation.Extension> {
    
     T build(T extension);
    
}
