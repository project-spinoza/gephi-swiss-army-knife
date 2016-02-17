package org.projectspinoza.gephiswissarmyknife.sigma.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GraphElement implements Serializable{

    private static final long serialVersionUID = -6443203274757816257L;
    
    private Map<String, Object> attributes = new HashMap<String, Object>();
    private String color;
    private Double size;
    private String label;
    
    public GraphElement(){
        this.color = "rgb(191, 48, 48)";
        this.size = 1.0;
        this.label = "";
    }
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Double getSize() {
        return size;
    }
    public void setSize(Double size) {
        this.size = size;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void addAttribute(String key, Object value){
        this.attributes.put(key, value);
    }
}