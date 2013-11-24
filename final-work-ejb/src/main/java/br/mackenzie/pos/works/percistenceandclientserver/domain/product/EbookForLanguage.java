package br.mackenzie.pos.works.percistenceandclientserver.domain.product;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Embeddable
public class EbookForLanguage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Lob
    @Column(name = "ect_cover")
    @Basic(fetch = FetchType.LAZY)
    private byte[] cover;

    @Lob
    @Column(name = "ect_content")
    @Basic(fetch = FetchType.LAZY, optional = false)
    private byte[] content;

    public byte[] getCover() {
        return this.cover;
    }

    public void setCover(final byte[] cover) {
        this.cover = cover;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(final byte[] content) {
        this.content = content;
    }
}