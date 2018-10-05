package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import bll.BLLException;
import bll.PromotionManager;
import bo.Promotion;

@Path("/promos")
public class GestionPromos {
	
	
	@GET
	@Path("/all")
	public List<Promotion> getAllPromos() throws BLLException{
		PromotionManager PMger = PromotionManager.getMger();
		List<Promotion> liste = PMger.selectAllPromos();
		return liste;
	}
	
	
}
