package projects.entity;
import provided.entity.EntityBase;
import java.math.BigDecimal;
import java.util.Objects;

public class Material extends EntityBase {
private Integer materialId;
private Integer projectId;
private Unit unit;
private String materialName;
private String instruction;
private Integer numRequired;
private BigDecimal cost;



@Override
public String toString() {
 StringBuilder b= new StringBuilder();
 
 b.append("ID").append(materialId).append(":");
b.append(toFraction (cost));

if(Objects.nonNull(unit) && Objects.nonNull(unit.getUnitId())) {
	String singular = unit.getUnitNameSingular();
	String plural = unit.getUnitNamePlural();
	String word= cost.compareTo(BigDecimal.ONE) > 0? plural : singular;
	
	b.append(word).append(" ");	
}
 b.append(materialName);
 
 if(Objects.nonNull(instruction)) {
	 b.append(",").append(instruction);
 }
 
 return b.toString();
}

public Integer getMaterialId() {
	return materialId;
}
public void setMaterialId(Integer materialId) {
	this.materialId = materialId;
}
public Integer getProjectId() {
	return projectId;
}
public void setProjectId(Integer projectId) {
	this.projectId = projectId;
}
public Unit getUnit() {
	return unit;
}
public void setUnit(Unit unit) {
	this.unit = unit;
}
public String getMaterialName() {
	return materialName;
}
public void setMaterialName(String materialName) {
	this.materialName = materialName;
}
public Integer getNumRequired() {
	return numRequired;
}
public void setNumRequired(Integer numRequired) {
	this.numRequired = numRequired;
}
public BigDecimal getCost() {
	return cost;
}
public void setCost(BigDecimal cost) {
	this.cost = cost;
}


}
