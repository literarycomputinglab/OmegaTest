package it.cnr.ilc.lc.omegatest.solution1;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 *
 * @author oakgen
 */
@NodeEntity
public class Note extends Annotation {

    private TextContent content;
    

    public TextContent getContent() {
        return content;
    }

    public void setContent(TextContent content) {
        this.content = content;
    }

}
