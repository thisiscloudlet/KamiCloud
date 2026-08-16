package haven;

public class AudioConfig {
    // Cloudy: volumes for sliders in the options
    public static int instrumentsSoundVolume;
    public static int clapSoundVolume;
    public static int quernSoundVolume;
    public static int swooshSoundVolume;
    public static int cauldronSoundVolume;
    public static int squeakSoundVolume;
    public static int butcherSoundVolume;
    public static int whiteDuckCapSoundVolume;
    public static int chippingSoundVolume;
    public static int miningSoundVolume;
    public static int chestTinkVolume;
    public static int creakSoundVolume;
    
    // Clody: loading them at the game start to prevent null pointers
    public static void loadSettings() {
	instrumentsSoundVolume   = Utils.getprefi("instrumentsSoundVolume", 70);
	clapSoundVolume   = Utils.getprefi("clapSoundVolume", 10);
	quernSoundVolume   = Utils.getprefi("quernSoundVolume", 10);
	swooshSoundVolume   = Utils.getprefi("swooshSoundVolume", 75);
	cauldronSoundVolume   = Utils.getprefi("cauldronSoundVolume", 25);
	squeakSoundVolume   = Utils.getprefi("squeakSoundVolume", 25);
	butcherSoundVolume   = Utils.getprefi("butcherSoundVolume", 75);
	whiteDuckCapSoundVolume   = Utils.getprefi("whiteDuckCapSoundVolume", 75);
	chippingSoundVolume   = Utils.getprefi("chippingSoundVolume", 75);
	miningSoundVolume   = Utils.getprefi("miningSoundVolume", 75);
	chestTinkVolume   = Utils.getprefi("chestTinkVolume", 75);
	creakSoundVolume   = Utils.getprefi("creakSoundVolume", 75);

    }
}
