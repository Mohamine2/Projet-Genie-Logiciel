package pgl.app.model;

import java.util.Objects;

public class Edge {
	private Site site1;
	private Site site2;
	
	public Edge(Site site1, Site site2) {
		if(site1 == null || site2 == null) {
			throw new IllegalArgumentException("Edge ne peut pas être nulle");
		}
		this.site1 = site1;
		this.site2 = site2;
	}
	
	public Site getSite1() {
		return site1;
	}
	
	public Site getSite2() {
		return site2;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof Edge other)) {
			return false;
		}
		return (this.site1.getId() == other.site1.getId() && this.site2.getId() == other.site2.getId()) || (this.site1.getId() == other.site2.getId() && this.site2.getId() == other.site1.getId());
	}
	
	@Override
	public int hashCode() {
		int minId = Math.min(site1.getId(), site2.getId());
		int maxId = Math.max(site1.getId(), site2.getId());
		return Objects.hash(minId, maxId);
	}
	
	@Override
	public String toString() {
		return "Edge {" + "site1=" + site1.getId() + ", site2=" + site2.getId() + "}";
	}
}
