/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omegatest.solution2;

/**
 *
 * @author simone
 */
public class ContentExtension extends Annotation.Extension {
    
    private Content content;

        public Content getContent() {
            return content;
        }

        public void setContent(Content content) {
            this.content = content;
        }
  
}
