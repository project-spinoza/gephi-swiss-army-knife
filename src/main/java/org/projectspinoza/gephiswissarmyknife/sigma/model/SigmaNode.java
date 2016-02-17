package org.projectspinoza.gephiswissarmyknife.sigma.model;

public class SigmaNode extends GraphElement{

    private static final long serialVersionUID = 3156004998125962790L;
    private Double x;
    private Double y;
    private String id;
    
    public SigmaNode(String id){
        super();
        this.id = id;
        this.x = 100 - 200*Math.random();
        this.y = 100 - 200*Math.random();
        this.setLabel(id);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
    
    public String getId() {
        return id;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        SigmaNode other = (SigmaNode) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SigmaNode [x=" + x + ", y=" + y + ", attributes=" + getAttributes() + ", color=" + getColor()
                + ", size=" + getSize() + ", label=" + getLabel() + ", id=" + getId() + "]";
    } 
}