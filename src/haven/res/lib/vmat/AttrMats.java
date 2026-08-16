/* Preprocessed source code */
package haven.res.lib.vmat;

import haven.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@haven.FromResource(name = "lib/vmat", version = 39)
public class AttrMats extends VarMats {
    public final Map<Integer, Material> mats;
    public final List<Resource> res;

    public AttrMats(Gob gob, Map<Integer, Material> mats) {
	super(gob);

	this.mats = mats;
	this.res = null;
    }

    public AttrMats(Gob gob, Pair<Map<Integer, Material>, List<Resource>> data) {
	super(gob);
	
	if (gob != null && gob.getres() != null) {
	    Resource res = gob.getres();
	    if ((OptWnd.disableHerbalistTablesVarMatsCheckBox.a && res.name.equals("gfx/terobjs/htable"))
		|| (OptWnd.disableCupboardsVarMatsCheckBox.a && res.name.equals("gfx/terobjs/cupboard"))
		|| (OptWnd.disableChestsVarMatsCheckBox.a && (res.name.equals("gfx/terobjs/chest") || res.name.equals("gfx/terobjs/stonecasket")))
		|| (OptWnd.disableMetalCabinetsVarMatsCheckBox.a && res.name.equals("gfx/terobjs/metalcabinet"))
		|| (OptWnd.disableTrellisesVarMatsCheckBox.a && res.name.equals("gfx/terobjs/plants/trellis"))
		|| (OptWnd.disableSmokeShedsVarMatsCheckBox.a && res.name.equals("gfx/terobjs/smokeshed"))
		|| (OptWnd.disableCheeseRacksVarMatsCheckBox.a && res.name.equals("gfx/terobjs/cheeserack"))
		|| (OptWnd.disableTroughsVarMatsCheckBox.a && res.name.equals("gfx/terobjs/trough"))
		|| (OptWnd.disableAllObjectsVarMatsCheckBox.a)) {
		this.mats = new IntMap<Material>();
		this.res = data.b;
	    }
	    else{
		this.mats = data.a;
		this.res = data.b;
	    }
	}
	
	else {
	this.mats = data.a;
	this.res = data.b;
	}
    }

    public Material varmat(int id) {
	return (mats.get(id));
    }

    public static Pair<Map<Integer, Material>, List<Resource>> decode(Resource.Resolver rr, Message sdt) {
	Map<Integer, Material> ret = new IntMap<>();
	List<Resource> resources = new LinkedList<>();
	int idx = 0;
	while (!sdt.eom()) {
	    Indir<Resource> mres = rr.getres(sdt.uint16());
	    int mid = sdt.int8();
	    Material.Res mat;
	    Resource res = mres.get();
	    resources.add(res);
	    if(mid >= 0)
		mat = res.layer(Material.Res.class, mid);
	    else
		mat = res.layer(Material.Res.class);
	    ret.put(idx++, mat.get());
	}
	return new Pair<>(ret, resources);
    }

    public static void parse(Gob gob, Message dat) {
	gob.setattr(new AttrMats(gob, decode(gob.context(Resource.Resolver.class), dat)));
    }
}

/* >spr: VarSprite */
