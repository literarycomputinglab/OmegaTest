package it.cnr.ilc.lc.omegatest.solution1;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author oakgen
 */
@NodeEntity
public class TextContent extends Content {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
