/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omegatest.solution3;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author simone
 * @param <T>
 */

@NodeEntity
public class Source<T extends Content> extends SuperNode {

    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
