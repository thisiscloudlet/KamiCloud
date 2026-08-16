package haven.automated;

import haven.Coord;
import haven.Coord2d;
import haven.GameUI;
import haven.Gob;

import java.awt.*;
import java.util.Objects;

import static haven.OCache.*;
//import static haven.automated.CombatDistanceTool.animalDistances;
//import static haven.automated.CombatDistanceTool.vehicleDistance;

public class CombatDistancerLite implements Runnable {
    
    private final GameUI gui;
    
    public CombatDistancerLite(GameUI gui) {
	this.gui = gui;
    }
    
    
    @Override
    public void run() {
	tryToAutoDistance();
    }
    
    private void tryToAutoDistance() {
	if (gui != null && gui.map != null && gui.map.player() != null && gui.fv.current != null) {
	    Double value = -1.0;
	    double addedValue = 0.0;
	    
	    Gob player = gui.map.player();
	    if (player.occupiedGobID != null) {
		Gob vehicle = gui.ui.sess.glob.oc.getgob(player.occupiedGobID);
		if (vehicle != null && vehicle.getres() != null) {
		    if (Objects.equals(vehicle.getres().name, "gfx/terobjs/vehicle/rowboat")) { addedValue = 13.3; }
		    else if (Objects.equals(vehicle.getres().name, "gfx/terobjs/vehicle/dugout")) { addedValue = 7.4; }
		    else if (Objects.equals(vehicle.getres().name, "gfx/terobjs/vehicle/snekkja")) { addedValue = 28.5; //29.35
			}
		    else if (Objects.equals(vehicle.getres().name, "gfx/terobjs/vehicle/knarr")) { addedValue = 54.5; }
		    else if (Objects.equals(vehicle.getres().name, "gfx/kritter/horse/stallion")) { addedValue = 5.4; }
		    else if (Objects.equals(vehicle.getres().name, "gfx/kritter/horse/mare")) { addedValue = 5.4; }
		    else { addedValue = 0; }
		    //addedValue = vehicleDistance.getOrDefault(vehicle.getres().name, 0.0);
		}
	    }
	    Gob enemy = getEnemy();
	    if(enemy != null && enemy.getres() != null){
		if (Objects.equals(enemy.getres().name, "gfx/kritter/adder/adder")) { value = 17.1; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/ant/ant")) { value = 15.2; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/cattle/cattle")) { value = 27.0; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/badger/badger")) { value = 19.9; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/bear/bear")) { value = 24.7; } //24.7
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/boar/boar")) { value = 25.1; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/caveangler/caveangler")) { value = 27.2; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/cavelouse/cavelouse")) { value = 22.0; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/fox/fox")) { value = 18.1; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/horse/horse")) { value = 23.0; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/lynx/lynx")) { value = 20.0; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/mammoth/mammoth")) { value = 30.3; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/moose/moose")) { value = 25.0; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/orca/orca")) { value = 49.25; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/reddeer/reddeer")) { value = 25.0; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/roedeer/roedeer")) { value = 22.0; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/spermwhale/spermwhale")) { value = 112.2; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/goat/wildgoat")) { value = 18.9; }
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/wolf/wolf")) { value = 25.0; } //25
		else if (Objects.equals(enemy.getres().name, "gfx/kritter/wolverine/wolverine")) { value = 21.0; }
		else if (Objects.equals(enemy.getres().name, "gfx/borka/body")) { value = 55.0; }
		else { gui.msg("No match =(", Color.WHITE); }
		//value = animalDistances.get(enemy.getres().name);
	    }
	    if(value != null && value > 0){
		gui.msg("Moving to: " + value + " + " + addedValue, Color.WHITE);
		moveToDistance(value+addedValue);
	    }
	    
	}
    }
    
    private Gob getEnemy() {
	if (gui.fv.current != null) {
	    long id = gui.fv.current.gobid;
	    synchronized (gui.map.glob.oc) {
		for (Gob gob : gui.map.glob.oc) {
		    if (gob.id == id) {
			return gob;
		    }
		}
	    }
	}
	return null;
    }
    
    private void moveToDistance(double distance) {
	try {
	    Gob enemy = getEnemy();
	    if (enemy != null && gui.map.player() != null) {
		double angle = enemy.rc.angle(gui.map.player().rc);
		gui.map.wdgmsg("click", Coord.z, getNewCoord(enemy, distance, angle).floor(posres), 1, 0);
	    } else {
		gui.msg("No visible target.", Color.WHITE);
	    }
	} catch (NumberFormatException e) {
	    gui.error("Wrong distance format. Use ##.###");
	}
    }
    
    private Coord2d getNewCoord(Gob enemy, double distance, double angle) {
	return new Coord2d(enemy.rc.x + distance * Math.cos(angle), enemy.rc.y + distance * Math.sin(angle));
    }
    
}
