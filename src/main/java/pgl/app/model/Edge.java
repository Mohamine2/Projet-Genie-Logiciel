package pgl.app.model;

import java.util.Objects;

/**
 * Represents an undirected edge between two {@link Site} objects.
 * <p>
 * An {@code Edge} is considered defined by its two endpoints. The equality
 * of two edges is independent of the order of the sites (i.e., an edge
 * between A and B is considered equal to an edge between B and A).
 * </p>
 * * @version 1.0
 */
public class Edge {
	private Site site1;
	private Site site2;

	/**
	 * Constructs a new {@code Edge} between the two specified sites.
	 *
	 * @param site1 the first endpoint of the edge.
	 * @param site2 the second endpoint of the edge.
	 * @throws IllegalArgumentException if either {@code site1} or {@code site2} is {@code null}.
	 */
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

	/**
	 * Compares this edge to the specified object.
	 * The result is {@code true} if and only if the argument is an {@code Edge}
	 * connecting the same two sites, regardless of the order in which
	 * they were provided.
	 *
	 * @param o the object to compare this {@code Edge} against.
	 * @return {@code true} if the given object is an edge connecting the
	 * same sites; {@code false} otherwise.
	 */
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