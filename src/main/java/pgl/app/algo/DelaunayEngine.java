package pgl.app.algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pgl.app.model.Edge;
import pgl.app.model.Point;
import pgl.app.model.Site;
import pgl.app.model.Triangle;

public class DelaunayEngine {
	public List<Triangle> triangulate(List<Site> sites){
		if(sites == null) {
			throw new IllegalArgumentException("La liste des sites ne peut pas être nulle.");
		}
		
		List<Triangle> triangles = new ArrayList<>();
		if(sites.size() < 3) {
			return triangles;
		}
		
		Triangle superTriangle = createSuperTriangle(sites);
		triangles.add(superTriangle);
		
		for(Site site : sites) {
			List<Triangle> badTriangles = findBadTriangles(site, triangles);
			List<Edge> polygon = extractPolygon(badTriangles);
			
			triangles.removeAll(badTriangles);
			
			for(Edge edge: polygon) {
				triangles.add(new Triangle((Point) edge.getStart(), (Point) edge.getEnd(), site));
			}
		}
		
		removeSuperTriangleTriangles(superTriangle, triangles);
		return triangles;
	}
	
	private Triangle createSuperTriangle(List<Site> sites) {
		double minX = sites.get(0).getX();
		double minY = sites.get(0).getY();
		double maxX = sites.get(0).getX();
		double maxY = sites.get(0).getY();
		
		for(Site site : sites) {
			minX = Math.min(minX, site.getX());
			minY = Math.min(minY,  site.getY());
			maxX = Math.max(maxX, site.getX());
			maxY = Math.max(maxY,  site.getY());
			
		}
		
		double dx = maxX - minX;
		double dy = maxY - minY;
		double deltaMax = Math.max(dx, dy) * 10;
		
		Point p1 = new Point(minX - deltaMax, minY - deltaMax);
		Point p2 = new Point(minX + 2 * deltaMax, minY - deltaMax);
		Point p3 = new Point(minX + dx/2, maxY + 2 * deltaMax);
		
		return new Triangle(p1, p2, p3);
	}
	
	private List<Triangle> findBadTriangles(Site site, List<Triangle> triangles){
		List<Triangle> badTriangles = new ArrayList<>();
		
		for(Triangle triangle : triangles) {
			if(triangle.containsInCircumcircle(site)) {
				badTriangles.add(triangle);
			}
		}
		
		return badTriangles;
	}
	
	
	private List<Edge> extractPolygon(List<Triangle> badTriangles){
		List<Edge> allEdges = new ArrayList<>();
		
		for(Triangle triangle : badTriangles) {
			allEdges.add(new Edge(triangle.getA(), triangle.getB()));
			allEdges.add(new Edge(triangle.getB(), triangle.getC()));
			allEdges.add(new Edge(triangle.getC(), triangle.getA()));
		}
		
		List<Edge> polygon = new ArrayList<>();
		
		for(Edge edge : allEdges) {
			int count = 0; 
			for(Edge other : allEdges) {
				if (edge.equals(other)) {
					count++;
				}
			}
			if (count == 1) {
				polygon.add(edge);
			}
		}
		
		return polygon;
	}
	
	private void removeSuperTriangleTriangles(Triangle superTriangle, List<Triangle> triangles) {
		Set<Point> superVertices = new HashSet<>();
		superVertices.add(superTriangle.getA());
		superVertices.add(superTriangle.getB());
		superVertices.add(superTriangle.getC());
		
		triangles.removeIf(triangle -> superVertices.contains(triangle.getA()) || superVertices.contains(triangle.getB()) || superVertices.add(triangle.getC()));
	}
	
}