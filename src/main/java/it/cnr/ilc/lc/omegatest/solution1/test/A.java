package it.cnr.ilc.lc.omegatest.solution1.test;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author oakgen
 */
@NodeEntity
public class A {

    private Long id;

    private R r;

    public R getR() {
        return r;
    }

    public void setR(R c) {
        this.r = c;
    }

}
