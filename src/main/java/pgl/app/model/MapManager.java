package pgl.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pgl.app.algo.DelaunayEngine;

/**
 * Manages and orchestrates map data.
 * Centralizes entities (Sites, UserPoints, Triangles) and synchronizes geometric calculations.
 * @version 1.0
 */
public class MapManager {

    private final List<Site> sites;
    private final List<UserPoint> userPoints;
    private final List<Triangle> triangles;
    private final DelaunayEngine engine = new DelaunayEngine();

    /**
     * Constructs a new MapManager with initialized empty lists for sites, user points, and triangles.
     */
    public MapManager() {
        this.sites = new ArrayList<>();
        this.userPoints = new ArrayList<>();
        this.triangles = new ArrayList<>();
    }

    /**
     * Adds a reference site to the map and updates the entire map structure.
     *
     * @param site the site to be added
     */
    public void addSite(Site site) {
        this.sites.add(site);
        this.updateAll(); 
    }

    /**
     * Removes a reference site from the map and updates the entire map structure.
     *
     * @param site the site to be removed
     */
    public void removeSite(Site site) {
        this.sites.remove(site);
        this.updateAll();
    }

    /**
     * Adds a user point to the map and immediately assigns it to its closest site.
     * This operation does not trigger a full triangulation recalculation.
     *
     * @param user the user point to be added
     */
    public void addUserPoint(UserPoint user) {
        this.userPoints.add(user);
        this.updateSingleUserAssignment(user); 
    }

    /**
     * Removes a user point from the map.
     *
     * @param user the user point to be removed
     */
    public void removeUserPoint(UserPoint user) {
        this.userPoints.remove(user);
    }

    /**
     * Updates the entire map state by recalculating the Delaunay triangulation 
     * and updating all user assignments.
     */
    public void updateAll() {
        this.updateTriangulation();
        this.updateAllUserAssignments();
    }

    /**
     * Triggers the recalculation of the Delaunay triangulation.
     * Clears existing triangles and recalculates if there are enough sites.
     */
    private void updateTriangulation() {
        this.triangles.clear();
        
        if (this.sites.size() < 3) {
            return; 
        }

        this.triangles.addAll(this.engine.triangulate(this.sites));
        System.out.println("[MapManager] Triangulation updated.");
    }

    /**
     * Recalculates and updates the closest site for all registered user points.
     */
    private void updateAllUserAssignments() {
        if (this.sites.isEmpty()) return;
        for (UserPoint user : this.userPoints) {
            this.updateSingleUserAssignment(user);
        }
    }

    /**
     * Calculates and assigns the closest site for a specific user point based on squared distance.
     *
     * @param user the user point to update
     */
    private void updateSingleUserAssignment(UserPoint user) {
        if (this.sites.isEmpty()) {
            user.setClosestSite(null);
            return;
        }

        Site closest = null;
        double minDistance = Double.MAX_VALUE;

        for (Site site : this.sites) {
            double dist = user.distanceSquaredTo(site.getX(), site.getY());
            if (dist < minDistance) {
                minDistance = dist;
                closest = site;
            }
        }
        user.setClosestSite(closest);
    }

    /**
     * Returns an unmodifiable view of the sites list to preserve strict encapsulation.
     *
     * @return an unmodifiable list of sites
     */
    public List<Site> getSites() { 
        return Collections.unmodifiableList(this.sites); 
    }

    /**
     * Returns an unmodifiable view of the user points list to preserve strict encapsulation.
     *
     * @return an unmodifiable list of user points
     */
    public List<UserPoint> getUserPoints() { 
        return Collections.unmodifiableList(this.userPoints); 
    }

    /**
     * Returns an unmodifiable view of the triangles list to preserve strict encapsulation.
     *
     * @return an unmodifiable list of triangles
     */
    public List<Triangle> getTriangles() { 
        return Collections.unmodifiableList(this.triangles); 
    }

    /**
     * Clears all sites, user points, and triangles from the manager.
     */
    public void clear() {
        this.sites.clear();
        this.userPoints.clear();
        this.triangles.clear();
    }
}