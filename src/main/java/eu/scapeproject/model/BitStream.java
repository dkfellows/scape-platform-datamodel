package eu.scapeproject.model;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import eu.scapeproject.util.CopyUtil;

@XmlRootElement(name = "bitstream", namespace = "http://scapeproject.eu/model")
public class BitStream {

    @XmlElement(name = "type", namespace = "http://scapeproject.eu/model")
    private final Type type;
    @XmlAnyElement
    private final Object technical;
    @XmlElement(name = "identifier", namespace = "http://scapeproject.eu/model")
    private final Identifier identifier;

    private BitStream() {
        this.type = null;
        this.technical = null;
        this.identifier = null;
    }

    private BitStream(Builder b) {
        super();
        this.type = b.type;
        this.technical = b.technical;
        this.identifier = b.identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Object getTechnical() {
        return technical;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "BitStream [type=" + type
                + ", technical=" + technical
                + ", identifier=" + identifier
                + "]";
    }

    public static class Builder {
        private Type type;
        private Object technical;
        private Identifier identifier;

        public Builder() {
            super();
        }

        public Builder(BitStream orig) {
            BitStream copy = CopyUtil.deepCopy(BitStream.class, orig);
            this.technical = copy.technical;
            this.type = copy.type;
            this.identifier = copy.identifier;
        }

        public BitStream build() {
            return new BitStream(this);
        }

        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder technical(Object technical) {
            this.technical = technical;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }
    }

    @XmlEnum
    public enum Type {
        STREAM;
    }
}
