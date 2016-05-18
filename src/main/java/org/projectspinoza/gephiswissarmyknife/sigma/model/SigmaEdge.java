package org.projectspinoza.gephiswissarmyknife.sigma.model;

public class SigmaEdge extends GraphElement{
    private static final long serialVersionUID = -7902367928325958002L;
    private String source;
    private String target;
    private String id;
    private static long edgeCounter;
    
    public SigmaEdge(String source, String target){
        super();
        this.id = ++edgeCounter+"";
        this.source = source;
        this.target = target;
    }
    public String getSource() {
        return source;
    }
    public String getTarget() {
        return target;
    }
    public String getId(){
        return this.id;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SigmaEdge other = (SigmaEdge) obj;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "SigmaEdge [source=" + source + ", target=" + target + ", attributes=" + getAttributes()
            + ", color=" + getColor()+ ", size=" + getSize() + ", label=" + getLabel()
                + ", id=" + getId() + "]";
    } 
     
}